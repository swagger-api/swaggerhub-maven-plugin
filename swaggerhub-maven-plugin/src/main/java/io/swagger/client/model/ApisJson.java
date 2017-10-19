package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.ApisJsonApi;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

/**
 * ApisJson
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class ApisJson {
  @SerializedName("name")
  private String name = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("url")
  private String url = null;

  @SerializedName("created")
  private DateTime created = null;

  @SerializedName("modified")
  private DateTime modified = null;

  @SerializedName("specificationVersion")
  private String specificationVersion = null;

  @SerializedName("offset")
  private Integer offset = null;

  @SerializedName("totalCount")
  private Long totalCount = null;

  @SerializedName("apis")
  private List<ApisJsonApi> apis = new ArrayList<ApisJsonApi>();

  public ApisJson name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ApisJson description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ApisJson url(String url) {
    this.url = url;
    return this;
  }

   /**
   * Get url
   * @return url
  **/
  @ApiModelProperty(value = "")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public ApisJson created(DateTime created) {
    this.created = created;
    return this;
  }

   /**
   * Get created
   * @return created
  **/
  @ApiModelProperty(value = "")
  public DateTime getCreated() {
    return created;
  }

  public void setCreated(DateTime created) {
    this.created = created;
  }

  public ApisJson modified(DateTime modified) {
    this.modified = modified;
    return this;
  }

   /**
   * Get modified
   * @return modified
  **/
  @ApiModelProperty(value = "")
  public DateTime getModified() {
    return modified;
  }

  public void setModified(DateTime modified) {
    this.modified = modified;
  }

  public ApisJson specificationVersion(String specificationVersion) {
    this.specificationVersion = specificationVersion;
    return this;
  }

   /**
   * Get specificationVersion
   * @return specificationVersion
  **/
  @ApiModelProperty(value = "")
  public String getSpecificationVersion() {
    return specificationVersion;
  }

  public void setSpecificationVersion(String specificationVersion) {
    this.specificationVersion = specificationVersion;
  }

  public ApisJson offset(Integer offset) {
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

  public ApisJson totalCount(Long totalCount) {
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

  public ApisJson apis(List<ApisJsonApi> apis) {
    this.apis = apis;
    return this;
  }

  public ApisJson addApisItem(ApisJsonApi apisItem) {
    this.apis.add(apisItem);
    return this;
  }

   /**
   * Get apis
   * @return apis
  **/
  @ApiModelProperty(value = "")
  public List<ApisJsonApi> getApis() {
    return apis;
  }

  public void setApis(List<ApisJsonApi> apis) {
    this.apis = apis;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApisJson apisJson = (ApisJson) o;
    return Objects.equals(this.name, apisJson.name) &&
        Objects.equals(this.description, apisJson.description) &&
        Objects.equals(this.url, apisJson.url) &&
        Objects.equals(this.created, apisJson.created) &&
        Objects.equals(this.modified, apisJson.modified) &&
        Objects.equals(this.specificationVersion, apisJson.specificationVersion) &&
        Objects.equals(this.offset, apisJson.offset) &&
        Objects.equals(this.totalCount, apisJson.totalCount) &&
        Objects.equals(this.apis, apisJson.apis);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, url, created, modified, specificationVersion, offset, totalCount, apis);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApisJson {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    modified: ").append(toIndentedString(modified)).append("\n");
    sb.append("    specificationVersion: ").append(toIndentedString(specificationVersion)).append("\n");
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
    sb.append("    apis: ").append(toIndentedString(apis)).append("\n");
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

