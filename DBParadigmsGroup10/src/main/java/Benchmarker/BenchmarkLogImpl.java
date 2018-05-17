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
        System.out.println(dbms.name());
        System.out.println(q.name());
        System.out.println(timer.getDuration().getDuration());
        return "hej";
    }

    @Override
    public BenchmarkTimer GetTimer() {
        return this.timer;
    }
}
