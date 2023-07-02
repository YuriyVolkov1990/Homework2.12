package homework.homework2_8.service;

import homework.homework2_8.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    List<Employee> employeeList = Arrays.asList(
            new Employee("ivan", "ivanov", 1, 1000),
            new Employee("masha", "ivanova", 2, 100),
            new Employee("dasha", "petrova", 3, 10000),
            new Employee("pasha", "petrov", 1, 900),
            new Employee("sasha", "ivanov", 2, 1000000.0011)
    );
    @BeforeEach
    void setup() {
        when(employeeService.getAll()).thenReturn(employeeList);
    }
    @Test
    void test() {
        System.out.println(departmentService.getEmployeeByDepartment(1));
    }
    @Test
    void testSum() {
        double actual = departmentService.getEmployeeSalarySum(2);
        assertEquals(1000100.0,actual, 0.01);
    }
    @Test
    void testMin() {
        double actual = departmentService.getEmployeeWithMinSalary(1);
        assertEquals(900.0,actual, 0.01);
    }
    @Test
    void testMax() {
        double actual = departmentService.getEmployeeWithMaxSalary(3);
        assertEquals(10000,actual, 0.01);
    }
    @Test
    void getAllByDepartment() {
        List<Employee> actual = departmentService.getEmployeeByDepartment(1);
        List<Employee> expected = Arrays.asList(
                new Employee("ivan", "ivanov", 1, 1000),
                new Employee("pasha", "petrov", 1, 900));
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual));
    }
    @Test
    void getAll() {
       List<Integer> expectedDepartments =  employeeList.stream()
                .map(Employee::getDepartment)
                .distinct()
                .toList();
        Map<Integer, List<Employee>> actual = departmentService.getEmployeeMap();
        assertEquals(expectedDepartments.size(), actual.keySet().size());
        assertTrue(expectedDepartments.containsAll(actual.keySet()));
        verify(employeeService).getAll();
        verify(employeeService, times(0)).remove(new Employee("sasha", "ivanov", 2, 1000000.0011));
    }
}
