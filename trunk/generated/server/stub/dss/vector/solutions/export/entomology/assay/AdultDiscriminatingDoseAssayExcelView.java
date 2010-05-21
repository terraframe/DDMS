package dss.vector.solutions.export.entomology.assay;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionView;
import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay;
import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;


public class AdultDiscriminatingDoseAssayExcelView extends AdultDiscriminatingDoseAssayExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1244508626257L;
  
  public AdultDiscriminatingDoseAssayExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    AdultDiscriminatingDoseAssay adda = new AdultDiscriminatingDoseAssay();
    
    adda.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    adda.setTestDate(this.getTestDate());
    adda.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(), AdultDiscriminatingDoseAssay.getTestMethodMd()));
    adda.setGeneration(Term.validateByDisplayLabel(this.getGeneration(), AdultDiscriminatingDoseAssay.getGenerationMd()));
    adda.setIsofemale(this.getIsofemale());
    adda.setSex(Term.validateByDisplayLabel(this.getSex(), AdultDiscriminatingDoseAssay.getSexMd()));    
    adda.setSpecie(Term.validateByDisplayLabel(this.getSpecie(), AdultDiscriminatingDoseAssay.getSpecieMd()));
    adda.setIdentificationMethod(Term.validateByDisplayLabel(this.getIdentificationMethod(), AdultDiscriminatingDoseAssay.getIdentificationMethodMd()));
    
    AdultAgeRange excelAgeRange = this.getAgeRange();
    AdultAgeRange newAgeRange = adda.getAgeRange();
    newAgeRange.setStartPoint(excelAgeRange.getStartPoint());
    newAgeRange.setEndPoint(excelAgeRange.getEndPoint());
    
    adda.setFed(this.getFed());
    adda.setGravid(this.getGravid());
    adda.setExposureTime(this.getExposureTime());
    adda.setHoldingTime(this.getHoldingTime());
    
    adda.setInsecticide(Insecticide.get(this.getInsecticideActiveIngredient(), this.getInsecticideUnits(), this.getInsecticideAmount()));
    
    adda.setQuantityTested(this.getQuantityTested());
    adda.setQuantityDead(this.getQuantityDead());
    adda.setControlTestMortality(this.getControlTestMortality());
    adda.setKd50(this.getKd50());
    adda.setKd95(this.getKd95());
    
    adda.apply();
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
    list.add(HOLDINGTIME);
    list.add(INSECTICIDEACTIVEINGREDIENT);
    list.add(INSECTICIDEAMOUNT);
    list.add(INSECTICIDEUNITS);
    list.add(QUANTITYTESTED);
    list.add(QUANTITYDEAD);
    list.add(CONTROLTESTMORTALITY);
    list.add(KD50);
    list.add(KD95);
    return list;
  }
}
