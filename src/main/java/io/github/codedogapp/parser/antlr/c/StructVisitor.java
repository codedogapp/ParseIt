package io.github.codedogapp.parser.antlr.c;


import io.github.bakhomious.grammar.c.CBaseVisitor;
import io.github.bakhomious.grammar.c.CParser;
import io.github.codedogapp.parser.antlr.c.info.StructInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


class StructVisitor extends CBaseVisitor<List<StructInfo>> {

    private final List<StructInfo> structs = new ArrayList<>();

    @Override
    public List<StructInfo> visitStructOrUnionSpecifier(
        final CParser.StructOrUnionSpecifierContext ctx
    ) {
        final var identifier = ctx.Identifier();
        final String name;
        if (identifier != null) {
            name = identifier.getText();
        } else {
            // Anonymous struct, have to traverse up
            name = extractTypedefName(ctx);
        }
        final var declarationList = ctx.structDeclarationList()
            .structDeclaration()
            .stream()
            .flatMap(s -> s.structDeclaratorList().structDeclarator().stream())
            .map(sd -> sd.declarator().getText())
            .toList();
        structs.add(new StructInfo(name, declarationList));
        return structs;
    }

    private String extractTypedefName(final CParser.StructOrUnionSpecifierContext ctx) {
        final var declarationSpecifiersCtx = traverseToDeclarationSpecifiers(ctx);

        final var name = declarationSpecifiersCtx.declarationSpecifier()
            .stream()
            .map(CParser.DeclarationSpecifierContext::typeSpecifier)
            .filter(Objects::nonNull)
            .filter(ts -> ts.typedefName() != null)
            .map(ts -> ts.typedefName().getText())
            .findFirst()
            .orElse(null);

        return name;
    }

    private static CParser.DeclarationSpecifiersContext traverseToDeclarationSpecifiers(
        final CParser.StructOrUnionSpecifierContext ctx
    ) {
        var parent = ctx.getParent();
        while (
            parent != null
                && !(parent instanceof CParser.DeclarationSpecifiersContext)
        ) {
            parent = parent.getParent();
        }
        return (CParser.DeclarationSpecifiersContext) parent;
    }

    @Override
    protected List<StructInfo> defaultResult() {
        return structs;
    }

}
