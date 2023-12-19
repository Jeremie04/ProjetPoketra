<%-- 
    Document   : accueil
    Created on : 17 déc. 2023, 19:45:21
    Author     : ranto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String pages = (String) request.getAttribute("page");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/general.css">
        <title>JSP Page</title>
    </head>
<body class="full">
    <div class="row full">
        
        <div class="col-md-2 shadow h-100 sidebar bg-white">
            <jsp:include page="layOut/sideBar.jsp"/>
        </div>
        
        <div class="col-md-10" style="background-color: #E7E5E6">
            <jsp:include page="layOut/header.jsp" />
            
            
            <jsp:include page="<%=pages%>" />
        </div>
    </body>
</html>
