package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.domain.Employee;
import ru.job4j.domain.Person;
import ru.job4j.repository.EmployeeRepository;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private RestTemplate rest;
    private static final String API = "http://localhost:8080/person/";
    private static final String API_ID = "http://localhost:8080/person/{id}";
    private final EmployeeRepository er;

    public EmployeeController(EmployeeRepository er) {
        this.er = er;
    }

    @GetMapping("/")
    public List<Employee> findAll() {
        return (List<Employee>) er.findAll();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Person> create(@PathVariable int id, @RequestBody Person person) {
        Employee employee = er.findById(id).get();
        employee.addPerson(person);
        er.save(employee);
        return new ResponseEntity<>(
                person,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        rest.put(API, person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/empl/{eid}/prsn/{pid}")
    public void delete(@PathVariable int eid, @PathVariable int pid) {
        Employee employee = er.findById(eid).orElseThrow();
        employee.getPerson().removeIf(person -> person.getId() == pid);
        er.save(employee);
    }
}
