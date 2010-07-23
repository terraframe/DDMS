package dss.vector.solutions.intervention.monitor;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Session;

import dss.vector.solutions.AgeConverter;
import dss.vector.solutions.RefusedResponse;
import dss.vector.solutions.Response;
import dss.vector.solutions.ResponseMaster;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermComparator;

public class SurveyedPersonView extends SurveyedPersonViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1558679294;

  public SurveyedPersonView()
  {
    super();
  }

  public void populateView(SurveyedPerson concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setHousehold(concrete.getHousehold());
    this.setPersonId(concrete.getPersonId());
    this.setHeadOfHousehold(concrete.getHeadOfHousehold());
    this.setDob(concrete.getDob());

    if (concrete.getDob() != null)
    {
      this.setAge(new AgeConverter(concrete.getDob()).getAge());
    }

    this.setSex(concrete.getSex());
    this.setPregnant(concrete.getPregnant());
    this.setImmuneCompromised(concrete.getImmuneCompromised());
    this.setSleptUnderNet(concrete.getSleptUnderNet());

    this.clearHaemoglobinMeasured();
    for (RefusedResponse response : concrete.getHaemoglobinMeasured())
    {
      this.addHaemoglobinMeasured(response);
    }

    this.setHaemoglobin(concrete.getHaemoglobin());
    this.setAnaemiaTreatment(concrete.getAnaemiaTreatment());
    this.setIron(concrete.getIron());

    this.clearPerformedRDT();
    for (RefusedResponse response : concrete.getPerformedRDT())
    {
      this.addPerformedRDT(response);
    }

    this.setRdtResult(concrete.getRdtResult());
    this.setRdtDetail(concrete.getRdtDetail());
    this.setRdtTreatment(concrete.getRdtTreatment());
    this.setPerformedBloodslide(concrete.getPerformedBloodslide());
    this.setBloodslideReason(concrete.getBloodslideReason());
    this.setBloodslideResult(concrete.getBloodslideResult());
    this.setBloodslideDetail(concrete.getBloodslideDetail());

    this.clearFever();
    for (Response response : concrete.getFever())
    {
      this.addFever(response);
    }

    this.clearMalaria();
    for (Response response : concrete.getMalaria())
    {
      this.addMalaria(response);
    }

    this.setMalariaConformationTechnique(concrete.getMalariaConformationTechnique());
    this.setPayment(concrete.getPayment());
  }

  private void populateConcrete(SurveyedPerson concrete)
  {
    concrete.setHousehold(this.getHousehold());
    concrete.setPersonId(this.getPersonId());
    concrete.setHeadOfHousehold(this.getHeadOfHousehold());
    concrete.setDob(this.getDob());

    if (this.getDob() == null && this.getAge() != null)
    {
      concrete.setDob(new AgeConverter(this.getAge()).getDateOfBirth());
    }

    concrete.setSex(this.getSex());
    concrete.setPregnant(this.getPregnant());
    concrete.setImmuneCompromised(this.getImmuneCompromised());
    concrete.setSleptUnderNet(this.getSleptUnderNet());

    concrete.clearHaemoglobinMeasured();
    for (RefusedResponse response : this.getHaemoglobinMeasured())
    {
      concrete.addHaemoglobinMeasured(response);
    }

    concrete.setHaemoglobin(this.getHaemoglobin());
    concrete.setAnaemiaTreatment(this.getAnaemiaTreatment());
    concrete.setIron(this.getIron());

    concrete.clearPerformedRDT();
    for (RefusedResponse response : this.getPerformedRDT())
    {
      concrete.addPerformedRDT(response);
    }

    concrete.setRdtResult(this.getRdtResult());
    concrete.setRdtDetail(this.getRdtDetail());
    concrete.setRdtTreatment(this.getRdtTreatment());

    concrete.setPerformedBloodslide(this.getPerformedBloodslide());

    if (this.getPerformedBloodslide() != null && this.getPerformedBloodslide())
    {
      concrete.setBloodslideReason(null);
      concrete.setBloodslideResult(this.getBloodslideResult());
      concrete.setBloodslideDetail(this.getBloodslideDetail());
    }
    else
    {
      concrete.setBloodslideReason(this.getBloodslideReason());
      concrete.setBloodslideResult(null);
      concrete.setBloodslideDetail(null);
    }

    concrete.clearFever();
    for (Response response : this.getFever())
    {
      concrete.addFever(response);
    }

    concrete.clearMalaria();
    for (Response response : this.getMalaria())
    {
      concrete.addMalaria(response);
    }

    concrete.setMalariaConformationTechnique(this.getMalariaConformationTechnique());
    concrete.setPayment(this.getPayment());
  }

  private void buildAttributeMap(SurveyedPerson concrete)
  {
    new AttributeNotificationMap(concrete, SurveyedPerson.ID, this, SurveyedPersonView.CONCRETEID);
    new AttributeNotificationMap(concrete, SurveyedPerson.HOUSEHOLD, this, SurveyedPersonView.HOUSEHOLD);
    new AttributeNotificationMap(concrete, SurveyedPerson.PERSONID, this, SurveyedPersonView.PERSONID);
    new AttributeNotificationMap(concrete, SurveyedPerson.HEADOFHOUSEHOLD, this, SurveyedPersonView.HEADOFHOUSEHOLD);
    new AttributeNotificationMap(concrete, SurveyedPerson.DOB, this, SurveyedPersonView.DOB);
    new AttributeNotificationMap(concrete, SurveyedPerson.DOB, this, SurveyedPersonView.AGE);
    new AttributeNotificationMap(concrete, SurveyedPerson.SEX, this, SurveyedPersonView.SEX);
    new AttributeNotificationMap(concrete, SurveyedPerson.PREGNANT, this, SurveyedPersonView.PREGNANT);
    new AttributeNotificationMap(concrete, SurveyedPerson.IMMUNECOMPROMISED, this, SurveyedPersonView.IMMUNECOMPROMISED);
    new AttributeNotificationMap(concrete, SurveyedPerson.SLEPTUNDERNET, this, SurveyedPersonView.SLEPTUNDERNET);
    new AttributeNotificationMap(concrete, SurveyedPerson.HAEMOGLOBINMEASURED, this, SurveyedPersonView.HAEMOGLOBINMEASURED);
    new AttributeNotificationMap(concrete, SurveyedPerson.HAEMOGLOBIN, this, SurveyedPersonView.HAEMOGLOBIN);
    new AttributeNotificationMap(concrete, SurveyedPerson.ANAEMIATREATMENT, this, SurveyedPersonView.ANAEMIATREATMENT);
    new AttributeNotificationMap(concrete, SurveyedPerson.IRON, this, SurveyedPersonView.IRON);
    new AttributeNotificationMap(concrete, SurveyedPerson.PERFORMEDRDT, this, SurveyedPersonView.PERFORMEDRDT);
    new AttributeNotificationMap(concrete, SurveyedPerson.RDTRESULT, this, SurveyedPersonView.RDTRESULT);
    new AttributeNotificationMap(concrete, SurveyedPerson.RDTDETAIL, this, SurveyedPersonView.RDTDETAIL);
    new AttributeNotificationMap(concrete, SurveyedPerson.RDTTREATMENT, this, SurveyedPersonView.RDTTREATMENT);
    new AttributeNotificationMap(concrete, SurveyedPerson.PERFORMEDBLOODSLIDE, this, SurveyedPersonView.PERFORMEDBLOODSLIDE);
    new AttributeNotificationMap(concrete, SurveyedPerson.BLOODSLIDEREASON, this, SurveyedPersonView.BLOODSLIDEREASON);
    new AttributeNotificationMap(concrete, SurveyedPerson.BLOODSLIDERESULT, this, SurveyedPersonView.BLOODSLIDERESULT);
    new AttributeNotificationMap(concrete, SurveyedPerson.BLOODSLIDEDETAIL, this, SurveyedPersonView.BLOODSLIDEDETAIL);
    new AttributeNotificationMap(concrete, SurveyedPerson.FEVER, this, SurveyedPersonView.FEVER);
    new AttributeNotificationMap(concrete, SurveyedPerson.MALARIA, this, SurveyedPersonView.MALARIA);
    new AttributeNotificationMap(concrete, SurveyedPerson.MALARIACONFORMATIONTECHNIQUE, this, SurveyedPersonView.MALARIACONFORMATIONTECHNIQUE);
    new AttributeNotificationMap(concrete, SurveyedPerson.PAYMENT, this, SurveyedPersonView.PAYMENT);
  }

  @Override
  @Transaction
  public void apply()
  {
    SurveyedPerson concrete = new SurveyedPerson();

    if (this.hasConcrete())
    {
      concrete = SurveyedPerson.get(this.getConcreteId());
    }

    // We need to update the number of Nets if this person used to sleep under a
    // net but no longer does.
    ITNInstance newNet = this.getSleptUnderNet();
    ITNInstance existingNet = concrete.getSleptUnderNet();

    // Build the attribute map between SurveyedPerson and
    // SurveyedPersonView for error handling
    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.updateNet(newNet);
    this.updateNet(existingNet);

    this.populateView(concrete);
  }

  private void updateNet(ITNInstance net)
  {
    if (net != null)
    {
      net.lock();

      long count = SurveyedPerson.getCount(net);

      net.setSleptUnderNet(count);
      net.apply();
    }
  }

  @Override
  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      SurveyedPerson.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Override
  @Transaction
  public void applyAll(Term[] locations, Term[] treatments)
  {
    validateDisplayLocations(locations);
    validateDisplayTreatments(treatments);

    this.apply();

    SurveyedPerson person = SurveyedPerson.get(this.getConcreteId());

    List<Term> locationList = Arrays.asList(locations);
    this.clearLocations(locationList, person);
    this.setLocations(person, locationList);

    List<Term> treatmentList = Arrays.asList(treatments);
    this.clearTreatments(treatmentList, person);
    this.setTreatments(person, treatmentList);
  }

  private void validateDisplayLocations(Term[] locations)
  {
    if (locations != null && locations.length > 0)
    {
      List<Response> _malaria = this.getMalaria();

      if (_malaria.size() > 0 && !_malaria.contains(Response.YES))
      {
        String msg = "Cannot have a treatment locations when malaria was not present";
        String value = ResponseMaster.getValueForErrorMsg(_malaria);

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, DISPLAYLOCATIONS);
        p.setInputAttribute(getMalariaMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }

  private void validateDisplayTreatments(Term[] treatments)
  {
    if (treatments != null && treatments.length > 0)
    {
      List<Response> _malaria = this.getMalaria();

      if (_malaria.size() > 0 && !_malaria.contains(Response.YES))
      {
        String msg = "Cannot have a treatment when malaria was not present";
        String value = ResponseMaster.getValueForErrorMsg(_malaria);

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, DISPLAYTREATMENTS);
        p.setInputAttribute(getMalariaMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public Term[] getLocations()
  {
    if (this.hasConcrete())
    {
      SurveyedPerson person = SurveyedPerson.get(this.getConcreteId());

      List<? extends Term> results = person.getAllLocations().getAll();

      return results.toArray(new Term[results.size()]);
    }

    return new Term[0];
  }

  @Override
  public Term[] getTreatments()
  {
    if (this.hasConcrete())
    {
      SurveyedPerson person = SurveyedPerson.get(this.getConcreteId());

      List<? extends Term> results = person.getAllTreatments().getAll();

      return results.toArray(new Term[results.size()]);
    }

    return new Term[0];
  }

  private void setLocations(SurveyedPerson person, List<Term> resultList)
  {
    Set<Term> set = new TreeSet<Term>(new TermComparator());
    set.addAll(resultList);

    List<? extends Term> existing = person.getAllLocations().getAll();

    // Get all of the new results which this Person does not already have
    set.removeAll(existing);

    for (Term result : set)
    {
      SurveyedPersonTreatmentLocation relationship = person.addLocations(result);

      relationship.apply();
    }
  }

  private void clearLocations(List<Term> resultList, SurveyedPerson person)
  {
    // First delete all of the exiting relationships where the Term is not in
    // the result list
    List<? extends SurveyedPersonTreatmentLocation> relationships = person.getAllLocationsRel().getAll();

    for (SurveyedPersonTreatmentLocation relationship : relationships)
    {
      if (!resultList.contains(relationship.getChild()))
      {
        relationship.delete();
      }
    }
  }

  private void setTreatments(SurveyedPerson person, List<Term> resultList)
  {
    Set<Term> set = new TreeSet<Term>(new TermComparator());
    set.addAll(resultList);

    List<? extends Term> existing = person.getAllTreatments().getAll();

    // Get all of the new results which this Person does not already have
    set.removeAll(existing);

    for (Term result : set)
    {
      SurveyedPersonTreatment relationship = person.addTreatments(result);

      relationship.apply();
    }
  }

  private void clearTreatments(List<Term> resultList, SurveyedPerson person)
  {
    // First delete all of the exiting relationships where the Term is not in
    // the result list
    List<? extends SurveyedPersonTreatment> relationships = person.getAllTreatmentsRel().getAll();

    for (SurveyedPersonTreatment relationship : relationships)
    {
      if (!resultList.contains(relationship.getChild()))
      {
        relationship.delete();
      }
    }
  }

}
