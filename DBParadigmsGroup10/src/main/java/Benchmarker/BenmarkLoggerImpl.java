package Benchmarker;

import Benchmarker.enums.DBMS;
import Benchmarker.enums.Query;

public class BenmarkLoggerImpl implements BenmarkLogger {

    private String wholeLog;
    private BenchmarkLogFactory BLF;
    private BenchmarkDurationFactory BDF;

    public BenmarkLoggerImpl(BenchmarkLogFactory BLF, BenchmarkDurationFactory BDF){
        this.BLF = BLF;
        this.BDF = BDF;
    }

    @Override
    public void Savelog(BenchmarkLog log) {
        System.out.println("Saving log");
        String save = log.PrepareMeForLogging();
        System.out.println(save);

        //File write and log outwards.
        wholeLog += save;

    }

    @Override
    public BenchmarkLog CreateNewLog(Query q, DBMS db) {

        return BLF.CreateNewLog(db, q, BDF);
    }

    @Override
    public String PrintLog() {
        return wholeLog;
    }
}
