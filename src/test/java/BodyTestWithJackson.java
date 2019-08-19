import entities.NotFound;
import entities.RateLimit;
import entities.User;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class BodyTestWithJackson extends BaseClass{

    @Test
    public void checksCorrectLoginAndId() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT+ "/users/andrejss88");
        response = client.execute(get);

        User user = ResponseUtils.unmarshall(response,User.class);

        Assert.assertEquals(user.getLogin(),"andrejss88");
        Assert.assertEquals(user.getId(),"11834443");
    }

    @Test
    public void checksNotFoundMessage() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT+ "/nonexistingendpoint");
        response = client.execute(get);

        NotFound notfound = ResponseUtils.unmarshall(response, NotFound.class);

        Assert.assertEquals(notfound.getMessage(),"Not Found");

    }

    @Test
    public void checksRateLimits() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT+ "/rate_limit");
        response = client.execute(get);

        RateLimit rateLimit = ResponseUtils.unmarshall(response, RateLimit.class);

        Assert.assertEquals(rateLimit.getCoreLimit(),60);
        Assert.assertEquals(rateLimit.getSearchLimit(),10);

    }



}
