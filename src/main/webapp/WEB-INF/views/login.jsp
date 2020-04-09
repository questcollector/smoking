<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
</head>
<body>
    <form action="/login" method="POST">
        
        <input type="text" name="username"/> <br>
        <input type="password" name="password"/> <br>
        <input type="submit" value="확인"/> <br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <script src="<c:url value='/js/login.js'/>"></script>
</body>
</html>
