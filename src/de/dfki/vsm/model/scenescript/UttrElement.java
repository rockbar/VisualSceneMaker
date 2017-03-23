package de.dfki.vsm.model.scenescript;

/**
 * @author Gregor Mehlmann
 */
public abstract class UttrElement extends ScriptEntity {

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public UttrElement() {}

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public UttrElement(final int lower, final int upper) {

        // Initialize Boundary
        super(lower, upper);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public abstract UttrElement getCopy();
}