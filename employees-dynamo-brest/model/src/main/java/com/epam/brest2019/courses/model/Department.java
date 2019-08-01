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
    public Department(String departmentName) {
        this.departmentName = departmentName;
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

    @Override
    public String toString() {
        return "Department {"
                + "departmentId = " + departmentId
                + ", departmentName = " + departmentName
                + "}";
    }
}

