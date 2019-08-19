import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ResponseHeaders extends BaseClass{

    @Test // To get content type
    public void getContentTypeFromHeaders() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        /*
         ContentType is always received in response header. e.g - "application/json; charset=utf-8"
         Media type and MIME type is type of response received. e.g - application/json,plain text,html etc
         */
        //Step 1 - to get content type from response
        Header contentType = response.getEntity().getContentType();
        Assert.assertEquals(contentType.getValue(),"application/json; charset=utf-8");
        //Step 2 - to get media type only in content type
        ContentType ct = ContentType.getOrDefault(response.getEntity());
        Assert.assertEquals(ct.getMimeType(),"application/json");
    }
    @Test //To get other headers
    public void serverIsGit() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);

        String headerValue = ResponseUtils.getResponseHeader (response,"Server");
        Assert.assertEquals(headerValue,"GitHub.com");
        boolean eTagPresent = ResponseUtils.isResponseHeaderPresent (response,"Etag");
        Assert.assertTrue(eTagPresent);
    }



}
