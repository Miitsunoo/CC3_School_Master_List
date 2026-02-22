import java.util.List;
import java.util.Scanner;

public class Main {
    private static Repository repo = new Repository();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        
        while (running) {
            showMainMenu();
            int choice = getIntInput("Enter your choice (1, 2, 3 or 4): ");
            
            switch (choice) {
                case 1:
                    addStudents();
                    break;
                case 2:
                    showStudents();
                    break;
                case 3:
                    System.out.println("Closing application. Goodbye!");
                    running = false;
                    break;
                case 4:
                    deleteDatabase();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void showMainMenu() {
        System.out.println("===== Student Database =====");
        System.out.println("1. Add Students");
        System.out.println("2. View Students");
        System.out.println("3. Close");
        System.out.println("4. Delete Database");
        System.out.println("=============================");
    }

    private static void addStudents() {
        boolean addMore = true;
        
        while (addMore) {
            System.out.println("\n--- Add Student ---");
            String firstName = getStringInput("First Name: ");
            String lastName = getStringInput("Last Name: ");
            int age = getIntInput("Age: ");
            String gender = getStringInput("Gender (M/F): ");
            String course = getStringInput("Course: ");
            String email = getStringInput("Email: ");
            String phone = getStringInput("Phone Number: ");
            String address = getStringInput("Address: ");
            int studentNumber = getIntInput("Student Number: ");
            
            Student student = new Student(firstName, lastName, age, gender, course, email, phone, address, studentNumber);
            if (repo.addStudent(student)) {
                System.out.println("✓ Student added successfully!");
            } else {
                System.out.println("✗ Failed to add student.");
            }
            
            String continueInput = getStringInput("\nAdd another student? (y/n): ");
            if (!continueInput.equalsIgnoreCase("y")) {
                addMore = false;
            }
        }
    }

    private static void showStudents() {
        System.out.println("\n===== Enrolled Students =====");
        List<Student> students = repo.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No student in DB");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return getIntInput(prompt);
        }
    }

    private static void deleteDatabase() {
        System.out.println("\n⚠️  WARNING: You are about to delete the entire database!");
        String confirm = getStringInput("Are you sure? This action cannot be undone. Type 'YES' to confirm: ");
        
        if (confirm.equalsIgnoreCase("YES")) {
            if (repo.deleteAllStudents()) {
                System.out.println("✓ Database deleted successfully!");
            } else {
                System.out.println("✗ Failed to delete database.");
            }
        } else {
            System.out.println("✓ Database deletion cancelled.");
        }
    }
}