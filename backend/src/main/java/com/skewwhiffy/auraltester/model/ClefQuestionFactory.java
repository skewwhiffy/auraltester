package com.skewwhiffy.auraltester.model;

import com.skewwhiffy.auraltester.dao.ClefQuestionDao;
import com.skewwhiffy.auraltester.dto.question.QuestionType;
import com.skewwhiffy.auraltester.helper.CollectionHelper;
import com.skewwhiffy.auraltester.notation.factory.ClefFactory;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.service.AbcService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class ClefQuestionFactory extends QuestionFactory<ClefQuestionDao> {
    private final AbcService abcService;
    private final ClefFactory clefFactory;

    @Override
    public Question<ClefQuestionDao> getNewQuestion() {
        val clefType = CollectionHelper.oneOf(Arrays.stream(ClefType.values()).toList());
        val clef = clefFactory.get(clefType);
        val noteCandidates = AbsoluteNote.range(clef.lowLedgerNote(), clef.highLedgerNote());
        val note = CollectionHelper.oneOf(noteCandidates);
        return builder()
                .absoluteNote(note)
                .type(clefType)
                .build();
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.CLEF;
    }

    @Override
    Class<ClefQuestionDao> getDao() {
        return ClefQuestionDao.class;
    }

    @Override
    Question<ClefQuestionDao> getQuestion(ClefQuestionDao dao) {
        return builder()
                .type(dao.type())
                .absoluteNote(dao.absoluteNote().toModel())
                .build();
    }

    private ClefQuestion.ClefQuestionBuilder builder() {
        return ClefQuestion
                .builder()
                .abcService(abcService)
                .clefFactory(clefFactory);
    }
}
