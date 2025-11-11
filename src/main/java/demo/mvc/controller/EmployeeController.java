package demo.mvc.controller;

import demo.mvc.entity.Employee;
import demo.mvc.error.AppException;
import demo.mvc.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl service;

    @Value("${EmployeeStatus}")
    private List<String> status;

    @Autowired
    public EmployeeController(EmployeeServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/management")
    public String manage(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        if (page < 0) page = 0;
        if (size <= 0) size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by("lastName").ascending());
        Page<Employee> employeePage = service.findAllPaged(pageable);
        model.addAttribute("employees", employeePage.getContent());
        model.addAttribute("currentPage", employeePage.getNumber());
        model.addAttribute("totalPages", employeePage.getTotalPages());
        model.addAttribute("totalItems", employeePage.getTotalElements());
        model.addAttribute("pageSize", employeePage.getSize());

        return "management";
    }

    @GetMapping("/new")
    public String newEmployee(Model model){
        Employee newEmployee = new Employee();
        model.addAttribute("newEmployee", newEmployee);
        model.addAttribute("status", status);
        return "newEmployee";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("newEmployee") Employee newEmployee, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("status", status);
            return "newEmployee";
        } else {
            service.create(newEmployee);
            return "redirect:/employee/management";
        }

    }

    @GetMapping("/update")
    public String updateEmployee(@RequestParam("employeeId") int id, Model model){
        Optional<Employee> opt = service.findById(id);
        if(opt.isPresent()){
            model.addAttribute("newEmployee", opt.get());
            model.addAttribute("status", status);
            return "newEmployee";
        } else {
            throw new AppException("The employee does not exist!");
        }
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id, Model model){
        service.deleteById(id);
        model.addAttribute("status", status);
        return "redirect:/employee/management";
    }
}
