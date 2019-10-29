package dss.vector.solutions.export;

import java.util.List;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.io.excel.ExcelAdapter;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttribute;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public abstract class AbstractExcelAdapter extends ExcelAdapter implements Reloadable
{
  protected void addGridColumns(List<ExcelColumn> extraColumns, MdAttributeReferenceDAOIF mdAttribute, String prefix)
  {
    for (Term term : TermRootCache.getRoots(mdAttribute))
    {
      String columnName = prefix + term.getTermId();
      String label = term.getTermDisplayLabel().getValue();

      extraColumns.add(new GridExcelColumn(columnName, label, mdAttribute.definesAttribute(), term.getTermId()));
    }
  }

  protected void addGridColumns(List<ExcelColumn> extraColumns, MdAttributeReferenceDAOIF mdAttribute, String prefix, String suffix)
  {
    for (Term term : TermRootCache.getRoots(mdAttribute))
    {
      String columnName = prefix + term.getTermId();
      String label = term.getTermDisplayLabel().getValue();

      extraColumns.add(new GridExcelColumn(columnName, label + " " + suffix, mdAttribute.definesAttribute(), term.getTermId()));
    }
  }
  
  protected void addGridColumns(List<ExcelColumn> extraColumns, MdAttributeReferenceDAOIF mdAttribute, String prefix, String suffix, String subAttribute)
  {
    for (Term term : TermRootCache.getRoots(mdAttribute))
    {
      String columnName = prefix + term.getTermId();
      String label = suffix != null ? term.getTermDisplayLabel().getValue() + " " + suffix : term.getTermDisplayLabel().getValue();
      
      extraColumns.add(new GridExcelColumn(columnName, label, mdAttribute.definesAttribute(), term.getTermId(), subAttribute));
    }
  }

  protected void addGridColumns(List<ExcelColumn> extraColumns, MdAttributeReferenceDAOIF mdAttribute, String prefix, MdAttributeDAOIF suffix)
  {
    String suffixLabel = MdAttribute.get(suffix.getId()).getDisplayLabel().getValue();

    this.addGridColumns(extraColumns, mdAttribute, prefix, suffixLabel);
  }

}
