<%-- 
    Document   : searchSuccess
    Created on : Jun 2, 2010, 8:01:09 PM
    Author     : chetans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Results found</title>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    </head>
    <body>
        <%@include file="../index.jsp" %>
        Search Results are:<br>
        <c:forEach items="${searchResult}" var="result">
            ${result}<br>
        </c:forEach>
</body>
</html>
