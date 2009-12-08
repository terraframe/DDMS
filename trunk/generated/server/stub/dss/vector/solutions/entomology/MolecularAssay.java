package dss.vector.solutions.entomology;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.MolecularSumProblem;
import dss.vector.solutions.Property;

public class MolecularAssay extends MolecularAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1142002498;

  public MolecularAssay()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    return this.getId();
  }

  @Override
  public void apply()
  {
    validateMosquitoId();
    validateSum();

    super.apply();
  }

  @Override
  public void validateMosquitoId()
  {
    Integer sum = this.getSum();

    if (sum > 1 && ( this.hasMosquitoId() ))
    {
      this.setMosquitoId(null);
    }
    if (sum == 1 && ( !this.hasMosquitoId() ))
    {
      this.setMosquitoId(Property.getNextId());
    }
  }

  private Integer getSum()
  {
    int sum = 0;

    sum += ( this.getNumberRR() != null ? this.getNumberRR() : 0 );
    sum += ( this.getNumberRS() != null ? this.getNumberRS() : 0 );
    sum += ( this.getNumberSS() != null ? this.getNumberSS() : 0 );

    return sum;
  }

  public void validateSum()
  {
    Integer sum = this.getSum();

    if (!(sum > 0))
    {
      String msg = "The sum of RR, RS and SS must be GT 0";
      MolecularSumProblem p = new MolecularSumProblem(msg);
      p.apply();

      p.throwIt();
    }
  }

  private boolean hasMosquitoId()
  {
    return this.getMosquitoId() != null && !this.getMosquitoId().equals("");
  }

  @Override
  @Transaction
  public MolecularAssayView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public MolecularAssayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public MolecularAssayView getView()
  {
    MolecularAssayView view = new MolecularAssayView();

    view.populateView(this);

    return view;
  }

}
