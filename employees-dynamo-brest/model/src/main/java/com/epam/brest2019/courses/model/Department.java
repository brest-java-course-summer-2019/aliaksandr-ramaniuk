package com.epam.brest2019.courses.model;

/**
 *  POJO Department for model
 */
public class Department {

    /**
     * Constructor with department name.
     */
      public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Constructor without parameters
     */
    public Department() {
    }

    /**
     * Department Id
     */
    private Integer departmentId;
    /**
     * Department name
     */
    private String departmentName;

    /**
     * Set department Id
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * Set department name
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Get department Id
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Get department name
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Get total employees in Department
     */

    @Override
    public String toString() {
        return "Department {"
                + "departmentId = " + departmentId
                + ", departmentName = " + departmentName
                + "}";
    }
}

