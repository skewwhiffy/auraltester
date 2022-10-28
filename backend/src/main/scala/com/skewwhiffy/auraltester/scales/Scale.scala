package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.notes.AbsoluteNote

class Scale(private val start: AbsoluteNote, private val scaleType: ScaleType) {
  lazy val displayName: String = s"${start.note.displayString} ${scaleType.displayName}"
  lazy val notes: List[AbsoluteNote] = scaleType.intervals.map(start.apply)
  lazy val abc: String = notes.map(it => it.abc).mkString("")
}