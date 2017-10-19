package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.CollaborationHint;
import io.swagger.client.model.CollaborationMembership;
import java.util.ArrayList;
import java.util.List;

/**
 * CollaborationMembershipList
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class CollaborationMembershipList {
  @SerializedName("hint")
  private CollaborationHint hint = null;

  @SerializedName("items")
  private List<CollaborationMembership> items = new ArrayList<CollaborationMembership>();

  public CollaborationMembershipList hint(CollaborationHint hint) {
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

  public CollaborationMembershipList items(List<CollaborationMembership> items) {
    this.items = items;
    return this;
  }

  public CollaborationMembershipList addItemsItem(CollaborationMembership itemsItem) {
    this.items.add(itemsItem);
    return this;
  }

   /**
   * Get items
   * @return items
  **/
  @ApiModelProperty(value = "")
  public List<CollaborationMembership> getItems() {
    return items;
  }

  public void setItems(List<CollaborationMembership> items) {
    this.items = items;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollaborationMembershipList collaborationMembershipList = (CollaborationMembershipList) o;
    return Objects.equals(this.hint, collaborationMembershipList.hint) &&
        Objects.equals(this.items, collaborationMembershipList.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hint, items);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollaborationMembershipList {\n");
    
    sb.append("    hint: ").append(toIndentedString(hint)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
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

