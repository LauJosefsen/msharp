package msharp;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.logging.Logger;

public class CustomAntlrErrorListener extends BaseErrorListener {
    private Logger logger;
    
    public CustomAntlrErrorListener (Logger logger)
    {
        this.logger = logger;
    }
    
    @Override
    public void syntaxError (Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
    {
        logger.info("[SYNTAX ERROR] " + msg + " at line " + line + " position " + charPositionInLine + ".");
    }
    
    //    @Override
    //    public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
    //        logger.info(msg);
    //    }
    //
    //    @Override
    //    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
    //        logger.info("reportAttemptingFullContext " + startIndex);
    //    }
    //
    //    @Override
    //    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
    //        logger.info("reportContextSensitivity "+startIndex);
    //    }
}
