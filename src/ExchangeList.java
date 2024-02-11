import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * The ExchangeList class represents a list of exchange operations.
 */

public class ExchangeList {

  private final List<Exchange> list;  // List to store exchange operations

  // Constructs an ExchangeList object with an empty list.
  public ExchangeList() {
    this.list = new ArrayList<>();
  }

  /**
   * Adds an exchange operation to the list.
   *
   * @param exchange the exchange operation to add
   */
  public void add(Exchange exchange) {
    list.add(exchange);
  }

  /**
   * Gets the size of the list.
   *
   * @return the size of the list
   */
  public int getSize() { return list.size();  }

  /**
   * Returns a deque containing all exchange operations in the list.
   *
   * @return a deque containing all exchange operations
   */
  public Deque<Exchange> getListExchange() {
    return new ArrayDeque<>(list);
  }

  /**
   * Writes the list of exchange operations to a file named "list.txt".
   *
   * @throws IOException if an I/O error occurs while writing to the file
   */
  public void writeFileListHistory()
      throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter("list.txt", true));
    writer.write(toString());
    writer.close();
  }

  /**
   * Returns a string representation of the list.
   *
   * @return a string representation of the list
   */
  @Override
  public String toString() {
    return "" + list;
  }


}
