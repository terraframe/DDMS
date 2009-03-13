package csu.mrc.ivcc.mdss.entomology.assay;

import java.util.LinkedList;
import java.util.List;

import javax.vecmath.GMatrix;
import javax.vecmath.GVector;

import com.gregdennis.drej.PolynomialKernel;
import com.gregdennis.drej.Regression;
import com.gregdennis.drej.Representer;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.OrderBy.SortOrder;

import csu.mrc.ivcc.mdss.Property;

public class AdultDiscriminatingDoseAssay extends AdultDiscriminatingDoseAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234543769104L;

  public AdultDiscriminatingDoseAssay()
  {
    super();
  }
  
  @Override
  public void validateAgeRange()
  {
    super.validateAgeRange();

    new AdultAgeRangeValidator(this).validate();
  }

  @Override
  public void validateGravid()
  {
    super.validateGravid();

    new GravidValidator(this).validate();
  }

  @Override
  public void validateFed()
  {
    super.validateFed();

    new FedValidator(this).validate();
  }

  @Override
  public void apply()
  {
    validateAgeRange();
    validateFed();
    validateGravid();
    
    super.apply();
  }


  @Override
  public void delete()
  {
    this.deleteIntevals();

    super.delete();
  }

  private void deleteIntevals()
  {
    for (ADDATestIntervalView view : this.getTestIntervals())
    {
      ADDATestInterval.get(view.getIntervalId()).delete();
    }
  }

  public ADDATestIntervalView[] getTestIntervals()
  {
    List<ADDATestIntervalView> list = new LinkedList<ADDATestIntervalView>();

    ADDATestIntervalQuery query = new ADDATestIntervalQuery(new QueryFactory());
    query.WHERE(query.getAssay().getId().EQ(this.getId()));
    query.ORDER_BY(query.getPeriod(), SortOrder.ASC);

    OIterator<? extends ADDATestInterval> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        ADDATestInterval interval = iterator.next();

        ADDATestIntervalView view = new ADDATestIntervalView();
        view.setIntervalId(interval.getId());
        view.setAssayId(this.getId());
        view.setKnockedDown(interval.getKnockedDown());
        view.setPeriod(interval.getPeriod());
        view.setIntervalTime(interval.getIntervalTime());
        view.applyNoPersist();

        list.add(view);
      }

      return list.toArray(new ADDATestIntervalView[list.size()]);
    }
    finally
    {
      iterator.close();
    }

  }

  @Override
  public Double getKD50()
  {
    return this.getKD(50);
  }

  @Override
  public Double getKD95()
  {
    return this.getKD(95);
  }

  private Double getKD(int value)
  {
    // Use regression of the form log(y) = (a1 * x) + a0 where a0 = 0

    // x data array
    double[] x = this.getTimeIntervals();
    // Log of the observed y data array
    double[] y = this.getLogKnockDownPercent();

    GMatrix data = new GMatrix(1, x.length);
    GVector values = new GVector(y);

    for (int i = 0; i < x.length; i++)
    {
      data.setColumn(i, new double[] { x[i] });
    }

    double lambda = 0.5;

    // do the regression, which returns a function fit to the data
    Representer representer = Regression.solve(data, values, PolynomialKernel.QUADRATIC_KERNEL, lambda);

    // return representer.eval(new GVector(new double[]{Math.log(value)}));
    return representer.eval(new GVector(new double[] { value }));
  }

  private double[] getTimeIntervals()
  {
    ADDATestIntervalView[] array = this.getTestIntervals();
    double[] d = new double[array.length];

    for (int i = 0; i < array.length; i++)
    {
      d[i] = array[i].getIntervalTime();
    }

    return d;
  }

  private double[] getLogKnockDownPercent()
  {
    ADDATestIntervalView[] array = this.getTestIntervals();

    double[] d = new double[array.length];

    for (int i = 0; i < array.length; i++)
    {
      double percent = array[i].getKnockedDown() / (double) this.getQuantityTested() * 100;

      if (percent > 0)
      {
        // d[i] = Math.log(percent);
        d[i] = percent;
      }
      else
      {
        d[i] = 0;
      }
    }

    return d;
  }

  protected boolean isResistant()
  {
    Integer resistant = Property.getInt(Property.RESISTANCE_PACKAGE, Property.ADULT_DDA_RESISTANCE);

    return ( this.getMortality() < resistant );
  }

  protected boolean isPotentiallyResistant()
  {
    Integer susceptible = Property.getInt(Property.RESISTANCE_PACKAGE, Property.ADULT_DDA_SUSCEPTIBILE);
    Integer resistant = Property.getInt(Property.RESISTANCE_PACKAGE, Property.ADULT_DDA_RESISTANCE);

    return ( resistant < this.getMortality() && this.getMortality() <= susceptible );
  }

  protected boolean isSusceptible()
  {
    Integer susceptible = Property.getInt(Property.RESISTANCE_PACKAGE, Property.ADULT_DDA_SUSCEPTIBILE);

    return ( this.getMortality() > susceptible );
  }
}
