package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.CollaborationMember;
import io.swagger.client.model.CollaborationMembership;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

/**
 * CollaborationTeamMembership
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class CollaborationTeamMembership extends CollaborationMembership {
  @SerializedName("members")
  private List<CollaborationMember> members = new ArrayList<CollaborationMember>();

  public CollaborationTeamMembership members(List<CollaborationMember> members) {
    this.members = members;
    return this;
  }

  public CollaborationTeamMembership addMembersItem(CollaborationMember membersItem) {
    this.members.add(membersItem);
    return this;
  }

   /**
   * Get members
   * @return members
  **/
  @ApiModelProperty(value = "")
  public List<CollaborationMember> getMembers() {
    return members;
  }

  public void setMembers(List<CollaborationMember> members) {
    this.members = members;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollaborationTeamMembership collaborationTeamMembership = (CollaborationTeamMembership) o;
    return Objects.equals(this.members, collaborationTeamMembership.members) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(members, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollaborationTeamMembership {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    members: ").append(toIndentedString(members)).append("\n");
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

