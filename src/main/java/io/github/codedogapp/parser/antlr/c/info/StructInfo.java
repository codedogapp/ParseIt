package io.github.codedogapp.parser.antlr.c.info;

import java.util.List;


public record StructInfo(
    String name,
    List<String> fields
) implements TypeInfo {

}
