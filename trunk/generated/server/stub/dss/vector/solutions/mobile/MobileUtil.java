package dss.vector.solutions.mobile;

import com.runwaysdk.session.Request;

import dss.vector.solutions.export.MosquitoCollectionExcelView;
import dss.vector.solutions.odk.ODKForm;
import dss.vector.solutions.odk.ODKFormExporter;

public class MobileUtil extends MobileUtilBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 2080435980;
  
  public MobileUtil()
  {
    super();
  }
  
  public static void main(String[] args) {
    mainInRequest();
  }
  
  @Request
  private static void mainInRequest()
  {
    MobileUtil.export(MosquitoCollectionExcelView.CLASS);
  }
  
  public static String export(java.lang.String mobileType)
  {
    ODKForm master = ODKForm.factory(mobileType);
    
    ODKFormExporter odkExp = new ODKFormExporter(master);
    
    return odkExp.doIt();
  }
}
