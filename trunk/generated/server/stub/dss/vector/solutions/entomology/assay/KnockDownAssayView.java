package dss.vector.solutions.entomology.assay;

import com.terraframe.mojo.query.AttributeEnumeration;
import com.terraframe.mojo.query.AttributeLocal;
import com.terraframe.mojo.query.AttributeReference;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectablePrimitive;

import dss.vector.solutions.ontology.Term;

public class KnockDownAssayView extends KnockDownAssayViewBase implements com.terraframe.mojo.generation.loader.Reloadable
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
    
    Selectable attribute = query.getComponentQuery().getSelectable(sortAttribute);

    if (attribute instanceof AttributeEnumeration)
    {
      attribute = ( (AttributeEnumeration) attribute ).aCharacter("enumName");
    }
    else if (attribute instanceof AttributeReference)
    {
      attribute = ((AttributeReference) attribute).aAttribute(Term.NAME);
    }
    else if (attribute instanceof AttributeLocal)
    {
      attribute = ( (AttributeLocal) attribute ).currentLocale();
    }

    if (isAscending)
    {
      query.ORDER_BY_ASC((SelectablePrimitive) attribute);
    }
    else
    {
      query.ORDER_BY_DESC((SelectablePrimitive) attribute);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;
  }

  
}
