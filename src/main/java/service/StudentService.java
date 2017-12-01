package service;

import database.ConnectionManager;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private Statement stmt = null;
    private Connection con = null;
    private ResultSet rs = null;
    private CallableStatement cStmt = null;
    private String sql = "";
    private ArrayList<Student> listOfAllStudents;

    public StudentService() {
        listOfAllStudents = new ArrayList<>();
    }

    public ArrayList<Student> getAllStudents(){
        listOfAllStudents.clear();
        sql = "SELECT * FROM student";
        con = ConnectionManager.getConnection();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                int student_id = rs.getInt("student_id");
                String korisnicko_ime = rs.getString("korisnicko_ime");
                String lozinka = rs.getString("lozinka");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String indeks = rs.getString("indeks");
                String korisnik_slika = rs.getString("korisnik_slika");
                listOfAllStudents.add(new Student(student_id, korisnicko_ime, lozinka, ime, prezime, indeks, korisnik_slika));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfAllStudents;
    }

    public List<Student> createStudent(String korisnicko_ime, String lozinka, String ime, String prezime, String indeks, String korisnik_slika){
        sql = "INSERT INTO student(korisnicko_ime, lozinka, ime, prezime, indeks, korisnik_slika) " +
                "VALUES ('" +korisnicko_ime+"','"+lozinka+"','"+ime+"','"+prezime+"','"+indeks+"','"+korisnik_slika+"')";
        con = ConnectionManager.getConnection();
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfAllStudents;
    }

    public void deleteStudent(String id){
        System.out.println("Delete Called");
        sql = "DELETE FROM student WHERE student_id="+id;
        con = ConnectionManager.getConnection();
        try{
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateStudent(String korisnicko_ime, String lozinka, String ime, String prezime, String indeks, String korisnik_slika){
        con = ConnectionManager.getConnection();
        System.out.println("Izvrsen update");
        try {
            cStmt = con.prepareCall("{call student_update(?, ?, ?, ?, ?, ?)}");
            cStmt.setString(1, korisnicko_ime);
            cStmt.setString(2, lozinka);
            cStmt.setString(3, ime);
            cStmt.setString(4, prezime);
            cStmt.setString(5, indeks);
            cStmt.setString(6, korisnik_slika);
            cStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
