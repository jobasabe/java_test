<%-- 
    Document   : newRestrctedWord
    Created on : 05/04/2017, 10:43:33 AM
    Author     : José Basabe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Java Test - Restricted Word</title>
    </head>
    <body style="background-color: rgba(0,0,0,.06);">        
        <div style="margin-top: 100px;margin-right: 300px;margin-left: 300px;
             padding: 16px;background-color:white;">
        <h3>New restricted word</h3>
        <form:form method="post" commandName="word">            
            <form:input placeholder="Write the new restricted word here" 
                        path="word" cssClass="mdl-textfield__input" 
                        cssStyle="margin-top:16px;margin-bottom:16px;"/>
            <form:button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" 
                         style="margin-top:16px;margin-bottom:16px;">
                Send
            </form:button> 
            <a href="restricted_word_index.htm" 
               class="mdl-button mdl-js-button mdl-button--raised" 
               style="margin-top:16px;margin-bottom:16px;">
                Return
            </a>
            <br><form:errors path="*"/>
        </form:form>
        </div>
    </body>
</html>