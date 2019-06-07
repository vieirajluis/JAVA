package org.interview.data.util;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

import org.interview.model.*;

////The utility class to manipulate data
public class Utils {
    public static final String CONNECTION_STRING = "CONNECTION";
    private static final String USER_NAME = "USER_NAME_COOKIE";

    // Store Connection in request attribute.
    // (Information stored only exist during requests)
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(CONNECTION_STRING, conn);
    }

    // Get the Connection object has been stored in attribute of the request.
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(CONNECTION_STRING);
        return conn;
    }


    //Helper to create JSON FamilyTree Nodes
    public static void createJSONFile(List<IPerson> list, IPerson _person)
    {
        List<IPerson> lstGrandParent = new ArrayList<IPerson>();
        List<IPerson> lstParent = new ArrayList<IPerson>();
        List<IPerson> lstChildren = new ArrayList<IPerson>();
        JSONArray objGrandParent = new JSONArray();
        JSONArray objParent = new JSONArray();
        JSONArray objChildren = new JSONArray();
        JSONObject familyList = new JSONObject();
        IPerson person = new Person();


        for (IPerson personFind:list)
        {
            for (IRelationship relation: personFind.getRelationships())
            {
                    switch (relation.getRoleType().getRoleDesc())
                    {
                        case "GRANDPARENT":
                            person = new Person(relation.getPersonTwo().getPersonID(), relation.getPersonTwo().getName() + " - " + relation.getRoleType().getRoleDesc(),relation.getPersonTwo().getYearDOB());

                            if (lstGrandParent.indexOf(relation.getPersonTwo().getName())==-1)
                            {
                                lstGrandParent.add(person);
                            }

                            break;
                        case "FATHER":
                            person = new Person(relation.getPersonTwo().getPersonID(), relation.getPersonTwo().getName() + " - " + relation.getRoleType().getRoleDesc(),relation.getPersonTwo().getYearDOB());

                            if (lstParent.indexOf(relation.getPersonTwo().getName())==-1)
                            {
                                lstParent.add(person);
                            }

                            break;
                        case "MOTHER":
                            person = new Person(relation.getPersonTwo().getPersonID(), relation.getPersonTwo().getName() + " - " + relation.getRoleType().getRoleDesc(),relation.getPersonTwo().getYearDOB());

                            if (lstParent.indexOf(relation.getPersonTwo().getName())==-1)
                            {
                                lstParent.add(person);
                            }

                            break;
                        default:
                            person = new Person(relation.getPersonTwo().getPersonID(), relation.getPersonTwo().getName() + " - " + relation.getRoleType().getRoleDesc(),relation.getPersonTwo().getYearDOB());

                            if (lstChildren.indexOf(relation.getPersonTwo().getName())==-1)
                            {
                                lstChildren.add(person);
                            }

                            break;
                    }
            }
        }

        familyList.put("Name", _person.getName() );
        familyList.put("DOB",_person.getYearDOB() );
        if (lstGrandParent.size()>0 ) {
            for (IPerson grandparent:lstGrandParent) {

                if (!grandparent.isVisited()) {
                    grandparent.setVisited(true);

                    objGrandParent.put(grandparent.getName() + " - " + grandparent.getYearDOB());


                }
            }

            for (IPerson grandparent:lstGrandParent) {
                grandparent.setVisited(false);
            }
        }

        if (lstParent.size()>0 ) {
            for (IPerson parent:lstParent) {

                if (!parent.isVisited()) {
                    parent.setVisited(true);

                    objParent.put( parent.getName() + " - " + parent.getYearDOB());


                }
            }
            for (IPerson parent:lstParent) {
                parent.setVisited(false);
            }
        }

        if (lstChildren.size()>0 ) {
            for (IPerson children:lstChildren) {

                if (!children.isVisited()) {
                    children.setVisited(true);

                    objChildren.put( children.getName() + " - " + children.getYearDOB());


                }
            }
            for (IPerson children:lstChildren) {
                children.setVisited(false);
            }
        }

        if (!objGrandParent.isEmpty())
        {
            familyList.put("GrandParent",objGrandParent);
        }
        if(!objParent.isEmpty())
        {
            familyList.put("Parent",objParent);
        }
        if(!objChildren.isEmpty())
        {
            familyList.put("Members",objChildren);
        }


        //Export JSON file.
        try (FileWriter file = new FileWriter("/Users/vieir/IdeaProjects/FamilyTreeInterview/web/WEB-INF/json/family.json"))
        {
            file.write(familyList.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + familyList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
