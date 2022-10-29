package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.internalnotation.NoteFactory
import com.skewwhiffy.auraltester.notes.AbsoluteNote
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ClefFactory(@Autowired noteFactory: NoteFactory) {
  def treble: Clef = new Clef("treble", AbsoluteNote.middleC, noteFactory.getAbsoluteNote("a"))

  def alto: Clef = new Clef("alto", noteFactory.getAbsoluteNote("D,"), noteFactory.getAbsoluteNote("B"))

  def tenor: Clef = new Clef("tenor", noteFactory.getAbsoluteNote("B,,"), noteFactory.getAbsoluteNote("G"))

  def bass: Clef = new Clef("bass", noteFactory.getAbsoluteNote("E,,"), AbsoluteNote.middleC)
}
