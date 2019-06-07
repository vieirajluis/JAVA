package org.interview.model;

/*
 * Entity Roles of a Relationship.
 * */
public class Roles implements IRoles {

    private int roleID;
    private String roleDesc;

    public Roles() {

    }

    public Roles(int roleID,String roleDesc) {

        this.roleID = roleID;
        this.roleDesc = roleDesc;
    }

    @Override
    public int getRoleId() {
        return roleID;
    }
    @Override
    public void setRoleId(int _roleID) { this.roleID = _roleID; }

    @Override
    public String getRoleDesc() {
        return roleDesc;
    }
    @Override
    public void setRoleDesc(String _roleDesc) { this.roleDesc = _roleDesc; }


}
