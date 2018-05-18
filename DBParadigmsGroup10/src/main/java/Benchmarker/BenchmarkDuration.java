package Benchmarker;

public interface BenchmarkDuration {

    long getDuration();

    void addDuration(long time);

    void reset();

    void removeDuration(long time);

    String getTask();

}
