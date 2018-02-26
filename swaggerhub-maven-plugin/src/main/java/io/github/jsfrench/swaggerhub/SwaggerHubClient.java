package io.github.jsfrench.swaggerhub;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.apache.maven.plugin.MojoExecutionException;
import java.io.IOException;

public class SwaggerHubClient {
    private final OkHttpClient client;
    private final String host;
    private final String token;

    public SwaggerHubClient(String host, String token) {
        client = new OkHttpClient();
        this.host = host;
        this.token = token;
    }

    public String getDefinition(String owner, String api, String version, String format) throws MojoExecutionException {
        HttpUrl httpUrl = getDownloadUrl(owner, api, version);
        MediaType mediaType = MediaType.parse("application/" + format);

        final Request request = new Request.Builder()
                .url(httpUrl)
                .addHeader("Authorization", mediaType.toString())
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

    public void saveDefinition(String owner, String api, String version, String swagger, String format) throws MojoExecutionException {
        HttpUrl httpUrl = getUploadUrl(owner, api, version);

        final RequestBody body = RequestBody.create(MediaType.parse("application/" + format), swagger);

        final Request request = new Request.Builder()
                .url(httpUrl)
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

    private HttpUrl getDownloadUrl(String owner, String api, String version) {
        return new HttpUrl.Builder()
                .scheme("https")
                .host(host)
                .addPathSegment("apis")
                .addEncodedPathSegment(owner)
                .addEncodedPathSegment(api)
                .addEncodedPathSegment(version)
                .build();
    }

    private HttpUrl getUploadUrl(String owner, String api, String version) {
        return new HttpUrl.Builder()
                .scheme("https")
                .host(host)
                .addPathSegment("apis")
                .addEncodedPathSegment(owner)
                .addEncodedPathSegment(api)
                .addEncodedQueryParameter("version", version)
                .addEncodedQueryParameter("isPrivate", Boolean.toString(false))
                .addEncodedQueryParameter("force", Boolean.toString(false))
                .build();
    }
}
