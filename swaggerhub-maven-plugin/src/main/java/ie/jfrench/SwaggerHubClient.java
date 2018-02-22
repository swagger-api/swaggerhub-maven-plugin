package ie.jfrench;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.maven.plugin.MojoExecutionException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SwaggerHubClient {
    private final OkHttpClient client;
    private static final String GET_URL_TEMPLATE = "https://api.swaggerhub.com/apis/${owner}/${api}/${version}";
    private static final String POST_URL_TEMPLATE = "https://api.swaggerhub.com/apis/${owner}/${api}?version=${version}";
    private final String token;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public SwaggerHubClient(String token) {
        client = new OkHttpClient();
        this.token = token;
    }

    public String getDefinition(String owner, String api, String version) throws MojoExecutionException {
        final String url = createDownloadUrl(owner, api, version);
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", token)
                .build();

        final String jsonResponse;
        try {
            final Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new MojoExecutionException(
                        String.format("Failed to save definition: %s", response.body().string())
                );
            } else {
                jsonResponse = response.body().string();
            }
        } catch (IOException e) {
            throw new MojoExecutionException("Failed to save definition", e);
        }
        return jsonResponse;
    }

    private String createDownloadUrl(String owner, String api, String version) {
        final Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("owner", owner);
        valuesMap.put("api", api);
        valuesMap.put("version", version);

        return new StrSubstitutor(valuesMap).replace(GET_URL_TEMPLATE);
    }

    private String createUploadUrl(String owner, String api, String version) {
        final Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("owner", owner);
        valuesMap.put("api", api);
        valuesMap.put("version", version);

        return new StrSubstitutor(valuesMap).replace(POST_URL_TEMPLATE);
    }

    public void saveDefinition(String owner, String api, String version, String swaggerJson) throws MojoExecutionException {
        final String url = createUploadUrl(owner, api, version);
        final RequestBody body = RequestBody.create(JSON, swaggerJson);
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", token)
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new MojoExecutionException(
                        String.format("Failed to save definition: %s", response.body().string())
                );
            }
        } catch (IOException e) {
            throw new MojoExecutionException("Failed to save definition", e);
        }
        return;
    }
}
