package dss.vector.solutions.localization;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
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

  @AttributeAnnotation(description = "The key for the localized value", rtexprvalue = true)
  public String getKey()
  {
    return key;
  }

  public void setKey(String key)
  {
    this.key = key;
  }
  
  @Override
  @Request
  public void doTag() throws JspException, IOException
  {
    JspWriter out = this.getJspContext().getOut();
    ClientSession clientSession = (ClientSession) this.getJspContext().findAttribute(ClientConstants.CLIENTSESSION);
    ClientRequestIF request = clientSession.getRequest();
    
    String localizedValue = LocalizationFacadeDTO.getFromBundles(request, getKey());
    out.write(localizedValue);
  }
}
