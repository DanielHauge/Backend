package Benchmarker;

import java.util.ArrayList;

public class BenchmarkTimerImpl implements BenchmarkTimer {

    ArrayList<BenchmarkDuration> BD;
    BenchmarkDurationFactory BDF;

    public BenchmarkTimerImpl(BenchmarkDurationFactory BDF){
        this.BDF = BDF;
        this.BD = new ArrayList<>();
    }

    @Override
    public void start(String task) {
        BD.add(BDF.CreateNewDuration(task));
    }

    @Override
    public void stop(String task) {
        long stop = System.currentTimeMillis();
        BD.forEach(dur -> {
            if (dur.getTask().equals(task)){
                dur.addDuration(stop);
            }
        });
    }

    @Override
    public BenchmarkDuration[] getDurations() {
        return this.BD.toArray(new BenchmarkDuration[0]);
    }
}
