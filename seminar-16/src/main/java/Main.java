import di.Container;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        try (Container container = new Container()) {
            container.rateRepository().createTable();
            int saved = container.rateService().refreshAndStore(List.of("RUB", "EUR"));
            System.out.println("Saved " + saved + " rates");
            container.rateRepository().findAll().forEach(System.out::println);
        }
    }
}
