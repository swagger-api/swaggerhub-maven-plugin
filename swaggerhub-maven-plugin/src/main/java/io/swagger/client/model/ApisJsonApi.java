package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.ApisJsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * ApisJsonApi
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class ApisJsonApi {
  @SerializedName("name")
  private String name = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("tags")
  private List<String> tags = new ArrayList<String>();

  @SerializedName("properties")
  private List<ApisJsonProperty> properties = new ArrayList<ApisJsonProperty>();

  public ApisJsonApi name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ApisJsonApi description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ApisJsonApi tags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  public ApisJsonApi addTagsItem(String tagsItem) {
    this.tags.add(tagsItem);
    return this;
  }

   /**
   * Get tags
   * @return tags
  **/
  @ApiModelProperty(value = "")
  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public ApisJsonApi properties(List<ApisJsonProperty> properties) {
    this.properties = properties;
    return this;
  }

  public ApisJsonApi addPropertiesItem(ApisJsonProperty propertiesItem) {
    this.properties.add(propertiesItem);
    return this;
  }

   /**
   * Get properties
   * @return properties
  **/
  @ApiModelProperty(value = "")
  public List<ApisJsonProperty> getProperties() {
    return properties;
  }

  public void setProperties(List<ApisJsonProperty> properties) {
    this.properties = properties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApisJsonApi apisJsonApi = (ApisJsonApi) o;
    return Objects.equals(this.name, apisJsonApi.name) &&
        Objects.equals(this.description, apisJsonApi.description) &&
        Objects.equals(this.tags, apisJsonApi.tags) &&
        Objects.equals(this.properties, apisJsonApi.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, tags, properties);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApisJsonApi {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
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

