package org.interview.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.interview.model.*;
import org.interview.data.util.*;


@WebServlet(urlPatterns = { "/createPerson" })
public class CreatePersonServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public CreatePersonServlet() {
        super();
    }

    // Show person creation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createPersonView.jsp");
        dispatcher.forward(request, response);
    }

    // When the user enters the person information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = Utils.getStoredConnection(request);

        String name = (String) request.getParameter("name");

        int yeardb = 0;
        int personid = 0;

        try {
            personid = Integer.parseInt((String)request.getParameter("personID"));
            yeardb=Integer.parseInt((String)request.getParameter("yearDOB"));
        } catch (Exception e) {}

        IPerson person = new Person(personid, name, yeardb);

        String errorString = null;

        try {
            DB.insertPerson(conn, person);
        } catch (SQLException ex) {
            ex.printStackTrace();
            errorString = ex.getMessage();
        }


        // Store information to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("person", person);

        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createPersonView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the person listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/personList");
        }
    }
}
