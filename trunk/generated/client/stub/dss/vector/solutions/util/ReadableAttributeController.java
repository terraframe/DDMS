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
package dss.vector.solutions.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.RolesDTO;
import com.runwaysdk.system.metadata.MdClassDTO;
import com.runwaysdk.system.metadata.MdWebFormDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.generator.MdFormUtilDTO;
import dss.vector.solutions.permission.MDSSRoleDTO;

public class ReadableAttributeController extends ReadableAttributeControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/util/ReadableAttributeController/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1239296298324L;

  public ReadableAttributeController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void getUniversal(String actor) throws IOException, ServletException
  {
    try
    {
      // Ensure the user has permissions to read this page
      new ReadableAttributeViewDTO(getClientRequest());

      if (actor == null)
      {
        actor = MDSSRoleInfo.GUI_VISIBILITY;
      }

      ClientRequestIF clientRequest = super.getClientRequest();

      Map<String, RolesDTO> roles = this.getAllRoles(clientRequest);

      MdWebFormDTO[] forms = MdFormUtilDTO.getAllForms(clientRequest);
      List<String> formURLs = new LinkedList<String>();

      for (MdWebFormDTO form : forms)
      {
        MdClassDTO formMdClass = form.getFormMdClass();
        String classType = formMdClass.getPackageName() + "." + formMdClass.getTypeName();
        String label = form.getDisplayLabel().getValue();
        String encodedLabel = this.getEncodedText(label);

        StringBuffer buffer = new StringBuffer();

        buffer.append("<a href=\"dss.vector.solutions.util.ReadableAttributeController.getAttributes.mojo?component=" + encodedLabel + "&universal=" + classType + "&actor=" + actor + "\">");
        buffer.append(label);
        buffer.append("</a>");

        formURLs.add(buffer.toString());
      }

      req.setAttribute("actor", actor);
      req.setAttribute("actorOptions", roles.values());
      req.setAttribute("forms", formURLs);

      render("selectUniversal.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failGetUniversal(actor);
      }
    }
  }

  @SuppressWarnings("deprecation")
  private String getEncodedText(String text)
  {
    try
    {
      return URLEncoder.encode(text, "UTF-8");
    }
    catch (UnsupportedEncodingException e)
    {
      return URLEncoder.encode(text);
    }
  }

  private Map<String, RolesDTO> getAllRoles(ClientRequestIF clientRequest)
  {
    Map<String, RolesDTO> roles = new LinkedHashMap<String, RolesDTO>();

    RolesDTO[] systemRoles = FacadeDTO.getMDSSRoles(clientRequest);
    for (int i = 0; i < systemRoles.length; i++)
    {
      roles.put(systemRoles[i].getRoleName(), systemRoles[i]);
    }

    RolesDTO[] mdssRoles = MDSSRoleDTO.getRoles(clientRequest);
    for (int i = 0; i < mdssRoles.length; i++)
    {
      roles.put(mdssRoles[i].getRoleName(), mdssRoles[i]);
    }

    return roles;
  }

  @Override
  public void failGetUniversal(String actor) throws IOException, ServletException
  {
    this.req.getRequestDispatcher("/index").forward(this.req, this.resp);
  }

  @Override
  public void getFormAttributes(String actor) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();

      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.put("universal", MdWebFormDTO.CLASS);
      utility.put("actor", actor);
      utility.checkURL(this.getClass().getSimpleName(), "getFormAttributes");

      ReadableAttributeViewDTO[] views = ReadableAttributeViewDTO.getActorAttributes(clientRequest, MdWebFormDTO.CLASS, actor);
      List<ReadableAttributeViewDTO> list = new LinkedList<ReadableAttributeViewDTO>();

      for (ReadableAttributeViewDTO view : views)
      {
        String attributeName = view.getAttributeName();

        if (attributeName.equals(MdWebFormDTO.DISPLAYLABEL) || attributeName.equals(MdWebFormDTO.FORMNAME))
        {
          list.add(view);
        }
      }

      req.setAttribute("views", list);
      req.setAttribute("universal", MdWebFormDTO.CLASS);
      req.setAttribute("actor", actor);
      req.setAttribute("actorLabel", this.getAllRoles(clientRequest).get(actor).getDisplayLabel());
      req.setAttribute("component", req.getParameter("component"));

      render("view.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failGetFormAttributes(actor);
      }
    }
  }

  @Override
  public void failGetFormAttributes(String actor) throws IOException, ServletException
  {
    this.getUniversal(actor);
  }

  @Override
  public void getAttributes(String universal, String actor) throws IOException, ServletException
  {
    if (universal.equals(MdWebFormDTO.CLASS))
    {
      this.getFormAttributes(actor);
    }
    
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();

      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.put("universal", universal);
      utility.put("actor", actor);
      utility.checkURL(this.getClass().getSimpleName(), "getAttributes");

      ReadableAttributeViewDTO[] attributeViews = ReadableAttributeViewDTO.getActorAttributes(clientRequest, universal, actor);
      req.setAttribute("views", Arrays.asList(attributeViews));
      req.setAttribute("universal", universal);
      req.setAttribute("actor", actor);
      req.setAttribute("actorLabel", this.getAllRoles(clientRequest).get(actor).getDisplayLabel());
      req.setAttribute("component", req.getParameter("component"));

      render("view.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failGetAttributes(universal, actor);
      }
    }
  }

  @Override
  public void failGetAttributes(String universal, String actor) throws IOException, ServletException
  {
    this.getUniversal(actor);
  }

  @Override
  public void setAttributes(String universal, String actor, ReadableAttributeViewDTO[] attributeViews) throws IOException, ServletException
  {
    try
    {
      ReadableAttributeViewDTO.setActorAttributes(super.getClientRequest(), universal, actor, attributeViews);
      render("success.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failSetAttributes(universal, actor, attributeViews);
      }
    }
  }

  @Override
  public void failSetAttributes(String universal, String actor, ReadableAttributeViewDTO[] attributeViews) throws IOException, ServletException
  {
    this.getAttributes(universal, actor);
  }
}
