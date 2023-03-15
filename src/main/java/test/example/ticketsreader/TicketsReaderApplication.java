package test.example.ticketsreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import test.example.ticketsreader.controllers.TicketsController;
import test.example.ticketsreader.model.requests.PercentilePosition;
import test.example.ticketsreader.model.requests.TicketsSource;
import test.example.ticketsreader.model.responses.AverageFlightTime;
import test.example.ticketsreader.model.responses.Percentile;

@SpringBootApplication
public class TicketsReaderApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(TicketsReaderApplication.class, args);
        final TicketsController controller = context.getBean(TicketsController.class);
        final boolean argsExist = args != null && args.length > 0;
        final String sourceFilePath = argsExist ? args[0] : "./assets/tickets.json";
        final String city1 = argsExist && args.length >= 2 ? args[1] : "Владивосток";
        final String city2 = argsExist && args.length >= 3 ? args[2] : "Тель-Авив";
        final TicketsSource ticketsSource = new TicketsSource(sourceFilePath, city1, city2);
        final AverageFlightTime averageFlightTime = controller.getAverageFlightTime(ticketsSource);
        final PercentilePosition percentilePosition = new PercentilePosition(90);
        final Percentile percentile = controller.getPercentile(percentilePosition);
        System.out.println(formatAverage(averageFlightTime, city1, city2));
        System.out.println(formatPercentile(percentile, city1, city2));
    }

    private static String formatAverage(AverageFlightTime averageFlightTime, String city1, String city2) {
        return String.format(
                "\nCреднее время полета между городами %s и %s %d:%02d:%02d",
                city1,
                city2,
                averageFlightTime.hours(),
                averageFlightTime.minutes(),
                averageFlightTime.seconds()
        );
    }

    private static String formatPercentile(Percentile percentile, String city1, String city2) {
        return String.format(
                "90-й процентиль времени полета между городами %s и %s %d:%02d:%02d\n",
                city1,
                city2,
                percentile.hours(),
                percentile.minutes(),
                percentile.seconds()
        );
    }
}
