package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.SimpleSpec;
import java.util.ArrayList;
import java.util.List;

/**
 * MissingSpecMembers
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class MissingSpecMembers {
  @SerializedName("spec")
  private SimpleSpec spec = null;

  @SerializedName("names")
  private List<String> names = new ArrayList<String>();

  public MissingSpecMembers spec(SimpleSpec spec) {
    this.spec = spec;
    return this;
  }

   /**
   * Get spec
   * @return spec
  **/
  @ApiModelProperty(value = "")
  public SimpleSpec getSpec() {
    return spec;
  }

  public void setSpec(SimpleSpec spec) {
    this.spec = spec;
  }

  public MissingSpecMembers names(List<String> names) {
    this.names = names;
    return this;
  }

  public MissingSpecMembers addNamesItem(String namesItem) {
    this.names.add(namesItem);
    return this;
  }

   /**
   * Get names
   * @return names
  **/
  @ApiModelProperty(value = "")
  public List<String> getNames() {
    return names;
  }

  public void setNames(List<String> names) {
    this.names = names;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MissingSpecMembers missingSpecMembers = (MissingSpecMembers) o;
    return Objects.equals(this.spec, missingSpecMembers.spec) &&
        Objects.equals(this.names, missingSpecMembers.names);
  }

  @Override
  public int hashCode() {
    return Objects.hash(spec, names);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MissingSpecMembers {\n");
    
    sb.append("    spec: ").append(toIndentedString(spec)).append("\n");
    sb.append("    names: ").append(toIndentedString(names)).append("\n");
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

