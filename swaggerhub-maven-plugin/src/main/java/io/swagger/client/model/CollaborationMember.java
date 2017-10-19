package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

/**
 * CollaborationMember
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class CollaborationMember {
  @SerializedName("uuid")
  private String uuid = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("title")
  private String title = null;

  @SerializedName("startTime")
  private DateTime startTime = null;

   /**
   * Get uuid
   * @return uuid
  **/
  @ApiModelProperty(value = "")
  public String getUuid() {
    return uuid;
  }

  public CollaborationMember name(String name) {
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

   /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(value = "")
  public String getTitle() {
    return title;
  }

   /**
   * Get startTime
   * @return startTime
  **/
  @ApiModelProperty(value = "")
  public DateTime getStartTime() {
    return startTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollaborationMember collaborationMember = (CollaborationMember) o;
    return Objects.equals(this.uuid, collaborationMember.uuid) &&
        Objects.equals(this.name, collaborationMember.name) &&
        Objects.equals(this.title, collaborationMember.title) &&
        Objects.equals(this.startTime, collaborationMember.startTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, name, title, startTime);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollaborationMember {\n");
    
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
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

