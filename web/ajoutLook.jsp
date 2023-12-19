<%-- 
    Document   : AjoutLook
    Created on : 13 déc. 2023, 22:21:43
    Author     : Ranto Jeremy
--%>

<%@page import="model.sac.Matiere"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
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
            <h1 class="text-center">Ajout de Look</h1>
            <form action="">               
                <div class="row" >
                    <div class="col-md-2" >
                        Nom :
                    </div>
                    <div class="col-md-10">
                        <input type="text" name="nom" class="form-control">               
                    </div>                    
                </div>

                <div class="row">
                    <h3 class="mt-3">Matieres Premieres</h3>
                    <%for(Matiere m : matieres){%>
                        <div class="list-group-item list-group-item-action">
                            <div class="custom-control custom-checkbox">
                              <input type="checkbox" name="id_matieres[]" class="custom-control-input" id="<%=m.getNom()%>" value="<%=m.getId()%>">
                              <label class="custom-control-label" for="<%=m.getNom()%>"><%=m.getNom()%></label>
                            </div>
                        </div>
                    <%}%>              
                </div>

                <button type="submit" class="btn btn-primary">Valider</button>
            </form>
        </div>
    </body>
</html>
