/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.RequiredAttributeProblem;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.intervention.monitor.Household;
import dss.vector.solutions.intervention.monitor.HouseholdQuery;
import dss.vector.solutions.intervention.monitor.HouseholdView;
import dss.vector.solutions.intervention.monitor.ITNInstance;
import dss.vector.solutions.intervention.monitor.ITNInstanceQuery;
import dss.vector.solutions.intervention.monitor.ITNInstanceView;
import dss.vector.solutions.intervention.monitor.SurveyPoint;
import dss.vector.solutions.intervention.monitor.SurveyedPerson;
import dss.vector.solutions.intervention.monitor.SurveyedPersonQuery;
import dss.vector.solutions.intervention.monitor.SurveyedPersonView;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class SurveyExcelView extends SurveyExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245785160306L;

  private List<Term>        locations;

  private List<Term>        treatments;

  public SurveyExcelView()
  {
    super();
    locations = new LinkedList<Term>();
    treatments = new LinkedList<Term>();
  }

  @Override
  @Transaction
  public void apply()
  {
    Locale currentLocale = Session.getCurrentLocale();
    boolean fail = false;
    if (this.getGeoEntity() == null)
    {
      RequiredAttributeProblem geoRequired = new RequiredAttributeProblem();
      geoRequired.setAttributeName(GEOENTITY);
      geoRequired.setAttributeDisplayLabel(getGeoEntityMd().getDisplayLabel(currentLocale));
      geoRequired.throwIt();
      fail = true;
    }

    if (this.getSurveyDate() == null)
    {
      RequiredAttributeProblem dateRequired = new RequiredAttributeProblem();
      dateRequired.setAttributeName(SURVEYDATE);
      dateRequired.setAttributeDisplayLabel(getSurveyDateMd().getDisplayLabel(currentLocale));
      dateRequired.throwIt();
      fail = true;
    }

    // If we're missing critical information, there's no point in proceeding
    if (fail)
    {
      return;
    }

    HouseholdView house = getHousehold();

    applyItnInstance(house);

    // SurveyedPerson stuff
    applyPerson(house);
  }

  private void applyItnInstance(HouseholdView house)
  {
    // Check to ensure that a net ID is specified before proceeding
    String nid = this.getNetId();
    if (nid.length() == 0)
    {
      return;
    }

    ITNInstanceView itn;

    // Search first
    ITNInstance netById = getNetById(nid);
    if (netById != null)
    {
      // Update if found
      itn = netById.lockView();
    }
    else
    {
      // Create if not
      itn = new ITNInstanceView();
      itn.setNetId(nid);
      itn.setHousehold(Household.get(house.getConcreteId()));
    }

    itn.setNetBrand(Term.validateByDisplayLabel(this.getNetBrand(), ITNInstanceView.getNetBrandMd()));
    itn.addMonthReceived(ExcelEnums.getMonthOfYear(this.getMonthReceived()));
    itn.setYearReceived(this.getYearReceived());
    itn.setObtained(Term.validateByDisplayLabel(this.getObtained(), ITNInstanceView.getObtainedMd()));
    itn.setPrice(this.getPrice());
    itn.setRetreated(this.getRetreated());
    itn.addMonthRetreated(ExcelEnums.getMonthOfYear(this.getMonthRetreated()));
    itn.setYearRetreated(this.getYearRetreated());
    itn.setDamaged(Term.validateByDisplayLabel(this.getDamaged(), ITNInstanceView.getDamagedMd()));
    itn.setHanging(Term.validateByDisplayLabel(this.getHanging(), ITNInstanceView.getHangingMd()));
    itn.setNotUsedForSleeping(this.getNotUsedForSleeping());
    itn.setPurpose(Term.validateByDisplayLabel(this.getPurpose(), ITNInstanceView.getPurposeMd()));
    itn.setPurposeComments(this.getPurposeComments());
    itn.addWashed(ExcelEnums.getResponse(this.getWashed()));
    itn.setWashFrequency(this.getWashFrequency());
    itn.setWashPeriod(Term.validateByDisplayLabel(this.getWashPeriod(), ITNInstanceView.getWashPeriodMd()));
    itn.setSleptUnderNet(this.getSleptUnderNet());
    itn.apply();
  }

  private ITNInstance getNetById(String netId)
  {
    ITNInstanceQuery query = new ITNInstanceQuery(new QueryFactory());
    query.WHERE(query.getNetId().EQ(netId));
    OIterator<? extends ITNInstance> iterator = query.getIterator();

    ITNInstance instance = null;
    if (iterator.hasNext())
    {
      instance = iterator.next();
      iterator.close();
    }
    return instance;
  }

  private void applyPerson(HouseholdView house)
  {
    // Check to ensure that a person ID was specified before proceeding
    String pid = this.getPersonId();
    if (pid.length() == 0)
    {
      return;
    }

    SurveyedPersonView person;

    // Search first
    SurveyedPersonQuery query = new SurveyedPersonQuery(new QueryFactory());
    query.WHERE(query.getPersonId().EQ(pid));
    OIterator<? extends SurveyedPerson> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      // Update if found
      SurveyedPerson next = iterator.next();
      person = next.lockView();
    }
    else
    {
      // Create if not
      person = new SurveyedPersonView();
      person.setPersonId(pid);
      person.setHousehold(Household.get(house.getConcreteId()));
    }
    // close the iterator no matter what
    iterator.close();

    person.setHeadOfHousehold(Term.validateByDisplayLabel(this.getHeadOfHousehold(), SurveyedPersonView.getHeadOfHouseholdMd()));
    person.setDob(this.getDob());
    person.setAge(this.getAge());
    person.setSex(Term.validateByDisplayLabel(this.getSex(), SurveyedPersonView.getSexMd()));
    person.setPregnant(this.getPregnant());
    person.setImmuneCompromised(Term.validateByDisplayLabel(this.getImmuneCompromised(), SurveyedPersonView.getImmuneCompromisedMd()));
    person.setSleptUnderNet(getNetById(this.getSleptUnderNetId()));
    person.addHaemoglobinMeasured(ExcelEnums.getRefusedResponse(this.getHaemoglobinMeasured()));
    person.setHaemoglobin(this.getHaemoglobin());
    person.setAnaemiaTreatment(Term.validateByDisplayLabel(this.getAnaemiaTreatment(), SurveyedPersonView.getAnaemiaTreatmentMd()));
    person.setIron(this.getIron());
    person.addPerformedRDT(ExcelEnums.getRefusedResponse(this.getPerformedRDT()));
    person.setRdtResult(this.getRdtResult());
    person.setRdtDetail(Term.validateByDisplayLabel(this.getRdtDetail(), SurveyedPersonView.getRdtDetailMd()));
    person.setRdtTreatment(Term.validateByDisplayLabel(this.getRdtTreatment(), SurveyedPersonView.getRdtTreatmentMd()));
    person.setPerformedBloodslide(this.getPerformedBloodslide());
    person.setBloodslideReason(Term.validateByDisplayLabel(this.getBloodslideReason(), SurveyedPersonView.getBloodslideReasonMd()));
    person.setBloodslideResult(this.getBloodslideResult());
    person.setBloodslideDetail(Term.validateByDisplayLabel(this.getBloodslideDetail(), SurveyedPersonView.getBloodslideDetailMd()));
    person.addFever(ExcelEnums.getResponse(this.getFever()));
    person.addMalaria(ExcelEnums.getResponse(this.getMalaria()));
    person.setMalariaConformationTechnique(Term.validateByDisplayLabel(this.getMalariaConformationTechnique(), SurveyedPersonView.getMalariaConformationTechniqueMd()));
    person.setPayment(Term.validateByDisplayLabel(this.getPayment(), SurveyedPersonView.getPaymentMd()));

    person.applyAll(locations.toArray(new Term[locations.size()]), treatments.toArray(new Term[treatments.size()]));
  }

  public SurveyPoint getSurveyPoint()
  {
    SurveyPoint surveyPoint = SurveyPoint.searchByGeoEntityAndDate(this.getGeoEntity(), this.getSurveyDate());

    if (surveyPoint == null)
    {
      // The survey point is null so we need to create it
      surveyPoint = new SurveyPoint();
      surveyPoint.setGeoEntity(this.getGeoEntity());
      surveyPoint.setSurveyDate(this.getSurveyDate());
      surveyPoint.apply();
    }

    return surveyPoint;
  }

  /**
   * Gets the household by it's unique name. If the name is not found, a new one
   * is created (but not applied).
   * 
   * @return
   */
  private HouseholdView getHousehold()
  {
    HouseholdView house;
    SurveyPoint surveyPoint = this.getSurveyPoint();

    String name = this.getHouseholdName();
    HouseholdQuery query = new HouseholdQuery(new QueryFactory());
    query.WHERE(query.getHouseholdName().EQ(name));
    OIterator<? extends Household> iterator = query.getIterator();

    if (iterator.hasNext())
    {
      Household concrete = iterator.next();
      house = concrete.lockView();

      if (!house.getSurveyPoint().equals(surveyPoint))
      {
        // Problem!
        HouseholdAlreadyAssignedException e = new HouseholdAlreadyAssignedException();
        e.setHousehold(name);
        throw e;
      }
    }
    else
    {
      house = new HouseholdView();
      house.setHouseholdName(name);
      house.setSurveyPoint(surveyPoint);
    }
    iterator.close();

    house.setUrban(this.getUrban());
    house.setPeople(this.getPeople());
    house.setWall(Term.validateByDisplayLabel(this.getWallSurface(), HouseholdView.getWallMd()));
    house.setWallInfo(this.getWallInfo());
    house.setRoof(Term.validateByDisplayLabel(this.getRoofSurface(), HouseholdView.getRoofMd()));
    house.setRoofInfo(this.getRoofInfo());
    house.setHasWindows(this.getHasWindows());
    house.setWindowType(Term.validateByDisplayLabel(this.getWindowType(), HouseholdView.getWindowTypeMd()));
    house.setRooms(this.getRooms());
    house.addHasBeenSprayed(ExcelEnums.getResponse(this.getHasBeenSprayed()));
    house.setLastSprayed(this.getLastSprayed());
    house.setNets(this.getNets());
    house.apply();

    return house;
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(SURVEYDATE);
    list.add(HOUSEHOLDNAME);
    list.add(URBAN);
    list.add(PEOPLE);
    list.add(WALLSURFACE);
    list.add(WALLINFO);
    list.add(ROOFSURFACE);
    list.add(ROOFINFO);
    list.add(HASWINDOWS);
    list.add(WINDOWTYPE);
    list.add(ROOMS);
    list.add(HASBEENSPRAYED);
    list.add(LASTSPRAYED);
    list.add(NETS);
    list.add(PERSONID);
    list.add(HEADOFHOUSEHOLD);
    list.add(DOB);
    list.add(AGE);
    list.add(SEX);
    list.add(PREGNANT);
    list.add(IMMUNECOMPROMISED);
    list.add(SLEPTUNDERNETID);
    list.add(HAEMOGLOBINMEASURED);
    list.add(HAEMOGLOBIN);
    list.add(ANAEMIATREATMENT);
    list.add(IRON);
    list.add(PERFORMEDRDT);
    list.add(RDTRESULT);
    list.add(RDTDETAIL);
    list.add(RDTTREATMENT);
    list.add(PERFORMEDBLOODSLIDE);
    list.add(BLOODSLIDEREASON);
    list.add(BLOODSLIDERESULT);
    list.add(BLOODSLIDEDETAIL);
    list.add(FEVER);
    list.add(MALARIA);
    list.add(MALARIACONFORMATIONTECHNIQUE);
    list.add(PAYMENT);
    list.add(NETID);
    list.add(NETBRAND);
    list.add(MONTHRECEIVED);
    list.add(YEARRECEIVED);
    list.add(OBTAINED);
    list.add(PRICE);
    list.add(RETREATED);
    list.add(MONTHRETREATED);
    list.add(YEARRETREATED);
    list.add(DAMAGED);
    list.add(HANGING);
    list.add(NOTUSEDFORSLEEPING);
    list.add(PURPOSE);
    list.add(PURPOSECOMMENTS);
    list.add(WASHED);
    list.add(WASHFREQUENCY);
    list.add(WASHPERIOD);
    list.add(SLEPTUNDERNET);
    return list;
  }

  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    context.addListener(new SurveyExcelListener());
    context.addListener(createExcelGeoListener(importer));
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener(null));
    exporter.addListener(new SurveyExcelListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener(ExcelImportManager importer)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(SentinelSite.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder, importer);
  }

  public void addLocation(Term location)
  {
    locations.add(location);
  }

  public void addTreatment(Term treatment)
  {
    treatments.add(treatment);
  }
}
