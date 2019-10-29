package dss.vector.solutions.odk;

import java.util.LinkedList;
import java.util.Map;

public interface MobileImportViewIF
{
  public Map<String,String[]> getAttributeSourceMap();
  
  public LinkedList<String> getAttributeOrder();
}
