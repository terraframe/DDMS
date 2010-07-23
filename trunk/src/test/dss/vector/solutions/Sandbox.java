package dss.vector.solutions;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.constants.ComponentInfo;
import com.runwaysdk.constants.RelationshipInfo;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AttributePrimitive;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.F;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.StartSession;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyViewQuery;
import dss.vector.solutions.geo.LocatedInQuery;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.CaseDiseaseManifestationView;

public class Sandbox
{
  public static java.util.Date startTime   = new java.util.Date();

  private static int           feedbackMod = 50;

  @StartSession
  public static void main(String[] args) throws Exception
  {
     Term term = new Term();
     term.apply();


//    Term[] terms = Term.getSortedRootChildren(CaseDiseaseManifestationView.CLASS, CaseDiseaseManifestationView.DISEASECATEGORY, true);
//
//    for(Term term : terms)
//    {
//      System.out.println(term.getTermDisplayLabel().getValue());
//    }
  }

  private static String concatenate(Selectable[] selectableArray)
  {
    StringBuilder sb = new StringBuilder();
    sb.append("LOWER(' ' || ");
    for (int i = 0; i < selectableArray.length; i++)
    {
      if (i > 0)
      {
        sb.append(" || ' ' || ");
      }
      sb.append(selectableArray[i].getDbQualifiedName());
    }
    sb.append(")");
    return sb.toString();
  }

  private static ValueQuery textLookup(QueryFactory qf, String[] tokenArray, SelectablePrimitive[] selectableArray, Condition[] conditionArray)
  {
    long WEIGHT = 256;

    ValueQuery uQ = qf.valueQuery();

    ValueQuery[] valueQueryArray = new ValueQuery[tokenArray.length];

    if (tokenArray.length > 1)
    {
      for (int i = 0; i < tokenArray.length; i++)
      {
        String token = tokenArray[i].toLowerCase();
        valueQueryArray[i] = buildQueryForToken(qf, token, selectableArray, conditionArray, WEIGHT, i);
      }
      uQ.UNION(valueQueryArray);
    }
    else
    {
      uQ = buildQueryForToken(qf, tokenArray[0].toLowerCase(), selectableArray, conditionArray, WEIGHT, 0);
    }

    // Build outermost select clause. This would be cleaner if the API supported
    // incrementally adding
    // to the select clause. One day that will be supported.
    ValueQuery resultQuery = qf.valueQuery();
    Selectable[] selectClauseArray = new Selectable[selectableArray.length + 2];
    for (int k = 0; k < selectableArray.length; k++)
    {
      selectClauseArray[k] = uQ.get(selectableArray[k].getResultAttributeName());
    }
    selectClauseArray[selectableArray.length] = F.COUNT(uQ.get("weight"), "weight");
    selectClauseArray[selectableArray.length + 1] = F.SUM(uQ.get("weight"), "sum");

    resultQuery.SELECT(selectClauseArray);
    resultQuery.ORDER_BY_DESC(F.COUNT(uQ.get("weight"), "weight"));
    resultQuery.ORDER_BY_DESC(F.SUM(uQ.get("weight"), "sum"));
    for (SelectablePrimitive selectable : selectableArray)
    {
      resultQuery.ORDER_BY_ASC((AttributePrimitive) uQ.get(selectable.getResultAttributeName()));
    }
    resultQuery.HAVING(F.COUNT(uQ.get("weight")).EQ(tokenArray.length));
    System.out.println(resultQuery.getSQL());

    for (ValueObject valueObject : resultQuery.getIterator())
    {
      valueObject.printAttributes();
    }

    return resultQuery;
  }

