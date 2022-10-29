package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.AbsoluteNote
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ClefFactory(@Autowired internalNotationFactory: InternalNotationFactory) {
  def treble: Clef = new Clef("treble", AbsoluteNote.middleC, internalNotationFactory.note("a"))
  def alto: Clef = new Clef("alto", internalNotationFactory.note("D,"), internalNotationFactory.note("B"))
  def tenor: Clef = new Clef("tenor", internalNotationFactory.note("B,,"), internalNotationFactory.note("G"))
  def bass: Clef = new Clef("bass", internalNotationFactory.note("E,,"), AbsoluteNote.middleC)
}
