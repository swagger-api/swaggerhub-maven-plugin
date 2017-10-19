package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.ClosableCommentPatch;
import io.swagger.client.model.CommentPatch;
import io.swagger.client.model.NewComment;
import io.swagger.client.model.NewReply;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CommentsBatch
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class CommentsBatch {
  @SerializedName("addComment")
  private List<NewComment> addComment = new ArrayList<NewComment>();

  @SerializedName("updateComment")
  private Map<String, ClosableCommentPatch> updateComment = new HashMap<String, ClosableCommentPatch>();

  /**
   * Gets or Sets inner
   */
  public enum InnerEnum {
    @SerializedName("OPEN")
    OPEN("OPEN"),
    
    @SerializedName("RESOLVED")
    RESOLVED("RESOLVED");

    private String value;

    InnerEnum(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
  }

  @SerializedName("updateStatus")
  private Map<String, InnerEnum> updateStatus = new HashMap<String, InnerEnum>();

  @SerializedName("deleteComment")
  private List<String> deleteComment = new ArrayList<String>();

  @SerializedName("addReply")
  private Map<String, List<NewReply>> addReply = new HashMap<String, List<NewReply>>();

  @SerializedName("updateReply")
  private Map<String, Map<String, CommentPatch>> updateReply = new HashMap<String, Map<String, CommentPatch>>();

  @SerializedName("deleteReply")
  private Map<String, List<String>> deleteReply = new HashMap<String, List<String>>();

  public CommentsBatch addComment(List<NewComment> addComment) {
    this.addComment = addComment;
    return this;
  }

  public CommentsBatch addAddCommentItem(NewComment addCommentItem) {
    this.addComment.add(addCommentItem);
    return this;
  }

   /**
   * Get addComment
   * @return addComment
  **/
  @ApiModelProperty(value = "")
  public List<NewComment> getAddComment() {
    return addComment;
  }

  public void setAddComment(List<NewComment> addComment) {
    this.addComment = addComment;
  }

  public CommentsBatch updateComment(Map<String, ClosableCommentPatch> updateComment) {
    this.updateComment = updateComment;
    return this;
  }

  public CommentsBatch putUpdateCommentItem(String key, ClosableCommentPatch updateCommentItem) {
    this.updateComment.put(key, updateCommentItem);
    return this;
  }

   /**
   * Get updateComment
   * @return updateComment
  **/
  @ApiModelProperty(value = "")
  public Map<String, ClosableCommentPatch> getUpdateComment() {
    return updateComment;
  }

  public void setUpdateComment(Map<String, ClosableCommentPatch> updateComment) {
    this.updateComment = updateComment;
  }

  public CommentsBatch updateStatus(Map<String, InnerEnum> updateStatus) {
    this.updateStatus = updateStatus;
    return this;
  }

  public CommentsBatch putUpdateStatusItem(String key, InnerEnum updateStatusItem) {
    this.updateStatus.put(key, updateStatusItem);
    return this;
  }

   /**
   * Get updateStatus
   * @return updateStatus
  **/
  @ApiModelProperty(value = "")
  public Map<String, InnerEnum> getUpdateStatus() {
    return updateStatus;
  }

  public void setUpdateStatus(Map<String, InnerEnum> updateStatus) {
    this.updateStatus = updateStatus;
  }

  public CommentsBatch deleteComment(List<String> deleteComment) {
    this.deleteComment = deleteComment;
    return this;
  }

  public CommentsBatch addDeleteCommentItem(String deleteCommentItem) {
    this.deleteComment.add(deleteCommentItem);
    return this;
  }

   /**
   * Get deleteComment
   * @return deleteComment
  **/
  @ApiModelProperty(value = "")
  public List<String> getDeleteComment() {
    return deleteComment;
  }

  public void setDeleteComment(List<String> deleteComment) {
    this.deleteComment = deleteComment;
  }

  public CommentsBatch addReply(Map<String, List<NewReply>> addReply) {
    this.addReply = addReply;
    return this;
  }

  public CommentsBatch putAddReplyItem(String key, List<NewReply> addReplyItem) {
    this.addReply.put(key, addReplyItem);
    return this;
  }

   /**
   * Get addReply
   * @return addReply
  **/
  @ApiModelProperty(value = "")
  public Map<String, List<NewReply>> getAddReply() {
    return addReply;
  }

  public void setAddReply(Map<String, List<NewReply>> addReply) {
    this.addReply = addReply;
  }

  public CommentsBatch updateReply(Map<String, Map<String, CommentPatch>> updateReply) {
    this.updateReply = updateReply;
    return this;
  }

  public CommentsBatch putUpdateReplyItem(String key, Map<String, CommentPatch> updateReplyItem) {
    this.updateReply.put(key, updateReplyItem);
    return this;
  }

   /**
   * Get updateReply
   * @return updateReply
  **/
  @ApiModelProperty(value = "")
  public Map<String, Map<String, CommentPatch>> getUpdateReply() {
    return updateReply;
  }

  public void setUpdateReply(Map<String, Map<String, CommentPatch>> updateReply) {
    this.updateReply = updateReply;
  }

  public CommentsBatch deleteReply(Map<String, List<String>> deleteReply) {
    this.deleteReply = deleteReply;
    return this;
  }

  public CommentsBatch putDeleteReplyItem(String key, List<String> deleteReplyItem) {
    this.deleteReply.put(key, deleteReplyItem);
    return this;
  }

   /**
   * Get deleteReply
   * @return deleteReply
  **/
  @ApiModelProperty(value = "")
  public Map<String, List<String>> getDeleteReply() {
    return deleteReply;
  }

  public void setDeleteReply(Map<String, List<String>> deleteReply) {
    this.deleteReply = deleteReply;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentsBatch commentsBatch = (CommentsBatch) o;
    return Objects.equals(this.addComment, commentsBatch.addComment) &&
        Objects.equals(this.updateComment, commentsBatch.updateComment) &&
        Objects.equals(this.updateStatus, commentsBatch.updateStatus) &&
        Objects.equals(this.deleteComment, commentsBatch.deleteComment) &&
        Objects.equals(this.addReply, commentsBatch.addReply) &&
        Objects.equals(this.updateReply, commentsBatch.updateReply) &&
        Objects.equals(this.deleteReply, commentsBatch.deleteReply);
  }

  @Override
  public int hashCode() {
    return Objects.hash(addComment, updateComment, updateStatus, deleteComment, addReply, updateReply, deleteReply);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommentsBatch {\n");
    
    sb.append("    addComment: ").append(toIndentedString(addComment)).append("\n");
    sb.append("    updateComment: ").append(toIndentedString(updateComment)).append("\n");
    sb.append("    updateStatus: ").append(toIndentedString(updateStatus)).append("\n");
    sb.append("    deleteComment: ").append(toIndentedString(deleteComment)).append("\n");
    sb.append("    addReply: ").append(toIndentedString(addReply)).append("\n");
    sb.append("    updateReply: ").append(toIndentedString(updateReply)).append("\n");
    sb.append("    deleteReply: ").append(toIndentedString(deleteReply)).append("\n");
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

