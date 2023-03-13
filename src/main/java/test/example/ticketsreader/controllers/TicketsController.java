package test.example.ticketsreader.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import test.example.ticketsreader.model.requests.PercentilePosition;
import test.example.ticketsreader.model.requests.TicketsSource;
import test.example.ticketsreader.model.responses.AverageFlightTime;
import test.example.ticketsreader.model.responses.Percentile;
import test.example.ticketsreader.services.TicketsService;

import java.io.IOException;
import java.time.Duration;

@Controller
@RequiredArgsConstructor
public class TicketsController {

    private final TicketsService ticketsService;

    public AverageFlightTime getAverageFlightTime(TicketsSource ticketsSource) {
        try {
            final Duration duration =
                    ticketsService.getAverageFlightTime(ticketsSource.path(), ticketsSource.city1(), ticketsSource.city2());
            return new AverageFlightTime(duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Percentile getPercentile(PercentilePosition params) {
        try {
            final Duration duration = ticketsService.getPercentile(params.position());
            return new Percentile(duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
