package com.skewwhiffy.auraltester.cucumber

import com.skewwhiffy.auraltester.AuralTesterApplication
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource

// TODO: Report only reports a single feature
@RunWith(Cucumber::class)
@CucumberOptions(features = ["src/test/resources"], publish = true)
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = [RestRequests::class, AuralTesterApplication::class])
@AutoConfigureMockMvc
@ActiveProfiles(profiles = ["test"])
@TestPropertySource(locations = ["classpath:application-integration-test.properties"])
class CucumberIntegrationTest
