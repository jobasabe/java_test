<%-- 
    Document   : user
    Created on : 02/04/2017, 01:36:32 PM
    Author     : José Basabe
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta content="UTF-8">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">        
    </head>    
    <body style="background-color: rgba(0,0,0,.06);">        
        <div style="margin-top: 100px;margin-right: 300px;margin-left: 300px;
             padding: 16px;background-color:white;">
            <h3>New user</h3>
            <form:form method="post" commandName="user" cssStyle="margin:16px;">
                <form:input placeholder="Write your username here" path="userName" 
                            cssClass="mdl-textfield__input" 
                            cssStyle="margin-top:16px;margin-bottom:16px;"/>
                <form:button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" 
                             style="margin-top:16px;margin-bottom:16px;">Send
                </form:button> 
                <a href="index.htm" class="mdl-button mdl-js-button mdl-button--raised" 
                   style="margin-top:16px;margin-bottom:16px;">
                    Return
                </a>
                    <br><form:errors path="*"/>     
                    <c:if test="${showSuggested==true}">
                        We suggest to you a list of valid username:  
                        <ul>
                            <c:forEach items="${suggests}" var="word">
                                <li>
                                    <c:out value="${word}" />
                                </li>                                                                      
                            </c:forEach> 
                        </ul>
                    </c:if>
            </form:form>
        </div>
    </body>
</html>