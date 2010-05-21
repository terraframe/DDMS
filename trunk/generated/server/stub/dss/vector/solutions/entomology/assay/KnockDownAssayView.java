package dss.vector.solutions.entomology.assay;

import com.runwaysdk.query.AttributeEnumeration;
import com.runwaysdk.query.AttributeLocal;
import com.runwaysdk.query.AttributeReference;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.system.EnumerationMaster;

import dss.vector.solutions.ontology.Term;

public class KnockDownAssayView extends KnockDownAssayViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 483840115;
  
  public KnockDownAssayView()
  {
    super();
  }
  
  public static KnockDownAssayViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    KnockDownAssayViewQuery query = new KnockDownAssayViewQuery(new QueryFactory());
    
    sortAttribute = (sortAttribute == null ? KnockDownAssayView.COLLECTIONLABEL : sortAttribute); 
    
    Selectable attribute = query.getComponentQuery().getSelectableRef(sortAttribute);

    if (attribute instanceof AttributeEnumeration)
    {
      attribute = ( (AttributeEnumeration) attribute ).aCharacter(EnumerationMaster.ENUMNAME);
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
