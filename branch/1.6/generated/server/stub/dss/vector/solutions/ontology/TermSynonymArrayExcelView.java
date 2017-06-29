package dss.vector.solutions.ontology;

import java.util.ArrayList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.ExcelImportManager;







public class TermSynonymArrayExcelView extends TermSynonymArrayExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 853636322;
  
  public TermSynonymArrayExcelView()
  {
    super();
  }
  
  
  
  
  @Override
  @Transaction
  public void apply()
  {
    String termInsId = this.getTermInstanceId();
    
    Term term = Term.getByTermId(termInsId);
    
    String[] names = this.getSynonymNames().split(",");
    for (int i = 0; i < names.length ; ++i)
    {
      String name = names[i];
      
      TermSynonym.createSynonym(name, term.getTermId());
    }
  }
  
  public static List<String> customAttributeOrder()
  {
    List<String> list = new ArrayList<String>();
    
    list.add(TERMINSTANCEID);
    list.add(SYNONYMNAMES);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
//    exporter.addListener(createExcelTermListener(null));
  }
  
  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
//    context.addListener(createExcelTermListener(importer));
  }

//  private static ExcelAdapter createExcelTermListener(ExcelImportManager importer)
//  {
//    HierarchyBuilder builder = new HierarchyBuilder();
//    for (TermHierarchy hierarchy : TermHierarchy.getAll())
//    {
//      builder.add(hierarchy);
//    }
//    return new DynamicTermColumnListener(CLASS, TERM, builder, importer);
//    return new ExcelAdapter();
//  }
}
