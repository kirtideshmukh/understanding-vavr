import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;

public class EitherExample {
  public static void main(String[] args) {
    understandingFlatMap();
    understandingMap();
    understandingSequence();
    understandingSequenceRight();

    /** Clarifications: 1. Why there is NO support for Either.sequenceLeft() */
  }

  private static void understandingFlatMap() {
    System.out.println("Understanding Either.flatMap()");

    Either<Object, Object> result1 = Either.right("kirti").flatMap(n -> toUpperCase(n));
    System.out.println(result1.isRight()); // true
    System.out.println(result1.get()); // KIRTI
  }

  private static Either<Object, Object> toUpperCase(String text) {
    return text == null ? Either.left("error") : Either.right(text.toUpperCase());
  }

  private static void understandingMap() {
    System.out.println("Understanding Either.Map()");

    Either<Object, Object> result1 = Either.right("kirti").map(n -> toUpperCase(n));
    System.out.println(result1.isRight()); // true
    System.out.println(result1.get()); // Right(KIRTI)
  }

  private static void understandingSequence() {
    System.out.println("Understanding Either.sequence()");
    /**
     * Reduces many Eithers into a single Either by transforming an Iterable<Either<L, R>> into a
     * Either<Seq<L>, Seq<R>>. a) If any of the given Eithers is a Either.Left then sequence returns
     * a Either.Left containing a non-empty Seq of all left values. b) If none of the given Eithers
     * is a Either.Left then sequence returns a Either.Right containing a (possibly empty) Seq of
     * all right values.
     */
    Either<Seq<String>, Seq<String>> result1 = Either.sequence(List.empty()); // Right(Seq())
    System.out.println(result1.isRight()); // true

    Either<Seq<String>, Seq<String>> result2 =
        Either.sequence(
            List.of(Either.right("kirti"), Either.right("john"))); // Right(Seq(kirti, john))
    System.out.println(result2.isRight()); // true
    System.out.println(result2.get()); // Vector(kirti, john)

    Either<Seq<String>, Seq<String>> result3 =
        Either.sequence(
            List.of(
                Either.right("kirti"),
                Either.left("error1"),
                Either.left("error2"))); // Left(Seq(error))
    System.out.println(result3.isRight()); // false
    System.out.println(result3.getLeft()); // Vector(error1, error2)
  }

  private static void understandingSequenceRight() {
    System.out.println("Understanding Either.sequenceRight()");
    /**
     * Reduces many Eithers into a single Either by transforming an Iterable<Either<L, R>> into a
     * Either<L, Seq<R>>. a) If any of the given Eithers is a Either.Left then sequenceRight returns
     * a Either.Left containing the first left value (in iteration order). b) If none of the given
     * Eithers is a Either.Left then sequenceRight returns a Either.Right containing a (possibly
     * empty) Seq of all right values.
     */
    Either<String, Seq<String>> result1 = Either.sequenceRight(List.empty()); // Right(Seq())
    System.out.println(result1.isRight()); // true

    Either<String, Seq<String>> result2 =
        Either.sequenceRight(
            List.of(Either.right("kirti"), Either.right("john"))); // Right(Seq(kirti, john))
    System.out.println(result2.isRight()); // true
    System.out.println(result2.get()); // Vector(kirti, john)

    Either<String, Seq<String>> result3 =
        Either.sequenceRight(
            List.of(
                Either.right("kirti"),
                Either.left("error1"),
                Either.left("error2"))); // Left(Seq(1))
    System.out.println(result3.isRight()); // false
    System.out.println(
        result3.getLeft()); // error1 ==> VERY IMP i.e. first left value in the iteration order
  }
}
