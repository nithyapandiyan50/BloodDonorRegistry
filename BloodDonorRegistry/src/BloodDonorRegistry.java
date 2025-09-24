// src/BloodDonorRegistry.java
import java.util.ArrayList;
import java.util.List;

public class BloodDonorRegistry {
    private List<Donor> donors = new ArrayList<>();
    private int nextId = 1;

    // Add a donor
    public void addDonor(String name, int age, String bloodGroup, String contact, String location) {
        Donor donor = new Donor(nextId++, name, age, bloodGroup, contact, location);
        donors.add(donor);
    }

    // Delete by ID
    public boolean deleteById(int id) {
        return donors.removeIf(d -> d.getId() == id);
    }

    // List all donors
    public void listDonors() {
        if(donors.isEmpty()) {
            System.out.println("No donors found.");
        } else {
            for(Donor d : donors) {
                System.out.println(d);
            }
        }
    }

    // Find by blood group
    public void findByBloodGroup(String bg) {
        for(Donor d : donors) {
            if(d.getBloodGroup().equalsIgnoreCase(bg)) {
                System.out.println(d);
            }
        }
    }
}
