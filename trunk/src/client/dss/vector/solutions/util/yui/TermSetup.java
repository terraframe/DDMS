package dss.vector.solutions.util.yui;

import java.util.Map;

import com.runwaysdk.ApplicationException;
import com.runwaysdk.business.ViewDTO;
import com.runwaysdk.business.generation.GenerationUtil;
import com.runwaysdk.controller.DTOFacade;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.TermDTO;

public class TermSetup implements Reloadable
{
  private String labelKey;

  private String termKey;

  public TermSetup()
  {
    this("", null);
  }

  public TermSetup(String labelKey, String termKey)
  {
    this.labelKey = GenerationUtil.upperFirstCharacter(labelKey);
    this.termKey = GenerationUtil.upperFirstCharacter(termKey);
  }

  public String getLabelKey()
  {
    return labelKey;
  }

  public void setLabelKey(String labelKey)
  {
    this.labelKey = labelKey;
  }

  public void prepare(TermDTO termDTO, Map<String, ColumnSetup> map, ViewDTO view)
  {
    if (map.containsKey(labelKey))
    {
      ColumnSetup setup = map.get(labelKey);

      setup.setLabel(termDTO.getDisplayLabel());
    }
    
    if(termKey != null)
    {
      
      try
      {
        DTOFacade facade = new DTOFacade(termKey, view);
        facade.setValue(termDTO);
      }
      catch (Exception e)
      {
        throw new ApplicationException(e);
      }
    }
  }
}
