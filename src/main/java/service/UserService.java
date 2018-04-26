package service;

import database.ConnectionManager;

import java.sql.*;

public class UserService {
    private PreparedStatement stmt = null;
    private Connection con = null;
    private ResultSet rs = null;
    private CallableStatement cStmt = null;
    private String sql = "";

    public UserService() {
        this.con = ConnectionManager.getConnection();
    }

    public String userLogin(String username, String password){
        con = ConnectionManager.getConnection();
        try{
            cStmt=con.prepareCall ("{ CALL loginUser_sp(?, ?, ?) }");
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
