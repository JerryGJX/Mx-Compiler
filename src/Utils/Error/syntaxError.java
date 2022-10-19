package Utils.error;

import Utils.Position;

//语法错误
public class syntaxError extends  error{
    public syntaxError(String msg, Position pos) {
        super("SyntaxError" + msg, pos);
    }
}
