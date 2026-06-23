package ui;

import model.Employee;
import service.EmployeeService;

import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private final EmployeeService service = new EmployeeService();
    private final Scanner scanner         = new Scanner(System.in);

    public void start() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║   Employee Management System     ║");
        System.out.println("╚══════════════════════════════════╝");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployee();
                case 3 -> viewAllEmployees();
                case 4 -> updateEmployee();
                case 5 -> deleteEmployee();
                case 6 -> { running = false; System.out.println("Goodbye!"); }
                default -> System.out.println("✘ Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    // ── MENU ─────────────────────────────────────────────────────────────────

    private void printMenu() {
        System.out.println("\n┌─────────────────────────┐");
        System.out.println("│  1. Add Employee        │");
        System.out.println("│  2. View Employee       │");
        System.out.println("│  3. View All Employees  │");
        System.out.println("│  4. Update Employee     │");
        System.out.println("│  5. Delete Employee     │");
        System.out.println("│  6. Exit                │");
        System.out.println("└─────────────────────────┘");
    }

    // ── HANDLERS ─────────────────────────────────────────────────────────────

    private void addEmployee() {
        System.out.println("\n── Add Employee ──");
        String name   = readString("Name       : ");
        String dept   = readString("Department : ");
        double salary = readDouble("Salary     : ");
        String email  = readString("Email      : ");
        service.addEmployee(name, dept, salary, email);
    }

    private void viewEmployee() {
        int id = readInt("\nEnter Employee ID: ");
        Employee emp = service.getEmployee(id);
        if (emp != null) {
            printHeader();
            System.out.println(emp);
            printDivider();
        }
    }

    private void viewAllEmployees() {
        List<Employee> list = service.getAllEmployees();
        if (list.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        printHeader();
        list.forEach(System.out::println);
        printDivider();
        System.out.println("Total: " + list.size() + " employee(s).");
    }

    private void updateEmployee() {
        int id = readInt("\nEnter Employee ID to update: ");
        Employee existing = service.getEmployee(id);
        if (existing == null) return;

        System.out.println("Leave blank to keep current value.\n");

        String name = readString("Name [" + existing.getName() + "]: ");
        if (name.isBlank()) name = existing.getName();

        String dept = readString("Department [" + existing.getDepartment() + "]: ");
        if (dept.isBlank()) dept = existing.getDepartment();

        String salaryStr = readString("Salary [" + existing.getSalary() + "]: ");
        double salary = salaryStr.isBlank() ? existing.getSalary() : Double.parseDouble(salaryStr);

        String email = readString("Email [" + existing.getEmail() + "]: ");
        if (email.isBlank()) email = existing.getEmail();

        service.updateEmployee(id, name, dept, salary, email);
    }

    private void deleteEmployee() {
        int id = readInt("\nEnter Employee ID to delete: ");
        String confirm = readString("Are you sure? (yes/no): ");
        if (confirm.equalsIgnoreCase("yes")) {
            service.deleteEmployee(id);
        } else {
            System.out.println("Cancelled.");
        }
    }

    // ── INPUT HELPERS ─────────────────────────────────────────────────────────

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("✘ Enter a valid number.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("✘ Enter a valid number.");
            }
        }
    }

    // ── TABLE FORMATTING ─────────────────────────────────────────────────────

    private void printHeader() {
        printDivider();
        System.out.printf("| %-4s | %-20s | %-15s | %-10s | %-25s |%n",
            "ID", "Name", "Department", "Salary", "Email");
        printDivider();
    }

    private void printDivider() {
        System.out.println("+------+----------------------+-----------------+------------+---------------------------+");
    }
}
