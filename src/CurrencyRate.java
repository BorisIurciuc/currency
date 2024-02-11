import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents a currency rate with its currency code and exchange rate.

public enum CurrencyRate {
  EUR("EUR"), // Euro
  USD("USD"), // US Dollar
  GBP("GBP"), // British Pound
  CHF("CHF"); // Swiss Franc

  private final String codCurrency; // The currency code
  private double rate;  // The exchange rate

  /**
   * Constructs a new CurrencyRate object with the given currency code.
   *
   * @param codCurrency codCurrency the currency code ("EUR" for Euro)
   */
  CurrencyRate(String codCurrency) {
    this.codCurrency = codCurrency;
  }

  /**
   * Retrieves the exchange rate of this currency.
   *
   * @return the exchange rate
   */

  public double getRate() {
    return rate;
  }
  // Retrieves the currency code of this currency.

  /**
   * Retrieves the currency code of this currency.
   *
   * @return the currency code
   */
  public String getCodCurrency() {
    return codCurrency;
  }

  /**
   * Sets the exchange rate of this currency.
   *
   * @param rate the exchange rate to set
   */
  public void setRate(double rate) {
    this.rate = rate;
  }

  // A nested class for importing currency rates from a file.
  public static class ImportCurrencyRate {

    /**
     * Imports currency rates from a file named "rate.txt".
     *
     * @throws FileNotFoundException if the file "rate.txt" is not found
     */

    public void importRate() throws FileNotFoundException {
      File file = new File("rate.txt");
      Scanner scanner = new Scanner(new FileReader(file));
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        String[] parts = line.split("\\s+");
        if (parts.length == 2) {
          String currencyCode = parts[0];
          double currencyRate = Double.parseDouble(parts[1]);
          for (CurrencyRate currency : values()) {
            if (currency.codCurrency.equals(currencyCode)) {
              currency.setRate(currencyRate);
              break;
            }
          }
        } else {
          System.err.println("Invalid line " + line);
        }
      }
    }

    /**
     * Prints the contents of the file "rate.txt".
     *
     * @throws FileNotFoundException if the file "rate.txt" is not found
     */
    public void printCurrencyRate() throws FileNotFoundException {
      List<String> fileContent = new ArrayList<>();
      File file = new File("rate.txt");

      Scanner scanner = new Scanner(new FileReader(file));

      while (scanner.hasNext()) {
        fileContent.add(scanner.nextLine());
      }
      fileContent.forEach(System.out::println);
    }
  }
}
