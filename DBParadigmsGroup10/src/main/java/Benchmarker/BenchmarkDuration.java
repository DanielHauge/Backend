package Benchmarker;

public interface BenchmarkDuration {

    long getDuration();

    void addDuration(long time);

    void removeDuration(long time);

}
