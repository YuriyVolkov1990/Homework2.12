package homework.homework2_8.controller;

import homework.homework2_8.model.Employee;
import homework.homework2_8.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    private final DepartmentService departmentService;

    public DepartmentsController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/sum-salary")
    public double getSum(@RequestParam("departmentId") int depart) {
        return departmentService.getEmployeeSalarySum(depart);
    }
    @GetMapping("/min-salary")
    public double getMin(@RequestParam("departmentId") int depart) {
        return departmentService.getEmployeeWithMinSalary(depart);
    }
    @GetMapping("/max-salary")
    public double getMax(@RequestParam("departmentId")int depart) {
        return departmentService.getEmployeeWithMaxSalary(depart);
    }
    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> getAll(@RequestParam("departmentId")int depart) {
        return departmentService.getEmployeeByDepartment(depart);
    }
    @GetMapping(value = "/all")
    public Map<Integer,List<Employee>> getAll() {
        return departmentService.getEmployeeMap();
    }
}
