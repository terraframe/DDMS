package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionView;
import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.KnockDownAssay;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class KnockDownAssayExcelView extends KnockDownAssayExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245998013289L;
  
  public KnockDownAssayExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    KnockDownAssay kda = new KnockDownAssay();
    
    kda.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    kda.setTestDate(this.getTestDate());
    kda.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(), KnockDownAssay.getTestMethodMd()));
    kda.setGeneration(Term.validateByDisplayLabel(this.getGeneration(), KnockDownAssay.getGenerationMd()));
    kda.setIsofemale(this.getIsofemale());
    kda.setSex(Term.validateByDisplayLabel(this.getSex(), KnockDownAssay.getSexMd()));
    kda.setSpecie(Term.validateByDisplayLabel(this.getSpecie(), KnockDownAssay.getSpecieMd()));
    kda.setIdentificationMethod(Term.validateByDisplayLabel(this.getIdentificationMethod(), KnockDownAssay.getIdentificationMethodMd()));
    
    AdultAgeRange excelAgeRange = this.getAgeRange();
    AdultAgeRange newAgeRange = kda.getAgeRange();
    newAgeRange.setStartPoint(excelAgeRange.getStartPoint());
    newAgeRange.setEndPoint(excelAgeRange.getEndPoint());
    
    kda.setFed(this.getFed());
    kda.setGravid(this.getGravid());
    kda.setExposureTime(this.getExposureTime());
    kda.setInsecticide(Insecticide.get(this.getInsecticideActiveIngredient(), this.getInsecticideUnits(), this.getInsecticideAmount()));
    kda.setQuantityTested(this.getQuantityTested());
    kda.setKd50(this.getKd50());
    kda.setKd95(this.getKd95());
    kda.setInterval10(this.getInterval10());
    kda.setInterval20(this.getInterval20());
    kda.setInterval30(this.getInterval30());
    kda.setInterval40(this.getInterval40());
    kda.setInterval50(this.getInterval50());
    kda.setInterval60(this.getInterval60());
    
    kda.apply();
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(COLLECTIONID);
    list.add(TESTDATE);
    list.add(TESTMETHOD);
    list.add(GENERATION);
    list.add(ISOFEMALE);
    list.add(SEX);
    list.add(SPECIE);
    list.add(IDENTIFICATIONMETHOD);
    list.add(AGERANGE);
    list.add(FED);
    list.add(GRAVID);
    list.add(EXPOSURETIME);
    list.add(INSECTICIDEACTIVEINGREDIENT);
    list.add(INSECTICIDEAMOUNT);
    list.add(INSECTICIDEUNITS);
    list.add(QUANTITYTESTED);
    list.add(KD50);
    list.add(KD95);
    list.add(INTERVAL10);
    list.add(INTERVAL20);
    list.add(INTERVAL30);
    list.add(INTERVAL40);
    list.add(INTERVAL50);
    list.add(INTERVAL60);
    return list;
  }
}
