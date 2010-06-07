package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.UnknownAgeGroupException;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.AggregatedAgeGroup;
import dss.vector.solutions.surveillance.AggregatedAgeGroupQuery;
import dss.vector.solutions.surveillance.AggregatedCase;
import dss.vector.solutions.surveillance.AggregatedCaseView;
import dss.vector.solutions.surveillance.PeriodType;
import dss.vector.solutions.util.HierarchyBuilder;

public class AggregatedCaseExcelView extends AggregatedCaseExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1246505362370L;

  private List<Term>        stock;

  private List<Boolean>     stockValues;

  private List<Term>        treatments;

  private List<Integer>     treatmentAmounts;

  private List<Term>        methods;

  private List<Integer>     methodAmounts;

  private List<Term>        diagnostics;

  private List<Integer>     diagnosticAmounts;

  private List<Integer>     diagnosticPositives;

  private List<Term>        referrals;

  private List<Integer>     referralAmounts;

  public AggregatedCaseExcelView()
  {
    super();
    stock = new LinkedList<Term>();
    stockValues = new LinkedList<Boolean>();
    treatments = new LinkedList<Term>();
    treatmentAmounts = new LinkedList<Integer>();
    methods = new LinkedList<Term>();
    methodAmounts = new LinkedList<Integer>();
    diagnostics = new LinkedList<Term>();
    diagnosticAmounts = new LinkedList<Integer>();
    diagnosticPositives = new LinkedList<Integer>();
    referrals = new LinkedList<Term>();
    referralAmounts = new LinkedList<Integer>();
  }

  @Override
  @Transaction
  public void apply()
  {
//    GeoEntity geoEntity = getGeoEntity();
//
//    PeriodType periodType = getPeriodTypeByLabel(this.getPeriodType());
//
//    AggregatedAgeGroupQuery query = new AggregatedAgeGroupQuery(new QueryFactory());
//    query.WHERE(query.getDisplayLabel().EQ(this.getAggregatedAgeGroup()));
//    OIterator<? extends AggregatedAgeGroup> iterator = query.getIterator();
//    
//    if (!iterator.hasNext()) {
//    	throw new UnknownAgeGroupException();
//    }
//    
//    AggregatedAgeGroup ageGroup = iterator.next();
//    AggregatedCaseView acv = AggregatedCase.searchByGeoEntityAndEpiDate(geoEntity, periodType, this.getPeriod(), this.getEpiYear(), ageGroup);
//
//    acv.apply();
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(PERIODTYPE);
    list.add(PERIOD);
    list.add(EPIYEAR);
    list.add(AGGREGATEDAGEGROUP);
    list.add(CASES);
    list.add(CASESFEMALE);
    list.add(CASESMALE);
    list.add(CASESPREGNANT);
    list.add(CLINICALLYDIAGNOSED);
    list.add(CLINICALLYDIAGNOSEDDEATH);
    list.add(DAYSOUTOFSTOCK);
    list.add(DEATHS);
    list.add(DEATHSFEMALE);
    list.add(DEATHSMALE);
    list.add(DEATHSPREGNANT);
    list.add(DEFINITIVELYDIAGNOSED);
    list.add(DEFINITIVELYDIAGNOSEDDEATH);
    list.add(INPATIENTS);
    list.add(INPATIENTSANEMIA);
    list.add(INPATIENTSCLINICALLY);
    list.add(INPATIENTSDEFINITIVE);
    list.add(INPATIENTSDISCHARGED);
    list.add(INPATIENTSFEMALE);
    list.add(INPATIENTSMALE);
    list.add(INPATIENTSNOTTREATED);
    list.add(INPATIENTSPREGNANTANEMIA);
    list.add(INPATIENTSPREGNANTDIANOSIS);
    list.add(INPATIENTSTOTAL);
    list.add(OUTPATIENTS);
    list.add(OUTPATIENTSFEMALE);
    list.add(OUTPATIENTSMALE);
    list.add(OUTPATIENTSNOTTREATED);
    list.add(OUTPATIENTSTOTAL);
    list.add(PATIENTSNOTTREATED);
    list.add(PREGNANTDIAGNOSIS);
    list.add(PREGNANTDIAGNOSISDEATH);
    list.add(PREGNANTREFERRALSRECEIVED);
    list.add(REFERRALSRECEIVED);
    list.add(REFERRALSSENT);
    list.add(STILLBIRTHS);
    return list;
  }

  public static void setupImportListener(ImportContext context, String... params)
  {
    context.addListener(new AggregatedCaseListener());
    context.addListener(createExcelGeoListener());
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener());
    exporter.addListener(new AggregatedCaseListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllPoliticals())
    {
      builder.add(hierarchy);
    }
    builder.add(GeoHierarchy.getGeoHierarchyFromType(HealthFacility.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
  }

  public static PeriodType getPeriodTypeByLabel(String label)
  {
    if (label == null || label.equals(""))
    {
      return null;
    }

    for (PeriodType e : PeriodType.values())
    {
      if (e.getDisplayLabel().equalsIgnoreCase(label) ||
          e.getEnumName().equalsIgnoreCase(label))
      {
        return e;
      }
    }
    String message = "[" + label + "] is not a valid display label for [" + PeriodType.CLASS + "]";
    throw new DataNotFoundException(message, MdTypeDAO.getMdTypeDAO(PeriodType.CLASS));
  }

  public void addStock(Term grid, boolean inStock)
  {
    stock.add(grid);
    stockValues.add(inStock);
  }

  public void addTreatment(Term grid, int count)
  {
    treatments.add(grid);
    treatmentAmounts.add(count);
  }

  public void addMethod(Term grid, int count)
  {
    methods.add(grid);
    methodAmounts.add(count);
  }

  public void addDiagnostic(Term grid, int amount, int positive)
  {
    diagnostics.add(grid);
    diagnosticAmounts.add(amount);
    diagnosticPositives.add(positive);
  }

  public void addReferral(Term grid, int count)
  {
    referrals.add(grid);
    referralAmounts.add(count);
  }
}
