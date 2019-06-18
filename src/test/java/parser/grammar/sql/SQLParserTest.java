package parser.grammar.sql;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

class SQLParserTest {

    @Test
    void testAntlrParse() throws IOException {
        FileInputStream stream =
            new FileInputStream("/Users/geoffreymoller/Code/database/src/test/java/parser/grammar/sql/test_sql.sql");
        ANTLRInputStream antlr = new ANTLRInputStream(stream);
        SQLLexer lexer = new SQLLexer(antlr);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SQLParser parser = new SQLParser(tokens);
        SQLParser.ParseContext tree = parser.parse();
        System.out.println(tree.toString());
    }
}