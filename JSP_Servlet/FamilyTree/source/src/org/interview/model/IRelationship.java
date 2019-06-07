package org.interview.model;

public interface IRelationship {
    int getRelationshipId();

    void setRelationId(int _relationshipID);

    IPerson getPersonOne();

    void setPersonOne(IPerson personOne);

    IPerson getPersonTwo();

    void setPersonTwo(IPerson personTwo);

    IRoles getRoleType();

    void setRoleType(IRoles roleType);
}
