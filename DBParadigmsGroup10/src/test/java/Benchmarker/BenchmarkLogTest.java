package Benchmarker;

import Benchmarker.enums.DBMS;
import Benchmarker.enums.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BenchmarkLogTest {


    //Mock Timer
    BenchmarkTimer timer;

    BenchmarkLog BL;

    @BeforeEach
    void setup(){
        BL = new BenchmarkLogImpl(DBMS.redis, Query.bookbyauthor, timer);
    }

    @Test
    void prepareMeForLogging() {
    }

    @Test
    void getTimer() {
    }
}