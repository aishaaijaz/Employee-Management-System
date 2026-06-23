package dao;

import db.DBConnection;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    // ── CREATE ───────────────────────────────────────────────────────────────

    @Override
    public void addEmployee(Employee emp) throws SQLException {
        String sql = "INSERT INTO employees (name, department, salary, email) VALUES (?, ?, ?, ?)";

        // PreparedStatement: ? placeholders prevent SQL injection
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setDouble(3, emp.getSalary());
            ps.setString(4, emp.getEmail());
            ps.executeUpdate();
            System.out.println("✔ Employee added.");
        }
    }

    // ── READ ONE ─────────────────────────────────────────────────────────────

    @Override
    public Employee getEmployee(int id) throws SQLException {
        String sql = "SELECT * FROM employees WHERE id = ?";

        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapRow(rs);   // convert DB row → Employee object
            }
        }
        return null; // not found
    }

    // ── READ ALL ─────────────────────────────────────────────────────────────

    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        String sql = "SELECT * FROM employees ORDER BY id";
        List<Employee> list = new ArrayList<>();

        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    // ── UPDATE ───────────────────────────────────────────────────────────────

    @Override
    public void updateEmployee(Employee emp) throws SQLException {
        String sql = "UPDATE employees SET name=?, department=?, salary=?, email=? WHERE id=?";

        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setDouble(3, emp.getSalary());
            ps.setString(4, emp.getEmail());
            ps.setInt(5, emp.getId());

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("✔ Employee updated.");
            else          System.out.println("✘ No employee found with ID " + emp.getId());
        }
    }

    // ── DELETE ───────────────────────────────────────────────────────────────

    @Override
    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("✔ Employee deleted.");
            else          System.out.println("✘ No employee found with ID " + id);
        }
    }

    // ── HELPER: DB row → Employee object ────────────────────────────────────

    private Employee mapRow(ResultSet rs) throws SQLException {
        return new Employee(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("department"),
            rs.getDouble("salary"),
            rs.getString("email")
        );
    }
}
