package secondProject.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import secondProject.Models.Employee;
import secondProject.Services.EmployeeService;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testAddNewEmployee() throws Exception {

        Employee employee = new Employee();
        employee.setName("John");
        employee.setSurname("Doe");
        employee.setEmail("john.doe@example.com");
        employee.setDateOfBirth(LocalDate.of(1990, 1, 1));
        employee.setDateOfHiring(LocalDate.now());


        MockMultipartFile multipartFile = new MockMultipartFile(
                "image", "defaultPhoto.png", MediaType.IMAGE_PNG_VALUE, "photo".getBytes()
        );


        mockMvc.perform(multipart("/api/v1/employee")
                        .file(multipartFile)
                        .param("name", employee.getName())
                        .param("surname", employee.getSurname())
                        .param("email", employee.getEmail())
                        .param("dateOfBirth", employee.getDateOfBirth().toString())
                        .param("dateOfHiring", employee.getDateOfHiring().toString()))
                .andExpect(status().isOk());

        verify(employeeService, times(1)).addNewEmployee(eq(employee), multipartFile);


    }
}
