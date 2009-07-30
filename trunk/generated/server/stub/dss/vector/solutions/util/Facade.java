package dss.vector.solutions.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.terraframe.mojo.SystemException;
import com.terraframe.mojo.constants.MdBusinessInfo;
import com.terraframe.mojo.constants.MdViewInfo;
import com.terraframe.mojo.query.ColumnInfo;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.system.Roles;
import com.terraframe.mojo.system.metadata.MdClassQuery;
import com.terraframe.mojo.system.metadata.MdElementQuery;

import dss.vector.solutions.geo.GeoSynonym;

public abstract class Facade extends FacadeBase implements com.terraframe.mojo.generation.loader.Reloadable
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
    valueQuery.SELECT(elementQuery.getId());
    valueQuery.WHERE(elementQuery.getIsAbstract().EQ(true));

    MdClassQuery classQuery = new MdClassQuery(queryFactory);
    classQuery.WHERE(classQuery.getPackageName().LIKEi("dss.vector.solutions.%"));
    Condition or = OR.get(classQuery.getType().EQ(MdBusinessInfo.CLASS), classQuery.getType().EQ(MdViewInfo.CLASS));
    classQuery.WHERE(or);
    classQuery.WHERE(classQuery.NOT_IN(classQuery.getId(), valueQuery));
    classQuery.ORDER_BY_ASC(classQuery.getDisplayLabel().currentLocale());

    return classQuery;
  }

  public static Roles[] getMDSSRoles()
  {
    return new Roles[]
    {
      Roles.findRoleByName("GUIVisibility"),
      Roles.findRoleByName("entomologist"),
      Roles.findRoleByName("dataCapturer"),
      Roles.findRoleByName("manager"),
      Roles.findRoleByName("MDSS"),
      Roles.findRoleByName("mdssCoordinator"),
      Roles.findRoleByName("operationalManager")
    };
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

    return com.terraframe.mojo.facade.Facade.importExcelFile(Session.getCurrentSession().getId(), inputStream, type, listenerMethod, params);
  }


}
