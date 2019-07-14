package java.com.epam.brest2019.courses.model;

import org.junit.Assert;
import org.junit.Test;

public class DepartmentTest {

    Department department = new Department();

    @Test
    public void getDepartentId() {
        department.setDepartmentId(5);
        Assert.assertTrue(department.getDepartmentId().equals(5));
    }

    @Test
    public void getDepartentName() {
        department.setDepartmentName("Игрок");
        Assert.assertTrue(department.getDepartmentName().equals("Игрок"));
    }
}
