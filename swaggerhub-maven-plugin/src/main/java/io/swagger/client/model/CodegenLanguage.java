package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CodegenLanguage
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class CodegenLanguage {
  @SerializedName("visible")
  private Boolean visible = true;

  @SerializedName("customValues")
  private Map<String, String> customValues = new HashMap<String, String>();

  public CodegenLanguage visible(Boolean visible) {
    this.visible = visible;
    return this;
  }

   /**
   * Get visible
   * @return visible
  **/
  @ApiModelProperty(required = true, value = "")
  public Boolean getVisible() {
    return visible;
  }

  public void setVisible(Boolean visible) {
    this.visible = visible;
  }

  public CodegenLanguage customValues(Map<String, String> customValues) {
    this.customValues = customValues;
    return this;
  }

  public CodegenLanguage putCustomValuesItem(String key, String customValuesItem) {
    this.customValues.put(key, customValuesItem);
    return this;
  }

   /**
   * Get customValues
   * @return customValues
  **/
  @ApiModelProperty(value = "")
  public Map<String, String> getCustomValues() {
    return customValues;
  }

  public void setCustomValues(Map<String, String> customValues) {
    this.customValues = customValues;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CodegenLanguage codegenLanguage = (CodegenLanguage) o;
    return Objects.equals(this.visible, codegenLanguage.visible) &&
        Objects.equals(this.customValues, codegenLanguage.customValues);
  }

  @Override
  public int hashCode() {
    return Objects.hash(visible, customValues);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CodegenLanguage {\n");
    
    sb.append("    visible: ").append(toIndentedString(visible)).append("\n");
    sb.append("    customValues: ").append(toIndentedString(customValues)).append("\n");
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

