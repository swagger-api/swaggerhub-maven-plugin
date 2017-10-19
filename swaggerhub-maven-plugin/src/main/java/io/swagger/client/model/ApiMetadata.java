package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.ApiMetadataLink;
import java.util.ArrayList;
import java.util.List;

/**
 * ApiMetadata
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class ApiMetadata {
  @SerializedName("defaultVersion")
  private String defaultVersion = null;

  @SerializedName("categories")
  private List<String> categories = new ArrayList<String>();

  @SerializedName("tags")
  private List<String> tags = new ArrayList<String>();

  @SerializedName("links")
  private List<ApiMetadataLink> links = new ArrayList<ApiMetadataLink>();

  public ApiMetadata defaultVersion(String defaultVersion) {
    this.defaultVersion = defaultVersion;
    return this;
  }

   /**
   * Get defaultVersion
   * @return defaultVersion
  **/
  @ApiModelProperty(value = "")
  public String getDefaultVersion() {
    return defaultVersion;
  }

  public void setDefaultVersion(String defaultVersion) {
    this.defaultVersion = defaultVersion;
  }

  public ApiMetadata categories(List<String> categories) {
    this.categories = categories;
    return this;
  }

  public ApiMetadata addCategoriesItem(String categoriesItem) {
    this.categories.add(categoriesItem);
    return this;
  }

   /**
   * Get categories
   * @return categories
  **/
  @ApiModelProperty(required = true, value = "")
  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public ApiMetadata tags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  public ApiMetadata addTagsItem(String tagsItem) {
    this.tags.add(tagsItem);
    return this;
  }

   /**
   * Get tags
   * @return tags
  **/
  @ApiModelProperty(required = true, value = "")
  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public ApiMetadata links(List<ApiMetadataLink> links) {
    this.links = links;
    return this;
  }

  public ApiMetadata addLinksItem(ApiMetadataLink linksItem) {
    this.links.add(linksItem);
    return this;
  }

   /**
   * Get links
   * @return links
  **/
  @ApiModelProperty(value = "")
  public List<ApiMetadataLink> getLinks() {
    return links;
  }

  public void setLinks(List<ApiMetadataLink> links) {
    this.links = links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiMetadata apiMetadata = (ApiMetadata) o;
    return Objects.equals(this.defaultVersion, apiMetadata.defaultVersion) &&
        Objects.equals(this.categories, apiMetadata.categories) &&
        Objects.equals(this.tags, apiMetadata.tags) &&
        Objects.equals(this.links, apiMetadata.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(defaultVersion, categories, tags, links);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiMetadata {\n");
    
    sb.append("    defaultVersion: ").append(toIndentedString(defaultVersion)).append("\n");
    sb.append("    categories: ").append(toIndentedString(categories)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

