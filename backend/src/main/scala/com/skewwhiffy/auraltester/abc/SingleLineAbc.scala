package com.skewwhiffy.auraltester.abc

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, NoteLength}
import com.skewwhiffy.auraltester.scales.{Key, Scale}

object SingleLineAbc {
  def apply(
    displayName: String,
    clef: Clef,
    noteLength: NoteLength,
    notes: List[AbsoluteNote]
  ) = new SingleLineAbc(Some(displayName), clef, noteLength, notes)

  def apply(clef: Clef, noteLength: NoteLength, notes: List[AbsoluteNote]) =
    new SingleLineAbc(None, clef, noteLength, notes)
}

class SingleLineAbc(
  private val displayName: Option[String],
  private val clef: Clef,
  private val noteLength: NoteLength,
  private val notes: List[AbsoluteNote],
  private val keySignature: Option[Key] = None
) {
  def includeKeySignature(key: Key): SingleLineAbc =
    new SingleLineAbc(displayName, clef, noteLength, notes, Some(key))

  lazy val abc: String =
    List(
      "X:1",
      displayName.map(it => s"T:$it").getOrElse(""),
      s"K:clef=${clef.abc}",
      keySignature.map(it => s"K:${it.abc}").getOrElse(""),
      s"L:${noteLength.abc}",
      notes.map(it => it.abc(keySignature.getOrElse(Key.cMajor))).mkString
    )
      .filter(it => it.nonEmpty)
      .mkString(System.lineSeparator())
}