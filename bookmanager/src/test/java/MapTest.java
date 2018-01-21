/**
 * Created by dela on 1/21/18.
 */

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapTest {
    public static void main(String[] args) throws JSONException {
        JSONObject temp = new JSONObject();
        temp.put("name", "zhuyidi");
        temp.put("sex", "f");

        System.out.println(temp);
    }
}
