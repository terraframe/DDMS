package dss.vector.solutions.report;

public class MissingReportDocumentException extends MissingReportDocumentExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -884017385;
  
  public MissingReportDocumentException()
  {
    super();
  }
  
  public MissingReportDocumentException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public MissingReportDocumentException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public MissingReportDocumentException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
