package com.skewwhiffy.auraltester.notation.factory;

import com.skewwhiffy.auraltester.exception.UnrecognizedClefException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;

import java.util.Locale;

@Service
@AllArgsConstructor
public class InternalNotationFactory {
    /*
class InternalNotationFactory(
*/
  private final ClefFactory clefFactory;

    /*
  val intervalFactory: IntervalFactory,
  val keyFactory: KeyFactory,
  val noteFactory: NoteFactory
) {
*/
    public Clef clef(String clefRaw) {
        return switch (clefRaw.toLowerCase(Locale.UK)) {
            case "treble" -> clefFactory.getTreble();
            case "alto" -> clefFactory.getAlto();
            case "tenor" -> clefFactory.getTenor();
            case "bass" -> clefFactory.getBass();
            default -> throw new UnrecognizedClefException(clefRaw);
        };
    }

  /*
  fun getNote(noteRaw: String): AbsoluteNote {
    return noteFactory.getAbsoluteNote(noteRaw)
  }

  fun getNotes(notesRaw: String): List<AbsoluteNote> = notesRaw
    .split(' ')
    .map(::getNote)
    .toList()

  fun getDirectedInterval(rawInterval: String): DirectedInterval = intervalFactory
    .getDirectedInterval(rawInterval)

  fun getDirectedIntervals(rawIntervals: String): List<DirectedInterval> = intervalFactory
    .getDirectedIntervals(rawIntervals)

  fun getKey(rawKey: String): Key = keyFactory.getKey(rawKey)
}
     */
}
