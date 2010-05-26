package dss.vector.solutions.entomology;

import java.util.List;

import com.runwaysdk.constants.SupportedLocaleInfo;
import com.runwaysdk.dataaccess.SupportedLocaleDAOIF;
import com.runwaysdk.dataaccess.metadata.SupportedLocaleDAO;
import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.system.EnumerationMaster;

public class ImmatureThresholdView extends ImmatureThresholdViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -953552550;

  public ImmatureThresholdView()
  {
    super();
  }

  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      ImmatureThreshold.get(this.getConcreteId()).delete();
    }
  }

  public void apply()
  {
    ImmatureThreshold concrete = new ImmatureThreshold();

    if (this.hasConcrete())
    {
      concrete = ImmatureThreshold.lock(this.getConcreteId());
    }

    this.populateMapping(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  private void populateMapping(ImmatureThreshold concrete)
  {
    new AttributeNotificationMap(concrete, ImmatureThreshold.THRESHOLDINDEX, this, ImmatureThresholdView.THRESHOLDINDEX);
    new AttributeNotificationMap(concrete, ImmatureThreshold.DISPLAYLABEL, this, ImmatureThresholdView.DISPLAYLABEL);
    new AttributeNotificationMap(concrete, ImmatureThreshold.THRESHOLDVALUE, this, ImmatureThresholdView.THRESHOLDVALUE);
  }

  public void populateView(ImmatureThreshold concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setThresholdIndex(concrete.getThresholdIndex());
    this.populateLabel(concrete.getDisplayLabel());
    this.setThresholdValue(concrete.getThresholdValue());
  }

  private void populateLabel(ImmatureThresholdDisplayLabel source)
  {
    ImmatureThresholdDisplayLabel dest = this.getDisplayLabel();

    List<String> ids = SupportedLocaleDAO.getEntityIdsDB(SupportedLocaleInfo.CLASS);
    
    String defaultValue = source.getValue("defaultLocale");
    dest.setValue("defaultLocale", defaultValue);

    for (String id : ids)
    {
      SupportedLocaleDAOIF supportedLocale = SupportedLocaleDAO.get(id);
      String enumName = supportedLocale.getValue(EnumerationMaster.ENUMNAME);

      String value = source.getValue(enumName);
      dest.setValue(enumName, value);
    }
  }

  public void populateConcrete(ImmatureThreshold concrete)
  {
    concrete.setThresholdValue(this.getThresholdValue());
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  public static ImmatureThresholdViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    ImmatureThresholdViewQuery query = new ImmatureThresholdViewQuery(new QueryFactory());

    if (sortAttribute == null)
    {
      sortAttribute = DISPLAYLABEL;
    }

    SelectablePrimitive selectable = null;

    if (sortAttribute.equals(DISPLAYLABEL))
    {
      selectable = (SelectablePrimitive) query.getDisplayLabel().getSessionLocale();
    }
    else
    {
      selectable = (SelectablePrimitive)query.getComponentQuery().getSelectableRef(sortAttribute);
    }
    

    if (isAscending)
    {
      query.ORDER_BY_ASC(selectable, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC(selectable, sortAttribute);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;

  }
}
