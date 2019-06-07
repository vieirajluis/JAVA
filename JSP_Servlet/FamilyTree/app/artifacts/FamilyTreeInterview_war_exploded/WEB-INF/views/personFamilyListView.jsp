<%--
  Created by IntelliJ IDEA.
  User: vieir
  Date: 2019-04-28
  Time: 1:42 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.io.*, java.net.*"
         import="org.json.JSONObject,org.json.JSONException,java.util.*"%>
<%@ page import="org.json.JSONTokener" %>




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">


</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Family List</h3>

This is demo Simple web application using jsp,servlet &amp; Jdbc to list a Family Tree. <br><br>

<p style="color: red;">${errorString}</p>

<%


    String fileName = "/WEB-INF/json/family.json";


    InputStream ins = application.getResourceAsStream(fileName);

    try
    {
        if(ins == null)
        {
            response.setStatus(response.SC_NOT_FOUND);
        }
        else
        {

            BufferedReader br = new BufferedReader((new InputStreamReader(ins)));
            String data;

            StringBuilder builder = new StringBuilder();

            String json="";
            while((data= br.readLine())!= null)
            {
                //json=json+data;
                builder.append(data);
                //out.println(data+"<br>");
            }
            ins.close();
            br.close();

            JSONObject obj = new JSONObject(builder.toString());



                if (obj.has("GrandParent"))
                {
                    if (obj.get("GrandParent")!=null)
                    {
                        out.write("<html>");

                        out.write("<head><style>\n" +
                                "p {\n" +
                                "  border: 2px solid black;\n" +
                                "  outline: #4CAF50 solid 5px;\n" +
                                "  text-align: center; width: 600px;\n" +
                                "}</style>");

                        out.write("</head>");

                        out.write("<body>");

                        out.write("<p>" +obj.get("GrandParent") +"</p>");
                        out.write("<span style='font-size:50px;'>&#8659;</span>");

                        out.write("</body>");

                        out.write("</html>");




                    }
                }
                if (obj.has("Parent")) {
                    if (obj.get("Parent") != null) {


                        out.write("<html>");

                        out.write("<head><style>\n" +
                                "p {\n" +
                                "  border: 2px solid black;\n" +
                                "  outline: #79ef58 solid 5px;\n" +
                                "  text-align: center; width: 500px;\n" +
                                "}</style>");

                        out.write("</head>");

                        out.write("<body>");


                        out.write("<p>" + obj.get("Parent") +"</p>");
                        out.write("<span style='font-size:50px;'>&#8659;</span>");
                        out.write("</body>");

                        out.write("</html>");

                    }

                }
                if (obj.has("Members")) {
                    if (obj.get("Members") != null) {
                        out.write("<html>");

                        out.write("<head><style>\n" +
                                "p {\n" +
                                "  border: 2px solid black;\n" +
                                "  outline: #5e9af9 solid 5px;\n" +
                                "  text-align: center; width: 500px;\n" +
                                "} </style>");

                        out.write("</head>");

                        out.write("<body>");


                        out.write("<p>" + obj.get("Members") +"</p>");

                        out.write("</body>");

                        out.write("</html>");


                    }
                }



            builder.delete(0,builder.length());

        }
    }
    catch(IOException e)
    {
        out.println(e.getMessage());
    }

%>
<table border="1" cellpadding="5" cellspacing="1" >
<c:choose>
    <c:when test="${not empty personFamilyList}">
            <tr>
                <th>Person Id</th>
                <th>Name</th>
                <th>Year of Birth</th>
                <th>Relationship Id</th>
                <th>Relationship Description</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${personFamilyList}" var="person" >
              <c:forEach items="${person.relationships}" var="relation" >




                <tr>
                    <td>${relation.personTwo.personID}</td>
                    <td>${relation.personTwo.name}</td>
                    <td>${relation.personTwo.yearDOB}</td>
                    <td>${relation.roleType.getRoleId()}</td>
                    <td>${relation.roleType.getRoleDesc()}</td>

                    <td>
                        <a href="editRelationship?relationshipID=${relation.getRelationshipId()}">Edit</a>
                    </td>
                    <td>
                        <a href="deleteRelationship?relationshipID=${relation.getRelationshipId()}">Delete</a>
                    </td>
                </tr>
        </c:forEach>
            </c:forEach>
    </c:when>
    <c:otherwise>
        <p style="color: red;">No records were found!</p>

    </c:otherwise>
</c:choose>

        </table>

<a href="${pageContext.request.contextPath}/personList">Person List</a>      <a href="createRelationship" >Create Relationship</a>



<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>


