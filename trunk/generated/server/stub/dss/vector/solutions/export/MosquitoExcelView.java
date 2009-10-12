package dss.vector.solutions.export;

import com.terraframe.mojo.constants.MdAttributeBooleanInfo;
import com.terraframe.mojo.dataaccess.AttributeLocalIF;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.entomology.ConcreteMosquitoCollection;
import dss.vector.solutions.entomology.MosquitoView;
import dss.vector.solutions.entomology.assay.biochemical.AAcetateTestResult;
import dss.vector.solutions.entomology.assay.infectivity.POvaleTestResult;
import dss.vector.solutions.ontology.Term;

public class MosquitoExcelView extends MosquitoExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1247805641630L;
  
  private static String BIOCHEMICAL_POSITIVE;
  
  private static String BIOCHEMICAL_NEGATIVE;
  
  private static String MOLECULAR_POSITIVE;
  
  private static String MOLECULAR_NEGATIVE;
  
  public MosquitoExcelView()
  {
    super();
    MdAttributeDAOIF testResultMd = POvaleTestResult.getTestResultMd();
    AttributeLocalIF positiveAttribute = (AttributeLocalIF)testResultMd.getAttributeIF(MdAttributeBooleanInfo.POSITIVE_DISPLAY_LABEL);
    MOLECULAR_POSITIVE = positiveAttribute.getValue(Session.getCurrentLocale());
    AttributeLocalIF negativeAttribute = (AttributeLocalIF)testResultMd.getAttributeIF(MdAttributeBooleanInfo.NEGATIVE_DISPLAY_LABEL);
    MOLECULAR_NEGATIVE = negativeAttribute.getValue(Session.getCurrentLocale());
    
    testResultMd = AAcetateTestResult.getTestResultMd();
    positiveAttribute = (AttributeLocalIF)testResultMd.getAttributeIF(MdAttributeBooleanInfo.POSITIVE_DISPLAY_LABEL);
    BIOCHEMICAL_POSITIVE = positiveAttribute.getValue(Session.getCurrentLocale());
    negativeAttribute = (AttributeLocalIF)testResultMd.getAttributeIF(MdAttributeBooleanInfo.NEGATIVE_DISPLAY_LABEL);
    BIOCHEMICAL_NEGATIVE = negativeAttribute.getValue(Session.getCurrentLocale());
  }
  
  @Override
  public void apply()
  {
    MosquitoView view = new MosquitoView();
    
    view.setCollection(ConcreteMosquitoCollection.getByCollectionId(this.getCollectionId()));
    view.setSampleId(this.getSampleId());
    view.setSpecie(Term.validateByDisplayLabel(this.getSpecie(), MosquitoView.getSpecieMd()));
    view.setIdentificationMethod(Term.validateByDisplayLabel(this.getIdentificationMethod(), MosquitoView.getIdentificationMethodMd()));
    view.setGeneration(Term.validateByDisplayLabel(this.getGeneration(), MosquitoView.getGenerationMd()));
    view.setIsofemale(this.getIsofemale());
    view.setSex(Term.validateByDisplayLabel(this.getSex(), MosquitoView.getSexMd()));
    view.setTestDate(this.getTestDate());
    
    // Biochemical Assays
    view.setAAcetate(getTestResultByDisplayLabel(this.getAAcetate()));
    view.setP450(getTestResultByDisplayLabel(this.getP450()));
    view.setBAcetate(getTestResultByDisplayLabel(this.getBAcetate()));
    view.setHeme(getTestResultByDisplayLabel(this.getHeme()));
    view.setGSTDCNB(getTestResultByDisplayLabel(this.getGSTDCNB()));
    view.setGSTCDNB(getTestResultByDisplayLabel(this.getGSTCDNB()));
    view.setPNPA(getTestResultByDisplayLabel(this.getPNPA()));
    
    // Molecular Assays
    view.setIAcHE(Term.validateByDisplayLabel(this.getIAcHE(), MosquitoView.getIAcHEMd()));
    view.setIAcHEMethod(Term.validateByDisplayLabel(this.getIAcHEMethod(), MosquitoView.getIAcHEMethodMd()));
    view.setAcHEW(Term.validateByDisplayLabel(this.getAcHEW(), MosquitoView.getAcHEWMd()));
    view.setAcHEWMethod(Term.validateByDisplayLabel(this.getAcHEWMethod(), MosquitoView.getAcHEWMethodMd()));
    view.setGABAG(Term.validateByDisplayLabel(this.getGABAG(), MosquitoView.getGABAGMd()));
    view.setGABAGMethod(Term.validateByDisplayLabel(this.getGABAGMethod(), MosquitoView.getGABAGMethodMd()));
    view.setAcHEV(Term.validateByDisplayLabel(this.getAcHEV(), MosquitoView.getAcHEVMd()));
    view.setAcHEVMethod(Term.validateByDisplayLabel(this.getAcHEVMethod(), MosquitoView.getAcHEVMethodMd()));
    view.setEKDR(Term.validateByDisplayLabel(this.getEKDR(), MosquitoView.getEKDRMd()));
    view.setEKDRMethod(Term.validateByDisplayLabel(this.getEKDRMethod(), MosquitoView.getEKDRMethodMd()));
    view.setWKDR(Term.validateByDisplayLabel(this.getWKDR(), MosquitoView.getWKDRMd()));
    view.setWKDRMethod(Term.validateByDisplayLabel(this.getWKDRMethod(), MosquitoView.getWKDRMethodMd()));
    view.setAcHES(Term.validateByDisplayLabel(this.getAcHES(), MosquitoView.getAcHESMd()));
    view.setAcHESMethod(Term.validateByDisplayLabel(this.getAcHESMethod(), MosquitoView.getAcHESMethodMd()));
    view.setGABAS(Term.validateByDisplayLabel(this.getGABAS(), MosquitoView.getGABASMd()));
    view.setGABASMethod(Term.validateByDisplayLabel(this.getGABASMethod(), MosquitoView.getGABASMethodMd()));
    
    // Infectivity Assays
    view.setPOvale(getTestResultByDisplayLabel(this.getPOvale()));
    view.setPOvaleMethod(Term.validateByDisplayLabel(this.getPOvaleMethod(), MosquitoView.getPOvaleMethodMd()));
    view.setPMalariae(getTestResultByDisplayLabel(this.getPMalariae()));
    view.setPMalariaeMethod(Term.validateByDisplayLabel(this.getPMalariaeMethod(), MosquitoView.getPMalariaeMethodMd()));
    view.setPFalciparum(getTestResultByDisplayLabel(this.getPFalciparum()));
    view.setPFalciparumMethod(Term.validateByDisplayLabel(this.getPFalciparumMethod(), MosquitoView.getPFalciparumMethodMd()));
    view.setPVivax(getTestResultByDisplayLabel(this.getPVivax()));
    view.setPVivaxMethod(Term.validateByDisplayLabel(this.getPVivaxMethod(), MosquitoView.getPVivaxMethodMd()));
    view.setMixed(getTestResultByDisplayLabel(this.getMixed()));
    view.setMixedMethod(Term.validateByDisplayLabel(this.getMixedMethod(), MosquitoView.getMixedMethodMd()));
    
    view.apply();
  }
  
  private Boolean getTestResultByDisplayLabel(String displayLabel)
  {
    if (MOLECULAR_POSITIVE.equals(displayLabel) || BIOCHEMICAL_POSITIVE.equals(displayLabel))
    {
      return Boolean.TRUE;
    }
    if (MOLECULAR_NEGATIVE.equals(displayLabel) || BIOCHEMICAL_NEGATIVE.equals(displayLabel))
    {
      return Boolean.FALSE;
    }
    return null;
  }
}
