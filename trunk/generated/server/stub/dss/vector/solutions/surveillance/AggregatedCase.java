package dss.vector.solutions.surveillance;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.business.rbac.Authenticate;
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
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryCSVExporter;
import com.terraframe.mojo.query.ValueQueryExcelExporter;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.session.SessionFacade;
import com.terraframe.mojo.session.SessionIF;
import com.terraframe.mojo.session.StartSession;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.query.MapUtil;
import dss.vector.solutions.query.NoThematicLayerException;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchRequiredException;
import dss.vector.solutions.query.ThematicLayer;
import dss.vector.solutions.util.QueryConfig;
import dss.vector.solutions.util.QueryUtil;

public class AggregatedCase extends AggregatedCaseBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693161773L;

  public AggregatedCase()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    if(this.getGeoEntity() != null && this.getStartDate() != null && this.getEndDate() != null && this.getStartAge() != null && this.getEndAge() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      
      String period = format.format(this.getStartDate()) + "-" + format.format(this.getEndDate());
      String ageRange = this.getStartAge() + "-" + this.getEndAge();

      return this.getGeoEntity().getGeoId() + "." + period + "." + ageRange;
    }
    return this.getId();
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
   * Precondition:</br> <code>Session.getCurrentSession()</code> does not return
   * null.
   *
   * @param attributeName
   * @param viewCaseAttributeMap
   * @return true if the given attribute is defined by a
   *         <code>MdAttributeDAOIF</code> in the given map and the current user
   *         has permission to view the attribute, false otherwise.
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
    EpiDate epiDate = EpiDate.getInstanceByDate(this.getStartDate(), this.getEndDate());

    view.setGeoEntity(this.getGeoEntity());
    view.setPeriod(epiDate.getPeriod());
    view.addPeriodType(epiDate.getEpiPeriodType());
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
    EpiDate.validate(periodType, period, year);

    EpiDate date = EpiDate.getInstanceByPeriod(periodType, period, year);
    AggregatedCase c = AggregatedCase.searchByGeoEntityAndDate(geoEntity, date.getStartDate(), date
        .getEndDate(), ageGroup);

    if (c != null)
    {
      AggregatedCaseView view = c.getView();
      view.setAgeGroup(ageGroup);
      view.setCaseId(c.getId());

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

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   *
   * @param xml
   * @return
   */
  @Authenticate
  public static ValueQuery xmlToValueQuery(String xml, String[] selectedUniversals,
      boolean includeGeometry, ThematicLayer thematicLayer)
  {
    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory,
        valueQuery, xml, thematicLayer, includeGeometry, selectedUniversals, AggregatedCase.CLASS, AggregatedCase.GEOENTITY);

    MdRelationshipDAOIF caseTreatmentStockRel = MdRelationshipDAO
        .getMdRelationshipDAO(CaseTreatmentStock.CLASS);

    AggregatedCaseQuery aggregatedCaseQuery = (AggregatedCaseQuery) queryMap.get(AggregatedCase.CLASS);

    for (String gridAlias : queryMap.keySet())
    {
      GeneratedEntityQuery generatedQuery = queryMap.get(gridAlias);
      
      if(true) continue;
      /*
      
      if (generatedQuery instanceof TreatmentGridQuery)
      {
        TermQuery treatmentGridQuery = (TermQuery) generatedQuery;
        // Alias startse with CaseTreatmentStock_
        if (gridAlias.startsWith(caseTreatmentStockRel.getTypeName() + "_"))
        {
          String caseTreatmentStockAlias = getRelationshipAlias(gridAlias);
          CaseTreatmentStockQuery ctsq = (CaseTreatmentStockQuery) queryMap.get(caseTreatmentStockAlias);
          valueQuery.AND(aggregatedCaseQuery.treatmentStock(ctsq));
          valueQuery.AND(ctsq.hasChild(treatmentGridQuery));
        }
        else
        {
          String caseTreatmentAlias = getRelationshipAlias(gridAlias);
          CaseTreatmentQuery ctq = (CaseTreatmentQuery) queryMap.get(caseTreatmentAlias);
          valueQuery.AND(aggregatedCaseQuery.treatment(ctq));
          valueQuery.AND(ctq.hasChild(treatmentGridQuery));
        }
      }
      else if (generatedQuery instanceof ReferralGridQuery)
      {
        ReferralGridQuery referralGridQuery = (ReferralGridQuery) generatedQuery;
        String caseReferralAlias = getRelationshipAlias(gridAlias);
        CaseReferralQuery crq = (CaseReferralQuery) queryMap.get(caseReferralAlias);
        valueQuery.AND(aggregatedCaseQuery.referral(crq));
        valueQuery.AND(crq.hasChild(referralGridQuery));
      }
      else if (generatedQuery instanceof DiagnosticGridQuery)
      {
        DiagnosticGridQuery diagnosticGridQuery = (DiagnosticGridQuery) generatedQuery;
        String caseDiagnosticAlias = getRelationshipAlias(gridAlias);
        CaseDiagnosticQuery cdq = (CaseDiagnosticQuery) queryMap.get(caseDiagnosticAlias);
        valueQuery.AND(aggregatedCaseQuery.diagnosticMethod(cdq));
        valueQuery.AND(cdq.hasChild(diagnosticGridQuery));
      }
      else if (generatedQuery instanceof TreatmentMethodGridQuery)
      {
        TreatmentMethodGridQuery treatmentMethodGridQuery = (TreatmentMethodGridQuery) generatedQuery;
        String caseTreatmentMethodAlias = getRelationshipAlias(gridAlias);
        CaseTreatmentMethodQuery ctmq = (CaseTreatmentMethodQuery) queryMap
            .get(caseTreatmentMethodAlias);
        valueQuery.AND(aggregatedCaseQuery.treatmentMethod(ctmq));
        valueQuery.AND(ctmq.hasChild(treatmentMethodGridQuery));
      }
      */
    }

    String sd = aggregatedCaseQuery.getStartDate().getQualifiedName();
    String ed = aggregatedCaseQuery.getEndDate().getQualifiedName();

    return QueryUtil.setQueryDates(xml, valueQuery, sd, ed);
  }



  /**
   * Returns the alias for the relationship query that maps to the grid query
   * with the given alias.
   */
  private static String getRelationshipAlias(String gridAlias)
  {
    // int firstIndex = gridAlias.indexOf("_", 0);
    int index = gridAlias.lastIndexOf("_");

    return gridAlias.substring(0, index);
  }

  /**
   * Queries for AggregatedCases.
   *
   * @param xml
   */
  @Transaction
  @Authenticate
  public static com.terraframe.mojo.query.ValueQuery queryAggregatedCase(String xml, String config,
      Integer pageNumber, Integer pageSize)
  {
    QueryConfig queryConfig = new QueryConfig(config);
    String[] selectedUniversals = queryConfig.getSelectedUniversals();

    ValueQuery valueQuery = xmlToValueQuery(xml, selectedUniversals, false, null);

    valueQuery.restrictRows(pageSize, pageNumber);

    return valueQuery;
  }

  /**
   * Creates a
   *
   * @param xml
   * @return
   */
  @Transaction
  public static String mapQuery(String xml, String config, String[] universalLayers, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot map a query without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);
    QueryConfig queryConfig = new QueryConfig(config);

    ThematicLayer thematicLayer = search.getThematicLayer();

    if (thematicLayer == null || thematicLayer.getGeoHierarchy() == null)
    {
      String error = "Cannot create a map for search [" + search.getQueryName()
          + "] without having selected a thematic layer.";
      NoThematicLayerException ex = new NoThematicLayerException(error);
      throw ex;
    }

    // Update ThematicLayer if the thematic layer type has changed or
    // if one has not yet been defined.
    String thematicLayerType = thematicLayer.getGeoHierarchy().getGeoEntityClass().definesType();
    if (thematicLayer.getGeometryStyle() == null
        || !thematicLayer.getGeoHierarchy().getQualifiedType().equals(thematicLayerType))
    {
      thematicLayer.changeLayerType(thematicLayerType);
    }

    String[] selectedUniversals = queryConfig.getSelectedUniversals();
    ValueQuery query = xmlToValueQuery(xml, selectedUniversals, true, thematicLayer);

    String layers = MapUtil.generateLayers(universalLayers, query, search, thematicLayer);

    return layers;
  }

  @Transaction
  public static InputStream exportQueryToExcel(String queryXML, String config, String savedSearchId)
  {
    QueryConfig queryConfig = new QueryConfig(config);
    String[] selectedUniversals = queryConfig.getSelectedUniversals();

    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to Excel without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);

    ValueQuery query = xmlToValueQuery(queryXML, selectedUniversals, false, null);

    ValueQueryExcelExporter exporter = new ValueQueryExcelExporter(query, search.getQueryName());
    return exporter.exportStream();
  }

  @Transaction
  public static InputStream exportQueryToCSV(String queryXML, String config, String savedSearchId)
  {
    QueryConfig queryConfig = new QueryConfig(config);
    String[] selectedUniversals = queryConfig.getSelectedUniversals();

    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to CSV without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    ValueQuery query = xmlToValueQuery(queryXML, selectedUniversals, false, null);

    ValueQueryCSVExporter exporter = new ValueQueryCSVExporter(query);
    return exporter.exportStream();
  }

  /**
   * Returns all AbstractGrid subclass instances relative to Aggregated Cases.
   *
   * @return
   */
  public static AbstractGridQuery getGridInstances()
  {
    QueryFactory f = new QueryFactory();
    AbstractGridQuery q = new AbstractGridQuery(f);

    String[] types = new String[] { CaseTreatmentStock.CLASS, TreatmentGrid.CLASS,
        TreatmentMethodGrid.CLASS, DiagnosticGrid.CLASS, ReferralGrid.CLASS };

    q.WHERE(q.getType().IN(types));

    return q;
  }
}
