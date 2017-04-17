package dss.vector.solutions;

import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.runwaysdk.dataaccess.io.ExcelImporter;

import dss.vector.solutions.export.ExcelReadException;
import dss.vector.solutions.general.EpiCache;
import dss.vector.solutions.generator.ContextBuilderFacade;
import dss.vector.solutions.generator.DefaultContextBuilder;
import dss.vector.solutions.geo.UnknownGeoEntity;
import dss.vector.solutions.ontology.TermRootCache;

public class ExcelImportManager extends ExcelImportManagerBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 792922881;
  
  private List<UnknownGeoEntity> unknownEntityList = new LinkedList<UnknownGeoEntity>();
  
  private Set<String> unknownGeoEntityNameSet = new HashSet<String>();
  
  public ExcelImportManager()
  {
    super();
  }
  
  public static dss.vector.solutions.ExcelImportManager getNewInstance()
  {
    ExcelImportManager inst = new ExcelImportManager();
    inst.apply();
    return inst;
  }
  
  /**
   * MdMethod
   * 
   * @return
   */
  public java.io.InputStream importWhatYouCan(java.io.InputStream inputStream, java.lang.String[] params)
  {
    // Start caching Broswer Roots for this Thread.
    TermRootCache.start();
    EpiCache.start();

    try
    {
      ContextBuilderFacade builder = new ContextBuilderFacade(new DefaultContextBuilder(params, this));

      ExcelImporter importer = new ExcelImporter(inputStream, builder);

      try
      {
        return new ByteArrayInputStream(importer.read());
      }
      catch (RuntimeException e)
      {
        /*
         * Ticket #2663:  Errors from reading external sheet should have a better error message.
         * Unfortunately, the HSSF API doesn't throw a specific exception for external sheet errors,
         * but throws a RuntimeException.  As such the only way to tell if the exception is an
         * external sheet error is by reading the message.
         */
        Throwable cause = e.getCause();

        if (cause != null && cause.getMessage().startsWith("No external workbook with name"))
        {
          throw new ExcelReadException();
        }

        throw e;
      }
    }
    finally
    {
      TermRootCache.stop();
      EpiCache.stop();
    }
  }
  
  /**
   * MdMethod
   * 
   * @return
   */
  public dss.vector.solutions.geo.UnknownGeoEntity[] getUnmatchedGeoViews()
  {
    String str = this.getUnmatchedGeoViewIdString();
    if (str == null || str.equals("")) { return null; }
    String[] ids = str.split(","); // if this character is a valid character in an id string then we done goofed hardcore here
    
    UnknownGeoEntity[] views = new UnknownGeoEntity[ids.length];
    for (int index = 0; index < ids.length; ++index)
    {
      String id = ids[index];
      views[index] = UnknownGeoEntity.get(id);
    }
    
    return views;
  }
  
  public void setUnmatchedGeoViews(List<UnknownGeoEntity> views)
  {
    if (views.size() <= 0) { return; }
    
    String str = "";
    
    for (int index = 0; index < (views.size() - 1); ++index)
    {
      str = str + views.get(index).getId() + ",";
    }
    
    str = str + views.get(views.size()-1).getId();
    
    this.setUnmatchedGeoViewIdString(str);
  }

  public void addUnknownEntity(UnknownGeoEntity unknownGeoEntity)
  {
    unknownEntityList.add(unknownGeoEntity);
  }

  public void addUnknownGeoEntityName(String endPointEntityName)
  {
    unknownGeoEntityNameSet.add(endPointEntityName);
  }

  public boolean isGeoEntityNameUnknown(String endPointEntityName)
  {
    return unknownGeoEntityNameSet.contains(endPointEntityName);
  }
  
  public void onFinishImport()
  {
    this.setUnmatchedGeoViews(unknownEntityList);
    this.apply();
  }
}
