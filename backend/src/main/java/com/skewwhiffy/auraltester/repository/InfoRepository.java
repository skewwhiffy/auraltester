package com.skewwhiffy.auraltester.repository;

import com.skewwhiffy.auraltester.dao.Info;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface InfoRepository extends CrudRepository<Info, UUID> {
}
