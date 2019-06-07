package org.interview.model;

import java.util.ArrayList;
import java.util.List;

/*
* Entity Person.
* */
public class Person implements IPerson {

    private int personID;
    private String name;
    private int yearDOB;
    private int roleID;
    private String roleDesc;
    private int flagList;
    private boolean visited = false;
    private List<Relationship> relationships= new ArrayList<>();

    public Person() {

    }

    public Person(int personID, String name, int yearDOB) {

        this.setPersonID(personID);
        this.setName(name);
        this.setYearDOB(yearDOB);

    }


    @Override
    public int getPersonID() {
        return personID;
    }

    @Override
    public void setPersonID(int personID) {
        this.personID = personID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getYearDOB() {
        return yearDOB;
    }

    @Override
    public void setYearDOB(int yearDOB) {
        this.yearDOB = yearDOB;
    }

    @Override
    public int getRoleID() {
        return roleID;
    }

    @Override
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    @Override
    public String getRoleDesc() {
        return roleDesc;
    }

    @Override
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public int getFlagList() {
        return flagList;
    }

    @Override
    public void setFlagList(int flagList) {
        this.flagList = flagList;
    }

    @Override
    public boolean isVisited() {
        return visited;
    }

    @Override
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public List<Relationship> getRelationships() {
        return relationships;
    }

    @Override
    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }

    @Override
    public void addRelation(Relationship relation) {
        this.relationships.add(relation);
    }
}