  private static ValueQuery buildQueryForToken(QueryFactory qf, String token, SelectablePrimitive[] selectableArray, Condition[] conditionArray, long WEIGHT, int i)
  {
    ValueQuery vQ = qf.valueQuery();

    // Build select clause. This would be cleaner if the API supported
    // incrementally adding
    // to the select clause. One day that will be supported.
    Selectable[] selectClauseArray = new Selectable[selectableArray.length + 1];
    for (int k = 0; k < selectableArray.length; k++)
    {
      selectClauseArray[k] = selectableArray[k];
    }
    selectClauseArray[selectableArray.length] = vQ.aSQLDouble("weight", "1.0 / (" + Math.pow(WEIGHT, i) + " * STRPOS(" + concatenate(selectableArray) + ", ' " + token + "'))");

    vQ.SELECT(selectClauseArray);
    vQ.WHERE(vQ.aSQLCharacter("fields", concatenate(selectableArray)).LIKE("% " + token + "%"));

    for (Condition condition : conditionArray)
    {
      vQ.AND(condition);
    }
    return vQ;
  }

  @StartSession
  public static void testGeoEntityQuery(String sessionId)
  {
    new CleanupContextListener().contextInitialized(null);

    String geoId = Property.getStr(PropertyInfo.INSTALL_PACKAGE, PropertyInfo.COUNTRY_GEO_ID);
    System.out.println(geoId);
  }

  @StartSession
  public static void testNoLogin()
  {

    QueryFactory qf = new QueryFactory();

    GeoHierarchyViewQuery q = new GeoHierarchyViewQuery(qf, "", true, 20, 1);
    q.WHERE(q.getIsADisplayLabel().EQ("Geo Entity"));

    System.out.println(q.getSQL());

    // ValueQuery valueQuery = new ValueQuery(factory);
    // AggregatedCaseQuery query = new AggregatedCaseQuery(factory);
    //
    // Condition condition = caseQuery.getGeoEntity().EQ(entityQuery);
    // condition = AND.get(condition,
    // caseQuery.getStartDate().GE(initialWeek.getStartDate()));
    // condition = AND.get(condition,
    // caseQuery.getEndDate().LE(finalWeek.getEndDate()));
    // //condition = AND.get(condition,
    // caseQuery.getEndDate().EQ(caseQuery.getStartDate() + 7));
    // valueQuery.WHERE(caseQuery.getGeoEntity().EQ(entityQuery));
    // valueQuery.AND(caseQuery.getStartDate().GE(initialWeek.getStartDate()));
    // valueQuery.AND(caseQuery.getEndDate().LE(finalWeek.getEndDate()));
    // valueQuery.AND(caseQuery.getEndDate().EQ(valueQuery.aSQLDate("startDate",
    // caseQuery.getStartDate().getQualifiedName() + "+ interval '7 days'")));
    // valueQuery.FROM(caseQuery.getStartDate().getDefiningTableName(),
    // caseQuery.getStartDate().getDefiningTableAlias());

    /*
     * select name, count(weight) c, sum(weight) s from ( select name, 1.0 /
     * (1.0 * strpos(lower(' ' || name), ' b')) as weight from term where
     * lower(' ' || name) like '% b%' union all select name, 1.0 / (256.0 *
     * strpos(lower(' ' || name), ' a')) as weight from term where lower(' ' ||
     * name) like '% a%' ) as foo group by name --having count(weight) = 2 order
     * by c desc, s desc, name
     */

    // String[] tokenArray = new String[]{"Plasmodium"};
    // String[] tokenArray = new String[]{"Plasmodium", "falciparum"};
    //
    //
    // QueryFactory qf = new QueryFactory();
    // TermQuery tQ = new TermQuery(qf);
    // GeoEntityQuery gQ = new GeoEntityQuery(qf);
    //
    // SelectablePrimitive[] selectableArray = new SelectablePrimitive[2];
    // selectableArray[0] = tQ.getName();
    // selectableArray[1] = tQ.getTermId();
    //
    // // This is a COMPLETELY contrived example that makes no sense in real
    // ife.
    // Condition joinCondition = tQ.getName().EQ(gQ.getEntityName());
    // // textLookup(qf, tokenArray, selectableArray, new
    // Condition[]{joinCondition});
    //
    // textLookup(qf, tokenArray, selectableArray, new Condition[]{});

    // GeoEntity.buildAllPathsFast();

    // GeoEntity.copyTermFast("xs8cxvues3ab3m879rgl4tu70gy0s3r72a0u0u4o9c8ol44kb9vl8tf3ieviu8co",
    // "rekttx1h9ehy40ubzbxg2fd044owmwzp2a0u0u4o9c8ol44kb9vl8tf3ieviu8co");

    // AllPaths.rebuildAllPaths();

    // AllPaths.copyTermFast("05brck3zaer5w5vbcee16ry28lzao1e3xeklfgy3bklheiiulqszhfay163qlpta",
    // "zbdda5r3mmkkwyuhla67ymkn551jmtpnxeklfgy3bklheiiulqszhfay163qlpta",
    // "05kt1jddumk5qm8juvcxseyist5klwhrzm1g3udb394gw9tn8ca9qvefl9ncyubd");

    // try
    // {
    // FileInputStream fileInputStream = new FileInputStream(new
    // File("/Users/nathan/workspace3.5/MDSS/PersonExcelView.xls"));
    //
    // GeoEntitySearcher geoSynonymMatcher = new GeoEntitySearcher();
    // geoSynonymMatcher.checkExcelGeoHierarchy(fileInputStream);
    // }
    // catch (FileNotFoundException e)
    // {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    /*
     * Mocambique Mozambique
     *
     * select metaphone('Mocambique', 255); select metaphone('Mozambique', 255);
     *
     * select dmetaphone('Mocambique'); select dmetaphone_alt('Mocambique');
     *
     * select dmetaphone('Mozambique'); select dmetaphone_alt('Mozambique');
     *
     * SELECT levenshtein('Mocambique', 'Mozambique');
     *
     * Bilene Bellene
     */
    // QueryFactory qf = new QueryFactory();
    // GeoEntityQuery geoEntityQuery = new GeoEntityQuery(qf);
    //
    // geoEntityQuery.WHERE(geoEntityQuery.getEntityName().EQ("Gaza"));
    //
    // OIterator<? extends GeoEntity> i = geoEntityQuery.getIterator();
    //
    // try
    // {
    // GeoEntity geoEntity = i.next();
    // System.out.println(geoEntity.getEntityName());
    //
    // GeoSynonym geoSynonym = new GeoSynonym();
    // geoSynonym.setEntityName("Gaaza");
    // geoSynonym.apply();
    //
    // geoEntity.addSynonyms(geoSynonym).apply();
    //
    // }
    // finally
    // {
    // i.close();
    // }
  }

