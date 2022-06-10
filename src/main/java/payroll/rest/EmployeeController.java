package payroll.rest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import payroll.exception.EmployeeNotFoundException;
import payroll.model.Employee;
import payroll.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class
EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository repository) {
        this.employeeRepository = repository;
    }

    //Aggregate root
    //tag ::get-aggregate-root[]
    /*
    @GetMapping("/employees")
    List<Employee> all() {
        return repository.findAll();
    }
     */
    //end::get-aggregate-root[]

    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> all() {

        List<EntityModel<Employee>> employees = employeeRepository.findAll().stream()
                //.filter(employee -> employee.getId()<2)
                .map(employee -> EntityModel.of(employee,
                        linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                        linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
                .collect(Collectors.toList());

        /*
        List<EntityModel<Employee>> result = new ArrayList<>();
        for (Employee employee : employeeRepository.findAll()) {
            EntityModel<Employee> employeeEntityModel = EntityModel.of(employee,
                    linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                    linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
            result.add(employeeEntityModel);
        }
         */

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    // Single item
/*
  @GetMapping("/employees/{id}")
  Employee one(@PathVariable Long id) {

    return repository.findById(id)
      .orElseThrow(() -> new EmployeeNotFoundException(id));
  }
*/
    @GetMapping("/employees/{id}")
    EntityModel<Employee> one(@PathVariable Long id) {

        Employee employee = employeeRepository.findById(id) //
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return EntityModel.of(employee, //
                linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}
