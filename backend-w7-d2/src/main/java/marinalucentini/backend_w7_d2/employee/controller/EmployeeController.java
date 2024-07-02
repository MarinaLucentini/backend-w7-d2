package marinalucentini.backend_w7_d2.employee.controller;

import marinalucentini.backend_w7_d2.employee.Employee;
import marinalucentini.backend_w7_d2.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Employee putEmployee (@PathVariable UUID employeeId, @RequestBody Employee employee){
return employeeService.findAndUpload(employeeId, employee);
}
@DeleteMapping("{employeeId}")
    public void deleteEmployee(@PathVariable UUID employeeId){
employeeService.findByIdAndDelete(employeeId);
}


@PostMapping("/{employeeId}/avatar")
    public Employee uploadAvatar(@PathVariable UUID employeeId, @RequestParam("avatar") MultipartFile image) throws IOException {
        String imageUrl = employeeService.uploadImage(image);
        Employee updatedEmployee = employeeService.saveImage(imageUrl, employeeId);
        return updatedEmployee;
    }
}
