import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static entities.User.RATE;

public class BodyTestWithSimpleMap extends BaseClass {

    @Test
    public void rateLimitReturns200() throws IOException, JSONException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");
        response = client.execute(get);

        String jsBody = EntityUtils.toString(response.getEntity());
        JSONObject jsObj = new JSONObject(jsBody);

        JSONObject result =  (JSONObject) getJSONObjValue(jsObj,RATE);
        Integer output =  (Integer)getJSONObjValue(result,"limit");
        Assert.assertEquals(output,Integer.valueOf(60));
    }

    private Object getJSONObjValue(JSONObject jsObj, String outerKey) throws JSONException {

        return jsObj.get(outerKey);
    }


}
