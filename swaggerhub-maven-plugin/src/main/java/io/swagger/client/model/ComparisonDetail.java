package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.Position;

/**
 * details for one side of a ComparisonPart
 */
@ApiModel(description = "details for one side of a ComparisonPart")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class ComparisonDetail {
  @SerializedName("startPosition")
  private Position startPosition = null;

  @SerializedName("endPosition")
  private Position endPosition = null;

  @SerializedName("content")
  private String content = null;

  @SerializedName("path")
  private String path = null;

  public ComparisonDetail startPosition(Position startPosition) {
    this.startPosition = startPosition;
    return this;
  }

   /**
   * Get startPosition
   * @return startPosition
  **/
  @ApiModelProperty(required = true, value = "")
  public Position getStartPosition() {
    return startPosition;
  }

  public void setStartPosition(Position startPosition) {
    this.startPosition = startPosition;
  }

  public ComparisonDetail endPosition(Position endPosition) {
    this.endPosition = endPosition;
    return this;
  }

   /**
   * Get endPosition
   * @return endPosition
  **/
  @ApiModelProperty(required = true, value = "")
  public Position getEndPosition() {
    return endPosition;
  }

  public void setEndPosition(Position endPosition) {
    this.endPosition = endPosition;
  }

  public ComparisonDetail content(String content) {
    this.content = content;
    return this;
  }

   /**
   * the content of the API definition between `startPosition` and `endPosition`
   * @return content
  **/
  @ApiModelProperty(required = true, value = "the content of the API definition between `startPosition` and `endPosition`")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public ComparisonDetail path(String path) {
    this.path = path;
    return this;
  }

   /**
   * the path to the object (notation similar to JSONPath)
   * @return path
  **/
  @ApiModelProperty(value = "the path to the object (notation similar to JSONPath)")
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ComparisonDetail comparisonDetail = (ComparisonDetail) o;
    return Objects.equals(this.startPosition, comparisonDetail.startPosition) &&
        Objects.equals(this.endPosition, comparisonDetail.endPosition) &&
        Objects.equals(this.content, comparisonDetail.content) &&
        Objects.equals(this.path, comparisonDetail.path);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startPosition, endPosition, content, path);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComparisonDetail {\n");
    
    sb.append("    startPosition: ").append(toIndentedString(startPosition)).append("\n");
    sb.append("    endPosition: ").append(toIndentedString(endPosition)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
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

