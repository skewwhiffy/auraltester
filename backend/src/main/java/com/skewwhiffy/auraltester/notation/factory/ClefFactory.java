package com.skewwhiffy.auraltester.notation.factory;

import com.skewwhiffy.auraltester.model.ClefType;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClefFactory {
    private final NoteFactory noteFactory;

    public Clef get(ClefType clefType) {
        return switch (clefType) {
            case TREBLE -> getTreble();
            case ALTO -> getAlto();
            case TENOR -> getTenor();
            case BASS -> getBass();
        };
    }

    public Clef getTreble() {
        return new Clef(
                ClefType.TREBLE,
                "treble",
                AbsoluteNote.getMiddleC(),
                noteFactory.getAbsoluteNote("a")
        );
    }

    public Clef getAlto() {
        return new Clef(
                ClefType.ALTO,
                "alto",
                noteFactory.getAbsoluteNote("D,"),
                noteFactory.getAbsoluteNote("B")
        );
    }


    public Clef getTenor() {
        return new Clef(
                ClefType.TENOR,
                "tenor",
                noteFactory.getAbsoluteNote("B,,"),
                noteFactory.getAbsoluteNote("G")
        );
    }


    public Clef getBass() {
        return new Clef(
                ClefType.BASS,
                "bass",
                noteFactory.getAbsoluteNote("E,,"),
                AbsoluteNote.getMiddleC()
        );
    }
}
