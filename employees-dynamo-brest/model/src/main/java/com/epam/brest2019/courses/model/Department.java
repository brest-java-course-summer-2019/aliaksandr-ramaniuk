package com.epam.brest2019.courses.model;

/**
 * POJO Department for model.
 */
public class Department {

    /**
     * Department id is the primary key.
     */
    private Integer departmentId;

    private String departmentName;

    private String departmentAccessRights;

    /**
     * Number of employees in the department.
     */
    private Integer countEmployeesInDepartment;

    /**
     * Constructor with parameter.
     *
     * @param departmentName department name.
     * @param departmentAccessRights department access rights.
     */
    public Department(String departmentName, String departmentAccessRights) {
        this.departmentName = departmentName;
        this.departmentAccessRights = departmentAccessRights;
    }

    /**
     * Constructor without parameter.
     */
    public Department() {
    }

    /**
     * Set department id (departmentId).
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
     * Set department access rights.
     *
     * @param departmentAccessRights department access rights.
     */
    public void setDepartmentAccessRights(String departmentAccessRights) {
        this.departmentAccessRights = departmentAccessRights;
    }

    /**
     * Set the number of employees in the department.
     *
     * @param countEmployeesInDepartment count employees in department.
     */
    public void setCountEmployeesInDepartment(Integer countEmployeesInDepartment) {
        this.countEmployeesInDepartment = countEmployeesInDepartment;
    }

    /**
     * Get department id.
     *
     * @return departmentId department by id.
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
     * Get department access rights.
     *
     * @return departmentAccessRights department access rights.
     */
    public String getDepartmentAccessRights() {
        return departmentAccessRights;
    }

    /**
     * Get the number of employees in the department.
     *
     * @return countEmployeesInDepartment count employees in department.
     */
    public Integer getCountEmployeesInDepartment() {
        return countEmployeesInDepartment;
    }

    @Override
    public String toString() {
        return "Department {"
                + "departmentId = " + departmentId
                + ", departmentName = " + departmentName
                + ", departmentAccessRights = " + departmentAccessRights
                + ", countEmployeesInDepartment = " + countEmployeesInDepartment
                + "}";
    }
}



