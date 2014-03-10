package org.nthdimenzion.object;

import org.nthdimenzion.object.utils.IIdGenerator;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 8/9/13
 * Time: 12:08 AM
 */
public class StubIdGenerator implements IIdGenerator {

    private final String id;

    public StubIdGenerator(String id) {
        this.id = id;
    }

    @Override
    public String nextId() {
        return id;
    }
}
