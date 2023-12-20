<%-- 
    Document   : creationModele
    Created on : 14 déc. 2023, 14:31:03
    Author     : itu
--%>

<%@page import="model.sac.Look"%>
<%@page import="model.sac.Type"%>
<%@page import="model.sac.Taille"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Type[] types = (Type[])request.getAttribute("types");
    Look[] looks = (Look[])request.getAttribute("looks");
%>
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
    </head>
    <body>
        <div class="container mt-5 col-md-6 shadow p-5 rounded-3 mx-auto">
            <h1 class="text-center">Ajout Type</h1>
            <form action="<%=request.getContextPath()%>/ModeleController" method="post">
                <div class="row" >
                    <div class="col-md-2" >
                        Nom :
                    </div>
                    <div class="col-md-10">
                        <input type="text" name="nom" class="form-control">               
                    </div>                    
                </div>
                <div class="row" >
                    <div class="col-md-2" >
                        Type :
                    </div>
                    <div class="col-md-10">
                        <select name="type" class="form-control">
                            <%
                                for(Type type : types){
                                %>
                                <option value="<%= type.getId()%>"><%= type.getNom()%></option>
                                <%
                                }
                            %>
                        </select>               
                    </div>                    
                </div>
                <div class="row" >
                    <div class="col-md-2" >
                        Look :
                    </div>
                    <div class="col-md-10">
                        <select name="look" class="form-control">
                            <%
                                for(Look look : looks){
                                %>
                                <option value="<%= look.getId()%>"><%= look.getNom()%></option>
                                <%
                                }
                            %>
                        </select>               
                    </div>                    
                </div>
                <button type="submit" class="btn btn-primary">Valider</button>
            </form>
        </div>
    </body>
</html>
