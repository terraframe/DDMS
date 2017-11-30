package dss.vector.solutions.generator;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.runwaysdk.dataaccess.MdWebFormDAOIF;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.io.excel.ContextBuilderIF;
import com.runwaysdk.dataaccess.metadata.MdWebFormDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdWebForm;

import dss.vector.solutions.ExcelImportManager;

public class ContextBuilderFacade implements ContextBuilderIF, Reloadable
{
  private static final String           DEFAULT = "DEFAULT";

  private Map<String, ContextBuilderIF> map;
  
  private ExcelImportManager manager;

  public ContextBuilderFacade(ExcelImportManager manager)
  {
    this.map = new HashMap<String, ContextBuilderIF>();
    this.map.put(DEFAULT, new DefaultContextBuilder());
    this.manager = manager;
  }

  public ContextBuilderFacade(ContextBuilderIF defaultBuilder, ExcelImportManager manager)
  {
    this.map = new HashMap<String, ContextBuilderIF>();
    this.map.put(DEFAULT, defaultBuilder);
    this.manager = manager;
  }
  
  public void add(String contextType, ContextBuilderIF builder)
  {
    this.map.put(contextType, builder);
  }

  @Override
  public ImportContext createContext(Sheet sheet, String sheetName, Workbook errorWorkbook, String type)
  {
    ContextBuilderIF builder = this.getBuilder(type);

    return builder.createContext(sheet, sheetName, errorWorkbook, type);
  }
  
  @Override
  public void configure(ImportContext context, Row typeRow, Row nameRow, Row labelRow)
  {
    ContextBuilderIF builder = this.getBuilder(context.getMdClassType());

    builder.configure(context, typeRow, nameRow, labelRow);
  }

  public ContextBuilderIF getBuilder(String type)
  {
    if (this.map.containsKey(type))
    {
      return this.map.get(type);
    }

    if (MdFormUtil.isFormBusinessPackage(type))
    {
      if (!this.map.containsKey(type))
      {
        MdWebForm mdForm = MdFormUtil.getMdFormFromBusinessType(type);

        if (mdForm != null)
        {
          MdWebFormDAOIF mdFormDao = MdWebFormDAO.get(mdForm.getId());

          String classType = mdForm.getFormMdClass().definesType();

          this.add(classType, new FormContextBuilder(mdFormDao, new FormImportFilter(), manager));

          MdFormUtil.addGridContexts(mdFormDao, this);
        }
        else
        {
          UnsupportedImportTypeException e = new UnsupportedImportTypeException();
          e.setClassType(type);
          e.apply();

          throw e;
        }
      }

      return this.map.get(type);
    }
    else if (MdFormUtil.isFormRelationshipPackage(type))
    {
      if (!this.map.containsKey(type))
      {
        UnsupportedImportTypeException e = new UnsupportedImportTypeException();
        e.setClassType(type);
        e.apply();

        throw e;
      }

      return this.map.get(type);
    }

    return this.map.get(DEFAULT);
  }
}
