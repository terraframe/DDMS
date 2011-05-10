package dss.vector.solutions.ontology;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.RoleDAOIF;
import com.runwaysdk.business.rbac.UserDAO;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.Person;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.irs.AreaStandards;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.InsecticideBrandConcentrationQualifier;
import dss.vector.solutions.irs.InsecticideBrandQuery;
import dss.vector.solutions.irs.InsecticideBrandUnitQualifier;
import dss.vector.solutions.irs.InsecticideBrandUse;
import dss.vector.solutions.irs.InsecticideNozzle;
import dss.vector.solutions.irs.Nozzle;
import dss.vector.solutions.irs.TargetUnit;

public class PostOntologySetup
{
  public static final String USERNAME          = "ddms";

  public static final String DELTAMETHRIN      = "MIRO:10000133";

  public static final String DDT               = "MIRO:10000157";

  public static final String K_OTHERINE_WG_250 = "MDSS:0000476";

  public static final String DDT_WP_75         = "MDSS:0000477";

  /**
   * @param args[0] Attribute Roots excel spread sheet
   * @param args[1] Geo Hierarchy Universal spread sheet
   */
  @Request
  public static void main(String[] args) throws Exception
  {
    // Setup root values
    AttributeRootImporter.main(new String[] { args[0] });

    setupMDSSUser();

    setupApplicationRate();

    setGeoUniversals(args[1]);
    
    CacheShutdown.shutdown();
  }

  @Transaction
  private static void setupMDSSUser()
  {
    if (!PostOntologySetup.isDDMSDefined())
    {
      String UNKNOWN = "MDSS:0000320";

      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.set(Calendar.YEAR, 2009);
      calendar.set(Calendar.MONTH, 1);
      calendar.set(Calendar.DAY_OF_YEAR, 1);

      Person person = new Person();
      person.setDateOfBirth(calendar.getTime());
      person.setFirstName("DDMS");
      person.setLastName("User");
      person.setSex(Term.getByTermId(UNKNOWN));
      person.apply();

      MDSSUser user = new MDSSUser();
      user.setUsername(USERNAME);
      user.setPassword(USERNAME);
      user.setSessionLimit(25);
      user.setPerson(person);
      user.apply();

      RoleDAO.findRole(RoleDAOIF.ADMIN_ROLE).assignMember(UserDAO.get(user.getId()));

      person.setUserDelegate(user);
      person.apply();
    }
  }

  private static boolean isDDMSDefined()
  {
    try
    {
      UserDAO.findUser(USERNAME);

      return true;
    }
    catch (DataNotFoundException e)
    {
      return false;
    }

  }

  @Transaction
  private static void setupApplicationRate()
  {
    if (!PostOntologySetup.hasConfiguration())
    {
      final Disease DEFAULT_DISEASE = Disease.getMalaria();

      InsecticideBrand deltamethrin = new InsecticideBrand();
      deltamethrin.setProductName(Term.getByTermId(K_OTHERINE_WG_250));
      deltamethrin.addInsecticideUse(InsecticideBrandUse.IRS);
      deltamethrin.setDisease(DEFAULT_DISEASE);
      deltamethrin.setActiveIngredient(Term.getByTermId(DELTAMETHRIN));
      deltamethrin.setConcentrationQuantifier(new BigDecimal("25"));
      deltamethrin.addConcentrationQualifier(InsecticideBrandConcentrationQualifier.PERCENT);
      deltamethrin.setUnitQuantifier(new BigDecimal(20.00));
      deltamethrin.addUnitQualifier(InsecticideBrandUnitQualifier.GRAMS);
      deltamethrin.setUnitsPerApplication(1);
      deltamethrin.setEnabled(true);
      deltamethrin.apply();

      InsecticideBrand ddt = new InsecticideBrand();
      ddt.setProductName(Term.getByTermId(DDT_WP_75));
      ddt.addInsecticideUse(InsecticideBrandUse.IRS);
      ddt.setDisease(DEFAULT_DISEASE);
      ddt.setActiveIngredient(Term.getByTermId(DDT));
      ddt.setConcentrationQuantifier(new BigDecimal("75"));
      ddt.addConcentrationQualifier(InsecticideBrandConcentrationQualifier.PERCENT);
      ddt.setUnitQuantifier(new BigDecimal(670.00));
      ddt.addUnitQualifier(InsecticideBrandUnitQualifier.GRAMS);
      ddt.setUnitsPerApplication(1);
      ddt.setEnabled(true);
      ddt.apply();

      Nozzle n8001E = new Nozzle();
      n8001E.setDisease(DEFAULT_DISEASE);
      n8001E.setDisplayLabel("8001 E");
      n8001E.setRatio(new BigDecimal(2.00));
      n8001E.setEnabled(true);
      n8001E.apply();

      Nozzle n8002E = new Nozzle();
      n8002E.setDisease(DEFAULT_DISEASE);
      n8002E.setDisplayLabel("8002 E");
      n8002E.setRatio(new BigDecimal(1.00));
      n8002E.setEnabled(true);
      n8002E.apply();

      InsecticideNozzle configuration = new InsecticideNozzle(deltamethrin, n8001E);
      configuration.setDisease(DEFAULT_DISEASE);
      configuration.setEnabled(true);
      configuration.apply();

      InsecticideNozzle configuration2 = new InsecticideNozzle(ddt, n8002E);
      configuration2.setDisease(DEFAULT_DISEASE);
      configuration2.setEnabled(true);
      configuration2.apply();

      AreaStandards standards = new AreaStandards();
      standards.setDisease(DEFAULT_DISEASE);
      standards.setUnitNozzleAreaCoverage(250.0F);
      standards.setRoom(25F);
      standards.setStructureArea(100F);
      standards.setHousehold(300F);
      standards.addTargetUnit(TargetUnit.ROOM);
      standards.apply();
    }
  }

  public static boolean hasConfiguration()
  {
    InsecticideBrandQuery query = new InsecticideBrandQuery(new QueryFactory());
    query.WHERE(query.getProductName().EQ(Term.getByTermId(K_OTHERINE_WG_250)));

    return ( query.getCount() != 0 );
  }

  @Transaction
  private static void setGeoUniversals(String excel)
  {
    try
    {
      InputStream is = new FileInputStream(excel);
      HSSFWorkbook wb = new HSSFWorkbook(is);
      HSSFSheet sheet = wb.getSheetAt(0); // Use first sheet

      int rowCount = 1; // Start at second row
      HSSFRow row = sheet.getRow(rowCount++);

      while (row != null && row.getCell(0) != null && row.getCell(0).getCellType() != HSSFCell.CELL_TYPE_BLANK)
      {
        String universalName = ExcelUtil.getString(row.getCell(0));
        String termId = ExcelUtil.getString(row.getCell(6));

        if (termId != null && termId.length() > 0)
        {
          Term term = Term.getByTermId(termId);

          GeoHierarchy geoHierarchy = GeoHierarchy.getGeoHierarchyFromLabel(universalName);
          geoHierarchy.setTerm(term);
          geoHierarchy.apply();
        }
        
        row = sheet.getRow(rowCount++);
      }
    }
    catch (Exception e)
    {
      throw new RuntimeException();
    }
  }

}
