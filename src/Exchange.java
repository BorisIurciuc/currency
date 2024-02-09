import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Exchange class Represents an exchange operation between two currencies.
 */

public class Exchange implements Convertable {
  private String cyForSell; // The currency code for selling
  private String cyToBuy; // The currency code for buying
  private double amountToChange; // The amount to exchange
  private double amountResult; // The resulting amount after conversion
  private final LocalDateTime current;  // The current date and time of the exchange

  /**
   * Constructs an Exchange object with the specified parameters.
   *
   * @param cyForSell      the currency code for selling
   * @param cyToBuy        the currency code for buying
   * @param amountToChange the amount to exchange
   */
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
  /**
   * Gets the amount from interface to exchange.
   *
   * @return the amount to exchange
   */
  public double getAmountToChange() {
    return amountToChange;
  }

  /**
   * Gets the index of the currency for selling in the CurrencyRate enum.
   *
   */
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

  /**
   * Gets the index of the currency for buying in the CurrencyRate enum.
   *
   */
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

  /**
   * Converts the amount using the exchange rates of the specified currencies.
   *
   * @return the resulting amount after conversion
   */
  @Override
  public double convert() {
   return amountToChange *
        (CurrencyRate.values()[getIndexSell()].getRate()
            * CurrencyRate.values()[getIndexBuy()].getRate());
  }

  DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

  /**
   * Returns a string representation of the Exchange object.
   *
   * @return a string representation of the Exchange object
   */
  @Override
  public String toString() {
    String amountResultStr = String.format("%.2f", amountResult);
    return "\nExchange(data " + current.format(myFormatObj) + " -- sell: " + amountToChange + " "
        + cyForSell + " - buy: " + amountResultStr + " " + cyToBuy + ')';
  }
}
