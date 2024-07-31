package com.sweetmanagement.bdd.steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "Features",
        plugin = { "html:target/cucumber/wikipedia.html"},
        monochrome = true,
        snippets = SnippetType.CAMELCASE,
        glue = {"com.sweetmanagement.bdd.steps"}
)
public class AcceptanceTest {
}
