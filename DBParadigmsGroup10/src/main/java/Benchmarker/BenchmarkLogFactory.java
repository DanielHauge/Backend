package Benchmarker;

import Benchmarker.enums.DBMS;
import Benchmarker.enums.Query;

public interface BenchmarkLogFactory {

    BenchmarkLog CreateNewLog(DBMS db, Query q, BenchmarkDurationFactory bdf);

}
