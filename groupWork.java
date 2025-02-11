// VICTOR NJUKI
// IRENE GITAU
// BLESSED MARGARET
// CHANTAL MATU
// COLLINS OWENDE
import java.util.*;

public class groupWork {
    Scanner input = new Scanner(System.in);
    
    User[] users = {
        new User("Alice", 0, "1234"),
        new User("Bob", 1, "4321"),
        new User("Charlie", 2, "5678"),
        new User("David", 3, "8765"),
    };

    public static class User {
        String username;
        int accountNumber;
        double balance = 0.00;
        String pin;

        public User(String username, int accountNumber, String pin) {
            this.accountNumber = accountNumber;
            this.pin = pin;
        }
    }

    public class Deposit {
        User user;

        public Deposit(User user) {
            this.user = user;
        }

        public void deposit() {
            System.out.print("Enter amount to be deposited: ");
            double depositAmount = input.nextDouble();
            if (depositAmount < 1) {
                System.out.println("Error depositing, amount is too low.");
            } else {                
                user.balance += depositAmount;
                System.out.println("Deposit successful, new balance is KES " + user.balance);
            }
        }
    }

    public class Withdraw {
        User user;

        public Withdraw(User user) {
            this.user = user;
        }

        public void withdraw() {
            System.out.print("Enter amount to be withdrawn: ");
            double withdrawAmount = input.nextDouble();
            if (user.balance == 0 || user.balance < withdrawAmount) {
                System.out.println("Insufficient balance. Please top up.");
            } else {
                user.balance -= withdrawAmount;
                System.out.println("KES " + withdrawAmount + " withdrawn successfully! New balance: KES " + user.balance);
            }
        }
    }

    public class CheckBalance {
        User user;

        public CheckBalance(User user) {
            this.user = user;
        }

        public void displayBalance() {
            System.out.println("Your current balance is KES " + user.balance);
        }
    }

    public User findUser() {
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;
        
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter username: ");
            String username = input.next();
            System.out.print("Enter account number: ");
            int accountNumber = input.nextInt();
            
            for (User user : users) {
                if (user.username.equalsIgnoreCase(username) && user.accountNumber == accountNumber) {
                    for (int pinAttempts = 0; pinAttempts < MAX_ATTEMPTS; pinAttempts++) {
                        System.out.print("Enter PIN: ");
                        String pin = input.next();
                        
                        if (user.pin.equals(pin)) {
                            return user;
                        } else {
                            System.out.println("Incorrect PIN. Please try again.");
                        }
                    }
                    System.out.println("Maximum PIN attempts reached. Access denied.");
                    return null;
                }
            }
            System.out.println("Incorrect username or account number. Please try again.");
            attempts++;
        }
        System.out.println("Maximum login attempts reached. Access denied.");
        return null;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        groupWork outer = new groupWork();
        
        User user = outer.findUser();
        if (user == null) {
            System.out.println("Access Denied. Exiting...");
            return;
        }
        
        Deposit makeDeposit = outer.new Deposit(user);
        Withdraw makeWithdraw = outer.new Withdraw(user);
        CheckBalance requestBalance = outer.new CheckBalance(user);
        
        System.out.println("\n--------------------------------------\nWelcome to DiddyBank!");
        
        while (true) {
            System.out.println("\nPlease select a service (enter 4 to exit):");
            System.out.println("1. Deposit money \n2. Check Account Balance \n3. Withdraw Money\n4. Exit");
            int choice = input.nextInt();
            
            switch (choice) {
                case 1:
                    makeDeposit.deposit();
                    break;
                case 2:
                    requestBalance.displayBalance();
                    break;
                case 3:
                    makeWithdraw.withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for choosing DiddyBank!");
                    input.close();
                    return;
                default:
                    System.out.println("Error: Choose a valid option.");
                    break;
            }
}
}
}
