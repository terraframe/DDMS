package dss.vector.solutions.export.entomology.assay;


public class AdultDiscriminatingDoseAssayExcelView extends AdultDiscriminatingDoseAssayExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1244508626257L;
  
  public AdultDiscriminatingDoseAssayExcelView()
  {
    super();
  }
//  
//  @Override
//  @Transaction
//  public void apply()
//  {
//    AdultDiscriminatingDoseAssay adda = new AdultDiscriminatingDoseAssay();
//    
//    MosquitoCollectionView mosquitoCollectionView = new MosquitoCollectionView();
//    mosquitoCollectionView.setCollectionMethod(this.getCollectionMethod());
//    mosquitoCollectionView.setDateCollected(this.getDateCollected());
//    mosquitoCollectionView.setGeoEntity(this.getGeoEntity());
//    adda.setCollection(mosquitoCollectionView.findMatch());
//    
//    adda.setTestDate(this.getTestDate());
//    adda.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(), AdultDiscriminatingDoseAssay.getTestMethodMd()));
//    adda.setGeneration(Term.validateByDisplayLabel(this.getGeneration(), AdultDiscriminatingDoseAssay.getGenerationMd()));
//    adda.setIsofemale(this.getIsofemale());
//    adda.setSex(Term.validateByDisplayLabel(this.getSex(), AdultDiscriminatingDoseAssay.getSexMd()));    
//    adda.setIdentificationMethod(Term.validateByDisplayLabel(this.getIdentificationMethod(), AdultDiscriminatingDoseAssay.getIdentificationMethodMd()));
//
//    // Specie is optional so don't validate the input if
//    // the value is null or empty
//    if(this.hasSpecie())
//    {
//      adda.setSpecie(Term.validateByDisplayLabel(this.getSpecie(), AdultDiscriminatingDoseAssay.getSpecieMd()));
//    }
//    
//    AdultAgeRange excelAgeRange = this.getAgeRange();
//    AdultAgeRange newAgeRange = adda.getAgeRange();
//    newAgeRange.setStartPoint(excelAgeRange.getStartPoint());
//    newAgeRange.setEndPoint(excelAgeRange.getEndPoint());
//    
//    adda.setFed(this.getFed());
//    adda.setGravid(this.getGravid());
//    adda.setExposureTime(this.getExposureTime());
//    adda.setHoldingTime(this.getHoldingTime());
//    
//    adda.setInsecticide(Insecticide.get(this.getInsecticideActiveIngredient(), this.getInsecticideUnits(), this.getInsecticideAmount()));
//    
//    adda.setQuantityTested(this.getQuantityTested());
//    adda.setQuantityDead(this.getQuantityDead());
//    adda.setControlTestMortality(this.getControlTestMortality());
//    adda.setIntervalTime(this.getIntervalTime());
//    
//    adda.apply();
//  }
//  
//  public static List<String> customAttributeOrder()
//  {
//    LinkedList<String> list = new LinkedList<String>();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    list.add();
//    return list;
//  }
//  
//  private boolean hasSpecie()
//  {
//    return this.getSpecie() != null && !this.getSpecie().equals("");
//  }
//
//  public static AssaySex getAssaySexByLabel(String label)
//  {
//    for (AssaySex e : AssaySex.values())
//    {
//      if (e.getDisplayLabel().equals(label))
//      {
//        return e;
//      }
//    }
//    String message = "[" + label + "] is not a valid display label for [" + AssaySex.CLASS + "]";
//    throw new DataNotFoundException(message, MdTypeDAO.getMdTypeDAO(AssaySex.CLASS));
//  }
//  
//  public static void setupExportListener(ExcelExporter exporter, String...params)
//  {
//    exporter.addListener(createExcelGeoListener());
//  }
//  
//  public static void setupImportListener(ExcelImporter importer, String... params)
//  {
//    importer.addListener(createExcelGeoListener());
//  }
//
//  private static DynamicGeoColumnListener createExcelGeoListener()
//  {
//    HierarchyBuilder builder = new HierarchyBuilder();
//    builder.add(GeoHierarchy.getGeoHierarchyFromType(SentinelSite.CLASS));
//    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
//  }
}
