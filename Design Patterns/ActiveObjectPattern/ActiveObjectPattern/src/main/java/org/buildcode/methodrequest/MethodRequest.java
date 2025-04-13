package org.buildcode.methodrequest;

import org.buildcode.servant.ArithmeticServant;

public abstract class MethodRequest {
    protected final ArithmeticServant servant;

    public MethodRequest(ArithmeticServant servant) {
        this.servant = servant;
    }

    public abstract void execute();
}