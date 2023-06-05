package oz.example.employeemanagment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import oz.example.employeemanagment.entities.Employee;
import oz.example.employeemanagment.repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee){
        Optional<Employee> employeeOptional = employeeRepository.findByIdNumber(employee.getIdNumber());
        if (employeeOptional.isPresent()){
            throw new RuntimeException("The employee with ID number " + employee.getIdNumber() + " is already exist");
        }
        employeeRepository.save(employee);
        return employee;
    }

    public void updateEmployee(Employee employee){
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
        if (employeeOptional.isEmpty()){
            throw new RuntimeException("The employee with ID  " + employee.getId() + " is not exist");
        }
        Employee updatedEmployee = employeeOptional.get();
        if (updatedEmployee.getIdNumber() != employee.getIdNumber()){
            throw new RuntimeException("You can`t change id number!");
        }
        employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()){
            throw new RuntimeException("The employee with ID " + id + " is not exist");
        }
        return employeeOptional.get();
    }

    public void deleteEmployee(long id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()){
            throw new RuntimeException("The employee with ID " + id + " is not exist");
        }
        employeeRepository.delete(employeeOptional.get());
    }

    public List<Employee> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()){
            return null;
        }
        return employees;
    }




}
