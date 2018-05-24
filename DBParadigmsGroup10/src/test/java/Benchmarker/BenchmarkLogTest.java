package Benchmarker;

import Benchmarker.enums.DBMS;
import Benchmarker.enums.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.Matchers.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class BenchmarkLogTest {


    @Mock
    BenchmarkTimer timer;


    @Mock
    BenchmarkDuration a, b, c, d;


    BenchmarkLog BL;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        given(a.getDuration()).willReturn(Long.valueOf(100));
        given(b.getDuration()).willReturn(Long.valueOf(200));
        given(c.getDuration()).willReturn(Long.valueOf(300));
        given(d.getDuration()).willReturn(Long.valueOf(400));
        given(a.getTask()).willReturn("Task a");
        given(b.getTask()).willReturn("Task b");
        given(c.getTask()).willReturn("Task c");
        given(d.getTask()).willReturn("Task d");
        given(timer.getDurations()).willReturn(new BenchmarkDuration[]{a,b,c,d});

        BL = new BenchmarkLogImpl(DBMS.redis, Query.booksbycity, timer);
    }

    @Test
    void prepareMeForLoggingBehavior() {
        BL.PrepareMeForLogging();
        // Verify that timer calls to get all its durations
        verify(timer).getDurations();

        // Verifies that each duration gets asked about duration and what task it is twice for string and system outprint
        verify(a).getDuration();
        verify(a).getTask();
        verify(b).getDuration();
        verify(b).getTask();
        verify(c).getDuration();
        verify(c).getTask();
        verify(d).getDuration();
        verify(d).getTask();

    }

    @Test
    void getTimer() {
        assertThat(BL.GetTimer(), is(timer));
    }
}