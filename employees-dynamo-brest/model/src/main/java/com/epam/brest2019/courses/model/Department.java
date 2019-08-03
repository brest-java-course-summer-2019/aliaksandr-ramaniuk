package com.epam.brest2019.courses.model;

/**
 * POJO Department for model.
 */
public class Department {

    /**
     * Constructor with parameter.
     *
     * @param departmentName department name.
     */
    public Department(String departmentName, Integer countEmployeesInDepartment) {
        this.departmentName = departmentName;
        this.countEmployeesInDepartment = countEmployeesInDepartment;
    }

    /**
     * Constructor without parameter.
     */
    public Department() {
    }

    /**
     * Department id.
     */
    private Integer departmentId;

    /**
     * Department name.
     */
    private String departmentName;


    /**
     * Number of employees in the department.
     */
    private Integer countEmployeesInDepartment;

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
                + ", countEmployeesInDepartment = " + countEmployeesInDepartment
                + "}";
    }
}

