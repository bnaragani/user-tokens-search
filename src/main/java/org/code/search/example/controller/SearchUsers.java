package org.code.search.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.code.search.example.helper.LoadFiles;
import org.code.search.example.model.Users;

/**
 * @author bnaragani created on 25/07/2021
 */
public class SearchUsers implements SearchUsersInterface {

    private List<Users> users;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    public List<Users> searchUsersByGivenParam(String parameter, String value) {
        List<Users> matchingUsers = null;
        //Load users
        getUsers();
        switch (parameter) {
            case "_id":
                try {
                    if (!value.isEmpty()) {
                        matchingUsers = searchUsersById(Long.valueOf(value));
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Id value should be a number.");
                }
                break;
            case "name":
                matchingUsers = searchUsersByName(value);
                break;
            case "created_at":
                try {
                    Date inputDate = simpleDateFormat.parse(value);
                    matchingUsers = searchUsersByCreatedAt(inputDate);
                } catch (ParseException e) {
                    System.out.println("Invalid Date input.Unable to parse the given input to Date. Please try again.");
                }
                break;
            case "verified":
                matchingUsers = searchUsersByVerified(Boolean.parseBoolean(value));
                break;
            default:
                System.out.println("Invalid search term.");
        }

        return matchingUsers;
    }


    private List<Users> searchUsersById(Long id) {
        List<Users> matchingUsers = users.stream().filter(u -> u.getId() == id).collect(Collectors.toList());
        return matchingUsers;
    }

    private List<Users> searchUsersByName(String name) {
        List<Users> matchingUsers = users.stream().filter(u -> u.getName().equals(name)).collect(Collectors.toList());
        return matchingUsers;
    }


    private List<Users> searchUsersByCreatedAt(Date date) {
        List<Users> matchingUsers = users.stream().filter(u -> u.getCreatedAt().compareTo(date) == 0).collect(Collectors.toList());
        return matchingUsers;
    }

    private List<Users> searchUsersByVerified(boolean verified) {
        List<Users> matchingUsers = users.stream().filter(u -> u.isVerified() == verified).collect(Collectors.toList());
        return matchingUsers;

    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public List<Users> getUsers() {
        if (users == null) {
            users = LoadFiles.loadUsers();
        }
        return users;
    }


}
