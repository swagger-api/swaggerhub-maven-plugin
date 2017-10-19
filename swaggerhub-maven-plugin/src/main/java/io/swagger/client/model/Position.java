package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * a position in a text document
 */
@ApiModel(description = "a position in a text document")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class Position {
  @SerializedName("line")
  private Integer line = null;

  @SerializedName("column")
  private Integer column = null;

  public Position line(Integer line) {
    this.line = line;
    return this;
  }

   /**
   * Get line
   * @return line
  **/
  @ApiModelProperty(required = true, value = "")
  public Integer getLine() {
    return line;
  }

  public void setLine(Integer line) {
    this.line = line;
  }

  public Position column(Integer column) {
    this.column = column;
    return this;
  }

   /**
   * Get column
   * @return column
  **/
  @ApiModelProperty(required = true, value = "")
  public Integer getColumn() {
    return column;
  }

  public void setColumn(Integer column) {
    this.column = column;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Position position = (Position) o;
    return Objects.equals(this.line, position.line) &&
        Objects.equals(this.column, position.column);
  }

  @Override
  public int hashCode() {
    return Objects.hash(line, column);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Position {\n");
    
    sb.append("    line: ").append(toIndentedString(line)).append("\n");
    sb.append("    column: ").append(toIndentedString(column)).append("\n");
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

