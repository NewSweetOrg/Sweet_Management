package com.sweetmanagement.bdd.steps;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "Features", // المسار الصحيح لملف .feature
        plugin = {"html:target/cucumber/wikipedia.html"},
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"com.sweetmanagement.bdd.steps"} // الحزمة الصحيحة التي تحتوي على خطوات BDD
)
public class acceptTest {
}
