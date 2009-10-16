package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.metadata.MdTypeDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.CollectionSite;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.AggregatedAgeGroup;
import dss.vector.solutions.surveillance.AggregatedAgeGroupQuery;
import dss.vector.solutions.surveillance.AggregatedCase;
import dss.vector.solutions.surveillance.AggregatedCaseView;
import dss.vector.solutions.surveillance.CaseDiagnostic;
import dss.vector.solutions.surveillance.CaseReferral;
import dss.vector.solutions.surveillance.CaseTreatment;
import dss.vector.solutions.surveillance.CaseTreatmentMethod;
import dss.vector.solutions.surveillance.CaseTreatmentStock;
import dss.vector.solutions.surveillance.PeriodType;
import dss.vector.solutions.util.HierarchyBuilder;

public class AggregatedCaseExcelView extends AggregatedCaseExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
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
    GeoEntity geoEntity = getGeoEntity();

    PeriodType periodType = getPeriodTypeByLabel(this.getPeriodType());

    AggregatedAgeGroupQuery query = new AggregatedAgeGroupQuery(new QueryFactory());
    query.WHERE(query.getDisplayLabel().EQ(this.getAggregatedAgeGroup()));
    OIterator<? extends AggregatedAgeGroup> iterator = query.getIterator();
    AggregatedAgeGroup ageGroup = iterator.next();

    AggregatedCaseView acv = AggregatedCase.searchByGeoEntityAndEpiDate(geoEntity, periodType, this.getPeriod(), this.getEpiYear(), ageGroup);
    acv.setCases(this.getCases());
    acv.setCasesFemale(this.getCasesFemale());
    acv.setCasesMale(this.getCasesMale());
    acv.setCasesPregnant(this.getCasesPregnant());
    acv.setDeaths(this.getDeaths());
    acv.setDeathsMale(this.getDeathsMale());
    acv.setDeathsFemale(this.getDeathsFemale());
    acv.setDeathsPregnant(this.getDeathsPregnant());
    acv.setInPatients(this.getInPatients());
    acv.setOutPatients(this.getOutPatients());
    acv.setReferralsReceived(this.getReferralsReceived());
    acv.setReferralsSent(this.getReferralsSent());
    acv.setPregnantReferralsReceived(this.getPregnantReferralsReceived());
    acv.setPregnantDiagnosis(this.getPregnantDiagnosis());
    acv.setPregnantDiagnosisDeath(this.getPregnantDiagnosisDeath());
    acv.setClinicallyDiagnosed(this.getClinicallyDiagnosed());
    acv.setDefinitivelyDiagnosed(this.getDefinitivelyDiagnosed());
    acv.setClinicallyDiagnosedDeath(this.getClinicallyDiagnosedDeath());
    acv.setDefinitivelyDiagnosedDeath(this.getDefinitivelyDiagnosedDeath());
    acv.setInPatientsTotal(this.getInPatientsTotal());
    acv.setInPatientsAnemia(this.getInPatientsAnemia());
    acv.setInPatientsPregnantAnemia(this.getInPatientsPregnantAnemia());
    acv.setInPatientsPregnantDianosis(this.getInPatientsPregnantDianosis());
    acv.setInPatientsFemale(this.getInPatientsFemale());
    acv.setInPatientsMale(this.getInPatientsMale());
    acv.setInPatientsDefinitive(this.getInPatientsDefinitive());
    acv.setInPatientsClinically(this.getInPatientsClinically());
    acv.setInPatientsDischarged(this.getInPatientsDischarged());
    acv.setInPatientsNotTreated(this.getInPatientsNotTreated());
    acv.setOutPatientsTotal(this.getOutPatientsTotal());
    acv.setOutPatientsFemale(this.getOutPatientsFemale());
    acv.setOutPatientsMale(this.getOutPatientsMale());
    acv.setPatientsNotTreated(this.getPatientsNotTreated());
    acv.setOutPatientsNotTreated(this.getOutPatientsNotTreated());
    acv.setStillBirths(this.getStillBirths());
    acv.setDaysOutOfStock(this.getDaysOutOfStock());

    AggregatedCase aggregatedCase = acv.getAggregatedCase();

    CaseTreatmentStock[] stockArray = new CaseTreatmentStock[stock.size()];
    for (int i = 0; i < stockArray.length; i++)
    {
      if (i < stockValues.size())
      {
        stockArray[i] = new CaseTreatmentStock(aggregatedCase, stock.get(i));
        stockArray[i].setOutOfStock(stockValues.get(i));
      }
    }

    CaseTreatment[] treatmentArray = new CaseTreatment[treatments.size()];
    for (int i = 0; i < treatmentArray.length; i++)
    {
      if (i < treatmentAmounts.size())
      {
        treatmentArray[i] = new CaseTreatment(aggregatedCase, treatments.get(i));
        treatmentArray[i].setAmount(treatmentAmounts.get(i));
      }
    }

    CaseTreatmentMethod[] methodArray = new CaseTreatmentMethod[methods.size()];
    for (int i = 0; i < methodArray.length; i++)
    {
      if (i < methodAmounts.size())
      {
        methodArray[i] = new CaseTreatmentMethod(aggregatedCase, methods.get(i));
        methodArray[i].setAmount(methodAmounts.get(i));
      }
    }

    CaseDiagnostic[] diagnosticArray = new CaseDiagnostic[diagnostics.size()];
    for (int i = 0; i < diagnosticArray.length; i++)
    {
      if (i < diagnosticAmounts.size())
      {
        diagnosticArray[i] = new CaseDiagnostic(aggregatedCase, diagnostics.get(i));
        diagnosticArray[i].setAmount(diagnosticAmounts.get(i));
        diagnosticArray[i].setAmountPositive(diagnosticPositives.get(i));
      }
    }

    CaseReferral[] referralArray = new CaseReferral[referrals.size()];
    for (int i = 0; i < referralArray.length; i++)
    {
      if (i < referralAmounts.size())
      {
        referralArray[i] = new CaseReferral(aggregatedCase, referrals.get(i));
        referralArray[i].setAmount(referralAmounts.get(i));
      }
    }

    acv.applyAll(treatmentArray, methodArray, stockArray, diagnosticArray, referralArray);
  }

  public static void setupImportListener(ExcelImporter importer, String... params)
  {
    importer.addListener(new AggregatedCaseListener());
    importer.addListener(createExcelGeoListener());
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener());
    exporter.addListener(new AggregatedCaseListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(CollectionSite.CLASS));
    builder.add(GeoHierarchy.getGeoHierarchyFromType(HealthFacility.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
  }

  private PeriodType getPeriodTypeByLabel(String label)
  {
    if (label == null || label.equals(""))
    {
      return null;
    }

    for (PeriodType e : PeriodType.values())
    {
      if (e.getDisplayLabel().equals(label))
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
