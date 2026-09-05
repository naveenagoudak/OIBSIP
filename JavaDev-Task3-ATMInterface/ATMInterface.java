import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String toString() {
        return type + ": Rs. " + amount;
    }
}

class Account {
    private String userId;
    private String userPin;
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public Account(String userId, String userPin, double initialBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean validatePin(String pin) {
        return this.userPin.equals(pin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposited", amount));
            System.out.println("Rs. " + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient Funds! Current balance: Rs. " + balance);
        } else {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawn", amount));
            System.out.println("Rs. " + amount + " withdrawn successfully.");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid transfer amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient Funds to transfer! Current balance: Rs. " + balance);
        } else {
            balance -= amount;
            recipient.balance += amount;
            transactionHistory.add(new Transaction("Transferred to " + recipient.userId, amount));
            recipient.transactionHistory.add(new Transaction("Received from " + this.userId, amount));
            System.out.println("Rs. " + amount + " transferred successfully to " + recipient.userId);
        }
    }

    public void printTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction t : transactionHistory) {
                System.out.println(t);
            }
        }
    }
}

class Bank {
    private Account account;
    private Account recipientAccount; // For demo transfer

    public Bank() {
        // Sample default user: User ID: admin, PIN: 1234, Initial Balance: 10000
        this.account = new Account("admin", "1234", 10000.0);
        // Sample recipient for transfer
        this.recipientAccount = new Account("user2", "0000", 5000.0);
    }

    public Account getAccount() {
        return account;
    }

    public Account getRecipientAccount() {
        return recipientAccount;
    }
}

class ATM {
    private Account currentAccount;
    private Account recipientAccount;
    private Scanner scanner;

    public ATM(Account account, Account recipientAccount) {
        this.currentAccount = account;
        this.recipientAccount = recipientAccount;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("==========================================");
        System.out.println("         WELCOME TO ATM SYSTEM            ");
        System.out.println("==========================================");

        int attempts = 0;
        boolean authenticated = false;

        while (attempts < 3) {
            System.out.print("Enter User ID: ");
            String id = scanner.next();
            System.out.print("Enter 4-Digit PIN: ");
            String pin = scanner.next();

            if (currentAccount.validatePin(pin)) {
                authenticated = true;
                System.out.println("\nLogin Successful! Welcome, " + id);
                break;
            } else {
                attempts++;
                System.out.println("Invalid Credentials! Attempts left: " + (3 - attempts));
            }
        }

        if (!authenticated) {
            System.out.println("Access Denied! Account locked due to 3 failed attempts.");
            return;
        }

        int choice;
        do {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
            System.out.println("6. Quit");
            System.out.print("Choose an option (1-6): ");

            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                scanner.next();
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    currentAccount.printTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: Rs. ");
                    double wAmount = scanner.nextDouble();
                    currentAccount.withdraw(wAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: Rs. ");
                    double dAmount = scanner.nextDouble();
                    currentAccount.deposit(dAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient User ID (e.g., user2): ");
                    String targetId = scanner.next();
                    System.out.print("Enter amount to transfer: Rs. ");
                    double tAmount = scanner.nextDouble();
                    currentAccount.transfer(recipientAccount, tAmount);
                    break;
                case 5:
                    System.out.println("Current Available Balance: Rs. " + currentAccount.getBalance());
                    break;
                case 6:
                    System.out.println("\nThank you for using our ATM service. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option! Please select between 1 and 6.");
            }
        } while (choice != 6);
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Bank bank = new Bank();
        ATM atm = new ATM(bank.getAccount(), bank.getRecipientAccount());
        atm.start();
    }
}
