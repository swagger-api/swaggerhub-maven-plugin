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
    private final int port;
    private final String token;
    private final String protocol;


    public SwaggerHubClient(String host, int port, String protocol, String token) {
        client = new OkHttpClient();
        this.host = host;
        this.port = port;
        this.protocol = protocol;
        this.token = token;
    }

    public String getDefinition(String owner, String api, String version, String format) throws MojoExecutionException {
        HttpUrl httpUrl = getDownloadUrl(owner, api, version);
        MediaType mediaType = MediaType.parse("application/" + format);

        Request requestBuilder = buildGetRequest(httpUrl, mediaType);

        final String jsonResponse;
        try {
            final Response response = client.newCall(requestBuilder).execute();
            if (!response.isSuccessful()) {
                throw new MojoExecutionException(
                        String.format("Failed to download definition: %s", response.body().string())
                );
            } else {
                jsonResponse = response.body().string();
            }
        } catch (IOException e) {
            throw new MojoExecutionException("Failed to download definition", e);
        }
        return jsonResponse;
    }

    private Request buildGetRequest(HttpUrl httpUrl, MediaType mediaType) {
        Request.Builder requestBuilder = new Request.Builder()
                .url(httpUrl)
                .addHeader("Accept", mediaType.toString());
        if (token != null) {
            requestBuilder.addHeader("Authorization", token);
        }
        return requestBuilder.build();
    }

    public void saveDefinition(String owner, String api, String version, String swagger, String format) throws MojoExecutionException {
        HttpUrl httpUrl = getUploadUrl(owner, api, version);
        MediaType mediaType = MediaType.parse("application/" + format);

        final Request request = buildPostRequest(httpUrl, mediaType, swagger);

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new MojoExecutionException(
                        String.format("Failed to upload definition: %s", response.body().string())
                );
            }
        } catch (IOException e) {
            throw new MojoExecutionException("Failed to upload definition", e);
        }
        return;
    }

    private Request buildPostRequest(HttpUrl httpUrl, MediaType mediaType, String content) {
        return new Request.Builder()
                    .url(httpUrl)
                    .addHeader("Content-Type", mediaType.toString())
                    .addHeader("Authorization", token)
                    .post(RequestBody.create(mediaType, content))
                    .build();
    }

    private HttpUrl getDownloadUrl(String owner, String api, String version) {
        return new HttpUrl.Builder()
                .scheme(protocol)
                .host(host)
                .port(port)
                .addPathSegment("apis")
                .addEncodedPathSegment(owner)
                .addEncodedPathSegment(api)
                .addEncodedPathSegment(version)
                .build();
    }

    private HttpUrl getUploadUrl(String owner, String api, String version) {
        return new HttpUrl.Builder()
                .scheme(protocol)
                .host(host)
                .port(port)
                .addPathSegment("apis")
                .addEncodedPathSegment(owner)
                .addEncodedPathSegment(api)
                .addEncodedQueryParameter("version", version)
                .addEncodedQueryParameter("isPrivate", Boolean.toString(false))
                .addEncodedQueryParameter("force", Boolean.toString(false))
                .build();
    }
}
