/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import static rc.config.Config.DATABASE;
import static rc.config.Config.DATABASENAME;
import static rc.config.Config.HOST;
import static rc.config.Config.PASSWORD;
import static rc.config.Config.PORT;
import static rc.config.Config.USERNAME;

public class __Connection {

    public Connection getConnect(String nomJDBC, String user, String pwd, String dataBase,String host,int port) {
        Connection con = null;
        try {
            if (nomJDBC.equals("postgres")) {
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/" + dataBase, user, pwd);
                con.setAutoCommit(false);
                System.out.println("[ RC Framework : Postgres Connected ]");
            } else if (nomJDBC.equals("oracle")) {

                Class.forName("oracle.jdbc.driver.OracleDriver");
                con = DriverManager.getConnection("jdbc:oracle:thin:@"+host+":"+port+":orcl", user, pwd);
                con.setAutoCommit(false);
                System.out.println("[ RC Framework : Oracle Connected ]");
            }

        } catch (Exception e) {
            
            e.printStackTrace();

        }
        return con;
    }
    
    public Connection getConnect() {
        Connection con = null;
        try {
            if (DATABASENAME.equals("postgres")) {
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection("jdbc:postgresql://"+HOST+":"+PORT+"/" + DATABASE, USERNAME, PASSWORD);
                con.setAutoCommit(false);
                System.out.println("[ RC Framework : Postgres Connected ]");
            } else if (DATABASENAME.equals("oracle")) {

                Class.forName("oracle.jdbc.driver.OracleDriver");
                con = DriverManager.getConnection("jdbc:oracle:thin:@"+HOST+":"+PORT+":orcl", USERNAME, PASSWORD);
                con.setAutoCommit(false);
                System.out.println("[ RC Framework : Oracle Connected ]");
            }

        } catch (Exception e) {
            
            e.printStackTrace();

        }
        return con;
    }
}