  @StartSession
  public static void test(String sessionId)
  {

    // QueryFactory f = new QueryFactory();
    //
    // ValueQuery v = new ValueQuery(f);
    // ValueQuery v2 = new ValueQuery(f);
    //
    // PersonQuery p1 = new PersonQuery(f); // Original PersonQuery
    // PersonQuery p2 = new PersonQuery(f); // PersonQuery for Prevalence
    //
    // // total tested
    // Condition or = OR.get(p2.getPerformedRDT().containsAny(RDTResponse.YES),
    // p2.getBloodslide().containsAny(BloodslideResponse.DONE));
    // p2.WHERE(or);
    //
    // // total positive
    // p2.AND(p2.getRDTResult().containsAny(RDTResult.MALARIAE_POSITIVE,
    // RDTResult.MIXED_POSITIVE,
    // RDTResult.OVALE_POSITIVE, RDTResult.PF_POSITIVE,
    // RDTResult.VIVAX_POSITIVE));
    //
    // v2.SELECT(F.COUNT(p2.getId()));
    //
    // SelectableSQLDouble precision = v.aSQLAggregateDouble("precision", "");
    // precision.setSQL("100 * AVG( ("+v2.getSQL()+" WHERE "+p2.getTableAlias()+".id = "+p1.getTableAlias()+".id))");
    //
    //
    // v.SELECT(precision);
    // v.FROM(p1);
    //
    // System.out.println(v.getSQL());
  }

