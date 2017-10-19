package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.CollaborationMember;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

/**
 * CollaborationMembership
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class CollaborationMembership extends CollaborationMember {
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

  @SerializedName("donotdisturb")
  private Boolean donotdisturb = null;

  @SerializedName("blocked")
  private Boolean blocked = null;

  public CollaborationMembership roles(List<RolesEnum> roles) {
    this.roles = roles;
    return this;
  }

  public CollaborationMembership addRolesItem(RolesEnum rolesItem) {
    this.roles.add(rolesItem);
    return this;
  }

   /**
   * Get roles
   * @return roles
  **/
  @ApiModelProperty(required = true, value = "")
  public List<RolesEnum> getRoles() {
    return roles;
  }

  public void setRoles(List<RolesEnum> roles) {
    this.roles = roles;
  }

   /**
   * Get donotdisturb
   * @return donotdisturb
  **/
  @ApiModelProperty(value = "")
  public Boolean getDonotdisturb() {
    return donotdisturb;
  }

   /**
   * Get blocked
   * @return blocked
  **/
  @ApiModelProperty(value = "")
  public Boolean getBlocked() {
    return blocked;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollaborationMembership collaborationMembership = (CollaborationMembership) o;
    return Objects.equals(this.roles, collaborationMembership.roles) &&
        Objects.equals(this.donotdisturb, collaborationMembership.donotdisturb) &&
        Objects.equals(this.blocked, collaborationMembership.blocked) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roles, donotdisturb, blocked, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollaborationMembership {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
    sb.append("    donotdisturb: ").append(toIndentedString(donotdisturb)).append("\n");
    sb.append("    blocked: ").append(toIndentedString(blocked)).append("\n");
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

