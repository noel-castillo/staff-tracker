package com.tp.staffing.model;

import java.util.List;

public class Employee {

    // V A R I A B L E S
    private Integer id;     //Primary Key, Cannot be null
    public String firstName; //Cannot be null
    public String lastName; //Cannot be null

    // C O N S T R U C T O R S

    public Employee() {

    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(Employee that) {
        this.id = that.id;
        this.firstName = that.firstName;
        this.lastName = that.lastName;

    }

    // M E T H O D S

    // G E T T E R S && S E T T E R S
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
