package com.epam.brest2019.courses.model;


import java.time.LocalDate;

/**
 * Employee model
 */

public class Employee {

    /**
     * Constructor with department id.
     * Employee login
     * Employee Last Name
     * Employee First Name
     * Employee Patronic Name
     * Employee Date
     */
    public Employee(Integer departmentId, String login, String lastName, String firstName, String patronicName, LocalDate localDate) {
        this.departmentId = departmentId;
        this.login = login;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronicName = patronicName;
        this.localDate = localDate;
    }

    /**
     * Constructor without parameters
     */

    public Employee() {
    }

    /**
     * Department Id
     */
    private Integer departmentId;

    /**
     * Employee Id
     */
    private Integer employeeId;

    /**
     * Employee login
     */
    private String login;

    /**
     * Employee Last Name
     */
    private String lastName;

    /**
     * Employee First Name
     */
    private String firstName;

    /**
     * Employee Patronic Name
     */
    private String patronicName;

     /**
     * Employee date of registration
     */
    private LocalDate localDate;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getLogin() {
        return login;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronicName() {
        return patronicName;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPatronicName(String patronicName) {
        this.patronicName = patronicName;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "Employee {" +
                "departmentId = " + departmentId
                + ", employeeId = " + employeeId
                + ", login = " + login
                + ", lastName = " + lastName
                + ", firstName = " + firstName
                + ", patronicName = " + patronicName
                + ", localDate = " + localDate
                + "}";
    }
}