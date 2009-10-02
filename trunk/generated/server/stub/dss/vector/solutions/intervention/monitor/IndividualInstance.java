package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import dss.vector.solutions.surveillance.DiagnosticGrid;
import dss.vector.solutions.surveillance.TreatmentGrid;

public class IndividualInstance extends IndividualInstanceBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254360074462L;
  
  public IndividualInstance()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    Date admissionDate = this.getAdmissionDate();
    Date facilityVisit = this.getFacilityVisit();
    Boolean clinicalDiagnosis = this.getClinicalDiagnosis();
    DiagnosticGrid labTest = this.getLabTest();
    TreatmentGrid treatment = this.getTreatment();
    if(admissionDate != null && facilityVisit != null && clinicalDiagnosis != null && labTest != null && treatment != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      
      return format.format(admissionDate) + "." + format.format(facilityVisit) + "." + clinicalDiagnosis + "." + labTest.getOptionName() + "." + treatment.getOptionName();
    }
    return this.getId();
  }
}
