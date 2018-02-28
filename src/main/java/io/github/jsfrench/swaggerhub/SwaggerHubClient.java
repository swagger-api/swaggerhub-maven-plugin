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
    private static final String APIS = "apis";


    public SwaggerHubClient(String host, int port, String protocol, String token) {
        client = new OkHttpClient();
        this.host = host;
        this.port = port;
        this.protocol = protocol;
        this.token = token;
    }

    public String getDefinition(SwaggerHubRequest swaggerHubRequest) throws MojoExecutionException {
        HttpUrl httpUrl = getDownloadUrl(swaggerHubRequest);
        MediaType mediaType = MediaType.parse("application/" + swaggerHubRequest.getFormat());

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

    public void saveDefinition(SwaggerHubRequest swaggerHubRequest) throws MojoExecutionException {
        HttpUrl httpUrl = getUploadUrl(swaggerHubRequest);
        MediaType mediaType = MediaType.parse("application/" + swaggerHubRequest.getFormat());

        final Request httpRequest = buildPostRequest(httpUrl, mediaType, swaggerHubRequest.getSwagger());

        try {
            Response response = client.newCall(httpRequest).execute();
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

    private HttpUrl getDownloadUrl(SwaggerHubRequest swaggerHubRequest) {
        return getBaseUrl(swaggerHubRequest.getOwner(), swaggerHubRequest.getApi())
                .addEncodedPathSegment(swaggerHubRequest.getVersion())
                .build();
    }

    private HttpUrl getUploadUrl(SwaggerHubRequest swaggerHubRequest) {
        return getBaseUrl(swaggerHubRequest.getOwner(), swaggerHubRequest.getApi())
                .addEncodedQueryParameter("version", swaggerHubRequest.getVersion())
                .addEncodedQueryParameter("isPrivate", Boolean.toString(swaggerHubRequest.isPrivate()))
                .addEncodedQueryParameter("force", Boolean.toString(swaggerHubRequest.isForce()))
                .build();
    }

    private HttpUrl.Builder getBaseUrl(String owner, String api) {
        return new HttpUrl.Builder()
                .scheme(protocol)
                .host(host)
                .port(port)
                .addPathSegment(APIS)
                .addEncodedPathSegment(owner)
                .addEncodedPathSegment(api);
    }
}
