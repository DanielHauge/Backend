package DataObjects.Json;

import com.google.gson.Gson;

public class DataSerializer implements JsonSerializer {

    public String SerializeToJson() {
        return new Gson().toJson(this);
    }

}
