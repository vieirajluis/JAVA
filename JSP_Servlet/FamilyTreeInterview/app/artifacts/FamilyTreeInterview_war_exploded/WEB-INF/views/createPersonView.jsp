<%--
  Created by IntelliJ IDEA.
  User: vieir
  Date: 2019-04-26
  Time: 11:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Person</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Create Person</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="${pageContext.request.contextPath}/createPerson">
    <table border="0">
        <tr>
            <td>Id</td>
            <td><input type="text" name="personID" value="${person.personID}" /></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" value="${person.name}" /></td>
        </tr>
        <tr>
            <td>Year of Birth</td>
            <td><input type="text" name="yearDOB" value="${person.yearDOB}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit" />
                <a href="personList">Cancel</a>
            </td>
        </tr>
    </table>
</form>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
