package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.EfficacyAssayView;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;
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

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, GEOENTITY))
    {
      eav.setGeoId(getGeoEntity().getGeoId());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, SURFACETYPE))
    {
      eav.setSurfaceType(Term.validateByDisplayLabel(this.getSurfaceType(),
          EfficacyAssayView.getSurfaceTypeMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, TESTMETHOD))
    {
      eav.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(),
          EfficacyAssayView.getTestMethodMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, SPECIE))
    {
      eav.setSpecie(Term.validateByDisplayLabel(this.getSpecie(), EfficacyAssayView.getSpecieMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, TESTDATE))
    {
      eav.setTestDate(this.getTestDate());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, COLONYNAME))
    {
      eav.setColonyName(this.getColonyName());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, AGERANGE))
    {
      AdultAgeRange excelAgeRange = this.getAgeRange();
      AdultAgeRange newAgeRange = eav.getAgeRange();
      newAgeRange.setStartPoint(excelAgeRange.getStartPoint());
      newAgeRange.setEndPoint(excelAgeRange.getEndPoint());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, CONTROLTESTMORTALITY))
    {
      eav.setControlTestMortality(this.getControlTestMortality());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, SEX))
    {
      eav.setSex(Term.validateByDisplayLabel(this.getSex(), EfficacyAssayView.getSexMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, GRAVID))
    {
      eav.setGravid(this.getGravid());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, FED))
    {
      eav.setFed(this.getFed());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, TIMEONSURFACE))
    {
      eav.setTimeOnSurface(this.getTimeOnSurface());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, SURFACEPOSITION))
    {
      eav.setSurfacePostion(Term.validateByDisplayLabel(this.getSurfacePosition(),
          EfficacyAssayView.getSurfacePostionMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, EXPOSURETIME))
    {
      eav.setExposureTime(this.getExposureTime());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, HOLDINGTIME))
    {
      eav.setHoldingTime(this.getHoldingTime());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, QUANTITYTESTED))
    {
      eav.setQuantityTested(this.getQuantityTested());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, QUANTITYDEAD))
    {
      eav.setQuantityDead(this.getQuantityDead());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, eav, INSECTICIDETERM))
    {
      eav.setInsecticideBrand(InsecticideBrand.validateByName(this.getInsecticideTerm()));
    }

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
