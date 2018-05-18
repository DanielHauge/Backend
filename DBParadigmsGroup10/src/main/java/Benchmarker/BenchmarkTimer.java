package Benchmarker;

public interface BenchmarkTimer {

    void start(String task);

    void stop(String task);

    BenchmarkDuration[] getDurations();

}
