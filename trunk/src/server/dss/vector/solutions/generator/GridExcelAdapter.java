package dss.vector.solutions.generator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdFormDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.MdWebSingleTermGridDAOIF;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.ExcelExportSheet;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.io.excel.AttributeColumn;
import com.runwaysdk.dataaccess.io.excel.ContextBuilderIF;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdWebPrimitive;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class GridExcelAdapter extends ExcelExportSheet implements ContextBuilderIF, Reloadable
{
  public static final String       PARENT_COLUMN_NAME = "parentId";

  private MdFormDAOIF              mdForm;

  private MdWebSingleTermGridDAOIF mdCompositeField;

  private MdRelationshipDAOIF      mdRelationship;

  public GridExcelAdapter(MdFormDAOIF mdForm, MdWebSingleTermGridDAOIF mdCompositeField, MdRelationshipDAOIF mdRelationship)
  {
    super(new LinkedList<ExcelExportListener>());

    this.mdForm = mdForm;
    this.mdCompositeField = mdCompositeField;
    this.mdRelationship = mdRelationship;
  }

  @Override
  public void addTemplate(String type)
  {
    this.setType(mdRelationship.definesType());
    this.prepareColumns(type);
    this.addColumnsFromListeners();
  }

  /**
   * Converts a list of MdAttributes into a list of ColumnInfos, storing any
   * necessary metadata in the process.
   * 
   * @param mdForm
   */
  protected void prepareColumns(String type)
  {
    Term[] roots = this.getRoots();

    MdWebPrimitive[] fields = MdFormUtil.getCompositeFields(mdCompositeField.getId());

    this.addExpectedColumn(new ExcelColumn(PARENT_COLUMN_NAME, this.mdForm.getDisplayLabel(Session.getCurrentLocale())));

    for (Term root : roots)
    {
      String termDisplayLabel = root.getTermDisplayLabel().getValue();

      for (MdWebPrimitive field : fields)
      {
        String fieldName = field.getFieldName();
        String fieldLabel = field.getDisplayLabel().getValue();
        String columnLabel = fieldLabel + " " + termDisplayLabel;
        String attributeName = this.getAttributeName(fieldName, root);

        this.addExpectedColumn(new ExcelColumn(attributeName, columnLabel));
      }
    }
  }

  @Override
  public String getSheetName()
  {
    String formLabel = this.mdForm.getDisplayLabel(Session.getCurrentLocale());
    String fieldLabel = mdCompositeField.getDisplayLabel(Session.getCurrentLocale());

    return fieldLabel + " (" + formLabel + ")";
  }

  private Term[] getRoots()
  {
    return TermRootCache.getRoots(mdCompositeField.getDefiningMdAttribute());
  }

  private String getAttributeName(String fieldName, Term root)
  {
    return fieldName + " - " + root.getTermId();
  }

  @Override
  public ImportContext createContext(Sheet sheet, String sheetName, Workbook errorWorkbook, String type)
  {
    return new GridContext(sheet, sheetName, errorWorkbook.createSheet(sheetName), mdRelationship, mdCompositeField);
  }

  @Override
  public void configure(ImportContext context, Row typeRow, Row nameRow, Row labelRow)
  {
    // Copy the type, name, and label rows to the error sheet
    context.addErrorRow(typeRow);
    context.addErrorRow(nameRow);
    context.addErrorRow(labelRow);

    Map<String, ExcelColumn> map = new HashMap<String, ExcelColumn>();

    // Load the expected columns and ignore all of the rest
    Term[] roots = TermRootCache.getRoots(mdCompositeField.getDefiningMdAttribute());

    MdWebPrimitive[] fields = MdFormUtil.getCompositeFields(mdCompositeField.getId());

    map.put(PARENT_COLUMN_NAME, new ExcelColumn(PARENT_COLUMN_NAME, this.mdForm.getDisplayLabel(Session.getCurrentLocale())));

    for (Term root : roots)
    {
      String termDisplayLabel = root.getTermDisplayLabel().getValue();

      for (MdWebPrimitive field : fields)
      {
        String fieldName = field.getFieldName();
        String fieldLabel = field.getDisplayLabel().getValue();
        String columnLabel = fieldLabel + " " + termDisplayLabel;
        String attributeName = this.getAttributeName(fieldName, root);

        MdAttributeDAOIF mdAttribute = (MdAttributeDAOIF) BusinessFacade.getEntityDAO(field.getDefiningMdAttribute());

        AttributeColumn column = new AttributeColumn(mdAttribute);
        column.setDisplayLabel(columnLabel);
        column.setAttributeName(attributeName);

        map.put(attributeName, column);
      }
    }

    Iterator<Cell> nameIterator = nameRow.cellIterator();
    Iterator<Cell> labelIterator = labelRow.cellIterator();
    while (nameIterator.hasNext())
    {
      Cell nameCell = nameIterator.next();
      Cell labelCell = labelIterator.next();

      String columnName = ExcelUtil.getString(nameCell);
      String columnLabel = ExcelUtil.getString(labelCell);

      if (map.containsKey(columnName))
      {
        ExcelColumn column = map.get(columnName);
        column.setDisplayLabel(columnLabel);
        column.setIndex(nameCell.getColumnIndex());

        context.addExtraColumn(column);
      }
    }
  }

}
