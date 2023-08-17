package com.skewwhiffy.auraltester.repository;

import com.skewwhiffy.auraltester.dao.QuestionDao;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QuestionRepository extends CrudRepository<QuestionDao, UUID> {
}
