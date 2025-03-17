package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/new")
    public String showAddStudentForm(Student student) {
        return "new-student";
    }

    @PostMapping("/save")
    public String addStudent(@Valid @ModelAttribute("student") Student student,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "new-student";
        }
        studentService.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/json")
    @ResponseBody
    public List<Student> getAllStudentsJson() {
        return studentService.getAllStudents();
    }
}
