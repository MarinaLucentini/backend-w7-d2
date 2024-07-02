package marinalucentini.backend_w7_d2.employee.controller;

import marinalucentini.backend_w7_d2.employee.Employee;
import marinalucentini.backend_w7_d2.employee.payload.EmployeeDto;
import marinalucentini.backend_w7_d2.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/eployees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

@GetMapping
    public Page<Employee> getEmployee (@RequestParam (defaultValue = "0")int page, @RequestParam (defaultValue = "5")int size, @RequestParam (defaultValue = "name")String sortBy){
return employeeService.getEmployee(page, size, sortBy);
}
@GetMapping("/{employeeId}")
    public Employee getEmployeeById (@PathVariable UUID employeeId){
return  employeeService.findById(employeeId);
}
@PutMapping("/{employeeId}")
@PreAuthorize("hasAuthority('DIRIGENTE')")
    public Employee putEmployee (@PathVariable UUID employeeId, @RequestBody Employee employee){
return employeeService.findAndUpload(employeeId, employee);
}
@DeleteMapping("{employeeId}")
@PreAuthorize("hasAuthority('DIRIGENTE')")
    public void deleteEmployee(@PathVariable UUID employeeId){
employeeService.findByIdAndDelete(employeeId);
}

    @GetMapping("/me")
    public Employee getProfile(@AuthenticationPrincipal Employee currentAuthenticatedEmployee){
        return currentAuthenticatedEmployee;
    }

    @PutMapping("/me")
    public Employee updateProfile(@AuthenticationPrincipal Employee currentAuthenticatedEmployee, @RequestBody Employee body){
        return this.employeeService.findAndUpload(currentAuthenticatedEmployee.getId(), body);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void deleteProfile(@AuthenticationPrincipal Employee currentAuthenticatedEmployee){
        this.employeeService.findByIdAndDelete(currentAuthenticatedEmployee.getId());
    }
@PostMapping("/me/avatar")
    public Employee uploadAvatar(@AuthenticationPrincipal Employee currentAuthenticatedEmployee, @RequestParam("avatar") MultipartFile image) throws IOException {
        String imageUrl = employeeService.uploadImage(image);
        Employee updatedEmployee = employeeService.saveImage(imageUrl, currentAuthenticatedEmployee.getId());
        return updatedEmployee;
    }
}
