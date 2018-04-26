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
        con = ConnectionManager.getConnection();
    }

}
