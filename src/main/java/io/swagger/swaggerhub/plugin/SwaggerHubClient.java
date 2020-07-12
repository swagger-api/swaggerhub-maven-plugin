package io.swagger.swaggerhub.plugin;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.swaggerhub.plugin.requests.SaveSCMPluginConfigRequest;
import io.swagger.swaggerhub.plugin.requests.SwaggerHubRequest;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class SwaggerHubClient {
    private final OkHttpClient client;
    private final String host;
    private final int port;
    private final String token;
    private final String protocol;
    private Log log;
    private String basePath;

    public SwaggerHubClient(String host, int port, String protocol, String token, Log log, String basePath, org.apache.maven.settings.Proxy proxy) {
        client = setupHttpClient(proxy);
        this.host = host;
        this.port = port;
        this.protocol = protocol;
        this.token = token;
        this.log = log;
        this.basePath = basePath;
    }

    private OkHttpClient setupHttpClient(org.apache.maven.settings.Proxy proxy) {

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        if (proxy != null) {
            Proxy.Type proxyType = getProxyType(proxy.getProtocol());
            Proxy httpProxy = new Proxy(proxyType,
                    new InetSocketAddress(proxy.getHost(), proxy.getPort()));
            okHttpClientBuilder.proxy(httpProxy);
            if (StringUtils.isNotEmpty(proxy.getUsername())) {
                Authenticator proxyAuthenticator = new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        String credential = Credentials.basic(proxy.getUsername(), proxy.getPassword());
                        return response.request().newBuilder()
                                .header("Proxy-Authorization", credential)
                                .build();
                    }
                };
                okHttpClientBuilder.proxyAuthenticator(proxyAuthenticator);
            }
        }

        OkHttpClient client = okHttpClientBuilder
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        return client;
    }

    /**
     * Only accepts http or https protocol.
     *
     * @param proxy
     * @return java.net.Proxy.Type
     */
    private java.net.Proxy.Type getProxyType(String protocol) {
        if ("http".equals(protocol) || "https".equals(protocol) || protocol == null) {
            return java.net.Proxy.Type.HTTP;
        }
        return null;
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
            if (!response.isSuccessful()) {
                log.error(String.format("Error when attempting to save API %s version %s", swaggerHubRequest.getApi(), swaggerHubRequest.getVersion()));
                log.error("Error response: " + response.body().string());
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
            if (!response.isSuccessful()) {
                log.error(String.format("Error when attempting to save %s plugin integration for API %s version %s", saveSCMPluginConfigRequest.getScmProvider(), saveSCMPluginConfigRequest.getApi(), saveSCMPluginConfigRequest.getVersion()));
                log.error("Error response: " + response.body().string());
                response.body().close();
            }
            return Optional.ofNullable(response);
        } catch (IOException e) {
            log.error(String.format("Error when attempting to save %s plugin integration for API %s. Error message %s", saveSCMPluginConfigRequest.getScmProvider(), saveSCMPluginConfigRequest.getApi(), e.getMessage()));
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
        return getBaseUrl(swaggerHubRequest.getDefinitionType(), swaggerHubRequest.getOwner(), swaggerHubRequest.getApi())
                .addEncodedPathSegment(swaggerHubRequest.getVersion())
                .build();
    }

    private HttpUrl getUploadUrl(SwaggerHubRequest swaggerHubRequest) {
        return getBaseUrl(swaggerHubRequest.getDefinitionType(), swaggerHubRequest.getOwner(), swaggerHubRequest.getApi())
                .addEncodedQueryParameter("version", swaggerHubRequest.getVersion())
                .addEncodedQueryParameter("isPrivate", Boolean.toString(swaggerHubRequest.isPrivate()))
                .addEncodedQueryParameter("oas", swaggerHubRequest.getOas())
                .build();
    }

    private HttpUrl.Builder getBaseUrl(DefinitionType definitionType, String owner, String api) {
        HttpUrl.Builder httpUrlBuilder = new HttpUrl.Builder()
                .scheme(protocol)
                .host(host)
                .port(port);
        return addOptionalPathSegment(httpUrlBuilder, basePath)
                .addPathSegment(definitionType.getPathSegment())
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

    private HttpUrl.Builder addOptionalPathSegment(HttpUrl.Builder builder, String pathSegment) {
        if (StringUtils.isEmpty(pathSegment)) {
            return builder;
        }
        return builder.addPathSegment(pathSegment);

    }

}
