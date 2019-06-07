package org.interview.data.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.interview.model.*;



//The utility class to manipulate data
public class DB {

    //Family List fetcher.
    public static List<IPerson> queryPersonFamily(Connection conn, String code) throws SQLException {


        IPerson person = null;


        try {
            person = DB.findPerson(conn, code);
        } catch (SQLException e) {
           throw e;
        }
        person.setFlagList(1);
        String sql = "select * from selectfamilytree(" + person.getPersonID() + ")";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        List<IPerson> persons = new ArrayList<IPerson>();
        List<Relationship> listRelationship = new ArrayList<Relationship>();
        List<IRoles> listRoles = new ArrayList<IRoles>();

        while (rs.next()) {
            int personOneID = rs.getInt("yourid");
            String personOneName = rs.getString("yourname");
            int personOneYearDOB = rs.getInt("youryeardob");
            int personTwoID = rs.getInt("relid");
            String personTwoName = rs.getString("relname");
            int personTwoYearDOB = rs.getInt("yeardob");
            int roleID = rs.getInt("roleid");
            String roleDesc = rs.getString("roledesc");
            int relationshipID = rs.getInt("relationid");
            Relationship relationship = new Relationship();
            relationship.setPersonOne(new Person(personOneID,personOneName,personOneYearDOB));
            relationship.setPersonTwo(new Person(personTwoID,personTwoName,personTwoYearDOB));
            relationship.setRoleType(new Roles(roleID,roleDesc));
            relationship.setRelationId(relationshipID);
            listRelationship.add(relationship);
        }


        person.setRelationships(listRelationship);
        persons.add(person);

        Utils.createJSONFile(persons,person);

        return persons;
    }

    //Person List fetcher.
    public static List<IPerson> queryPerson(Connection conn) throws SQLException {
        String sql = "select p.* from tblpersons p";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<IPerson> list = new ArrayList<IPerson>();
        while (rs.next()) {
            int personID = rs.getInt("personid");
            String name = rs.getString("name");
            int yearDOB = rs.getInt("yeardob");
            int flagList = 0;
            String roleDesc = null;
            Person person = new Person();
            person.setPersonID(personID);
            person.setName(name);
            person.setYearDOB(yearDOB);
            person.setFlagList(flagList);
            person.setRoleDesc(roleDesc);
            list.add(person);
        }
        return list;
    }


    //Entity Person fetcher.
    public static IPerson findPerson(Connection conn, String code) throws SQLException {
        String sql = "Select a.personid, a.name, a.yeardob from tblpersons a where a.personid =" + code;

        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            String name = rs.getString("Name");
            int yeardb = rs.getInt("yeardob");
            int personid = rs.getInt("personid");
            IPerson person = new Person(personid, name, yeardb);
            return person;
        }
        return null;
    }

    //Update Person record.
    public static void updatePerson(Connection conn, IPerson person) throws SQLException {
        String sql = "Update tblpersons set name =?, yeardob=? where personid=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, person.getName());
        pstm.setInt(2, person.getYearDOB());
        pstm.setInt(3, person.getPersonID());
        pstm.executeUpdate();
    }

    //Insert Person record.
    public static void insertPerson(Connection conn, IPerson person) throws SQLException {
        String sql = "Insert into tblpersons(personid, name,yeardob) values (?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, person.getPersonID());
        pstm.setString(2, person.getName());
        pstm.setInt(3, person.getYearDOB());

        pstm.executeUpdate();
    }

    //Delete Person record.
    public static void deletePerson(Connection conn, String code) throws SQLException {
        String sql = "Delete From tblpersons where personid= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        int personid = 0;
        try {
            personid = Integer.parseInt(code);

        } catch (Exception e) {
        }
        pstm.setInt(1, personid);

        pstm.executeUpdate();
    }

    //Entity Relationship fetcher.
    public static IRelationship findRelationship(Connection conn, String code) throws SQLException {
        String sql = "Select a.relationid,a.personone_id, a.persontwo_id , a.person_role, p1.name as persononename, p2.name as persontwoname from \n" +
                "tblrelationship a \n" +
                "join tblpersons p1 on p1.personid = a.personone_id \n" +
                "join tblpersons p2 on p2.personid = a.persontwo_id where a.relationid =" + code;

        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            int personOne = rs.getInt("personone_id");
            String personOneName = rs.getString("persononename");
            int personTwo = rs.getInt("persontwo_id");
            String personTwoName = rs.getString("persontwoname");
            int relationshipID = rs.getInt("relationid");
            int roleID=rs.getInt("person_role");
            IRelationship relationship = new Relationship(relationshipID,new Person(personOne,personOneName,0)
                    , new Person(personTwo,personTwoName,0),new Roles(roleID,""));
            return relationship;
        }
        return null;
    }

    //Update Relationship record.
    public static void updateRelationship(Connection conn, IRelationship relation) throws SQLException {
        String sql = "Update tblrelationship set personone_id  =?, persontwo_id =?,person_role =? where relationid =? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, relation.getPersonOne().getPersonID());
        pstm.setInt(2, relation.getPersonTwo().getPersonID());
        pstm.setInt(3, relation.getRoleType().getRoleId());
        pstm.setInt(4,relation.getRelationshipId());
        pstm.executeUpdate();
    }

    //Insert Relationship record.
    public static void insertRelationship(Connection conn, IRelationship relation) throws SQLException {
        String sql = "Insert into tblrelationship(relationid,personone_id, persontwo_id,person_role) values (?,?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, relation.getRelationshipId() );
        pstm.setInt(2, relation.getPersonOne().getPersonID() );
        pstm.setInt(3, relation.getPersonTwo().getPersonID() );
        pstm.setInt(4, relation.getRoleType().getRoleId() );


        pstm.executeUpdate();
    }

    //Delete Relationship record.
    public static void deleteRelationship(Connection conn, String code) throws SQLException {
        String sql = "Delete From tblrelationship where relationid= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        int relationid = 0;
        try {
            relationid = Integer.parseInt(code);

        } catch (Exception e) {
        }
        pstm.setInt(1, relationid);

        pstm.executeUpdate();
    }
}
