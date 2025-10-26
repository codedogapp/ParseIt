package io.github.codedogapp;

import io.github.codedogapp.parser.antlr.c.CVisitor;


public class Main {

    static void main(final String[] args) {
        final var code = """
                         #include <stdio.h>
                         
                         typedef struct {
                             int id;
                             char name[50];
                             double score;
                         } Student;
                         
                         void printStudent(Student s) {
                             printf("ID: %d\\n", s.id);
                             printf("Name: %s\\n", s.name);
                             printf("Score: %.2f\\n", s.score);
                         }
                         
                         int main() {
                             Student st = {1, "Alice", 92.5};
                             printStudent(st);
                             return 0;
                         }
                         """;

        final var result = CVisitor.FUNCTION.visit(code);
        IO.println("Functions: " + result);

        final var structResult = CVisitor.STRUCT.visit(code);
        IO.println("Structs: " + structResult);
    }

}
