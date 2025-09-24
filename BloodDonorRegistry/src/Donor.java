// src/Donor.java
public class Donor {
    private int id;
    private String name;
    private int age;
    private String bloodGroup;
    private String contact;
    private String location;

    // Constructor
    public Donor(int id, String name, int age, String bloodGroup, String contact, String location) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.contact = contact;
        this.location = location;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getBloodGroup() { return bloodGroup; }
    public String getContact() { return contact; }
    public String getLocation() { return location; }

    // Convert to CSV
    public String toCSV() {
        return id + "," + name + "," + age + "," + bloodGroup + "," + contact + "," + location;
    }

    // Convert from CSV
    public static Donor fromCSV(String line) {
        String[] parts = line.split(",");
        return new Donor(
            Integer.parseInt(parts[0]),
            parts[1],
            Integer.parseInt(parts[2]),
            parts[3],
            parts[4],
            parts[5]
        );
    }

    @Override
    public String toString() {
        return "Donor [id=" + id + ", name=" + name + ", age=" + age + ", bloodGroup=" + bloodGroup +
               ", contact=" + contact + ", location=" + location + "]";
    }
}
