package org.code.search.example.controller;

import java.util.List;
import org.code.search.example.model.Tickets;

public interface SearchTicketsInterface {

    List<Tickets> searchTicketsByGivenParam(String parameter, String value);

}
