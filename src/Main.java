import java.io.*;
import java.util.*;

class Customer {
    int accountNumber;
    String firstName;
    String lastName;
    String address;
    String contact;
    String email;
    String nic;
    String dob;
    String regDate;

    Customer(int accountNumber, String firstName, String lastName, String address, String contact, String email, String nic, String dob, String regDate) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.nic = nic;
        this.dob = dob;
        this.regDate = regDate;
    }
}

class Bill {
    int accountNumber;
    String name;
    String address;
    String contact;
    String email;
    double voice;
    double data;
    int sms;



    Bill(int accountNumber, String name, String address, String contact, String email, double voice, double data, int sms) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.voice = voice;
        this.data = data;
        this.sms = sms;
    }
}

public class Main {
    private static final String CUSTOMER_FILE = "customers.txt";
    private static final String BILL_FILE = "bills.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean loggedIn = false;
        boolean isAdmin = false;
        boolean isUser = false;

        System.out.println("Welcome To Dialcom Telecommunication !");

        while (!loggedIn) {
            System.out.print("Enter your Username: ");
            String userName = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            if (userName.equals("Admin") && password.equals("admin123")) {
                    loggedIn = true;
                    isAdmin = true;
            }else if (userName.equals("User") && password.equals("user123")){
                    loggedIn = true;
                    isUser = true;
            }else {
                System.out.println("Invalid user type. Try again.");
            }
        }

        if (isAdmin) {
            performAdminOperations();
        } else if (isUser) {
            performUserOperations();
        }
    }

    private static void performAdminOperations() {
        boolean exit = false;

        while (!exit) {
            System.out.println("Admin Menu:");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Logout");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    viewCustomers();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void performUserOperations() {
        boolean exit = false;

        while (!exit) {
            System.out.println("User Menu:");
            System.out.println("1. Calculate the Bill");
            System.out.println("2. Logout");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    calculateBill();
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addCustomer() {
        System.out.print("Enter Account Number:");   
        int accountNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter customer First Name:");
        String firstName = scanner.nextLine();

        System.out.print("Enter customer Last Name:");
        String lastName = scanner.nextLine();

        System.out.print("Enter customer Address:");
        String address = scanner.nextLine();

        System.out.print("Enter customer Contact:");
        String contact = scanner.nextLine();

        System.out.print("Enter customer Email:");
        String email = scanner.nextLine();

        System.out.print("Enter customer NIC:");
        String nic = scanner.nextLine();

        System.out.print("Enter customer Date of Birth:");
        String dob = scanner.nextLine();


        String regDate = "2023-01-25";

        Customer customer = new Customer(accountNumber, firstName, lastName, address, contact, email, nic, dob, regDate);
        writeCustomerToFile(customer);
        System.out.println("Customer added successfully.");
    }

    private static void writeCustomerToFile(Customer customer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMER_FILE, true))) {
            writer.write(customer.accountNumber + "--" + customer.firstName + "--" + customer.lastName + "--" + customer.address + "--" + customer.contact + "--" + customer.email + "--" + customer.nic + "--" + customer.dob + "--" + customer.regDate + ",");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void viewCustomers() {
        System.out.println("List of Customers:");
        System.out.println("----------------------------");

        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("--");
                System.out.println("Account Number : " + data[0]);
                System.out.println("Name : " + data[1] + " " + data[2]);
                System.out.println("Address : " + data[3]);
                System.out.println("Contact : " + data[4]);
                System.out.println("Email : " + data[5]);
                System.out.println("NIC : " + data[6]);
                System.out.println("Birth Day : " + data[7]);
                System.out.println("Registerd Date : " + data[8]);
                System.out.println("----------------------------");
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    private static void updateCustomer() {
        // Implement customer update logic here
        System.out.println("Update Customer");
    }

    private static void deleteCustomer() {
        // Implement customer delete logic here
        System.out.println("Delete Customer");
    }


    private static void calculateBill(){
        System.out.print("Enter Account number to select a Custome : ");
        int accountId = scanner.nextInt();

        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("--");
                String accountNumber = data[0];
                if(accountId == Integer.parseInt(accountNumber)){
                    Customer selectedCustomer = new Customer(accountId, data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]);+
                    line =null;
                }else{
                    System.out.println("Customer Not Found !");
                }
                System.out.println("Account Number : " + selectedCustomer.accountNumber);
                System.out.println("Name : " + data[1] + " " + data[2]);
                System.out.println("Address : " + data[3]);
                System.out.println("Contact : " + data[4]);
                System.out.println("Email : " + data[5]);
                System.out.println("NIC : " + data[6]);
                System.out.println("Birth Day : " + data[7]);
                System.out.println("Registerd Date : " + data[8]);
                System.out.println("----------------------------");
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

    } 

    private static void writeBillToFile(Bill Bill) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMER_FILE, true))) {
            writer.write(customer.accountNumber + "--" + customer.firstName + "--" + customer.lastName + "--" + customer.address + "--" + customer.contact + "--" + customer.email + "--" + customer.nic + "--" + customer.dob + "--" + customer.regDate + ",");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void viewBill() {
        System.out.println("List of Customers:");
        System.out.println("----------------------------");

        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("--");
                System.out.println("Account Number : " + data[0]);
                System.out.println("Name : " + data[1] + " " + data[2]);
                System.out.println("Address : " + data[3]);
                System.out.println("Contact : " + data[4]);
                System.out.println("Email : " + data[5]);
                System.out.println("NIC : " + data[6]);
                System.out.println("Birth Day : " + data[7]);
                System.out.println("Registerd Date : " + data[8]);
                System.out.println("----------------------------");
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    private static void updateBill() {
        // Implement customer update logic here
        System.out.println("Update Customer");
    }

    private static void deleteBill() {
        // Implement customer delete logic here
        System.out.println("Delete Customer");
    }

}
