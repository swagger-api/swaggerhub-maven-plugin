package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.NewReply;
import java.util.ArrayList;
import java.util.List;

/**
 * NewComment
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class NewComment {
  @SerializedName("position")
  private Integer position = null;

  @SerializedName("body")
  private String body = null;

  @SerializedName("replies")
  private List<NewReply> replies = new ArrayList<NewReply>();

  public NewComment position(Integer position) {
    this.position = position;
    return this;
  }

   /**
   * Get position
   * @return position
  **/
  @ApiModelProperty(required = true, value = "")
  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public NewComment body(String body) {
    this.body = body;
    return this;
  }

   /**
   * Get body
   * @return body
  **/
  @ApiModelProperty(required = true, value = "")
  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public NewComment replies(List<NewReply> replies) {
    this.replies = replies;
    return this;
  }

  public NewComment addRepliesItem(NewReply repliesItem) {
    this.replies.add(repliesItem);
    return this;
  }

   /**
   * Get replies
   * @return replies
  **/
  @ApiModelProperty(value = "")
  public List<NewReply> getReplies() {
    return replies;
  }

  public void setReplies(List<NewReply> replies) {
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
    NewComment newComment = (NewComment) o;
    return Objects.equals(this.position, newComment.position) &&
        Objects.equals(this.body, newComment.body) &&
        Objects.equals(this.replies, newComment.replies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, body, replies);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewComment {\n");
    
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
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

