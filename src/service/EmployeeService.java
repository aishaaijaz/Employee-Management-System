package service;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import model.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {

    // Depends on interface, not impl — good practice
    private final EmployeeDAO dao = new EmployeeDAOImpl();

    // ── ADD ──────────────────────────────────────────────────────────────────

    public void addEmployee(String name, String dept, double salary, String email) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("✘ Name cannot be empty.");
            return;
        }
        if (salary < 0) {
            System.out.println("✘ Salary cannot be negative.");
            return;
        }
        if (!email.contains("@")) {
            System.out.println("✘ Invalid email.");
            return;
        }
        try {
            dao.addEmployee(new Employee(name.trim(), dept.trim(), salary, email.trim()));
        } catch (SQLException e) {
            System.err.println("DB Error: " + e.getMessage());
        }
    }

    // ── GET ONE ──────────────────────────────────────────────────────────────

    public Employee getEmployee(int id) {
        try {
            Employee emp = dao.getEmployee(id);
            if (emp == null) System.out.println("✘ No employee with ID " + id);
            return emp;
        } catch (SQLException e) {
            System.err.println("DB Error: " + e.getMessage());
            return null;
        }
    }

    // ── GET ALL ──────────────────────────────────────────────────────────────

    public List<Employee> getAllEmployees() {
        try {
            return dao.getAllEmployees();
        } catch (SQLException e) {
            System.err.println("DB Error: " + e.getMessage());
            return List.of();
        }
    }

    // ── UPDATE ───────────────────────────────────────────────────────────────

    public void updateEmployee(int id, String name, String dept, double salary, String email) {
        if (salary < 0) {
            System.out.println("Salary cannot be negative.");
            return;
        }
        try {
            dao.updateEmployee(new Employee(id, name.trim(), dept.trim(), salary, email.trim()));
        } catch (SQLException e) {
            System.err.println("DB Error: " + e.getMessage());
        }
    }

    // ── DELETE ───────────────────────────────────────────────────────────────

    public void deleteEmployee(int id) {
        try {
            dao.deleteEmployee(id);
        } catch (SQLException e) {
            System.err.println("DB Error: " + e.getMessage());
        }
    }
}
