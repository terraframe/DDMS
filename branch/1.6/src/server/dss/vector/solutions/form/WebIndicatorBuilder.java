package dss.vector.solutions.form;

import java.util.List;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.constants.IndicatorCompositeInfo;
import com.runwaysdk.constants.IndicatorPrimitiveInfo;
import com.runwaysdk.constants.MdAttributeIndicatorInfo;
import com.runwaysdk.dataaccess.IndicatorCompositeDAO;
import com.runwaysdk.dataaccess.IndicatorElementDAO;
import com.runwaysdk.dataaccess.IndicatorPrimitiveDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.IndicatorAggregateFunction;
import com.runwaysdk.system.metadata.IndicatorOperator;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeIndicator;
import com.runwaysdk.system.metadata.MdWebAttribute;

import dss.vector.solutions.generator.MdWebIndicator;

public class WebIndicatorBuilder extends WebAttributeBuilder implements Reloadable
{
  public WebIndicatorBuilder()
  {
    super();
  }

  @Override
  public MdWebIndicator getMdField()
  {
    return (MdWebIndicator) super.getMdField();
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebIndicatorBuilder();
  }

  @Override
  protected MdAttributeConcrete newMdAttribute()
  {
    String mdAttributeType = this.getMdField().getExpectedMdAttributeType();

    return (MdAttributeConcrete) BusinessFacade.newBusiness(mdAttributeType);
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttribute)
  {
    MdWebIndicator mdWebIndicator = this.getMdField();

    MdAttributeIndicator mdAttributeIndicator = (MdAttributeIndicator) mdAttribute;

    List<IndicatorAggregateFunction> nAgg = mdWebIndicator.getNumeratorAggregation();
    MdWebAttribute numeratorField = mdWebIndicator.getNumeratorField();
    String numeratorAggregation = nAgg.size() > 0 ? nAgg.get(0).getId() : "";

    List<IndicatorAggregateFunction> dAgg = mdWebIndicator.getDenominatorAggregation();
    MdWebAttribute denominatorField = mdWebIndicator.getDenominatorField();
    String denominatorAggregation = dAgg.size() > 0 ? dAgg.get(0).getId() : "";

    if (mdAttributeIndicator.isNew())
    {
      IndicatorPrimitiveDAO left = IndicatorPrimitiveDAO.newInstance();
      left.setValue(IndicatorPrimitiveInfo.MD_ATTRIBUTE_PRIMITIVE, numeratorField.getDefiningMdAttributeId());
      left.setValue(IndicatorPrimitiveInfo.INDICATOR_FUNCTION, numeratorAggregation);
      left.apply();

      IndicatorPrimitiveDAO right = IndicatorPrimitiveDAO.newInstance();
      right.setValue(IndicatorPrimitiveInfo.MD_ATTRIBUTE_PRIMITIVE, denominatorField.getDefiningMdAttributeId());
      right.setValue(IndicatorPrimitiveInfo.INDICATOR_FUNCTION, denominatorAggregation);
      right.apply();

      IndicatorCompositeDAO composite = IndicatorCompositeDAO.newInstance();
      composite.setValue(IndicatorCompositeInfo.LEFT_OPERAND, left.getId());
      composite.setValue(IndicatorCompositeInfo.OPERATOR, IndicatorOperator.DIV.getId());
      composite.setValue(IndicatorCompositeInfo.RIGHT_OPERAND, right.getId());
      composite.setValue(IndicatorCompositeInfo.PERCENTAGE, mdWebIndicator.getPercentage().toString());
      composite.apply();

      mdAttributeIndicator.setValue(MdAttributeIndicatorInfo.INDICATOR_ELEMENT, composite.getId());
    }
    else
    {
      IndicatorCompositeDAO composite = IndicatorCompositeDAO.get(mdAttributeIndicator.getIndicatorElementId()).getBusinessDAO();
      composite.setValue(IndicatorCompositeInfo.PERCENTAGE, mdWebIndicator.getPercentage().toString());
      composite.apply();

      IndicatorElementDAO left = composite.getLeftOperand().getBusinessDAO();
      left.setValue(IndicatorPrimitiveInfo.MD_ATTRIBUTE_PRIMITIVE, numeratorField.getDefiningMdAttributeId());
      left.setValue(IndicatorPrimitiveInfo.INDICATOR_FUNCTION, numeratorAggregation);
      left.apply();

      IndicatorElementDAO right = composite.getRightOperand().getBusinessDAO();
      right.setValue(IndicatorPrimitiveInfo.MD_ATTRIBUTE_PRIMITIVE, denominatorField.getDefiningMdAttributeId());
      right.setValue(IndicatorPrimitiveInfo.INDICATOR_FUNCTION, denominatorAggregation);
      right.apply();
    }

    super.updateMdAttribute(mdAttributeIndicator);
  }

}