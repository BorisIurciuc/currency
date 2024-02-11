import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static void makeNewExchange(ExchangeList exchangeList) {
        Scanner scanner = new Scanner(System.in);
        int indexSell;
        int indexBuy;
        double amountToChange;

        try {

            System.out.println("Select currency to sell  1:EUR, 2:GRP, 3:USD");
            indexSell = scanner.nextInt();
            if (indexSell < 1 || indexSell > 3) {
                throw new RuntimeException();
            }
            System.out.println("Select currency to buy 1:EUR, 2:GRP, 3:USD");
            indexBuy = scanner.nextInt();
            if (indexBuy < 1 || indexBuy > 3) {
                throw new RuntimeException();
            }
            System.out.println("Select amount");
            amountToChange = scanner.nextDouble();
            if (amountToChange <= 0) {
                throw new RuntimeException();
            }
        } catch (Exception e) {

            System.out.println("Unaccepted value!");
            return;
        }

        Exchange exchange = new Exchange();
        LocalDate date = LocalDate.now();

        exchangeList.add(new Exchange(indexSell, indexBuy, amountToChange, exchange.convert(), date));


    }


    private static void printExchangeHistory(ExchangeList exchangeList) {
        System.out.println("history " + exchangeList.getListExchange());
}
