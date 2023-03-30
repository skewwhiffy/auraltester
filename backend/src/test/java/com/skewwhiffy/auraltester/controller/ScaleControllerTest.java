package com.skewwhiffy.auraltester.controller;

/*
@ExtendWith(MockitoExtension::class)
class ScaleControllerTest {
  @Mock
  private AbcService abcService;

  @Mock
  private InternalNotationFactory internalNotationFactory;

  @Mock
  private ScaleService scaleService;

  @Mock
  private ScaleTypeFactory scaleTypeFactory;

  @InjectMocks
  private ScaleController scaleController;

  companion object {
    @JvmStatic
    fun scaleTestCases(): Stream<Arguments> = mapOf<String, (f: ScaleTypeFactory) -> ScaleType>(
      "major" to { it.major },
      "minor-harmonic" to { it.minorHarmonic },
      "minor-melodic" to { it.minorMelodic }
    ).flatMap { scaleType ->
      mapOf(
        "ascending" to ScaleDirection.ASCENDING,
        "descending" to ScaleDirection.DESCENDING
      )
        .map { direction ->
          Arguments.of(scaleType.key, scaleType.value, direction.key, direction.value)
        }
    }.stream()
  }

  @ParameterizedTest
  @MethodSource("scaleTestCases")
  fun `responds correctly`(
    scaleTypeString: String,
    getScale: (f: ScaleTypeFactory) -> ScaleType,
    direction: String,
    directionObject: ScaleDirection,
  ) {
    val clefString = TestData.random.string
    val noteString = TestData.random.string
    val abcWithoutKeySignature = TestData.random.string
    val abcWithKeySignature = TestData.random.string
    val scaleLowestNote = TestData.random.absoluteNote
    val scale = TestData.random.scale
    val key = Key(scale.lowestNote.note, scaleTypeString != "major")
    val clefObject = TestData.random.clef
    val scaleType = TestData.random.scaleType
    val keyCaptor: ArgumentCaptor<Key> = ArgumentCaptor.forClass(Key::class.java)
    `when`(internalNotationFactory.clef(clefString)).thenReturn(clefObject)
    `when`(internalNotationFactory.getNote(noteString)).thenReturn(scaleLowestNote)
    `when`(getScale(scaleTypeFactory)).thenReturn(scaleType)
    `when`(
      scaleService.getScale(
        clefObject,
        scaleLowestNote.note,
        scaleType,
        directionObject
      )
    ).thenReturn(scale)
    `when`(abcService.getAbc(clefObject, scale))
      .thenReturn(object : AbcProvider {
        override val abc = abcWithoutKeySignature
      })
    `when`(abcService.getAbc(eq(clefObject), eq(scale), keyCaptor.capture()))
      .then {
        val suppliedClef = it.getArgument<Clef>(0)
        val suppliedScale = it.getArgument<Scale>(1)
        val suppliedKey = it.getArgument<Key>(2)

        assertThat(suppliedClef).isEqualTo(clefObject)
        assertThat(suppliedScale).isEqualTo(scale)
        object : AbcProvider {
          override val abc = suppliedKey?.let { abcWithKeySignature } ?: abcWithoutKeySignature
        }
      }

    val actual = scaleController.get(
      clefString,
      noteString,
      scaleTypeString,
      direction
    )

    assertThat(keyCaptor.allValues).isEqualTo(listOf(null, key))
    assertThat(actual.withKeySignature).isEqualTo(abcWithKeySignature)
    assertThat(actual.withoutKeySignature).isEqualTo(abcWithoutKeySignature)
  }
}

/*
  "throw when an invalid scale type is requested" in {
    val note = TestData.random.string
    when(internalNotationFactory.getNote(note)).thenReturn(TestData.random.absoluteNote)

    assertThrows[IllegalArgumentException] {
      scaleController.index("alto", note, "not-a-scale", "ascending").apply(getRequest)
    }
  }

  "throw when an invalid direction is requested" in {
    val note = TestData.random.string
    when(internalNotationFactory.getNote(note)).thenReturn(TestData.random.absoluteNote)
    when(scaleTypeFactory.major).thenReturn(TestData.random.scaleType)

    assertThrows[IllegalArgumentException] {
      scaleController.index("bass", note, "major", "stationary").apply(getRequest)
    }
  }
}
*/
