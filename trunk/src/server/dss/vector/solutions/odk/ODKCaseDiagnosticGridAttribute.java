package dss.vector.solutions.odk;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class ODKCaseDiagnosticGridAttribute extends ODKGridAttribute implements Reloadable
{
  public ODKCaseDiagnosticGridAttribute(ODKForm containingForm, MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, String type) {
    super(containingForm, sourceMdAttr, viewMdAttr, type);
  }
  
  @Override
  protected void constructGridAttrs(MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, String type)
  {
    for (Term term : TermRootCache.getRoots(sourceMdAttr))
    {
      String totalLbl = term.getTermDisplayLabel().getValue() + " - Total tests";
      gridAttrs.add(new ODKAttribute(this.getContainingForm(), type, GRID_ATTR_PREFIX + sourceMdAttr.definesAttribute() + "." + term.getKey() + ".total", totalLbl, totalLbl, sourceMdAttr.isRequired()));
      
      String positiveLbl = term.getTermDisplayLabel().getValue() + " - Total positive tests";
      gridAttrs.add(new ODKAttribute(this.getContainingForm(), type, GRID_ATTR_PREFIX + sourceMdAttr.definesAttribute() + "." + term.getKey() + ".positive", positiveLbl, positiveLbl, sourceMdAttr.isRequired()));
    }
  }
}
