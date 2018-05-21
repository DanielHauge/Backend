package Benchmarker;

import DataObjects.log;

public interface BenchmarkLog {

    log PrepareMeForLogging();

    BenchmarkTimer GetTimer();

}
