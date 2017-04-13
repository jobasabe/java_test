<%-- 
    Document   : result
    Created on : 02/04/2017, 03:17:14 PM
    Author     : José Basabe
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User result</title>
    </head>
    <body>
        <h1>Result</h1>
        <p class="no_valido">
            The username <b>${user_name}</b> is not vaid. Remember that usermane:
        <ul>
            <li>Could not be empty.</li>
            <li>Should be at least 6 characters long.</li>
            <li>Could not contain the next words: <c:forEach items="${data}" var="word">
                    <c:out value="${word.word}" />,                    
                </c:forEach></li>
        </ul>
            We suggest to you a list of valid username that you can use:           
        </p>
        <a href="user.htm">Return</a>
    </body>
</html>