package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * CollaborationRoles
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class CollaborationRoles {
  @SerializedName("owner")
  private String owner = null;

  @SerializedName("api")
  private String api = null;

  /**
   * Gets or Sets roles
   */
  public enum RolesEnum {
    @SerializedName("EDIT")
    EDIT("EDIT"),
    
    @SerializedName("COMMENT")
    COMMENT("COMMENT"),
    
    @SerializedName("VIEW")
    VIEW("VIEW");

    private String value;

    RolesEnum(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
  }

  @SerializedName("roles")
  private List<RolesEnum> roles = new ArrayList<RolesEnum>();

  public CollaborationRoles owner(String owner) {
    this.owner = owner;
    return this;
  }

   /**
   * Get owner
   * @return owner
  **/
  @ApiModelProperty(value = "")
  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public CollaborationRoles api(String api) {
    this.api = api;
    return this;
  }

   /**
   * Get api
   * @return api
  **/
  @ApiModelProperty(value = "")
  public String getApi() {
    return api;
  }

  public void setApi(String api) {
    this.api = api;
  }

  public CollaborationRoles roles(List<RolesEnum> roles) {
    this.roles = roles;
    return this;
  }

  public CollaborationRoles addRolesItem(RolesEnum rolesItem) {
    this.roles.add(rolesItem);
    return this;
  }

   /**
   * Get roles
   * @return roles
  **/
  @ApiModelProperty(value = "")
  public List<RolesEnum> getRoles() {
    return roles;
  }

  public void setRoles(List<RolesEnum> roles) {
    this.roles = roles;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollaborationRoles collaborationRoles = (CollaborationRoles) o;
    return Objects.equals(this.owner, collaborationRoles.owner) &&
        Objects.equals(this.api, collaborationRoles.api) &&
        Objects.equals(this.roles, collaborationRoles.roles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(owner, api, roles);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollaborationRoles {\n");
    
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    api: ").append(toIndentedString(api)).append("\n");
    sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
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

