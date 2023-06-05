package oz.example.employeemanagment.clr;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import oz.example.employeemanagment.entities.Employee;
import oz.example.employeemanagment.service.EmployeeService;

import java.util.*;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class Test implements CommandLineRunner {


    private final EmployeeService employeeService;

    private final RestTemplate restTemplate;

    List<Employee> employees = new ArrayList<>();


    Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        employeesCreation();
        menu();

    }

    public void menu(){
        Map<Integer, Object> menu = new HashMap<>();
        System.out.println("What do you want to test?" +
                "\n1- Create new employee" +
                "\n2- Update employee" +
                "\n3- Get employee details" +
                "\n4- Delete employee");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        switch (num){
            case 1:
                menu.put(1, newEmployeeCreation());
                menu();
                break;

            case 2:
                menu.put(2, employeeUpdate());
                menu();
                break;

            case 3:
                System.out.println("Enter the id of the employee that you want to get: ");
                menu.put(3, getEmployee(scanner.nextInt()));
                menu();
                break;

            case 4:
                System.out.println("Enter the id of the employee that you want to delete: ");
                menu.put(4, employeeDeletion(scanner.nextInt()));
                menu();
                break;

            default:
                System.out.println("Please enter valid option");
        }



    }

    public Void employeesCreation() {
        for (int i = 0; i < TestConstants.EMPLOYEES_TO_CREATE; i++) {
            employees.add(Employee.builder()
                    .firstName("Employee " + i)
                    .lastName("none")
                    .idNumber(random.nextInt(399999999))
                    .phoneNumber("484848485")
                    .salary(random.nextInt(15000))
                    .build());
        }

        Employee employeeToUpdateTest = Employee.builder()
                .firstName("To be")
                .lastName("Updated")
                .idNumber(123456789)
                .phoneNumber("145236987")
                .salary(12000)
                .build();
        employees.add(employeeToUpdateTest);

        employees.forEach(employee -> {
            try {
                employeeService.addEmployee(employee);
            } catch (Exception e){
                System.out.println("Something went wrong");;
            }

        });
        return null;
    }

    public Boolean newEmployeeCreation(){
        int i = employees.size();
        i++;
        Employee employee =
                Employee.builder()
                        .firstName("employee " + i)
                        .lastName("Shemesh")
                        .idNumber(random.nextInt(399999999))
                        .phoneNumber("1258963214")
                        .salary(9000)
                        .build();
                employees.add(employee);
        try {
            ResponseEntity<Employee> employeeResponseEntity = restTemplate.
                    postForEntity(TestConstants.ADD_EMPLOYEES_URL, employee, Employee.class);
            Employee employeeRespone = employeeResponseEntity.getBody();
            System.out.println("New Employee has been created");
            return employeeRespone != null;

        }catch (Exception e){
            e.getMessage();
            return false;
        }
    }

    public Boolean employeeUpdate() {


        HttpEntity<Employee> request = new HttpEntity<>(
                Employee.builder()
                        .id(11L)
                        .firstName("Shir")
                        .lastName("Shemesh")
                        .phoneNumber("0545207778")
                        .idNumber(123456789)
                        .salary(10000)
                        .build());

        try {
            restTemplate.put(TestConstants.UPDATE_EMPLOYEE_URL, request, HttpMethod.PUT, Void.class);
            System.err.println("employee has been updated, TEST PASSED!");
            return true;

        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public boolean getEmployee(long id) {

        try {
            ResponseEntity<Employee> employeeResponseEntity = restTemplate.
                    getForEntity(TestConstants.GET_EMPLOYEE_URL + id, Employee.class);
            Employee employee = employeeResponseEntity.getBody();
            System.err.println(employee);
            return employee != null;

        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public boolean employeeDeletion(long id) {

        try {
            restTemplate.delete(TestConstants.DELETE_EMPLOYEE_URL + id);
            System.err.println("Employee has been deleted, TEST PASSED!");
            return true;

        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }



}
