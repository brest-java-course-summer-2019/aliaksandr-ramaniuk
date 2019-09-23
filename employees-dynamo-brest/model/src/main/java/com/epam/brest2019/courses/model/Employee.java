package com.epam.brest2019.courses.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * Employee model
 */

public class Employee {

    /**
     * Department id is the foreign key (employee to department).
     */
    private Integer departmentId;

    private String departmentName;

    /**
     * Employee id is the primary key.
     */
    private Integer employeeId;

    private String login;

    private String lastName;

    private String firstName;

    private String patronicName;

    /**
     * Employee date of registration.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate localDate;

    /**
     * Employee date (String) of registration.
     */
    private String localDateView;

    /**
     * Constructor with parameters.
     *
     * @param departmentId department id.
     * @param login        login.
     * @param firstName    First Name.
     * @param patronicName Patronic Name.
     * @param localDate    Date of registration..
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
     * Constructor without parameters.
     */
    public Employee() {
    }

    /**
     * Set department id.
     *
     * @param departmentId department id.
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * Set department name.
     *
     * @param departmentName department name.
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Set employee id.
     *
     * @param employeeId employee id.
     */
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Set employee login.
     *
     * @param login login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Set employee lastName.
     *
     * @param lastName last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Set employee firstName.
     *
     * @param firstName first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Set employee patronicName.
     *
     * @param patronicName patronic name.
     */
    public void setPatronicName(String patronicName) {
        this.patronicName = patronicName;
    }

    /**
     * Set date of registration employee.
     *
     * @param localDate date.
     */
    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * Set date (String) of registration employee.
     *
     * @param localDateView date.
     */
    public void setLocalDateView(String localDateView) {
        this.localDateView = localDateView;
    }


    /**
     * Get department id.
     *
     * @return departmentId department by id).
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Get department name.
     *
     * @return departmentName department name.
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Get employee id.
     *
     * @return employeeId employee by id.
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * Get employee login.
     *
     * @return login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Get employee lastName.
     *
     * @return lastName last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get employee firstName.
     *
     * @return firstName first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get employee patronicName.
     *
     * @return patronicName patronic name.
     */
    public String getPatronicName() {
        return patronicName;
    }

    /**
     * Get date of registration employee.
     *
     * @return localDate date of registration employee.
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * Get date (String) of registration employee.
     *
     * @return localDateView date of registration employee.
     */
    public String getLocalDateView() {
        return localDateView;
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