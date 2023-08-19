package com.skewwhiffy.auraltester.notation.factory

import com.skewwhiffy.auraltester.controller.ClefType
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import org.springframework.stereotype.Service

@Service
class ClefFactory(val noteFactory: NoteFactory) {
    /*
    public Clef get(ClefType clefType) {
        return switch (clefType) {
            case TREBLE -> getTreble();
            case ALTO -> getAlto();
            case TENOR -> getTenor();
            case BASS -> getBass();
        };
    }
    */

    val treble: Clef
        get() {
            return Clef(
                ClefType.TREBLE,
                "treble",
                AbsoluteNote.middleC,
                noteFactory.getAbsoluteNote("a")
            )
        }

    val alto: Clef
        get() {
            return Clef(
                ClefType.ALTO,
                "alto",
                noteFactory.getAbsoluteNote("D,"),
                noteFactory.getAbsoluteNote("B")
            )
        }

    val tenor: Clef
        get() {
            return Clef(
                ClefType.TENOR,
                "tenor",
                noteFactory.getAbsoluteNote("B,,"),
                noteFactory.getAbsoluteNote("G")
            )
        }

    val bass: Clef
        get() {
            return Clef(
                ClefType.BASS,
                "bass",
                noteFactory.getAbsoluteNote("E,,"),
                AbsoluteNote.middleC
            )
        }
}