  @StartSession
  private static void testAllPaths()
  {
    // defineDatatypesForAllPaths();

    // GeoHierarchy earthGeoHierarchy =
    // GeoHierarchy.getGeoHierarchyFromType(Earth.CLASS);
    // updateAllPaths(earthGeoHierarchy);
    //
    // updateAllPaths();
    //
    // java.util.Date endTime = new java.util.Date();
    // long totalTime = endTime.getTime() - startTime.getTime();
    // System.out.println("\nTotal Time: " + totalTime);
  }

  @Transaction
  private static void defineDatatypesForAllPaths()
  {
    MdBusinessDAOIF allPathsMdBusinessDAO = MdBusinessDAO.getMdBusinessDAO(PropertyInfo.GEO_PACKAGE + "." + "AllPaths");
    allPathsMdBusinessDAO.getBusinessDAO().delete();

    // MdBusinessDAOIF geoEntityMdBusiness =
    // MdBusinessDAO.getMdBusinessDAO(GeoEntity.CLASS);
    //
    // MdBusinessDAO allPathsMdBusinessDAO = MdBusinessDAO.newInstance();
    // allPathsMdBusinessDAO.setValue(MdBusinessInfo.NAME, "AllPaths");
    // allPathsMdBusinessDAO.setValue(MdBusinessInfo.PACKAGE,
    // PropertyInfo.GEO_PACKAGE);
    // allPathsMdBusinessDAO.setStructValue(MdBusinessInfo.DISPLAY_LABEL,
    // MdAttributeLocalInfo.DEFAULT_LOCALE, "All Paths");
    // allPathsMdBusinessDAO.setValue(MdBusinessInfo.EXTENDABLE,
    // MdAttributeBooleanInfo.FALSE);
    // allPathsMdBusinessDAO.setGenerateMdController(false);
    // allPathsMdBusinessDAO.apply();
    //
    // MdAttributeReferenceDAO parentMdAttributeReferenceDAO =
    // MdAttributeReferenceDAO.newInstance();
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.NAME,
    // "parentGeoEntity");
    // parentMdAttributeReferenceDAO.setStructValue(MdAttributeReferenceInfo.DISPLAY_LABEL,
    // MdAttributeLocalInfo.DEFAULT_LOCALE, "Parent Geo Entity");
    // parentMdAttributeReferenceDAO.setStructValue(MdAttributeReferenceInfo.DESCRIPTION,
    // MdAttributeLocalInfo.DEFAULT_LOCALE, "Parent Geo Entity");
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REQUIRED,
    // MdAttributeBooleanInfo.TRUE);
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REMOVE,
    // MdAttributeBooleanInfo.TRUE);
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.INDEX_TYPE,
    // IndexTypes.NON_UNIQUE_INDEX.getId());
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REF_MD_BUSINESS,
    // geoEntityMdBusiness.getId());
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.DEFINING_MD_CLASS,
    // allPathsMdBusinessDAO.getId());
    // parentMdAttributeReferenceDAO.apply();
    //
    // MdAttributeReferenceDAO childMdAttributeReferenceDAO =
    // MdAttributeReferenceDAO.newInstance();
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.NAME,
    // "childGeoEntity");
    // childMdAttributeReferenceDAO.setStructValue(MdAttributeReferenceInfo.DISPLAY_LABEL,
    // MdAttributeLocalInfo.DEFAULT_LOCALE, "Child Geo Entity");
    // childMdAttributeReferenceDAO.setStructValue(MdAttributeReferenceInfo.DESCRIPTION,
    // MdAttributeLocalInfo.DEFAULT_LOCALE, "Child Geo Entity");
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REQUIRED,
    // MdAttributeBooleanInfo.TRUE);
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REMOVE,
    // MdAttributeBooleanInfo.TRUE);
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.INDEX_TYPE,
    // IndexTypes.NON_UNIQUE_INDEX.getId());
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REF_MD_BUSINESS,
    // geoEntityMdBusiness.getId());
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.DEFINING_MD_CLASS,
    // allPathsMdBusinessDAO.getId());
    // childMdAttributeReferenceDAO.apply();
    //
    //
    // MdIndexDAO mdIndex = MdIndexDAO.newInstance();
    // mdIndex.setValue(MdIndexInfo.MD_ENTITY, allPathsMdBusinessDAO.getId());
    // mdIndex.setValue(MdIndexInfo.UNIQUE, MdAttributeBooleanInfo.TRUE);
    // mdIndex.setStructValue(MdIndexInfo.DISPLAY_LABEL,
    // MdAttributeLocalInfo.DEFAULT_LOCALE,
    // "Parent Child Geo Entity Unique Index");
    // mdIndex.apply();
    //
    // mdIndex.addAttribute(parentMdAttributeReferenceDAO, 0);
    // mdIndex.addAttribute(childMdAttributeReferenceDAO, 1);
    //
    // mdIndex.setValue(MdIndexInfo.ACTIVE, MdAttributeBooleanInfo.TRUE);
    // mdIndex.apply();
  }

