package dss.vector.solutions.odk;

import com.runwaysdk.constants.MdAttributeDateInfo;
import com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDateDAOIF;
import com.runwaysdk.dataaccess.MdAttributeNumberDAOIF;
import com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeDateDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.util.LocalizationFacade;

/**
 * A constraint is a value enforcement on an attribute. I.e. this value must be greater than 5.
 * If the constraint is violated we disallow the user from entering it and reject the form.
 * 
 * @author rrowlands
 */
abstract public class ODKAttributeConstraint implements Reloadable
{
  abstract public String getBindConstraint();
  
  private String constraintMsg;
  
  public ODKAttributeConstraint(String constraintMsg)
  {
    this.constraintMsg = constraintMsg;
  }
  
  public String getConstraintMsg()
  {
    return this.constraintMsg;
  }
  
  public static void addConstraintsToAttribute(MdAttributeDAOIF mdAttr, ODKAttribute odkAttr)
  {

    if (mdAttr instanceof MdAttributeVirtualDAOIF)
    {
      mdAttr = ((MdAttributeVirtualDAOIF) mdAttr).getMdAttributeConcrete();
    }
    
    if (mdAttr instanceof MdAttributeNumberDAOIF)
    {
      MdAttributeNumberDAOIF mdNumAttr = (MdAttributeNumberDAOIF) mdAttr;
      
      if (mdNumAttr.getStartRange() != null)
      {
        String value = String.valueOf(mdNumAttr.getStartRange());
        
        String localized = LocalizationFacade.getFromBundles("odk_constraint_number_low");
        localized = localized.replace("{0}", mdAttr.getDisplayLabel(Session.getCurrentLocale()));
        localized = localized.replace("{1}", value);
        
        odkAttr.addConstraint(new ODKAttributeConstraintBasic(odkAttr, ODKAttributeConditionOperation.GREATER_THAN, value, localized));
      }
      if (mdNumAttr.getEndRange() != null)
      {
        String value = String.valueOf(mdNumAttr.getEndRange());
        
        String localized = LocalizationFacade.getFromBundles("odk_constraint_number_high");
        localized = localized.replace("{0}", mdAttr.getDisplayLabel(Session.getCurrentLocale()));
        localized = localized.replace("{1}", value);
        
        odkAttr.addConstraint(new ODKAttributeConstraintBasic(odkAttr, ODKAttributeConditionOperation.LESS_THAN, value, localized));
      }
      if (mdNumAttr.isPositiveRejected() != null && Boolean.parseBoolean(mdNumAttr.isPositiveRejected()))
      {
        String localized = LocalizationFacade.getFromBundles("odk_constraint_number_negative");
        localized = localized.replace("{0}", mdAttr.getDisplayLabel(Session.getCurrentLocale()));
        
        odkAttr.addConstraint(new ODKAttributeConstraintBasic(odkAttr, ODKAttributeConditionOperation.LESS_THAN_EQUALS, "0", localized));
      }
      if (mdNumAttr.isNegativeRejected() != null && Boolean.parseBoolean(mdNumAttr.isNegativeRejected()))
      {
        String localized = LocalizationFacade.getFromBundles("odk_constraint_number_positive");
        localized = localized.replace("{0}", mdAttr.getDisplayLabel(Session.getCurrentLocale()));
        
        odkAttr.addConstraint(new ODKAttributeConstraintBasic(odkAttr, ODKAttributeConditionOperation.GREATER_THAN_EQUALS, "0", localized));
      }
      if (mdNumAttr.isZeroRejected() != null && Boolean.parseBoolean(mdNumAttr.isZeroRejected()))
      {
        String localized = LocalizationFacade.getFromBundles("odk_constraint_number_zero");
        localized = localized.replace("{0}", mdAttr.getDisplayLabel(Session.getCurrentLocale()));
        
        odkAttr.addConstraint(new ODKAttributeConstraintBasic(odkAttr, ODKAttributeConditionOperation.NOT_EQUALS, "0", localized));
      }
    }
    else if (mdAttr instanceof MdAttributeCharacterDAOIF)
    {
      MdAttributeCharacterDAOIF mdChar = (MdAttributeCharacterDAOIF) mdAttr;
      
      if (mdChar.getSize() != null && mdChar.getSize().length() > 0)
      {
        String localized = LocalizationFacade.getFromBundles("odk_constraint_char_long");
        localized = localized.replace("{0}", mdAttr.getDisplayLabel(Session.getCurrentLocale()));
        localized = localized.replace("{1}", mdChar.getSize());
        
        odkAttr.addConstraint(new ODKAttributeLengthConstraint(mdChar, odkAttr, mdChar.getSize(), localized));
      }
    }
    else if (mdAttr instanceof MdAttributeDateDAOIF)
    {
      MdAttributeDateDAO mdDate = (MdAttributeDateDAO) mdAttr;
      
      String start = mdDate.getValue(MdAttributeDateInfo.START_DATE);
      String end = mdDate.getValue(MdAttributeDateInfo.END_DATE);
      String beforeIncl = mdDate.getValue(MdAttributeDateInfo.BEFORE_TODAY_INCLUSIVE);
      String beforeExcl = mdDate.getValue(MdAttributeDateInfo.BEFORE_TODAY_EXCLUSIVE);
      String afterExcl = mdDate.getValue(MdAttributeDateInfo.AFTER_TODAY_EXCLUSIVE);
      String afterIncl = mdDate.getValue(MdAttributeDateInfo.AFTER_TODAY_INCLUSIVE);
      
      if (start != null && start.length() > 0)
      {
        String localized = LocalizationFacade.getFromBundles("odk_constraint_date_early");
        localized = localized.replace("{0}", mdAttr.getDisplayLabel(Session.getCurrentLocale()));
        localized = localized.replace("{1}", start);
        
        odkAttr.addConstraint(new ODKAttributeConstraintBasic(odkAttr, ODKAttributeConditionOperation.GREATER_THAN, start, localized));
      }
      if (end != null && end.length() > 0)
      {
        String localized = LocalizationFacade.getFromBundles("odk_constraint_date_late");
        localized = localized.replace("{0}", mdAttr.getDisplayLabel(Session.getCurrentLocale()));
        localized = localized.replace("{1}", end);
        
        odkAttr.addConstraint(new ODKAttributeConstraintBasic(odkAttr, ODKAttributeConditionOperation.LESS_THAN, end, localized));
      }
      if (beforeIncl != null && beforeIncl.length() > 0 && Boolean.parseBoolean(beforeIncl))
      {
        String localized = LocalizationFacade.getFromBundles("odk_constraint_date_beforeTodayInclusive");
        localized = localized.replace("{0}", mdAttr.getDisplayLabel(Session.getCurrentLocale()));
        
        odkAttr.addConstraint(new ODKAttributeConstraintBasic(odkAttr, ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", localized));
      }
      if (beforeExcl != null && beforeExcl.length() > 0 && Boolean.parseBoolean(beforeExcl))
      {
        String localized = LocalizationFacade.getFromBundles("odk_constraint_date_beforeTodayExclusive");
        localized = localized.replace("{0}", mdAttr.getDisplayLabel(Session.getCurrentLocale()));
        
        odkAttr.addConstraint(new ODKAttributeConstraintBasic(odkAttr, ODKAttributeConditionOperation.LESS_THAN, "today()", localized));
      }
      if (afterExcl != null && afterExcl.length() > 0 && Boolean.parseBoolean(afterExcl))
      {
        String localized = LocalizationFacade.getFromBundles("odk_constraint_date_afterTodayExclusive");
        localized = localized.replace("{0}", mdAttr.getDisplayLabel(Session.getCurrentLocale()));
        
        odkAttr.addConstraint(new ODKAttributeConstraintBasic(odkAttr, ODKAttributeConditionOperation.GREATER_THAN, "today()", localized));
      }
      if (afterIncl != null && afterIncl.length() > 0 && Boolean.parseBoolean(afterIncl))
      {
        String localized = LocalizationFacade.getFromBundles("odk_constraint_date_afterTodayInclusive");
        localized = localized.replace("{0}", mdAttr.getDisplayLabel(Session.getCurrentLocale()));
        
        odkAttr.addConstraint(new ODKAttributeConstraintBasic(odkAttr, ODKAttributeConditionOperation.GREATER_THAN_EQUALS, "today()", localized));
      }
    }
  }
}
