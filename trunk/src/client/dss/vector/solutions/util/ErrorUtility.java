package dss.vector.solutions.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.terraframe.mojo.AttributeNotificationDTO;
import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.InformationDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.dataaccess.ProgrammingErrorExceptionDTO;
import com.terraframe.mojo.generation.loader.Reloadable;

public class ErrorUtility implements Reloadable
{
  public static final String  ERROR_MESSAGE_ARRAY = "errorMessageArray";

  public static final String  ERROR_MESSAGE       = "errorMessage";

  public static final String  DEVELOPER_MESSAGE   = "developerMessage";

  public static final String MESSAGE_ARRAY       = "messageArray";

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

  public static void prepareInformation(List<InformationDTO> list, HttpServletRequest req)
  {
    List<String> messages = new LinkedList<String>();

    for (InformationDTO problem : list)
    {
      messages.add(problem.getMessage());
    }

    if (messages.size() > 0)
    {
      req.setAttribute(ErrorUtility.MESSAGE_ARRAY, messages.toArray(new String[messages.size()]));
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

  private static String getErrorMessage(HttpServletRequest req)
  {
    Object errorMessage = req.getAttribute(ErrorUtility.ERROR_MESSAGE);

    if (errorMessage != null && errorMessage instanceof String)
    {
      return ErrorUtility.encodeMessage((String) errorMessage);
    }

    return null;
  }

  private static String getErrorMessageArray(HttpServletRequest req)
  {
    Object errorMessage = req.getAttribute(ErrorUtility.ERROR_MESSAGE_ARRAY);

    if (errorMessage != null && errorMessage instanceof String[])
    {
      StringBuffer buffer = new StringBuffer();

      for (String msg : (String[]) errorMessage)
      {
        buffer.append(msg + "\n");
      }

      return ErrorUtility.encodeMessage(buffer.toString());
    }

    return null;
  }
  
  private static String getMessageArray(HttpServletRequest req)
  {
    Object message = req.getAttribute(ErrorUtility.MESSAGE_ARRAY);
    
    if (message != null && message instanceof String[])
    {
      StringBuffer buffer = new StringBuffer();
      
      for (String msg : (String[]) message)
      {
        buffer.append(msg + "\n");
      }
      
      return ErrorUtility.encodeMessage(buffer.toString());
    }
    
    return null;
  }

  @SuppressWarnings("deprecation")
  private static String encodeMessage(String errorMessage)
  {
    try
    {
      return URLEncoder.encode(errorMessage, "UTF-8");
    }
    catch (UnsupportedEncodingException e)
    {
      return URLEncoder.encode(errorMessage);
    }
  }

  public static void addErrorMessages(HttpServletRequest req, URLUtility utility)
  {
    String errorMessage = ErrorUtility.getErrorMessage(req);
    String errorMessageArray = ErrorUtility.getErrorMessageArray(req);
    String messageArray = ErrorUtility.getMessageArray(req);

    if (errorMessage != null)
    {
      utility.addParameter(ERROR_MESSAGE, errorMessage);
    }

    if (errorMessageArray != null)
    {
      utility.addParameter(ERROR_MESSAGE_ARRAY, errorMessageArray);
    }

    if (messageArray != null)
    {
      utility.addParameter(MESSAGE_ARRAY, messageArray);
    }
  }

  public static void prepareMessages(HttpServletRequest req)
  {
    String errorMessage = req.getParameter(ErrorUtility.ERROR_MESSAGE);
    String errorMessageArray = req.getParameter(ErrorUtility.ERROR_MESSAGE_ARRAY);
    String messageArray = req.getParameter(ErrorUtility.MESSAGE_ARRAY);

    if (errorMessage != null)
    {
      req.setAttribute(ERROR_MESSAGE, errorMessage);
    }

    if (errorMessageArray != null)
    {
      String[] array = errorMessageArray.split("\\n");

      req.setAttribute(ERROR_MESSAGE_ARRAY, array);
    }
    
    if (messageArray != null)
    {
      String[] array = messageArray.split("\\n");
      
      req.setAttribute(MESSAGE_ARRAY, array);
    }    
  }
}
