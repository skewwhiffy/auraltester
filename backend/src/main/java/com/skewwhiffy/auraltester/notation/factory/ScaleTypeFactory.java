package com.skewwhiffy.auraltester.notation.factory;

import com.skewwhiffy.auraltester.notation.model.scale.ScaleType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScaleTypeFactory {
    private final IntervalFactory intervalFactory;

    public ScaleType getMajor() {
        return new ScaleType("major", "", intervalFactory.getDirectedIntervals("1 2 3 4 5 6 7 8"));
    }

    public ScaleType getMinorHarmonic() {
        return new ScaleType("minor harmonic", "min", intervalFactory.getDirectedIntervals("1 2 3- 4 5 6- 7 8"));
    }

    public ScaleType getMinorMelodic() {
        return new ScaleType(
                "minor melodic",
                "min",
                intervalFactory.getDirectedIntervals("1 2 3- 4 5 6 7 8"),
                intervalFactory.getDirectedIntervals("1 2 3- 4 5 6- 7- 8")
        );
    }

}
