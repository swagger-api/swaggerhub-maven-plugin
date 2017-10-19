package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * GitHubExportSettings
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class GitHubExportSettings {
  @SerializedName("token")
  private String token = null;

  @SerializedName("owner")
  private String owner = null;

  @SerializedName("repository")
  private String repository = null;

  @SerializedName("branch")
  private String branch = null;

  @SerializedName("path")
  private String path = null;

  @SerializedName("yamlPath")
  private String yamlPath = null;

  @SerializedName("notificationEmail")
  private String notificationEmail = null;

  public GitHubExportSettings token(String token) {
    this.token = token;
    return this;
  }

   /**
   * Get token
   * @return token
  **/
  @ApiModelProperty(required = true, value = "")
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public GitHubExportSettings owner(String owner) {
    this.owner = owner;
    return this;
  }

   /**
   * Get owner
   * @return owner
  **/
  @ApiModelProperty(required = true, value = "")
  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public GitHubExportSettings repository(String repository) {
    this.repository = repository;
    return this;
  }

   /**
   * Get repository
   * @return repository
  **/
  @ApiModelProperty(required = true, value = "")
  public String getRepository() {
    return repository;
  }

  public void setRepository(String repository) {
    this.repository = repository;
  }

  public GitHubExportSettings branch(String branch) {
    this.branch = branch;
    return this;
  }

   /**
   * Get branch
   * @return branch
  **/
  @ApiModelProperty(required = true, value = "")
  public String getBranch() {
    return branch;
  }

  public void setBranch(String branch) {
    this.branch = branch;
  }

  public GitHubExportSettings path(String path) {
    this.path = path;
    return this;
  }

   /**
   * Get path
   * @return path
  **/
  @ApiModelProperty(value = "")
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public GitHubExportSettings yamlPath(String yamlPath) {
    this.yamlPath = yamlPath;
    return this;
  }

   /**
   * Get yamlPath
   * @return yamlPath
  **/
  @ApiModelProperty(value = "")
  public String getYamlPath() {
    return yamlPath;
  }

  public void setYamlPath(String yamlPath) {
    this.yamlPath = yamlPath;
  }

  public GitHubExportSettings notificationEmail(String notificationEmail) {
    this.notificationEmail = notificationEmail;
    return this;
  }

   /**
   * Get notificationEmail
   * @return notificationEmail
  **/
  @ApiModelProperty(required = true, value = "")
  public String getNotificationEmail() {
    return notificationEmail;
  }

  public void setNotificationEmail(String notificationEmail) {
    this.notificationEmail = notificationEmail;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GitHubExportSettings gitHubExportSettings = (GitHubExportSettings) o;
    return Objects.equals(this.token, gitHubExportSettings.token) &&
        Objects.equals(this.owner, gitHubExportSettings.owner) &&
        Objects.equals(this.repository, gitHubExportSettings.repository) &&
        Objects.equals(this.branch, gitHubExportSettings.branch) &&
        Objects.equals(this.path, gitHubExportSettings.path) &&
        Objects.equals(this.yamlPath, gitHubExportSettings.yamlPath) &&
        Objects.equals(this.notificationEmail, gitHubExportSettings.notificationEmail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token, owner, repository, branch, path, yamlPath, notificationEmail);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GitHubExportSettings {\n");
    
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    repository: ").append(toIndentedString(repository)).append("\n");
    sb.append("    branch: ").append(toIndentedString(branch)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    yamlPath: ").append(toIndentedString(yamlPath)).append("\n");
    sb.append("    notificationEmail: ").append(toIndentedString(notificationEmail)).append("\n");
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

