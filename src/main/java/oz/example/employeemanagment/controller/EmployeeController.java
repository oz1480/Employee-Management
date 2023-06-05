package oz.example.employeemanagment.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import oz.example.employeemanagment.entities.Employee;
import oz.example.employeemanagment.service.EmployeeService;

import java.util.List;

@RequestMapping("employee")
@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("{id}")
    public Employee getEmployee(@PathVariable long id){
        return employeeService.getEmployee(id);
    }

    @GetMapping("/all-employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping("/add-employee")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @DeleteMapping("/employee")
    public void deleteEmployee(@RequestParam long id){
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/update-employee")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateEmployee(@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
    }



}
