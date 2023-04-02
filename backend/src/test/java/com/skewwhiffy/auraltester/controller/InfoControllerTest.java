package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.dto.InfoResponse;
import com.skewwhiffy.auraltester.service.InfoService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InfoControllerTest {
    @Mock
    private InfoService infoService;
    @InjectMocks
    private InfoController infoController;

    @Test
    void getsInformation() {
        val expected = new InfoResponse("0.0.1-java");
        when(infoService.get()).thenReturn(expected);

        val actual = infoController.get();

        assertThat(actual).isEqualTo(expected);
    }
}