package com.registry;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class BloodDonorRegistry {
    private List<Donor> donors = new ArrayList<>();
    private final String csvFile = "data/donors.csv";

    public BloodDonorRegistry() throws IOException {
        importCSV(csvFile);
    }

    public void addDonor(Donor d) {
        donors.add(d);
        saveData();
    }

    public boolean deleteDonor(int id) {
        boolean removed = donors.removeIf(d -> d.getId() == id);
        if (removed) saveData();
        return removed;
    }

    public boolean updateDonor(int id, String newHospital, String newDoctor, String newContact) {
        for (Donor d : donors) {
            if (d.getId() == id) {
                d.setHospital(newHospital);
                d.setDoctor(newDoctor);
                d.setContactNumber(newContact);
                saveData();
                return true;
            }
        }
        return false;
    }

    public List<Donor> search(String bloodGroup, String location) {
        final String bgFilter = bloodGroup == null ? "" : bloodGroup.trim().toLowerCase();
        final String locFilter = location == null ? "" : location.trim().toLowerCase();

        return donors.stream()
                .filter(d ->
                        (bgFilter.isEmpty() || d.getBloodGroup().toLowerCase().equals(bgFilter)) &&
                        (locFilter.isEmpty() || d.getLocation().toLowerCase().contains(locFilter))
                )
                .collect(Collectors.toList());
    }

    public void displayAll() {
        if (donors.isEmpty()) {
            System.out.println("No donors available.");
            return;
        }
        donors.sort(Comparator.comparing(Donor::getBloodGroup).thenComparing(Donor::getName));
        System.out.println("ID | Date | Name | Age | Blood | Location | Hospital | Doctor | Contact");
        for (Donor d : donors) System.out.println(d.display());
    }

    public void exportCSV(String fileName) throws IOException {
        Files.createDirectories(Paths.get("data"));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write("ID,Date,Name,Age,BloodGroup,Location,Hospital,Doctor,ContactNumber\n");
            for (Donor d : donors) bw.write(d.toString() + "\n");
        }
        // Backup
        String backup = "data/donors_backup_" + System.currentTimeMillis() + ".csv";
        Files.copy(Paths.get(fileName), Paths.get(backup), StandardCopyOption.REPLACE_EXISTING);
    }

    public void importCSV(String fileName) throws IOException {
        File f = new File(fileName);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 9) {
                    Donor d = new Donor(
                            Integer.parseInt(parts[0]), parts[1], parts[2],
                            Integer.parseInt(parts[3]), parts[4], parts[5],
                            parts[6], parts[7], parts[8]
                    );
                    donors.add(d);
                }
            }
        }
    }

    private void saveData() {
        try {
            exportCSV(csvFile);
        } catch (IOException e) {
            System.err.println("❌ Error saving data: " + e.getMessage());
        }
    }

    public Map<String, Long> bloodGroupCount() {
        return donors.stream().collect(Collectors.groupingBy(Donor::getBloodGroup, Collectors.counting()));
    }

    public List<Donor> getDonors() { return donors; }
}