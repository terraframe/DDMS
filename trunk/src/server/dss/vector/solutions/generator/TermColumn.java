package dss.vector.solutions.generator;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.io.excel.AttributeColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;

public class TermColumn extends AttributeColumn implements Reloadable
{
  private MdAttributeReferenceDAOIF mdAttribute;

  public TermColumn(MdAttributeReferenceDAOIF mdAttribute)
  {
    super(mdAttribute);

    this.mdAttribute = mdAttribute;
  }

  @Override
  public String javaType()
  {
    return Term.class.getName();
  }

  @Override
  public Object getValue(HSSFCell cell) throws Exception
  {
    String displayLabel = ExcelUtil.getString(cell);

    return Term.validateByDisplayLabel(displayLabel, mdAttribute);
  }
}
