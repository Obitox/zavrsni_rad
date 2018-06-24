package service;

import database.ConnectionManager;
import model.Student;

import java.sql.*;
import java.util.ArrayList;

public class AdminService {
    private PreparedStatement stmt = null;
    private Connection con = null;
    private ResultSet rs = null;
    private CallableStatement cStmt = null;
    private String sql = "";
    private ArrayList<Student> listOfAllStudents;

    public AdminService() {
        this.listOfAllStudents = new ArrayList<>();
        this.con = ConnectionManager.getConnection();
    }

    public ArrayList<Student> retrieveStudents(){
        listOfAllStudents.clear();
        sql = "SELECT * FROM student";
        con = ConnectionManager.getConnection();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                int student_id = rs.getInt("student_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String jmbg = rs.getString("jmbg");
                String fullname = rs.getString("fullname");
                String index = rs.getString("index");
                String address = rs.getString("address");
                listOfAllStudents.add(new Student(student_id, username, password, email, jmbg, fullname, index, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfAllStudents;
    }

    public String createStudent(String username, String password, String email, String jmbg, String fullname, String index, String address){
        con = ConnectionManager.getConnection();
        try {
            cStmt = con.prepareCall("{CALL createStudent_sp(?, ?, ?, ?, ?, ?, ?)}");
            cStmt.setString(1, username);
            cStmt.setString(2, password);
            cStmt.setString(3, email);
            cStmt.setString(4, jmbg);
            cStmt.setString(5, fullname);
            cStmt.setString(6, index);
            cStmt.setString(7, address);
            int rowsUpdated = cStmt.executeUpdate();
            if(rowsUpdated == 1) {
                return "Successful";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Student already exists in a database!";
    }


    public String deleteStudent(int id){
        System.out.println("id: "+id);
        sql = "DELETE FROM student WHERE student_id=?";
        con = ConnectionManager.getConnection();
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            int rowsUpdated = stmt.executeUpdate();
            if(rowsUpdated == 1){
                return "Student has been succesfully deleted!";
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "Student has not been deleted!";
    }

    public String updateStudent(int student_id ,String username, String password, String email, String jmbg, String fullname, String index, String address){
        System.out.println("student_id: " + student_id);
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        System.out.println("email: " + email);
        System.out.println("jmbg: " + jmbg);
        System.out.println("fullname: " + fullname);
        System.out.println("index: " + index);
        System.out.println("address: " + address);
        con = ConnectionManager.getConnection();
        System.out.println("Izvrsen update");
        try {
            cStmt = con.prepareCall("{call updateStudent_sp(? ,?, ?, ?, ?, ?, ?, ?)}");
            cStmt.setInt(1, student_id);
            cStmt.setString(2, username);
            cStmt.setString(3, password);
            cStmt.setString(4, email);
            cStmt.setString(5, jmbg);
            cStmt.setString(6, fullname);
            cStmt.setString(7, index);
            cStmt.setString(8, address);
            int rowsUpdated = cStmt.executeUpdate();
            if(rowsUpdated == 1){
                return "Student has been succesfully updated!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Student has not been updated";
    }

    public String administratorRegistration(String username, String password, String fullname, String jmbg, String address, String email, String phone){
        con = ConnectionManager.getConnection();
        try {
            cStmt = con.prepareCall("{CALL createAdmin_sp(?, ?, ?, ?, ?, ?, ?)}");
            cStmt.setString(1, username);
            cStmt.setString(2, password);
            cStmt.setString(3, fullname);
            cStmt.setString(4, jmbg);
            cStmt.setString(5, address);
            cStmt.setString(6, email);
            cStmt.setString(7, phone);
            int rowsUpdated = cStmt.executeUpdate();
            if(rowsUpdated == 1) {
                return "Successful";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Admin already exists in a database";
    }

    public String adminLogin(String username, String password){
        con = ConnectionManager.getConnection();
        try{
            cStmt=con.prepareCall ("{ CALL loginAdmin_sp(?, ?, ?) }");
            cStmt.setString(1,username);
            cStmt.setString(2, password);
            cStmt.registerOutParameter (3, Types.TINYINT);
            cStmt.executeUpdate();
            if(cStmt.getInt (3) == 1){
                return "Success";
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "Failed";
    }

    public String getAdminId(String username){
        con = ConnectionManager.getConnection();
        try {
            stmt = con.prepareStatement("SELECT administrator_id FROM administrator WHERE username = ?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.next()){
                return String.valueOf(rs.getInt("administrator_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
