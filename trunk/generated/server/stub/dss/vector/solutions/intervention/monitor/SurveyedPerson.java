package dss.vector.solutions.intervention.monitor;

import java.util.List;

import com.terraframe.mojo.dataaccess.MdAttributeBooleanDAOIF;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.RefusedResponse;
import dss.vector.solutions.Response;
import dss.vector.solutions.ResponseMaster;

public class SurveyedPerson extends SurveyedPersonBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1299176290;

  public SurveyedPerson()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    if (this.getPersonId() != null)
    {
      return this.getPersonId();
    }

    return this.getId();
  }

  @Override
  @Transaction
  public void apply()
  {
    validateHaemoglobin();
    validateRdtResult();
    validateRdtDetail();
    validateRdtTreatment();
    validateBloodslideReason();
    validateBloodslideResult();
    validateBloodslideDetail();
    validateMalaria();
    validateMalariaConformationTechnique();
    validatePayment();

    boolean first = this.isNew() && !this.isAppliedToDB();

    super.apply();

    if (first)
    {
      HouseholdSurveyedPerson householdPerson = new HouseholdSurveyedPerson(this.getHousehold(), this);
      householdPerson.apply();
    }
    
    ITNInstance net = this.getSleptUnderNet();

    if(net != null)
    {
      net.lock();
      
      long count = SurveyedPerson.getCount(net);
      
      net.setSleptUnderNet(count);
      net.apply();
    }
  }

  @Override
  public void validateHaemoglobin()
  {
    if (this.getHaemoglobin() != null)
    {
      if (!this.getHaemoglobinMeasured().contains(RefusedResponse.YES))
      {
        String msg = "Cannot have a haemoglobin value when haemoglobin measured is not 'Yes'";
        String value = ResponseMaster.getValueForErrorMsg(this.getHaemoglobinMeasured());

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, HAEMOGLOBIN);
        p.setInputAttribute(getHaemoglobinMeasuredMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateRdtResult()
  {
    if (this.getRdtResult() != null)
    {
      if (!this.getPerformedRDT().contains(RefusedResponse.YES))
      {
        String msg = "Cannot have a RDT result value when performed RDT is not 'Yes'";
        String value = ResponseMaster.getValueForErrorMsg(this.getPerformedRDT());

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, RDTRESULT);
        p.setInputAttribute(getPerformedRDTMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateRdtDetail()
  {
    if (this.getRdtDetail() != null)
    {
      if (!this.getPerformedRDT().contains(RefusedResponse.YES))
      {
        String msg = "Cannot have a RDT detail value when performed RDT is not 'Yes'";
        String value = ResponseMaster.getValueForErrorMsg(this.getPerformedRDT());

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, RDTDETAIL);
        p.setInputAttribute(getPerformedRDTMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateRdtTreatment()
  {
    if (this.getRdtTreatment() != null)
    {
      if (!this.getPerformedRDT().contains(RefusedResponse.YES))
      {
        String msg = "Cannot have a treatment value when performed RDT is not 'Yes'";
        String value = ResponseMaster.getValueForErrorMsg(this.getPerformedRDT());

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, RDTTREATMENT);
        p.setInputAttribute(getPerformedRDTMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateBloodslideReason()
  {
    if (this.getBloodslideReason() != null)
    {
      if (this.getPerformedBloodslide() != null && this.getPerformedBloodslide())
      {
        String msg = "Cannot have a reason for not performing Bloodslide when performed bloodslide is true";
        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, BLOODSLIDEREASON);
        p.setInputAttribute(getPerformedBloodslideMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue( ( (MdAttributeBooleanDAOIF) getPerformedBloodslideMd() ).getPositiveDisplayLabel(Session.getCurrentLocale()));
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateBloodslideDetail()
  {
    if (this.getBloodslideDetail() != null)
    {
      if (this.getPerformedBloodslide() != null && !this.getPerformedBloodslide())
      {
        String msg = "Cannot have a bloodslide result deatils when performed bloodslide is false";
        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, BLOODSLIDEDETAIL);
        p.setInputAttribute(getPerformedBloodslideMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue( ( (MdAttributeBooleanDAOIF) getPerformedBloodslideMd() ).getNegativeDisplayLabel(Session.getCurrentLocale()));
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateBloodslideResult()
  {
    if (this.getBloodslideResult() != null)
    {
      if (this.getPerformedBloodslide() != null && !this.getPerformedBloodslide())
      {
        String msg = "Cannot have a bloodslide result when performed bloodslide is false";
        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, BLOODSLIDERESULT);
        p.setInputAttribute(getPerformedBloodslideMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue( ( (MdAttributeBooleanDAOIF) getPerformedBloodslideMd() ).getNegativeDisplayLabel(Session.getCurrentLocale()));
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateMalaria()
  {
    List<Response> list = this.getMalaria();

    if (list.size() > 0)
    {
      if (!this.getFever().contains(Response.YES))
      {
        String msg = "Cannot have a malaria when a fever was not present";
        String value = ResponseMaster.getValueForErrorMsg(this.getFever());

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, MALARIA);
        p.setInputAttribute(getFeverMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }
  
  @Override
  public void validateMalariaConformationTechnique()
  {
    if (this.getMalariaConformationTechnique() != null)
    {
      if (!this.getMalaria().contains(Response.YES))
      {
        String msg = "Cannot have a malaria conformation technique when a malaria was not present";
        String value = ResponseMaster.getValueForErrorMsg(this.getMalaria());

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, MALARIACONFORMATIONTECHNIQUE);
        p.setInputAttribute(getMalariaMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }
  
  @Override
  public void validatePayment()
  {
    if (this.getPayment() != null)
    {
      if (!this.getMalaria().contains(Response.YES))
      {
        String msg = "Cannot have a pay for treatment when malaria was not present";
        String value = ResponseMaster.getValueForErrorMsg(this.getMalaria());

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, PAYMENT);
        p.setInputAttribute(getMalariaMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  @Transaction
  public SurveyedPersonView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public SurveyedPersonView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public SurveyedPersonView getView()
  {
    SurveyedPersonView view = new SurveyedPersonView();

    view.populateView(this);

    return view;
  }
  
  public static long getCount(ITNInstance net)
  {
    SurveyedPersonQuery query = new SurveyedPersonQuery(new QueryFactory());
    query.WHERE(query.getSleptUnderNet().EQ(net));
    
    return query.getCount();
  }
}
