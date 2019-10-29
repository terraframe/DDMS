package dss.vector.solutions.migration;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdWebForm;
import com.runwaysdk.system.metadata.MdWebFormQuery;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.generator.FormSystemURLBuilder;

public class Patcher3974 implements DDMSPatchIF, Reloadable
{
  public static void main(String[] args) {
    mainInRequest();
  }
  @Request
  public static void mainInRequest()
  {
    new Patcher3974().doIt(); 
  }
  
  @Override
  public Integer getPatchVersion()
  {
    return 9158;
  }

  @Override
  public void doIt()
  {
    // Refresh all the url permissions for generated forms
    MdWebFormQuery formQ = new MdWebFormQuery(new QueryFactory());
    OIterator<? extends MdWebForm> it = formQ.getIterator();
    
    try
    {
      for (MdWebForm form : it)
      {
        FormSystemURLBuilder builder = new FormSystemURLBuilder(form);
        builder.addPermissions(Disease.getAllDiseases());
      }
    }
    finally
    {
      it.close();
    }
  }

  @Override
  public void undoIt()
  {
    throw new UnsupportedOperationException();
  }
}
