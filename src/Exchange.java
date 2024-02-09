import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Exchange implements Convertable {
  private String cyForSell;
  private String cyToBuy;
  private double amountToChange;
  private double amountResult;
  private LocalDateTime current;

  public Exchange(String cyForSell, String cyToBuy, double amountToChange) {
    try {
      // Check if cyForSell and cyToBuy are valid currency codes
      boolean validSellCurrency = false;
      boolean validBuyCurrency = false;
      for (CurrencyRate currency : CurrencyRate.values()) {
        if (cyForSell.equals(currency.getCodCurrency())) {
          validSellCurrency = true;
        }
        if (cyToBuy.equals(currency.getCodCurrency())) {
          validBuyCurrency = true;
        }
      }

      if (!validSellCurrency) {
        throw new IllegalArgumentException("Invalid currency code for selling: " + cyForSell);
      }

      if (!validBuyCurrency) {
        throw new IllegalArgumentException("Invalid currency code for buying: " + cyToBuy);
      }

      if (amountToChange < 0) {
        throw new IllegalArgumentException("Value cannot be negative");
      }

      this.cyForSell = cyForSell;
      this.cyToBuy = cyToBuy;
      this.amountToChange = amountToChange;
      this.amountResult = convert();
    } catch (IllegalArgumentException ex) {
      System.err.println("Exception caught: " + ex.getMessage());
    }

    this.current = LocalDateTime.now();
  }

  public double getAmountToChange() {
    return amountToChange;
  }

  int indexSell;
  public int getIndexSell() {
    switch (cyForSell) {
      case "EUR" -> indexSell = 0;
      case "USD" -> indexSell = 1;
      case "GBP" -> indexSell = 2;
      case "CHF" -> indexSell = 3;
    }
    return indexSell;
  }

  int indexBuy;
  public int getIndexBuy() {
    switch (cyToBuy) {
      case "EUR" -> indexBuy = 0;
      case "USD" -> indexBuy = 1;
      case "GBP" -> indexBuy = 2;
      case "CHF" -> indexBuy = 3;
    }
    return indexBuy;
  }

  @Override
  public double convert() {
   return amountToChange *
        (CurrencyRate.values()[getIndexSell()].getRate()
            * CurrencyRate.values()[getIndexBuy()].getRate());
  }

  DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

  @Override
  public String toString() {
    String amountResultStr = String.format("%.2f", amountResult);
    return "\nExchange(data " + current.format(myFormatObj) + " -- sell: " + amountToChange + " "
        + cyForSell + " - buy: " + amountResultStr + " " + cyToBuy + ')';
  }
}
