package dss.vector.solutions.export;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.Person;
import dss.vector.solutions.PersonQuery;
import dss.vector.solutions.PersonView;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.intervention.monitor.ITNDistribution;
import dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroup;
import dss.vector.solutions.intervention.monitor.ITNDistributionView;
import dss.vector.solutions.intervention.monitor.ITNDistributionViewQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class ITNDistributionExcelView extends ITNDistributionExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256860224442L;
  
  private List<Term>        targetGroups;
  private List<Integer>     targetGroupAmounts;
  
  public ITNDistributionExcelView()
  {
    super();
    targetGroups = new LinkedList<Term>();
    targetGroupAmounts = new LinkedList<Integer>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    ITNDistributionView view = new ITNDistributionView();
    
    view.setPerson(searchForRecipient());
    view.setFacility(this.getFacility().getGeoId());
    view.setDistributionDate(this.getDistributionDate());
    view.setBatchNumber(this.getBatchNumber());
    ITNDistributionViewQuery search = ITNDistributionView.searchHistory(view);
    OIterator<? extends ITNDistributionView> iterator = search.getIterator();
    if (iterator.hasNext())
    {
      view = ITNDistribution.lockView(iterator.next().getConcreteId());
    }
    iterator.close();
    
    view.setService(Term.validateByDisplayLabel(this.getService(), ITNDistributionView.getServiceMd()));
    view.setNet(Term.validateByDisplayLabel(this.getNet(), ITNDistributionView.getNetMd()));
    view.setNumberSold(this.getNumberSold());
    view.setCurrencyReceived(this.getCurrencyReceived());
    view.setDistributorName(this.getDistributorName());
    view.setDistributorSurname(this.getDistributorSurname());
    
    ITNDistributionTargetGroup[] existingGroups = view.getDistributionTargetGroups();
    ITNDistributionTargetGroup[] targetGroupArray = new ITNDistributionTargetGroup[targetGroups.size()];
    for (int i = 0; i < targetGroupArray.length; i++)
    {
      // Default to a new record
      String termId = targetGroups.get(i).getId();
      targetGroupArray[i] = new ITNDistributionTargetGroup(view.getConcreteId(), termId);
      
      // If a record already exists, use it instead
      for (ITNDistributionTargetGroup existing : existingGroups)
      {
        // Use IDs to avoid cost of instantiating the whole object
        if (existing.getChildId().equals(termId))
        {
          if (!existing.isNew())
          {
            existing.lock();
          }
          targetGroupArray[i] = existing;
        }
      }
      
      // Set the amount
      if (i < targetGroupAmounts.size())
      {
        targetGroupArray[i].setAmount((targetGroupAmounts.get(i)));
      }
    }
    
    view.applyAll(targetGroupArray);
  }
  
  private Person searchForRecipient()
  {
    String firstName = this.getRecipientFirstName();
    String lastName = this.getRecipientLastName();
    Date dob = this.getRecipientDOB();
    Term sex = Term.validateByDisplayLabel(this.getRecipientSex(), PersonView.getSexMd());
    
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
    if (sex!=null)
    {
      query.WHERE(query.getSex().EQ(sex));
    }
    
    if (query.getCount()>1)
    {
      AmbiguousRecipientProblem problem = new AmbiguousRecipientProblem();
      problem.setFirstName(firstName);
      problem.setLastName(lastName);
      problem.setDob(dob);
      problem.setLocale(Session.getCurrentLocale());

      problem.throwIt();
      return null;
    }
    
    Person person;
    OIterator<? extends Person> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      person = iterator.next();
      person.lock();
    }
    else
    {
      person = new Person();
      person.setFirstName(firstName);
      person.setLastName(lastName);
      person.setDateOfBirth(dob);
      person.setSex(sex);
      person.apply();
    }
    iterator.close();
    
    return person;
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(DISTRIBUTIONDATE);
    list.add(BATCHNUMBER);
    list.add(RECIPIENTFIRSTNAME);
    list.add(RECIPIENTLASTNAME);
    list.add(RECIPIENTDOB);
    list.add(RECIPIENTSEX);
    list.add(SERVICE);
    list.add(NET);
    list.add(NUMBERSOLD);
    list.add(CURRENCYRECEIVED);
    list.add(DISTRIBUTORNAME);
    list.add(DISTRIBUTORSURNAME);
    return list;
  }

  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    context.addListener(new ITNDistributionListener());
    context.addListener(createExcelGeoListener(importer));
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener(null));
    exporter.addListener(new ITNDistributionListener());
  }
  
  private static DynamicGeoColumnListener createExcelGeoListener(ExcelImportManager importer)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(HealthFacility.CLASS));
    return new DynamicGeoColumnListener(CLASS, FACILITY, builder);
  }

  public void addTargetGroup(Term grid, Integer amount)
  {
    targetGroups.add(grid);
    targetGroupAmounts.add(amount);
  }
}
