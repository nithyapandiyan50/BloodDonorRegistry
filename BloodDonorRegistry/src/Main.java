// src/Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BloodDonorRegistry registry = new BloodDonorRegistry();
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("\n1. Add Donor");
            System.out.println("2. List Donors");
            System.out.println("3. Delete Donor by ID");
            System.out.println("4. Find by Blood Group");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch(choice) {
                case 1:
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Age: "); int age = sc.nextInt(); sc.nextLine();
                    System.out.print("Blood Group: "); String bg = sc.nextLine();
                    System.out.print("Contact: "); String contact = sc.nextLine();
                    System.out.print("Location: "); String loc = sc.nextLine();
                    registry.addDonor(name, age, bg, contact, loc);
                    System.out.println("Donor added.");
                    break;
                case 2:
                    registry.listDonors();
                    break;
                case 3:
                    System.out.print("Enter ID to delete: ");
                    int id = sc.nextInt(); sc.nextLine();
                    if(registry.deleteById(id)) System.out.println("Deleted successfully.");
                    else System.out.println("ID not found.");
                    break;
                case 4:
                    System.out.print("Enter Blood Group: ");
                    String searchBG = sc.nextLine();
                    registry.findByBloodGroup(searchBG);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
