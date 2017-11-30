package service;

import com.mysql.cj.jdbc.ConnectionGroupManager;
import com.mysql.cj.jdbc.PreparedStatement;
import database.ConnectionManager;
import model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private Statement stmt = null;
    private Connection con = null;
    private ResultSet rs = null;
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

    public void updateStudnet(String korisnicko_ime, String lozinka, String ime, String prezime, String indeks, String korisnik_slika){
        
    }

}
