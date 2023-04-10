package com.skewwhiffy.auraltester.model;

import com.skewwhiffy.auraltester.dao.ClefQuestionDao;
import com.skewwhiffy.auraltester.dto.question.*;
import com.skewwhiffy.auraltester.exception.Todo;
import com.skewwhiffy.auraltester.notation.factory.ClefFactory;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.service.AbcService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.val;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Builder
@AllArgsConstructor
public final class ClefQuestion extends Question<ClefQuestionDao> {
    private final AbcService abcService;
    private final ClefFactory clefFactory;
    private final ClefType type;
    private final AbsoluteNote absoluteNote;

    @Override
    public ClefQuestionDao toDao() {
        return new ClefQuestionDao(type, absoluteNote.toDao());
    }

    @Override
    public List<QuestionResponseElement> getQuestionElements() {
        val abc = abcService.getAbc(clefFactory.get(type), absoluteNote).getAbc();
        return Arrays.asList(
                new TextQuestionResponseElement("What is the name of this note?"),
                new AbcQuestionResponseElement(abc)
        );
    }

    @Override
    public List<AnswerType> getAnswerTypes() {
        return Collections.singletonList(AnswerType.NOTE_NAME);
    }

    @Override
    public List<QuestionResponseElement> getCorrectResponse() {
        throw new Todo();
    }

    @Override
    public List<QuestionResponseElement> getIncorrectResponse() {
        throw new Todo();
    }

    @Override
    public List<String> getAnswer() {
        return Collections.singletonList(absoluteNote.note().noteName());
    }

}
