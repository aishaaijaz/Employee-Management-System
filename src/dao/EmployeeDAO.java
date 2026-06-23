package dao;

import model.Employee;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {

    void         addEmployee(Employee emp)    throws SQLException;
    Employee     getEmployee(int id)          throws SQLException;
    List<Employee> getAllEmployees()           throws SQLException;
    void         updateEmployee(Employee emp) throws SQLException;
    void         deleteEmployee(int id)       throws SQLException;
}
