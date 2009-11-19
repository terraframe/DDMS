package dss.vector.solutions;

import java.util.List;

import com.terraframe.mojo.business.BusinessEnumeration;

public class ResponseMaster extends ResponseMasterBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 976308167;
  
  public ResponseMaster()
  {
    super();
  }
  
  public static String getValueForErrorMsg(List<? extends BusinessEnumeration> list)
  {
    String value = "";
    
    for(BusinessEnumeration response : list)
    {
      value += "/" + response.getDisplayLabel();
    }
    
    value = value.replaceFirst("/", "");
    return value;
  }  
}
