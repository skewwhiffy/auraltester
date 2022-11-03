package com.skewwhiffy.auraltester.services

import com.skewwhiffy.auraltester.clefs.ClefFactory
import com.skewwhiffy.auraltester.internalnotation.{IntervalFactory, NoteFactory}
import com.skewwhiffy.auraltester.notes.Note
import com.skewwhiffy.auraltester.scales.{Key, ScaleDirection, ScaleTypeFactory}
import com.skewwhiffy.auraltester.testutils.MockInstantiation
import org.mockito.InjectMocks
import org.scalatest.flatspec.AnyFlatSpec

class ScaleServiceTest extends AnyFlatSpec with MockInstantiation {
  private val noteFactory = new NoteFactory()
  private val clefFactory = new ClefFactory(noteFactory)
  private val intervalFactory = new IntervalFactory()
  private val scaleTypeFactory = new ScaleTypeFactory(intervalFactory)
  @InjectMocks
  private var scaleService: ScaleService = _

  it should "return correct abc for major scale" in {
    //noinspection SpellCheckingInspection
    val expected = "DE^FGAB^cd"
    val result = scaleService.getScale(clefFactory.treble, Note.d, scaleTypeFactory.major, ScaleDirection.ascending)

    assert(result.abc(Key.cMajor).contains(expected))
  }

  it should "return correct abc for minor melodic descending scale" in {
    //noinspection SpellCheckingInspection
    val expected = "agfedcBA"

    val result = scaleService.getScale(clefFactory.treble, Note.a, scaleTypeFactory.minorMelodic, ScaleDirection.descending)

    assert(result.abc(Key.cMajor).contains(expected))
  }
}