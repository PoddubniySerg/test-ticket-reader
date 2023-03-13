package test.example.ticketsreader.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import test.example.ticketsreader.model.entities.Ticket;
import test.example.ticketsreader.model.params.GetTicketsParams;
import test.example.ticketsreader.services.TicketsRepository;
import test.example.ticketsreader.util.FileSource;
import test.example.ticketsreader.util.TicketParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TicketsRepositoryImpl implements TicketsRepository {

    private final FileSource fileSource;
    private final TicketParser ticketParser;
    private final List<Ticket> tickets = new ArrayList<>();

    @Override
    public List<Ticket> getTickets(GetTicketsParams params) throws IOException {
        final String json = fileSource.getContent(params.path());
        if (tickets.isEmpty()) {
            tickets.addAll(ticketParser.fromJson(json));
        }
        return tickets;
    }

    @Override
    public List<Ticket> getTickets() {
        return tickets;
    }
}
