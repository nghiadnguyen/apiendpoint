package vn.homecredit.smt.api;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("vn.homecredit.smt.api");

        noClasses()
            .that()
            .resideInAnyPackage("vn.homecredit.smt.api.service..")
            .or()
            .resideInAnyPackage("vn.homecredit.smt.api.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..vn.homecredit.smt.api.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
