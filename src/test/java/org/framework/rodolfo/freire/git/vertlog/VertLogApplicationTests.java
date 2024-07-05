package org.framework.rodolfo.freire.git.vertlog;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;


/**
 * This class represents a test suite for the VertLog application.
 * It uses the {@link Suite}, {@link SuiteDisplayName} and {@link SelectClasses} annotations
 * to define and organize the unit tests that will be executed together.
 */

@Suite
@SuiteDisplayName("VertLog Test Suite")
@SelectClasses({
        BuyControllerTest.class,
        BuyServiceTest.class
})
class VertLogApplicationTests {

}
