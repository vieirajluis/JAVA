<%--
  Created by IntelliJ IDEA.
  User: vieir
  Date: 2019-04-27
  Time: 6:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html>
<head>

</head>

<body >


<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Person List</h3>

This is demo Simple web application using jsp,servlet &amp; Jdbc to list a Family Tree. <br><br>

<p style="color: red;">${errorString}</p>

<table id="tblList" border="1" cellpadding="6" cellspacing="1" >

<c:choose>
    <c:when test="${not empty personList}">

                <tr>
                    <th>Person Id</th>
                    <th>Name</th>
                    <th>Year of Birth</th>
                    <th>Get Family</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>



    </c:when>
    <c:otherwise>
        <p style="color: red;">No records were found!</p>

    </c:otherwise>

</c:choose>

    <c:forEach items="${personList}" var="person" >

            <tr>
                <td>${person.personID}</td>
                <td>${person.name}</td>
                <td>${person.yearDOB}</td>

                <td>
                    <a href="personFamilyList?personID=${person.personID}">Get Family</a>
                </td>
                <td>
                    <a href="editPerson?personID=${person.personID}">Edit</a>
                </td>
                <td>
                    <a href="deletePerson?personID=${person.personID}">Delete</a>
                </td>
            </tr>
            <br />

    </c:forEach>

</table>

<a href="${pageContext.request.contextPath}/personList">Person List</a>    <a href="createPerson" >Create Person</a>




<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>

