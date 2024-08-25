package org.buildcode.proxy;

import org.buildcode.FutureResult;
import org.buildcode.methodrequest.AddRequest;
import org.buildcode.methodrequest.MethodRequest;
import org.buildcode.methodrequest.SubtractRequest;
import org.buildcode.scheduler.Scheduler;
import org.buildcode.servant.ArithmeticServant;

public class ArithmeticProxy {
    private final Scheduler scheduler;
    private final ArithmeticServant servant;

    public ArithmeticProxy(Scheduler scheduler, ArithmeticServant servant) {
        this.scheduler = scheduler;
        this.servant = servant;
    }

    public FutureResult<Integer> add(int a, int b) {
        FutureResult<Integer> futureResult = new FutureResult<>();
        MethodRequest methodRequest = new AddRequest(servant, 10, 20, futureResult);
        scheduler.enqueue(methodRequest);
        return futureResult;
    }

    public FutureResult<Integer> subtract(int a, int b) {
        FutureResult<Integer> futureResult = new FutureResult<>();
        MethodRequest methodRequest = new SubtractRequest(servant, 10, 20, futureResult);
        scheduler.enqueue(methodRequest);
        return futureResult;
    }
}