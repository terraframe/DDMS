package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.EfficacyAssayView;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.Surface;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class EfficacyAssayExcelView extends EfficacyAssayExcelViewBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1246490694186L;

  public EfficacyAssayExcelView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    EfficacyAssayView eav = new EfficacyAssayView();

    eav.setUniqueAssayId(this.getUniqueAssayId());

    if (getGeoEntity() != null)
    {
      eav.setGeoId(getGeoEntity().getGeoId());
    }
    else
    {
      eav.setGeoId("");
    }

    eav.setSurfaceType(Term.validateByDisplayLabel(this.getSurfaceType(),
      EfficacyAssayView.getSurfaceTypeMd()));
    eav.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(),
      EfficacyAssayView.getTestMethodMd()));
    eav.setSpecie(Term.validateByDisplayLabel(this.getSpecie(), EfficacyAssayView.getSpecieMd()));
    eav.setTestDate(this.getTestDate());
    eav.setColonyName(this.getColonyName());

    AdultAgeRange excelAgeRange = this.getAgeRange();
    if (excelAgeRange != null)
    {
      AdultAgeRange newAgeRange = eav.getAgeRange();
      newAgeRange.setStartPoint(excelAgeRange.getStartPoint());
      newAgeRange.setEndPoint(excelAgeRange.getEndPoint());
    }

    eav.setControlTestMortality(this.getControlTestMortality());
    eav.setSex(Term.validateByDisplayLabel(this.getSex(), EfficacyAssayView.getSexMd()));
    eav.setGravid(this.getGravid());
    eav.setFed(this.getFed());
    eav.setTimeOnSurface(this.getTimeOnSurface());
    eav.setSurfacePostion(Term.validateByDisplayLabel(this.getSurfacePosition(),
        EfficacyAssayView.getSurfacePostionMd()));
    eav.setExposureTime(this.getExposureTime());
    eav.setHoldingTime(this.getHoldingTime());
    eav.setQuantityTested(this.getQuantityTested());
    eav.setQuantityDead(this.getQuantityDead());
    eav.setInsecticideBrand(InsecticideBrand.validateByName(this.getInsecticideTerm()));

    eav.apply();
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(UNIQUEASSAYID);
    list.add(SURFACETYPE);
    list.add(TESTDATE);
    list.add(CONTROLTESTMORTALITY);
    list.add(TESTMETHOD);
    list.add(SPECIE);
    list.add(COLONYNAME);
    list.add(AGERANGE);
    list.add(SEX);
    list.add(GRAVID);
    list.add(FED);
    list.add(INSECTICIDETERM);
    list.add(TIMEONSURFACE);
    list.add(SURFACEPOSITION);
    list.add(EXPOSURETIME);
    list.add(HOLDINGTIME);
    list.add(QUANTITYTESTED);
    list.add(QUANTITYDEAD);
    return list;
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener(null));
  }

  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    context.addListener(createExcelGeoListener(importer));
  }

  private static DynamicGeoColumnListener createExcelGeoListener(ExcelImportManager importer)
  {

    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(Surface.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder, importer);
  }
}
