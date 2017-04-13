<%-- 
    Document   : suggestionIndex
    Created on : 04/04/2017, 10:01:25 AM
    Author     : José Basabe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
        <title>Restricted Word Index</title>
    </head>
    <body style="background-color: rgba(0,0,0,.06);">        
        <div style="margin-top: 100px;margin-right: 300px;margin-left: 300px;
             padding: 16px;background-color:white;">          
        <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" 
               style="width: 100%;">
            <h3>Restricted Word</h3>
            <thead>
            <th class="mdl-data-table__cell--non-numeric">Word</th>
                <th>Action</th>
            </thead>
            <tbody>
                <c:forEach items="${data}" var="word">
                    <tr>
                        <td class="mdl-data-table__cell--non-numeric">
                            <c:out value="${word.word}" /></td>
                        <td>
                            <a href="delete_restricted_word.htm?word=${word.word}">
                            <i class="material-icons">delete</i></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
       <a class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" 
          style="margin-top:16px;margin-bottom:16px;" href="new_restricted_word.htm">Add new</a>  
          <a class="mdl-button mdl-js-button mdl-button--raised" 
             style="margin-top:16px;margin-bottom:16px;" href="index.htm">Return</a>
        </div>
    </body>
</html>