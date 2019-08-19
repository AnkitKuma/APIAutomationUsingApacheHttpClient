import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class Get200 extends BaseClass{



    @Test
    public void baseUrlReturns200() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);

        int actualStatus = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatus,200);
    }

    @Test
    public void rateLimitReturns200() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");
        response = client.execute(get);

        int actualStatus = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatus,200);
    }

    @Test
    public void repoSearchUrlReturns200() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/search/repositories?q=java");
        response = client.execute(get);

        int actualStatus = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatus,200);
    }


}
