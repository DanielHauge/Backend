package Benchmarker;

public class BenchmarkDurationImpl implements BenchmarkDuration {

    long dur;

    public BenchmarkDurationImpl(){
        this.dur = 0;
    }

    @Override
    public long getDuration() {
        return this.dur;
    }

    @Override
    public void addDuration(long time) {
        this.dur += time;
    }

    @Override
    public void removeDuration(long time) {
        this.dur -=time;
    }
}
