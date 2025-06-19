package Model;

import java.util.Date;

public class Task {
    private int id;
    private String title;
    private String description;
    private Date taskDate;
    private int villageChiefId;
    private int cellChiefId;

    public Task() {
    }

    public Task(String title, String description, Date taskDate, int villageChiefId, int cellChiefId) {
        this.title = title;
        this.description = description;
        this.taskDate = taskDate;
        this.villageChiefId = villageChiefId;
        this.cellChiefId = cellChiefId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public int getVillageChiefId() {
        return villageChiefId;
    }

    public void setVillageChiefId(int villageChiefId) {
        this.villageChiefId = villageChiefId;
    }

    public int getCellChiefId() {
        return cellChiefId;
    }

    public void setCellChiefId(int cellChiefId) {
        this.cellChiefId = cellChiefId;
    }
}