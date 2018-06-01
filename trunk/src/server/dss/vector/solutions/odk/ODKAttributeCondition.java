package dss.vector.solutions.odk;

import java.util.Set;

import com.runwaysdk.constants.BasicConditionInfo;
import com.runwaysdk.dataaccess.EnumerationItemDAO;
import com.runwaysdk.dataaccess.EnumerationItemDAOIF;
import com.runwaysdk.dataaccess.FieldConditionDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.attributes.entity.AttributeEnumeration;
import com.runwaysdk.dataaccess.metadata.BasicConditionDAO;
import com.runwaysdk.dataaccess.metadata.CompositeFieldConditionDAO;

abstract public class ODKAttributeCondition
{
  abstract public String getBindConstraint();
  
  public static ODKAttributeCondition factory(FieldConditionDAOIF condition, ODKAttribute odkAttr, ODKForm odkForm)
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
      
      ODKAttribute condOdkAttr = odkForm.getAttributeByName(basicCond.getDefiningMdFieldDAO().getFieldName());
      if (condOdkAttr == null)
      {
        throw new ProgrammingErrorException("Unable to find attribute by name [" + basicCond.getDefiningMdFieldDAO().getFieldName() + "].");
      }
      
      String value = basicCond.getAttribute(BasicConditionInfo.VALUE).getValue();
      
      return new ODKAttributeConditionBasic(odkAttr, condOdkAttr, operation, value);
    }
    else if (condition instanceof CompositeFieldConditionDAO)
    {
      CompositeFieldConditionDAO composite = (CompositeFieldConditionDAO) condition;
      
      FieldConditionDAOIF firstCond = composite.getFirstCondition();
      FieldConditionDAOIF secondCond = composite.getSecondCondition();
      
      ODKAttributeCondition parent = ODKAttributeCondition.factory(firstCond, odkAttr, odkForm);
      ODKAttributeCondition child = ODKAttributeCondition.factory(secondCond, odkAttr, odkForm);
      
      return new ODKAttributeConditionComposite(parent, ODKAttributeConditionOperation.AND, child);
    }
    else
    {
      throw new UnsupportedOperationException("Unknown condition [" + condition.getKey() + "].");
    }
  }
}
