package com.sweetmanagement.bdd.steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "user_stories",
        plugin = { "summary", "html:target/cucumber/wikipedia.html"},
        monochrome = true,
        snippets = SnippetType.CAMELCASE,
        glue = {"com.sweet"}
)
public class AcceptanceTest {
}
