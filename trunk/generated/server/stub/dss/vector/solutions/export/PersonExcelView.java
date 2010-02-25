package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.PersonView;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class PersonExcelView extends PersonExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
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
    personView.setSex(Term.validateByDisplayLabel(this.getSex(), PersonView.getSexMd()));
    
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

  public static void setupImportListener(ExcelImporter importer, String... params)
  {
    importer.addListener(createExcelGeoListener(RESIDENTIALGEOENTITY));
    importer.addListener(createExcelGeoListener(WORKGEOENTITY));
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
