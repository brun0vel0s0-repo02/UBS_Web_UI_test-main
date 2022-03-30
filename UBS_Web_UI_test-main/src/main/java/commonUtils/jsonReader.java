package commonUtils;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class jsonReader {
    public String readData(String Key) throws IOException, IOException, ParseException {
        JSONParser js = new JSONParser();
        FileReader file = new FileReader("src/main/resources/paramData.json");
        Object obj = js.parse(file);
        JSONObject jsob = (JSONObject) obj;
        String value = jsob.get(Key).toString();
        return value;
    }
}
