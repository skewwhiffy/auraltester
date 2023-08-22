package com.skewwhiffy.auraltester.service

import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.note.Note
import com.skewwhiffy.auraltester.notation.model.scale.Scale
import com.skewwhiffy.auraltester.notation.model.scale.ScaleDirection
import com.skewwhiffy.auraltester.notation.model.scale.ScaleType
import org.springframework.stereotype.Service

@Service
class ScaleService {
    fun getScale(clef: Clef, note: Note, scaleType: ScaleType, direction: ScaleDirection) = Scale(
        clef.getNoteNearBottom(note),
        scaleType,
        direction
    )
}
