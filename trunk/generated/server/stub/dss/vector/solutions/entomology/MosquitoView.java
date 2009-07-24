package dss.vector.solutions.entomology;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.terraframe.mojo.business.generation.GenerationUtil;
import com.terraframe.mojo.business.rbac.Operation;
import com.terraframe.mojo.constants.MdAttributeConcreteInfo;
import com.terraframe.mojo.constants.MdAttributeVirtualInfo;
import com.terraframe.mojo.dataaccess.MdAttributeConcreteDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeVirtualDAOIF;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.metadata.MdAttributeConcreteDAO;
import com.terraframe.mojo.dataaccess.metadata.MdAttributeDAO;
import com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO;
import com.terraframe.mojo.dataaccess.metadata.MdViewDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.generation.loader.LoaderDecorator;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.session.SessionFacade;
import com.terraframe.mojo.session.SessionIF;
import com.terraframe.mojo.system.metadata.MdAttributeBoolean;
import com.terraframe.mojo.system.metadata.MdAttributeVirtual;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.entomology.assay.AssayComparator;
import dss.vector.solutions.entomology.assay.AssayTestResult;
import dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResult;
import dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult;
import dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult;
import dss.vector.solutions.mo.AbstractTerm;
import dss.vector.solutions.mo.InfectivityMethodology;
import dss.vector.solutions.mo.InsecticideMethodology;
import dss.vector.solutions.mo.MolecularAssayResult;

public class MosquitoView extends MosquitoViewBase implements Reloadable
{
  private static final long serialVersionUID = 1235599942174L;

//  private static boolean    viewGenerated    = false;

  public MosquitoView()
  {
    super();
  }

  @Transaction
  public void apply()
  {
    Mosquito mosquito = new Mosquito();

    if (this.hasConcreteId())
    {
      mosquito = Mosquito.lock(this.getMosquitoId());
    }

    this.populateConcrete(mosquito);

    mosquito.apply();

    try
    {
      applyAssays(mosquito);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }

    this.populateView(mosquito);
  }

  private boolean hasConcreteId()
  {
    return this.getMosquitoId() != null && !this.getMosquitoId().equals("");
  }

