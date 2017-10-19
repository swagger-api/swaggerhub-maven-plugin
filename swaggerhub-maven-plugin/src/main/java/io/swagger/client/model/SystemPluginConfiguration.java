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
 * SystemPluginConfiguration
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class SystemPluginConfiguration {
  @SerializedName("id")
  private String id = null;

  @SerializedName("lifecycles")
  private List<String> lifecycles = new ArrayList<String>();

  @SerializedName("configuration")
  private Map<String, String> _configuration = new HashMap<String, String>();

  @SerializedName("implementingClass")
  private String implementingClass = null;

  @SerializedName("version")
  private String version = null;

  public SystemPluginConfiguration id(String id) {
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

  public SystemPluginConfiguration lifecycles(List<String> lifecycles) {
    this.lifecycles = lifecycles;
    return this;
  }

  public SystemPluginConfiguration addLifecyclesItem(String lifecyclesItem) {
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

  public SystemPluginConfiguration _configuration(Map<String, String> _configuration) {
    this._configuration = _configuration;
    return this;
  }

  public SystemPluginConfiguration putConfigurationItem(String key, String _configurationItem) {
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

  public SystemPluginConfiguration implementingClass(String implementingClass) {
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

  public SystemPluginConfiguration version(String version) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SystemPluginConfiguration systemPluginConfiguration = (SystemPluginConfiguration) o;
    return Objects.equals(this.id, systemPluginConfiguration.id) &&
        Objects.equals(this.lifecycles, systemPluginConfiguration.lifecycles) &&
        Objects.equals(this._configuration, systemPluginConfiguration._configuration) &&
        Objects.equals(this.implementingClass, systemPluginConfiguration.implementingClass) &&
        Objects.equals(this.version, systemPluginConfiguration.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lifecycles, _configuration, implementingClass, version);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SystemPluginConfiguration {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    lifecycles: ").append(toIndentedString(lifecycles)).append("\n");
    sb.append("    _configuration: ").append(toIndentedString(_configuration)).append("\n");
    sb.append("    implementingClass: ").append(toIndentedString(implementingClass)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
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

