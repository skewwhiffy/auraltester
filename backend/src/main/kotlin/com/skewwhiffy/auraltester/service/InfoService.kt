package com.skewwhiffy.auraltester.service

import com.skewwhiffy.auraltester.dto.InfoResponse
import com.skewwhiffy.auraltester.repository.InfoRepository
import org.springframework.stereotype.Service

@Service
class InfoService(private val infoRepository: InfoRepository) {
    fun get() = infoRepository.findAll()
        .first()
        .let { InfoResponse(it.version) }
}