  @Transaction
  private static void updateAllPaths(GeoHierarchy parentGeoHierarchy)
  {
    updateAllPathsForUniversal(parentGeoHierarchy);

    List<? extends GeoHierarchy> geoHierarchyChildren = parentGeoHierarchy.getAllAcceptsGeoEntity().getAll();

    for (GeoHierarchy childGeoHierarchy : geoHierarchyChildren)
    {
      updateAllPaths(childGeoHierarchy);
    }
  }

  private static void createPath(String parentId, String childId)
  {
    // try
    // {
    // AllPaths allPaths = new AllPaths();
    // allPaths.setValue(AllPaths.PARENTGEOENTITY, parentId);
    // allPaths.setValue(AllPaths.CHILDGEOENTITY, childId);
    // allPaths.apply();
    // }
    // catch(DuplicateDataDatabaseException ex)
    // {
    // // This might happen. Relationship already exists.
    // }
  }

  private static void updateAllPathsForUniversal(GeoHierarchy geoHierarchy)
  {
    MdBusiness mdBusiness = geoHierarchy.getGeoEntityClass();

    // We only need to do this for the parent most types.
    // The child types will be included when we update paths for
    // the parent type.
    if (!mdBusiness.getSuperMdBusiness().definesType().equals(GeoEntity.CLASS))
    {
      return;
    }

    String geoEntityType = mdBusiness.definesType();

    QueryFactory qf = new QueryFactory();

    BusinessQuery businessQ = qf.businessQuery(geoEntityType);

    ValueQuery q = new ValueQuery(qf);
    q.SELECT(businessQ.aCharacter(ComponentInfo.ID, ComponentInfo.ID));

    OIterator<ValueObject> i = q.getIterator();

    try
    {
      int applyCount = 0;

      for (ValueObject valueObject : i)
      {
        String childId = valueObject.getValue(ComponentInfo.ID);
        createPath(childId, childId);
        System.out.print(".");

        applyCount++;

        if (applyCount % feedbackMod == 0)
        {
          System.out.println();
        }

        List<String> parentIdList = getParentIds(childId);

        for (String parentId : parentIdList)
        {
          createPath(parentId, childId);
          System.out.print(".");

          applyCount++;

          if (applyCount % feedbackMod == 0)
          {
            System.out.println();
          }
        }

      }
    }
    finally
    {
      i.close();
    }
  }

