package edu.wpi.cs3733.programname.database.QueryMethods;

import edu.wpi.cs3733.programname.commondata.Interpreter;
import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.commondata.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeesQuery {
    private DBConnection dbConnection;
    private InterpreterQuery interpreterQuery;
    public EmployeesQuery(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.interpreterQuery = new InterpreterQuery(dbConnection);
    }

    public ArrayList<Employee> queryAllEmployees(){
        Employee queryResult = null;
        ArrayList<Employee> group = new ArrayList<Employee>();
        try {
            String sql = "SELECT * FROM Employees";

            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            String username;
            String password;
            String firstName;
            String middleName;
            String lastName;
            boolean sysAdmin;
            int sysAdminInt;
            String serviceType;
            String email;
            while(result.next()) {
                username = result.getString("username");
                password = result.getString("password");
                firstName = result.getString("firstName");
                middleName = result.getString("middleName");
                lastName = result.getString("lastName");
                sysAdminInt = result.getInt("sysAdmin");
                serviceType = result.getString("serviceType");
                sysAdmin = (sysAdminInt == 1)? true : false;
                email = result.getString("email");
                if(serviceType=="interpreter"){
                    ArrayList<String> languages = this.interpreterQuery.queryInterpreterSkills(username);
                    queryResult = new Interpreter(username, password, firstName, middleName, lastName, sysAdmin, serviceType, email,languages);
                }
                else{
                    queryResult = new Employee(username, password, firstName, middleName, lastName, sysAdmin, serviceType, email);
                }
                //queryResult = new Employee(username, password, firstName, middleName, lastName, sysAdmin, serviceType, email);
                group.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Get Employee Failed!");
            e.printStackTrace();
        }
        return group;
    }

    public ArrayList<Employee> queryEmployeesByType(String type){
        Employee queryResult = null;
        ArrayList<Employee> group = new ArrayList<Employee>();
        try {
            String sql = "SELECT * FROM Employees WHERE serviceType = '" + type + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            String username;
            String password;
            String firstName;
            String middleName;
            String lastName;
            boolean sysAdmin;
            int sysAdminInt;
            //String serviceType;
            String email;
            while(result.next()) {
                username = result.getString("username");
                password = result.getString("password");
                firstName = result.getString("firstName");
                middleName = result.getString("middleName");
                lastName = result.getString("lastName");
                sysAdminInt = result.getInt("sysAdmin");
                //serviceType = result.getString("serviceType");
                sysAdmin = (sysAdminInt == 1)? true : false;
                email = result.getString("email");
                queryResult = new Employee(username, password, firstName, middleName, lastName, sysAdmin, type, email);
                group.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Get Employee Failed!");
            e.printStackTrace();
        }
        return group;
    }

    public Employee queryEmployeeByUsername(String username){
        Employee queryResult = null;
        try {
            String sql = "SELECT * FROM Employees WHERE username = '" + username +"'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            String password;
            String firstName;
            String middleName;
            String lastName;
            boolean sysAdmin;
            int sysAdminInt;
            String serviceType;
            String email;
            while(result.next()) {
                //username = result.getString("username");
                password = result.getString("password");
                firstName = result.getString("firstName");
                middleName = result.getString("middleName");
                lastName = result.getString("lastName");
                sysAdminInt = result.getInt("sysAdmin");
                serviceType = result.getString("serviceType");
                sysAdmin = (sysAdminInt == 1)? true : false;
                email = result.getString("email");
                queryResult = new Employee(username, password, firstName, middleName, lastName, sysAdmin, serviceType, email);
            }
        } catch (SQLException e) {
            System.out.println("Get Employee Failed!");
            e.printStackTrace();
        }
        return queryResult;
    }

    public boolean validateLogin(String usernameAttempt, String passwordAttempt) {
        Employee queryResult = null;
        try {
            String sql = "SELECT * FROM Employees WHERE username = '" + usernameAttempt +"'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            String password;
            String firstName;
            String middleName;
            String lastName;
            boolean sysAdmin;
            int sysAdminInt;
            String serviceType;
            String email;
            while(result.next()) {
                //username = result.getString("username");
                password = result.getString("password");
                firstName = result.getString("firstName");
                middleName = result.getString("middleName");
                lastName = result.getString("lastName");
                sysAdminInt = result.getInt("sysAdmin");
                serviceType = result.getString("serviceType");
                sysAdmin = (sysAdminInt == 1)? true : false;
                email = result.getString("email");
                queryResult = new Employee(usernameAttempt, password, firstName, middleName, lastName, sysAdmin, serviceType, email);
            }
        } catch (SQLException e) {
            System.out.println("Get Employee Failed!");
            e.printStackTrace();
        }

        if (queryResult.getPassword().equals(passwordAttempt) ) {
            return true;
        }
        else return false;
    }


}
