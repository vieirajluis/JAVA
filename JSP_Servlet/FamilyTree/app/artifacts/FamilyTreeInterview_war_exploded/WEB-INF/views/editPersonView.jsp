<%--
  Created by IntelliJ IDEA.
  User: vieir
  Date: 2019-04-26
  Time: 11:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Person</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Edit Person</h3>

<p style="color: red;">${errorString}</p>

<c:if test="${not empty person}">
    <form method="POST" action="${pageContext.request.contextPath}/editPerson">
        <input type="hidden" name="personID" value="${person.personID}" />
        <table border="0">
            <tr>
                <td>Id</td>
                <td style="color:red;">${person.personID}</td>
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
                <td colspan = "2">
                    <input type="submit" value="Submit" />
                    <a href="${pageContext.request.contextPath}/personList">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
</c:if>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
