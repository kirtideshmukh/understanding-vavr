import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;

public class OptionExample {
  public static void main(String[] args) {
    understandingMap();
    understandingFilter();
    understandingSequence();
  }

  private static void understandingMap() {
    System.out.println("Understanding Option.map()");
    /**
     * a) Some(value).map(v -> otherValue) == Some(otherValue) b ) None().map(v -> otherValue) ==
     * None()
     */
    Option<String> result1 = Option.of("kirti").map(n -> "kiri");
    System.out.println(result1.isDefined()); // true
    System.out.println(result1.get()); // "kiri"

    Option<String> result2 = Option.of(null).map(n -> "kiri");
    System.out.println(result2.isDefined()); // false
  }

  private static void understandingFilter() {
    System.out.println("Understanding Option.filter()");
    /** It's similar to ternary operator */
    String name = "kirti";
    String error = "error";
    Either<String, String> result1 = Option.of(name).filter((n) -> true).toEither(error);

    System.out.println(result1.isRight()); // true
    System.out.println(result1.get()); // kirti

    Either<String, String> result2 = Option.of(name).filter((n) -> false).toEither(error);

    System.out.println(result2.isRight()); // false
    System.out.println(result2.getLeft()); // error

    // all above functions with ternary operator

    Either<String, String> result1Ternary =
        2 > 1 // i.e true
            ? Either.right(name)
            : Either.left(error);
    System.out.println(result1Ternary.isRight()); // true
    System.out.println(result1Ternary.get()); // kirti

    Either<String, String> result2Ternary =
        1 > 2 // i.e false
            ? Either.right(name)
            : Either.left(error);

    System.out.println(result2Ternary.isRight()); // false
    System.out.println(result2Ternary.getLeft()); // error
  }

  private static void understandingSequence() {
    System.out.println("Understanding Option.sequence()");
    /*** Reduces many {@code Option}s into a single  Option by transforming an
     * {@code Iterable<Option<? extends T>>} into a Option<Seq<T>>. If any of
     * the Options are  Option.None, then this returns Option.None.
     */

    Option<Seq<String>> result1 = Option.sequence(List.of(Option.of("kirti"), Option.of("john")));
    System.out.println(result1.isDefined()); // true
    System.out.println(result1.get()); // Vector(kirti, john)

    Option<Seq<String>> result2 = Option.sequence(List.of(Option.of("kirti"), Option.none()));
    System.out.println(result2.isDefined()); // false
  }
}
