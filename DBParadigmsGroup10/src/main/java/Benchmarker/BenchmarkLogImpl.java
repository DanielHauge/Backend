package Benchmarker;

import Benchmarker.enums.DBMS;
import Benchmarker.enums.Query;
import DataObjects.BMTask;
import DataObjects.log;

import java.util.ArrayList;

public class BenchmarkLogImpl implements BenchmarkLog {

    private final DBMS dbms;
    private final Query q;
    private final BenchmarkTimer timer;

    public BenchmarkLogImpl(DBMS dbms, Query q, BenchmarkTimer timer){
        this.dbms = dbms;
        this.q = q;
        this.timer = timer;
    }

    @Override
    public log PrepareMeForLogging() {
        System.out.println("Database Type:\t"+dbms.name());
        System.out.println("Query Type:\t"+q.name());
        System.out.print("Durations: \t");
        long Fulldur = 0;
        ArrayList<BMTask> tasks = new ArrayList<>();
        for (BenchmarkDuration benchmarkDuration : timer.getDurations()) {
            long dur = benchmarkDuration.getDuration();
            String task = benchmarkDuration.getTask();
            System.out.print(task+": "+dur+"\t");
            tasks.add(new BMTask(task, (int)dur));
            Fulldur += dur;
        }
        System.out.print("INALL: "+Fulldur);
        System.out.println();
        return new log(dbms.name(), q.name(), tasks.toArray(new BMTask[0]), (int)Fulldur);
    }

    @Override
    public BenchmarkTimer GetTimer() {
        return this.timer;
    }
}
