package com.skewwhiffy.auraltester.services

import com.skewwhiffy.auraltester.abc.SingleLineAbc
import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.notes.NoteLength
import com.skewwhiffy.auraltester.scales.{Key, Scale}
import org.springframework.stereotype.Component

@Component
class AbcService {
  def getAbc(clef: Clef, scale: Scale): String = {
    new SingleLineAbc(
      s"${scale.displayName} ${scale.direction.displayString}",
      clef,
      NoteLength.semibreve,
      scale.notes
    ).abc
  }

  def getAbc(clef: Clef, scale: Scale, key: Key): String = {
    if (!key.canRenderSignature) {
      ""
    } else {
      new SingleLineAbc(
        s"${scale.displayName} ${scale.direction.displayString} (with key signature)",
        clef,
        NoteLength.semibreve,
        scale.notes
      ).includeKeySignature(key).abc
    }
  }

}
