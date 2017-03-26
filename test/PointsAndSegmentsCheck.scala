import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

object PointsAndSegmentsCheck extends Properties("PointsAndSegments"){

  val genSegments: Gen[(Int, Int)] = for {
    lower <- Gen.choose(-1000,1000)
    upper <- Gen.choose(lower, 1000)
  } yield (lower, upper)
  val listOfSegments: Gen[Array[(Int,Int)]] =
    for {
      size <- Gen.choose(1,100)
      elems <- Gen.containerOfN[Array, (Int, Int)](size, genSegments)
    } yield elems

  val genSize: Gen[Int] = Gen.choose(1, 1000)
  val listOfNumbers: Gen[Array[Int]] =
    for {
      size <- Gen.choose(1,100)
      elems <- Gen.containerOfN[Set, Int](size, genSize)
    } yield elems.toArray

  property("both solutions should be equivalent") =
    forAll(listOfSegments, listOfNumbers){(segments, points)  =>
      val starts = segments.map(_._1)
      val ends = segments.map(_._2)

      val expectedResult = PointsAndSegments.naiveCountSegments(starts, ends, points)
      val result = PointsAndSegments.fastCountSegments(starts,ends,points)

      val assertion = expectedResult sameElements result
      if (!assertion)
        System.out.println(s"ERROR with expected result: ${expectedResult.mkString(",")} and result: ${result.mkString(",")} for segments : ${segments.mkString(",")} and points: ${points.mkString(",")}")
      assertion
    }

}
