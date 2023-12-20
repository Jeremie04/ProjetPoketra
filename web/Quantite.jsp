<%-- 
    Document   : Quantite
    Created on : 19 déc. 2023, 14:20:50
    Author     : itu
--%>

<%@page import="model.sac.Taille"%>
<%@page import="model.sac.V_Matiere_Look"%>
<%@page import="model.sac.Modele"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
        <title>Ajout Type</title>
        <style>
            .row{
                margin-bottom: 2%;
            }
        </style>
        <%
            Modele modele = (Modele)request.getAttribute("modele");
            V_Matiere_Look[] vmatlook = (V_Matiere_Look[])request.getAttribute("matiereLook");
            Taille[] tailles = (Taille[])request.getAttribute("taille");
        %>
    </head>
    <body>
        <div class="container mt-5 col-md-6 shadow p-5 rounded-3 mx-auto">
            <h1 class="text-center">Ajout Type</h1>
            <form action="<%=request.getContextPath()%>/QuantiteController" method="post">
                <div class="row" >
                    <input type="hidden" value="<%= modele.getId()%>" name="idModele">
                    <div class="col-md-2" >
                        Taille :
                    </div>
                    <div class="col-md-10">
                        <select name="idTaille" class="form-control">
                            <%
                                for(Taille t : tailles){
                                %>
                                <option value="<%= t.getId()%>"><%= t.getNom()%></option>
                                <%
                                }
                            %>
                        </select>               
                    </div>                    
                </div>
                <div class="row" >
                    <div class="col-md-2" >
                        Matiere :
                    </div>
                    <div class="col-md-10">
                        <select name="idMatiereLook" class="form-control">
                            <%
                                for(V_Matiere_Look mat : vmatlook){
                                %>
                                <option value="<%= mat.getId()%>"><%= mat.getNomMatiere()%></option>
                                <%
                                }
                            %>
                        </select>               
                    </div>                    
                </div>
                <div class="row" >
                    <div class="col-md-2" >
                        Quantite :
                    </div>
                    <div class="col-md-10">
                        <input type="number" name="quantite" class="form-control">
                    </div>                    
                </div>
                <button type="submit" class="btn btn-primary">Valider</button>
            </form>
        </div>
    </body>
</html>
