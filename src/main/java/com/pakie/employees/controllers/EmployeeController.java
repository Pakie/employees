package com.pakie.employees.controllers;

import com.pakie.employees.domain.Employee;
import com.pakie.employees.repositories.EmployeeRepository;
import com.pakie.employees.services.EmployeeService;
import com.pakie.employees.services.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //List Employees
    @GetMapping("/employees")
    public String getEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees/list";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employees/new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
        //Get employee from service
        Employee employee = employeeService.getEmployeeById(id);

        //Set employee as an attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "employees/update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id, Model model) {
        //Call Delete Employee method
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }
}