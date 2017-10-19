package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.Template;
import java.util.ArrayList;
import java.util.List;

/**
 * TemplateCatalog
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-19T21:00:42.108Z")
public class TemplateCatalog {
  @SerializedName("templates")
  private List<Template> templates = new ArrayList<Template>();

  @SerializedName("default")
  private Template _default = null;

  public TemplateCatalog templates(List<Template> templates) {
    this.templates = templates;
    return this;
  }

  public TemplateCatalog addTemplatesItem(Template templatesItem) {
    this.templates.add(templatesItem);
    return this;
  }

   /**
   * Get templates
   * @return templates
  **/
  @ApiModelProperty(value = "")
  public List<Template> getTemplates() {
    return templates;
  }

  public void setTemplates(List<Template> templates) {
    this.templates = templates;
  }

  public TemplateCatalog _default(Template _default) {
    this._default = _default;
    return this;
  }

   /**
   * Get _default
   * @return _default
  **/
  @ApiModelProperty(value = "")
  public Template getDefault() {
    return _default;
  }

  public void setDefault(Template _default) {
    this._default = _default;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemplateCatalog templateCatalog = (TemplateCatalog) o;
    return Objects.equals(this.templates, templateCatalog.templates) &&
        Objects.equals(this._default, templateCatalog._default);
  }

  @Override
  public int hashCode() {
    return Objects.hash(templates, _default);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TemplateCatalog {\n");
    
    sb.append("    templates: ").append(toIndentedString(templates)).append("\n");
    sb.append("    _default: ").append(toIndentedString(_default)).append("\n");
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

