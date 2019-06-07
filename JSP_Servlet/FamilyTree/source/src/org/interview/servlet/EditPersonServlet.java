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


@WebServlet(urlPatterns = { "/editPerson" })
public class EditPersonServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public EditPersonServlet() {
        super();
    }

    // Show person edit page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = Utils.getStoredConnection(request);

        String code = (String) request.getParameter("personID");

        IPerson person = null;

        String errorString = null;

        try {
            person = DB.findPerson(conn, code);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // If no error.
        // The person does not exist to edit.
        // Redirect to personList page.
        if (errorString != null && person == null) {
            response.sendRedirect(request.getServletPath() + "/personList");
            return;
        }

        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("person", person);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editPersonView.jsp");
        dispatcher.forward(request, response);

    }

    // After the user modifies the person information, and click Submit.
    // This method will be executed.
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
        } catch (Exception e) {
        }
        Person person = new Person(personid, name, yeardb);

        String errorString = null;

        try {
            DB.updatePerson(conn, person);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("person", person);

        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editPersonView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the person listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/personList");
        }
    }
}
