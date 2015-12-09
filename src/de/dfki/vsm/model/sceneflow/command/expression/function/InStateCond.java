package de.dfki.vsm.model.sceneflow.command.expression.function;

//~--- non-JDK imports --------------------------------------------------------
import de.dfki.vsm.model.sceneflow.command.expression.AbstractExpression;
import de.dfki.vsm.util.ios.IOSIndentWriter;

import org.w3c.dom.Element;

/**
 *
 * @author Gregor Mehlmann
 */
public class InStateCond extends AbstractExpression {

    String mState;

    public InStateCond() {
    }

    public InStateCond(String state) {
        mState = state;
    }

    public String getState() {
        return mState;
    }

    @Override
    public ExpType getExpType() {
        return ExpType.STATE;
    }

    @Override
    public String getAbstractSyntax() {
        return "In( " + mState + " )";
    }

    public String getConcreteSyntax() {
        return "In( " + mState + " )";
    }

    @Override
    public String getFormattedSyntax() {
        return "In( " + mState + " )";
    }

    @Override
    public InStateCond getCopy() {
        return new InStateCond(mState);
    }

    @Override
    public void writeXML(IOSIndentWriter out) {
        out.println("<StateCondition state=\"" + mState + "\"/>");
    }

    @Override
    public void parseXML(Element element) {
        mState = element.getAttribute("state");
    }
}
