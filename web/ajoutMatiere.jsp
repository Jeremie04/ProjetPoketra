<%-- 
    Document   : AjoutTaille
    Created on : 13 déc. 2023, 21:57:16
    Author     : Ranto Jeremy
--%>

<%@page import="model.sac.Unite"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Unite[] unites = (Unite[]) request.getAttribute("unites");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
        <title>Ajout Matiere</title>
    </head>
    <body>
        <div class="container mt-5 col-md-6 shadow p-5 rounded-1 mx-auto">
            <h1 class="text-center">Ajout Matiere</h1>
            <form action="<%=request.getContextPath()%>/MatiereController" method="post">
                <div class="row" >
                    <div class="col-md-2" >
                        Nom :
                    </div>
                    <div class="col-md-10">
                        <input type="text" name="nom_matiere" class="form-control">               
                    </div>                    
                </div>
                <div class="row" >
                    <div class="col-md-2" >
                        Unite :
                    </div>
                    <div class="col-md-10">
                        <select class="form-control" name="id_unite">
                            <%for(Unite unite : unites){%>
                                <option value="<%=unite.getId()%>"><%=unite.getIntitule()%></option>
                            <%}%>
                        </select>            
                    </div>                    
                </div>
                <button type="submit" class="btn btn-primary">Valider</button>
            </form>
        </div>
    </body>
</html>
