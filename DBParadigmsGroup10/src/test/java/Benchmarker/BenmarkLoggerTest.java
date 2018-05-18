package Benchmarker;

import Benchmarker.enums.DBMS;
import Benchmarker.enums.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

class BenmarkLoggerTest {

    @Mock BenchmarkLogFactory BLF;

    @Mock BenchmarkDurationFactory BDF;

    @Mock BenchmarkLog log;

    BenmarkLogger BL;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        BL = new BenmarkLoggerImpl(BLF, BDF);
        given(log.PrepareMeForLogging()).willReturn(anyString());
        given(BLF.CreateNewLog(any(), any(), BDF)).willReturn(log);
    }

    @Test
    void savelog() {
        BL.Savelog(log);
        verify(log).PrepareMeForLogging();

    }

    @Test
    void createNewLog() {
        BL.CreateNewLog(Query.booksbycity,DBMS.mongo);
        verify(BLF).CreateNewLog(DBMS.mongo,Query.booksbycity,BDF);
    }
}