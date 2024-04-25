package secondProject.Controllers;


import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import secondProject.Models.Employee;
import secondProject.Services.EmployeeService;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path = "api/v1/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }

    @DeleteMapping(path = "{employeeId}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addNewEmployee(@ModelAttribute Employee employee, @RequestParam(value = "image", required = false) MultipartFile[] photo) {
        employeeService.addNewEmployee(employee, photo==null?null:photo[0]);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable("employeeId") Long employeeId, @ModelAttribute Employee employee) {
        employeeService.updateEmployee(employeeId, employee);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "fire/{employeeId}")
    public ResponseEntity<?> fireEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.fireEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }
}
