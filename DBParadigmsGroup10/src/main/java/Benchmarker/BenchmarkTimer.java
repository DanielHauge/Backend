package Benchmarker;

public interface BenchmarkTimer {

    void start();

    void stop();

    BenchmarkDuration getDuration();

}
