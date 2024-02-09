import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public enum CurrencyRate {
  EUR("EUR",1.0),
  USD("USD",1.0),
  GBP("GBP",1.0),
  CHF("CHF", 1.0);

  private final String codCurrency;
  private double rate;

  CurrencyRate(String codCurrency, double nominal) {
    this.codCurrency = codCurrency;
  }
  public double getRate() {
    return rate;
  }

  public String getCodCurrency() {
    return codCurrency;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }
  public static class ImportCurrencyRate {
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
