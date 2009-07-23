package dss.vector.solutions.export;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.metadata.MdTypeDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.PersonView;
import dss.vector.solutions.entomology.Sex;
import dss.vector.solutions.geo.generated.City;
import dss.vector.solutions.geo.generated.Farm;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.Locality;
import dss.vector.solutions.geo.generated.PopulatedArea;
import dss.vector.solutions.geo.generated.Town;
import dss.vector.solutions.geo.generated.Village;
import dss.vector.solutions.util.GenericHierarchySearcher;
import dss.vector.solutions.util.GeoColumnListener;
import dss.vector.solutions.util.GeoEntitySearcher;
import dss.vector.solutions.util.SearchableHierarchy;

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
    List<SearchableHierarchy> hierarchy = getHierarchy();
    
    GeoEntitySearcher searcher = new GeoEntitySearcher(hierarchy);    
    GeoEntity entity = searcher.getGeoEntity(this.getGeoEntityNames());
    
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
    Map<String, String> map = new HashMap<String, String>(); 
    List<SearchableHierarchy> hierarchy = getHierarchy();
    List<MdAttributeDAOIF> attributes = getGeoEntityAttributes();
    
    int size = Math.min(hierarchy.size(), attributes.size());
    
    for(int i = 0; i < size; i++)
    {
      String key = attributes.get(i).getId();
      String displayLabel = hierarchy.get(i).getDisplayLabel();

      map.put(key, displayLabel);
    }
    
    exporter.addListener(new GeoColumnListener(map));
  }

  private static List<SearchableHierarchy> getHierarchy()
  {
    List<SearchableHierarchy> hierarchy = new LinkedList<SearchableHierarchy>(GeoColumnListener.getPoliticalHierarchy());
    hierarchy.add(new GenericHierarchySearcher(Locality.CLASS));
    hierarchy.add(new GenericHierarchySearcher(Village.CLASS, Town.CLASS, Farm.CLASS, City.CLASS, PopulatedArea.CLASS));
    return hierarchy;
  }
  
  private static List<MdAttributeDAOIF> getGeoEntityAttributes()
  {
    List<MdAttributeDAOIF> list = new LinkedList<MdAttributeDAOIF>();
    list.add(getGeoEntity_0Md());
    list.add(getGeoEntity_01Md());
    list.add(getGeoEntity_02Md());
    list.add(getGeoEntity_03Md());
    list.add(getGeoEntity_04Md());
    list.add(getGeoEntity_05Md());
    list.add(getGeoEntity_06Md());
    list.add(getGeoEntity_07Md());
    list.add(getGeoEntity_08Md());
    list.add(getGeoEntity_09Md());
    list.add(getGeoEntity_10Md());
    
    return list;
  }
  
  private List<String> getGeoEntityNames()
  {
    List<String> list = new LinkedList<String>();
    list.add(this.getGeoEntity_0());
    list.add(this.getGeoEntity_01());
    list.add(this.getGeoEntity_02());
    list.add(this.getGeoEntity_03());
    list.add(this.getGeoEntity_04());
    list.add(this.getGeoEntity_05());
    list.add(this.getGeoEntity_06());
    list.add(this.getGeoEntity_07()); 
    list.add(this.getGeoEntity_08());
    list.add(this.getGeoEntity_09());
    list.add(this.getGeoEntity_10());
    
    return list;
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
