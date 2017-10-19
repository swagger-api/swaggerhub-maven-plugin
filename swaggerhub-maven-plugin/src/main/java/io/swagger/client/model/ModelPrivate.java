package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ModelPrivate
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class ModelPrivate {
  @SerializedName("private")
  private Boolean _private = null;

  public ModelPrivate _private(Boolean _private) {
    this._private = _private;
    return this;
  }

   /**
   * Get _private
   * @return _private
  **/
  @ApiModelProperty(required = true, value = "")
  public Boolean getPrivate() {
    return _private;
  }

  public void setPrivate(Boolean _private) {
    this._private = _private;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelPrivate _private = (ModelPrivate) o;
    return Objects.equals(this._private, _private._private);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_private);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelPrivate {\n");
    
    sb.append("    _private: ").append(toIndentedString(_private)).append("\n");
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

