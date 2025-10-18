package com.registry;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainApp {
    private static final String[] BLOOD_GROUPS = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

    public static void main(String[] args) throws IOException {
        BloodDonorRegistry registry = new BloodDonorRegistry();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Donor  2. Display All  3. Delete by ID  4. Search  5. Update Donor  6. Export CSV  7. Blood Group Chart  8. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: "); 
                    String name = sc.nextLine();
                    System.out.print("Age: "); 
                    int age = sc.nextInt(); sc.nextLine();

                    // Blood group selection with arrow navigation simulation
                    String bloodGroup = selectBloodGroup(sc);

                    System.out.print("Location: "); String loc = sc.nextLine();
                    System.out.print("Hospital: "); String hosp = sc.nextLine();
                    System.out.print("Doctor: "); String doc = sc.nextLine();
                    System.out.print("Contact Number: "); String contact = sc.nextLine();

                    if (name.isEmpty() || bloodGroup.isEmpty() || contact.isEmpty()) {
                        System.out.println("❗ Name, Blood Group, and Contact are mandatory!");
                        break;
                    }

                    registry.addDonor(new Donor(name, age, bloodGroup, loc, hosp, doc, contact));
                    System.out.println("✅ Donor added successfully!");
                    break;

                case 2:
                    registry.displayAll();
                    break;

                case 3:
                    System.out.print("Enter ID to delete: ");
                    int delId = sc.nextInt(); sc.nextLine();
                    System.out.println(registry.deleteDonor(delId) ? "🗑 Deleted!" : "⚠ ID not found!");
                    break;

                case 4:
                    System.out.print("Blood Group (leave empty to ignore): "); 
                    String sBG = sc.nextLine();
                    System.out.print("Location (leave empty to ignore): "); 
                    String sLoc = sc.nextLine();

                    List<Donor> results = registry.search(sBG, sLoc);
                    if (results.isEmpty()) {
                        System.out.println("❌ No matching donors found.");
                    } else {
                        System.out.println("\nID | Date | Name | Age | Blood | Location | Hospital | Doctor | Contact");
                        results.forEach(d -> System.out.println(d.display()));
                    }
                    break;

                case 5:
                    System.out.print("Enter Donor ID to Update: ");
                    int updId = sc.nextInt(); sc.nextLine();
                    System.out.print("New Hospital: "); String newHosp = sc.nextLine();
                    System.out.print("New Doctor: "); String newDoc = sc.nextLine();
                    System.out.print("New Contact: "); String newContact = sc.nextLine();
                    System.out.println(registry.updateDonor(updId, newHosp, newDoc, newContact) ?
                            "✅ Updated successfully!" : "⚠ ID not found!");
                    break;

                case 6:
                    registry.exportCSV("data/donors.csv");
                    System.out.println("📁 CSV exported & backup created!");
                    break;

                case 7:
                    Map<String, Long> counts = registry.bloodGroupCount();
                    System.out.println("\n🩸 Blood Group Counts (Vertical Chart):");
                    counts.forEach((bgp, cnt) ->
                            System.out.printf("%-4s | %-30s (%d)%n", bgp, "|".repeat(cnt.intValue()), cnt)
                    );
                    break;

                case 8:
                    registry.exportCSV("data/donors.csv");
                    System.out.println("💾 Data saved. Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Simulated arrow navigation for blood group selection
    private static String selectBloodGroup(Scanner sc) {
        System.out.println("Select Blood Group:");
        for (int i = 0; i < BLOOD_GROUPS.length; i++) {
            System.out.printf("%d. %s\n", i + 1, BLOOD_GROUPS[i]);
        }
        int choice = -1;
        while (choice < 1 || choice > BLOOD_GROUPS.length) {
            System.out.print("Enter number (1-8): ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt(); sc.nextLine();
            } else {
                sc.nextLine();
            }
        }
        return BLOOD_GROUPS[choice - 1];
    }
}