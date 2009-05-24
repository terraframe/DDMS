package dss.vector.solutions.surveillance;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.business.rbac.Operation;
import com.terraframe.mojo.dataaccess.MdAttributeConcreteDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.MdRelationshipDAOIF;
import com.terraframe.mojo.dataaccess.MdViewDAOIF;
import com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO;
import com.terraframe.mojo.dataaccess.metadata.MdRelationshipDAO;
import com.terraframe.mojo.dataaccess.metadata.MdViewDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedBusinessQuery;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryExcelExporter;
import com.terraframe.mojo.query.ValueQueryParser;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.session.SessionFacade;
import com.terraframe.mojo.session.SessionIF;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.PeriodMonthProblem;
import dss.vector.solutions.PeriodQuarterProblem;
import dss.vector.solutions.PeriodWeekProblem;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.query.MapUtil;
import dss.vector.solutions.query.MapWithoutGeoEntityException;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchRequiredException;
import dss.vector.solutions.query.ThematicLayer;
import dss.vector.solutions.query.ThematicVariable;

public class AggregatedCase extends AggregatedCaseBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693161773L;

  public AggregatedCase()
  {
    super();
  }

  @StartSession
  public static java.lang.String[] getVisibleAttributeNames()
  {
    MdBusinessDAOIF aggregateCaseMdBusiness = MdBusinessDAO.getMdBusinessDAO(AggregatedCase.CLASS);
    List<? extends MdAttributeConcreteDAOIF> aggregateCaseAttributes = aggregateCaseMdBusiness
        .getAllDefinedMdAttributes();

    MdViewDAOIF aggregatedCaseMdView = MdViewDAO.getMdViewDAO(AggregatedCaseView.CLASS);

    List<MdViewDAOIF> aggregatedCaseViewSubClasses = aggregatedCaseMdView.getSubClasses();

    // Key is the class name, value is a map of attributes
    Map<String, Map<String, ? extends MdAttributeDAOIF>> subClassAttrMap = new HashMap<String, Map<String, ? extends MdAttributeDAOIF>>();

    for (MdViewDAOIF mdViewDAOIF : aggregatedCaseViewSubClasses)
    {
      subClassAttrMap.put(mdViewDAOIF.definesType(), mdViewDAOIF.getAllDefinedMdAttributeMap());
    }

    LinkedList<String> visibleAttributeNameList = new LinkedList<String>();

    for (MdAttributeConcreteDAOIF mdAttribute : aggregateCaseAttributes)
    {
      for (MdViewDAOIF mdViewDAOIF : aggregatedCaseViewSubClasses)
      {
        boolean hasVisibility = hasVisibility(mdAttribute.definesAttribute(), subClassAttrMap
            .get(mdViewDAOIF.definesType()));
        if (hasVisibility)
        {
          visibleAttributeNameList.add(mdAttribute.definesAttribute());
          break;
        }
      }
    }

    String[] visibleAttributeNames = new String[visibleAttributeNameList.size()];

    visibleAttributeNameList.toArray(visibleAttributeNames);

    return visibleAttributeNames;
  }

  /**
   * Returns true if the given attribute is defined by a
   * <code>MdAttributeDAOIF</code> in the given map and the current user has
   * permission to view the attribute, false otherwise.
   *
   * <br>
   * Precondition:</br> <code>Session.getCurrentSession()</code> does not
   * return null.
   *
   * @param attributeName
   * @param viewCaseAttributeMap
   * @return true if the given attribute is defined by a
   *         <code>MdAttributeDAOIF</code> in the given map and the current
   *         user has permission to view the attribute, false otherwise.
   */
  private static boolean hasVisibility(String attributeName,
      Map<String, ? extends MdAttributeDAOIF> viewCaseAttributeMap)
  {
    String attrNameLowerCase = attributeName.toLowerCase();

    boolean hasVisibility = false;

    SessionIF session = Session.getCurrentSession();

    if (viewCaseAttributeMap.containsKey(attrNameLowerCase))
    {
      MdAttributeDAOIF mdAttributeDAOIF = viewCaseAttributeMap.get(attrNameLowerCase);
      hasVisibility = SessionFacade.checkAttributeAccess(session.getId(), Operation.READ,
          mdAttributeDAOIF);
    }
    return hasVisibility;
  }

  @Override
  public void validateStartDate()
  {
    if (this.getStartDate() != null)
    {
      super.validateStartDate();

      Date current = new Date();

      if (current.before(this.getStartDate()))
      {
        String msg = "It is impossible to have a start date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getStartDate());
        p.setCurrentDate(current);
        p.setNotification(this, STARTDATE);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateEndDate()
  {
    if (this.getEndDate() != null)
    {
      super.validateEndDate();

      Date current = new Date();

      if (current.before(this.getEndDate()))
      {
        String msg = "It is impossible to have a end date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getEndDate());
        p.setCurrentDate(current);
        p.setNotification(this, ENDDATE);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void apply()
  {
    validateStartDate();
    validateEndDate();

    this.setStartAge(this.getAgeGroup().getStartAge());
    this.setEndAge(this.getAgeGroup().getEndAge());

    super.apply();
  }

  @Override
  @Transaction
  public void lock()
  {
    super.lock();

    // Lock the grid relationship also, this must be in the same transaction
    for (CaseDiagnostic diagnostic : this.getAllDiagnosticMethodRel())
    {
      diagnostic.lock();
    }

    for (CaseReferral referral : this.getAllReferralRel())
    {
      referral.lock();
    }

    for (CaseTreatment treatment : this.getAllTreatmentRel())
    {
      treatment.lock();
    }

    for (CaseTreatmentMethod method : this.getAllTreatmentMethodRel())
    {
      method.lock();
    }

    for (CaseTreatmentStock stock : this.getAllTreatmentStockRel())
    {
      stock.lock();
    }
  }

  @Override
  @Transaction
  public void unlock()
  {
    // Unlock the grid relationship also, this must be in the same transaction
    for (CaseDiagnostic diagnostic : this.getAllDiagnosticMethodRel())
    {
      diagnostic.unlock();
    }

    for (CaseReferral referral : this.getAllReferralRel())
    {
      referral.unlock();
    }

    for (CaseTreatment treatment : this.getAllTreatmentRel())
    {
      treatment.unlock();
    }

    for (CaseTreatmentMethod method : this.getAllTreatmentMethodRel())
    {
      method.unlock();
    }

    for (CaseTreatmentStock stock : this.getAllTreatmentStockRel())
    {
      stock.unlock();
    }

    super.unlock();
  }

  @Override
  @Transaction
  public void applyAll(CaseTreatment[] treatments, CaseTreatmentMethod[] treatmentMethods,
      CaseTreatmentStock[] stock, CaseDiagnostic[] diagnosticMethods, CaseReferral[] referrals)
  {
    boolean newCase = this.isNew();

    this.apply();

    if (newCase)
    {
      for (int i = 0; i < diagnosticMethods.length; i++)
      {
        diagnosticMethods[i] = diagnosticMethods[i].clone(this);
      }

      for (int i = 0; i < referrals.length; i++)
      {
        referrals[i] = referrals[i].clone(this);
      }

      for (int i = 0; i < treatments.length; i++)
      {
        treatments[i] = treatments[i].clone(this);
      }

      for (int i = 0; i < treatmentMethods.length; i++)
      {
        treatmentMethods[i] = treatmentMethods[i].clone(this);
      }

      for (int i = 0; i < stock.length; i++)
      {
        stock[i] = stock[i].clone(this);
      }
    }

    for (CaseDiagnostic method : diagnosticMethods)
    {
      method.apply();
    }

    for (CaseReferral referral : referrals)
    {
      referral.apply();
    }

    for (CaseTreatment treatment : treatments)
    {
      treatment.apply();
    }

    for (CaseTreatmentMethod method : treatmentMethods)
    {
      method.apply();
    }

    for (CaseTreatmentStock s : stock)
    {
      s.apply();
    }

  }

  public static AggregatedCase searchByGeoEntityAndDate(GeoEntity geoEntity, Date startDate,
      Date endDate, AggregatedAgeGroup ageGroup)
  {
    AggregatedCaseQuery query = new AggregatedCaseQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().EQ(geoEntity));
    query.AND(query.getStartDate().EQ(startDate));
    query.AND(query.getEndDate().EQ(endDate));
    query.AND(query.getStartAge().EQ(ageGroup.getStartAge()));
    query.AND(query.getEndAge().EQ(ageGroup.getEndAge()));

    OIterator<? extends AggregatedCase> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }

  public AggregatedCaseView getView()
  {
    return this.updateView(this.getAgeGroup().getView());
  }

  public AggregatedCaseView updateView(AggregatedCaseView view)
  {
    EpiDate epiDate = new EpiDate(this.getStartDate(), this.getEndDate());

    view.setGeoEntity(this.getGeoEntity());
    view.setPeriod(epiDate.getPeriod());
    view.addPeriodType(epiDate.getType());
    view.setPeriodYear(epiDate.getYear());
    view.setAgeGroup(this.getAgeGroup());
    view.setCaseId(this.getId());
    view.setCases(this.getCases());
    view.setCasesMale(this.getCasesMale());
    view.setCasesFemale(this.getCasesFemale());
    view.setCasesPregnant(this.getCasesPregnant());
    view.setDeaths(this.getDeaths());
    view.setDeathsMale(this.getDeathsMale());
    view.setDeathsFemale(this.getDeathsFemale());
    view.setDeathsPregnant(this.getDeathsPregnant());
    view.setInPatients(this.getInPatients());
    view.setOutPatients(this.getOutPatients());
    view.setReferralsReceived(this.getReferralsReceived());
    view.setReferralsSent(this.getReferralsSent());
    view.setPregnantReferralsReceived(this.getPregnantReferralsReceived());
    view.setPregnantDiagnosis(this.getPregnantDiagnosis());
    view.setPregnantDiagnosisDeath(this.getPregnantDiagnosisDeath());
    view.setClinicallyDiagnosed(this.getClinicallyDiagnosed());
    view.setDefinitivelyDiagnosed(this.getDefinitivelyDiagnosed());
    view.setClinicallyDiagnosedDeath(this.getClinicallyDiagnosedDeath());
    view.setDefinitivelyDiagnosedDeath(this.getDefinitivelyDiagnosedDeath());
    view.setInPatientsTotal(this.getInPatientsTotal());
    view.setInPatientsAnemia(this.getInPatientsAnemia());
    view.setInPatientsPregnantAnemia(this.getInPatientsPregnantAnemia());
    view.setInPatientsPregnantDianosis(this.getInPatientsPregnantDianosis());
    view.setInPatientsFemale(this.getInPatientsFemale());
    view.setInPatientsMale(this.getInPatientsMale());
    view.setInPatientsDefinitive(this.getInPatientsDefinitive());
    view.setInPatientsClinically(this.getInPatientsClinically());
    view.setInPatientsDischarged(this.getInPatientsDischarged());
    view.setInPatientsNotTreated(this.getInPatientsNotTreated());
    view.setOutPatientsTotal(this.getOutPatientsTotal());
    view.setOutPatientsFemale(this.getOutPatientsFemale());
    view.setOutPatientsMale(this.getOutPatientsMale());
    view.setPatientsNotTreated(this.getPatientsNotTreated());
    view.setOutPatientsNotTreated(this.getOutPatientsNotTreated());
    view.setStillBirths(this.getStillBirths());
    view.setDaysOutOfStock(this.getDaysOutOfStock());

    return view;
  }

  @Transaction
  public static AggregatedCaseView searchByGeoEntityAndEpiDate(GeoEntity geoEntity,
      PeriodType periodType, Integer period, Integer year, AggregatedAgeGroup ageGroup)
  {
    validate(periodType, period, year);

    EpiDate date = new EpiDate(periodType, period, year);
    AggregatedCase c = AggregatedCase.searchByGeoEntityAndDate(geoEntity, date.getStartDate(), date
        .getEndDate(), ageGroup);

    if (c != null)
    {
      AggregatedCaseView view = c.getView();
      view.setAgeGroup(ageGroup);
      view.setCaseId(c.getId());
      view.applyNoPersist();

      return view;
    }

    AggregatedCaseView view = ageGroup.getView();
    view.setGeoEntity(geoEntity);
    view.setPeriod(period);
    view.addPeriodType(periodType);
    view.setPeriodYear(year);
    view.setAgeGroup(ageGroup);

    return view;
  }

  private static void validate(PeriodType periodType, Integer period, Integer year)
  {
    if (period > periodType.getMaximumPeriod() && periodType.equals(PeriodType.QUARTER))
    {
      PeriodQuarterProblem p = new PeriodQuarterProblem();
      p.setPeriod(period);
      p.setMaxPeriod(periodType.getMaximumPeriod());
      p.throwIt();
    }
    else if (period > periodType.getMaximumPeriod() && periodType.equals(PeriodType.MONTH))
    {
      PeriodMonthProblem p = new PeriodMonthProblem();
      p.setPeriod(period);
      p.setMaxPeriod(periodType.getMaximumPeriod());
      p.throwIt();
    }
    else if (period > periodType.getMaximumPeriod() && periodType.equals(PeriodType.WEEK))
    {
      PeriodWeekProblem p = new PeriodWeekProblem();
      p.setPeriod(period);
      p.setMaxPeriod(periodType.getMaximumPeriod());
      p.throwIt();
    }
  }

  public static AggregatedCaseView getView(String id)
  {
    return AggregatedCase.get(id).getView();
  }

  public static AggregatedCaseView lockView(String id)
  {
    return AggregatedCase.lock(id).getView();
  }

  public static AggregatedCaseView unlockView(String id)
  {
    return AggregatedCase.unlock(id).getView();
  }

  private <T> List<T> convertToList(OIterator<T> it)
  {
    List<T> list = new LinkedList<T>();

    for (T value : it)
    {
      list.add(value);
    }

    return list;
  }

  @Override
  public CaseDiagnostic[] getDiagnosticMethods()
  {
    List<? extends CaseDiagnostic> list = convertToList(this.getAllDiagnosticMethodRel());

    return list.toArray(new CaseDiagnostic[list.size()]);
  }

  @Override
  public CaseReferral[] getReferrals()
  {
    List<? extends CaseReferral> list = convertToList(this.getAllReferralRel());

    return list.toArray(new CaseReferral[list.size()]);
  }

  @Override
  public CaseTreatmentMethod[] getTreatmentMethods()
  {
    List<? extends CaseTreatmentMethod> list = convertToList(this.getAllTreatmentMethodRel());

    return list.toArray(new CaseTreatmentMethod[list.size()]);
  }

  @Override
  public CaseTreatment[] getTreatments()
  {
    List<? extends CaseTreatment> list = convertToList(this.getAllTreatmentRel());

    return list.toArray(new CaseTreatment[list.size()]);
  }

  @Override
  public CaseTreatmentStock[] getTreatmentStocks()
  {
    List<? extends CaseTreatmentStock> list = convertToList(this.getAllTreatmentStockRel());

    return list.toArray(new CaseTreatmentStock[list.size()]);
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   *
   * @param xml
   * @return
   */
  private static ValueQuery xmlToValueQuery(String xml, String geoEntityType, boolean includeGeometry,
      ThematicLayer thematicLayer)
  {
    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    ValueQueryParser valueQueryParser = new ValueQueryParser(xml, valueQuery);

    // include the thematic layer (if applicable).
    if (thematicLayer != null)
    {
      ThematicVariable thematicVariable = thematicLayer.getThematicVariable();
      if (thematicVariable != null)
      {
        String entityAlias = thematicVariable.getEntityAlias();
        String attributeName = thematicVariable.getAttributeName();

        valueQueryParser.setColumnAlias(entityAlias, attributeName,
            QueryConstants.THEMATIC_DATA_COLUMN);
      }
    }

    // include the geometry of the GeoEntity
    if (includeGeometry)
    {
      MdBusiness geoEntityMd = MdBusiness.getMdBusiness(geoEntityType);

      MdAttributeGeometry mdAttrGeo = GeoHierarchy.getGeometry(geoEntityMd);

      String attributeName = mdAttrGeo.getAttributeName();

      valueQueryParser.addAttributeSelectable(geoEntityType, attributeName, "", "");
      valueQueryParser.addAttributeSelectable(geoEntityType, GeoEntity.ENTITYNAME, "",
          QueryConstants.ENTITY_NAME_COLUMN);
    }

    Map<String, GeneratedEntityQuery> queryMap = valueQueryParser.parse();

    GeoHierarchy.addGeoHierarchyJoinConditions(valueQuery, queryMap);

    AggregatedCaseQuery aggregatedCaseQuery = (AggregatedCaseQuery) queryMap.get(AggregatedCase.CLASS);

    // join collection with geo entity and select that entity type's geometry
    if (geoEntityType != null && geoEntityType.trim().length() > 0)
    {
      GeneratedBusinessQuery businessQuery = (GeneratedBusinessQuery) queryMap.get(geoEntityType);

      valueQuery.WHERE(aggregatedCaseQuery.getGeoEntity().EQ(businessQuery));
    }

    MdRelationshipDAOIF caseTreatmentStockRel = MdRelationshipDAO.getMdRelationshipDAO(CaseTreatmentStock.CLASS);

    for (String gridAlias : queryMap.keySet())
    {
      GeneratedEntityQuery generatedQuery = queryMap.get(gridAlias);

      if (generatedQuery instanceof TreatmentGridQuery)
      {
        TreatmentGridQuery treatmentGridQuery = (TreatmentGridQuery)generatedQuery;
        //Alias startse with CaseTreatmentStock_
        if (gridAlias.startsWith(caseTreatmentStockRel.getTypeName()+"_"))
        {
          String caseTreatmentStockAlias = getRelationshipAlias(gridAlias);
          CaseTreatmentStockQuery ctsq = (CaseTreatmentStockQuery)queryMap.get(caseTreatmentStockAlias);
          valueQuery.AND(aggregatedCaseQuery.treatmentStock(ctsq));
          valueQuery.AND(ctsq.hasChild(treatmentGridQuery));
        }
        else
        {
          String caseTreatmentAlias = getRelationshipAlias(gridAlias);
          CaseTreatmentQuery ctq = (CaseTreatmentQuery)queryMap.get(caseTreatmentAlias);
          valueQuery.AND(aggregatedCaseQuery.treatment(ctq));
          valueQuery.AND(ctq.hasChild(treatmentGridQuery));
        }
      }
      else if (generatedQuery instanceof ReferralGridQuery)
      {
        ReferralGridQuery referralGridQuery = (ReferralGridQuery)generatedQuery;
        String caseReferralAlias = getRelationshipAlias(gridAlias);
        CaseReferralQuery crq = (CaseReferralQuery)queryMap.get(caseReferralAlias);
        valueQuery.AND(aggregatedCaseQuery.referral(crq));
        valueQuery.AND(crq.hasChild(referralGridQuery));
      }
      else if (generatedQuery instanceof DiagnosticGridQuery)
      {
        DiagnosticGridQuery diagnosticGridQuery = (DiagnosticGridQuery)generatedQuery;
        String caseDiagnosticAlias = getRelationshipAlias(gridAlias);
        CaseDiagnosticQuery cdq = (CaseDiagnosticQuery)queryMap.get(caseDiagnosticAlias);
        valueQuery.AND(aggregatedCaseQuery.diagnosticMethod(cdq));
        valueQuery.AND(cdq.hasChild(diagnosticGridQuery));
      }
      else if (generatedQuery instanceof TreatmentMethodGridQuery)
      {
        TreatmentMethodGridQuery treatmentMethodGridQuery = (TreatmentMethodGridQuery)generatedQuery;
        String caseTreatmentMethodAlias = getRelationshipAlias(gridAlias);
        CaseTreatmentMethodQuery ctmq = (CaseTreatmentMethodQuery)queryMap.get(caseTreatmentMethodAlias);
        valueQuery.AND(aggregatedCaseQuery.treatmentMethod(ctmq));
        valueQuery.AND(ctmq.hasChild(treatmentMethodGridQuery));
      }
    }

    return valueQuery;
  }

  /**
   * Returns the alias for the relationship query that maps to the grid query
   * with the given alias.
   */
  private static String getRelationshipAlias(String gridAlias)
  {
//    int firstIndex = gridAlias.indexOf("_", 0);
    int index = gridAlias.lastIndexOf("_");

    return gridAlias.substring(0, index);
  }

  /**
   * Queries for AggregatedCases.
   *
   * @param xml
   */
  @Transaction
  public static com.terraframe.mojo.query.ValueQuery queryAggregatedCase(String xml, String geoEntityType)
  {
    return xmlToValueQuery(xml, geoEntityType, false, null);
  }

  /**
   * Creates a
   *
   * @param xml
   * @return
   */
  @Transaction
  public static String mapQuery(String xml, String thematicLayerType, String[] universalLayers, String savedSearchId)
  {
    if(savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot map a query without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);

    if(thematicLayerType == null || thematicLayerType.trim().length() == 0)
    {
      String error = "Cannot create a map for search ["+search.getQueryName()+"] without having restricted by a GeoEntity(s).";
      MapWithoutGeoEntityException ex = new MapWithoutGeoEntityException(error);
      throw ex;
    }

    // Create the thematic layer if it does not exist
    ThematicLayer thematicLayer = search.getThematicLayer();

    // Update ThematicLayer if the thematic layer type has changed or
    // if one has not yet been defined.
    if(thematicLayer.getGeometryStyle() == null ||
        !thematicLayer.getGeoHierarchy().getQualifiedType().equals(thematicLayerType))
    {
      thematicLayer.changeLayerType(thematicLayerType);
    }


    ValueQuery query = xmlToValueQuery(xml, thematicLayerType, true, thematicLayer);

    String layers = MapUtil.generateLayers(universalLayers, query, search, thematicLayer);
    return layers;
  }

  @Transaction
  public static Byte[] exportQueryToExcel(String queryXML, String geoEntityType, String savedSearchId)
  {
    if(savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to Excel without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);

    ValueQuery query = xmlToValueQuery(queryXML, geoEntityType, false, null);

    ValueQueryExcelExporter exporter = new ValueQueryExcelExporter(query, search.getQueryName());
    return exporter.export();
  }
}
