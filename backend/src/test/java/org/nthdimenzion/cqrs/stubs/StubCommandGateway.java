package org.nthdimenzion.cqrs.stubs;

import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 15/8/13
 * Time: 9:42 PM
 */
public class StubCommandGateway implements CommandGateway {
    @Override
    public <R> void send(Object command, CommandCallback<R> callback) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <R> R sendAndWait(Object command) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <R> R sendAndWait(Object command, long timeout, TimeUnit unit) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void send(Object command) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
