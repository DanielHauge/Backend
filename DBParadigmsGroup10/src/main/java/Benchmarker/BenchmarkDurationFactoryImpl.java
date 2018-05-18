package Benchmarker;

public class BenchmarkDurationFactoryImpl implements BenchmarkDurationFactory {
    @Override
    public BenchmarkDuration CreateNewDuration(String task) {
        return new BenchmarkDurationImpl(task);
    }
}
