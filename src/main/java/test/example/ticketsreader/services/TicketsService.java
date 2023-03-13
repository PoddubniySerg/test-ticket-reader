package test.example.ticketsreader.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.example.ticketsreader.model.entities.Ticket;
import test.example.ticketsreader.model.params.GetTicketsParams;
import test.example.ticketsreader.util.Tickets;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketsService {

    private final TicketsRepository ticketsRepository;

    public Duration getAverageFlightTime(String path, String city1, String city2) throws IOException {
        final GetTicketsParams params = new GetTicketsParams(path);
        final List<Ticket> tickets = ticketsRepository.getTickets(params);
        final double duration = new Tickets().getAverageDuration(tickets, city1, city2);
        return Duration.ofMillis(Math.round(duration));
    }

    public Duration getPercentile(int percentile) {
        final List<Ticket> tickets = ticketsRepository.getTickets();
        final long percentileValue = new Tickets().getPercentile(tickets, percentile);
        return Duration.ofMillis(percentileValue);
    }
}
