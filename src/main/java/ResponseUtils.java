import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ResponseUtils {

    public static String getResponseHeader(CloseableHttpResponse response, String header) {

        Header[] headers = response.getAllHeaders();
        List<Header> httpHeaders = Arrays.asList(headers);
        String result = "";
        for (Header obj: httpHeaders){
            if (obj.getName().equalsIgnoreCase(header)){
                result = obj.getValue();
            }
        }

        if (result.isEmpty()) {
            throw new RuntimeException("Header is empty");
        }

        return result;
    }

    public static boolean isResponseHeaderPresent(CloseableHttpResponse response, String header) {

        Header[] headers = response.getAllHeaders();
        List<Header> httpHeaders = Arrays.asList(headers);
        boolean result = false;
        for (Header obj: httpHeaders){
            if (obj.getName().equalsIgnoreCase(header)){
                result = true;
                break;
            }
        }
        return result;
    }

    public static <T> T unmarshall(CloseableHttpResponse response, Class<T> userClass) throws IOException {

        //unmarshall - json obj to java object
        String jsonBody = EntityUtils.toString(response.getEntity());
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
                .readValue(jsonBody,userClass);
    }

}
