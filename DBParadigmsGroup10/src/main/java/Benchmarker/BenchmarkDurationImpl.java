package Benchmarker;

public class BenchmarkDurationImpl implements BenchmarkDuration {

    final String task;
    long start;
    long end;

    public BenchmarkDurationImpl(String task){
        this.start = System.currentTimeMillis();
        this.end = System.currentTimeMillis();
        this.task = task;
    }

    @Override
    public long getDuration() {
        return this.end-this.start;
    }

    @Override
    public void addDuration(long time) {
        this.end += time-this.end;
    }



    @Override
    public void reset() {
        this.start = System.currentTimeMillis();
        this.end = System.currentTimeMillis();
    }

    @Override
    public void removeDuration(long time) {
        this.end -=time-this.end;
    }


    @Override
    public String getTask() {
        return this.task;
    }
}
