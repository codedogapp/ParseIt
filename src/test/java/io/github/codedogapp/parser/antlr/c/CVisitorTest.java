package io.github.codedogapp.parser.antlr.c;

import io.github.codedogapp.parser.antlr.c.info.FunctionInfo;
import io.github.codedogapp.parser.antlr.c.info.StructInfo;
import io.github.codedogapp.parser.antlr.c.info.TypeInfo;
import io.github.codedogapp.utils.TestUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


class CVisitorTest {

    private static final String ANON_STRUCT_FILE = "/c/anon_struct.c";
    private static final String NAMED_STRUCT_FILE = "/c/named_struct.c";
    private static final String FUNC_FILE = "/c/functions.c";

    private static final Map<String, List<TypeInfo>> TEST_DATA = Map.of(
        FUNC_FILE,
        List.of(
            new FunctionInfo("printStudent(Students)"),
            new FunctionInfo("main()")
        ),

        NAMED_STRUCT_FILE,
        List.of(
            new StructInfo(
                "Subject",
                List.of("title[100]", "credits")
            )
        ),

        ANON_STRUCT_FILE,
        List.of(
            new StructInfo(
                "Student",
                List.of("id", "name[50]", "score")
            )
        )
    );

    private static Stream<TestUtil.TestCase> provideTestCases() {
        return Stream.of(
            new TestUtil.TestCase(FUNC_FILE, CVisitor.FUNCTION),
            new TestUtil.TestCase(NAMED_STRUCT_FILE, CVisitor.STRUCT),
            new TestUtil.TestCase(ANON_STRUCT_FILE, CVisitor.STRUCT)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testVisitors(final TestUtil.TestCase testCase) {
        final var code = TestUtil.readFileAsString(testCase.file());
        final var expected = TEST_DATA.get(testCase.file());
        final var actual = testCase.visitor().visit(code);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected, actual);
    }

}
