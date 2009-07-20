package dss.vector.solutions.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.terraframe.mojo.AttributeNotificationDTO;
import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.dataaccess.ProgrammingErrorExceptionDTO;
import com.terraframe.mojo.generation.loader.Reloadable;

public class ErrorUtility implements Reloadable
{
  public static final String ERROR_MESSAGE_ARRAY = "errorMessageArray";

  public static final String ERROR_MESSAGE       = "errorMessage";

  public static final String DEVELOPER_MESSAGE   = "developerMessage";

  public static void prepareProblems(ProblemExceptionDTO e, HttpServletRequest req)
  {
    List<String> messages = new LinkedList<String>();

    for (ProblemDTOIF problem : e.getProblems())
    {
      if (! ( problem instanceof AttributeNotificationDTO ))
      {
        String message = problem.getMessage();

        messages.add(message);
      }
    }

    if (messages.size() > 0)
    {
      req.setAttribute(ErrorUtility.ERROR_MESSAGE_ARRAY, messages.toArray(new String[messages.size()]));
    }
  }

  public static void prepareThrowable(Throwable t, HttpServletRequest req)
  {
    req.setAttribute(ErrorUtility.ERROR_MESSAGE, t.getLocalizedMessage());

    if (t instanceof ProgrammingErrorExceptionDTO)
    {
      try
      {
        ProgrammingErrorExceptionDTO pee = (ProgrammingErrorExceptionDTO) t;
        req.setAttribute(ErrorUtility.DEVELOPER_MESSAGE, pee.getDeveloperMessage());
      }
      catch (Exception e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

  }

  public static void forceProblems(ProblemExceptionDTO e, HttpServletRequest req)
  {
    List<String> messages = new LinkedList<String>();

    for (ProblemDTOIF problem : e.getProblems())
    {
      messages.add(problem.getMessage());
    }

    if (messages.size() > 0)
    {
      req.setAttribute(ErrorUtility.ERROR_MESSAGE_ARRAY, messages.toArray(new String[messages.size()]));
    }
  }

  @SuppressWarnings("deprecation")
  public static String getMessages(HttpServletRequest req)
  {
    String messages = new String();

    Object errorMessage = req.getAttribute(ErrorUtility.ERROR_MESSAGE);

    if (errorMessage != null && errorMessage instanceof String)
    {
      try
      {
        String encode = URLEncoder.encode((String)errorMessage, "UTF-8");

        messages = ErrorUtility.ERROR_MESSAGE + "=" + encode;
      }
      catch (UnsupportedEncodingException e)
      {
        return URLEncoder.encode(messages);
      }
    }

    if (messages.equals(""))
    {
      return null;
    }

    return messages;
  }

  public static String prepareURL(String path, HttpServletRequest req, boolean firstAttribute)
  {
    String message = ErrorUtility.getMessages(req);

    if (message != null)
    {
      if (firstAttribute)
      {
        return path += "?" + message;
      }
      else
      {
        return path += "&" + message;
      }
    }

    return path;
  }
}
