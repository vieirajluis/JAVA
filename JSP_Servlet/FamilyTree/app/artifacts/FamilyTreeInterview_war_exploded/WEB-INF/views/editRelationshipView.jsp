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
    <title>Edit Relationship</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Edit Relationship</h3>

<p style="color: red;">${errorString}</p>

<c:if test="${not empty relation}">
    <form method="POST" action="${pageContext.request.contextPath}/editRelationship">
        <input type="hidden"  name="relationshipID" value="${relation.getRelationshipId()}" />
        <table border="0">
            <tr>
                <th>Person Id|</th>
                <th>Person Name|</th>
                <th>Family Member Id|</th>
                <th>Family Member Name|</th>
                <th>Relationship</th>
            </tr>
            <tr>
                <td style="color:red;text-align: right"><input type="text" readonly="true" name="personOneID" value="${relation.personOne.personID}" />|</td>
                <td style="color:red;text-align: right"><input type="text" readonly="true" name="personOneName" value="${relation.personOne.name}" />| </td>
                <td style="color:red;text-align: right"><input type="text" readonly="true" name="personTwoID" value="${relation.personTwo.personID}" />|</td>
                <td style="color:red;text-align: right"><input type="text" readonly="true" name="personTwoName" value="${relation.personTwo.name}" />| </td>
                <td><select name="rolesId">
                    <option value="">Select Relationship</option>
                    <option value="1">SPOUSE</option>
                    <option value="2">MOTHER</option>
                    <option value="3">FATHER</option>
                    <option value="4">SIBLING</option>
                    <option value="5">GRANDPARENT</option>
                    <option value="6">OTHER</option>
                    <option value="7">NONE</option>
                </select></td>
            </tr>

            <tr>
                <td colspan = "2">
                    <input type="submit" value="Submit" />
                    <a href="${pageContext.request.contextPath}/personFamilyList?personID=${relation.personOne.personID}">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
</c:if>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
