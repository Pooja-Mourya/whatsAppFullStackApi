import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyList {

  public static void main(String[] args) {
    List<String> stringSms = new ArrayList<>();
    stringSms.add("this is new message");
    stringSms.add("this is Second one");
    stringSms.add("this is third one");
    stringSms.add("this is fifth last");

    Iterator<String> iterator = stringSms.iterator();
    while (iterator.hasNext()) {
      String card = iterator.next();
      System.out.println(card);
    }
  }
}
