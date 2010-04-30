package dss.vector.solutions.util.yui;

import java.util.Map;

import com.runwaysdk.business.generation.GenerationUtil;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.TermDTO;


public class TermSetup implements Reloadable
{
  private String  labelKey;

  public TermSetup()
  {
    this("");
  }
  
  public TermSetup(String labelKey)
  {
    this.labelKey = GenerationUtil.upperFirstCharacter(labelKey);
  }

  public String getLabelKey()
  {
    return labelKey;
  }

  public void setLabelKey(String labelKey)
  {
    this.labelKey = labelKey;
  }

  public void prepare(Map<String, ColumnSetup> map, TermDTO termDTO)
  {
    if(map.containsKey(labelKey))
    {
      ColumnSetup setup = map.get(labelKey);
      
      setup.setLabel(termDTO.getDisplayLabel());
    }
  }
}
