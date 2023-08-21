package com.skewwhiffy.auraltester.service;

import org.springframework.stereotype.Service

@Service
class QuestionService {
    /*
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final Collection<QuestionFactory<?>> questionFactories;
    private final QuestionRepository questionRepository;

    public QuestionResponse get(QuestionRequest request) {
        val questionType = request.type();
        val factory = questionFactories
            .stream()
            .filter(it -> it.getQuestionType() == questionType)
        .findFirst()
            .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR,
        MessageFormat.format(
            "No question factory for {0}.",
            questionType
        )
        ));
        val question = factory.getNewQuestion();
        try {
            val questionJson = objectMapper.writeValueAsString(question.toDao());
            val questionEntity = new QuestionDao(questionType, questionJson);
            val savedQuestionEntity = questionRepository.save(questionEntity);
            return QuestionResponse
                .builder()
                .questionId(savedQuestionEntity.getId())
                .elements(question.getQuestionElements())
                .answerTypes(question.getAnswerTypes())
                .build();
        } catch (JsonProcessingException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
            "Cannot serialize clef question"
            );
        }
    }

    public AnswerResponse answer(UUID id, List<String> answers) {
        val savedQuestionEntity = questionRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        MessageFormat.format(
            "Question with ID '{0}' not found.",
            id
        )
        ));
        val factory = questionFactories
            .stream()
            .filter(it -> it.getQuestionType() == savedQuestionEntity.getQuestionType())
        .findAny()
            .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR,
        MessageFormat.format(
            "No question factory for {0}.",
            savedQuestionEntity.getQuestionType()
        )
        ));
        return factory
            .getQuestion(savedQuestionEntity.getQuestion())
            .answer(answers);
    }
     */
}
