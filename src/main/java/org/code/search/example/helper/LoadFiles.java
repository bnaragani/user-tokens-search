package org.code.search.example.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.code.search.example.model.Tickets;
import org.code.search.example.model.Users;

/**
 * @author bnaragani created on 24/07/2021
 */
public class LoadFiles {

    private static List<Users> users;

    private static final String USERS_FILE_NAME = "/users.json";
    private static final String TICKETS_FILE_NAME = "/tickets.json";

    private static List<Tickets> tickets;


    public static List<Users> loadUsers() {
        if (users == null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                InputStream userFileStream = LoadFiles.class.getResourceAsStream(USERS_FILE_NAME);

                //JSON file to Java object
                users = mapper.readValue(userFileStream, new TypeReference<List<Users>>() {
                });
                userFileStream.close();
                populateUsersWithTickets();
            } catch (IOException ex) {
                System.out.println("IO Exception while reading the file:");
            }
        }
        return users;
    }

    public static List<Tickets> loadTickets() {
        if (tickets == null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                InputStream ticketsFileStream = LoadFiles.class.getResourceAsStream(TICKETS_FILE_NAME);
                tickets = mapper.readValue(ticketsFileStream, new TypeReference<List<Tickets>>() {
                });
                ticketsFileStream.close();
                populateTicketsWithUsers();
            } catch (IOException ex) {
                System.out.println("IO Exception while reading the file");
            }
        }
        return tickets;
    }

    private static List<Users> populateUsersWithTickets() {
        loadTickets();
        Map<Long, List<Tickets>> userTickets = tickets.stream().filter(tickets1 -> tickets1.getAssigneeId() != null)
                .collect(Collectors.groupingBy(Tickets::getAssigneeId));
        for (Users user : users) {
            user.setTickets(userTickets.get(user.getId()));
        }
        return users;
    }

    private static List<Tickets> populateTicketsWithUsers() {
        loadUsers();
        Map<Long, Users> userDetails = users.stream().collect(Collectors.toMap(Users::getId, users1 -> users1));
        for (Tickets tickets : tickets) {
            if (tickets.getAssigneeId() != null) {
                tickets.setAssigneeDetails(userDetails.get(tickets.getAssigneeId()));
            }
        }
        return tickets;
    }

}
