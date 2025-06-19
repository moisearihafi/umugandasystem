package Model;

public class VillageChief {
    private int id;
    private String fullName;
    private String nationalId;
    private String villageName;
    private String phoneNumber;
    private int cellChiefId;

    public VillageChief() {
    }

    public VillageChief(String fullName, String nationalId, String villageName, String phoneNumber, int cellChiefId) {
        this.fullName = fullName;
        this.nationalId = nationalId;
        this.villageName = villageName;
        this.phoneNumber = phoneNumber;
        this.cellChiefId = cellChiefId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCellChiefId() {
        return cellChiefId;
    }

    public void setCellChiefId(int cellChiefId) {
        this.cellChiefId = cellChiefId;
    }
    @Override
    public String toString() {
        // Return a string that makes sense for your combo box, e.g., the full name and village name
         return fullName + " (" + villageName + ")"; // Modify this as needed
    }
}