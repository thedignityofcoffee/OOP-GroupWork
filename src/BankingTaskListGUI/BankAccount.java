/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankingTaskListGUI;

/**
 * @author ashongtical
 * @author Bertrand
 */
public class BankAccount {
    
    // declare variables
    private double balance;
    private Integer depositsCount;
    private Integer withdrawalsCount;
    private double annualInterestRate;
    private double monthlyServiceCharges;
    
    // constructor
    public BankAccount(double balance, double annualInterestRate) {
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.depositsCount = 0;
        this.withdrawalsCount = 0;
        this.monthlyServiceCharges = 0;
    }

    // The Deposit method to put 
    // check if amount to be deposited is 0 or less otherwise add amount to balance
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            depositsCount++;
            System.out.println("Deposit of RMB " + amount + " successful. New balance: RMB " + balance);
        } else {
            System.out.println("Invalid deposit amount. Amount must be greater than 0.");
        }
    }

    // Withdraw method to take money from the bank
    // check if balance is less than RMB 25 and deny withdrawal
    public void withdraw(double amount) {
        
        // first check if balance is at least 25.00
        // if not, deny withdrawal
        if (balance < 25.00) {
            System.out.println("Account is inactive. Minimum balance of RMB 25.00 required for transactions.");
            return;
        }
        
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Amount must be greater than 0.");
            return;
        }
        
        if (amount > balance) {
            System.out.println("Insufficient funds. Current balance: RMB " + balance);
            return;
        }
        
        balance -= amount;
        withdrawalsCount++;
        System.out.println("Withdrawal of RMB " + amount + " successful. New balance: RMB " + balance);
    }

    // Calculate monthly interest
    public void calcInterest() {
        double monthlyInterestRate = annualInterestRate / 12;
        double monthlyInterest = balance * monthlyInterestRate;
        balance += monthlyInterest;
        System.out.println("Monthly interest of RMB " + monthlyInterest + " added. New balance: RMB " + balance);
    }
    

    // Monthly processing
    public void monthlyProcess() {
        balance -= monthlyServiceCharges;
        calcInterest();
        depositsCount = 0;
        withdrawalsCount = 0;
        monthlyServiceCharges = 0;
        System.out.println("Monthly processing completed.");
    }

    // Display account details
    public void displayAccountDetails() {
        System.out.println("Balance: RMB " + balance);
        System.out.println("Deposits this month: " + depositsCount);
        System.out.println("Withdrawals this month: " + withdrawalsCount);
    }
    
    // Getter method for balance
    public double getBalance() {
        return balance;
    }
    
    // Getter method for annual interest rate
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }
    
    // Getter method for monthly interest
    public double getMonthlyInterest() {
        return balance * (annualInterestRate / 12);
    }
}
