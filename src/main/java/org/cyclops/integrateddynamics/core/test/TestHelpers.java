package org.cyclops.integrateddynamics.core.test;

import org.apache.http.util.Asserts;
import org.cyclops.integrateddynamics.command.CommandTest;

import java.util.Objects;

/**
 * Helpers for tests
 * @author rubensworks
 */
public class TestHelpers {

    public static boolean canRunIntegrationTests() {
        try {
            Class.forName(CommandTest.CLASSES.get(0));
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Assertion for equal objects.
     * @param actual Actual value.
     * @param expected Expected value.
     * @param ifEqual Message identifying the assertion.
     * @param <T> The type.
     */
    public static <T> void assertEqual(T actual, T expected, String ifEqual) {
        try {
            if(actual instanceof Double) {
                Asserts.check(((Double) actual - (Double) expected) < 0.0001D, ifEqual);
            } else if(actual instanceof Float) {
                Asserts.check(((Float) actual - (Float) expected) < 0.0001F, ifEqual);
            } else {
                Asserts.check(Objects.equals(actual, expected), ifEqual);
            }
        } catch (IllegalStateException e) {
            throw new AssertionError(String.format("Failure: %s. Expected %s, but got %s.", ifEqual, expected, actual));
        }
    }

}
