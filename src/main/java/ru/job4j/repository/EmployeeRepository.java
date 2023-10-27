package ru.job4j.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Employee;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    @Override
    @Query("SELECT distinct e FROM Employee e LEFT JOIN FETCH e.person")
    Iterable<Employee> findAll();

    @Override
    @Query("SELECT distinct e FROM Employee e LEFT JOIN FETCH e.person where e.id=?1")
    Optional<Employee> findById(Integer id);
}