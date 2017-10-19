package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.CollaborationHint;
import io.swagger.client.model.CollaborationMembership;
import io.swagger.client.model.CollaborationTeamMembership;
import java.util.ArrayList;
import java.util.List;

/**
 * Collaboration
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class Collaboration {
  @SerializedName("owner")
  private String owner = null;

  @SerializedName("owners")
  private List<String> owners = new ArrayList<String>();

  @SerializedName("hint")
  private CollaborationHint hint = null;

  @SerializedName("members")
  private List<CollaborationMembership> members = new ArrayList<CollaborationMembership>();

  @SerializedName("pendingMembers")
  private List<CollaborationMembership> pendingMembers = new ArrayList<CollaborationMembership>();

  @SerializedName("teams")
  private List<CollaborationTeamMembership> teams = new ArrayList<CollaborationTeamMembership>();

   /**
   * Get owner
   * @return owner
  **/
  @ApiModelProperty(value = "")
  public String getOwner() {
    return owner;
  }

  public Collaboration owners(List<String> owners) {
    this.owners = owners;
    return this;
  }

  public Collaboration addOwnersItem(String ownersItem) {
    this.owners.add(ownersItem);
    return this;
  }

   /**
   * Get owners
   * @return owners
  **/
  @ApiModelProperty(value = "")
  public List<String> getOwners() {
    return owners;
  }

  public void setOwners(List<String> owners) {
    this.owners = owners;
  }

  public Collaboration hint(CollaborationHint hint) {
    this.hint = hint;
    return this;
  }

   /**
   * Get hint
   * @return hint
  **/
  @ApiModelProperty(required = true, value = "")
  public CollaborationHint getHint() {
    return hint;
  }

  public void setHint(CollaborationHint hint) {
    this.hint = hint;
  }

  public Collaboration members(List<CollaborationMembership> members) {
    this.members = members;
    return this;
  }

  public Collaboration addMembersItem(CollaborationMembership membersItem) {
    this.members.add(membersItem);
    return this;
  }

   /**
   * Get members
   * @return members
  **/
  @ApiModelProperty(value = "")
  public List<CollaborationMembership> getMembers() {
    return members;
  }

  public void setMembers(List<CollaborationMembership> members) {
    this.members = members;
  }

  public Collaboration pendingMembers(List<CollaborationMembership> pendingMembers) {
    this.pendingMembers = pendingMembers;
    return this;
  }

  public Collaboration addPendingMembersItem(CollaborationMembership pendingMembersItem) {
    this.pendingMembers.add(pendingMembersItem);
    return this;
  }

   /**
   * Get pendingMembers
   * @return pendingMembers
  **/
  @ApiModelProperty(value = "")
  public List<CollaborationMembership> getPendingMembers() {
    return pendingMembers;
  }

  public void setPendingMembers(List<CollaborationMembership> pendingMembers) {
    this.pendingMembers = pendingMembers;
  }

  public Collaboration teams(List<CollaborationTeamMembership> teams) {
    this.teams = teams;
    return this;
  }

  public Collaboration addTeamsItem(CollaborationTeamMembership teamsItem) {
    this.teams.add(teamsItem);
    return this;
  }

   /**
   * Get teams
   * @return teams
  **/
  @ApiModelProperty(value = "")
  public List<CollaborationTeamMembership> getTeams() {
    return teams;
  }

  public void setTeams(List<CollaborationTeamMembership> teams) {
    this.teams = teams;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Collaboration collaboration = (Collaboration) o;
    return Objects.equals(this.owner, collaboration.owner) &&
        Objects.equals(this.owners, collaboration.owners) &&
        Objects.equals(this.hint, collaboration.hint) &&
        Objects.equals(this.members, collaboration.members) &&
        Objects.equals(this.pendingMembers, collaboration.pendingMembers) &&
        Objects.equals(this.teams, collaboration.teams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(owner, owners, hint, members, pendingMembers, teams);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Collaboration {\n");
    
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    owners: ").append(toIndentedString(owners)).append("\n");
    sb.append("    hint: ").append(toIndentedString(hint)).append("\n");
    sb.append("    members: ").append(toIndentedString(members)).append("\n");
    sb.append("    pendingMembers: ").append(toIndentedString(pendingMembers)).append("\n");
    sb.append("    teams: ").append(toIndentedString(teams)).append("\n");
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

