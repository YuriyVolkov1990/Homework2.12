package homework.homework2_8.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private Integer department;
    private double salary;

    //StringUtils.capitalize(firstName.toLowerCase())
    //StringUtils.capitalize(lastName.toLowerCase())
    public Employee(String firstName, String lastName, Integer department, double salary) {
        this.firstName = firstName;
        this.lastName =lastName;
        this.department = department;
        this.salary = salary;
    }
    public void setDepartment(Integer department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Integer getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public String getFirstName() {
        return StringUtils.capitalize(firstName.toLowerCase());
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return StringUtils.capitalize(lastName.toLowerCase());
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
