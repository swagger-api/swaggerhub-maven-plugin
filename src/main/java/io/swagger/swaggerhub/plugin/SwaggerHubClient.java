package io.swagger.swaggerhub.plugin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import io.swagger.swaggerhub.plugin.requests.SaveSCMPluginConfigRequest;
import io.swagger.swaggerhub.plugin.requests.SwaggerHubRequest;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class SwaggerHubClient {
    private final OkHttpClient client;
    private final String host;
    private final int port;
    private final String token;
    private final String protocol;
    private static final String APIS = "apis";
    private Log log;
    private String basePath;

    public SwaggerHubClient(String host, int port, String protocol, String token, Log log, String basePath) {
        client = setupHttpClient();
        this.host = host;
        this.port = port;
        this.protocol = protocol;
        this.token = token;
        this.log=log;
        this.basePath = basePath;
    }

    private OkHttpClient setupHttpClient(){
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(30, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);
        client.setWriteTimeout(30, TimeUnit.SECONDS);
        return client;
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
                .addHeader("Accept", mediaType.toString())
                .addHeader("User-Agent", "swaggerhub-maven-plugin");
        if (token != null) {
            requestBuilder.addHeader("Authorization", token);
        }
        return requestBuilder.build();
    }

    public Optional<Response> saveDefinition(SwaggerHubRequest swaggerHubRequest) {
        HttpUrl httpUrl = getUploadUrl(swaggerHubRequest);
        MediaType mediaType = MediaType.parse("application/" + swaggerHubRequest.getFormat());
        Request httpRequest = buildPostRequest(httpUrl, mediaType, swaggerHubRequest.getSwagger());
        try {
            Response response = client.newCall(httpRequest).execute();
            if(!response.isSuccessful()){
                log.error(String.format("Error when attempting to save API %s version %s", swaggerHubRequest.getApi(), swaggerHubRequest.getVersion()));
                log.error("Error response: "+response.body().string());
                response.body().close();
            }
            return Optional.ofNullable(response);
        } catch (IOException e) {
            log.error(String.format("Error when attempting to save API %s. Error message %s", swaggerHubRequest.getApi(), e.getMessage()));
            return Optional.empty();
        }
    }


    public Optional<Response> saveIntegrationPluginOfType(SaveSCMPluginConfigRequest saveSCMPluginConfigRequest) throws JsonProcessingException {

        HttpUrl httpUrl = getSaveIntegrationPluginConfigURL(saveSCMPluginConfigRequest);
        MediaType mediaType = MediaType.parse("application/json");
        Request httpRequest = buildPutRequest(httpUrl, mediaType, saveSCMPluginConfigRequest.getRequestBody());
        try {
            Response response = client.newCall(httpRequest).execute();
            if(!response.isSuccessful()){
                log.error(String.format("Error when attempting to save %s plugin integration for API %s version %s", saveSCMPluginConfigRequest.getScmProvider(), saveSCMPluginConfigRequest.getApi(),saveSCMPluginConfigRequest.getVersion()));
                log.error("Error response: "+response.body().string());
                response.body().close();
            }
            return Optional.ofNullable(response);
        } catch (IOException e) {
            log.error(String.format("Error when attempting to save % plugin integration for API %s. Error message %s", saveSCMPluginConfigRequest.getScmProvider(), saveSCMPluginConfigRequest.getApi(), e.getMessage()));
            return Optional.empty();
        }

    }

    private Request buildPostRequest(HttpUrl httpUrl, MediaType mediaType, String content) {
        return new Request.Builder()
                .url(httpUrl)
                .addHeader("Content-Type", mediaType.toString())
                .addHeader("Authorization", token)
                .addHeader("User-Agent", "swaggerhub-maven-plugin")
                .post(RequestBody.create(mediaType, content))
                .build();
    }

    private Request buildPutRequest(HttpUrl httpUrl, MediaType mediaType, String content) {
        return new Request.Builder()
                .url(httpUrl)
                .addHeader("Content-Type", mediaType.toString())
                .addHeader("Authorization", token)
                .addHeader("User-Agent", "swaggerhub-maven-plugin")
                .put(RequestBody.create(mediaType, content))
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
                .addEncodedQueryParameter("oas", swaggerHubRequest.getOas())
                .build();
    }

    private HttpUrl.Builder getBaseUrl(String owner, String api) {
        HttpUrl.Builder httpUrlBuilder = new HttpUrl.Builder()
                .scheme(protocol)
                .host(host)
                .port(port);
                return addOptionalPathSegment(httpUrlBuilder, basePath)
                .addPathSegment(APIS)
                .addEncodedPathSegment(owner)
                .addEncodedPathSegment(api);
    }

    private HttpUrl getSaveIntegrationPluginConfigURL(SaveSCMPluginConfigRequest saveSCMPluginConfigRequest) {
        HttpUrl.Builder httpUrlBuilder = new HttpUrl.Builder()
                .scheme(protocol)
                .host(host)
                .port(port);
                return addOptionalPathSegment(httpUrlBuilder, basePath)
                .addPathSegment("plugins")
                .addPathSegment("configurations")
                .addEncodedPathSegment(saveSCMPluginConfigRequest.getOwner())
                .addEncodedPathSegment(saveSCMPluginConfigRequest.getApi())
                .addEncodedPathSegment(saveSCMPluginConfigRequest.getVersion())
                .addEncodedPathSegment(saveSCMPluginConfigRequest.getScmProvider())
                .addEncodedQueryParameter("oas", saveSCMPluginConfigRequest.getOas())
                .build();
    }

    private HttpUrl.Builder addOptionalPathSegment(HttpUrl.Builder builder, String pathSegment){
        if(StringUtils.isEmpty(pathSegment)){
            return builder;
        }
        return builder.addPathSegment(pathSegment);

    }

}
