package demo.mvc.service;

import demo.mvc.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee create(Employee employee);

    Employee update(Employee employee);

    Optional<Employee> partialUpdate(int id, Employee patchData);

    Optional<Employee> findById(int id);

    List<Employee> findAll();

    void deleteById(int id);

    void delete(Employee employee);

    boolean existsById(int id);

    long count();

    Optional<Employee> findByEmail(String email);

    List<Employee> findByDepartmentId(int departmentId);
}
