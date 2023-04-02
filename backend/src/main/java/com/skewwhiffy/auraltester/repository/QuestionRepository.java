package com.skewwhiffy.auraltester.repository;

import com.skewwhiffy.auraltester.dao.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QuestionRepository extends CrudRepository<Question, UUID> {
}
