package com.gerefi.tools.online;

import com.devexperts.logging.Logging;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static com.devexperts.logging.Logging.getLogging;

public class HttpUtil {
    private static final Logging log = getLogging(Logging.class);

    // todo: migrate proxy http json API server to TLS
    public static final String gerefi_PROXY_JSON_PROTOCOL = "http://";
    public static final int PROXY_JSON_API_HTTP_PORT = getIntProperty("http.port", 8001);

    /**
     * hostname of PROXY server, not primary gerefi web server - those are two separate hosts at the moment
     */
    public static String gerefi_PROXY_HOSTNAME = System.getProperty("gerefi_PROXY_URL", "proxy.gerefi.com");

    public static String gerefi_ONLINE_JSON_API_PREFIX = "https://gerefi.com/online/api.php?method=";

    public static <T> T getJsonResponse(String responseString) throws ParseException {

        JSONParser parser = new JSONParser();
        return (T) parser.parse(responseString);
    }

    public static String getResponse(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        log.info("responseString=" + responseString);
        return responseString;
    }

    public static String executeGet(String url) throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpParams httpParameters = httpclient.getParams();
//        HttpConnectionParams.setConnectionTimeout(httpParameters, CONNECTION_TIMEOUT);
//        HttpConnectionParams.setSoTimeout(httpParameters, WAIT_RESPONSE_TIMEOUT);
        // without this magic http response is pretty slow
        HttpConnectionParams.setTcpNoDelay(httpParameters, true);
        log.info("GET " + url);
        HttpGet httpget = new HttpGet(url);

        // in case of emergency
        //  -Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.SimpleLog -Dorg.apache.commons.logging.simplelog.showdatetime=true -Dorg.apache.commons.logging.simplelog.log.org.apache.http=DEBUG -Dorg.apache.commons.logging.simplelog.log.org.apache.http.wire=ERROR
        try {
            HttpResponse httpResponse = httpclient.execute(httpget);
            return HttpUtil.getResponse(httpResponse);
        } finally {
            httpget.releaseConnection();
        }
    }

    public static JSONObject parse(String jsonString) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) parser.parse(jsonString);
        } catch (ParseException e) {
            throw new IllegalStateException(e);
        }
        return jsonObject;
    }

    public static int getIntProperty(String propertyName, int defaultValue) {
        return Integer.parseInt(System.getProperty(propertyName, Integer.toString(defaultValue)));
    }
}
