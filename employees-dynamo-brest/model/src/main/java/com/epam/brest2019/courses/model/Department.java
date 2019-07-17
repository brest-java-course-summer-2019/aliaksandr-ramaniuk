package com.epam.brest2019.courses.model;

/**
 *  POJO Department for model
 */
public class Department {
    /**
     * Department Id
     */
    private Integer departmentId;
    /**
     * Department name
     */
    private String departmentName;
    /**
     * Total employees in Department
     */
    private Integer totalEmployees;


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
     * Set total employees in Department
     */
    public void setTotalEmployees(Integer totalEmployees) {
        this.totalEmployees = totalEmployees;
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
    public Integer getTotalEmployees() {
        return totalEmployees;
    }

    @Override
    public String toString() {
        return "Department{"
                + "departmentId=" + departmentId
                + ", departmentNmae='" + departmentName + '\''
                + ", totalEmployees=" + totalEmployees
                + '}';
    }
}

