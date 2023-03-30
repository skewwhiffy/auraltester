package com.skewwhiffy.auraltester.controller;

/*
@ExtendWith(MockitoExtension::class)
class KeySignatureControllerTest {
  @Mock
  private InternalNotationFactory internalNotationFactory;

  @Mock
  private AbcService abcService;

  @InjectMocks
  private KeySignatureController keySignatureController;
  private Clef clef;
  private String clefString;
  private String keySignature;
  private AbsoluteNote keySignatureNote;
  private String abc;

  @BeforeEach
  fun `set up`() {
    clef = TestData.random.clef
    clefString = TestData.random.string
    keySignature = TestData.random.string
    keySignatureNote = TestData.random.absoluteNote
    abc = TestData.random.string
  }

  @Test
  fun `responds correctly`() {
    `when`(internalNotationFactory.clef(clefString)).thenReturn(clef)
    `when`(internalNotationFactory.getNote(keySignature)).thenReturn(keySignatureNote)
    `when`(abcService.getAbc(clef, Key(keySignatureNote.note))).thenReturn(abc.let {
      object : AbcProvider {
        override val abc: String = it
      }
    })

    val actual = keySignatureController.get(clefString, keySignature)

    assertThat(actual.abc).isEqualTo(abc)
  }
}

 */