package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.internalnotation.NoteFactory
import com.skewwhiffy.auraltester.notes.AbsoluteNote
import org.springframework.stereotype.Component

@Component
class ClefFactory() {
  def treble: Clef = new Clef("treble", AbsoluteNote.middleC, new NoteFactory("a").absoluteNote)
  def alto: Clef = new Clef("alto", new NoteFactory("D,").absoluteNote, new NoteFactory("B").absoluteNote)
  def tenor: Clef = new Clef("tenor", new NoteFactory("B,,").absoluteNote, new NoteFactory("G").absoluteNote)
  def bass: Clef = new Clef("bass", new NoteFactory("E,,").absoluteNote, AbsoluteNote.middleC)
}
