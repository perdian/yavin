package de.perdian.yavin.backend.application;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

@AnalyzeClasses(packages = "de.perdian.yavin")
public class ArchitectureTest {

    @ArchTest
    void controllersMustNotAccessRepositories(JavaClasses classes) {
        ArchRuleDefinition
            .noClasses().that().areAnnotatedWith(Controller.class)
            .should().dependOnClassesThat().areAnnotatedWith(Repository.class)
            .check(classes);
    }

}
