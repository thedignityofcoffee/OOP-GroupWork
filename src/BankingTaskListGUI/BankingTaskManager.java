/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.demo.bankingtasklistgui;

/**
 *
 * @author ashongtical
 * @author Bertrand
 */
//use HashMap for bank_accounts
//use TaskManager to manage tasks

//import packages
import java.util.HashMap;
import java.util.ArrayList;

public class BankingTaskManager {
    
    // use HashMap for new accounts, task manager should TaskManager type
    private HashMap<String, BankAccount> accounts;
    private TaskManager taskManager;    

    //create new HashMap object for accounts and new object for Task Manager
    public BankingTaskManager() {
        accounts = new HashMap<>();
        taskManager = new TaskManager();        
    }

    // Create (add) a new bank account 
    public void createAccount(String accountNumber, double balance, double annualInterestRate) {
        BankAccount newAccount = new BankAccount(balance, annualInterestRate);
                    accounts.put(accountNumber, newAccount); 
    }

    // Deposit money
    // check if account has an account number, if yes get and add balance to the account number
    public void deposit(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            BankAccount account = accounts.get(accountNumber);
            account.deposit(amount);
            taskManager.addTask("Deposit RMB " + amount + " to Account: " + accountNumber);
        } else {
            System.out.println("Account not found: " + accountNumber);
        }        
    }

    // Withdraw money
    // check if account has an account number, if yes get and subtract the amount from the balance of the account number
    public void withdraw(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            BankAccount account = accounts.get(accountNumber);
            // Save previous balance to detect if the transaction was successful
            double previousBalance = account.getBalance();
            account.withdraw(amount);
            // Check if balance changed to determine if the transaction was successful
            if (account.getBalance() < previousBalance) {
                taskManager.addTask("Withdrew RMB " + amount + " from account: " + accountNumber);
            } else {
                taskManager.addTask("Failed withdrawal attempt of RMB " + amount + " from account: " + accountNumber);
            }
        } else {
            System.out.println("Account not found: " + accountNumber);
        }        
    }

    // View account details
    // check if account has an account number, if yes get and print the details
    public void viewAccountDetails(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            BankAccount account = accounts.get(accountNumber);
            System.out.println("Account Details for " + accountNumber + ":");
            System.out.println("Balance: RMB" + account.getBalance());
            System.out.println("Annual Interest Rate: " + account.getAnnualInterestRate() + "%");
            System.out.println("Monthly Interest: RMB" + account.getMonthlyInterest());
        } else {
            System.out.println("Account not found: " + accountNumber);
        }
    }
    
    // Perform monthly process for an account
    public void monthlyProcess(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            BankAccount account = accounts.get(accountNumber);
            account.monthlyProcess();
            taskManager.addTask("Performed monthly process for account: " + accountNumber);
        } else {
            System.out.println("Account not found: " + accountNumber);
        }        
    }

    // Manage tasks
    public void viewTasks() {
        taskManager.displayTasks();
    }
    
    // New method to add a task
    public void addTask(String task) {
        taskManager.addTask(task);
    }

    // New method to remove a task
    public void removeTask(String priority, int index) {
        taskManager.removeTask(priority, index);
    }

    // New method to change task priority
    public void changePriority(String priority, int index) {
        taskManager.changePriority(priority, index);
    }

    // New method to promote a task
    public void promoteTask(int index) {
        taskManager.promoteTask(index);
    }

    // New method to get high-priority tasks
    public ArrayList<String> getHighPriorityTasks() {
        return taskManager.getHighPriorityTasks();
    }

    // New method to get low-priority tasks
    public ArrayList<String> getLowPriorityTasks() {
        return taskManager.getLowPriorityTasks();
    }
    }
