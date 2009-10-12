package dss.vector.solutions.export;

import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.MorphologicalSpecieGroup;
import dss.vector.solutions.entomology.MosquitoCollectionPoint;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.PermanentWaterBody;
import dss.vector.solutions.geo.generated.Trap;
import dss.vector.solutions.ontology.Term;

public class MorphologicalSpecieGroupExcelView extends MorphologicalSpecieGroupExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1246002222478L;
  
  public MorphologicalSpecieGroupExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    MorphologicalSpecieGroup msg = new MorphologicalSpecieGroup();
    
    MosquitoCollectionPoint mcp = MosquitoCollectionPoint.findOrCreate(this.getGeoEntity(), this.getDateCollected());
    msg.setCollection(mcp);
    msg.setSpecie(Term.validateByDisplayLabel(this.getSpecie(), MorphologicalSpecieGroup.getSpecieMd()));
    msg.setIdentificationMethod(Term.validateByDisplayLabel(this.getIdentificationMethod(), MorphologicalSpecieGroup.getIdentificationMethodMd()));
    msg.setQuantity(this.getQuantity());
    msg.setQuantityMale(this.getQuantityMale());
    msg.setQuantityFemale(this.getQuantityFemale());
    msg.apply();
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener());
  }
  
  public static void setupImportListener(ExcelImporter importer, String... params)
  {
    importer.addListener(createExcelGeoListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    GeoHierarchy trap = GeoHierarchy.getGeoHierarchyFromType(Trap.CLASS);
    GeoHierarchy pwb = GeoHierarchy.getGeoHierarchyFromType(PermanentWaterBody.CLASS);
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, trap, pwb);
  }
}
