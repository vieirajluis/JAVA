<%--
  Created by IntelliJ IDEA.
  User: vieir
  Date: 2019-04-26
  Time: 11:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Home Page</h3>

This is an example of a web application using JSP, Servlet & Jdbc. <br><br>
<b>It includes the following functions:</b>
<ul>

    <li>Family Tree Details</li>
    <li>Person Details</li>
    <li>Create Relationship</li>
    <li>Edit Relationship</li>
    <li>Delete Relationship</li>
</ul>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
