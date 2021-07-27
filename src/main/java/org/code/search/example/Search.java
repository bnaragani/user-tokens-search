package org.code.search.example;

import java.util.List;
import java.util.Scanner;
import org.code.search.example.controller.SearchTickets;
import org.code.search.example.controller.SearchTicketsInterface;
import org.code.search.example.controller.SearchUsers;
import org.code.search.example.controller.SearchUsersInterface;
import org.code.search.example.model.Tickets;
import org.code.search.example.model.Users;

/**
 * @author bnaragani created on 24/07/2021
 */
public class Search {

    private static final String NO_RESULTS_FOUND = "No results found";
    private static Scanner sc ;
    public static void main(String[] args) {
        System.out.println("Welcome to Zendesk Search\n Select the search options:");
        showSearchMenu();
    }

    public static void showSearchMenu() {
        sc = new Scanner(System.in);
        System.out.println("---------------------------------------------------");
        System.out.println("* Press 1 to search Zendesk");
        System.out.println("* Press 2 to view a list of Searchable fields");
        System.out.println("* Type quit to exit");
        System.out.println("---------------------------------------------------");
        System.out.print("Select an option :");
        String input = sc.nextLine();
        switch (input) {
            case "1":
                showZendeskSearch();
                break;
            case "2":
                showSearchableFields();
                break;
            case "quit":
                System.out.println("Thanks for Searching!");
                break;
            default:
                System.out.println("Invalid input. Try again");
        }
    }

    private static void showZendeskSearch() {
        System.out.println("Select 1.Users or 2.Tickets");
        String input = sc.nextLine();
        if (input.equals("1")) {
            System.out.println("Searching Users:");
            searchUsers();
        } else if (input.equals("2")) {
            System.out.println("Searching Tickets:");
            searchTickets();
        } else {
            System.out.println("Invalid input, try again");
        }
    }

    private static void showSearchableFields() {
        System.out.println("---------------------------------------------------");
        System.out.println("Search Users with");
        System.out.println("_id\nname\ncreated_at\nverified");
        System.out.println("---------------------------------------------------");
        System.out.println("Search Tokens with");
        System.out.println("_id\ncreated_at\ntype\nsubject\nassignee_id\ntags");
        System.out.println("---------------------------------------------------");
        showSearchMenu();
    }

    private static void searchUsers() {
        SearchUsersInterface searchFiles = new SearchUsers();
        System.out.print("Enter the search term:");
        String searchTerm = sc.nextLine();
        System.out.print("Enter the search value:");
        String searchValue = sc.nextLine();
        System.out.println("Searching users "+ searchTerm + " for  with value " + searchValue);
        List<Users> users = searchFiles.searchUsersByGivenParam(searchTerm.trim(), searchValue.trim());
        printUserDetails(users);
    }


    private static void searchTickets() {
        SearchTicketsInterface searchFiles = new SearchTickets();
        System.out.print("Enter the search term:");
        String searchTerm = sc.nextLine();
        System.out.print("Enter the search value:");
        String searchValue = sc.nextLine();
        System.out.println("Searching tickets "+ searchTerm + " for  with value " + searchValue);
        List<Tickets> tickets = searchFiles.searchTicketsByGivenParam(searchTerm.trim(), searchValue.trim());
        printTicketsDetails(tickets);
    }

    private static void printUserDetails(List<Users> usersList) {
        if (usersList != null && !usersList.isEmpty()) {
            System.out.println("---------------------------------------------------");
            for (Users user : usersList) {
                System.out.println(user);
                System.out.println("---------------------------------------------------");
            }
        } else {
            System.out.println(NO_RESULTS_FOUND);
        }
    }

    private static void printTicketsDetails(List<Tickets> ticketsList) {
        if (ticketsList != null && !ticketsList.isEmpty()) {
            System.out.println("---------------------------------------------------");
            for (Tickets ticket : ticketsList) {
                System.out.println(ticket);
                System.out.println("---------------------------------------------------");
            }
        } else {
            System.out.println(NO_RESULTS_FOUND);
        }
    }

}
