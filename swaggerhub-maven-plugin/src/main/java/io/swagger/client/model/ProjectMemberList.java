package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.ProjectMember;
import java.util.ArrayList;
import java.util.List;

/**
 * ProjectMemberList
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class ProjectMemberList {
  @SerializedName("members")
  private List<ProjectMember> members = new ArrayList<ProjectMember>();

  public ProjectMemberList members(List<ProjectMember> members) {
    this.members = members;
    return this;
  }

  public ProjectMemberList addMembersItem(ProjectMember membersItem) {
    this.members.add(membersItem);
    return this;
  }

   /**
   * Get members
   * @return members
  **/
  @ApiModelProperty(value = "")
  public List<ProjectMember> getMembers() {
    return members;
  }

  public void setMembers(List<ProjectMember> members) {
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
    ProjectMemberList projectMemberList = (ProjectMemberList) o;
    return Objects.equals(this.members, projectMemberList.members);
  }

  @Override
  public int hashCode() {
    return Objects.hash(members);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectMemberList {\n");
    
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

