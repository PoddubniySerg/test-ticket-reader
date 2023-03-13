package test.example.ticketsreader.util;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import test.example.ticketsreader.model.entities.Ticket;

import java.util.List;

@Component
public class TicketParser {

    public List<Ticket> fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, TicketList.class)
                .getTickets()
                .stream()
                .map(ticket -> (Ticket) ticket)
                .toList();
    }

    @RequiredArgsConstructor
    private static class TicketList {

        @Getter
        @SerializedName("tickets")
        private final List<TicketImpl> tickets;
    }

    @RequiredArgsConstructor
    private static class TicketImpl implements Ticket {

        @SerializedName("origin")
        private final String origin;

        @SerializedName("origin_name")
        private final String originName;

        @SerializedName("destination")
        private final String destination;

        @SerializedName("destination_name")
        private final String destinationName;

        @SerializedName("departure_date")
        private final String departureDate;

        @SerializedName("departure_time")
        private final String departureTime;

        @SerializedName("arrival_date")
        private final String arrivalDate;

        @SerializedName("arrival_time")
        private final String arrivalTime;

        @SerializedName("carrier")
        private final String carrier;

        @SerializedName("stops")
        private final int stops;

        @SerializedName("price")
        private final long price;

        private transient Long departureDateTime;

        private transient Long arrivalDateTime;

        @Override
        public String getOriginName() {
            return originName;
        }

        @Override
        public String getDestinationName() {
            return destinationName;
        }

        @Override
        public long getDepartureDateTime() {
            if (departureDateTime == null) {
                departureDateTime = new DateTimeConverter().stringToLong(departureDate, departureTime);
            }
            return departureDateTime;
        }

        @Override
        public long getArrivalDateTime() {
            if (arrivalDateTime == null) {
                arrivalDateTime = new DateTimeConverter().stringToLong(arrivalDate, arrivalTime);
            }
            return arrivalDateTime;
        }
    }
}

