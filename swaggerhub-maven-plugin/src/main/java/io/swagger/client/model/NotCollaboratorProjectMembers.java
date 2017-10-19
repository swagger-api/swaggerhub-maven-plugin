package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.MissingSpecMembers;
import java.util.ArrayList;
import java.util.List;

/**
 * NotCollaboratorProjectMembers
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class NotCollaboratorProjectMembers {
  @SerializedName("missingApisMembers")
  private List<MissingSpecMembers> missingApisMembers = new ArrayList<MissingSpecMembers>();

  @SerializedName("missingDomainsMembers")
  private List<MissingSpecMembers> missingDomainsMembers = new ArrayList<MissingSpecMembers>();

  @SerializedName("missingApisTeams")
  private List<MissingSpecMembers> missingApisTeams = new ArrayList<MissingSpecMembers>();

  @SerializedName("missingDomainsTeams")
  private List<MissingSpecMembers> missingDomainsTeams = new ArrayList<MissingSpecMembers>();

  public NotCollaboratorProjectMembers missingApisMembers(List<MissingSpecMembers> missingApisMembers) {
    this.missingApisMembers = missingApisMembers;
    return this;
  }

  public NotCollaboratorProjectMembers addMissingApisMembersItem(MissingSpecMembers missingApisMembersItem) {
    this.missingApisMembers.add(missingApisMembersItem);
    return this;
  }

   /**
   * Get missingApisMembers
   * @return missingApisMembers
  **/
  @ApiModelProperty(value = "")
  public List<MissingSpecMembers> getMissingApisMembers() {
    return missingApisMembers;
  }

  public void setMissingApisMembers(List<MissingSpecMembers> missingApisMembers) {
    this.missingApisMembers = missingApisMembers;
  }

  public NotCollaboratorProjectMembers missingDomainsMembers(List<MissingSpecMembers> missingDomainsMembers) {
    this.missingDomainsMembers = missingDomainsMembers;
    return this;
  }

  public NotCollaboratorProjectMembers addMissingDomainsMembersItem(MissingSpecMembers missingDomainsMembersItem) {
    this.missingDomainsMembers.add(missingDomainsMembersItem);
    return this;
  }

   /**
   * Get missingDomainsMembers
   * @return missingDomainsMembers
  **/
  @ApiModelProperty(value = "")
  public List<MissingSpecMembers> getMissingDomainsMembers() {
    return missingDomainsMembers;
  }

  public void setMissingDomainsMembers(List<MissingSpecMembers> missingDomainsMembers) {
    this.missingDomainsMembers = missingDomainsMembers;
  }

  public NotCollaboratorProjectMembers missingApisTeams(List<MissingSpecMembers> missingApisTeams) {
    this.missingApisTeams = missingApisTeams;
    return this;
  }

  public NotCollaboratorProjectMembers addMissingApisTeamsItem(MissingSpecMembers missingApisTeamsItem) {
    this.missingApisTeams.add(missingApisTeamsItem);
    return this;
  }

   /**
   * Get missingApisTeams
   * @return missingApisTeams
  **/
  @ApiModelProperty(value = "")
  public List<MissingSpecMembers> getMissingApisTeams() {
    return missingApisTeams;
  }

  public void setMissingApisTeams(List<MissingSpecMembers> missingApisTeams) {
    this.missingApisTeams = missingApisTeams;
  }

  public NotCollaboratorProjectMembers missingDomainsTeams(List<MissingSpecMembers> missingDomainsTeams) {
    this.missingDomainsTeams = missingDomainsTeams;
    return this;
  }

  public NotCollaboratorProjectMembers addMissingDomainsTeamsItem(MissingSpecMembers missingDomainsTeamsItem) {
    this.missingDomainsTeams.add(missingDomainsTeamsItem);
    return this;
  }

   /**
   * Get missingDomainsTeams
   * @return missingDomainsTeams
  **/
  @ApiModelProperty(value = "")
  public List<MissingSpecMembers> getMissingDomainsTeams() {
    return missingDomainsTeams;
  }

  public void setMissingDomainsTeams(List<MissingSpecMembers> missingDomainsTeams) {
    this.missingDomainsTeams = missingDomainsTeams;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NotCollaboratorProjectMembers notCollaboratorProjectMembers = (NotCollaboratorProjectMembers) o;
    return Objects.equals(this.missingApisMembers, notCollaboratorProjectMembers.missingApisMembers) &&
        Objects.equals(this.missingDomainsMembers, notCollaboratorProjectMembers.missingDomainsMembers) &&
        Objects.equals(this.missingApisTeams, notCollaboratorProjectMembers.missingApisTeams) &&
        Objects.equals(this.missingDomainsTeams, notCollaboratorProjectMembers.missingDomainsTeams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(missingApisMembers, missingDomainsMembers, missingApisTeams, missingDomainsTeams);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NotCollaboratorProjectMembers {\n");
    
    sb.append("    missingApisMembers: ").append(toIndentedString(missingApisMembers)).append("\n");
    sb.append("    missingDomainsMembers: ").append(toIndentedString(missingDomainsMembers)).append("\n");
    sb.append("    missingApisTeams: ").append(toIndentedString(missingApisTeams)).append("\n");
    sb.append("    missingDomainsTeams: ").append(toIndentedString(missingDomainsTeams)).append("\n");
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

