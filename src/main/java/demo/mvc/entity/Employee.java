package demo.mvc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false, length = 50)
    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only letters")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only letters")
    private String lastName;

    @Column(name = "email", unique = true, length = 100)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "phone_number", length = 20)
    @Pattern(
            regexp = "^\\+?[0-9\\-\\s()]{7,20}$",
            message = "Phone number must be valid and contain only digits, spaces, dashes, or parentheses"
    )
    private String phoneNumber;

    @Column(name = "status", length = 20)
    @Pattern(
            regexp = "^(Active|On Leave|Resigned|Terminated)$",
            message = "Status must be one of: Active, On Leave, Resigned, or Terminated"
    )
    private String status;

    @Column(name = "job_id")
    @Positive(message = "Job ID must be positive")
    private int jobId;

    @Column(name = "department_id")
    @Positive(message = "Department ID must be positive")
    private int departmentId;

    @Column(name = "hire_date")
    @PastOrPresent(message = "Hire date cannot be in the future")
    private LocalDate hireDate;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, String phoneNumber, String status, int jobId, int departmentId, LocalDate hireDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.jobId = jobId;
        this.departmentId = departmentId;
        this.hireDate = hireDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status='" + status + '\'' +
                ", jobId=" + jobId +
                ", departmentId=" + departmentId +
                ", hireDate=" + hireDate +
                '}';
    }
}