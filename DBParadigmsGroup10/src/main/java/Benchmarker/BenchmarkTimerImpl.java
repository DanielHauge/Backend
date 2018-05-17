package Benchmarker;

public class BenchmarkTimerImpl implements BenchmarkTimer {

    BenchmarkDuration BD;
    private long now;
    private long after;

    public BenchmarkTimerImpl(BenchmarkDuration bd){
        this.BD = bd;
    }

    @Override
    public void start() {
        this.now = System.currentTimeMillis();
    }

    @Override
    public void stop() {
        this.after = System.currentTimeMillis();
        BD.addDuration(after-now);
    }

    @Override
    public BenchmarkDuration getDuration() {
        return this.BD;
    }
}
