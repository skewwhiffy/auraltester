package com.skewwhiffy.auraltester.controller;

/*
@ExtendWith(MockitoExtension::class)
class ClefControllerTest {
    @Mock
    private InternalNotationFactory internalNotationFactory;

    @Mock
    private AbcService abcService;

    @InjectMocks
    private ClefController clefController;
    private String clefString;
    private Clef clef;
    private String abc;

    @BeforeEach
    fun `set up`() {
        clefString = TestData.random.string
        clef = TestData.random.clef
        abc = TestData.random.string
    }

    @Test
    fun `gets clef information`() {
        val expected = ClefResponse(abc)
    `when`(internalNotationFactory.clef(clefString)).thenReturn(clef)
    `when`(abcService.getAbc(clef)).thenReturn(abc.let {
            object : AbcProvider {
                override val abc = it
            }
        })

        val actual = clefController.get(clefString)

        assertThat(actual).isEqualTo(expected)
    }
}

 */