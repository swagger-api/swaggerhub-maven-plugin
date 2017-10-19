

package io.swagger.client.api;

import io.swagger.client.ApiCallback;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;
import io.swagger.client.ProgressRequestBody;
import io.swagger.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import io.swagger.client.model.ApisJson;
import io.swagger.client.model.Page;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomainsApi {
    private ApiClient apiClient;

    public DomainsApi() {
        this(Configuration.getDefaultApiClient());
    }

    public DomainsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /* Build call for deleteDomain */
    private com.squareup.okhttp.Call deleteDomainCall(String owner, String domain, Boolean force, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling deleteDomain(Async)");
        }
        
        // verify the required parameter 'domain' is set
        if (domain == null) {
            throw new ApiException("Missing the required parameter 'domain' when calling deleteDomain(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/domains/{owner}/{domain}".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()))
        .replaceAll("\\{" + "domain" + "\\}", apiClient.escapeString(domain.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        if (force != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "force", force));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "TokenSecured" };
        return apiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    /**
     * Deletes the specified domain
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param force force update (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void deleteDomain(String owner, String domain, Boolean force) throws ApiException {
        deleteDomainWithHttpInfo(owner, domain, force);
    }

    /**
     * Deletes the specified domain
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param force force update (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> deleteDomainWithHttpInfo(String owner, String domain, Boolean force) throws ApiException {
        com.squareup.okhttp.Call call = deleteDomainCall(owner, domain, force, null, null);
        return apiClient.execute(call);
    }

    /**
     * Deletes the specified domain (asynchronously)
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param force force update (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call deleteDomainAsync(String owner, String domain, Boolean force, final ApiCallback<Void> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = deleteDomainCall(owner, domain, force, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /* Build call for deleteDomainVersion */
    private com.squareup.okhttp.Call deleteDomainVersionCall(String owner, String domain, String version, Boolean force, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling deleteDomainVersion(Async)");
        }
        
        // verify the required parameter 'domain' is set
        if (domain == null) {
            throw new ApiException("Missing the required parameter 'domain' when calling deleteDomainVersion(Async)");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new ApiException("Missing the required parameter 'version' when calling deleteDomainVersion(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/domains/{owner}/{domain}/{version}".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()))
        .replaceAll("\\{" + "domain" + "\\}", apiClient.escapeString(domain.toString()))
        .replaceAll("\\{" + "version" + "\\}", apiClient.escapeString(version.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        if (force != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "force", force));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "TokenSecured" };
        return apiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    /**
     * Deletes a particular version of the specified domain
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param version version identifier (required)
     * @param force force update (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void deleteDomainVersion(String owner, String domain, String version, Boolean force) throws ApiException {
        deleteDomainVersionWithHttpInfo(owner, domain, version, force);
    }

    /**
     * Deletes a particular version of the specified domain
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param version version identifier (required)
     * @param force force update (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> deleteDomainVersionWithHttpInfo(String owner, String domain, String version, Boolean force) throws ApiException {
        com.squareup.okhttp.Call call = deleteDomainVersionCall(owner, domain, version, force, null, null);
        return apiClient.execute(call);
    }

    /**
     * Deletes a particular version of the specified domain (asynchronously)
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param version version identifier (required)
     * @param force force update (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call deleteDomainVersionAsync(String owner, String domain, String version, Boolean force, final ApiCallback<Void> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = deleteDomainVersionCall(owner, domain, version, force, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /* Build call for getDomainDefinition */
    private com.squareup.okhttp.Call getDomainDefinitionCall(String owner, String domain, String version, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling getDomainDefinition(Async)");
        }
        
        // verify the required parameter 'domain' is set
        if (domain == null) {
            throw new ApiException("Missing the required parameter 'domain' when calling getDomainDefinition(Async)");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new ApiException("Missing the required parameter 'version' when calling getDomainDefinition(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/domains/{owner}/{domain}/{version}".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()))
        .replaceAll("\\{" + "domain" + "\\}", apiClient.escapeString(domain.toString()))
        .replaceAll("\\{" + "version" + "\\}", apiClient.escapeString(version.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json", "application/yaml"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "TokenSecured" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    /**
     * Retrieves the Swagger definition for the specified domain and version
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param version version identifier (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Object getDomainDefinition(String owner, String domain, String version) throws ApiException {
        ApiResponse<Object> resp = getDomainDefinitionWithHttpInfo(owner, domain, version);
        return resp.getData();
    }

    /**
     * Retrieves the Swagger definition for the specified domain and version
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param version version identifier (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Object> getDomainDefinitionWithHttpInfo(String owner, String domain, String version) throws ApiException {
        com.squareup.okhttp.Call call = getDomainDefinitionCall(owner, domain, version, null, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Retrieves the Swagger definition for the specified domain and version (asynchronously)
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param version version identifier (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getDomainDefinitionAsync(String owner, String domain, String version, final ApiCallback<Object> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getDomainDefinitionCall(owner, domain, version, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for getDomainJsonDefinition */
    private com.squareup.okhttp.Call getDomainJsonDefinitionCall(String owner, String domain, String version, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling getDomainJsonDefinition(Async)");
        }
        
        // verify the required parameter 'domain' is set
        if (domain == null) {
            throw new ApiException("Missing the required parameter 'domain' when calling getDomainJsonDefinition(Async)");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new ApiException("Missing the required parameter 'version' when calling getDomainJsonDefinition(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/domains/{owner}/{domain}/{version}/domain.json".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()))
        .replaceAll("\\{" + "domain" + "\\}", apiClient.escapeString(domain.toString()))
        .replaceAll("\\{" + "version" + "\\}", apiClient.escapeString(version.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "TokenSecured" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    /**
     * Retrieves the definition for the specified domain and version in JSON format
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param version version identifier (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Object getDomainJsonDefinition(String owner, String domain, String version) throws ApiException {
        ApiResponse<Object> resp = getDomainJsonDefinitionWithHttpInfo(owner, domain, version);
        return resp.getData();
    }

    /**
     * Retrieves the definition for the specified domain and version in JSON format
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param version version identifier (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Object> getDomainJsonDefinitionWithHttpInfo(String owner, String domain, String version) throws ApiException {
        com.squareup.okhttp.Call call = getDomainJsonDefinitionCall(owner, domain, version, null, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Retrieves the definition for the specified domain and version in JSON format (asynchronously)
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param version version identifier (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getDomainJsonDefinitionAsync(String owner, String domain, String version, final ApiCallback<Object> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getDomainJsonDefinitionCall(owner, domain, version, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for getDomainVersions */
    private com.squareup.okhttp.Call getDomainVersionsCall(String owner, String domain, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling getDomainVersions(Async)");
        }
        
        // verify the required parameter 'domain' is set
        if (domain == null) {
            throw new ApiException("Missing the required parameter 'domain' when calling getDomainVersions(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/domains/{owner}/{domain}".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()))
        .replaceAll("\\{" + "domain" + "\\}", apiClient.escapeString(domain.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "TokenSecured" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    /**
     * Retrieves an APIs.json listing for all domain versions for this owner and domain
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @return ApisJson
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApisJson getDomainVersions(String owner, String domain) throws ApiException {
        ApiResponse<ApisJson> resp = getDomainVersionsWithHttpInfo(owner, domain);
        return resp.getData();
    }

    /**
     * Retrieves an APIs.json listing for all domain versions for this owner and domain
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @return ApiResponse&lt;ApisJson&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<ApisJson> getDomainVersionsWithHttpInfo(String owner, String domain) throws ApiException {
        com.squareup.okhttp.Call call = getDomainVersionsCall(owner, domain, null, null);
        Type localVarReturnType = new TypeToken<ApisJson>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Retrieves an APIs.json listing for all domain versions for this owner and domain (asynchronously)
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getDomainVersionsAsync(String owner, String domain, final ApiCallback<ApisJson> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getDomainVersionsCall(owner, domain, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<ApisJson>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for getDomainYamlDefinition */
    private com.squareup.okhttp.Call getDomainYamlDefinitionCall(String owner, String domain, String version, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling getDomainYamlDefinition(Async)");
        }
        
        // verify the required parameter 'domain' is set
        if (domain == null) {
            throw new ApiException("Missing the required parameter 'domain' when calling getDomainYamlDefinition(Async)");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new ApiException("Missing the required parameter 'version' when calling getDomainYamlDefinition(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/domains/{owner}/{domain}/{version}/domain.yaml".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()))
        .replaceAll("\\{" + "domain" + "\\}", apiClient.escapeString(domain.toString()))
        .replaceAll("\\{" + "version" + "\\}", apiClient.escapeString(version.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/yaml"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "TokenSecured" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    /**
     * Retrieves the definition for the specified domain and version in YAML format
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param version version identifier (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Object getDomainYamlDefinition(String owner, String domain, String version) throws ApiException {
        ApiResponse<Object> resp = getDomainYamlDefinitionWithHttpInfo(owner, domain, version);
        return resp.getData();
    }

    /**
     * Retrieves the definition for the specified domain and version in YAML format
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param version version identifier (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Object> getDomainYamlDefinitionWithHttpInfo(String owner, String domain, String version) throws ApiException {
        com.squareup.okhttp.Call call = getDomainYamlDefinitionCall(owner, domain, version, null, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Retrieves the definition for the specified domain and version in YAML format (asynchronously)
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param version version identifier (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getDomainYamlDefinitionAsync(String owner, String domain, String version, final ApiCallback<Object> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getDomainYamlDefinitionCall(owner, domain, version, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for getOwnerDomains */
    private com.squareup.okhttp.Call getOwnerDomainsCall(String owner, Integer page, Integer limit, String sort, String order, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling getOwnerDomains(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/domains/{owner}".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        if (page != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "page", page));
        if (limit != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "limit", limit));
        if (sort != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "sort", sort));
        if (order != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "order", order));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "TokenSecured" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    /**
     * Retrieves an APIs.json listing of all domains defined for this owner
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param page page to return (optional, default to 0)
     * @param limit number of results per page (optional, default to 10)
     * @param sort sort criteria or result set * NAME - * UPATED * CREATED * OWNER  (optional, default to NAME)
     * @param order sort order (optional, default to ASC)
     * @return ApisJson
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApisJson getOwnerDomains(String owner, Integer page, Integer limit, String sort, String order) throws ApiException {
        ApiResponse<ApisJson> resp = getOwnerDomainsWithHttpInfo(owner, page, limit, sort, order);
        return resp.getData();
    }

    /**
     * Retrieves an APIs.json listing of all domains defined for this owner
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param page page to return (optional, default to 0)
     * @param limit number of results per page (optional, default to 10)
     * @param sort sort criteria or result set * NAME - * UPATED * CREATED * OWNER  (optional, default to NAME)
     * @param order sort order (optional, default to ASC)
     * @return ApiResponse&lt;ApisJson&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<ApisJson> getOwnerDomainsWithHttpInfo(String owner, Integer page, Integer limit, String sort, String order) throws ApiException {
        com.squareup.okhttp.Call call = getOwnerDomainsCall(owner, page, limit, sort, order, null, null);
        Type localVarReturnType = new TypeToken<ApisJson>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Retrieves an APIs.json listing of all domains defined for this owner (asynchronously)
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param page page to return (optional, default to 0)
     * @param limit number of results per page (optional, default to 10)
     * @param sort sort criteria or result set * NAME - * UPATED * CREATED * OWNER  (optional, default to NAME)
     * @param order sort order (optional, default to ASC)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getOwnerDomainsAsync(String owner, Integer page, Integer limit, String sort, String order, final ApiCallback<ApisJson> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getOwnerDomainsCall(owner, page, limit, sort, order, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<ApisJson>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for saveDomainDefinition */
    private com.squareup.okhttp.Call saveDomainDefinitionCall(String owner, String domain, String version, Boolean isPrivate, String definition, Boolean force, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = definition;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling saveDomainDefinition(Async)");
        }
        
        // verify the required parameter 'domain' is set
        if (domain == null) {
            throw new ApiException("Missing the required parameter 'domain' when calling saveDomainDefinition(Async)");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new ApiException("Missing the required parameter 'version' when calling saveDomainDefinition(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/domains/{owner}/{domain}".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()))
        .replaceAll("\\{" + "domain" + "\\}", apiClient.escapeString(domain.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        if (isPrivate != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "isPrivate", isPrivate));
        if (version != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "version", version));
        if (force != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "force", force));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json", "application/yaml"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "TokenSecured" };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    /**
     * Saves the provided Swagger definition of a domain
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param version domain version (required)
     * @param isPrivate Defines whether the API has to be private (optional, default to false)
     * @param definition the Swagger definition of this Domain (optional)
     * @param force force update (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void saveDomainDefinition(String owner, String domain, String version, Boolean isPrivate, String definition, Boolean force) throws ApiException {
        saveDomainDefinitionWithHttpInfo(owner, domain, version, isPrivate, definition, force);
    }

    /**
     * Saves the provided Swagger definition of a domain
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param version domain version (required)
     * @param isPrivate Defines whether the API has to be private (optional, default to false)
     * @param definition the Swagger definition of this Domain (optional)
     * @param force force update (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> saveDomainDefinitionWithHttpInfo(String owner, String domain, String version, Boolean isPrivate, String definition, Boolean force) throws ApiException {
        com.squareup.okhttp.Call call = saveDomainDefinitionCall(owner, domain, version, isPrivate, definition, force, null, null);
        return apiClient.execute(call);
    }

    /**
     * Saves the provided Swagger definition of a domain (asynchronously)
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param domain domain identifier (required)
     * @param version domain version (required)
     * @param isPrivate Defines whether the API has to be private (optional, default to false)
     * @param definition the Swagger definition of this Domain (optional)
     * @param force force update (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call saveDomainDefinitionAsync(String owner, String domain, String version, Boolean isPrivate, String definition, Boolean force, final ApiCallback<Void> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = saveDomainDefinitionCall(owner, domain, version, isPrivate, definition, force, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /* Build call for searchApisAndDomains */
    private com.squareup.okhttp.Call searchApisAndDomainsCall(String specType, String visibility, String state, String owner, String query, Integer page, Integer limit, String sort, String order, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        

        // create path and map variables
        String localVarPath = "/specs".replaceAll("\\{format\\}","json");

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        if (specType != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "specType", specType));
        if (visibility != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "visibility", visibility));
        if (state != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "state", state));
        if (owner != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "owner", owner));
        if (query != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "query", query));
        if (page != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "page", page));
        if (limit != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "limit", limit));
        if (sort != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "sort", sort));
        if (order != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "order", order));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "TokenSecured" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    /**
     * Retrieves a list of currently defined APIs and Domains in APIs.json format
     * 
     * @param specType Type of Swagger specs to search * API - APIs only * DOMAIN - Domains only * ANY - Both APIs and Domains  (optional, default to ANY)
     * @param visibility The visibility of a spec in SwaggerHub * PUBLIC - can be viewed by anyone * PRIVATE - can only be viewed by you or your Org and those that you are collaborating with or have shared it with * ANY - either PUBLIC or PRIVATE  (optional, default to ANY)
     * @param state matches against published state of the spec * UNPUBLISHED - spec is a draft, a work in progress * PUBLISHED - spec is a stable version ready for consuming from client applications * ANY - either PUBLISHED or UNPUBLISHED  (optional, default to ALL)
     * @param owner API or Domaion owner identifier. Can be username or Organization name (optional)
     * @param query free text query to match (optional)
     * @param page page to return (optional, default to 0)
     * @param limit number of results per page (optional, default to 10)
     * @param sort sort criteria or result set * NAME - * UPATED * CREATED * OWNER  (optional, default to NAME)
     * @param order sort order (optional, default to ASC)
     * @return ApisJson
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApisJson searchApisAndDomains(String specType, String visibility, String state, String owner, String query, Integer page, Integer limit, String sort, String order) throws ApiException {
        ApiResponse<ApisJson> resp = searchApisAndDomainsWithHttpInfo(specType, visibility, state, owner, query, page, limit, sort, order);
        return resp.getData();
    }

    /**
     * Retrieves a list of currently defined APIs and Domains in APIs.json format
     * 
     * @param specType Type of Swagger specs to search * API - APIs only * DOMAIN - Domains only * ANY - Both APIs and Domains  (optional, default to ANY)
     * @param visibility The visibility of a spec in SwaggerHub * PUBLIC - can be viewed by anyone * PRIVATE - can only be viewed by you or your Org and those that you are collaborating with or have shared it with * ANY - either PUBLIC or PRIVATE  (optional, default to ANY)
     * @param state matches against published state of the spec * UNPUBLISHED - spec is a draft, a work in progress * PUBLISHED - spec is a stable version ready for consuming from client applications * ANY - either PUBLISHED or UNPUBLISHED  (optional, default to ALL)
     * @param owner API or Domaion owner identifier. Can be username or Organization name (optional)
     * @param query free text query to match (optional)
     * @param page page to return (optional, default to 0)
     * @param limit number of results per page (optional, default to 10)
     * @param sort sort criteria or result set * NAME - * UPATED * CREATED * OWNER  (optional, default to NAME)
     * @param order sort order (optional, default to ASC)
     * @return ApiResponse&lt;ApisJson&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<ApisJson> searchApisAndDomainsWithHttpInfo(String specType, String visibility, String state, String owner, String query, Integer page, Integer limit, String sort, String order) throws ApiException {
        com.squareup.okhttp.Call call = searchApisAndDomainsCall(specType, visibility, state, owner, query, page, limit, sort, order, null, null);
        Type localVarReturnType = new TypeToken<ApisJson>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Retrieves a list of currently defined APIs and Domains in APIs.json format (asynchronously)
     * 
     * @param specType Type of Swagger specs to search * API - APIs only * DOMAIN - Domains only * ANY - Both APIs and Domains  (optional, default to ANY)
     * @param visibility The visibility of a spec in SwaggerHub * PUBLIC - can be viewed by anyone * PRIVATE - can only be viewed by you or your Org and those that you are collaborating with or have shared it with * ANY - either PUBLIC or PRIVATE  (optional, default to ANY)
     * @param state matches against published state of the spec * UNPUBLISHED - spec is a draft, a work in progress * PUBLISHED - spec is a stable version ready for consuming from client applications * ANY - either PUBLISHED or UNPUBLISHED  (optional, default to ALL)
     * @param owner API or Domaion owner identifier. Can be username or Organization name (optional)
     * @param query free text query to match (optional)
     * @param page page to return (optional, default to 0)
     * @param limit number of results per page (optional, default to 10)
     * @param sort sort criteria or result set * NAME - * UPATED * CREATED * OWNER  (optional, default to NAME)
     * @param order sort order (optional, default to ASC)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call searchApisAndDomainsAsync(String specType, String visibility, String state, String owner, String query, Integer page, Integer limit, String sort, String order, final ApiCallback<ApisJson> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = searchApisAndDomainsCall(specType, visibility, state, owner, query, page, limit, sort, order, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<ApisJson>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for searchDomains */
    private com.squareup.okhttp.Call searchDomainsCall(String query, String state, List<String> tag, Integer page, Integer limit, String sort, String order, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        

        // create path and map variables
        String localVarPath = "/domains".replaceAll("\\{format\\}","json");

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        if (query != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "query", query));
        if (state != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "state", state));
        if (tag != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("multi", "tag", tag));
        if (page != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "page", page));
        if (limit != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "limit", limit));
        if (sort != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "sort", sort));
        if (order != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "order", order));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "TokenSecured" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    /**
     * Retrieves a list of currently defined domains in APIs.json format
     * 
     * @param query free text query to match (optional)
     * @param state matches against published state of the spec * UNPUBLISHED - spec is a draft, a work in progress * PUBLISHED - spec is a stable version ready for consuming from client applications * ANY - either PUBLISHED or UNPUBLISHED  (optional, default to ALL)
     * @param tag matches against tags associated with a domain (optional)
     * @param page page to return (optional, default to 0)
     * @param limit number of results per page (optional, default to 10)
     * @param sort sort criteria or result set * NAME - * UPATED * CREATED * OWNER  (optional, default to NAME)
     * @param order sort order (optional, default to ASC)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void searchDomains(String query, String state, List<String> tag, Integer page, Integer limit, String sort, String order) throws ApiException {
        searchDomainsWithHttpInfo(query, state, tag, page, limit, sort, order);
    }

    /**
     * Retrieves a list of currently defined domains in APIs.json format
     * 
     * @param query free text query to match (optional)
     * @param state matches against published state of the spec * UNPUBLISHED - spec is a draft, a work in progress * PUBLISHED - spec is a stable version ready for consuming from client applications * ANY - either PUBLISHED or UNPUBLISHED  (optional, default to ALL)
     * @param tag matches against tags associated with a domain (optional)
     * @param page page to return (optional, default to 0)
     * @param limit number of results per page (optional, default to 10)
     * @param sort sort criteria or result set * NAME - * UPATED * CREATED * OWNER  (optional, default to NAME)
     * @param order sort order (optional, default to ASC)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> searchDomainsWithHttpInfo(String query, String state, List<String> tag, Integer page, Integer limit, String sort, String order) throws ApiException {
        com.squareup.okhttp.Call call = searchDomainsCall(query, state, tag, page, limit, sort, order, null, null);
        return apiClient.execute(call);
    }

    /**
     * Retrieves a list of currently defined domains in APIs.json format (asynchronously)
     * 
     * @param query free text query to match (optional)
     * @param state matches against published state of the spec * UNPUBLISHED - spec is a draft, a work in progress * PUBLISHED - spec is a stable version ready for consuming from client applications * ANY - either PUBLISHED or UNPUBLISHED  (optional, default to ALL)
     * @param tag matches against tags associated with a domain (optional)
     * @param page page to return (optional, default to 0)
     * @param limit number of results per page (optional, default to 10)
     * @param sort sort criteria or result set * NAME - * UPATED * CREATED * OWNER  (optional, default to NAME)
     * @param order sort order (optional, default to ASC)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call searchDomainsAsync(String query, String state, List<String> tag, Integer page, Integer limit, String sort, String order, final ApiCallback<Void> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = searchDomainsCall(query, state, tag, page, limit, sort, order, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
}
