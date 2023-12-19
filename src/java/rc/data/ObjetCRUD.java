/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.data;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Vector;
import rc.annotation.MyType;
import static rc.config.Config.DATABASE;
import static rc.config.Config.DATABASENAME;
import static rc.config.Config.HOST;
import static rc.config.Config.PASSWORD;
import static rc.config.Config.PORT;
import static rc.config.Config.USERNAME;
import rc.config.__Connection;
import rc.annotation.AClass;
import rc.annotation.AField;

public class ObjetCRUD {

    protected PreparedStatement stmt = null;
    protected ResultSet res = null;
    public static int initialize = 20662023; // Change it , if you need use this number
    protected Method method;
    private ArrayList fieldSpecific = new ArrayList();
    private String sequenceValue;
    private String sequenceValueBefore;
    private int pk;
    private String specific;
    private String idName;
    private ArrayList type = new ArrayList();
    private int line;
    private String tablename;

    public void setTableName (String name){
        tablename = name;
    }

    // Conditions
    private String order;
    private String plus;

    public ObjetCRUD() throws Exception {
        init();
        this.idName = "";
        this.order = "";
        this.plus = "";
    }

    // Initialize types primitifs
    public void init() throws Exception {
        Class c = this.getClass();
        Field[] f = c.getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
            if (f[i].getType().isPrimitive()) {
                method = this.getClass().getMethod("set" + f[i].getName().substring(0, 1).toUpperCase()
                        + f[i].getName().toString().substring(1), f[i].getType());
                if (f[i].getType().toString().contains("int")
                        || f[i].getType().toString().contains("float")
                        || f[i].getType().toString().contains("double")
                        || f[i].getType().toString().contains("long")) {
                    method.invoke(this, initialize);
                }
            }
        }
    }
   
    /*
        All function that we need for this class CRUD
     */
    // All fields not ignored in one class
    public ArrayList<Field> getFieldsNotIgnored() throws Exception {
        Class c = this.getClass();
        Field[] f = c.getDeclaredFields();
        ArrayList<Field> fields = new ArrayList<Field>();
        for (int i = 0; i < f.length; i++) {
            if (f[i].getAnnotation(AField.class) != null) {
                if (!f[i].getAnnotation(AField.class).ignored()) {
                    fields.add(f[i]);
                }
            } else {
                fields.add(f[i]);
            }
        }
        return fields;
    }

    // Transform ArrayList into Object Field
    public Field[] __getFieldsNotIgnored() throws Exception {
        ArrayList<Field> fields = this.getFieldsNotIgnored();
        Field[] __fields = new Field[fields.size()];
        for (int i = 0; i < fields.size(); i++) {
            __fields[i] = (Field) fields.get(i);
        }
        return __fields;
    }

    // Obtain all name fields  of one class
    public String[] getFieldsName() throws Exception {
        Field[] f = this.__getFieldsNotIgnored();
        String[] allFields = new String[f.length];
        for (int i = 0; i < allFields.length; i++) {
            allFields[i] = f[i].getName();
            if (f[i].getAnnotation(AField.class) != null) {
                if ((f[i].getAnnotation(AField.class).type() == MyType.SERIAL)) {
                    fieldSpecific.add(allFields[i] + "//" + "DEFAULT");
                } else if ((f[i].getAnnotation(AField.class).type() == MyType.SEQUENCE)) {
                    this.setSequenceValue(f[i].getAnnotation(AField.class).sequence());
                    this.setSequenceValueBefore(f[i].getAnnotation(AField.class).sequenceBefore());
                    fieldSpecific.add(allFields[i] + "//" + this.getSequenceValue());
                }

                if ((f[i].getAnnotation(AField.class).isId())) {
                    this.setIdName(allFields[i]);
                }
            }
        }
        return allFields;
    }

    // Verif if one field is specific
    public boolean isSpecific(String name) {
        for (int i = 0; i < fieldSpecific.size(); i++) {
            if (name.equals(((String) fieldSpecific.get(i)).split("//")[0])) {
                this.setSpecific(((String) fieldSpecific.get(i)).split("//")[1]);
                return true;
            }
        }
        return false;
    }

    // Obtain all name annotation of attributes
    public String[] __getFieldsName() throws Exception {
        Field[] f = this.__getFieldsNotIgnored();
        String[] allFields = new String[f.length];
        for (int i = 0; i < allFields.length; i++) {
            allFields[i] = f[i].getName();
            if (f[i].getAnnotation(AField.class) != null && !f[i].getAnnotation(AField.class).column().equals("NaN")) {
                allFields[i] = f[i].getAnnotation(AField.class).column();
            }
        }
        return allFields;
    }

    // Obtain all filelds's values of one class
    public Object[] getValuesFields() throws Exception {
        Class c = this.getClass();
        String[] fields = this.getFieldsName();
        Object[] values = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            Method methode = c.getMethod("get" + fields[i].substring(0, 1).toUpperCase()
                    + fields[i].substring(1));

            values[i] = methode.invoke(this);
        }
        return values;
    }

    // Obtain all fields with their values not null
    public ArrayList<String[]> getFieldsAndValuesNotNull() throws Exception {
        ArrayList<String[]> __fieldsAndValuesNotNull = new ArrayList<String[]>();
        String[] f = this.__getFieldsName();
        Object[] fv = this.getValuesFields();
        String name, value;
        Field[] field = this.__getFieldsNotIgnored();
        type.clear();
        for (int i = 0; i < f.length; i++) {
            if ((fv[i] != null
                    && !fv[i].equals(initialize)
                    && !fv[i].equals((long) initialize)
                    && !fv[i].equals((float) initialize)
                    && !fv[i].equals((double) initialize))
                    || (field[i].getAnnotation(AField.class) != null
                    && field[i].getAnnotation(AField.class).isId()
                    && fv[i] != null)) {
                name = f[i] + "";
                value = fv[i] + "";
                __fieldsAndValuesNotNull.add(new String[]{name, value});
                type.add(field[i].getType().toString());
            }
        }
        return __fieldsAndValuesNotNull;
    }

    // Transform ArrayList FieldsAndValuesNotNull into Object String
    public String[][] __getFieldsAndValuesNotNull() throws Exception {
        ArrayList<String[]> fieldsAndValuesNotNull = this.getFieldsAndValuesNotNull();
        String[][] __fieldsAndValuesNotNull = new String[fieldsAndValuesNotNull.size()][2];
        for (int i = 0; i < fieldsAndValuesNotNull.size(); i++) {
            __fieldsAndValuesNotNull[i][0] = ((String[]) (fieldsAndValuesNotNull.get(i)))[0].toString();
            __fieldsAndValuesNotNull[i][1] = ((String[]) (fieldsAndValuesNotNull.get(i)))[1].toString();
        }
        return __fieldsAndValuesNotNull;
    }

    // QueryInsert
    public String[] queryInsert() throws Exception {
        String[][] fieldsAndValuesNotNull = this.__getFieldsAndValuesNotNull();
        String[] retour = new String[2];
        String name = "(";
        String values = "(";
        for (int i = 0; i < fieldsAndValuesNotNull.length; i++) {
            String value = "?";
            if (this.isSpecific(fieldsAndValuesNotNull[i][0])) {
                value = this.getSpecific();

            }
            if (i == fieldsAndValuesNotNull.length - 1) {
                name += fieldsAndValuesNotNull[i][0];
                values += value;
                break;
            }
            if (this.getIdName() == null || !fieldsAndValuesNotNull[i][0].equals(this.getIdName())){
                name += fieldsAndValuesNotNull[i][0] + ",";
                values += value + ",";
            }
        }
        name += ")";
        values += ")";
        retour[0] = name;
        retour[1] = values;
        return retour;
    }

    public String queryFinalInsert(String tableName) throws Exception {
        String retour = "";
        String[] queryInsert = this.queryInsert();
        retour = "INSERT INTO " + tableName + queryInsert[0] + " VALUES " + queryInsert[1];
        return retour;
    }

    public String[] queryUpdate() throws Exception {
        String update = "";
        String where = "";
        String[] retour = new String[2];
        String[][] fieldsAndValuesNotNull = this.__getFieldsAndValuesNotNull();
        for (int i = 0; i < fieldsAndValuesNotNull.length; i++) {
            if (where == "" && fieldsAndValuesNotNull[i][0].equals(this.getIdName())) {
                if (((String) this.type.get(i)).contains("int")
                        || ((String) this.type.get(i)).contains("Integer")
                        || ((String) this.type.get(i)).contains("float")
                        || ((String) this.type.get(i)).contains("Float")
                        || ((String) this.type.get(i)).contains("Double")
                        || ((String) this.type.get(i)).contains("double")) {
                    where = " WHERE " + this.getIdName() + " = " + fieldsAndValuesNotNull[i][1];
                } else {
                    where = " WHERE " + this.getIdName() + " = '" + fieldsAndValuesNotNull[i][1] + "'";
                }

            } else {
                update += fieldsAndValuesNotNull[i][0] + "=" + "?,";
            }
        }
        if (update.contains(",")) {
            retour[0] = update.substring(0, update.length() - 1);
        } else {
            retour[0] = update;
        }
        retour[1] = where;
        return retour;
    }

    public String queryFinalUpdate(String tableName) throws Exception {
        String retour = "";
        String[] queryUpdate = this.queryUpdate();
        retour = "UPDATE " + tableName + " SET " + queryUpdate[0] + queryUpdate[1];
        return retour;
    }

    public String queryCondition() throws Exception {
        String retour = "";
        String[][] fieldsAndValuesNotNull = this.__getFieldsAndValuesNotNull();
        for (int i = 0; i < fieldsAndValuesNotNull.length; i++) {
            if (!fieldsAndValuesNotNull[i][1].equals(initialize + "")) {
                retour += fieldsAndValuesNotNull[i][0] + "=" + "? AND ";
            }
        }
        if (retour.contains("AND")) {
            retour = " WHERE " + retour.substring(0, retour.length() - 5);
        }
        return retour;
    }

    public String queryFinalDelete(String tableName) throws Exception {
        String retour = "";
        String[] queryUpdate = this.queryUpdate();
        retour = "DELETE FROM " + tableName + queryCondition();
        return retour;
    }

    public String queryFinalSelect(String tableName) throws Exception {
        String retour = "";
        String[] queryUpdate = this.queryUpdate();
        retour = "SELECT * FROM " + tableName + queryCondition();
        return retour;
    }

    // Obtain value of specific sequence 
    public String getSequence(Connection con, String name, boolean isClose) throws SQLException {
        Connection __con = con;
        __Connection c = new __Connection();
        if (con == null) {
            __con = c.getConnect(DATABASENAME, USERNAME, PASSWORD, DATABASE, HOST, PORT);
        }
        String query = "SELECT NEXTVAL(?)";
        String sequence = "";
        try {
            stmt = __con.prepareStatement(query);
            stmt.setString(1, name);
            res = stmt.executeQuery();
            while (res.next()) {
                sequence += res.getString(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null && isClose) {
                stmt.close();
            }
            if (__con != null && isClose) {
                __con.close();
            }
        }
        return sequence;
    }

    public void setStmt(PreparedStatement stmt, boolean isAcceptId) throws Exception {
        String[][] FieldAndValue = this.__getFieldsAndValuesNotNull();
        int __index = 1;
        String idName = this.getIdName();
        // C'est dans le cas des delete et select
        if (!isAcceptId) {
            idName = "";
        }
        for (int i = 0; i < FieldAndValue.length; i++) {
            if (FieldAndValue[i][1].equals("null")) {
                System.out.println("[ RC Framework : ATTENTION ! il y a une valeur null ]");
            }
            if (!(FieldAndValue[i][0].toString().equals(idName)) && !(FieldAndValue[i][1].toString().equals(initialize + ""))) {
                if (((String) this.type.get(i)).contains("Integer") || ((String) this.type.get(i)).contains("int")) {
                    stmt.setInt(__index, Integer.parseInt(FieldAndValue[i][1]));
                } else if (((String) this.type.get(i)).contains("Double") || ((String) this.type.get(i)).contains("double")) {
                    stmt.setDouble(__index, Double.parseDouble(FieldAndValue[i][1]));
                } else if (((String) this.type.get(i)).contains("Long") || ((String) this.type.get(i)).contains("long")) {
                    stmt.setLong(__index, Long.parseLong(FieldAndValue[i][1]));
                } else if (((String) this.type.get(i)).toString().contains("Float") || ((String) this.type.get(i)).contains("float")) {
                    stmt.setFloat(__index, Float.parseFloat(FieldAndValue[i][1]));
                } else if (((String) this.type.get(i)).toString().endsWith("Date")) {
                    stmt.setDate(__index, Date.valueOf(FieldAndValue[i][1]));
                } else if (((String) this.type.get(i)).toString().endsWith("Timestamp")) {
                    stmt.setTimestamp(__index, Timestamp.valueOf(FieldAndValue[i][1]));
                } else if (((String) this.type.get(i)).toString().endsWith("LocalDateTime")) {
                    stmt.setObject(__index, LocalDateTime.parse(FieldAndValue[i][1]));
                } else if (((String) this.type.get(i)).toString().endsWith("LocalTime")) {
                    stmt.setObject(__index, LocalTime.parse(FieldAndValue[i][1]));
                } else if (((String) this.type.get(i)).toString().endsWith("boolean") || ((String) this.type.get(i)).toString().endsWith("Boolean")) {
                    stmt.setBoolean(__index, Boolean.parseBoolean(FieldAndValue[i][1]));
                }// We can add here if you have other
                else {
                    stmt.setString(__index, FieldAndValue[i][1]);
                }
                __index++;
            }
        }
    }

    public String tableName() throws Exception {
        Class c = this.getClass();
        if(this.tablename != null) return this.tablename;
        if (c.isAnnotationPresent(AClass.class)) {
            AClass __a = (AClass) c.getAnnotation(AClass.class);
            if (__a.tableName() != "" || __a.tableName() != null) {
                return __a.tableName();
            }
        }
        return this.getClass().getSimpleName();
    }

    // Save
    public void Save(Connection con, boolean isClose) throws Exception {
        Connection __con = con;
        __Connection c = new __Connection();
        if (con == null) {
            __con = c.getConnect(DATABASENAME, USERNAME, PASSWORD, DATABASE, HOST, PORT);
        }
        String tableName = this.tableName();
        String query = this.queryFinalInsert(tableName);
        System.out.println("[ RC Framework : " + query + " ]");
        try {
            stmt = __con.prepareStatement(query);
            this.setStmt(stmt, true);
            stmt.executeUpdate();
            __con.commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null && isClose) {
                stmt.close();
            }
            if (__con != null && isClose) {
                __con.close();
            }
        }
    }

    // Update 
    public void Update(Connection con, boolean isClose) throws Exception {
        Connection __con = con;
        __Connection c = new __Connection();
        if (con == null) {
            __con = c.getConnect(DATABASENAME, USERNAME, PASSWORD, DATABASE, HOST, PORT);
        }
        String tableName = this.tableName();
        String query = this.queryFinalUpdate(tableName);
        System.out.println("[ RC Framework : " + query + " ]");
        try {
            stmt = __con.prepareStatement(query);
            this.setStmt(stmt, true);
            stmt.executeUpdate();
            __con.commit();
        } catch (Exception ex) {
            throw new Exception("UpdateException : Error of your sql ?  : " + ex);
        } finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null && isClose) {
                stmt.close();
            }
            if (__con != null && isClose) {
                __con.close();
            }
        }
    }

    // Delete
    public void Delete(Connection con, boolean isClose) throws Exception {
        Connection __con = con;
        __Connection c = new __Connection();
        if (con == null) {
            __con = c.getConnect(DATABASENAME, USERNAME, PASSWORD, DATABASE, HOST, PORT);
        }
        String tableName = this.tableName();
        String query = this.queryFinalDelete(tableName);
        System.out.println("[ RC Framework : " + query + " ]");
        try {
            stmt = __con.prepareStatement(query);
            this.setStmt(stmt, false);
            stmt.executeUpdate();
            __con.commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null && isClose) {
                stmt.close();
            }
            if (__con != null && isClose) {
                __con.close();
            }
        }
    }

    public int nbSelect(Connection con, int offset, int limit) throws Exception {
        String tableName = this.tableName();
        String query = this.queryFinalSelect(tableName) + " " + this.getPlus() + " " + this.getOrder() + " OFFSET " + offset + " LIMIT " + limit;
        PreparedStatement __stmt = con.prepareStatement(query);
        this.setStmt(__stmt, false);
        ResultSet __res = __stmt.executeQuery();
        int nb = 0;
        while (__res.next()) {
            nb++;
        }
        return nb;
    }

    public void setOrder(String order, String column) {
        this.order = " ORDER BY " + column + " " + order;
    }

    // Obatin all Object select : select
    public void setObjectSelect(PreparedStatement stmt, Object ob) throws Exception {
        Field[] f = this.__getFieldsNotIgnored();
        String[][] FieldAndValue = this.__getFieldsAndValuesNotNull();
        String[] fields = this.getFieldsName();
        int __index = 1;
        for (int i = 0; i < fields.length; i++) {
            method = this.getClass().getMethod("set" + fields[i].substring(0, 1).toUpperCase()
                    + f[i].getName().toString().substring(1), f[i].getType());
            if (f[i].getType().toString().contains("Integer") || f[i].getType().toString().contains("int")) {
                method.invoke(ob, res.getInt(i + 1));
            } else if (f[i].getType().toString().contains("Double") || f[i].getType().toString().contains("double")) {
                method.invoke(ob, res.getDouble(i + 1));
            } else if (f[i].getType().toString().contains("Float") || f[i].getType().toString().contains("float")) {
                method.invoke(ob, res.getFloat(i + 1));
            } else if (f[i].getType().toString().endsWith("Date")) {
                method.invoke(ob, res.getDate(i + 1));
            } else if (f[i].getType().toString().endsWith("Timestamp")) {
                method.invoke(ob, res.getTimestamp(i + 1));
            } else if (f[i].getType().toString().endsWith("Time")) {
                method.invoke(ob, res.getTime(i + 1));
            } else if (f[i].getType().toString().endsWith("LocalDateTime")) {
                method.invoke(ob, LocalDateTime.parse(res.getString(i + 1).replace(" ", "T")));
            } else if (f[i].getType().toString().endsWith("LocalTime")) {
                method.invoke(ob, LocalTime.parse(res.getString(i + 1)));
            } // We can add here if you have other
            else {
                method.invoke(ob, res.getString(i + 1));
            }
            __index++;
        }
    }

    // Select
    public <T> T[] Select(Connection con, boolean isClose, int offset, int limit) throws Exception {
        Connection __con = con;
        __Connection c = new __Connection();
        if (con == null) {
            __con = c.getConnect(DATABASENAME, USERNAME, PASSWORD, DATABASE, HOST, PORT);
        }
        String tableName = this.tableName();
        String query = this.queryFinalSelect(tableName) + " " + this.getPlus() + " " + this.getOrder() + " OFFSET " + offset + " LIMIT " + limit;
        Object[] all = new Object[0];
        System.out.println("[ RC Framework : " + query + " ]");
        try {
            int j = 0;
            int nb = this.nbSelect(__con, offset, limit);
            stmt = __con.prepareStatement(query);
            this.setStmt(stmt, false);
            res = stmt.executeQuery();
            ResultSetMetaData __meta = res.getMetaData();
            all = new Object[nb];
            while (res.next()) {
                Object ob = this.getClass().newInstance();
                for (int i = 1; i <= __meta.getColumnCount(); i++) {
                    this.setObjectSelect(stmt, ob);
                }
                all[j] = ob;
                j++;
            }
        } catch (Exception ex) {
            if (!this.queryFinalSelect(tableName).contains("WHERE")) {
                System.out.println("[ RC Framework : Peut-etre une erreur SQL : Vous avez ajouter une condition de plus , vous devez peut-être ajouter un « WHERE » ou reverifier votre condition plus ou l'ordre des attributs ! ]");
            }
            ex.printStackTrace();
            throw ex;
        } finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null && isClose) {
                stmt.close();
            }
            if (__con != null && isClose) {
                __con.close();
            }
        }
        return (T[]) listCast(this.getClass(), all);
    }

    public static <T> T[] listCast(Class<T> type, Object[] list) throws Exception {
        T[] array = (T[]) Array.newInstance(type, list.length);
        for (int i = 0; i < list.length; i++) {
            array[i] = (T) cast(type, list[i]);
        }
        return array;
    }

    private static <T> T cast(Class<T> clazz, Object obj) throws Exception {
        if (clazz.isInstance(obj)) {
            return (T) obj;
        }
        if (clazz == String.class) {
            return (T) obj.toString();
        }
        // Add more specific casting logic for other types as needed
        throw new IllegalArgumentException("Unsupported cast");
    }
    public Object findById (Connection con) throws Exception{
        boolean opened = false;
        if(con == null){
            con = this.getConnection();
            opened = true;
        }
        try{
            if(!this.primaryKeyIsNull()){
                int id = (int)this.getClass().getDeclaredMethod("getId").invoke(this);
                this.reset(false);
                // this.init();
                this.getClass().getDeclaredMethod("setId", int.class).invoke(this, id);
                System.out.println((String)this.getClass().getDeclaredMethod("getNom").invoke(this));
                return this.Select(con, false, 0, 1)[0];                
            }else{
                throw new Exception("La classe n'a pas de primary key définissez-le grâce à __AField isId ou sinon le primaryKey a une valeur null");
            }
        } finally {
            // TODO: handle exception
            if(con != null && opened){
                con.close();
            }
        }
    }

    public Object findById (Connection con, int id) throws Exception {
        boolean valid = false;
        if (con == null) {
            __Connection c = new __Connection();
            con = c.getConnect(DATABASENAME, USERNAME, PASSWORD, DATABASE, HOST, PORT);
        }
        try {
            String request = "SELECT * FROM " + this.tableName() + " where id = " + id +";";
            System.out.println(request);
            stmt = con.prepareStatement(request);
            this.setStmt(stmt, false);
            res = stmt.executeQuery();
            ResultSetMetaData __meta = res.getMetaData();
            res.next();
            Object ob = this.getClass().newInstance();
            for (int i = 1; i <= __meta.getColumnCount(); i++) {
                this.setObjectSelect(stmt, ob);
            }
            return ob;
        } finally {
            if(con != null && valid){
                con.close();
            }
        }
    }

    public int nbSelectQuery(Connection con, String query) throws Exception {
        PreparedStatement __stmt = con.prepareStatement(query);
        ResultSet __res = __stmt.executeQuery();
        int nb = 0;
        while (__res.next()) {
            nb++;
        }
        this.setLine(nb);
        return nb;
    }

    public Object[][] select(Connection con, String query, boolean isClose) throws Exception {
        System.out.println("[ RC Framework : " + query + " ]");
        Connection __con = con;
        __Connection c = new __Connection();
        if (con == null) {
            __con = c.getConnect(DATABASENAME, USERNAME, PASSWORD, DATABASE, HOST, PORT);
        }
        int nb = nbSelectQuery(__con, query);
        Object[][] retour = new Object[nb][0];
        try {
            int j = 0;
            stmt = __con.prepareStatement(query);
            res = stmt.executeQuery();
            ResultSetMetaData __meta = res.getMetaData();
            while (res.next()) {
                retour[j] = new Object[__meta.getColumnCount()];
                for (int i = 1; i <= __meta.getColumnCount(); i++) {
                    retour[j][i - 1] = res.getObject(i);
                }
                j++;
            }
            __con.commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null && isClose) {
                stmt.close();
            }
            if (__con != null && isClose) {
                __con.close();
            }
        }
        return retour;
    }

    public void executeQuery(Connection con, String query, boolean isClose) throws Exception {
        Connection __con = con;
        try {
            __Connection c = new __Connection();
            if (con == null) {
                __con = c.getConnect(DATABASENAME, USERNAME, PASSWORD, DATABASE, HOST, PORT);
            }
            System.out.println("[ RC Framework : " + query + " ]");
            stmt = __con.prepareStatement(query);
            stmt.executeUpdate();
            __con.commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null && isClose) {
                stmt.close();
            }
            if (__con != null && isClose) {
                __con.close();
            }
        }
    }

    public static void setMethod(Object obj, String attribut, Object parameter) throws Exception {
        if (obj == null || parameter == null)
            return;
        char[] division = attribut.toCharArray();
        division[0] = Character.toUpperCase(division[0]);
        String resultat = new String(division);
        Method setMethod = obj.getClass().getDeclaredMethod("set" + resultat, parameter.getClass());
        setMethod.invoke(obj, parameter);
    }

    public Object reset(boolean all) throws Exception {
        Object instance = this.getClass().getConstructor().newInstance();
        Object value;
        for (Field field : this.getClass().getDeclaredFields()) {
            if(!all && field.isAnnotationPresent(AField.class) // Si c'est un id, ne pas réinitialiser
                && field.getAnnotation(AField.class).isId()){
                    continue;
                }
            // if (field.getType() == Integer.class || field.getType() == Double.class || field.getType() == Float.class) {
            if (field.getType().toString().contains("int")
                || field.getType().toString().contains("float")
                || field.getType().toString().contains("double")
                || field.getType().toString().contains("long")) {
                value = 0;
            } else if (field.getType().toString().contains("boolean")) {
                value = false;
            } else {
                value = null;
            }
            setMethod(instance, field.getName(), value);
        }
        return instance;
    }

    /**
     * @return the sequenceValue
     */
    public String getSequenceValue() {
        return "concat('" + this.getSequenceValueBefore() + "',(SELECT nextval('" + sequenceValue + "'))::varchar)";
    }

    /**
     * @param sequenceValue the sequenceValue to set
     */
    public void setSequenceValue(String sequenceValue) {
        this.sequenceValue = sequenceValue;
    }

    /**
     * @return the specific
     */
    public String getSpecific() {
        return specific;
    }

    /**
     * @param specific the specific to set
     */
    public void setSpecific(String specific) {
        this.specific = specific;
    }

    /**
     * @return the sequenceValueBefore
     */
    public String getSequenceValueBefore() {
        String ValueBefore = sequenceValueBefore;
        for(Field field : this.getClass().getFields()){
            if(field.getAnnotation(AField.class) != null
                && !field.getAnnotation(AField.class).sequenceBefore().equals("")){
                sequenceValueBefore = field.getAnnotation(AField.class).sequenceBefore();
            }
        }
        return ValueBefore;
    }

    /**
     * @param sequenceValueBefore the sequenceValueBefore to set
     */
    public void setSequenceValueBefore(String sequenceValueBefore) {
        this.sequenceValueBefore = sequenceValueBefore;
    }

    /**
     * @return the idName
     */
    public String getIdName() {
        return idName;
    }

    /**
     * @param idName the idName to set
     */
    public void setIdName(String idName) {
        this.idName = idName;
    }

    /**
     * @return the order
     */
    public String getOrder() {
        return order;
    }

    /**
     * @return the plus
     */
    public String getPlus() {
        return plus;
    }

    /**
     * @param plus the plus to set
     */
    public void setPlus(String plus) {
        this.plus = plus;
    }

    /**
     * @return the line
     */
    public int getLine() {
        return line;
    }

    /**
     * @param line the line to set
     */
    public void setLine(int line) {
        this.line = line;
    }

    public boolean havePrimaryKey () throws Exception{
        boolean value = false;
        for (Field field : this.__getFieldsNotIgnored()) {
            if(field.isAnnotationPresent(AField.class) 
                && field.getAnnotation(AField.class).isId()){
                    value = true;
                }
                break;
        }
        return value;
    }

    public boolean primaryKeyIsNull () throws Exception {
        if( this.havePrimaryKey()){
            if( (int) this.getClass().getMethod("getId").invoke(this) != -2066){
                return false;
            }
        }
        return true;
    }

    public Connection getConnection () {
        __Connection c = new __Connection();
        return c.getConnect();
    }

    public String constructSequence(Connection co, String nom_seq)throws Exception{
        String sequence=this.getSequenceValueBefore();
        String numseq = getSequence(co, nom_seq, false);
        int nbzero =this.getPk() - (numseq.length()+this.getSequenceValueBefore().length());
        for(int i=0; i<nbzero; i++){
            sequence+="0";
        }  
        return sequence + numseq;
    }

    public int getPk () {
        return this.pk;
    }
    public void setPk (int num){
        this.pk = num;
    }
}
