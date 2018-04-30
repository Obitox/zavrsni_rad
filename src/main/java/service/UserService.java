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


}
