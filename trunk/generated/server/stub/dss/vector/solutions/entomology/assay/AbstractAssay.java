package dss.vector.solutions.entomology.assay;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.constants.MdBusinessInfo;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.CurrentDateProblem;

public abstract class AbstractAssay extends AbstractAssayBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234543768433L;

  public AbstractAssay()
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
    validateTestDate();

    super.apply();
  }

  @Override
  public void validateTestDate()
  {
    if (this.getTestDate() != null)
    {
      super.validateTestDate();

      Date current = new Date();

      if (current.before(this.getTestDate()))
      {
        String msg = "It is impossible to have a test date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getTestDate());
        p.setCurrentDate(current);
        p.setNotification(this, TESTDATE);
        p.apply();
        p.throwIt();
      }
    }
  }
}
