package dss.vector.solutions.intervention.monitor;

import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.attributes.InvalidReferenceException;
import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.query.AttributeEnumeration;
import com.runwaysdk.query.AttributeLocal;
import com.runwaysdk.query.AttributeReference;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.HealthFacility;

public class IndividualIPTView extends IndividualIPTViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1253643992260L;

  public IndividualIPTView()
  {
    super();
  }

  public void populateView(IndividualIPT concrete)
  {
    this.setConcreteId(concrete.getId());

    if (concrete.getFacility() != null)
    {
      this.setFacility(concrete.getFacility().getGeoId());
      this.setFacilityName(concrete.getFacility().getEntityName());
    }

    this.setIptCase(concrete.getIptCase());
    this.setServiceDate(concrete.getServiceDate());
    this.setPatientType(concrete.getPatientType());
    this.setIsANCVisit(concrete.getIsANCVisit());
    this.setVisitNumber(concrete.getVisitNumber());
    this.setDoseNumber(concrete.getDoseNumber());
    this.setDoseType(concrete.getDoseType());
    this.setReceivedSupplement(concrete.getReceivedSupplement());
    this.setReceivedITN(concrete.getReceivedITN());
    this.setNumberOfReceivedITNs(concrete.getNumberOfReceivedITNs());
    this.setAdministratorName(concrete.getAdministratorName());
    this.setAdministratorSurname(concrete.getAdministratorSurname());
  }

  private void populateConcrete(IndividualIPT concrete)
  {
    String facility = this.getFacility();

    if (facility != null && !facility.equals(""))
    {
      GeoEntity geoEntity = HealthFacility.searchByGeoId(facility);

      if (geoEntity instanceof HealthFacility)
      {
        concrete.setFacility((HealthFacility) geoEntity);
      }
      else
      {
        // No results = the geoId is invalid.
        throw new InvalidReferenceException("[" + facility + "] is not a valid Health Facility geo id.", (MdAttributeReferenceDAOIF) IndividualIPT.getFacilityMd());
      }
    }

    concrete.setIptCase(this.getIptCase());
    concrete.setServiceDate(this.getServiceDate());
    concrete.setPatientType(this.getPatientType());
    concrete.setIsANCVisit(this.getIsANCVisit());
    concrete.setVisitNumber(this.getVisitNumber());
    concrete.setDoseNumber(this.getDoseNumber());
    concrete.setDoseType(this.getDoseType());
    concrete.setReceivedSupplement(this.getReceivedSupplement());
    concrete.setReceivedITN(this.getReceivedITN());
    concrete.setNumberOfReceivedITNs(this.getNumberOfReceivedITNs());
    concrete.setAdministratorName(this.getAdministratorName());
    concrete.setAdministratorSurname(this.getAdministratorSurname());
  }

  private void buildAttributeMap(IndividualIPT concrete)
  {
    new AttributeNotificationMap(concrete, IndividualIPT.FACILITY, this, IndividualIPTView.FACILITY);
    new AttributeNotificationMap(concrete, IndividualIPT.SERVICEDATE, this, IndividualIPTView.SERVICEDATE);
    new AttributeNotificationMap(concrete, IndividualIPT.IPTCASE, this, IndividualIPTView.IPTCASE);
    new AttributeNotificationMap(concrete, IndividualIPT.PATIENTTYPE, this, IndividualIPTView.PATIENTTYPE);
    new AttributeNotificationMap(concrete, IndividualIPT.ISANCVISIT, this, IndividualIPTView.ISANCVISIT);
    new AttributeNotificationMap(concrete, IndividualIPT.VISITNUMBER, this, IndividualIPTView.VISITNUMBER);
    new AttributeNotificationMap(concrete, IndividualIPT.DOSENUMBER, this, IndividualIPTView.DOSENUMBER);
    new AttributeNotificationMap(concrete, IndividualIPT.DOSETYPE, this, IndividualIPTView.DOSETYPE);
    new AttributeNotificationMap(concrete, IndividualIPT.RECEIVEDSUPPLEMENT, this, IndividualIPTView.RECEIVEDSUPPLEMENT);
    new AttributeNotificationMap(concrete, IndividualIPT.RECEIVEDITN, this, IndividualIPTView.RECEIVEDITN);
    new AttributeNotificationMap(concrete, IndividualIPT.NUMBEROFRECEIVEDITNS, this, IndividualIPTView.NUMBEROFRECEIVEDITNS);
    new AttributeNotificationMap(concrete, IndividualIPT.ADMINISTRATORNAME, this, IndividualIPTView.ADMINISTRATORNAME);
    new AttributeNotificationMap(concrete, IndividualIPT.ADMINISTRATORSURNAME, this, IndividualIPTView.ADMINISTRATORSURNAME);
  }

  @Override
  public void apply()
  {
    IndividualIPT concrete = new IndividualIPT();

    if (this.hasConcrete())
    {
      concrete = IndividualIPT.get(this.getConcreteId());
    }

    // Build the attribute map between IndividualIPT and
    // IndividualIPTView for error handling
    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Override
  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      IndividualIPT.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  public static IndividualIPTViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    IndividualIPTViewQuery query = new IndividualIPTViewQuery(new QueryFactory());

    if (sortAttribute == null)
    {
      sortAttribute = IndividualIPTView.ADMINISTRATORNAME;
    }

    Selectable attribute = query.getComponentQuery().getSelectableRef(sortAttribute);

    if (attribute instanceof AttributeEnumeration)
    {
      attribute = ( (AttributeEnumeration) attribute ).aCharacter("enumName");
    }
    else if (attribute instanceof AttributeReference)
    {
      attribute = query.getComponentQuery().getSelectableRef(IndividualIPTView.ADMINISTRATORNAME);
    }
    else if (attribute instanceof AttributeLocal)
    {
      attribute = ( (AttributeLocal) attribute ).localize();
    }

    if (isAscending)
    {
      query.ORDER_BY_ASC((SelectablePrimitive) attribute, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC((SelectablePrimitive) attribute, sortAttribute);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;
  }
  
  public static IndividualIPTViewQuery getCaseInstances(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, String caseId)
  {
    IndividualIPTViewQuery query = new IndividualIPTViewQuery(new QueryFactory());
    query.WHERE(query.getIptCase().EQ(caseId));

    if (sortAttribute == null)
    {
      sortAttribute = IPTCASE;
    }

    Selectable attribute = query.getComponentQuery().getSelectableRef(sortAttribute);

    if (attribute instanceof AttributeEnumeration)
    {
      attribute = ( (AttributeEnumeration) attribute ).aCharacter("enumName");
    }
    else if (attribute instanceof AttributeReference)
    {
      attribute = ( (AttributeReference) attribute ).aCharacter("keyName");
    }
    else if (attribute instanceof AttributeLocal)
    {
      attribute = ( (AttributeLocal) attribute ).localize();
    }

    if (isAscending)
    {
      query.ORDER_BY_ASC((SelectablePrimitive) attribute, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC((SelectablePrimitive) attribute, sortAttribute);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;
  }

}
