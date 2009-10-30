package dss.vector.solutions.export;

import java.util.Date;

import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.Person;
import dss.vector.solutions.PersonQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.intervention.monitor.ITNDistributionView;
import dss.vector.solutions.intervention.monitor.ITNRecipient;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class ITNDistributionExcelView extends ITNDistributionExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256860224442L;
  
  public ITNDistributionExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    ITNDistributionView view = new ITNDistributionView();
    
    view.setDistributionDate(this.getDistributionDate());
    view.setFacility(this.getFacility().getGeoId());
    view.setService(Term.validateByDisplayLabel(this.getService(), getServiceMd()));
    view.setBatchNumber(this.getBatchNumber());
    view.setRecipient(searchForRecipient());
    view.setNet(Term.validateByDisplayLabel(this.getNet(), getNetMd()));
    view.setNumberSold(this.getNumberSold());
    view.setCurrencyReceived(this.getCurrencyReceived());
    view.setDistributorName(this.getDistributorName());
    view.setDistributorSurname(this.getDistributorSurname());
    view.apply();
  }
  
  private ITNRecipient searchForRecipient()
  {
    String firstName = this.getRecipientFirstName();
    String lastName = this.getRecipientLastName();
    Date dob = this.getRecipientDOB();
    
    PersonQuery query = new PersonQuery(new QueryFactory());
    if (firstName!=null && firstName.length()>0)
    {
      query.WHERE(query.getFirstName().EQ(firstName));
    }
    if (lastName!=null && lastName.length()>0)
    {
      query.WHERE(query.getLastName().EQ(lastName));
    }
    if (dob!=null)
    {
      query.WHERE(query.getDateOfBirth().EQ(dob));
    }
    
    if (query.getCount()>1)
    {
      AmbiguousRecipientProblem problem = new AmbiguousRecipientProblem();
      problem.setFirstName(firstName);
      problem.setLastName(lastName);
      problem.setDob(dob);
      problem.throwIt();
      return null;
    }
    
    Person person;
    OIterator<? extends Person> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      person = iterator.next();
    }
    else
    {
      person = new Person();
      person.setFirstName(firstName);
      person.setLastName(lastName);
      person.setDateOfBirth(dob);
      person.apply();
    }
    iterator.close();
    
    ITNRecipient recipient = person.getItnRecipientDelegate();
    if (recipient==null)
    {
      recipient = new ITNRecipient();
      recipient.setPerson(person);
      recipient.apply();
      
      person.setItnRecipientDelegate(recipient);
      person.apply();
    }
    return recipient;
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener());
  }

  public static void setupImportListener(ExcelImporter importer, String... params)
  {
    importer.addListener(createExcelGeoListener());
  }
  
  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(HealthFacility.CLASS));
    return new DynamicGeoColumnListener(CLASS, FACILITY, builder);
  }
}
