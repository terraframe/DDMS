package dss.vector.solutions.export;

import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.metadata.MdTypeDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.PersonView;
import dss.vector.solutions.entomology.Sex;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SubPopulatedArea;

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
    GeoEntity entity = this.getGeoEntity();
    
    PersonView personView = new PersonView();
    
    personView.setFirstName(this.getFirstName());
    personView.setLastName(this.getLastName());
    personView.setDateOfBirth(this.getDateOfBirth());
    personView.addSex(getSexByLabel(this.getSex()));
    personView.setResidentialGeoId(entity.getGeoId());
    
    personView.setIsMDSSUser(this.getIsMDSSUser());
    personView.setUsername(this.getUsername());
    personView.setPassword(this.getPassword());
    
    personView.setIsPatient(this.getIsPatient());
    personView.setIsIPTRecipient(this.getIsIPTRecipient());
    personView.setIsITNRecipient(this.getIsITNRecipient());
    
    personView.setIsSprayLeader(this.getIsSprayLeader());
    personView.setLeaderId(this.getLeaderId());
    
    personView.setIsSprayOperator(this.getIsSprayOperator());
    personView.setOperatorId(this.getOperatorId());
    
    personView.apply();
  }
  
  public static void setupExportListener(ExcelExporter exporter, String...params)
  {
    GeoHierarchy subPopulatedArea = GeoHierarchy.getGeoHierarchyFromType(SubPopulatedArea.CLASS);
    exporter.addListener(new DynamicGeoColumnListener(CLASS, GEOENTITY, subPopulatedArea));
  }

  public static Sex getSexByLabel(String label)
  {
    for (Sex e : Sex.values())
    {
      if (e.getDisplayLabel().equals(label))
      {
        return e;
      }
    }
    String message = "[" + label + "] is not a valid display label for [" + Sex.CLASS + "]";
    throw new DataNotFoundException(message, MdTypeDAO.getMdTypeDAO(Sex.CLASS));
  }
}
