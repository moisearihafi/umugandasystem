package Dao;

import Model.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {
    // Register a new task
    public boolean registerTask(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (title, description, task_date, village_chief_id, cell_chief_id) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, new java.sql.Date(task.getTaskDate().getTime()));
            stmt.setInt(4, task.getVillageChiefId());
            stmt.setInt(5, task.getCellChiefId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    // Update task
    public boolean updateTask(Task task) throws SQLException {
        String sql = "UPDATE tasks SET title = ?, description = ?, task_date = ?, village_chief_id = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, new java.sql.Date(task.getTaskDate().getTime()));
            stmt.setInt(4, task.getVillageChiefId());
            stmt.setInt(5, task.getId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    // Delete task
    public boolean deleteTask(int taskId) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, taskId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    // Get all tasks for a cell chief
    public List<Task> getAllTasks(int cellChiefId) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT t.*, v.full_name as village_chief_name FROM tasks t " +
                     "JOIN village_chiefs v ON t.village_chief_id = v.id " +
                     "WHERE t.cell_chief_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, cellChiefId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task();
                    task.setId(rs.getInt("id"));
                    task.setTitle(rs.getString("title"));
                    task.setDescription(rs.getString("description"));
                    task.setTaskDate(rs.getDate("task_date"));
                    task.setVillageChiefId(rs.getInt("village_chief_id"));
                    task.setCellChiefId(rs.getInt("cell_chief_id"));
                    
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }
    
    // Get task by ID
    public Task getTaskById(int taskId) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        Task task = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, taskId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    task = new Task();
                    task.setId(rs.getInt("id"));
                    task.setTitle(rs.getString("title"));
                    task.setDescription(rs.getString("description"));
                    task.setTaskDate(rs.getDate("task_date"));
                    task.setVillageChiefId(rs.getInt("village_chief_id"));
                    task.setCellChiefId(rs.getInt("cell_chief_id"));
                }
            }
        }
        return task;
    }
    
    // Get tasks by village chief
    public List<Task> getTasksByVillageChief(int villageChiefId) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE village_chief_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, villageChiefId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task();
                    task.setId(rs.getInt("id"));
                    task.setTitle(rs.getString("title"));
                    task.setDescription(rs.getString("description"));
                    task.setTaskDate(rs.getDate("task_date"));
                    task.setVillageChiefId(rs.getInt("village_chief_id"));
                    task.setCellChiefId(rs.getInt("cell_chief_id"));
                    
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }
}