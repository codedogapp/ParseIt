package io.github.codedogapp.utils;


import io.github.codedogapp.parser.antlr.c.CVisitor;


public class TestUtil {

    // Non-instantiable class
    private TestUtil() {
    }

    public static String readFileAsString(final String name) {
        try (final var inputStream = TestUtil.class.getResourceAsStream(name)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + name);
            }
            return new String(inputStream.readAllBytes());
        } catch (final Exception e) {
            throw new RuntimeException("Failed to read file: " + name, e);
        }
    }

    public record TestCase(
        String file,
        CVisitor visitor
    ) {

    }

}
