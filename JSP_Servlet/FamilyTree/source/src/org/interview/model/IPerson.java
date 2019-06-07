package org.interview.model;

import java.util.List;

public interface IPerson {
    int getPersonID();

    void setPersonID(int personID);

    String getName();

    void setName(String name);

    int getYearDOB();

    void setYearDOB(int yearDOB);

    int getRoleID();

    void setRoleID(int roleID);

    String getRoleDesc();

    void setRoleDesc(String roleDesc);

    int getFlagList();

    void setFlagList(int flagList);

    boolean isVisited();

    void setVisited(boolean visited);

    List<Relationship> getRelationships();

    void setRelationships(List<Relationship> relationships);

    void addRelation(Relationship relation);
}
