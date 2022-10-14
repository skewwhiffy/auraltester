package com.skewwhiffy.auraltester.notes;

import com.skewwhiffy.auraltester.helper.StreamHelper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.BiFunction;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class AccidentalTests {
  @Test
  void when_natural_then_displaysCorrectly() {
    val expected = "";
    val natural = Accidental.NATURAL();

    val actual = natural.getDisplayString();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void when_flattening_natural_then_displaysFlat() {
    val expected = "b";
    val natural = Accidental.NATURAL();

    val actual = natural.getFlat();

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @Test
  void when_sharpening_natural_then_displaysSharp() {
    val expected = "#";
    val natural = Accidental.NATURAL();

    val actual = natural.getSharp();

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @Test
  void when_flat_then_displaysFlat() {
    val expected = "b";
    val flat = Accidental.FLAT();

    val actual = flat.getDisplayString();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void when_flatteningFlat_then_displaysDoubleFlat() {
    val expected = "bb";
    val flat = Accidental.FLAT();

    val actual = flat.getFlat();

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @Test
  void when_sharpeningFlat_then_displaysNatural() {
    val expected = "";
    val flat = Accidental.FLAT();

    val actual = flat.getSharp();

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @ParameterizedTest
  @ValueSource(ints = {3, 7})
  void when_multipleFlats_then_displaysCorrectly(int flats) {
    val expected = "b".repeat(flats);
    BiFunction<Accidental, Integer, Accidental> reducer = (acc, i) -> acc.getFlat();
    val accidental = IntStream
      .range(0, flats).boxed()
      .reduce(Accidental.NATURAL(), reducer, StreamHelper::noParallel);

    val actual = accidental.getDisplayString();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void when_sharp_then_displaysSharp() {
    val expected = "#";
    val sharp = Accidental.SHARP();

    val actual = sharp.getDisplayString();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void when_sharpeningSharp_then_displaysDoubleSharp() {
    val expected = "x";
    val sharp = Accidental.SHARP();

    val actual = sharp.getSharp();

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @Test
  void when_flatteningSharp_then_displaysNatural() {
    val expected = "";
    val sharp = Accidental.SHARP();

    val actual = sharp.getFlat();

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @ParameterizedTest
  @ValueSource(ints = { 6, 10})
  void when_multipleEvenSharps_then_displaysCorrectly(int numberOfSharps) {
    val expected = "x".repeat(numberOfSharps/2);
    val accidental = IntStream
      .range(0, numberOfSharps)
      .boxed()
      .reduce(Accidental.NATURAL(), (it, i) -> it.getSharp(), StreamHelper::noParallel);

    val actual = accidental.getDisplayString();

    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @ValueSource(ints = { 7, 13})
  void when_multipleOddSharps_then_displaysCorrectly(int numberOfSharps) {
    val expected = "x".repeat(numberOfSharps/2) + "#";
    val accidental = IntStream
      .range(0, numberOfSharps)
      .boxed()
      .reduce(Accidental.NATURAL(), (it, i) -> it.getSharp(), StreamHelper::noParallel);

    val actual = accidental.getDisplayString();

    assertThat(actual).isEqualTo(expected);
  }
}