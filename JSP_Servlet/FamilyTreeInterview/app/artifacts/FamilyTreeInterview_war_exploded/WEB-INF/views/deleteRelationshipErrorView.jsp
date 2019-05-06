<%--
  Created by IntelliJ IDEA.
  User: vieir
  Date: 2019-05-05
  Time: 10:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Relationship</title>
</head>

<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Delete Relationship</h3>

<p style="color: red;">${errorString}</p>
<a href="personFamilyList">Family List</a>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>

