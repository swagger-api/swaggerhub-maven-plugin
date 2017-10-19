package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.CodegenLanguage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CodegenSettings
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class CodegenSettings {
  @SerializedName("server")
  private Map<String, CodegenLanguage> server = new HashMap<String, CodegenLanguage>();

  @SerializedName("client")
  private Map<String, CodegenLanguage> client = new HashMap<String, CodegenLanguage>();

  public CodegenSettings server(Map<String, CodegenLanguage> server) {
    this.server = server;
    return this;
  }

  public CodegenSettings putServerItem(String key, CodegenLanguage serverItem) {
    this.server.put(key, serverItem);
    return this;
  }

   /**
   * Get server
   * @return server
  **/
  @ApiModelProperty(value = "")
  public Map<String, CodegenLanguage> getServer() {
    return server;
  }

  public void setServer(Map<String, CodegenLanguage> server) {
    this.server = server;
  }

  public CodegenSettings client(Map<String, CodegenLanguage> client) {
    this.client = client;
    return this;
  }

  public CodegenSettings putClientItem(String key, CodegenLanguage clientItem) {
    this.client.put(key, clientItem);
    return this;
  }

   /**
   * Get client
   * @return client
  **/
  @ApiModelProperty(value = "")
  public Map<String, CodegenLanguage> getClient() {
    return client;
  }

  public void setClient(Map<String, CodegenLanguage> client) {
    this.client = client;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CodegenSettings codegenSettings = (CodegenSettings) o;
    return Objects.equals(this.server, codegenSettings.server) &&
        Objects.equals(this.client, codegenSettings.client);
  }

  @Override
  public int hashCode() {
    return Objects.hash(server, client);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CodegenSettings {\n");
    
    sb.append("    server: ").append(toIndentedString(server)).append("\n");
    sb.append("    client: ").append(toIndentedString(client)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

