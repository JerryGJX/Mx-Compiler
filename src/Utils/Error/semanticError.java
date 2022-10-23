package Utils.error;

import Utils.Position;

//语义错误
public class semanticError extends  error{
    public semanticError(String msg, Position pos) {
        super("[semanticError] " + msg, pos);
    }
}

