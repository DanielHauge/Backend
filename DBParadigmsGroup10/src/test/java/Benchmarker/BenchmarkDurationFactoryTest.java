package Benchmarker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BenchmarkDurationFactoryTest {

    BenchmarkDurationFactory BDF;

    @BeforeEach
    void setUp() {
        BDF = new BenchmarkDurationFactoryImpl();
    }

    @Test
    void createNewDuration() {
        BenchmarkDuration bd1 = BDF.CreateNewDuration("task1");
        assertThat(bd1.getTask(), is("task1"));
    }
}