package Benchmarker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class BenchmarkDurationTest {

    BenchmarkDuration BD;

    @BeforeEach
    void setUp() {
        BD = new BenchmarkDurationImpl();
    }


    @Test
    void getDuration() {
        assertThat((int)BD.getDuration(), is(0));
    }

    @Test
    void addDuration() {
        BD.addDuration(150);
        BD.addDuration(50);
        assertThat((int)BD.getDuration(), is(200));
    }

    @Test
    void removeDuration() {
        BD.addDuration(150);
        BD.removeDuration(50);
        assertThat((int)BD.getDuration(), is(100));
    }
}