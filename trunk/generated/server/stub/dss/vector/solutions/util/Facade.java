package dss.vector.solutions.util;

import java.util.List;

import com.runwaysdk.constants.MdBusinessInfo;
import com.runwaysdk.constants.MdViewInfo;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.Roles;
import com.runwaysdk.system.metadata.MdClassQuery;
import com.runwaysdk.system.metadata.MdElementQuery;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.geo.UnknownGeoEntity;

public abstract class Facade extends FacadeBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236374191440L;

  public Facade()
  {
    super();
  }

  public static MdClassQuery getMDSSClasses()
  {
    QueryFactory queryFactory = new QueryFactory();
    MdElementQuery elementQuery = new MdElementQuery(queryFactory);
    elementQuery.WHERE(elementQuery.getIsAbstract().EQ(true));

    ValueQuery valueQuery = queryFactory.valueQuery();
    valueQuery.SELECT(elementQuery.getId("id"));
    valueQuery.WHERE(elementQuery.getIsAbstract().EQ(true));

    MdClassQuery classQuery = new MdClassQuery(queryFactory);
    classQuery.WHERE(classQuery.getPackageName().LIKEi("dss.vector.solutions.%"));
    Condition or = OR.get(classQuery.getType().EQ(MdBusinessInfo.CLASS), classQuery.getType().EQ(MdViewInfo.CLASS));
    classQuery.WHERE(or);
    classQuery.WHERE(classQuery.getId().SUBSELECT_NOT_IN(valueQuery.get("id")));
    classQuery.ORDER_BY_ASC(classQuery.getDisplayLabel().localize());

    return classQuery;
  }

  public static Roles[] getMDSSRoles()
  {
    return new Roles[]
    {
      Roles.findRoleByName(MDSSRoleInfo.GUI_VISIBILITY),
      Roles.findRoleByName(MDSSRoleInfo.ENTOMOLOGIST),
      Roles.findRoleByName(MDSSRoleInfo.DATACAPTURER),
      Roles.findRoleByName(MDSSRoleInfo.MANAGER),
      Roles.findRoleByName(MDSSRoleInfo.MDSS),
      Roles.findRoleByName(MDSSRoleInfo.MDSS_CORRDINATOR),
      Roles.findRoleByName(MDSSRoleInfo.OPERATIONAL_MANAGER),
      Roles.findRoleByName(MDSSRoleInfo.STOCK_STAFF)
    };
  }

  /**
   * Checks the geo entity hierarchy in the excel file and tries to find synonym matches.  Each geo universal column is checked
   * in order of depth, starting from lowest to highest.
   *
   *
   * @param inputStream
   * @param type
   * @return
   */
  public static dss.vector.solutions.geo.UnknownGeoEntity[] checkSynonyms(java.io.InputStream inputStream, java.lang.String type)
  {
    GeoEntitySearcher geoEntitySearcher = new GeoEntitySearcher();

    List<UnknownGeoEntity> unknownGeoEntityList = geoEntitySearcher.checkExcelGeoHierarchy(inputStream);

    UnknownGeoEntity[] unknownGeoEntityArray = new UnknownGeoEntity[unknownGeoEntityList.size()];

    unknownGeoEntityList.toArray(unknownGeoEntityArray);

    return unknownGeoEntityArray;
  }

  /**
   * Preprocesses the geo entity names to verify they are recognized.  If they are not recognized and no synonyms have been assigned,
   * then synonyms are looked up and the user is prompeted with a list of possible synonym matches.
   *
   * @param inputStream
   * @param type
   * @param listenerMethod
   * @param params
   * @return
   */
  public static java.io.InputStream importExcelFile(java.io.InputStream inputStream, java.lang.String type, java.lang.String listenerMethod, java.lang.String[] params)
  {

//    GeoSynonym.checkExcelGeoHierarchy(inputStream);

    return com.runwaysdk.facade.Facade.importExcelFile(Session.getCurrentSession().getId(), inputStream, type, listenerMethod, params);
  }


}
