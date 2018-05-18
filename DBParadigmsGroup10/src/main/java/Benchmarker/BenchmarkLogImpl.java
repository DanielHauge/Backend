package Benchmarker;

import Benchmarker.enums.DBMS;
import Benchmarker.enums.Query;

public class BenchmarkLogImpl implements BenchmarkLog {

    private DBMS dbms;
    private Query q;
    private BenchmarkTimer timer;

    public BenchmarkLogImpl(DBMS dbms, Query q, BenchmarkTimer timer){
        this.dbms = dbms;
        this.q = q;
        this.timer = timer;
    }

    @Override
    public String PrepareMeForLogging() {
        String readyforlogging = "";
        System.out.println("Database Type:\t"+dbms.name());
        readyforlogging += "Database Type:\t"+dbms.name()+"\n";
        System.out.println("Query Type:\t"+q.name());
        readyforlogging += "Query Type:\t"+q.name()+"\n";
        System.out.print("Durations: \t");
        readyforlogging += "Durations: \t";
        long Fulldur = 0;
        for (BenchmarkDuration benchmarkDuration : timer.getDurations()) {
            long dur = benchmarkDuration.getDuration();
            String task = benchmarkDuration.getTask();
            System.out.print(task+": "+dur+"\t");
            readyforlogging += task+": "+dur+"\t";
        }
        System.out.print("INALL: "+Fulldur);
        readyforlogging += "INALL: "+Fulldur;
        System.out.println();
        readyforlogging += "\n";
        return readyforlogging;
    }

    @Override
    public BenchmarkTimer GetTimer() {
        return this.timer;
    }
}
