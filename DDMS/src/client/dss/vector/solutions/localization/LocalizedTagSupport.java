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
package dss.vector.solutions.localization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.runwaysdk.ClientSession;
import com.runwaysdk.constants.ClientConstants;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.tag.develop.AttributeAnnotation;
import com.runwaysdk.controller.tag.develop.TagAnnotation;
import com.runwaysdk.session.Request;

import dss.vector.solutions.util.LocalizationFacadeDTO;

@TagAnnotation(bodyContent = "empty", name = "localize", description = "Localizes the given key, respecting bundle precedence")
public class LocalizedTagSupport extends SimpleTagSupport
{
  private String key;
  private String var;

  @AttributeAnnotation(description = "The key for the localized value", rtexprvalue = true)
  public String getKey()
  {
    return key;
  }

  public void setKey(String key)
  {
    this.key = key;
  }
  
  @AttributeAnnotation(description = "The variable to store the localized key in", required = false, rtexprvalue = false)
  public String getVar()
  {
    return var;
  }
  
  public void setVar(String var)
  {
    this.var = var;
  }
  
  @Override
  @Request
  @SuppressWarnings("unchecked")
  public void doTag() throws JspException, IOException
  {
    PageContext pageContext = (PageContext)this.getJspContext();
    JspWriter out = pageContext.getOut();
    ClientSession clientSession = (ClientSession) pageContext.findAttribute(ClientConstants.CLIENTSESSION);
    
    if (clientSession==null)
    {
      ArrayList<Locale> arrayList = new ArrayList<Locale>();
      Enumeration<Locale> locales = pageContext.getRequest().getLocales();
      while (locales.hasMoreElements())
      {
        arrayList.add(locales.nextElement());
      }
      Locale[] array = arrayList.toArray(new Locale[arrayList.size()]);
      
      clientSession = ClientSession.createAnonymousSession(array);
    }
    
    ClientRequestIF request = clientSession.getRequest();
    
    String localizedValue = LocalizationFacadeDTO.getFromBundles(request, getKey());
    if (var == null)
    {
      out.write(localizedValue);
    }
    else
    {
      pageContext.setAttribute(var, localizedValue);
    }
  }
}
