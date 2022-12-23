package com.skewwhiffy.service

import com.skewwhiffy.notation.model.clef.Clef
import com.skewwhiffy.notation.model.note.Note
import com.skewwhiffy.notation.model.scale.Scale
import com.skewwhiffy.notation.model.scale.ScaleDirection
import com.skewwhiffy.notation.model.scale.ScaleType
import org.springframework.stereotype.Service

@Service
class ScaleService {
  fun getScale(
      clef: Clef,
      note: Note,
      scaleType: ScaleType,
      direction: ScaleDirection,
  ): Scale {
    return clef.getNoteNearBottom(note).let { Scale(it, scaleType, direction) }
  }
}