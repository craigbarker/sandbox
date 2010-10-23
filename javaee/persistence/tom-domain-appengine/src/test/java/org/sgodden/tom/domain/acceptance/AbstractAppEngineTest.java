package org.sgodden.tom.domain.acceptance;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/**
 *
 * @author Simon Godden
 */
public class AbstractAppEngineTest {

    private static final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    protected void setUp() {
        helper.setUp();
    }

    protected void tearDown() {
        helper.tearDown();
    }
}
