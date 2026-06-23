# Employee Management System
Java + JDBC + MySQL — CRUD Project

## Setup Steps

### 1. MySQL — Run schema first
```sql
mysql -u root -p < db/schema.sql
```

### 2. Download MySQL Connector JAR
Get from: https://dev.mysql.com/downloads/connector/j/
Place in: `lib/mysql-connector-j-x.x.x.jar`

### 3. Update DB password
Open `src/db/DBConnection.java` → change `PASSWORD` field.

---

## Compile & Run (from project root)

```bash
# Compile all .java files
javac -cp lib/mysql-connector-j-x.x.x.jar -sourcepath src -d out src/Main.java

# Run
java -cp out:lib/mysql-connector-j-x.x.x.jar Main

# Windows (use semicolon instead of colon)
java -cp out;lib/mysql-connector-j-x.x.x.jar Main
```

---

## Project Structure
```
src/
  Main.java               ← entry point
  model/Employee.java     ← data model
  db/DBConnection.java    ← MySQL connection
  dao/EmployeeDAO.java    ← interface
  dao/EmployeeDAOImpl.java← CRUD SQL
  service/EmployeeService ← validation + logic
  ui/MainMenu.java        ← CLI menu
db/
  schema.sql              ← create table
lib/
  mysql-connector.jar     ← download separately
```

## Java Skills Demonstrated
- OOP & Encapsulation (Employee.java)
- Interface & Abstraction (DAO pattern)
- JDBC + PreparedStatement (SQL injection safe)
- Singleton pattern (DBConnection)
- Layered Architecture (UI → Service → DAO → DB)
- Exception handling
- try-with-resources

## Further Improvements
- Add a GUI using a user friendy interface using JavaFX of Swing
- Expose the application as a REST API using Spring Boot
- Implement Authentication and a role based access
- Add search, filtering, and sorting for employee records.
- Export employee data to CSV or Excel.