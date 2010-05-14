package dss.vector.solutions.general;

public class Disease extends DiseaseBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1180941084;
  
  public static final String MALARIA = "MALARIA";
  public static final String DENGUE = "DENGUE";
  
  public static Disease getMalaria() {
	  return Disease.getByKey(MALARIA);
  }
  
  public static Disease getDengue() {
	  return Disease.getByKey(DENGUE);
  }
  
  public Disease()
  {
    super();
  }
  
}
