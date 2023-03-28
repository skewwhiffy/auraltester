package com.skewwhiffy.auraltester.notation.factory;

import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClefFactory {
    private final NoteFactory noteFactory;

    public Clef getTreble() {
        return new Clef(
                "treble",
                AbsoluteNote.getMiddleC(),
                noteFactory.getAbsoluteNote("a")
        );
    }

    public Clef getAlto() {
        return new Clef("alto", noteFactory.getAbsoluteNote("D,"), noteFactory.getAbsoluteNote("B"));
    }


    public Clef getTenor() {
        return new Clef("tenor", noteFactory.getAbsoluteNote("B,,"), noteFactory.getAbsoluteNote("G"));
    }


    public Clef getBass() {
        return new Clef("bass", noteFactory.getAbsoluteNote("E,,"), AbsoluteNote.getMiddleC());
    }
}
