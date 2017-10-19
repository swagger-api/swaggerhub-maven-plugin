package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PluginConfiguration
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class PluginConfiguration {
  @SerializedName("id")
  private String id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("objectId")
  private String objectId = null;

  @SerializedName("lifecycles")
  private List<String> lifecycles = new ArrayList<String>();

  @SerializedName("configuration")
  private Map<String, String> _configuration = new HashMap<String, String>();

  @SerializedName("ownerName")
  private String ownerName = null;

  @SerializedName("path")
  private String path = null;

  @SerializedName("definitionId")
  private String definitionId = null;

  @SerializedName("draft")
  private Boolean draft = null;

  public PluginConfiguration id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PluginConfiguration name(String name) {
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

  public PluginConfiguration objectId(String objectId) {
    this.objectId = objectId;
    return this;
  }

   /**
   * Get objectId
   * @return objectId
  **/
  @ApiModelProperty(required = true, value = "")
  public String getObjectId() {
    return objectId;
  }

  public void setObjectId(String objectId) {
    this.objectId = objectId;
  }

  public PluginConfiguration lifecycles(List<String> lifecycles) {
    this.lifecycles = lifecycles;
    return this;
  }

  public PluginConfiguration addLifecyclesItem(String lifecyclesItem) {
    this.lifecycles.add(lifecyclesItem);
    return this;
  }

   /**
   * Get lifecycles
   * @return lifecycles
  **/
  @ApiModelProperty(required = true, value = "")
  public List<String> getLifecycles() {
    return lifecycles;
  }

  public void setLifecycles(List<String> lifecycles) {
    this.lifecycles = lifecycles;
  }

  public PluginConfiguration _configuration(Map<String, String> _configuration) {
    this._configuration = _configuration;
    return this;
  }

  public PluginConfiguration putConfigurationItem(String key, String _configurationItem) {
    this._configuration.put(key, _configurationItem);
    return this;
  }

   /**
   * Get _configuration
   * @return _configuration
  **/
  @ApiModelProperty(value = "")
  public Map<String, String> getConfiguration() {
    return _configuration;
  }

  public void setConfiguration(Map<String, String> _configuration) {
    this._configuration = _configuration;
  }

  public PluginConfiguration ownerName(String ownerName) {
    this.ownerName = ownerName;
    return this;
  }

   /**
   * Get ownerName
   * @return ownerName
  **/
  @ApiModelProperty(required = true, value = "")
  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public PluginConfiguration path(String path) {
    this.path = path;
    return this;
  }

   /**
   * Get path
   * @return path
  **/
  @ApiModelProperty(required = true, value = "")
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public PluginConfiguration definitionId(String definitionId) {
    this.definitionId = definitionId;
    return this;
  }

   /**
   * Get definitionId
   * @return definitionId
  **/
  @ApiModelProperty(required = true, value = "")
  public String getDefinitionId() {
    return definitionId;
  }

  public void setDefinitionId(String definitionId) {
    this.definitionId = definitionId;
  }

  public PluginConfiguration draft(Boolean draft) {
    this.draft = draft;
    return this;
  }

   /**
   * Get draft
   * @return draft
  **/
  @ApiModelProperty(value = "")
  public Boolean getDraft() {
    return draft;
  }

  public void setDraft(Boolean draft) {
    this.draft = draft;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PluginConfiguration pluginConfiguration = (PluginConfiguration) o;
    return Objects.equals(this.id, pluginConfiguration.id) &&
        Objects.equals(this.name, pluginConfiguration.name) &&
        Objects.equals(this.objectId, pluginConfiguration.objectId) &&
        Objects.equals(this.lifecycles, pluginConfiguration.lifecycles) &&
        Objects.equals(this._configuration, pluginConfiguration._configuration) &&
        Objects.equals(this.ownerName, pluginConfiguration.ownerName) &&
        Objects.equals(this.path, pluginConfiguration.path) &&
        Objects.equals(this.definitionId, pluginConfiguration.definitionId) &&
        Objects.equals(this.draft, pluginConfiguration.draft);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, objectId, lifecycles, _configuration, ownerName, path, definitionId, draft);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PluginConfiguration {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    objectId: ").append(toIndentedString(objectId)).append("\n");
    sb.append("    lifecycles: ").append(toIndentedString(lifecycles)).append("\n");
    sb.append("    _configuration: ").append(toIndentedString(_configuration)).append("\n");
    sb.append("    ownerName: ").append(toIndentedString(ownerName)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    definitionId: ").append(toIndentedString(definitionId)).append("\n");
    sb.append("    draft: ").append(toIndentedString(draft)).append("\n");
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

