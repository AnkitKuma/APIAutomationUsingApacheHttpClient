import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Get401 extends BaseClass {


    @DataProvider
    private Object[][] endpoints(){
        return new Object[][]{
                {"/user"},
                {"/user/followers"},
                {"/notifications"}
        };
    }

    @Test(dataProvider = "endpoints")
    public void baseUrlReturns401(String endpoint) throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT+ endpoint);
        response = client.execute(get);

        int actualStatus = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatus,401);
    }


}
