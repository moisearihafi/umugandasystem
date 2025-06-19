package Dao;

import Model.VillageChief;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VillageChieDao {
    // Register a new village chief
    public boolean registerVillageChief(VillageChief villageChief) throws SQLException {
        String sql = "INSERT INTO village_chiefs (full_name, national_id, village_name, phone_number, cell_chief_id) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, villageChief.getFullName());
            stmt.setString(2, villageChief.getNationalId());
            stmt.setString(3, villageChief.getVillageName());
            stmt.setString(4, villageChief.getPhoneNumber());
            stmt.setInt(5, villageChief.getCellChiefId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    // Update village chief
    public boolean updateVillageChief(VillageChief villageChief) throws SQLException {
        String sql = "UPDATE village_chiefs SET full_name = ?, national_id = ?, village_name = ?, phone_number = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, villageChief.getFullName());
            stmt.setString(2, villageChief.getNationalId());
            stmt.setString(3, villageChief.getVillageName());
            stmt.setString(4, villageChief.getPhoneNumber());
            stmt.setInt(5, villageChief.getId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    // Delete village chief
    public boolean deleteVillageChief(int villageChiefId) throws SQLException {
        String sql = "DELETE FROM village_chiefs WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, villageChiefId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    // Get all village chiefs for a cell chief
    public List<VillageChief> getAllVillageChiefs(int cellChiefId) throws SQLException {
        List<VillageChief> villageChiefs = new ArrayList<>();
        String sql = "SELECT * FROM village_chiefs WHERE cell_chief_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, cellChiefId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    VillageChief villageChief = new VillageChief();
                    villageChief.setId(rs.getInt("id"));
                    villageChief.setFullName(rs.getString("full_name"));
                    villageChief.setNationalId(rs.getString("national_id"));
                    villageChief.setVillageName(rs.getString("village_name"));
                    villageChief.setPhoneNumber(rs.getString("phone_number"));
                    villageChief.setCellChiefId(rs.getInt("cell_chief_id"));
                    
                    villageChiefs.add(villageChief);
                }
            }
        }
        return villageChiefs;
    }
    
    // Check if village name exists
    public boolean villageNameExists(String villageName) throws SQLException {
        String sql = "SELECT id FROM village_chiefs WHERE village_name = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, villageName);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    // Check if national ID exists
    public boolean nationalIdExists(String nationalId) throws SQLException {
        String sql = "SELECT id FROM village_chiefs WHERE national_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nationalId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    // Get village chief by ID
    public VillageChief getVillageChiefById(int villageChiefId) throws SQLException {
        String sql = "SELECT * FROM village_chiefs WHERE id = ?";
        VillageChief villageChief = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, villageChiefId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    villageChief = new VillageChief();
                    villageChief.setId(rs.getInt("id"));
                    villageChief.setFullName(rs.getString("full_name"));
                    villageChief.setNationalId(rs.getString("national_id"));
                    villageChief.setVillageName(rs.getString("village_name"));
                    villageChief.setPhoneNumber(rs.getString("phone_number"));
                    villageChief.setCellChiefId(rs.getInt("cell_chief_id"));
                }
            }
        }
        return villageChief;
    }
    // Inside VillageChieDao.java

public VillageChief getVillageChiefByName(String villageChiefName, int cellChiefId) throws SQLException {
    String query = "SELECT * FROM village_chiefs WHERE village_name = ? AND cell_chief_Id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setString(1, villageChiefName);
        stmt.setInt(2, cellChiefId);
        
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                VillageChief villageChief = new VillageChief();
                villageChief.setId(rs.getInt("id"));
                villageChief.setVillageName(rs.getString("village_name"));
                villageChief.setCellChiefId(rs.getInt("cell_chief_Id"));
                return villageChief;
            }
        }
    }
    
    return null; // VillageChief not found
}


}