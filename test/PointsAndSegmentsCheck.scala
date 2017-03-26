import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties, Test}

object PointsAndSegmentsCheck extends Properties("PointsAndSegments"){

  val genSegments: Gen[(Int, Int)] = for {
    lower <- Gen.choose(-10000,0)
    upper <- Gen.choose(lower, 10000)
  } yield (lower, upper)
  val listOfSegments: Gen[Array[(Int,Int)]] =
    for {
      size <- Gen.choose(1,1000)
      elems <- Gen.containerOfN[Array, (Int, Int)](size, genSegments)
    } yield elems

  val genSize: Gen[Int] = Gen.choose(-10000, 10000)
  val listOfNumbers: Gen[Array[Int]] =
    for {
      size <- Gen.choose(1,1000)
      elems <- Gen.containerOfN[Array, Int](size, genSize)
    } yield elems

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

  override def overrideParameters(p: Test.Parameters): Test.Parameters = p.withMinSuccessfulTests(10000)
}
