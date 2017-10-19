package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.CodegenSettings;

/**
 * VersionMetadata
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class VersionMetadata {
  @SerializedName("codegenSettings")
  private CodegenSettings codegenSettings = null;

  public VersionMetadata codegenSettings(CodegenSettings codegenSettings) {
    this.codegenSettings = codegenSettings;
    return this;
  }

   /**
   * Get codegenSettings
   * @return codegenSettings
  **/
  @ApiModelProperty(value = "")
  public CodegenSettings getCodegenSettings() {
    return codegenSettings;
  }

  public void setCodegenSettings(CodegenSettings codegenSettings) {
    this.codegenSettings = codegenSettings;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VersionMetadata versionMetadata = (VersionMetadata) o;
    return Objects.equals(this.codegenSettings, versionMetadata.codegenSettings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codegenSettings);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VersionMetadata {\n");
    
    sb.append("    codegenSettings: ").append(toIndentedString(codegenSettings)).append("\n");
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

