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


@WebServlet(urlPatterns = { "/personFamilyList" })
public class PersonFamilyServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;


    public PersonFamilyServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = Utils.getStoredConnection(request);

        String code = (String) request.getParameter("personID");
        String errorString = null;
        List<IPerson> list = null;
        try {

            if (code!=null)
            {
                list= DB.queryPersonFamily(conn,code);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("personFamilyList", list);



            // Store the information in the request attribute, before forward to views.
            request.setAttribute("errorString", errorString);
        // Forward to /WEB-INF/views/personFamilyListView.jsp
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/personFamilyListView.jsp");
            dispatcher.forward(request, response);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
