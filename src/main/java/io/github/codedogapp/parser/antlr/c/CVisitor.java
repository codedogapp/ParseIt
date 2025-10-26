package io.github.codedogapp.parser.antlr.c;

import io.github.bakhomious.grammar.c.CBaseVisitor;
import io.github.codedogapp.parser.antlr.c.info.TypeInfo;

import java.util.List;
import java.util.function.Supplier;


public enum CVisitor {

    FUNCTION(FunctionVisitor::new),
    STRUCT(StructVisitor::new);

    private final Supplier<CBaseVisitor<?>> visitorSupplier;

    CVisitor(final Supplier<CBaseVisitor<?>> visitorSupplier) {
        this.visitorSupplier = visitorSupplier;
    }

    @SuppressWarnings("unchecked")
    public <V extends TypeInfo> List<V> visit(final String code) {
        final var tree = CParseTreeBuilder.INSTANCE.tree(code);
        final var visitor = this.visitorSupplier.get();
        final var result = visitor.visit(tree);
        return (List<V>) result;
    }
}
