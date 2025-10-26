package io.github.codedogapp.parser.antlr.c;

import io.github.bakhomious.grammar.c.CLexer;
import io.github.bakhomious.grammar.c.CParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;


class CParseTreeBuilder {

    public static final CParseTreeBuilder INSTANCE = new CParseTreeBuilder();

    public CParser.CompilationUnitContext tree(final String c) {
        final var lexer = buildCLexer(c);
        final var parser = buildCParser(lexer);
        return buildTree(parser);
    }

    private CLexer buildCLexer(final String c) {
        return new CLexer(CharStreams.fromString(c));
    }

    private CParser buildCParser(final CLexer cLexer) {
        return new CParser(new CommonTokenStream(cLexer));
    }

    private CParser.CompilationUnitContext buildTree(final CParser cParser) {
        return cParser.compilationUnit();
    }

}
