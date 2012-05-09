package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;

import dss.vector.solutions.PersonView;
import dss.vector.solutions.PersonWithDelegatesView;
import dss.vector.solutions.PersonWithDelegatesViewQuery;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class PersonExcelView extends PersonExcelViewBase implements Reloadable
{
  private static final long serialVersionUID = 1246241921437L;
  
  public PersonExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    GeoEntity residentialEntity = this.getResidentialGeoEntity();
    GeoEntity workEntity = this.getWorkGeoEntity();
    
    PersonView personView = new PersonView();
    
    personView.setFirstName(this.getFirstName());
    personView.setLastName(this.getLastName());
    personView.setDateOfBirth(this.getDateOfBirth());
    personView.setIdentifier(this.getIdentifier()); 
    personView.setSex(Term.validateByDisplayLabel(this.getSex(), PersonView.getSexMd()));
    
    PersonWithDelegatesViewQuery query = personView.searchForDuplicates();
    OIterator<? extends PersonWithDelegatesView> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      personView = iterator.next();
    }
    iterator.close();
    
    if(residentialEntity != null)
    {
      personView.setResidentialGeoId(residentialEntity.getGeoId());
    }
    
    if(workEntity != null)
    {
      personView.setWorkGeoId(workEntity.getGeoId());
    }
    
    personView.setIsMDSSUser(this.getIsMDSSUser() != null && this.getIsMDSSUser());
    personView.setUsername(this.getUsername());
    personView.setPassword(this.getPassword());
    String diseaseName = this.getDisease();
    if (diseaseName.equalsIgnoreCase(Disease.MALARIA))
    {
      personView.setDisease(Disease.getMalaria());
    }
    else if (diseaseName.equalsIgnoreCase(Disease.DENGUE))
    {
      personView.setDisease(Disease.getDengue());
    }
    
    personView.setIsPatient(this.getIsPatient() != null && this.getIsPatient());
    personView.setIsIPTRecipient(this.getIsIPTRecipient() != null && this.getIsIPTRecipient());
    personView.setIsITNRecipient(this.getIsITNRecipient() != null && this.getIsITNRecipient());
    
    personView.setIsSprayLeader(this.getIsSprayLeader() != null && this.getIsSprayLeader());
    personView.setIsSprayOperator(this.getIsSprayOperator() != null && this.getIsSprayOperator());
    personView.setMemberId(this.getMemberId());
    
    personView.setIsStockStaff(this.getIsStockStaff() != null && this.getIsStockStaff());
    personView.setIsSupervisor(this.getIsSupervisor() != null && this.getIsSupervisor());
        
    personView.apply();
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(FIRSTNAME);
    list.add(LASTNAME);
    list.add(DATEOFBIRTH);
    list.add(SEX);
    list.add(ISMDSSUSER);
    list.add(USERNAME);
    list.add(PASSWORD);
    list.add(DISEASE);
    list.add(ISSPRAYLEADER);
    list.add(MEMBERID);
    list.add(ISSPRAYOPERATOR);
    list.add(ISSTOCKSTAFF);
    list.add(ISSUPERVISOR);
    list.add(ISIPTRECIPIENT);
    list.add(ISITNRECIPIENT);
    list.add(ISPATIENT);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String...params)
  {
    exporter.addListener(createExcelGeoListener(RESIDENTIALGEOENTITY));
    exporter.addListener(createExcelGeoListener(WORKGEOENTITY));
  }

  public static void setupImportListener(ImportContext context, String... params)
  {
    context.addListener(createExcelGeoListener(RESIDENTIALGEOENTITY));
    context.addListener(createExcelGeoListener(WORKGEOENTITY));
  }
  
  private static DynamicGeoColumnListener createExcelGeoListener(String attributeName)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllPoliticals())
    {
      builder.add(hierarchy);
    }
    return new DynamicGeoColumnListener(CLASS, attributeName, builder);
  }
}
