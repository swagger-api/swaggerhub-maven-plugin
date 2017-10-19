package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.ProjectEntry;
import java.util.ArrayList;
import java.util.List;

/**
 * ProjectsJson
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class ProjectsJson {
  @SerializedName("offset")
  private Integer offset = null;

  @SerializedName("totalCount")
  private Long totalCount = null;

  @SerializedName("projects")
  private List<ProjectEntry> projects = new ArrayList<ProjectEntry>();

  public ProjectsJson offset(Integer offset) {
    this.offset = offset;
    return this;
  }

   /**
   * Get offset
   * @return offset
  **/
  @ApiModelProperty(value = "")
  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public ProjectsJson totalCount(Long totalCount) {
    this.totalCount = totalCount;
    return this;
  }

   /**
   * Get totalCount
   * @return totalCount
  **/
  @ApiModelProperty(value = "")
  public Long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Long totalCount) {
    this.totalCount = totalCount;
  }

  public ProjectsJson projects(List<ProjectEntry> projects) {
    this.projects = projects;
    return this;
  }

  public ProjectsJson addProjectsItem(ProjectEntry projectsItem) {
    this.projects.add(projectsItem);
    return this;
  }

   /**
   * Get projects
   * @return projects
  **/
  @ApiModelProperty(value = "")
  public List<ProjectEntry> getProjects() {
    return projects;
  }

  public void setProjects(List<ProjectEntry> projects) {
    this.projects = projects;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectsJson projectsJson = (ProjectsJson) o;
    return Objects.equals(this.offset, projectsJson.offset) &&
        Objects.equals(this.totalCount, projectsJson.totalCount) &&
        Objects.equals(this.projects, projectsJson.projects);
  }

  @Override
  public int hashCode() {
    return Objects.hash(offset, totalCount, projects);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectsJson {\n");
    
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
    sb.append("    projects: ").append(toIndentedString(projects)).append("\n");
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

