package org.code.search.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import org.code.search.example.model.Tickets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SearchTicketsTest {

    List<Tickets> tickets = new ArrayList<>();

    private void prepareTicketsTestData() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        Tickets tickets1 = new Tickets("1a227508", cal.getTime(), "problem", "A Problem in Heard and McDonald Islands", Long.valueOf(71),
                Arrays.asList("Alaska", "Maryland"));
        tickets.add(tickets1);
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -5);
        Tickets tickets2 = new Tickets("1a227509", cal.getTime(), "question", "A Problem in Turkey", Long.valueOf(71),
                Arrays.asList("Oklahoma", "Louisiana"));
        tickets.add(tickets2);
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -8);
        Tickets tickets3 = new Tickets("1a2243657", cal.getTime(), "problem", "A Catastrophe in Maldives", Long.valueOf(56),
                Arrays.asList("Massachusetts", "New York"));
        tickets.add(tickets3);
        cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -2);
        Tickets tickets4 = new Tickets("1a2243658", cal.getTime(), "task", "", Long.valueOf(71),
                Arrays.asList("North Dakota", "California"));
        tickets.add(tickets4);
        cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -7);
        Tickets tickets5 = new Tickets("1a2243685", cal.getTime(), "question", "A Drama in Uruguay", Long.valueOf(23),
                Arrays.asList("Maryland", "North Dakota"));
        tickets.add(tickets5);
        cal = Calendar.getInstance();
        cal.set(2021, 02, 21, 8, 30, 40);
        cal.set(Calendar.MILLISECOND, 0);
        Tickets tickets6 = new Tickets("1a2243687", cal.getTime(), "problem", "A Problem in London", Long.valueOf(67),
                Arrays.asList("New Hampshire", "Iowa "));
        tickets.add(tickets6);
        cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -5);
        Tickets tickets7 = new Tickets("2217c7dc", cal.getTime(), "question", "A Drama in Gujarat", Long.valueOf(90),
                Arrays.asList("Delaware", "Maryland "));
        tickets.add(tickets7);
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -2);
        Tickets tickets8 = new Tickets("2217c7d7", cal.getTime(), "task", "A Catastrophe in Lesotho", Long.valueOf(23),
                Arrays.asList("Hawaii", "Arizona"));
        tickets.add(tickets8);
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -10);
        Tickets tickets9 = new Tickets("2217c7ds", cal.getTime(), "question", "A Catastrophe in Lesotho", Long.valueOf(89),
                Arrays.asList("Alaska", "California"));
        tickets.add(tickets9);
        cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -2);
        Tickets tickets10 = new Tickets("2217c12", cal.getTime(), "problem", "A Problem in Indian ocean", Long.valueOf(23),
                Arrays.asList("Maryland", "North Dakota "));
        tickets.add(tickets10);
        cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -6);
        Tickets tickets11 = new Tickets("22174s22", cal.getTime(), "question", "A Drama in Perth", Long.valueOf(81),
                Arrays.asList(" New Hampshire", "Maryland"));
        tickets.add(tickets11);

    }

    //Invalid search Term
    @Test
    void searchTicketsByGivenParam() {
        SearchTickets searchTickets = new SearchTickets();
        List<Tickets> matchingTickets = searchTickets.searchTicketsByGivenParam("id", "235345");
        Assertions.assertNull(matchingTickets);
    }

    //Test ID search
    @Test
    void searchTicketsByGivenParamId() {
        prepareTicketsTestData();
        SearchTickets searchTickets = new SearchTickets();
        searchTickets.setTickets(tickets);
        List<Tickets> matchingTickets = searchTickets.searchTicketsByGivenParam("_id", "235345");
        Assertions.assertNotNull(matchingTickets);
        Assertions.assertEquals(0, matchingTickets.size());

        matchingTickets = searchTickets.searchTicketsByGivenParam("_id", "2217c7ds");
        Assertions.assertNotNull(matchingTickets);
        Assertions.assertEquals(1, matchingTickets.size());
    }

    //Test Type search
    @Test
    void searchTicketsByGivenParamType() {
        prepareTicketsTestData();
        SearchTickets searchTickets = new SearchTickets();
        searchTickets.setTickets(tickets);
        List<Tickets> matchingTickets = searchTickets.searchTicketsByGivenParam("type", "prob");
        Assertions.assertNotNull(matchingTickets);
        Assertions.assertEquals(0, matchingTickets.size());

        matchingTickets = searchTickets.searchTicketsByGivenParam("type", "problem");
        Assertions.assertNotNull(matchingTickets);
        Assertions.assertEquals(4, matchingTickets.size());
    }

    //Test Subject search
    @Test
    void searchTicketsByGivenParamSubject() {
        prepareTicketsTestData();
        SearchTickets searchTickets = new SearchTickets();
        searchTickets.setTickets(tickets);
        List<Tickets> matchingTickets = searchTickets.searchTicketsByGivenParam("subject", "A Catastrophe");
        Assertions.assertNotNull(matchingTickets);
        Assertions.assertEquals(0, matchingTickets.size());

        matchingTickets = searchTickets.searchTicketsByGivenParam("subject", "A Catastrophe in Maldives");
        Assertions.assertNotNull(matchingTickets);
        Assertions.assertEquals(1, matchingTickets.size());
    }

    //Test Tags search
    @Test
    void searchTicketsByGivenParamTags() {
        prepareTicketsTestData();
        SearchTickets searchTickets = new SearchTickets();
        searchTickets.setTickets(tickets);
        List<Tickets> matchingTickets = searchTickets.searchTicketsByGivenParam("tags", "California");
        Assertions.assertNotNull(matchingTickets);
        Assertions.assertEquals(2, matchingTickets.size());

        matchingTickets = searchTickets.searchTicketsByGivenParam("tags", "Perth");
        Assertions.assertNotNull(matchingTickets);
        Assertions.assertEquals(0, matchingTickets.size());
    }

    //Test AssigneeId search
    @Test
    void searchTicketsByGivenParamAssigneeId() {
        prepareTicketsTestData();
        SearchTickets searchTickets = new SearchTickets();
        searchTickets.setTickets(tickets);
        List<Tickets> matchingTickets = searchTickets.searchTicketsByGivenParam("assignee_id", "23");
        Assertions.assertNotNull(matchingTickets);
        Assertions.assertEquals(3, matchingTickets.size());

        matchingTickets = searchTickets.searchTicketsByGivenParam("assignee_id", "88");
        Assertions.assertNotNull(matchingTickets);
        Assertions.assertEquals(0, matchingTickets.size());
    }

    //Test createdAt search
    @Test
    void searchTicketsByGivenParamCreatedAt() {
        prepareTicketsTestData();
        SearchTickets searchTickets = new SearchTickets();
        searchTickets.setTickets(tickets);
        List<Tickets> matchingTickets = searchTickets.searchTicketsByGivenParam("created_at", "2021-03-21T08:30:40+11:00");
        Assertions.assertNotNull(matchingTickets);
        Assertions.assertEquals(1, matchingTickets.size());

        matchingTickets = searchTickets.searchTicketsByGivenParam("created_at", "2021-03-28T08:30:40+11:00");
        Assertions.assertNotNull(matchingTickets);
        Assertions.assertEquals(0, matchingTickets.size());
    }

}