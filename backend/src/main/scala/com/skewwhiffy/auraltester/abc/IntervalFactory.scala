package com.skewwhiffy.auraltester.abc

import com.skewwhiffy.auraltester.notes.Interval
import scala.util.chaining._

class IntervalFactory(private val abc: String) {
  lazy val interval: Interval = {
    abc.toInt.pipe(it => it match
      case 1 | 4 | 5 | 8 => Interval.perfect(it)
      case _ => Interval.major(it)
    )
  }
}
