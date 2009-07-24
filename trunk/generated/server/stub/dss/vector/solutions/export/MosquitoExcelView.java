package dss.vector.solutions.export;

import com.terraframe.mojo.constants.MdAttributeBooleanInfo;
import com.terraframe.mojo.dataaccess.AttributeLocalIF;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.entomology.ConcreteMosquitoCollection;
import dss.vector.solutions.entomology.MosquitoView;
import dss.vector.solutions.entomology.Sex;
import dss.vector.solutions.entomology.assay.biochemical.AAcetateTestResult;
import dss.vector.solutions.entomology.assay.infectivity.POvaleTestResult;
import dss.vector.solutions.mo.Generation;
import dss.vector.solutions.mo.IdentificationMethod;
import dss.vector.solutions.mo.InfectivityMethodology;
import dss.vector.solutions.mo.InsecticideMethodology;
import dss.vector.solutions.mo.MolecularAssayResult;
import dss.vector.solutions.mo.Specie;

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
    view.setSpecie(Specie.validateByDisplayLabel(this.getSpecie()));
    view.setIdentificationMethod(IdentificationMethod.validateByDisplayLabel(this.getIdentificationMethod()));
    view.setGeneration(Generation.validateByDisplayLabel(this.getGeneration()));
    view.setIsofemale(this.getIsofemale());
    view.addSex(PersonExcelView.getSexByLabel(this.getSex()));
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
    view.setIAcHE(MolecularAssayResult.validateByDisplayLabel(this.getIAcHE()));
    view.setIAcHEMethod(InsecticideMethodology.validateByDisplayLabel(this.getIAcHEMethod()));
    view.setAcHEW(MolecularAssayResult.validateByDisplayLabel(this.getAcHEW()));
    view.setAcHEWMethod(InsecticideMethodology.validateByDisplayLabel(this.getAcHEWMethod()));
    view.setGABAG(MolecularAssayResult.validateByDisplayLabel(this.getGABAG()));
    view.setGABAGMethod(InsecticideMethodology.validateByDisplayLabel(this.getGABAGMethod()));
    view.setAcHEV(MolecularAssayResult.validateByDisplayLabel(this.getAcHEV()));
    view.setAcHEVMethod(InsecticideMethodology.validateByDisplayLabel(this.getAcHEVMethod()));
    view.setEKDR(MolecularAssayResult.validateByDisplayLabel(this.getEKDR()));
    view.setEKDRMethod(InsecticideMethodology.validateByDisplayLabel(this.getEKDRMethod()));
    view.setWKDR(MolecularAssayResult.validateByDisplayLabel(this.getWKDR()));
    view.setWKDRMethod(InsecticideMethodology.validateByDisplayLabel(this.getWKDRMethod()));
    view.setAcHES(MolecularAssayResult.validateByDisplayLabel(this.getAcHES()));
    view.setAcHESMethod(InsecticideMethodology.validateByDisplayLabel(this.getAcHESMethod()));
    view.setGABAS(MolecularAssayResult.validateByDisplayLabel(this.getGABAS()));
    view.setGABASMethod(InsecticideMethodology.validateByDisplayLabel(this.getGABASMethod()));
    
    // Infectivity Assays
    view.setPOvale(getTestResultByDisplayLabel(this.getPOvale()));
    view.setPOvaleMethod(InfectivityMethodology.validateByDisplayLabel(this.getPOvaleMethod()));
    view.setPMalariae(getTestResultByDisplayLabel(this.getPMalariae()));
    view.setPMalariaeMethod(InfectivityMethodology.validateByDisplayLabel(this.getPMalariaeMethod()));
    view.setPFalciparum(getTestResultByDisplayLabel(this.getPFalciparum()));
    view.setPFalciparumMethod(InfectivityMethodology.validateByDisplayLabel(this.getPFalciparumMethod()));
    view.setPVivax(getTestResultByDisplayLabel(this.getPVivax()));
    view.setPVivaxMethod(InfectivityMethodology.validateByDisplayLabel(this.getPVivaxMethod()));
    view.setMixed(getTestResultByDisplayLabel(this.getMixed()));
    view.setMixedMethod(InfectivityMethodology.validateByDisplayLabel(this.getMixedMethod()));
    
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
