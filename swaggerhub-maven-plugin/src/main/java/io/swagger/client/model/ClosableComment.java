package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.Comment;
import io.swagger.client.model.User;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

/**
 * ClosableComment
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class ClosableComment extends Comment {
  @SerializedName("position")
  private Integer position = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    @SerializedName("OPEN")
    OPEN("OPEN"),
    
    @SerializedName("RESOLVED")
    RESOLVED("RESOLVED");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
  }

  @SerializedName("status")
  private StatusEnum status = null;

  @SerializedName("replies")
  private List<Comment> replies = new ArrayList<Comment>();

  public ClosableComment position(Integer position) {
    this.position = position;
    return this;
  }

   /**
   * Get position
   * @return position
  **/
  @ApiModelProperty(value = "")
  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public ClosableComment status(StatusEnum status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public ClosableComment replies(List<Comment> replies) {
    this.replies = replies;
    return this;
  }

  public ClosableComment addRepliesItem(Comment repliesItem) {
    this.replies.add(repliesItem);
    return this;
  }

   /**
   * Get replies
   * @return replies
  **/
  @ApiModelProperty(value = "")
  public List<Comment> getReplies() {
    return replies;
  }

  public void setReplies(List<Comment> replies) {
    this.replies = replies;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClosableComment closableComment = (ClosableComment) o;
    return Objects.equals(this.position, closableComment.position) &&
        Objects.equals(this.status, closableComment.status) &&
        Objects.equals(this.replies, closableComment.replies) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, status, replies, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClosableComment {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    replies: ").append(toIndentedString(replies)).append("\n");
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

