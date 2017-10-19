package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.EntryId;
import java.util.ArrayList;
import java.util.List;

/**
 * ProjectEntry
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class ProjectEntry {
  @SerializedName("groupId")
  private String groupId = null;

  @SerializedName("projectId")
  private String projectId = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("apis")
  private List<EntryId> apis = new ArrayList<EntryId>();

  @SerializedName("domains")
  private List<EntryId> domains = new ArrayList<EntryId>();

  public ProjectEntry groupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

   /**
   * Get groupId
   * @return groupId
  **/
  @ApiModelProperty(required = true, value = "")
  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public ProjectEntry projectId(String projectId) {
    this.projectId = projectId;
    return this;
  }

   /**
   * Get projectId
   * @return projectId
  **/
  @ApiModelProperty(required = true, value = "")
  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public ProjectEntry name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProjectEntry apis(List<EntryId> apis) {
    this.apis = apis;
    return this;
  }

  public ProjectEntry addApisItem(EntryId apisItem) {
    this.apis.add(apisItem);
    return this;
  }

   /**
   * Get apis
   * @return apis
  **/
  @ApiModelProperty(value = "")
  public List<EntryId> getApis() {
    return apis;
  }

  public void setApis(List<EntryId> apis) {
    this.apis = apis;
  }

  public ProjectEntry domains(List<EntryId> domains) {
    this.domains = domains;
    return this;
  }

  public ProjectEntry addDomainsItem(EntryId domainsItem) {
    this.domains.add(domainsItem);
    return this;
  }

   /**
   * Get domains
   * @return domains
  **/
  @ApiModelProperty(value = "")
  public List<EntryId> getDomains() {
    return domains;
  }

  public void setDomains(List<EntryId> domains) {
    this.domains = domains;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectEntry projectEntry = (ProjectEntry) o;
    return Objects.equals(this.groupId, projectEntry.groupId) &&
        Objects.equals(this.projectId, projectEntry.projectId) &&
        Objects.equals(this.name, projectEntry.name) &&
        Objects.equals(this.apis, projectEntry.apis) &&
        Objects.equals(this.domains, projectEntry.domains);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupId, projectId, name, apis, domains);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectEntry {\n");
    
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    apis: ").append(toIndentedString(apis)).append("\n");
    sb.append("    domains: ").append(toIndentedString(domains)).append("\n");
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

