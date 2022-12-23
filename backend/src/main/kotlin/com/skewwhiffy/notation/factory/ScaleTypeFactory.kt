package com.skewwhiffy.notation.factory

import com.skewwhiffy.notation.model.scale.ScaleType
import org.springframework.stereotype.Service

@Service
class ScaleTypeFactory(private val intervalFactory: IntervalFactory) {
  /*
class ScaleTypeFactory @Inject()(intervalFactory: IntervalFactory) {
*/
  private val majorIntervals: String = "1 2 3 4 5 6 7 8"
  /*
  private lazy val minorHarmonicIntervals: String = "1 2 3- 4 5 6- 7 8"
  private lazy val minorMelodicAscendingIntervals: String = "1 2 3- 4 5 6 7 8"
  private lazy val minorMelodicDescendingIntervals: String = "1 2 3- 4 5 6- 7- 8"
  */

  val major get() = ScaleType("major", "", majorIntervals.toIntervals)

  /*
  def minorHarmonic: ScaleType = new ScaleType("minor harmonic", "min", minorHarmonicIntervals.toIntervals)
  def minorMelodic: ScaleType = new ScaleType(
    "minor melodic",
    "min",
    minorMelodicAscendingIntervals.toIntervals,
    minorMelodicDescendingIntervals.toIntervals
  )
   */

  private val String.toIntervals get() = intervalFactory.getDirectedIntervals(this)

}