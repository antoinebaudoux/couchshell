package org.gradle

import org.junit.Test
import static org.junit.Assert.*

class ShellTest {
    @Test public void canConstructAPerson() {
    }

    @Test public void canConstructAPersonUsingName() {
    }

    @Test public void usingCorrectVersionOfGroovy() {
        assertEquals('2.0.5', GroovySystem.version)
    }
    
    @Test public void testResourcesAreAvailable() {
        assertNotNull(getClass().getResource('/testResource.txt'))
        assertNotNull(getClass().getResource('/testScript.groovy'))
    }
}
