package java.com.epam.brest2019.courses.model;

/**
 * Employee model
 */

public class Employee {

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
     * Employee First Name
     */
    private String firstName;

    /**
     * Employee Last Name
     */
    private String lastName;

    /**
     * Employee Patronic Name
     */
    private String patronicName;

    /**
     * Department Name
     */
    private String departmentName;


    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPatronicName(String patronicName) {
        this.patronicName = patronicName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


    public Integer getDepartmentId() {
        return departmentId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronicName() {
        return patronicName;
    }

    public String getDepartmentName() {
        return departmentName;
    }


    @Override
    public String toString() {
        return "Employee{"
                + "departmentId=" + departmentId
                + ", employeeId=" + employeeId
                + ", login='" + login + '\''
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", patronicName='" + patronicName + '\''
                + ", departmentName='" + departmentName + '\''
                + '}';
    }
}
