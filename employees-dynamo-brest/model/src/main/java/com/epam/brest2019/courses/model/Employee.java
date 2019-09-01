package com.epam.brest2019.courses.model;

import java.time.LocalDate;

/**
 * Employee model
 */

public class Employee {

    /**
     * Constructor with parameters.
     *
     * @param departmentId department id.
     * @param login        login.
     * @param firstName    First Name.
     * @param patronicName Patronic Name.
     * @param localDate    Date.
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
     * Department Id.
     */
    private Integer departmentId;

    /**
     * Employee Id.
     */
    private Integer employeeId;

    /**
     * Employee login.
     */
    private String login;

    /**
     * Employee last name.
     */
    private String lastName;

    /**
     * Employee first name.
     */
    private String firstName;

    /**
     * Employee patronic name.
     */
    private String patronicName;

    /**
     * Employee date of registration.
     */
    private LocalDate localDate;

    /**
     * Number of employees in all departments.
     */
    private Integer totalCountEmployeesInAllDepartments;

    /**
     * Filter employee by last name.
     */
    private String filterByLastName;

    /**
     * Set department id.
     *
     * @param departmentId department id.
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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
     * Set the number of employees in all departments.
     *
     * @param totalCountEmployeesInAllDepartments total count employees in all departments.
     */
    public void setTotalCountEmployeesInAllDepartments(Integer totalCountEmployeesInAllDepartments) {
        this.totalCountEmployeesInAllDepartments = totalCountEmployeesInAllDepartments;
    }

    /**
     * Set filter employee by last name.
     *
     * @param filterByLastName filter employee by last name.
     */
    public void setFilterByLastName(String filterByLastName) {
        this.filterByLastName = filterByLastName;
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
     * Get the number of employees in all departments.
     *
     * @return totalCountEmployeesInAllDepartments total count employees in all departments.
     */
    public Integer getTotalCountEmployeesInAllDepartments() {
        return totalCountEmployeesInAllDepartments;
    }

    /**
     * Get filter employee by last name.
     *
     * @return filterByLastName filter employee by last name.
     */
    public String getFilterByLastName() {
        return filterByLastName;
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