  public void populateView(Mosquito mosquito)
  {
    this.setSpecie(mosquito.getSpecie());
    this.setCollection(mosquito.getCollection());
    this.setGeneration(mosquito.getGeneration());
    this.setIsofemale(mosquito.getIsofemale());
    this.setIdentificationMethod(mosquito.getIdentificationMethod());
    this.setTestDate(mosquito.getTestDate());
    this.setMosquitoId(mosquito.getId());
    this.setSampleId(mosquito.getSampleId());
    this.clearSex();

    for (Sex sex : mosquito.getSex())
    {
      this.addSex(sex);
    }

    try
    {
      this.setAssays(mosquito.getTestResults());
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  private void populateConcrete(Mosquito mosquito)
  {
    mosquito.setIdentificationMethod(this.getIdentificationMethod());
    mosquito.setIsofemale(this.getIsofemale());
    mosquito.setGeneration(this.getGeneration());
    mosquito.setSpecie(this.getSpecie());
    mosquito.setTestDate(this.getTestDate());
    mosquito.setCollection(this.getCollection());
    mosquito.setSampleId(this.getSampleId());
    mosquito.clearSex();

    for (Sex sex : this.getSex())
    {
      mosquito.addSex(sex);
    }
  }

  public void delete()
  {
    throw new RuntimeException("This method should not be invoked");
  }

  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      Mosquito.get(this.getMosquitoId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return hasConcreteId();
  }

  /**
   * @return A mapping between a virtual attribute and the class of its concrete
   *         attribute. The class must extend from AssayTestResult.
   */
  @SuppressWarnings("unchecked")
  public static Map<Class<AssayTestResult>, MdAttributeVirtualDAOIF> getAssayMap()
  {
    Map<Class<AssayTestResult>, MdAttributeVirtualDAOIF> map = new HashMap<Class<AssayTestResult>, MdAttributeVirtualDAOIF>();

    for (MdAttributeDAOIF mdAttribute : MdViewDAO.getMdViewDAO(CLASS).definesAttributes())
    {
      // We want to return a map for all virtual attributes which
      if (mdAttribute instanceof MdAttributeVirtualDAOIF)
      {
        MdAttributeVirtualDAOIF virtual = (MdAttributeVirtualDAOIF) mdAttribute;
        String concreteId = virtual.getValue(MdAttributeVirtualInfo.MD_ATTRIBUTE_CONCRETE);
        MdAttributeConcreteDAOIF concrete = MdAttributeConcreteDAO.get(concreteId);

        MdBusinessDAOIF mdClass = MdBusinessDAO.get(concrete.getValue(MdAttributeConcreteInfo.DEFINING_MD_CLASS));

        Class<?> c = LoaderDecorator.load(mdClass.definesType());

        // We filter all abstract classes because we do not want to add the
        // virtual attributes which represent test methodology and these
        // virtual attributes are defined on abstract classes
        if (AssayTestResult.class.isAssignableFrom(c) && !Modifier.isAbstract(c.getModifiers()))
        {
          map.put((Class<AssayTestResult>) c, virtual);
        }
      }
    }

    return map;
  }

  private void applyAssays(Mosquito mosquito) throws InstantiationException, IllegalAccessException, IllegalArgumentException, SecurityException, InvocationTargetException, NoSuchMethodException
  {
    Map<Class<AssayTestResult>, MdAttributeVirtualDAOIF> assayMap = MosquitoView.getAssayMap();

    for (Class<AssayTestResult> c : assayMap.keySet())
    {
      // Get the result
      MdAttributeVirtualDAOIF mdAttribute = assayMap.get(c);
      String attributeName = GenerationUtil.upperFirstCharacter(mdAttribute.getAccessorName());

      String resultName = "get" + attributeName;
      String methodName = "get" + attributeName + "Method";

      Object testResult = MosquitoView.class.getMethod(resultName).invoke(this);
      Object testMethod = null;
      Object result = mosquito.getTestResult(c);

      try
      {
        testMethod = MosquitoView.class.getMethod(methodName).invoke(this);
      }
      catch (NoSuchMethodException e)
      {
        testMethod = null;
      }

      if (result == null)
      {
        result = c.newInstance();
      }
      else
      {
        c.getMethod("lock").invoke(result);
      }

      c.getMethod("setMosquito", Mosquito.class).invoke(result, mosquito);

      if (testResult != null)
      {

        c.getMethod("setTestResult", testResult.getClass()).invoke(result, testResult);
      }

      if (testMethod != null)
      {
        c.getMethod("setTestMethod", testMethod.getClass()).invoke(result, testMethod);
      }

      c.getMethod("apply").invoke(result);

    }
  }

  public void setAssays(AssayTestResult[] results) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
  {
    Map<Class<AssayTestResult>, MdAttributeVirtualDAOIF> assayMap = MosquitoView.getAssayMap();

    for (AssayTestResult result : results)
    {
      Class<? extends AssayTestResult> c = result.getClass();
      MdAttributeVirtualDAOIF mdAttribute = assayMap.get(c);

      if (mdAttribute != null)
      {
        String attributeName = GenerationUtil.upperFirstCharacter(mdAttribute.getAccessorName());

        Object testResult = result.getTestResult();
        AbstractTerm testMethod = result.getTestMethod();

        String resultName = "set" + attributeName;
        try
        {
          MosquitoView.class.getMethod(resultName, testResult.getClass()).invoke(this, testResult);

          if (result.hasTestMethod())
          {
            String methodName = "set" + attributeName + "Method";
            MosquitoView.class.getMethod(methodName, testMethod.getClass()).invoke(this, testMethod);
          }
        }
        catch (Exception e)
        {
          // TODO: handle exception
        }
      }
    }
  }

  @Transaction
  public static MosquitoView[] saveAll(MosquitoView[] array)
  {
    for (MosquitoView view : array)
    {
      view.apply();
    }

    return array;
  }

  public static MdAttributeVirtual[] getAccessors(String className)
  {
    Set<MdAttributeVirtual> list = new TreeSet<MdAttributeVirtual>(new AssayComparator());
    Map<Class<AssayTestResult>, MdAttributeVirtualDAOIF> map = MosquitoView.getAssayMap();
    Class<?> assayClass = LoaderDecorator.load(className);

    for (Class<AssayTestResult> key : map.keySet())
    {
      if (assayClass.isAssignableFrom(key))
      {
        //Ensure that an assay attribute has read permissions
        
        SessionIF session = Session.getCurrentSession();
        String attributeId = map.get(key).getId();

        if(SessionFacade.checkAttributeAccess(session.getId(), Operation.READ, (MdAttributeDAO) MdAttributeDAO.get(attributeId)))
        {
          list.add(MdAttributeVirtual.get(attributeId));
        }
      }
    }
    return list.toArray(new MdAttributeVirtual[list.size()]);
  }

  // This function is to get the column definitions for the query.
  public static String getAssayColumns(String superAssayClass)
  {

    // MosquitoView.createDatabaseView();

//    MosquitoView view = new MosquitoView();
    String s = "[";
    MdAttributeVirtual[] mdArray = MosquitoView.getAccessors(superAssayClass);
    for (MdAttributeVirtual md : mdArray)
    {

      String acc = md.getAttributeName();
      String result = acc.toLowerCase() + "_defaultLocale";
      String method = acc.toLowerCase() + "testmethod_defualtLocale";
      String type = "sqlcharacter";

//      String concreteId = md.getValue(MdAttributeVirtualInfo.MD_ATTRIBUTE_CONCRETE);
//      MdAttributeConcreteDAOIF concrete = MdAttributeConcreteDAO.get(concreteId);

//      MdBusinessDAOIF mdClass = MdBusinessDAO.get(concrete.getValue(MdAttributeConcreteInfo.DEFINING_MD_CLASS));

      s += "{";
      s += "viewAccessor:'" + acc + "',";
      s += "attributeName:'" + result + "',";
      s += "type:'"+type+"',";
      // s += "dtoType:'" + md.getMdAttributeConcrete().getType() + "',";
      s += "displayLabel:'" + md.getMdAttributeConcrete().getDisplayLabel() + "'";
      s += "},\n";

      try
      {
        String testMdGetter = acc + "Method";
//        String testMethodID = view.getMdAttributeDAO(testMdGetter).getId();

        s += "{";
        s += "viewAccessor:'" + testMdGetter + "',";
        s += "attributeName:'" + method + "',";
        s += "type:'sqlcharacter',";
        // FIXME
        // s += "displayLabel:'" + md.getMdAttributeConcrete().getDisplayLabel()
        s += "displayLabel:'" + md.getMdAttributeConcrete().getDisplayLabel() + " Test Method'";
        s += "},\n";

      }
      catch (Exception e)
      {
        System.out.print(e);
      }

    }
    return s + "]";
  }

  @Transaction
  /*
   * public static void createDatabaseView() { if(!MosquitoView.viewGenerated) {
   * Database.parseAndExecute(getAssayViewSQL()); MosquitoView.viewGenerated =
   * true; } }
   *
   * public static void createAssaySnapshot() { String sql =
   * "CREATE OR REPLACE TABLE MOSQUITO_ASSAY_SNAPSHOT AS SELECT * FROM MOSQUITO_ASSAY_VIEW"
   * ; Database.query(sql); }
   *
   * public static String getAssayViewSQL() { String sql =
   * "CREATE OR REPLACE VIEW MOSQUITO_ASSAY_VIEW AS SELECT A.*, B.*, C.* FROM\n"
   * ; sql += "(" + MosquitoView.getInfectivityAssayViewSQL() + ")AS A,\n"; sql
   * += "(" + MosquitoView.getTargetSiteAssayViewSQL() + ")AS B,\n"; sql += "("
   * + MosquitoView.getMetabolicAssayViewSQL() + ")AS C\n"; sql +=
   * "WHERE infectivity_mosquito_id = targetsite_mosquito_id AND targetsite_mosquito_id = metabolic_mosquito_id"
   * ;
   *
   * return sql; }
   */
  public static String getTempTableSQL(String klass, String tableName)
  {
    String sql = "DROP TABLE IF EXISTS "+tableName+";\n";
    sql += "CREATE TEMP TABLE "+tableName+" AS " + MosquitoView.getAssayViewSQL(klass) + ";\n";
    return sql;
  }

  public static String getAssayViewSQL(String klass)
  {
    if(klass.equals(InfectivityAssayTestResult.CLASS)) return MosquitoView.getInfectivityAssayViewSQL();
    if(klass.equals(TargetSiteAssayTestResult.CLASS)) return MosquitoView.getTargetSiteAssayViewSQL();
    if(klass.equals(MetabolicAssayTestResult.CLASS)) return MosquitoView.getMetabolicAssayViewSQL();
    return "";
  }

  public static String getInfectivityAssayViewSQL()
  {
    String select = "SELECT mosquito.id AS mosquito_id,\n";
    String from = "FROM  " + MdBusiness.getMdBusiness(Mosquito.CLASS).getTableName() + " mosquito, ";
    String where = "";
    MdAttributeVirtual[] mdArray = MosquitoView.getAccessors(InfectivityAssayTestResult.CLASS);
    int i = 0;

    for (MdAttributeVirtual md : mdArray)
    {
      String acc = md.getAttributeName();
      String result = acc.toLowerCase();
      String resultLocalized = acc.toLowerCase() + "_defaultLocale";
      String method = acc.toLowerCase() + "testmethod";
      String methodLocalized = acc.toLowerCase() + "testmethod_defualtLocale";

      String concreteId = md.getValue(MdAttributeVirtualInfo.MD_ATTRIBUTE_CONCRETE);
      MdAttributeConcreteDAOIF concrete = MdAttributeConcreteDAO.get(concreteId);
      MdBusinessDAOIF mdClass = MdBusinessDAO.get(concrete.getValue(MdAttributeConcreteInfo.DEFINING_MD_CLASS));

      String testResultTable = MdBusiness.getMdBusiness(InfectivityAssayTestResult.CLASS).getTableName();
      String testMethodTable = MdBusiness.getMdBusiness(InfectivityMethodology.CLASS).getTableName();
      String abstractTermTable = MdBusiness.getMdBusiness(AbstractTerm.CLASS).getTableName();
      String abstractTermDisplayLabelTable = "abstracttermdisplaylabel";

      String positveLabel = ( (MdAttributeBoolean) md.getMdAttributeConcrete() ).getPositiveDisplayLabel().getDefaultLocale();
      String negativeLabel = ( (MdAttributeBoolean) md.getMdAttributeConcrete() ).getNegativeDisplayLabel().getDefaultLocale();

      select +=  "(CASE WHEN (" + mdClass.getTableName() + ".testresult = 1) THEN 'true'\n";
      select +=  "WHEN (" + mdClass.getTableName() + ".testresult = 0) THEN 'false'\n";
      select +=  "ELSE '' END) AS " + result + ",\n";

      select += "(SELECT tr.testmethod  \n";
      select += "FROM " + testResultTable + "  tr LEFT JOIN " + testMethodTable + " tm on tr.testmethod = tm.id\n";
      select += "WHERE tr.id = " + mdClass.getTableName() + ".id) AS " + method +" ,\n";

      select +=  "(CASE WHEN (" + mdClass.getTableName() + ".testresult = 1) THEN '" + positveLabel + "'\n";
      select +=  "WHEN (" + mdClass.getTableName() + ".testresult = 0) THEN '" + negativeLabel + "'\n";
      select +=  "ELSE '' END) AS " + resultLocalized + ",\n";

      select += "(SELECT label.defaultLocale \n";
      select += "FROM " + testResultTable + "  tr LEFT JOIN " + testMethodTable + " tm on tr.testmethod = tm.id\n";
      select += "LEFT JOIN " + abstractTermTable + " term ON tm.id = term.id \n";
      select += "LEFT JOIN " + abstractTermDisplayLabelTable + " label ON term.displayLabel = label.id\n";
      select += "WHERE tr.id = " + mdClass.getTableName() + ".id) AS " + methodLocalized + ",\n";

      from += mdClass.getTableName() + ",\n";
      from += MdBusiness.getMdBusiness(AssayTestResult.CLASS).getTableName() + " assaytestresult_" + i + ",\n";

      where += "AND assaytestresult_" + i + ".mosquito = mosquito.id \n";
      where += "AND assaytestresult_" + i + ".id = " + mdClass.getTableName() + ".id \n";
      i++;
    }

    select = select.substring(0, select.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);
    from = from.substring(0, from.length() - 2);

    return select + "\n" + from + "\n" + where;
  }

  public static String getTargetSiteAssayViewSQL()
  {
    String select = "SELECT mosquito.id AS mosquito_id,";
    String from = "FROM  " + MdBusiness.getMdBusiness(Mosquito.CLASS).getTableName() + " mosquito, ";
    String where = "";
    MdAttributeVirtual[] mdArray =  MosquitoView.getAccessors(TargetSiteAssayTestResult.CLASS);
    int i = 0;

    for (MdAttributeVirtual md : mdArray)
    {
      String acc = md.getAttributeName();
      String result = acc.toLowerCase();
      String resultLocalized = acc.toLowerCase() + "_defaultLocale";
      String method = acc.toLowerCase() + "testmethod";
      String methodLocalized = acc.toLowerCase() + "testmethod_defualtLocale";
      String concreteId = md.getValue(MdAttributeVirtualInfo.MD_ATTRIBUTE_CONCRETE);
      MdAttributeConcreteDAOIF concrete = MdAttributeConcreteDAO.get(concreteId);
      MdBusinessDAOIF mdClass = MdBusinessDAO.get(concrete.getValue(MdAttributeConcreteInfo.DEFINING_MD_CLASS));

      String targetSiteTestResultTable = MdBusiness.getMdBusiness(TargetSiteAssayTestResult.CLASS).getTableName();
      String testMethodTable = MdBusiness.getMdBusiness(InsecticideMethodology.CLASS).getTableName();
      String abstractTermTable = MdBusiness.getMdBusiness(AbstractTerm.CLASS).getTableName();
      String abstractTermDisplayLabelTable = "abstracttermdisplaylabel";
      String molecularAssayResultTable = MdBusiness.getMdBusiness(MolecularAssayResult.CLASS).getTableName();

      select += "(SELECT " + mdClass.getTableName() + ".testresult  \n";
      select += "FROM " + targetSiteTestResultTable + "   tr LEFT JOIN " + molecularAssayResultTable + " mt on " + mdClass.getTableName() + ".testresult = mt.id\n";
      select += "WHERE tr.id = " + mdClass.getTableName() + ".id) AS " + result +" ,\n";

      select += "(SELECT tr.testmethod  \n";
      select += "FROM " + targetSiteTestResultTable + "  tr LEFT JOIN " + testMethodTable + " tm on tr.testmethod = tm.id\n";
      select += "WHERE tr.id = " + mdClass.getTableName() + ".id) AS " + method +" ,\n";

      select += "(SELECT label.defaultLocale \n";
      select += "FROM " + targetSiteTestResultTable + "   tr LEFT JOIN " + molecularAssayResultTable + " mt on " + mdClass.getTableName() + ".testresult = mt.id\n";
      select += "LEFT JOIN " + abstractTermTable + " term ON mt.id = term.id \n";
      select += "LEFT JOIN " + abstractTermDisplayLabelTable + " label ON term.displayLabel = label.id\n";
      select += "WHERE tr.id = " + mdClass.getTableName() + ".id) AS " + resultLocalized + ",\n";

      select += "(SELECT label.defaultLocale \n";
      select += "FROM " + targetSiteTestResultTable + "  tr LEFT JOIN " + testMethodTable + " tm on tr.testmethod = tm.id\n";
      select += "LEFT JOIN " + abstractTermTable + " term ON tm.id = term.id \n";
      select += "LEFT JOIN " + abstractTermDisplayLabelTable + " label ON term.displayLabel = label.id\n";
      select += "WHERE tr.id = " + mdClass.getTableName() + ".id) AS " + methodLocalized + ",\n";

      from += mdClass.getTableName() + ",\n";
      from += "assaytestresult  assaytestresult_" + i + ",\n";

      where += "AND assaytestresult_" + i + ".mosquito = mosquito.id \n";
      where += "AND assaytestresult_" + i + ".id = " + mdClass.getTableName() + ".id \n";
      i++;
    }

    select = select.substring(0, select.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);
    from = from.substring(0, from.length() - 2);

    return select + "\n" + from + "\n" + where;
  }

  public static String getMetabolicAssayViewSQL()
  {
    String select = "SELECT mosquito.id AS mosquito_id,";
    String from = "FROM  " + MdBusiness.getMdBusiness(Mosquito.CLASS).getTableName() + " mosquito, ";
    String where = "";
    MdAttributeVirtual[] mdArray = MosquitoView.getAccessors(MetabolicAssayTestResult.CLASS);
    int i = 0;
    for (MdAttributeVirtual md : mdArray)
    {
      String acc = md.getAttributeName();
      String result = acc.toLowerCase() + "";
      String resultLocalized = acc.toLowerCase() + "_defaultLocale";

      String concreteId = md.getValue(MdAttributeVirtualInfo.MD_ATTRIBUTE_CONCRETE);

      MdAttributeConcreteDAOIF concrete = MdAttributeConcreteDAO.get(concreteId);
      MdBusinessDAOIF mdClass = MdBusinessDAO.get(concrete.getValue(MdAttributeConcreteInfo.DEFINING_MD_CLASS));

      String positveLabel = ( (MdAttributeBoolean) md.getMdAttributeConcrete() ).getPositiveDisplayLabel().getDefaultLocale();
      String negativeLabel = ( (MdAttributeBoolean) md.getMdAttributeConcrete() ).getNegativeDisplayLabel().getDefaultLocale();

      select +=  "(CASE WHEN (" + mdClass.getTableName() + ".testresult = 1) THEN 'true'\n";
      select +=  "WHEN (" + mdClass.getTableName() + ".testresult = 0) THEN 'false'\n";
      select +=  "ELSE '' END) AS " + result + ",\n";

      select +=  "(CASE WHEN (" + mdClass.getTableName() + ".testresult = 1) THEN '" + positveLabel + "'\n";
      select +=  "WHEN (" + mdClass.getTableName() + ".testresult = 0) THEN '" + negativeLabel + "'\n";
      select +=  "ELSE '' END) AS " + resultLocalized + ",\n";

      from += mdClass.getTableName() + ", \n";
      from += "assaytestresult  assaytestresult_" + i + ",\n";

      where += "AND assaytestresult_" + i + ".mosquito = mosquito.id \n";
      where += "AND assaytestresult_" + i + ".id = " + mdClass.getTableName() + ".id \n";
      i++;
    }

    select = select.substring(0, select.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);
    from = from.substring(0, from.length() - 2);

    return select + "\n" + from + "\n" + where;
  }

}
