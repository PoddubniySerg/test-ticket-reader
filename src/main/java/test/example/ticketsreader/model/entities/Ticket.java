package test.example.ticketsreader.model.entities;

public interface Ticket {
    String getOriginName();

    String getDestinationName();

    long getDepartureDateTime();

    long getArrivalDateTime();
}
