import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class phonebook {

	private static final Scanner scanner = new Scanner(System.in);
    private static final List<User> phoneBook = new ArrayList<>();

    static class User {
        private String name;
        private String email;
        private String phoneNumber;

        public String getName() {
            return this.name;
        }

        public String getEmail() {
            return this.email;
        }

        public String getPhoneNumber() {
            return this.phoneNumber;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }

    public static void main(String args[]) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Add a new User");
            System.out.println("2. Search by Email");
            System.out.println("3. Search by Phone Number");
            System.out.println("4. Search by Name");
            System.out.println("5. Delete by Name");
            System.out.println("6. Edit by Name");
            System.out.println("7. Exit");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    addNewUser();
                    break;
                case 2:
                    searchUserByEmail();
                    break;
                case 3:
                    searchUserByPhoneNumber();
                    break;
                case 4:
                    searchUserByName();
                    break;
                case 5:
                    deleteUserByName();
                    break;
                case 6:
                    editUserByName();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void editUserByName() {
        System.out.print("Enter the name to edit: ");
        String searchName = scanner.next().trim();
        List<User> searchResults = findUsersByName(searchName);

        if (searchResults.isEmpty()) {
            System.out.println("No matching users found.");
            return;
        }

        System.out.println("Matching users:");
        displayUsers(searchResults);

        System.out.print("Enter the index of the user to edit: ");
        int index = scanner.nextInt();

        if (index >= 0 && index < searchResults.size()) {
            User userToEdit = searchResults.get(index);
            System.out.print("Enter new name: ");
            userToEdit.setName(scanner.next().trim());
            System.out.print("Enter new email: ");
            userToEdit.setEmail(scanner.next().trim());
            System.out.print("Enter new phone number: ");
            userToEdit.setPhoneNumber(scanner.next().trim());
            System.out.println("User updated successfully.");
        } else {
            System.out.println("Invalid index. No changes made.");
        }
    }

    private static void deleteUserByName() {
        System.out.print("Enter the name to delete: ");
        String searchName = scanner.next().trim();
        List<User> searchResults = findUsersByName(searchName);

        if (searchResults.isEmpty()) {
            System.out.println("No matching users found.");
            return;
        }

        System.out.println("Matching users:");
        displayUsers(searchResults);

        System.out.print("Enter the index of the user to delete: ");
        int index = scanner.nextInt();

        if (index >= 0 && index < searchResults.size()) {
            phoneBook.remove(searchResults.get(index));
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Invalid index. No changes made.");
        }
    }

    private static void searchUserByName() {
        System.out.print("Enter the name to search: ");
        String searchName = scanner.next().trim();
        List<User> searchResults = findUsersByName(searchName);

        if (searchResults.isEmpty()) {
            System.out.println("No matching users found.");
        } else {
            System.out.println("Matching users:");
            displayUsers(searchResults);
        }
    }

    private static List<User> findUsersByName(String searchName) {
        List<User> results = new ArrayList<>();
        for (User user : phoneBook) {
            if (user.getName().toLowerCase().startsWith(searchName.toLowerCase())) {
                results.add(user);
            }
        }
        return results;
    }

    private static void searchUserByPhoneNumber() {
        System.out.print("Enter the phone number to search: ");
        String searchNumber = scanner.next().trim();
        List<User> searchResults = findUsersByPhoneNumber(searchNumber);

        if (searchResults.isEmpty()) {
            System.out.println("No matching users found.");
        } else {
            System.out.println("Matching users:");
            displayUsers(searchResults);
        }
    }

    private static List<User> findUsersByPhoneNumber(String searchNumber) {
        List<User> results = new ArrayList<>();
        for (User user : phoneBook) {
            if (user.getPhoneNumber().equals(searchNumber)) {
                results.add(user);
            }
        }
        return results;
    }

    private static void searchUserByEmail() {
        System.out.print("Enter the email to search: ");
        String searchEmail = scanner.next().trim();
        List<User> searchResults = findUsersByEmail(searchEmail);

        if (searchResults.isEmpty()) {
            System.out.println("No matching users found.");
        } else {
            System.out.println("Matching users:");
            displayUsers(searchResults);
        }
    }

    private static List<User> findUsersByEmail(String searchEmail) {
        List<User> results = new ArrayList<>();
        for (User user : phoneBook) {
            if (user.getEmail().equalsIgnoreCase(searchEmail)) {
                results.add(user);
            }
        }
        return results;
    }

    private static void addNewUser() {
        User newUser = new User();
        System.out.print("Enter name: ");
        newUser.setName(scanner.next().trim());
        System.out.print("Enter email: ");
        String email = scanner.next().trim();
        if (isEmailUnique(email)) {
            newUser.setEmail(email);
        } else {
            System.out.println("Email already exists. Aborting user addition.");
            return;
        }
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.next().trim();
        if (isPhoneNumberUnique(phoneNumber)) {
            newUser.setPhoneNumber(phoneNumber);
        } else {
            System.out.println("Phone number already exists. Aborting user addition.");
            return;
        }

        phoneBook.add(newUser);
        System.out.println("User added successfully.");
    }

    private static boolean isEmailUnique(String email) {
        for (User user : phoneBook) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPhoneNumberUnique(String phoneNumber) {
        for (User user : phoneBook) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                return false;
            }
        }
        return true;
    }


    private static void displayUsers(List<User> users) {
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i + ". " + users.get(i).getName() + " - " + "Email");
}
        
    }
}
