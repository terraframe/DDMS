package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.SurfacePosition;
import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.EfficacyAssayView;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.Surface;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class EfficacyAssayExcelView extends EfficacyAssayExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
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
    
    eav.setGeoId(getGeoEntity().getGeoId());

    eav.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(), EfficacyAssayView.getTestMethodMd()));
    eav.setSpecie(Term.validateByDisplayLabel(this.getSpecie(), EfficacyAssayView.getSpecieMd()));
    eav.setTestDate(this.getTestDate());
    eav.setColonyName(this.getColonyName());
    
    AdultAgeRange excelAgeRange = this.getAgeRange();
    AdultAgeRange newAgeRange = eav.getAgeRange();
    newAgeRange.setStartPoint(excelAgeRange.getStartPoint());
    newAgeRange.setEndPoint(excelAgeRange.getEndPoint());
    
    eav.setSex(Term.validateByDisplayLabel(this.getSex(), EfficacyAssayView.getSexMd()));
    eav.setGravid(this.getGravid());
    eav.setFed(this.getFed());
    eav.setTimeOnSurface(this.getTimeOnSurface());
    eav.setSurfacePostion(Term.validateByDisplayLabel(this.getSurfacePosition(), EfficacyAssayView.getSurfacePostionMd()));
    eav.setExposureTime(this.getExposureTime());
    eav.setHoldingTime(this.getHoldingTime());
    eav.setQuantityTested(this.getQuantityTested());
    eav.setQuantityDead(this.getQuantityDead());
    
    eav.setInsecticide(Insecticide.get(this.getInsecticideActiveIngredient(), this.getInsecticideUnits(), this.getInsecticideAmount()));
    
    eav.apply();
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(TESTDATE);
    list.add(TESTMETHOD);
    list.add(SPECIE);
    list.add(COLONYNAME);
    list.add(AGERANGE);
    list.add(SEX);
    list.add(GRAVID);
    list.add(FED);
    list.add(INSECTICIDEACTIVEINGREDIENT);
    list.add(INSECTICIDEAMOUNT);
    list.add(INSECTICIDEUNITS);
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
    exporter.addListener(createExcelGeoListener());
  }
  
  public static void setupImportListener(ImportContext context, String... params)
  {
    context.addListener(createExcelGeoListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener()
  {

    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(Surface.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
  }

  public static SurfacePosition getSurfacePositionByLabel(String label)
  {
    if(label == null || label.equals(""))
    {
      return null;
    }
    
    for (SurfacePosition e : SurfacePosition.values())
    {
      if (e.getDisplayLabel().equals(label))
      {
        return e;
      }
    }
    String message = "[" + label + "] is not a valid display label for [" + SurfacePosition.CLASS + "]";
    throw new DataNotFoundException(message, MdTypeDAO.getMdTypeDAO(SurfacePosition.CLASS));
  }
}
