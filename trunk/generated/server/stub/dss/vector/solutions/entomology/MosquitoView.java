package dss.vector.solutions.entomology;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.terraframe.mojo.SystemException;
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
import dss.vector.solutions.ontology.Term;

public class MosquitoView extends MosquitoViewBase implements Reloadable
{
  private static final long serialVersionUID = 1235599942174L;

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

  public void populateView(Mosquito concrete)
  {
    this.setSpecie(concrete.getSpecie());
    this.setCollection(concrete.getCollection());
    this.setGeneration(concrete.getGeneration());
    this.setIsofemale(concrete.getIsofemale());
    this.setIdentificationMethod(concrete.getIdentificationMethod());
    this.setTestDate(concrete.getTestDate());
    this.setMosquitoId(concrete.getId());
    this.setSampleId(concrete.getSampleId());
    this.setSex(concrete.getSex());

    try
    {
      this.setAssays(concrete.getTestResults());
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  private void populateConcrete(Mosquito concrete)
  {
    concrete.setIdentificationMethod(this.getIdentificationMethod());
    concrete.setIsofemale(this.getIsofemale());
    concrete.setGeneration(this.getGeneration());
    concrete.setSpecie(this.getSpecie());
    concrete.setTestDate(this.getTestDate());
    concrete.setCollection(this.getCollection());
    concrete.setSampleId(this.getSampleId());
    concrete.setSex(this.getSex());
  }

  public void delete()
  {
    throw new RuntimeException("This method should not be invoked");
  }

  public void deleteConcrete()
  {
    if (this.hasConcreteId())
    {
      Mosquito.get(this.getMosquitoId()).delete();
    }
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

  private void applyAssays(Mosquito mosquito) throws IllegalAccessException, InvocationTargetException, InstantiationException
  {
    Map<Class<AssayTestResult>, MdAttributeVirtualDAOIF> assayMap = MosquitoView.getAssayMap();

    for (Class<AssayTestResult> c : assayMap.keySet())
    {
      // Get the result
      MdAttributeVirtualDAOIF mdAttribute = assayMap.get(c);
      String attributeName = GenerationUtil.upperFirstCharacter(mdAttribute.getAccessorName());

      String methodName = attributeName + "Method";

      Object result = this.getObject(attributeName);
      Term testMethod = (Term) this.getObject(methodName);

      if (result != null)
      {
        AssayTestResult test = this.getResult(mosquito, c);

        test.setMosquito(mosquito);
        test.setTestResult(result);
        
        if(test.hasTestMethod())
        {
          test.setTestMethod(testMethod);
        }

        test.apply();
      }
    }
  }
    
  private Object getObject(String attributeName) throws IllegalAccessException, InvocationTargetException
  {
    try
    {
      String methodName = "get" + attributeName;
      
      return MosquitoView.class.getMethod(methodName).invoke(this);
    }
    catch (NoSuchMethodException e)
    {
      return null;
    }
  }

  private AssayTestResult getResult(Mosquito mosquito, Class<AssayTestResult> c) throws InstantiationException, IllegalAccessException
  {
    AssayTestResult result = mosquito.getTestResult(c);

    if (result == null)
    {
      result = c.newInstance();
    }
    else
    {
      result.lock();
    }
    
    return result;
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
        Term testMethod = result.getTestMethod();

        try
        {
          this.setAttribute(attributeName, testResult);

          if (result.hasTestMethod())
          {
            String methodName = attributeName + "Method";
            this.setAttribute(methodName, testMethod);
          }
        }
        catch (Exception e)
        {
          throw new SystemException(e);
        }
      }
    }
  }

  private void setAttribute(String attributeName, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
  {
    Class<? extends Object> klass = value.getClass();

    if(Term.class.isAssignableFrom(klass))
    {
      klass = Term.class;
    }
    
    MosquitoView.class.getMethod("set" + attributeName, klass).invoke(this, value);
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
        // Ensure that an assay attribute has read permissions

        SessionIF session = Session.getCurrentSession();
        String attributeId = map.get(key).getId();

        if (SessionFacade.checkAttributeAccess(session.getId(), Operation.READ, (MdAttributeDAO) MdAttributeDAO.get(attributeId)))
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

    // MosquitoView view = new MosquitoView();
    String s = "[";
    MdAttributeVirtual[] mdArray = MosquitoView.getAccessors(superAssayClass);
    for (MdAttributeVirtual md : mdArray)
    {

      String acc = md.getAttributeName();
      String result = acc.toLowerCase() + "_defaultLocale";
      String method = acc.toLowerCase() + "testmethod_defualtLocale";
      String type = "sqlcharacter";

      // String concreteId =
      // md.getValue(MdAttributeVirtualInfo.MD_ATTRIBUTE_CONCRETE);
      // MdAttributeConcreteDAOIF concrete =
      // MdAttributeConcreteDAO.get(concreteId);

      // MdBusinessDAOIF mdClass =
      // MdBusinessDAO.get(concrete.getValue(MdAttributeConcreteInfo.DEFINING_MD_CLASS));

      s += "{";
      s += "viewAccessor:'" + acc + "',";
      s += "attributeName:'" + result + "',";
      s += "type:'" + type + "',";
      // s += "dtoType:'" + md.getMdAttributeConcrete().getType() + "',";
      s += "displayLabel:'" + md.getMdAttributeConcrete().getDisplayLabel() + "'";
      s += "},\n";
      if (! superAssayClass.equals(MetabolicAssayTestResult.class.getCanonicalName()))
      {
      try
      {
        String testMdGetter = acc + "Method";
        // String testMethodID = view.getMdAttributeDAO(testMdGetter).getId();

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
    String sql = "DROP TABLE IF EXISTS " + tableName + ";\n";
    sql += "CREATE TEMP TABLE " + tableName + " AS " + MosquitoView.getAssayViewSQL(klass) + ";\n";
    return sql;
  }

  public static String getAssayViewSQL(String klass)
  {
    if (klass.equals(InfectivityAssayTestResult.CLASS))
      return MosquitoView.getInfectivityAssayViewSQL();
    if (klass.equals(TargetSiteAssayTestResult.CLASS))
      return MosquitoView.getTargetSiteAssayViewSQL();
    if (klass.equals(MetabolicAssayTestResult.CLASS))
      return MosquitoView.getMetabolicAssayViewSQL();
    return "";
  }

  public static String getInfectivityAssayViewSQL()
  {
    String select = "SELECT mosquito.id AS mosquito_id,\n";
    String from = "FROM  " + MdBusiness.getMdBusiness(Mosquito.CLASS).getTableName() + " mosquito";
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
      String termTable = MdBusiness.getMdBusiness(Term.CLASS).getTableName();
      String atrTable =  MdBusiness.getMdBusiness(AssayTestResult.CLASS).getTableName();

      String positveLabel = ( (MdAttributeBoolean) md.getMdAttributeConcrete() ).getPositiveDisplayLabel().getDefaultLocale();
      String negativeLabel = ( (MdAttributeBoolean) md.getMdAttributeConcrete() ).getNegativeDisplayLabel().getDefaultLocale();

      select += "(SELECT CASE WHEN (arg.testresult = 1) THEN 'true'\n";
      select += "WHEN (arg.testresult = 0) THEN 'false' ELSE '' END\n";
      select += "FROM " + mdClass.getTableName()+ " arg\n"; 
      select += "JOIN " + atrTable + " atr on atr.id = arg.id\n";
      select += "WHERE atr.mosquito = mosquito.id) AS " + result + ",\n";

      select += "(SELECT tr.testmethod  \n";
      select += "FROM " + testResultTable + " tr\n" ;
      select += "JOIN " + mdClass.getTableName()+ " arg on tr.id = arg.id\n";
      select += "JOIN " + atrTable + " atr on atr.id = arg.id\n";
      select += "WHERE atr.mosquito = mosquito.id) AS " + method + ",\n";     

      select += "(SELECT CASE WHEN (arg.testresult = 1) THEN '" + positveLabel + "'\n";
      select += "WHEN (arg.testresult = 0) THEN '" + negativeLabel + "' ELSE '' END\n";
      select += "FROM " + mdClass.getTableName()+ " arg\n";
      select += "JOIN " + atrTable + " atr on atr.id = arg.id\n";
      select += "WHERE atr.mosquito = mosquito.id) AS " + resultLocalized + ",\n";

      select += "(SELECT term.termName \n";
      select += "FROM " + testResultTable + " tr\n" ;
      select += "JOIN " + mdClass.getTableName()+ " arg on tr.id = arg.id\n";
      select += "JOIN " + atrTable + " atr on atr.id = arg.id\n";
      select += "JOIN " + termTable + " term on tr.testmethod = term.id\n";
      select += "WHERE atr.mosquito = mosquito.id) AS " + methodLocalized + ",\n";

      //from += mdClass.getTableName() + ",\n";
      //from += MdBusiness.getMdBusiness(AssayTestResult.CLASS).getTableName() + " assaytestresult_" + i + ",\n";

      //where += "AND assaytestresult_" + i + ".mosquito = mosquito.id \n";
      //where += "AND assaytestresult_" + i + ".id = " + mdClass.getTableName() + ".id \n";
      i++;
    }

    select = select.substring(0, select.length() - 2);
    //where = "WHERE " + where.substring(3, where.length() - 2);
    //from = from.substring(0, from.length() - 2);

    return select + "\n" + from + "\n" + where;
  }

  public static String getTargetSiteAssayViewSQL()
  {
    String select = "SELECT mosquito.id AS mosquito_id,";
    String from = "FROM  " + MdBusiness.getMdBusiness(Mosquito.CLASS).getTableName() + " mosquito";
    String where = "";
    MdAttributeVirtual[] mdArray = MosquitoView.getAccessors(TargetSiteAssayTestResult.CLASS);
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
      String termTable = MdBusiness.getMdBusiness(Term.CLASS).getTableName();
      String atrTable =  MdBusiness.getMdBusiness(AssayTestResult.CLASS).getTableName();
      
      select += "(SELECT arg.testresult \n";
      select += "FROM " + targetSiteTestResultTable + " tr\n" ;
      select += "JOIN " + mdClass.getTableName()+ " arg on tr.id = arg.id\n";
      select += "JOIN " + atrTable + " atr on atr.id = arg.id\n";
      select += "WHERE atr.mosquito = mosquito.id) AS " + result + ",\n";
      
      
      select += "(SELECT tr.testmethod \n";
      select += "FROM " + targetSiteTestResultTable + " tr\n" ;
      select += "JOIN " + mdClass.getTableName()+ " arg on tr.id = arg.id\n";
      select += "JOIN " + atrTable + " atr on atr.id = arg.id\n";
      select += "WHERE atr.mosquito = mosquito.id) AS " + method+ ",\n";

     
      select += "(SELECT term.termName \n";
      select += "FROM " + targetSiteTestResultTable + " tr\n" ;
      select += "JOIN " + mdClass.getTableName()+ " arg on tr.id = arg.id\n";
      select += "JOIN " + atrTable + " atr on atr.id = arg.id\n";
      select += "JOIN " + termTable + " term on arg.testresult = term.id\n";
      select += "WHERE atr.mosquito = mosquito.id) AS " + resultLocalized + ",\n";
      
      
      select += "(SELECT term.termName \n";
      select += "FROM " + targetSiteTestResultTable + " tr\n" ;
      select += "JOIN " + mdClass.getTableName()+ " arg on tr.id = arg.id\n";
      select += "JOIN " + atrTable + " atr on atr.id = arg.id\n";
      select += "JOIN " + termTable + " term on tr.testmethod = term.id\n";
      select += "WHERE atr.mosquito = mosquito.id) AS " + methodLocalized + ",\n";

     // from += mdClass.getTableName() + ",\n";
      //from += "assaytestresult  assaytestresult_" + i + ",\n";

//      where += "AND assaytestresult_" + i + ".mosquito = mosquito.id \n";
  //    where += "AND assaytestresult_" + i + ".id = " + mdClass.getTableName() + ".id \n";
      i++;
    }

    select = select.substring(0, select.length() - 2);
   // where = "WHERE " + where.substring(3, where.length() - 2);
    //from = from.substring(0, from.length() - 2);

    return select + "\n" + from + "\n" + where;
  }

  public static String getMetabolicAssayViewSQL()
  {
    String select = "SELECT mosquito.id AS mosquito_id,";
    String from = "FROM  " + MdBusiness.getMdBusiness(Mosquito.CLASS).getTableName() + " mosquito ";
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
      String termTable = MdBusiness.getMdBusiness(Term.CLASS).getTableName();
      String atrTable =  MdBusiness.getMdBusiness(AssayTestResult.CLASS).getTableName();

      String positveLabel = ( (MdAttributeBoolean) md.getMdAttributeConcrete() ).getPositiveDisplayLabel().getDefaultLocale();
      String negativeLabel = ( (MdAttributeBoolean) md.getMdAttributeConcrete() ).getNegativeDisplayLabel().getDefaultLocale();

      select += "(SELECT CASE WHEN (arg.testresult = 1) THEN 'true'\n";
      select += "WHEN (arg.testresult = 0) THEN 'false' ELSE '' END\n";
      select += "FROM " + mdClass.getTableName()+ " arg\n"; 
      select += "JOIN " + atrTable + " atr on atr.id = arg.id\n";
      select += "WHERE atr.mosquito = mosquito.id) AS " + result + ",\n";

      select += "(SELECT CASE WHEN (arg.testresult = 1) THEN '" + positveLabel + "'\n";
      select += "WHEN (arg.testresult = 0) THEN '" + negativeLabel + "' ELSE '' END\n";
      select += "FROM " + mdClass.getTableName()+ " arg\n";
      select += "JOIN " + atrTable + " atr on atr.id = arg.id\n";
      select += "WHERE atr.mosquito = mosquito.id) AS " + resultLocalized + ",\n";

      //from += mdClass.getTableName() + ", \n";
      //from += "assaytestresult  assaytestresult_" + i + ",\n";

      //where += "AND assaytestresult_" + i + ".mosquito = mosquito.id \n";
      //where += "AND assaytestresult_" + i + ".id = " + mdClass.getTableName() + ".id \n";
      i++;
    }

    select = select.substring(0, select.length() - 2);
    //where = "WHERE " + where.substring(3, where.length() - 2);
    //from = from.substring(0, from.length() - 2);

    return select + "\n" + from + "\n" + where;
  }

}
