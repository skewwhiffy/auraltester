package com.skewwhiffy.auraltester.service;

import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.note.Note;
import com.skewwhiffy.auraltester.notation.model.scale.Scale;
import com.skewwhiffy.auraltester.notation.model.scale.ScaleDirection;
import com.skewwhiffy.auraltester.notation.model.scale.ScaleType;
import org.springframework.stereotype.Service;

@Service
public class ScaleService {
    public Scale getScale(
            Clef clef,
            Note note,
            ScaleType scaleType,
            ScaleDirection direction
    ) {
        return new Scale(clef.getNoteNearBottom(note), scaleType, direction);
    }
}
