package org.interview.model;

/*
 * Entity Relationship.
 * */
public class Relationship implements IRelationship {

    private int relationshipID;
    private IPerson personOne;
    private IPerson personTwo;
    private IRoles roleType;

    public Relationship() {

    }

    public Relationship(int _relationshipID, IPerson _personOne,  IPerson _personTwo, IRoles _roleType) {

        this.relationshipID = _relationshipID;
        this.setPersonOne(_personOne);
        this.setPersonTwo(_personTwo);
        this.roleType=_roleType;
    }

    public int getRelationshipId() {
        return relationshipID;
    }
    public void setRelationId(int _relationshipID) {
        this.relationshipID = _relationshipID;
    }

    public IRoles getRoleType() {
        return roleType;
    }

    public void setRoleType(IRoles roleType) {
        this.roleType = roleType;
    }

    @Override
    public IPerson getPersonOne() {
        return personOne;
    }

    @Override
    public void setPersonOne(IPerson personOne) {
        this.personOne = personOne;
    }

    @Override
    public IPerson getPersonTwo() {
        return personTwo;
    }

    @Override
    public void setPersonTwo(IPerson personTwo) {
        this.personTwo = personTwo;
    }
}
