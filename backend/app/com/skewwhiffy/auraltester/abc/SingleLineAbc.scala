package com.skewwhiffy.auraltester.abc

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, NoteLength}
import com.skewwhiffy.auraltester.scales.Key

object SingleLineAbc {
  def apply(
    displayName: String,
    clef: Clef,
    noteLength: NoteLength,
    notes: List[List[AbsoluteNote]]
  ): SingleLineAbc = SingleLineAbc(Some(displayName), clef, noteLength, notes)

  def apply(
    clef: Clef,
    noteLength: NoteLength,
    notes: List[List[AbsoluteNote]]
  ): SingleLineAbc = SingleLineAbc(None, clef, noteLength, None, notes)

  def apply(
    displayName: Option[String],
    clef: Clef,
    noteLength: NoteLength,
    notes: List[List[AbsoluteNote]]
  ): SingleLineAbc = SingleLineAbc(displayName, clef, noteLength, None, notes)
}

case class SingleLineAbc(
  private val displayName: Option[String],
  private val clef: Clef,
  private val noteLength: NoteLength,
  private val keySignature: Option[Key],
  private val notes: List[List[AbsoluteNote]]
) {

  def includeKeySignature(key: Key): SingleLineAbc = SingleLineAbc(displayName, clef, noteLength, Some(key), notes)

  lazy val abc: String = {
    def barAbc(value: List[AbsoluteNote]): String = {
      value
        .map(it => it.abc(keySignature.getOrElse(Key.cMajor)))
        .mkString
    }
    val notesAbc = notes
      .map(it => barAbc(it))
      .mkString("|") + "|"
    List(
      "X:1",
      displayName.map(it => s"T:$it").getOrElse(""),
      s"K:clef=${clef.abc}",
      keySignature.map(it => s"K:${it.abc}").getOrElse(""),
      s"L:${noteLength.abc}",
      notesAbc,
      "w:C D"
    )
      .filter(it => it.nonEmpty)
      .mkString(System.lineSeparator())
  }
}