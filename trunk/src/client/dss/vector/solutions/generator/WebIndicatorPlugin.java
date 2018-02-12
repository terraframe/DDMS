/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.generator;

import java.util.Map;

import com.runwaysdk.business.ComponentDTOIF;
import com.runwaysdk.form.web.WebFormVisitor;
import com.runwaysdk.form.web.field.FieldBuilders.PluginIF;
import com.runwaysdk.form.web.field.FieldBuilders.WebFieldBuilder;
import com.runwaysdk.form.web.field.WebAttribute;
import com.runwaysdk.form.web.field.WebField;
import com.runwaysdk.form.web.metadata.FieldMdBuilders.WebAttributeMdBuilder;
import com.runwaysdk.form.web.metadata.WebAttributeMd;
import com.runwaysdk.form.web.metadata.WebFieldMd;
import com.runwaysdk.system.metadata.MdFormDTO;
import com.runwaysdk.system.metadata.MdWebFieldDTO;
import com.runwaysdk.transport.attributes.AttributeDTO;
import com.runwaysdk.transport.attributes.AttributeIndicatorDTO;

public class WebIndicatorPlugin implements PluginIF
{
  public static class WebIndicator extends WebAttribute
  {

    public WebIndicator(WebIndicatorMd fMd)
    {
      super(fMd);
    }

    @Override
    public void accept(WebFormVisitor visitor)
    {
      super.accept(visitor);
      
      visitor.visit(this);
    }

    @Override
    public WebIndicatorMd getFieldMd()
    {
      return (WebIndicatorMd) super.getFieldMd();
    }
  }

  public static class WebIndicatorMd extends WebAttributeMd
  {
    protected WebIndicatorMd()
    {
      super();
    }
  }

  public static class WebIndicatorMdBuilder extends WebAttributeMdBuilder
  {
    @Override
    public WebFieldMd create(MdFormDTO mdForm, MdWebFieldDTO mdField)
    {
      WebIndicatorMd fMd = new WebIndicatorMd();

      this.init(mdField, fMd);
      return fMd;
    }
  }

  private static class WebIndicatorBuilder extends WebFieldBuilder
  {
    public WebIndicatorBuilder()
    {
      super(new WebIndicatorMdBuilder());
    }

    @Override
    public WebField create(MdFormDTO mdForm, MdWebFieldDTO mdField, ComponentDTOIF data, Map<String, AttributeDTO> mdIdToAttrDTOs)
    {
      WebIndicatorMdBuilder mdBuilder = (WebIndicatorMdBuilder) this.getMdBuilder();

      WebIndicatorMd fMd = (WebIndicatorMd) mdBuilder.create(mdForm, mdField);

      WebIndicator f = new WebIndicator(fMd);

      AttributeIndicatorDTO attr = (AttributeIndicatorDTO) mdIdToAttrDTOs.get(fMd.getDefiningMdAttribute());

      this.init(attr, f, mdField);

      return f;
    }
  }

  @Override
  public String getModuleIdentifier()
  {
    return "ddms";
  }

  @Override
  public WebFieldBuilder getBuilder(String type)
  {
    if (type.equals(MdWebIndicatorDTO.CLASS))
    {
      return new WebIndicatorBuilder();
    }

    return null;
  }
}
