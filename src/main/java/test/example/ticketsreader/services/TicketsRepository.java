package test.example.ticketsreader.services;

import test.example.ticketsreader.model.entities.Ticket;
import test.example.ticketsreader.model.params.GetTicketsParams;

import java.io.IOException;
import java.util.List;

public interface TicketsRepository {

    List<Ticket> getTickets(GetTicketsParams params) throws IOException;

    List<Ticket> getTickets();
}
