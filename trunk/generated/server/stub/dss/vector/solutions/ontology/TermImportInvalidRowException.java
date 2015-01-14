package dss.vector.solutions.ontology;



public class TermImportInvalidRowException extends TermImportInvalidRowExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1421437253;

  public TermImportInvalidRowException()
  {
    super();
  }

  public TermImportInvalidRowException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }

  public TermImportInvalidRowException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }

  public TermImportInvalidRowException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public TermImportInvalidRowException(String fileName, int rowNum, int colNum, Throwable e) {
    super("An error occurred while parsing " + fileName + " on line " + rowNum + ":" + colNum + ". " + e.getLocalizedMessage(), e);
  }
}
