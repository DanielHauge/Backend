package DataObjects.Json;

import DataObjects.City;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class DataSerializerTest {

    final City testdataobject = new City(1, "Copenhagen");

    @Test
    void serializeToJson() {
        String json = testdataobject.SerializeToJson();
        assertThat(json, is("{\"id\":1,\"cityName\":\"Copenhagen\"}"));
    }
}