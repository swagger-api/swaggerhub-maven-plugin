package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * LifecycleSettings
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class LifecycleSettings {
  @SerializedName("published")
  private Boolean published = null;

  public LifecycleSettings published(Boolean published) {
    this.published = published;
    return this;
  }

   /**
   * Get published
   * @return published
  **/
  @ApiModelProperty(value = "")
  public Boolean getPublished() {
    return published;
  }

  public void setPublished(Boolean published) {
    this.published = published;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LifecycleSettings lifecycleSettings = (LifecycleSettings) o;
    return Objects.equals(this.published, lifecycleSettings.published);
  }

  @Override
  public int hashCode() {
    return Objects.hash(published);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LifecycleSettings {\n");
    
    sb.append("    published: ").append(toIndentedString(published)).append("\n");
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

