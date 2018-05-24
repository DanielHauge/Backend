package Benchmarker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.*;

class BenchmarkTimerTest {


    @Mock
    BenchmarkDurationFactory bdf;

    @Mock BenchmarkDuration bd;

    final String hej1 = "hejsa";

    BenchmarkTimer sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        given(bdf.CreateNewDuration(hej1)).willReturn(bd);
        given(bd.getTask()).willReturn(hej1);

        sut = new BenchmarkTimerImpl(bdf);
    }

    @Test
    void start() {
        sut.start(hej1);
        verify(bdf).CreateNewDuration(hej1);
    }

    @Test
    void stop() throws InterruptedException {
        sut.start(hej1);
        sut.stop(hej1);
        verify(bd).getTask();
        verify(bd).addDuration(anyLong());
    }

    @Test
    void getDurations() throws InterruptedException {
        sut.start(hej1);
        sut.stop(hej1);
        assertThat(sut.getDurations().length, is(1));
    }
}