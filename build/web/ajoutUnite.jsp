<%-- 
    Document   : AjoutTaille
    Created on : 13 déc. 2023, 21:57:16
    Author     : Ranto Jeremy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
        <title>Ajout Unite</title>
    </head>
    <body>
        <div class="container mt-5 col-md-6 shadow p-5 rounded-1 mx-auto">
            <h1 class="text-center">Ajout Unite</h1>
            <form action="<%=request.getContextPath()%>/UniteController" method="post">
                <div class="row" >
                    <div class="col-md-2" >
                        Nom :
                    </div>
                    <div class="col-md-10">
                        <input type="text" name="nom_unite" class="form-control">               
                    </div>                    
                </div>
                <button type="submit" class="btn btn-primary">Valider</button>
            </form>
        </div>
    </body>
</html>
