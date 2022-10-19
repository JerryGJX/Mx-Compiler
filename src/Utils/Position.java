package Utils;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Position {
    private final int line, column;

    public Position() {
        line = 0;
        column = 0;
    }

    public Position(int line, int column) {
        this.line = line;
        this.column = column;
    }
    public Position(Token token) {
        this.line = token.getLine();
        this.column = token.getCharPositionInLine();
    }

    public Position(ParserRuleContext ctx) {
        this(ctx.getStart());
    }

    public int GetLine(){return line;}

    public int GetColumn(){return column;}

    public String toString() { return " in line:" + line + ", pos:" + column; }
}

