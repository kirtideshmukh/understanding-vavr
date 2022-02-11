import io.vavr.collection.List;
import io.vavr.collection.Map;

public class MapExample {

  public static void main(String[] args) {
    understandingMapValues();
  }

  private static void understandingMapValues() {
    System.out.println("Understanding List.groupBy()");

    List<String> names = List.of("kirti", "john", "kiran", "joey", "sana");
    Map<Integer, List<String>> namesByLength = names.groupBy(n -> n.length());
    System.out.println(
        namesByLength); // LinkedHashMap((5, List(kirti, kiran)), (4, List(john, joey, sana)))
    Map<Integer, Integer> countByTextLength = namesByLength.mapValues(v -> v.size());
    System.out.println(
        countByTextLength); // LinkedHashMap((5, 2), (4, 3)) i.e. there are 2 names with length 5
    // and 3 names with length 4
  }
}
