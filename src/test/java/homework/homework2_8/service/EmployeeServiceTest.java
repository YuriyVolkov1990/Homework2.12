package homework.homework2_8.service;

import homework.homework2_8.exception.EmployeeAlreadyAddedException;
import homework.homework2_8.exception.EmployeeNotFoundException;
import homework.homework2_8.exception.EmployeeStorageIsFullException;
import homework.homework2_8.exception.InvalidDataException;
import homework.homework2_8.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.net.ssl.ExtendedSSLSession;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();
    @Test
    void getAll(){
        Employee e1 = new Employee("ivan", "ivanov", 1, 10000);
        Employee e2 = new Employee("petr", "petrov", 2, 20000);
        Employee e3 = new Employee("sidor", "sidorov", 3, 30000);
        employeeService.add(e1);
        employeeService.add(e2);
        employeeService.add(e3);
        List<Employee> expected = Arrays.asList(e1, e2, e3);
        assertEquals(3,employeeService.getAll());
        assertIterableEquals(expected,employeeService.getAll());
    }
    @Test
    void add() {
        int prevSize = employeeService.getAll().size();
        Employee e1 = new Employee("ivan", "ivanov", 1, 10000);
        employeeService.add(e1);
        assertEquals(prevSize+1,employeeService.getAll().size());
        assertTrue(employeeService.getAll().contains(e1));
    }

    @Test
    void whenAddDuplicateThenException() {
        Employee e1 = new Employee("ivan", "ivanov", 1, 10000);
        assertDoesNotThrow(()-> employeeService.add(e1));
        assertThrows(EmployeeAlreadyAddedException.class,() -> employeeService.add(e1));
    }
//    @Test
//    void whenStorageIsFullThenThrowException() {
//        for (int i = 0; i < 5; i++) {
//          Employee employee = new Employee("Name", "Lastname", 1, 100);
//           assertDoesNotThrow(()->employeeService.add(employee));
//        }
//        assertThrows(EmployeeStorageIsFullException.class,
//                ()->new Employee("fdgds", "fgdhjfd", 1, 10000));
//    }
    @Test
    void findPositive() {
        Employee expected = new Employee ("ivan", "ivanov", 1, 10000);
        employeeService.add(expected);
        Employee actual = employeeService.find(new Employee ("ivan", "ivanov", 1, 10000));
        assertNotNull(actual);
        assertEquals(expected,actual);
    }

    @Test
    void findNegative() {
        Employee expected = new Employee ("ivan", "ivanov", 1, 10000);
        employeeService.add(expected);
        assertThrows(EmployeeNotFoundException.class, ()-> employeeService.find(new Employee("petr", "ivanov", 1, 10000)));
    }
    @Test
    void remove() {
        Employee expected = new Employee ("ivan", "ivanov", 1, 10000);
        employeeService.add(expected);
        assertTrue(employeeService.getAll().contains(expected));
        employeeService.remove(new Employee("ivan", "ivanov", 1, 10000));
        assertFalse(employeeService.getAll().contains(expected));
    }
}
