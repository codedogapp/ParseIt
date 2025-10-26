package io.github.codedogapp.parser.antlr.c;

import io.github.bakhomious.grammar.c.CBaseVisitor;
import io.github.bakhomious.grammar.c.CParser;
import io.github.codedogapp.parser.antlr.c.info.FunctionInfo;

import java.util.ArrayList;
import java.util.List;


class FunctionVisitor extends CBaseVisitor<List<FunctionInfo>> {

    private final List<FunctionInfo> functions = new ArrayList<>();

    @Override
    public List<FunctionInfo> visitFunctionDefinition(final CParser.FunctionDefinitionContext ctx) {
        final var name = ctx.declarator().getText();
        functions.add(new FunctionInfo(name));
        return functions;
    }

    @Override
    protected List<FunctionInfo> defaultResult() {
        return functions;
    }

}
