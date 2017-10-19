package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.ComparisonDetail;

/**
 * a part of the API that was (or wasn&#39;t) changed
 */
@ApiModel(description = "a part of the API that was (or wasn't) changed")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class ComparisonPart {
  /**
   * the type of change that this part describes * UNMODIFIED - this part is the same in both APIs. Only available for FULL compare. * INSERTED - this is present in `other` but missing in `base`. `base` will not be present. * DELETED - this is present in `base` but missing in `other`. `other` will not be present. * CHANGED - this has been changed between `base` and `other`. Both `base` and `other` present. 
   */
  public enum TypeEnum {
    @SerializedName("UNMODIFIED")
    UNMODIFIED("UNMODIFIED"),
    
    @SerializedName("INSERTED")
    INSERTED("INSERTED"),
    
    @SerializedName("DELETED")
    DELETED("DELETED"),
    
    @SerializedName("CHANGED")
    CHANGED("CHANGED");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
  }

  @SerializedName("type")
  private TypeEnum type = null;

  @SerializedName("base")
  private ComparisonDetail base = null;

  @SerializedName("other")
  private ComparisonDetail other = null;

  public ComparisonPart type(TypeEnum type) {
    this.type = type;
    return this;
  }

   /**
   * the type of change that this part describes * UNMODIFIED - this part is the same in both APIs. Only available for FULL compare. * INSERTED - this is present in `other` but missing in `base`. `base` will not be present. * DELETED - this is present in `base` but missing in `other`. `other` will not be present. * CHANGED - this has been changed between `base` and `other`. Both `base` and `other` present. 
   * @return type
  **/
  @ApiModelProperty(required = true, value = "the type of change that this part describes * UNMODIFIED - this part is the same in both APIs. Only available for FULL compare. * INSERTED - this is present in `other` but missing in `base`. `base` will not be present. * DELETED - this is present in `base` but missing in `other`. `other` will not be present. * CHANGED - this has been changed between `base` and `other`. Both `base` and `other` present. ")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public ComparisonPart base(ComparisonDetail base) {
    this.base = base;
    return this;
  }

   /**
   * Get base
   * @return base
  **/
  @ApiModelProperty(value = "")
  public ComparisonDetail getBase() {
    return base;
  }

  public void setBase(ComparisonDetail base) {
    this.base = base;
  }

  public ComparisonPart other(ComparisonDetail other) {
    this.other = other;
    return this;
  }

   /**
   * Get other
   * @return other
  **/
  @ApiModelProperty(value = "")
  public ComparisonDetail getOther() {
    return other;
  }

  public void setOther(ComparisonDetail other) {
    this.other = other;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ComparisonPart comparisonPart = (ComparisonPart) o;
    return Objects.equals(this.type, comparisonPart.type) &&
        Objects.equals(this.base, comparisonPart.base) &&
        Objects.equals(this.other, comparisonPart.other);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, base, other);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComparisonPart {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    base: ").append(toIndentedString(base)).append("\n");
    sb.append("    other: ").append(toIndentedString(other)).append("\n");
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

