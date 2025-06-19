package Dao;

import Model.CellChief;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CellChiefDao {
    // Register a new cell chief
    public boolean registerCellChief(CellChief cellChief) throws SQLException {
        String sql = "INSERT INTO cell_chiefs (first_name, last_name, national_id, password, email, cell_name, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cellChief.getFirstName());
            stmt.setString(2, cellChief.getLastName());
            stmt.setString(3, cellChief.getNationalId());
            stmt.setString(4, cellChief.getPassword());
            stmt.setString(5, cellChief.getEmail());
            stmt.setString(6, cellChief.getCellName());
            stmt.setString(7, cellChief.getPhoneNumber());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    // Login cell chief
    public CellChief loginCellChief(String email, String password) throws SQLException {
        String sql = "SELECT * FROM cell_chiefs WHERE email = ? AND password = ?";
        CellChief cellChief = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cellChief = new CellChief();
                    cellChief.setId(rs.getInt("id"));
                    cellChief.setFirstName(rs.getString("first_name"));
                    cellChief.setLastName(rs.getString("last_name"));
                    cellChief.setNationalId(rs.getString("national_id"));
                    cellChief.setPassword(rs.getString("password"));
                    cellChief.setEmail(rs.getString("email"));
                    cellChief.setCellName(rs.getString("cell_name"));
                    cellChief.setPhoneNumber(rs.getString("phone_number"));
                }
            }
        }
        return cellChief;
    }
    
    // Check if email exists
    public boolean emailExists(String email) throws SQLException {
        String sql = "SELECT id FROM cell_chiefs WHERE email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    // Check if cell name exists
    public boolean cellNameExists(String cellName) throws SQLException {
        String sql = "SELECT id FROM cell_chiefs WHERE cell_name = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cellName);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    // Check if national ID exists
    public boolean nationalIdExists(String nationalId) throws SQLException {
        String sql = "SELECT id FROM cell_chiefs WHERE national_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nationalId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}