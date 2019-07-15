package java.com.epam.brest2019.courses.model;


import org.junit.Assert;
import org.junit.Test;

public class DepartmentTest {

    Department department = new Department();

    @Test
    public void getDepartmentId() {
        department.setDepartmentId(5);
        Assert.assertTrue(department.getDepartmentId().equals(5));
    }

    @Test
    public void getDepartmentName() {
        department.setDepartmentName("Игрок");
        Assert.assertTrue(department.getDepartmentName().equals("Игрок"));
    }

    @Test
    public void getTotalEmployees() {
        department.setTotalEmployees(7);
        Assert.assertTrue(department.getTotalEmployees().equals(7));
    }
}
