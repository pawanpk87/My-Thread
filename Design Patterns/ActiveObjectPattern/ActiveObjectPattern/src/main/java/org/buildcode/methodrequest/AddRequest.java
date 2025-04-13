package org.buildcode.methodrequest;

import org.buildcode.servant.ArithmeticServant;
import org.buildcode.FutureResult;

public class AddRequest extends MethodRequest {
    private final int a, b;
    private final FutureResult<Integer> futureResult;

    public AddRequest(ArithmeticServant servant, int a, int b, FutureResult<Integer> futureResult) {
        super(servant);
        this.a = a;
        this.b = b;
        this.futureResult = futureResult;
    }

    @Override
    public void execute() {
        int result = servant.add(a, b);
        futureResult.setResult(result);
    }
}