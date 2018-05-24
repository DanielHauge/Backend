package Benchmarker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class BenchmarkDurTest {

    BenchmarkDuration BD;

    @BeforeEach
    void setUp() {
        BD = new BenchmarkDurationImpl("Test task");
    }

    @Test
    void getDuration() {
        assertThat((int)BD.getDuration(), is(0));
    }

    @Test
    void addDuration() {
        long time = System.currentTimeMillis();
        // This is based on getting system.currenttime and such. This works in practise, but is hard to simulate, with strict timing tests.
        BD.addDuration(time+150);
        BD.addDuration(time+50+150);
        assertThat((int)BD.getDuration(), is(200));
    }

    @Test
    void reset() {
        BD.addDuration(50);
        BD.addDuration(16543);
        BD.removeDuration(321);
        BD.reset();
        assertThat(BD.getDuration(), is((long)0));
    }

    @Test
    void removeDuration() {
        // This is based on getting system.currenttime and such. This works in practise, but is hard to simulate, with strict timing tests.
        BD.addDuration(150);
        BD.removeDuration(50);
        assertThat((int)BD.getDuration(), is(100));
    }

    @Test
    void getTask() {
        assertThat(BD.getTask(), is("Test task"));
    }
}