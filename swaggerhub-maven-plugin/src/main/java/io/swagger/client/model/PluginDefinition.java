package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

/**
 * PluginDefinition
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class PluginDefinition {
  @SerializedName("id")
  private String id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("implementingClass")
  private String implementingClass = null;

  @SerializedName("version")
  private String version = null;

  @SerializedName("createdBy")
  private String createdBy = null;

  @SerializedName("createdOn")
  private DateTime createdOn = null;

  @SerializedName("enabled")
  private Boolean enabled = null;

  @SerializedName("configurationSchema")
  private Object configurationSchema = null;

  @SerializedName("limit")
  private Long limit = null;

  public PluginDefinition id(String id) {
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

  public PluginDefinition name(String name) {
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

  public PluginDefinition implementingClass(String implementingClass) {
    this.implementingClass = implementingClass;
    return this;
  }

   /**
   * Get implementingClass
   * @return implementingClass
  **/
  @ApiModelProperty(required = true, value = "")
  public String getImplementingClass() {
    return implementingClass;
  }

  public void setImplementingClass(String implementingClass) {
    this.implementingClass = implementingClass;
  }

  public PluginDefinition version(String version) {
    this.version = version;
    return this;
  }

   /**
   * Get version
   * @return version
  **/
  @ApiModelProperty(required = true, value = "")
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public PluginDefinition createdBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

   /**
   * Get createdBy
   * @return createdBy
  **/
  @ApiModelProperty(required = true, value = "")
  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public PluginDefinition createdOn(DateTime createdOn) {
    this.createdOn = createdOn;
    return this;
  }

   /**
   * Get createdOn
   * @return createdOn
  **/
  @ApiModelProperty(required = true, value = "")
  public DateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(DateTime createdOn) {
    this.createdOn = createdOn;
  }

  public PluginDefinition enabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

   /**
   * Get enabled
   * @return enabled
  **/
  @ApiModelProperty(required = true, value = "")
  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public PluginDefinition configurationSchema(Object configurationSchema) {
    this.configurationSchema = configurationSchema;
    return this;
  }

   /**
   * Get configurationSchema
   * @return configurationSchema
  **/
  @ApiModelProperty(required = true, value = "")
  public Object getConfigurationSchema() {
    return configurationSchema;
  }

  public void setConfigurationSchema(Object configurationSchema) {
    this.configurationSchema = configurationSchema;
  }

  public PluginDefinition limit(Long limit) {
    this.limit = limit;
    return this;
  }

   /**
   * count of allowed configurations
   * @return limit
  **/
  @ApiModelProperty(value = "count of allowed configurations")
  public Long getLimit() {
    return limit;
  }

  public void setLimit(Long limit) {
    this.limit = limit;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PluginDefinition pluginDefinition = (PluginDefinition) o;
    return Objects.equals(this.id, pluginDefinition.id) &&
        Objects.equals(this.name, pluginDefinition.name) &&
        Objects.equals(this.implementingClass, pluginDefinition.implementingClass) &&
        Objects.equals(this.version, pluginDefinition.version) &&
        Objects.equals(this.createdBy, pluginDefinition.createdBy) &&
        Objects.equals(this.createdOn, pluginDefinition.createdOn) &&
        Objects.equals(this.enabled, pluginDefinition.enabled) &&
        Objects.equals(this.configurationSchema, pluginDefinition.configurationSchema) &&
        Objects.equals(this.limit, pluginDefinition.limit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, implementingClass, version, createdBy, createdOn, enabled, configurationSchema, limit);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PluginDefinition {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    implementingClass: ").append(toIndentedString(implementingClass)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
    sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
    sb.append("    configurationSchema: ").append(toIndentedString(configurationSchema)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
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

