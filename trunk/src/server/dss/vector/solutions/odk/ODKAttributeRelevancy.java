package dss.vector.solutions.odk;

import java.util.Set;

import com.runwaysdk.business.ontology.Term;
import com.runwaysdk.constants.BasicConditionInfo;
import com.runwaysdk.dataaccess.EnumerationItemDAO;
import com.runwaysdk.dataaccess.EnumerationItemDAOIF;
import com.runwaysdk.dataaccess.FieldConditionDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.attributes.entity.AttributeEnumeration;
import com.runwaysdk.dataaccess.metadata.BasicConditionDAO;
import com.runwaysdk.dataaccess.metadata.CompositeFieldConditionDAO;
import com.runwaysdk.dataaccess.metadata.MdWebAttributeDAO;
import com.runwaysdk.generation.loader.Reloadable;

/**
 * An attribute relevancy controls visibility of the attribute. If the condition is false, the attribute will
 * not be shown to the user.
 * 
 * @author rrowlands
 */
abstract public class ODKAttributeRelevancy implements Reloadable
{
  abstract public String getBindRelevant();
  
  public static ODKAttributeRelevancy factory(FieldConditionDAOIF condition, ODKAttribute odkAttr, ODKForm odkForm)
  {
    if (condition instanceof BasicConditionDAO)
    {
      BasicConditionDAO basicCond = (BasicConditionDAO) condition;
      
      ODKAttributeConditionOperation operation = null;
      AttributeEnumeration attribute = (AttributeEnumeration) basicCond.getAttribute(BasicConditionInfo.OPERATION);
      Set<String> itemIds = attribute.getEnumItemIdList();
      for (String itemId : itemIds)
      {
        EnumerationItemDAOIF item = EnumerationItemDAO.get(itemId);
        
        String opStr = item.getName();
        
        operation = ODKAttributeConditionOperation.factory(opStr);
      }
      if (operation == null)
      {
        throw new ProgrammingErrorException("Operation should not be null");
      }
      ODKAttribute condOdkAttr = odkForm.getAttributeByName((((MdWebAttributeDAO)basicCond.getDefiningMdFieldDAO()).getDefiningMdAttribute().definesAttribute()));
      if (condOdkAttr == null)
      {
        throw new ProgrammingErrorException("Unable to find attribute by name [" + basicCond.getDefiningMdFieldDAO().getFieldName() + "].");
      }
      
      String value = basicCond.getAttribute(BasicConditionInfo.VALUE).getValue();
      
      ODKConditionComparative comparative;
      if (odkAttr instanceof ODKTermAttribute)
      {
        comparative = new ODKConditionComparative(Term.get(value));
      }
      else
      {
        comparative = new ODKConditionComparative(value);
      }
      
      return new ODKAttributeRelevancyBasic(odkAttr, condOdkAttr, operation, comparative);
    }
    else if (condition instanceof CompositeFieldConditionDAO)
    {
      CompositeFieldConditionDAO composite = (CompositeFieldConditionDAO) condition;
      
      FieldConditionDAOIF firstCond = composite.getFirstCondition();
      FieldConditionDAOIF secondCond = composite.getSecondCondition();
      
      ODKAttributeRelevancy parent = ODKAttributeRelevancy.factory(firstCond, odkAttr, odkForm);
      ODKAttributeRelevancy child = ODKAttributeRelevancy.factory(secondCond, odkAttr, odkForm);
      
      return new ODKAttributeRelevancyComposite(parent, ODKAttributeConditionOperation.AND, child);
    }
    else
    {
      throw new UnsupportedOperationException("Unknown condition [" + condition.getKey() + "].");
    }
  }
}
