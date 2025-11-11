package demo.mvc.service;

import demo.mvc.entity.Employee;
import demo.mvc.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        if (!employeeRepository.existsById(employee.getId())) {
            throw new IllegalArgumentException("Employee with ID " + employee.getId() + " not found.");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> partialUpdate(int id, Employee patchData) {
        return employeeRepository.findById(id).map(existing -> {
            if (patchData.getFirstName() != null) existing.setFirstName(patchData.getFirstName());
            if (patchData.getLastName() != null) existing.setLastName(patchData.getLastName());
            if (patchData.getEmail() != null) existing.setEmail(patchData.getEmail());
            if (patchData.getPhoneNumber() != null) existing.setPhoneNumber(patchData.getPhoneNumber());
            if (patchData.getStatus() != null) existing.setStatus(patchData.getStatus());
            if (patchData.getJobId() > 0) existing.setJobId(patchData.getJobId());
            if (patchData.getDepartmentId() > 0) existing.setDepartmentId(patchData.getDepartmentId());
            if (patchData.getHireDate() != null) existing.setHireDate(patchData.getHireDate());
            return employeeRepository.save(existing);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(int id) {
        return employeeRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return employeeRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findByDepartmentId(int departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    @Transactional(readOnly = true)
    public Page<Employee> findAllPaged(Pageable pageable) {
        return employeeRepository.findAllByOrderByLastNameAsc(pageable);
    }

}
