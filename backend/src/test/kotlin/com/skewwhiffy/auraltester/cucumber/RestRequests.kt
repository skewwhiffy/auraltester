package com.skewwhiffy.auraltester.cucumber

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@Component
class RestRequests(
    private val mockMvc: MockMvc, private val responses: MutableList<MvcResult>
) {
    fun makeGetRequest(path: String) {
        val request = MockMvcRequestBuilders
            .get(path)
            .accept(MediaType.APPLICATION_JSON)
        mockMvc.perform(request).andDo { e: MvcResult -> responses.add(e) }
    }

    fun makePostRequest(path: String, payload: Any?) {
        val content = objectMapper.writeValueAsString(payload)
        val request = MockMvcRequestBuilders
            .post(path)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(content)
        mockMvc.perform(request).andDo { e: MvcResult -> responses.add(e) }
    }

    val lastResponse: MockHttpServletResponse
        get() = responses[responses.size - 1].response

    @Suppress("UNCHECKED_CAST")
    val lastResponseAsMap: Map<String, Any>
        get() = (objectMapper.readValue(lastResponse.contentAsString, Map::class.java)
            ?: throw RuntimeException()) as Map<String, Any>

    companion object {
        private val objectMapper = ObjectMapper()
    }
}
