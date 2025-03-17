package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();
    private Integer counter = 1; // Auto-generate student IDs

    public List<Student> getAllStudents() {
        return students;
    }

    public Optional<Student> getStudentById(Integer id) {
        return students.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    public void addStudent(Student student) {
        student.setId(counter++);
        students.add(student);
    }

    public boolean deleteStudent(Integer id) {
        return students.removeIf(student -> student.getId().equals(id));
    }
}
