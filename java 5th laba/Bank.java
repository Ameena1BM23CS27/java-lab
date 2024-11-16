import java.util.Scanner;

class Account {
    private String customer_name;
    private int acc_no;
    protected double balance;

    public Account(String customer_name, int acc_no, double balance) {
        this.customer_name = customer_name;
        this.acc_no = acc_no;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= getBalance()) {
            balance -= amount;
            System.out.println("Withdrew: " + amount + " | Balance is: " + balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void displayBalance() {
        System.out.println("Current Balance: " + balance);
    }
}

class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String customerName, int accountNumber, double initialBalance, double interestRate) {
        super(customerName, accountNumber, initialBalance);
        this.interestRate = interestRate;
    }

    public void computeAndDepositInterest() {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
    }
}

class CurrentAccount extends Account {
    private double minimumBalance;
    private double serviceCharge;

    public CurrentAccount(String customerName, int accountNumber, double initialBalance, double minimumBalance, double serviceCharge) {
        super(customerName, accountNumber, initialBalance);
        this.minimumBalance = minimumBalance;
        this.serviceCharge = serviceCharge;
    }

    public void checkMinimumBalance() {
        if (getBalance() < minimumBalance) {
            System.out.println("Balance is below the minimum!");
            balance -= serviceCharge;
            System.out.println("Deducted service charge: " + serviceCharge);
            System.out.println("Balance after deduction: " + balance);
        }
    }
}

public class Bank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer name:");
        String name = sc.nextLine();
        System.out.println("Enter account number:");
        int acc_no = sc.nextInt();
        System.out.println("Enter initial balance:");
        double balance = sc.nextDouble();
        System.out.println("Enter minimum balance:");
        double minimum_balance = sc.nextDouble();
        System.out.println("Enter interest rate:");
        double interest_rate = sc.nextDouble();
        System.out.println("Enter service charge:");
        double service_charge = sc.nextDouble();
        System.out.println("Enter choice:\n1. Current Account\n2. Savings Account");
        int ch = sc.nextInt();

        System.out.println("Customer Name: " + name);
        System.out.println("Account Number: " + acc_no);

        switch (ch) {
            case 1: // Current Account
                System.out.println("Account is of Current type.");
                CurrentAccount ca = new CurrentAccount(name, acc_no, balance, minimum_balance, service_charge);
                while (true) {
                    System.out.println("Enter choice:\n1. Deposit\n2. Withdraw\n3. Display Balance\n4. Exit");
                    int c = sc.nextInt();
                    if (c == 1) {
                        System.out.println("Enter amount to deposit:");
                        double amt = sc.nextDouble();
                        ca.deposit(amt);
                    } else if (c == 2) {
                        System.out.println("Enter amount to withdraw:");
                        double amt = sc.nextDouble();
                        ca.withdraw(amt);
                    } else if (c == 3) {
                        ca.checkMinimumBalance();
                        ca.displayBalance();
                    } else {
                        System.out.println("Exiting Current Account...");
                        break;
                    }
                }
                break;

            case 2: // Savings Account
                System.out.println("Account is of Savings type.");
                SavingsAccount sa = new SavingsAccount(name, acc_no, balance, interest_rate);
                while (true) {
                    System.out.println("Enter choice:\n1. Deposit\n2. Withdraw\n3. Display Balance\n4. Exit");
                    int c = sc.nextInt();
                    if (c == 1) {
                        System.out.println("Enter amount to deposit:");
                        double amt = sc.nextDouble();
                        sa.deposit(amt);
                    } else if (c == 2) {
                        System.out.println("Enter amount to withdraw:");
                        double amt = sc.nextDouble();
                        sa.withdraw(amt);
                    } else if (c == 3) {
                        sa.computeAndDepositInterest();
                        sa.displayBalance();
                    } else {
                        System.out.println("Exiting Savings Account...");
                        break;
                    }
                }
                break;

            default:
                System.out.println("Invalid choice! Exiting...");
        }

        System.out.println("Name: Ameena Yasmeen\nUSN: 1BM23CS027");
        sc.close();
    }
}
