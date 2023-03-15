package test.example.ticketsreader.util;

import test.example.ticketsreader.model.entities.Ticket;

import java.util.List;

public class Tickets {

    public long getPercentile(List<Ticket> tickets, int percentile) {
        final long[] array = tickets.stream()
                .mapToLong(this::getDuration)
                .sorted()
                .toArray();
        final int index = Math.ceilDiv(array.length * percentile, 100);
        return array[index - 1];
    }

    public double getAverageDuration(List<Ticket> tickets, String city1, String city2) {
        return tickets.stream()
                .filter(ticket -> ticketMatch(ticket, city1, city2))
                .mapToLong(this::getDuration)
                .average()
                .orElseThrow();
    }

    private boolean ticketMatch(Ticket ticket, String city1, String city2) {
        return ticket.getOriginName().equals(city1) && ticket.getDestinationName().equals(city2)
                || ticket.getOriginName().equals(city2) && ticket.getDestinationName().equals(city1);
    }

    private long getDuration(Ticket ticket) {
        return ticket.getArrivalDateTime() - ticket.getDepartureDateTime();
    }
}
