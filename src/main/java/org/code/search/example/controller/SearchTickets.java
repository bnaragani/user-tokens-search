package org.code.search.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.code.search.example.helper.LoadFiles;
import org.code.search.example.model.Tickets;

/**
 * @author bnaragani created on 26/07/2021
 */
public class SearchTickets implements SearchTicketsInterface {


    private List<Tickets> tickets;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    @Override
    public List<Tickets> searchTicketsByGivenParam(String parameter, String value) {
        List<Tickets> matchingTickets = null;
        //Load Tickets
        getTickets();
        switch (parameter) {
            case "_id":
                matchingTickets = searchTicketsById(value);
                break;
            case "type":
                matchingTickets = searchTicketsByType(value);
                break;
            case "created_at":
                try {
                    Date inputDate = simpleDateFormat.parse(value);
                    matchingTickets = searchTicketsByCreatedAt(inputDate);
                } catch (ParseException e) {
                    System.out.println("Invalid input.Unable to parse the given input to Date. Please try again.");
                }
                break;
            case "subject":
                matchingTickets = searchTicketsBySubject(value);
                break;
            case "assignee_id":
                Long id = Long.valueOf(0);
                try {
                    if (!value.isEmpty()) {
                        id = Long.valueOf(value);
                    }
                    matchingTickets = searchTicketsByAssigneeId(id);
                } catch (NumberFormatException ex) {
                    System.out.println("Assignee Id value should be a number.");
                }
                break;
            case "tags":
                matchingTickets = searchTicketsByTags(value);
                break;
            default:
                System.out.println("Invalid search term.");
        }

        return matchingTickets;
    }

    private List<Tickets> searchTicketsById(String id) {
        List<Tickets> matchingTickets = tickets.stream().filter(t -> t.getId().equals(id)).collect(Collectors.toList());
        return matchingTickets;
    }

    private List<Tickets> searchTicketsByType(String type) {
        List<Tickets> matchingTickets = tickets.stream().filter(t -> t.getType() != null && t.getType().equals(type))
                .collect(Collectors.toList());
        return matchingTickets;
    }

    private List<Tickets> searchTicketsByCreatedAt(Date date) {
        List<Tickets> matchingTickets = tickets.stream().filter(t -> t.getCreatedAt().compareTo(date) == 0).collect(Collectors.toList());
        return matchingTickets;
    }

    private List<Tickets> searchTicketsBySubject(String subject) {
        List<Tickets> matchingTickets = tickets.stream().filter(t -> t.getSubject().equals(subject)).collect(Collectors.toList());
        return matchingTickets;
    }

    private List<Tickets> searchTicketsByAssigneeId(Long id) {
        List<Tickets> matchingTickets;
        if (id == 0) {
           matchingTickets  = tickets.stream().filter(t -> t.getAssigneeId() == null).collect(Collectors.toList());
        } else {
            matchingTickets = tickets.stream().filter(t -> t.getAssigneeId() == id).collect(Collectors.toList());
        }
        return matchingTickets;
    }

    private List<Tickets> searchTicketsByTags(String tags) {
        List<Tickets> matchingTickets = tickets.stream().filter(t -> t.getTags().contains(tags)).collect(Collectors.toList());
        return matchingTickets;
    }

    private List<Tickets> getTickets() {
        if (tickets == null) {
            tickets = LoadFiles.loadTickets();
        }
        return tickets;
    }

    public void setTickets(List<Tickets> tickets) {
        this.tickets = tickets;
    }
}
