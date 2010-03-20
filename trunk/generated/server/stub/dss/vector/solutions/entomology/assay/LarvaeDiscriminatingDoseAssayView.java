package dss.vector.solutions.entomology.assay;

import com.runwaysdk.constants.EnumerationMasterInfo;
import com.runwaysdk.query.AttributeEnumeration;
import com.runwaysdk.query.AttributeLocal;
import com.runwaysdk.query.AttributeReference;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;

import dss.vector.solutions.ontology.Term;

public class LarvaeDiscriminatingDoseAssayView extends LarvaeDiscriminatingDoseAssayViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 942018061;
  
  public LarvaeDiscriminatingDoseAssayView()
  {
    super();
  }
  
  public static LarvaeDiscriminatingDoseAssayViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    LarvaeDiscriminatingDoseAssayViewQuery query = new LarvaeDiscriminatingDoseAssayViewQuery(new QueryFactory());
    
    sortAttribute = (sortAttribute == null ? LarvaeDiscriminatingDoseAssayView.COLLECTIONLABEL : sortAttribute); 
    
    Selectable attribute = query.getComponentQuery().getSelectableRef(sortAttribute);

    if (attribute instanceof AttributeEnumeration)
    {
      attribute = ( (AttributeEnumeration) attribute ).aCharacter(EnumerationMasterInfo.NAME);
    }
    else if (attribute instanceof AttributeReference)
    {
      attribute = ((AttributeReference) attribute).get(Term.NAME);
    }
    else if (attribute instanceof AttributeLocal)
    {
      attribute = ( (AttributeLocal) attribute ).localize();
    }

    if (isAscending)
    {
      query.ORDER_BY_ASC((SelectablePrimitive) attribute, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC((SelectablePrimitive) attribute, sortAttribute);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;
  }
  

}
