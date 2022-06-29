package ru.javawebinar.topjava;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.service.UserServiceTest;

import java.util.concurrent.TimeUnit;

public class TestWatch extends Stopwatch {
    public static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
    public static final StringBuilder sb = new StringBuilder();
    public static Long testTimeSpent = 0L;


    private void logInfo(Description description, String status, long nanos) {
        String testName = description.getMethodName();
        testTimeSpent += nanos;
        String msg = String.format("\n Test %-25s %7d",
                testName, TimeUnit.NANOSECONDS.toMillis(nanos));
        logger.info(msg);
        sb.append(msg);
    }

    @Override
    protected void succeeded(long nanos, Description description) {
        logInfo(description, "succeeded", nanos);
    }

//    @Override
//    protected void failed(long nanos, Throwable e, Description description) {
//        logInfo(description, "failed", nanos);
//    }

//    @Override
//    protected void finished(long nanos, Description description) {
//        logInfo(description, "finished", nanos);
//    }
}