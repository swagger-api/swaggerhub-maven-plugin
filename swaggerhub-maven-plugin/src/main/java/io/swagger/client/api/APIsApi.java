

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
import io.swagger.client.model.Collaboration;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIsApi {
    private ApiClient apiClient;

    public APIsApi() {
        this(Configuration.getDefaultApiClient());
    }

    public APIsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /* Build call for deleteApi */
    private com.squareup.okhttp.Call deleteApiCall(String owner, String api, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling deleteApi(Async)");
        }
        
        // verify the required parameter 'api' is set
        if (api == null) {
            throw new ApiException("Missing the required parameter 'api' when calling deleteApi(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/apis/{owner}/{api}".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()))
        .replaceAll("\\{" + "api" + "\\}", apiClient.escapeString(api.toString()));

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
        return apiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    /**
     * Deletes the specified API
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void deleteApi(String owner, String api) throws ApiException {
        deleteApiWithHttpInfo(owner, api);
    }

    /**
     * Deletes the specified API
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> deleteApiWithHttpInfo(String owner, String api) throws ApiException {
        com.squareup.okhttp.Call call = deleteApiCall(owner, api, null, null);
        return apiClient.execute(call);
    }

    /**
     * Deletes the specified API (asynchronously)
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call deleteApiAsync(String owner, String api, final ApiCallback<Void> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = deleteApiCall(owner, api, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /* Build call for deleteApiVersion */
    private com.squareup.okhttp.Call deleteApiVersionCall(String owner, String api, String version, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling deleteApiVersion(Async)");
        }
        
        // verify the required parameter 'api' is set
        if (api == null) {
            throw new ApiException("Missing the required parameter 'api' when calling deleteApiVersion(Async)");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new ApiException("Missing the required parameter 'version' when calling deleteApiVersion(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/apis/{owner}/{api}/{version}".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()))
        .replaceAll("\\{" + "api" + "\\}", apiClient.escapeString(api.toString()))
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
        return apiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    /**
     * Deletes a particular version of the specified API
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param version version identifier (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void deleteApiVersion(String owner, String api, String version) throws ApiException {
        deleteApiVersionWithHttpInfo(owner, api, version);
    }

    /**
     * Deletes a particular version of the specified API
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param version version identifier (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> deleteApiVersionWithHttpInfo(String owner, String api, String version) throws ApiException {
        com.squareup.okhttp.Call call = deleteApiVersionCall(owner, api, version, null, null);
        return apiClient.execute(call);
    }

    /**
     * Deletes a particular version of the specified API (asynchronously)
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param version version identifier (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call deleteApiVersionAsync(String owner, String api, String version, final ApiCallback<Void> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = deleteApiVersionCall(owner, api, version, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /* Build call for deleteCollaboration */
    private com.squareup.okhttp.Call deleteCollaborationCall(String owner, String api, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling deleteCollaboration(Async)");
        }
        
        // verify the required parameter 'api' is set
        if (api == null) {
            throw new ApiException("Missing the required parameter 'api' when calling deleteCollaboration(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/apis/{owner}/{api}/.collaboration".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()))
        .replaceAll("\\{" + "api" + "\\}", apiClient.escapeString(api.toString()));

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
        return apiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    /**
     * Deletes API&#39;s collaboration
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void deleteCollaboration(String owner, String api) throws ApiException {
        deleteCollaborationWithHttpInfo(owner, api);
    }

    /**
     * Deletes API&#39;s collaboration
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> deleteCollaborationWithHttpInfo(String owner, String api) throws ApiException {
        com.squareup.okhttp.Call call = deleteCollaborationCall(owner, api, null, null);
        return apiClient.execute(call);
    }

    /**
     * Deletes API&#39;s collaboration (asynchronously)
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call deleteCollaborationAsync(String owner, String api, final ApiCallback<Void> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = deleteCollaborationCall(owner, api, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /* Build call for getApiVersions */
    private com.squareup.okhttp.Call getApiVersionsCall(String owner, String api, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling getApiVersions(Async)");
        }
        
        // verify the required parameter 'api' is set
        if (api == null) {
            throw new ApiException("Missing the required parameter 'api' when calling getApiVersions(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/apis/{owner}/{api}".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()))
        .replaceAll("\\{" + "api" + "\\}", apiClient.escapeString(api.toString()));

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
     * Retrieves an APIs.json listing for all API versions for this owner and API
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @return ApisJson
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApisJson getApiVersions(String owner, String api) throws ApiException {
        ApiResponse<ApisJson> resp = getApiVersionsWithHttpInfo(owner, api);
        return resp.getData();
    }

    /**
     * Retrieves an APIs.json listing for all API versions for this owner and API
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @return ApiResponse&lt;ApisJson&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<ApisJson> getApiVersionsWithHttpInfo(String owner, String api) throws ApiException {
        com.squareup.okhttp.Call call = getApiVersionsCall(owner, api, null, null);
        Type localVarReturnType = new TypeToken<ApisJson>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Retrieves an APIs.json listing for all API versions for this owner and API (asynchronously)
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getApiVersionsAsync(String owner, String api, final ApiCallback<ApisJson> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getApiVersionsCall(owner, api, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<ApisJson>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for getCollaboration */
    private com.squareup.okhttp.Call getCollaborationCall(String owner, String api, Boolean expandTeams, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling getCollaboration(Async)");
        }
        
        // verify the required parameter 'api' is set
        if (api == null) {
            throw new ApiException("Missing the required parameter 'api' when calling getCollaboration(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/apis/{owner}/{api}/.collaboration".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()))
        .replaceAll("\\{" + "api" + "\\}", apiClient.escapeString(api.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        if (expandTeams != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "expandTeams", expandTeams));

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
     * Gets API&#39;s collaboration
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param expandTeams  (optional, default to false)
     * @return Collaboration
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Collaboration getCollaboration(String owner, String api, Boolean expandTeams) throws ApiException {
        ApiResponse<Collaboration> resp = getCollaborationWithHttpInfo(owner, api, expandTeams);
        return resp.getData();
    }

    /**
     * Gets API&#39;s collaboration
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param expandTeams  (optional, default to false)
     * @return ApiResponse&lt;Collaboration&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Collaboration> getCollaborationWithHttpInfo(String owner, String api, Boolean expandTeams) throws ApiException {
        com.squareup.okhttp.Call call = getCollaborationCall(owner, api, expandTeams, null, null);
        Type localVarReturnType = new TypeToken<Collaboration>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Gets API&#39;s collaboration (asynchronously)
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param expandTeams  (optional, default to false)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getCollaborationAsync(String owner, String api, Boolean expandTeams, final ApiCallback<Collaboration> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getCollaborationCall(owner, api, expandTeams, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Collaboration>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for getDefinition */
    private com.squareup.okhttp.Call getDefinitionCall(String owner, String api, String version, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling getDefinition(Async)");
        }
        
        // verify the required parameter 'api' is set
        if (api == null) {
            throw new ApiException("Missing the required parameter 'api' when calling getDefinition(Async)");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new ApiException("Missing the required parameter 'version' when calling getDefinition(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/apis/{owner}/{api}/{version}".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()))
        .replaceAll("\\{" + "api" + "\\}", apiClient.escapeString(api.toString()))
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
     * Retrieves the Swagger definition for the specified API and version
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param version version identifier (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Object getDefinition(String owner, String api, String version) throws ApiException {
        ApiResponse<Object> resp = getDefinitionWithHttpInfo(owner, api, version);
        return resp.getData();
    }

    /**
     * Retrieves the Swagger definition for the specified API and version
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param version version identifier (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Object> getDefinitionWithHttpInfo(String owner, String api, String version) throws ApiException {
        com.squareup.okhttp.Call call = getDefinitionCall(owner, api, version, null, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Retrieves the Swagger definition for the specified API and version (asynchronously)
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param version version identifier (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getDefinitionAsync(String owner, String api, String version, final ApiCallback<Object> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getDefinitionCall(owner, api, version, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for getJsonDefinition */
    private com.squareup.okhttp.Call getJsonDefinitionCall(String owner, String api, String version, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling getJsonDefinition(Async)");
        }
        
        // verify the required parameter 'api' is set
        if (api == null) {
            throw new ApiException("Missing the required parameter 'api' when calling getJsonDefinition(Async)");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new ApiException("Missing the required parameter 'version' when calling getJsonDefinition(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/apis/{owner}/{api}/{version}/swagger.json".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()))
        .replaceAll("\\{" + "api" + "\\}", apiClient.escapeString(api.toString()))
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
     * Retrieves the Swagger definition for the specified API and version in JSON format
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param version version identifier (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Object getJsonDefinition(String owner, String api, String version) throws ApiException {
        ApiResponse<Object> resp = getJsonDefinitionWithHttpInfo(owner, api, version);
        return resp.getData();
    }

    /**
     * Retrieves the Swagger definition for the specified API and version in JSON format
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param version version identifier (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Object> getJsonDefinitionWithHttpInfo(String owner, String api, String version) throws ApiException {
        com.squareup.okhttp.Call call = getJsonDefinitionCall(owner, api, version, null, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Retrieves the Swagger definition for the specified API and version in JSON format (asynchronously)
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param version version identifier (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getJsonDefinitionAsync(String owner, String api, String version, final ApiCallback<Object> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getJsonDefinitionCall(owner, api, version, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for getOwnerApis */
    private com.squareup.okhttp.Call getOwnerApisCall(String owner, Integer page, Integer limit, String sort, String order, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling getOwnerApis(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/apis/{owner}".replaceAll("\\{format\\}","json")
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
     * Retrieves an APIs.json listing of all APIs defined for this owner
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param page page to return (optional, default to 0)
     * @param limit number of results per page (optional, default to 10)
     * @param sort sort criteria or result set * NAME - * UPATED * CREATED * OWNER  (optional, default to NAME)
     * @param order sort order (optional, default to ASC)
     * @return ApisJson
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApisJson getOwnerApis(String owner, Integer page, Integer limit, String sort, String order) throws ApiException {
        ApiResponse<ApisJson> resp = getOwnerApisWithHttpInfo(owner, page, limit, sort, order);
        return resp.getData();
    }

    /**
     * Retrieves an APIs.json listing of all APIs defined for this owner
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param page page to return (optional, default to 0)
     * @param limit number of results per page (optional, default to 10)
     * @param sort sort criteria or result set * NAME - * UPATED * CREATED * OWNER  (optional, default to NAME)
     * @param order sort order (optional, default to ASC)
     * @return ApiResponse&lt;ApisJson&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<ApisJson> getOwnerApisWithHttpInfo(String owner, Integer page, Integer limit, String sort, String order) throws ApiException {
        com.squareup.okhttp.Call call = getOwnerApisCall(owner, page, limit, sort, order, null, null);
        Type localVarReturnType = new TypeToken<ApisJson>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Retrieves an APIs.json listing of all APIs defined for this owner (asynchronously)
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
    public com.squareup.okhttp.Call getOwnerApisAsync(String owner, Integer page, Integer limit, String sort, String order, final ApiCallback<ApisJson> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getOwnerApisCall(owner, page, limit, sort, order, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<ApisJson>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for getYamlDefinition */
    private com.squareup.okhttp.Call getYamlDefinitionCall(String owner, String api, String version, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling getYamlDefinition(Async)");
        }
        
        // verify the required parameter 'api' is set
        if (api == null) {
            throw new ApiException("Missing the required parameter 'api' when calling getYamlDefinition(Async)");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new ApiException("Missing the required parameter 'version' when calling getYamlDefinition(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/apis/{owner}/{api}/{version}/swagger.yaml".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()))
        .replaceAll("\\{" + "api" + "\\}", apiClient.escapeString(api.toString()))
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
     * Retrieves the Swagger definition for the specified API and version in YAML format
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param version version identifier (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Object getYamlDefinition(String owner, String api, String version) throws ApiException {
        ApiResponse<Object> resp = getYamlDefinitionWithHttpInfo(owner, api, version);
        return resp.getData();
    }

    /**
     * Retrieves the Swagger definition for the specified API and version in YAML format
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param version version identifier (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Object> getYamlDefinitionWithHttpInfo(String owner, String api, String version) throws ApiException {
        com.squareup.okhttp.Call call = getYamlDefinitionCall(owner, api, version, null, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Retrieves the Swagger definition for the specified API and version in YAML format (asynchronously)
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param version version identifier (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getYamlDefinitionAsync(String owner, String api, String version, final ApiCallback<Object> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getYamlDefinitionCall(owner, api, version, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for saveDefinition */
    private com.squareup.okhttp.Call saveDefinitionCall(String owner, String api, String definition, Boolean isPrivate, String version, Boolean force, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = definition;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling saveDefinition(Async)");
        }
        
        // verify the required parameter 'api' is set
        if (api == null) {
            throw new ApiException("Missing the required parameter 'api' when calling saveDefinition(Async)");
        }
        
        // verify the required parameter 'definition' is set
        if (definition == null) {
            throw new ApiException("Missing the required parameter 'definition' when calling saveDefinition(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/apis/{owner}/{api}".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()))
        .replaceAll("\\{" + "api" + "\\}", apiClient.escapeString(api.toString()));

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
     * Saves the provided Swagger definition
     * Saves the provided Swagger definition; the owner must match the token owner. The version will be extracted from the Swagger definitions itself.
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param definition the Swagger definition of this API (required)
     * @param isPrivate Defines whether the API has to be private (optional, default to false)
     * @param version api version (optional)
     * @param force force update (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void saveDefinition(String owner, String api, String definition, Boolean isPrivate, String version, Boolean force) throws ApiException {
        saveDefinitionWithHttpInfo(owner, api, definition, isPrivate, version, force);
    }

    /**
     * Saves the provided Swagger definition
     * Saves the provided Swagger definition; the owner must match the token owner. The version will be extracted from the Swagger definitions itself.
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param definition the Swagger definition of this API (required)
     * @param isPrivate Defines whether the API has to be private (optional, default to false)
     * @param version api version (optional)
     * @param force force update (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> saveDefinitionWithHttpInfo(String owner, String api, String definition, Boolean isPrivate, String version, Boolean force) throws ApiException {
        com.squareup.okhttp.Call call = saveDefinitionCall(owner, api, definition, isPrivate, version, force, null, null);
        return apiClient.execute(call);
    }

    /**
     * Saves the provided Swagger definition (asynchronously)
     * Saves the provided Swagger definition; the owner must match the token owner. The version will be extracted from the Swagger definitions itself.
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param definition the Swagger definition of this API (required)
     * @param isPrivate Defines whether the API has to be private (optional, default to false)
     * @param version api version (optional)
     * @param force force update (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call saveDefinitionAsync(String owner, String api, String definition, Boolean isPrivate, String version, Boolean force, final ApiCallback<Void> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = saveDefinitionCall(owner, api, definition, isPrivate, version, force, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /* Build call for searchApis */
    private com.squareup.okhttp.Call searchApisCall(String query, String state, List<String> tag, Integer page, Integer limit, String sort, String order, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        

        // create path and map variables
        String localVarPath = "/apis".replaceAll("\\{format\\}","json");

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
     * Retrieves a list of currently defined APIs in APIs.json format.
     * 
     * @param query free text query to match (optional)
     * @param state matches against published state of the spec * UNPUBLISHED - spec is a draft, a work in progress * PUBLISHED - spec is a stable version ready for consuming from client applications * ANY - either PUBLISHED or UNPUBLISHED  (optional, default to ALL)
     * @param tag matches against tags associated with an API (optional)
     * @param page page to return (optional, default to 0)
     * @param limit number of results per page (optional, default to 10)
     * @param sort sort criteria or result set * NAME - * UPATED * CREATED * OWNER  (optional, default to NAME)
     * @param order sort order (optional, default to ASC)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void searchApis(String query, String state, List<String> tag, Integer page, Integer limit, String sort, String order) throws ApiException {
        searchApisWithHttpInfo(query, state, tag, page, limit, sort, order);
    }

    /**
     * Retrieves a list of currently defined APIs in APIs.json format.
     * 
     * @param query free text query to match (optional)
     * @param state matches against published state of the spec * UNPUBLISHED - spec is a draft, a work in progress * PUBLISHED - spec is a stable version ready for consuming from client applications * ANY - either PUBLISHED or UNPUBLISHED  (optional, default to ALL)
     * @param tag matches against tags associated with an API (optional)
     * @param page page to return (optional, default to 0)
     * @param limit number of results per page (optional, default to 10)
     * @param sort sort criteria or result set * NAME - * UPATED * CREATED * OWNER  (optional, default to NAME)
     * @param order sort order (optional, default to ASC)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> searchApisWithHttpInfo(String query, String state, List<String> tag, Integer page, Integer limit, String sort, String order) throws ApiException {
        com.squareup.okhttp.Call call = searchApisCall(query, state, tag, page, limit, sort, order, null, null);
        return apiClient.execute(call);
    }

    /**
     * Retrieves a list of currently defined APIs in APIs.json format. (asynchronously)
     * 
     * @param query free text query to match (optional)
     * @param state matches against published state of the spec * UNPUBLISHED - spec is a draft, a work in progress * PUBLISHED - spec is a stable version ready for consuming from client applications * ANY - either PUBLISHED or UNPUBLISHED  (optional, default to ALL)
     * @param tag matches against tags associated with an API (optional)
     * @param page page to return (optional, default to 0)
     * @param limit number of results per page (optional, default to 10)
     * @param sort sort criteria or result set * NAME - * UPATED * CREATED * OWNER  (optional, default to NAME)
     * @param order sort order (optional, default to ASC)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call searchApisAsync(String query, String state, List<String> tag, Integer page, Integer limit, String sort, String order, final ApiCallback<Void> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = searchApisCall(query, state, tag, page, limit, sort, order, progressListener, progressRequestListener);
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
    /* Build call for updateCollaboration */
    private com.squareup.okhttp.Call updateCollaborationCall(String owner, String api, Collaboration body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = body;
        
        // verify the required parameter 'owner' is set
        if (owner == null) {
            throw new ApiException("Missing the required parameter 'owner' when calling updateCollaboration(Async)");
        }
        
        // verify the required parameter 'api' is set
        if (api == null) {
            throw new ApiException("Missing the required parameter 'api' when calling updateCollaboration(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/apis/{owner}/{api}/.collaboration".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "owner" + "\\}", apiClient.escapeString(owner.toString()))
        .replaceAll("\\{" + "api" + "\\}", apiClient.escapeString(api.toString()));

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
        return apiClient.buildCall(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    /**
     * Updates API&#39;s collaboration
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param body  (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void updateCollaboration(String owner, String api, Collaboration body) throws ApiException {
        updateCollaborationWithHttpInfo(owner, api, body);
    }

    /**
     * Updates API&#39;s collaboration
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param body  (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> updateCollaborationWithHttpInfo(String owner, String api, Collaboration body) throws ApiException {
        com.squareup.okhttp.Call call = updateCollaborationCall(owner, api, body, null, null);
        return apiClient.execute(call);
    }

    /**
     * Updates API&#39;s collaboration (asynchronously)
     * 
     * @param owner API or Domaion owner identifier (required)
     * @param api API identifier (required)
     * @param body  (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call updateCollaborationAsync(String owner, String api, Collaboration body, final ApiCallback<Void> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = updateCollaborationCall(owner, api, body, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
}
