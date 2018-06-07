package dss.vector.solutions.odk;

import com.runwaysdk.business.BusinessEnumeration;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;

import dss.vector.solutions.ontology.Term;

public class ODKConditionComparative
{
  private Object comparative;
  
  public ODKConditionComparative(Object comparative)
  {
    this.comparative = comparative;
  }
  
  public String toString()
  {
    if (comparative instanceof String)
    {
      return (String) comparative;
    }
    else if (comparative instanceof MdAttributeDAOIF)
    {
      return ((MdAttributeDAOIF) comparative).definesAttribute();
    }
    else if (comparative instanceof ODKAttribute)
    {
      return ((ODKAttribute)comparative).getAttributeName();
    }
    else if (comparative instanceof Term)
    {
      Term t = (Term) comparative;
      return ODKTermAttribute.sanitizeTermId(t.getTermId());
    }
    else if (comparative instanceof BusinessEnumeration)
    {
      return ((BusinessEnumeration) comparative).name();
    }
    else
    {
      throw new UnsupportedOperationException("Object [" + comparative.toString() + "] is not a supported ODK condition comparative.");
    }
  }
  
  public static ODKConditionComparative today()
  {
    return new ODKConditionComparative("today()");
  }
}
