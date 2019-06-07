package org.interview.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.interview.model.*;
import org.interview.data.util.*;


@WebServlet(urlPatterns = { "/createRelationship" })
public class CreateRelationshipServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public CreateRelationshipServlet() {
        super();
    }

    // Show relationship creation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = Utils.getStoredConnection(request);
        String errorString = null;
        List<IPerson> list = null;
        try {

            list = DB.queryPerson(conn);

        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }


        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("personList", list);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createRelationshipView.jsp");
        dispatcher.forward(request, response);
    }

    // When the user enters the relationship information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = Utils.getStoredConnection(request);

        int personOne = 0;
        int personTwo = 0;
        int roleID = 0;
        int relationshipID = 0;

        try {
            personOne = Integer.parseInt((String)request.getParameter("personOneID"));
            personTwo = Integer.parseInt((String)request.getParameter("personTwoID"));
            relationshipID = Integer.parseInt((String)request.getParameter("relationshipID"));
            roleID=Integer.parseInt((String)request.getParameter("rolesId"));

        } catch (Exception e) {
        }
        IRelationship relationship = new Relationship(relationshipID, new Person(personOne,"",0)
                , new Person(personTwo,"",0),new Roles(roleID,""));

        String errorString = null;

        try {
            DB.insertRelationship(conn, relationship);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store information to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("relationship", relationship);

        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createRelationshipView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the personFamily listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/personFamilyList?personID="+relationship.getPersonOne().getPersonID());
        }
    }
}



