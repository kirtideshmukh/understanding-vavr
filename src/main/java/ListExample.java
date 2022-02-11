import io.vavr.collection.List;
import io.vavr.collection.Map;

public class ListExample {
  public static void main(String[] args) {
    understandingMap();
    understandingGroupBy();
  }

  private static void understandingMap() {
    System.out.println("Understanding List.map()");

    List<String> names = List.of("kirti", "john");
    List<String> updatesNames = names.map(n -> n.toUpperCase());
    System.out.println(updatesNames); // List(KIRTI, JOHN)
  }

  private static void understandingGroupBy() {
    System.out.println("Understanding List.groupBy()");

    List<String> names = List.of("kirti", "john", "kiran", "joey", "sana");
    Map<Integer, List<String>> namesByLength = names.groupBy(n -> n.length());
    System.out.println(
        namesByLength); // LinkedHashMap((5, List(kirti, kiran)), (4, List(john, joey, sana)))
  }
}
