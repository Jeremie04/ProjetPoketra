<%-- 
    Document   : matiereParLook
    Created on : 13 déc. 2023, 22:29:36
    Author     : Ranto Jeremy
--%>

<%@page import="model.sac.Matiere"%>
<%@page import="model.sac.Look"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Look[] looks = (Look[]) request.getAttribute("looks");
    Matiere[] matieres = (Matiere[]) request.getAttribute("matieres");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container mt-5 col-md-6 shadow p-5 rounded-1 mx-auto">
            <h1 class="text-center">Matiere Par Look</h1>
            <form action="">
                <div class="row" >
                    <div class="col-md-2" >
                        Look :
                    </div>
                    <div class="col-md-8">
                        <select class="form-control" name="id_look">
                            <%for(Look look : looks){%>
                                <option value="<%=look.getId()%>"><%=look.getNom()%></option>
                            <%}%>
                        </select>           
                    </div> 
                    <div class="col-md-2">
                        <button class="btn btn-primary">Voir</button>
                    </div>
                </div>
                        
                <div class="row mt-5">
                    <ul>
                        <% for(Matiere matiere : matieres){ %>
                            <li><%=matiere.getNom()%></li>
                        <% } %>
                    </ul>
                </div>
            </form>
        </div>
    </body>
</html>
