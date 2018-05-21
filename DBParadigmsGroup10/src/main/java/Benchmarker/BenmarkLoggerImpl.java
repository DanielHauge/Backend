package Benchmarker;

import Benchmarker.enums.DBMS;
import Benchmarker.enums.Query;
import DataObjects.Fulllog;
import DataObjects.log;

import java.util.ArrayList;

public class BenmarkLoggerImpl implements BenmarkLogger {

    private ArrayList<log> logs = new ArrayList<>();
    private BenchmarkLogFactory BLF;
    private BenchmarkDurationFactory BDF;

    public BenmarkLoggerImpl(BenchmarkLogFactory BLF, BenchmarkDurationFactory BDF){
        this.BLF = BLF;
        this.BDF = BDF;
    }

    @Override
    public void Savelog(BenchmarkLog log) {
        System.out.println("Saving log");
        log save = log.PrepareMeForLogging();
        System.out.println(save);

        //File write and log outwards.
        logs.add(save);

    }

    @Override
    public BenchmarkLog CreateNewLog(Query q, DBMS db) {

        return BLF.CreateNewLog(db, q, BDF);
    }

    @Override
    public Fulllog PrintLog() {
        return new Fulllog(logs.toArray(new log[0]));
    }
}
