package Benchmarker;

import Benchmarker.enums.DBMS;
import Benchmarker.enums.Query;

public interface BenmarkLogger {

    void Savelog(BenchmarkLog log);

    BenchmarkLog CreateNewLog(Query q, DBMS db);

    String PrintLog();



}
