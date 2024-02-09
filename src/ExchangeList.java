import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.ListIterator;

public class ExchangeList {

  private final List<Exchange> list;

  public ExchangeList() {
    this.list = new ArrayList<>();
  }

  public void add(Exchange exchange) {
    list.add(exchange);
  }

  public int getSize() { return list.size();  }

  public Deque<Exchange> getListExchange() {
    return new ArrayDeque<>(list);
  }

  public Deque<Exchange> getReverseListingQueue() {
    List<Exchange> reversedList = new ArrayList<>(list.size());
    ListIterator<Exchange> iteratorS = list.listIterator(list.size());
    while (iteratorS.hasPrevious()) {
      reversedList.add(iteratorS.previous());
    }
    return new ArrayDeque<>(reversedList);
  }

  @Override
  public String toString() {
    return "" + list;
  }

  public void writeFileListHistory()
      throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter("list.txt", true));
    writer.write(toString());
    writer.close();
  }
}
