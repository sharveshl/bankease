package com.bankease;

import com.bankease.model.User;
import com.bankease.model.Account;
import com.bankease.model.Transaction;

import com.bankease.service.UserService;
import com.bankease.service.AccountService;
import com.bankease.dao.AccountDAO;
import com.bankease.dao.TransactionDAO;

import java.util.List;
import java.util.Scanner;

public class App {

    private static UserService userService = new UserService();
    private static AccountService accountService = new AccountService();
    private static AccountDAO accountDAO = new AccountDAO();
    private static TransactionDAO transactionDAO = new TransactionDAO();

    private static User loggedInUser = null;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            if (loggedInUser == null) {
                System.out.println("\n========== BANKEASE ==========");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Select: ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        register(sc);
                        break;
                    case 2:
                        login(sc);
                        break;
                    case 3:
                        System.out.println("Thank you for using BankEase!");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }

            } else {
                homeMenu(sc);
            }
        }
    }

    // ----------------------------------------------------
    // REGISTER
    // ----------------------------------------------------
    private static void register(Scanner sc) {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String pwd = sc.nextLine();

        userService.register(name, email, pwd);
    }

    // ----------------------------------------------------
    // LOGIN
    // ----------------------------------------------------
    private static void login(Scanner sc) {
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String pwd = sc.nextLine();

        loggedInUser = userService.login(email, pwd);
    }

    // ----------------------------------------------------
    // HOME MENU (AFTER LOGIN)
    // ----------------------------------------------------
    private static void homeMenu(Scanner sc) {

        System.out.println("\n========== WELCOME " + loggedInUser.getName().toUpperCase() + " ==========");
        System.out.println("1. Create Account");
        System.out.println("2. View My Accounts");
        System.out.println("3. Deposit Money");
        System.out.println("4. Withdraw Money");
        System.out.println("5. Transfer Money");
        System.out.println("6. View Transactions");
        System.out.println("7. Logout");
        System.out.print("Select: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

            case 1:
                createAccount(sc);
                break;

            case 2:
                viewAccounts();
                break;

            case 3:
                deposit(sc);
                break;

            case 4:
                withdraw(sc);
                break;

            case 5:
                transfer(sc);
                break;

            case 6:
                viewTransactions(sc);
                break;

            case 7:
                loggedInUser = null;
                System.out.println("Logged out successfully!");
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    // ----------------------------------------------------
    // CREATE ACCOUNT
    // ----------------------------------------------------
    private static void createAccount(Scanner sc) {
        System.out.println("Enter Account Type (SAVINGS/CURRENT): ");
        String type = sc.nextLine().toUpperCase();

        System.out.print("Initial Deposit: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        userService.createAccount(loggedInUser.getUser_id(), type, amount);
    }

    // ----------------------------------------------------
    // VIEW ACCOUNTS
    // ----------------------------------------------------
    private static void viewAccounts() {
        List<Account> accounts = accountDAO.getAccountsByUser(loggedInUser.getUser_id());

        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        System.out.println("\n--- Your Accounts ---");
        for (Account a : accounts) {
            System.out.println("ID: " + a.getAccount_id() +
                    " | Type: " + a.getAccount_type() +
                    " | Number: " + a.getAccount_number() +
                    " | Balance: " + a.getBalance());
        }
    }

    // ----------------------------------------------------
    // DEPOSIT
    // ----------------------------------------------------
    private static void deposit(Scanner sc) {

        viewAccounts();

        System.out.print("Enter Account ID: ");
        int accId = sc.nextInt();

        System.out.print("Amount to Deposit: ");
        double amt = sc.nextDouble();

        accountService.deposit(accId, amt);
    }

    // ----------------------------------------------------
    // WITHDRAW
    // ----------------------------------------------------
    private static void withdraw(Scanner sc) {

        viewAccounts();

        System.out.print("Enter Account ID: ");
        int accId = sc.nextInt();

        System.out.print("Amount to Withdraw: ");
        double amt = sc.nextDouble();

        accountService.withdraw(accId, amt);
    }

    // ----------------------------------------------------
    // TRANSFER
    // ----------------------------------------------------
    private static void transfer(Scanner sc) {

        viewAccounts();

        System.out.print("From Account ID: ");
        int fromAcc = sc.nextInt();

        System.out.print("To Account ID: ");
        int toAcc = sc.nextInt();

        System.out.print("Amount: ");
        double amt = sc.nextDouble();

        accountService.transfer(fromAcc, toAcc, amt);
    }

    // ----------------------------------------------------
    // VIEW TRANSACTIONS
    // ----------------------------------------------------
    private static void viewTransactions(Scanner sc) {

        viewAccounts();

        System.out.print("Enter Account ID: ");
        int accId = sc.nextInt();

        List<Transaction> txs = transactionDAO.getTransactionsByAccount(accId);

        if (txs.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("\n--- Transaction History ---");
        for (Transaction t : txs) {
            System.out.println(
                    "ID: " + t.getTransaction_id() +
                    " | Type: " + t.getType() +
                    " | Amount: " + t.getAmount() +
                    " | Balance After: " + t.getBalance_after() +
                    " | Desc: " + t.getDescription()
            );
        }
    }
}
