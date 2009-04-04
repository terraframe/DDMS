package dss.vector.solutions.entomology.assay;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.vecmath.GMatrix;
import javax.vecmath.GVector;

import com.gregdennis.drej.PolynomialKernel;
import com.gregdennis.drej.Regression;
import com.gregdennis.drej.Representer;

import dss.vector.solutions.mo.Generation;

public abstract class CollectionAssay extends CollectionAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236893774938L;
  
  public CollectionAssay()
  {
    super();
  }
  
  @Override
  public void validateIsofemale()
  {
    super.validateIsofemale();

    if (this.getIsofemale() && this.isGenerationF0())
    {
      String msg = "Isofemale line cannot be selected if the generation is F0.";

      InvalidGenerationProblem p = new InvalidGenerationProblem(msg);
      p.setNotification(this, ISOFEMALE);
      p.apply();      
      p.throwIt();
    }
  }
  
  private boolean isGenerationF0()
  {
    Generation gen = this.getGeneration();
    List<String> ids = new LinkedList<String>();
    ids.add("MIRO_343458349");
        
    return ids.contains(gen.getTermId());
  }
  
  @Override
  public void validateIntervalTime()
  {
    if (this.getIntervalTime() > this.getExposureTime())
    {
      String msg = "It is impossible to have an interval time larger than the exposure time";

      InvalidIntervalTimeProblem p = new InvalidIntervalTimeProblem(msg);
      p.setIntervalTime(this.getIntervalTime());
      p.setExposureTime(this.getExposureTime());
      p.setNotification(this, INTERVALTIME);
      p.apply();      
      p.throwIt();
    }
  }
  
  @Override
  public void validateTestDate()
  {
    super.validateTestDate();

    if (this.getTestDate() != null)
    {
      Date collectionDate = this.getCollection().getDateCollected();

      if (this.getTestDate().before(collectionDate))
      {
        String msg = "It is impossible to have a test date before the mosquito collection date";

        InvalidTestDateProblem p = new InvalidTestDateProblem(msg);
        p.setTestDate(this.getTestDate());
        p.setCollectionDate(collectionDate);
        p.setNotification(this, TESTDATE);
        p.apply();
        p.throwIt();
      }
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
  
  public abstract TestIntervalIF[] getTestIntervals();

  private double[] getTimeIntervals()
  {
    TestIntervalIF[] array = this.getTestIntervals();
    double[] d = new double[array.length];

    for (int i = 0; i < array.length; i++)
    {
      d[i] = array[i].getIntervalTime();
    }

    return d;
  }

  private double[] getLogKnockDownPercent()
  {
    TestIntervalIF[] array = this.getTestIntervals();

    double[] d = new double[array.length];

    for (int i = 0; i < array.length; i++)
    {
      double percent = array[i].getValue() / (double) this.getQuantityTested() * 100;

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
  
  @Override
  public void apply()
  {
    validateIsofemale();
    validateIntervalTime();
    validateTestDate();

    super.apply();
  }
    

  
  public Integer calculatePeriod()
  {
    double exposureTime = (double) this.getExposureTime();
    Integer intervalTime = this.getIntervalTime();

    return (int) Math.ceil(exposureTime / intervalTime) + 1;
  }
}