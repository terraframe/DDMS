package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import dss.vector.solutions.entomology.MolecularAssayView;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.ontology.Term;

public class MolecularAssayExcelView extends MolecularAssayExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1979900442;
  
  public MolecularAssayExcelView()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    MolecularAssayView assay = new MolecularAssayView();
    
    assay.setUniqueAssayId(this.getUniqueAssayId());
    assay.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    assay.setMosquitoId(this.getMosquitoId());
    assay.setSpecies(Term.validateByDisplayLabel(this.getSpecies(), MolecularAssayView.getSpeciesMd()));
    assay.setIdentMethod(Term.validateByDisplayLabel(this.getIdentMethod(), MolecularAssayView.getIdentMethodMd()));
    assay.setSex(Term.validateByDisplayLabel(this.getSex(), MolecularAssayView.getSexMd()));
    assay.setGeneration(Term.validateByDisplayLabel(this.getGeneration(), MolecularAssayView.getGenerationMd()));
    assay.setIsofemale(this.getIsofemale());
    assay.setAssayMethod(Term.validateByDisplayLabel(this.getAssayMethod(), MolecularAssayView.getAssayMethodMd()));
    assay.setTarget(Term.validateByDisplayLabel(this.getTarget(), MolecularAssayView.getTargetMd()));
    assay.setNumberRR(this.getNumberRR());
    assay.setNumberRS(this.getNumberRS());
    assay.setNumberSS(this.getNumberSS());
    assay.apply();
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(UNIQUEASSAYID);
    list.add(COLLECTIONID);
    list.add(MOSQUITOID);
    list.add(SPECIES);
    list.add(IDENTMETHOD);
    list.add(SEX);
    list.add(GENERATION);
    list.add(ISOFEMALE);
    list.add(ASSAYMETHOD);
    list.add(TARGET);
    list.add(NUMBERRR);
    list.add(NUMBERRS);
    list.add(NUMBERSS);
    return list;
  }
}
