package com.epam.brest2019.courses.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
     * Department name.
     */
    private String departmentName;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate localDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate localDate1;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate localDate2;

    private String localDate3;

    public String getLocalDate11() {
        return localDate11;
    }

    public void setLocalDate11(String localDate11) {
        this.localDate11 = localDate11;
    }

    public String getLocalDate22() {
        return localDate22;
    }

    public void setLocalDate22(String localDate22) {
        this.localDate22 = localDate22;
    }

    private String localDate11;
    private String localDate22;

    public LocalDate getLocalDate1() {
        return localDate1;
    }

    public void setLocalDate1(LocalDate localDate1) {
        this.localDate1 = localDate1;
    }

    public LocalDate getLocalDate2() {
        return localDate2;
    }

    public void setLocalDate2(LocalDate localDate2) {
        this.localDate2 = localDate2;
    }

    public String getLocalDate3() {
        return localDate3;
    }

    public void setLocalDate3(String localDate3) {
        this.localDate3 = localDate3;
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
     * Get date-string of registration employee.
     *
     * @return localDateString date-string of registration employee.
     */
    public String getLocalDateString() {
       if (localDate != null)
       {
           String localDateString = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
           return localDateString;
       }
       else return "no date";
    }

//    /**
//     * Get date-string of registration employee.
//     *
//     * @return localDateString date-string of registration employee.
//     */
//    public String getLocalDateString() {
//        LocalDate date = LocalDate.from(localDate);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String localDateString = date.format(formatter);
//        return localDateString;
//    }


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