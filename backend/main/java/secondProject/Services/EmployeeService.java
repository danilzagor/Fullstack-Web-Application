package secondProject.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import secondProject.Models.Employee;
import secondProject.Models.Photo;
import secondProject.Repositories.EmployeeRepository;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) {
         return employeeRepository.findById(employeeId).orElseThrow(()->new NoSuchElementException("Employee with id: " + employeeId + " doesn't exist"));
    }

    public void deleteEmployeeById(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public void addNewEmployee(Employee employee, MultipartFile photo) {
        Optional<Employee> employeeOptional =
                employeeRepository.findEmployeeByEmail(employee.getEmail());
        if(employeeOptional.isPresent()){
            throw new IllegalStateException("Email is taken");
        }
        try {
            Photo photoOfUser = new Photo();
            photoOfUser.setPhoto(photo==null?null:photo.getBytes());
            employee.setPhoto(photoOfUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        employeeRepository.save(employee);
    }
    @Transactional
    public void updateEmployee(Long employeeId, Employee employee) {
        Employee employeeToUpdate = employeeRepository.findById(employeeId)
                .orElseThrow(()->new NoSuchElementException("Employee with id: " + employeeId + " doesn't exist"));

        for(Field variable: employee.getClass().getDeclaredFields()){
            variable.setAccessible(true);
            if(variable.getName().equals("email")
                    && employee.getEmail()!=null
                    && employee.getEmail().length()>0){
                if(employeeRepository.findEmployeeByEmail(employee.getEmail()).isPresent()) {
                    throw new IllegalStateException("Email is taken");
                }else employeeToUpdate.setEmail(employee.getEmail());
            }else {
                try {
                    if(variable.get(employee)!=null && !variable.getName().equals("age")){
                        variable.set(employeeToUpdate,variable.get(employee));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        employeeRepository.save(employeeToUpdate);

    }

    @Transactional
    public void fireEmployee(Long employeeId) {
        Employee employeeToFire = employeeRepository.findById(employeeId)
                .orElseThrow(()->new NoSuchElementException("Employee with id: " + employeeId + " doesn't exist"));
        employeeToFire.setDateOfFiring(LocalDate.now());
        employeeRepository.save(employeeToFire);
    }
}
