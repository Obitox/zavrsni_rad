package service;

import database.ConnectionManager;
import model.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentService {
    private PreparedStatement stmt = null;
    private Connection con = null;
    private ResultSet rs = null;
    private CallableStatement cStmt = null;
    private String sql = "";
    private ArrayList<Student> listOfAllStudents;

    public StudentService() {
        listOfAllStudents = new ArrayList<>();
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
                listOfAllStudents.add(new Student(student_id, username, password, email, jmbg, fullname, index));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfAllStudents;
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

    public String updateStudent(int student_id ,String username, String password, String email, String jmbg, String fullname, String index){
        System.out.println("ID: " + student_id);
        con = ConnectionManager.getConnection();
        System.out.println("Izvrsen update");
        try {
            cStmt = con.prepareCall("{call updateStudent_sp(? ,?, ?, ?, ?, ?, ?)}");
            cStmt.setInt(1, student_id);
            cStmt.setString(2, username);
            cStmt.setString(3, password);
            cStmt.setString(4, email);
            cStmt.setString(5, jmbg);
            cStmt.setString(6, fullname);
            cStmt.setString(7, index);
            int rowsUpdated = cStmt.executeUpdate();
            if(rowsUpdated == 1){
               return "Student has been succesfully updated!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Student has not been updated";
    }

    public String studentRegistration(String username, String password, String email, String jmbg, String fullname, String index){
        con = ConnectionManager.getConnection();
        try {
            cStmt = con.prepareCall("{CALL createStudent_sp(?, ?, ?, ?, ?, ?)}");
            cStmt.setString(1, username);
            cStmt.setString(2, password);
            cStmt.setString(3, email);
            cStmt.setString(4, jmbg);
            cStmt.setString(5, fullname);
            cStmt.setString(6, index);
            int rowsUpdated = cStmt.executeUpdate();
            if(rowsUpdated == 1) {
                return "Successful";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Student already exists in a database";
    }

    public String studentLogin(String username, String password){
        con = ConnectionManager.getConnection();
        try{
            cStmt=con.prepareCall ("{ CALL loginStudent_sp(?, ?, ?) }");
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



}
