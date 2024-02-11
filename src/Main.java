import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  // Class ExchangeList to manage List of objects of transactions
  static ExchangeList exchangeList = new ExchangeList();

  // Inner class ImportCurrencyRate classes for import rates using text file "rate.txt"
  static CurrencyRate.ImportCurrencyRate importCurrencyRate = new CurrencyRate.ImportCurrencyRate();

  /**
   * Provides the console interface for carrying out currency exchange operations and managing
   * currency rates.
   *
   * @throws IOException if an I/O error occurs while interacting with the console or accessing
   *                     files
   */
  public static void makeExchange() throws IOException {
    Scanner scanner = new Scanner(System.in);
    int k;

    // Display the main console interface
    System.out.println("Menu :");
    System.out.println("1: View currency rate");
    System.out.println("2: Carry out currency exchange");
    System.out.println("3: View current exchange history");
    System.out.println("4: View full transaction history");
    System.out.println("5: test");

    // Handle user input
    do {
      System.out.print("Enter your choice - ");
      k = scanner.nextInt();
      if (k < 0 || k > 6) {
        throw new RuntimeException("\nThis number is out menu");
      }
      switch (k) {
        // Exit condition
        case 0 -> System.out.println("Quit from program");
        // Call review of current exchange rates from a file named "rate.txt"
        case 1 -> {
          System.out.println("View currency rate ");
          importCurrencyRate.printCurrencyRate();
        }
        // Transaction request
        case 2 -> {
          System.out.print("Enter currency to sell - ");
          String cySell = scanner.next(); // Enter string cod of currency
          System.out.print("Enter currency to buy - ");
          String cyBuy = scanner.next();  // Enter string cod of currency
          scanner.nextLine();
          System.out.print("Enter amount to sell - ");
          double amountSell = scanner.nextDouble(); //user input of a rational non-negative number
          try {
            Exchange exchange = new Exchange(cySell, cyBuy, amountSell);
            exchangeList.add(exchange);    // Adds an exchange operation to the list.
            System.out.println("Your transaction - " + exchange);
            exchangeList.writeFileListHistory(); // View current exchange
          } catch (IllegalArgumentException ex) {
            System.out.println("Invalid input: " + ex.getMessage());
          }
        }
        // View current exchange history
        case 3 -> System.out.println("List history " + exchangeList.getListExchange());
        // View full transaction history from the "list.txt" file
        case 4 -> readListTransaction();
        // Test case
        case 5 -> {
          Exchange exchange2 = new Exchange("EUR", "USD", 1);
          exchangeList.add(exchange2);
          System.out.println(exchange2);
          exchangeList.writeFileListHistory();
        }
      }
      scanner.nextLine();
    } while (k != 0);
    scanner.close();
  }

  public static void main(String[] args) throws IOException {
    // Import currency rates from the "rate.txt" file
    importCurrencyRate.importRate();
    try {
      makeExchange();  // Start the currency exchange interface
    } catch (RuntimeException ex) {
      System.out.println(ex.getMessage());
    }
  }

  /**
   * Reads and prints the transaction history from the "list.txt" file. In menu, method
   * makeExchange(), case 4
   *
   * @throws FileNotFoundException if the file "list.txt" is not found
   */
  public static void readListTransaction() throws FileNotFoundException {
    List<String> listTransaction = new ArrayList<>();
    File file = new File("list.txt");
    Scanner scanner = new Scanner(new FileReader(file));

    // Read each line of the file and add it to the list of transactions
    while (scanner.hasNext()) {
      listTransaction.add(scanner.nextLine());
    }
    // Print each transaction in the list
    listTransaction.forEach(System.out::println);
  }
}