  private static void updateAllPaths()
  {
    QueryFactory qf = new QueryFactory();

    GeoEntityQuery geoEntityQ = new GeoEntityQuery(qf);

    ValueQuery q = new ValueQuery(qf);
    q.SELECT(geoEntityQ.getId(ComponentInfo.ID));

    OIterator<ValueObject> i = q.getIterator();

    try
    {
      int applyCount = 0;

      for (ValueObject valueObject : i)
      {
        String childId = valueObject.getValue(ComponentInfo.ID);
        createPath(childId, childId);
        System.out.print(".");

        applyCount++;

        if (applyCount % feedbackMod == 0)
        {
          System.out.println();
        }

        List<String> parentIdList = getParentIds(childId);

        for (String parentId : parentIdList)
        {
          createPath(parentId, childId);
          System.out.print(".");

          applyCount++;

          if (applyCount % feedbackMod == 0)
          {
            System.out.println();
          }
        }

      }
    }
    finally
    {
      i.close();
    }
  }

  // private static boolean parentPathsExist(String parentId)
  // {
  // QueryFactory queryFactory = new QueryFactory();
  //
  // AllPathsQuery allPathsQuery = new AllPathsQuery(queryFactory);
  //
  // ValueQuery valueQuery = new ValueQuery(queryFactory);
  //
  // valueQuery.SELECT(allPathsQuery.getParentGeoEntity(AllPaths.PARENTGEOENTITY));
  // valueQuery.WHERE(allPathsQuery.getChildGeoEntity().EQ(parentId));
  //
  // for (ValueObject valueObject : valueQuery.getIterator())
  // {
  // return true;
  // }
  //
  // return false;
  // }

  private static List<String> getParentIds(String childId)
  {
    QueryFactory queryFactory = new QueryFactory();

    LocatedInQuery locatedInQuery = new LocatedInQuery(queryFactory);

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    valueQuery.SELECT(locatedInQuery.parentId(RelationshipInfo.PARENT_ID));
    valueQuery.WHERE(locatedInQuery.childId().EQ(childId));

    List<ValueObject> valueObjectList = valueQuery.getIterator().getAll();

    List<String> parentIdList = new LinkedList<String>();

    for (ValueObject valueObject : valueObjectList)
    {
      String parentId = valueObject.getValue(RelationshipInfo.PARENT_ID);
      parentIdList.add(parentId);
      parentIdList.addAll(getParentIds(parentId));
    }

    return parentIdList;
  }

  @StartSession
  private static void testQueries()
  {
    // QueryFactory qf = new QueryFactory();
    //
    // ValueQuery valueQuery = new ValueQuery(qf);
    //
    // AggregatedCaseQuery acq = new AggregatedCaseQuery(qf);
    //
    // CaseTreatmentMethodQuery ctmq1 = new CaseTreatmentMethodQuery(qf);
    // CaseTreatmentMethodQuery ctmq2 = new CaseTreatmentMethodQuery(qf);
    //
    // TreatmentMethodGridQuery tmgq1 = new TreatmentMethodGridQuery(qf);
    // tmgq1.WHERE(tmgq1.getOptionName().EQ("Tablets"));
    // TreatmentMethodGridQuery tmgq2 = new TreatmentMethodGridQuery(qf);
    // tmgq2.WHERE(tmgq2.getOptionName().EQ("Syrup"));
    //
    //
    // valueQuery.SELECT(acq.getStartAge(), acq.getEndAge(),
    // F.SUM(ctmq1.getAmount(), "Tablets"), F.SUM(ctmq2.getAmount(), "Syrup"));
    // valueQuery.WHERE(acq.treatmentMethod(ctmq1));
    // valueQuery.AND(acq.treatmentMethod(ctmq2));
    // valueQuery.AND(ctmq1.hasChild(tmgq1));
    // valueQuery.AND(ctmq2.hasChild(tmgq2));
    //
    //
    // System.out.println(valueQuery.getSQL());
    //
    //
    // for (ValueObject valueObject : valueQuery.getIterator())
    // {
    // valueObject.printAttributes();
    // for (com.runwaysdk.dataaccess.AttributeIF attributeIF :
    // valueObject.getAttributeArrayIF())
    // {
    // System.out.println(attributeIF.getDisplayLabel(Locale.ENGLISH));
    // }
    // }

  }

}
