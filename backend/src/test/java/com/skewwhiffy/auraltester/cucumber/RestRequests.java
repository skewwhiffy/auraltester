package com.skewwhiffy.auraltester.cucumber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class RestRequests {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final MockMvc mockMvc;
    private final List<MvcResult> responses = new ArrayList<>();

    public void makeGetRequest(String path) {
        val request = MockMvcRequestBuilders
                .get(path)
                .accept(MediaType.APPLICATION_JSON);
        try {
            val response = mockMvc.perform(request);
            response.andDo(responses::add);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    public MockHttpServletResponse getLastResponse() {
        return responses.get(responses.size() - 1).getResponse();
    }

    public Map<String, Object> getLastResponseAs() {
        try {
            //noinspection unchecked
            return objectMapper
                    .readValue(getLastResponse().getContentAsString(), Map.class);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
