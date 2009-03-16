package dss.vector.solutions.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.terraframe.mojo.ProblemExceptionDTO;

public class ErrorUtility
{
  public static final String ERROR_MESSAGE_ARRAY = "errorMessageArray";
  
  public static final String ERROR_MESSAGE = "errorMessage";
  
  public static void prepareProblems(ProblemExceptionDTO e, HttpServletRequest req)
  {
    List<String> messages = e.getProblemMessages();
    messages.add(0, e.getLocalizedMessage());
    String[] messagesArr = messages.toArray(new String[messages.size()]);
    
    req.setAttribute(ErrorUtility.ERROR_MESSAGE_ARRAY, messagesArr);
  }
  
  public static void prepareThrowable(Throwable t, HttpServletRequest req)
  {
    req.setAttribute(ErrorUtility.ERROR_MESSAGE, t.getLocalizedMessage());
  }
  
  public static void prepareMessages()
  {
    
  }
}
