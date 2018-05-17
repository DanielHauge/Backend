package Benchmarker;

import Benchmarker.enums.DBMS;
import Benchmarker.enums.Query;

public class BenmarkLoggerImpl implements BenmarkLogger {
    @Override
    public void Savelog(BenchmarkLog log) {
        System.out.println("Saving log");
        String save = log.PrepareMeForLogging();
        System.out.println(save);

        //File write and log outwards.

    }

    @Override
    public BenchmarkLog CreateNewLog(Query q, DBMS db) {

        return new BenchmarkLogImpl(db, q, new BenchmarkTimerImpl(new BenchmarkDurationImpl()));
    }
}
