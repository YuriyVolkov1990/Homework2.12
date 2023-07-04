package homework.homework2_8.service;

import homework.homework2_8.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;
    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public double getEmployeeSalarySum(int depart) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == depart)
                .mapToDouble(Employee::getSalary)
                .sum();
    }
    public double getEmployeeWithMaxSalary(int depart){
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == depart)
                .mapToDouble(Employee::getSalary)
                .max()
                .orElseThrow();
    }
    public double getEmployeeWithMinSalary(int depart){
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == depart)
                .mapToDouble(Employee::getSalary)
                .min()
                .orElseThrow();
    }
    public List<Employee> getEmployeeByDepartment(int depart) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == depart)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> getEmployeeMap() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

}
