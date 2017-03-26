import org.scalatest.FlatSpec

class PointsAndSegmentsTest extends FlatSpec {

  behavior of "PointsAndSegments"

  it should "compute the number of segments for some points for a case" in {
    assert(PointsAndSegments.fastCountSegments(Array(0,7), Array(5,10), Array(1,6,11)) === Array(1,0,0))
  }

  it should "compute the number of segments for some points for b case" in {
    assert(PointsAndSegments.fastCountSegments(Array(-10), Array(10), Array(-100,100,0)) === Array(0,0,1))
  }

  it should "compute the number of segments for some points for c case" in {
    assert(PointsAndSegments.fastCountSegments(Array(0,-3, 7), Array(5,2,10), Array(1,6)) === Array(2,0))
  }

  it should "compute the number of segments for some points for d case" in {
    assert(PointsAndSegments.fastCountSegments(Array(7), Array(16), Array(1)) === Array(0))
  }

  it should "compute the number of segments for some points for e case" in {
    assert(PointsAndSegments.fastCountSegments(Array(38), Array(777), Array(270,38)) === Array(1,1))
  }

  it should "compute the number of segments for some points for f case" in {
    assert(PointsAndSegments.fastCountSegments(Array(1), Array(1), Array(1,1)) === Array(1,1))
  }

  it should "compute the number of segments for some points for g case" in {
    assert(PointsAndSegments.fastCountSegments(Array(4,5,6), Array(5,6,7), Array(4,5,6)) === Array(1,2,2))
  }

  it should "compute the number of segments for some points for h case" in {
    assert(PointsAndSegments.fastCountSegments(Array(-1,14), Array(1,97), Array(-1,-1)) === Array(1,1))
  }

  it should "compute the number of segments for some points for i case" in {
    assert(PointsAndSegments.fastCountSegments(Array(0,-1), Array(0,0), Array(-1,0)) === Array(1,2))
  }

}
