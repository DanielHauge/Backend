package Benchmarker;

import Benchmarker.enums.DBMS;
import Benchmarker.enums.Query;
import DataObjects.Fulllog;

public interface BenmarkLogger {

    void Savelog(BenchmarkLog log);

    BenchmarkLog CreateNewLog(Query q, DBMS db);

    Fulllog PrintLog();



}
