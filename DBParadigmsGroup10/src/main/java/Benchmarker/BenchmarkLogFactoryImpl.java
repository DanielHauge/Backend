package Benchmarker;

import Benchmarker.enums.DBMS;
import Benchmarker.enums.Query;

public class BenchmarkLogFactoryImpl implements BenchmarkLogFactory {
    @Override
    public BenchmarkLog CreateNewLog(DBMS db, Query q, BenchmarkDurationFactory bdf) {
        return new BenchmarkLogImpl(db, q, new BenchmarkTimerImpl(bdf));
    }
}
