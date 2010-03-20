package dss.vector.solutions;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.ClientSession;
import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.constants.ComponentInfo;
import com.runwaysdk.constants.RelationshipInfo;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
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

import dss.vector.solutions.geo.GeoEntityView;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyViewQuery;
import dss.vector.solutions.geo.LocatedInQuery;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;

public class Sandbox
{
  public static java.util.Date startTime   = new java.util.Date();

  private static int           feedbackMod = 50;

  public static void main(String[] args) throws Exception
  {
    testGeoEntityQuery();

    // String s =
    // "ajlas aljskfdj GROUP BY entityname_v, geoid_v, type_4, geometry_v, exo_v WHERE jaja";
    // Pattern p =
    // Pattern.compile("(.*? GROUP BY\\s)(([a-z0-9_]+,?\\s?)+)(.*?)");
    // Matcher m = p.matcher(s);
    //    
    // m.matches();
    //    
    // String matched = m.group(2);
    //
    // String[] split = matched.split(",\\s");
    // String[] done = new String[split.length-1];
    // int count = 0;
    // for(String piece : split)
    // {
    // if(!piece.equals("geometry_v"))
    // {
    // done[count++] = piece;
    // }
    // }
    //    
    // String groupBy = StringUtils.join(done, ", ");
    //    
    // String sql = m.replaceFirst("$1"+groupBy+"$4");

    // try
    // {
    // ClientSession session = ClientSession.createUserSession("MDSS",
    // "mdsstest2", new Locale[]{Locale.ENGLISH});
    // gogo(session.getSessionId(), session);
    // // OntologyDefinitionDTO od = new
    // OntologyDefinitionDTO(session.getRequest());
    // // od.setOntologyName("MO");
    // // od.setNamespace("dss.vector.solutions.ontology");
    // // od.apply();
    //
    //
    //
    //
    // }
    // catch(ProblemExceptionDTO e)
    // {
    // }

    //
    // String temp = "CaseTreatmentStock_SP_TreatmentGrid";
    //
    // int firstIndex = temp.indexOf("_", 0);
    //
    // int secondIndex = temp.indexOf("_", firstIndex+1);
    //
    // String relString = temp.substring(0, secondIndex);
    //
    // System.out.println(firstIndex+"  "+secondIndex+"  "+relString);

    //
    // String s =
    // "dss.vector.solutions.geo.generated.CountryController&dss.vector.solutions.geo.generated.DistrictController&dss.vector.solutions.geo.generated.VillageController&dss.vector.solutions.geo.generated.LocalityController&dss.vector.solutions.geo.generated.AdminPostController&dss.vector.solutions.geo.generated.Trap&dss.vector.solutions.geo.generated.RoadController&dss.vector.solutions.geo.generated.Reserve&dss.vector.solutions.geo.generated.EarthController&dss.vector.solutions.geo.generated.CityController&dss.vector.solutions.geo.generated.Town&dss.vector.solutions.geo.generated.District&dss.vector.solutions.geo.generated.Village&dss.vector.solutions.geo.generated.RoofController&dss.vector.solutions.geo.generated.NonSentinelSiteController&dss.vector.solutions.geo.generated.Earth&dss.vector.solutions.geo.generated.SentinelSite&dss.vector.solutions.geo.generated.RiverController&dss.vector.solutions.geo.generated.Wall&dss.vector.solutions.geo.generated.SprayZoneController&dss.vector.solutions.geo.generated.WallController&dss.vector.solutions.geo.generated.PermanentWaterBodyController&dss.vector.solutions.geo.generated.BreedingSiteController&dss.vector.solutions.geo.generated.PopulatedArea&dss.vector.solutions.geo.generated.FacilityController&dss.vector.solutions.geo.generated.Railway&dss.vector.solutions.geo.generated.Locality&dss.vector.solutions.geo.generated.SentinelSiteController&dss.vector.solutions.geo.generated.NationalRoadController&dss.vector.solutions.geo.generated.Road&dss.vector.solutions.geo.generated.Facility&dss.vector.solutions.geo.generated.Province&dss.vector.solutions.geo.generated.PopulatedAreaController&dss.vector.solutions.geo.generated.ProvinceController&dss.vector.solutions.geo.generated.ReserveController&dss.vector.solutions.geo.generated.TrapController&dss.vector.solutions.geo.generated.BreedingSite&dss.vector.solutions.geo.generated.AdminPost&dss.vector.solutions.geo.generated.Roof&dss.vector.solutions.geo.generated.SprayZone&dss.vector.solutions.geo.generated.River&dss.vector.solutions.geo.generated.NationalRoad&dss.vector.solutions.geo.generated.City&dss.vector.solutions.geo.generated.Country&dss.vector.solutions.geo.generated.PermanentWaterBody&dss.vector.solutions.geo.generated.RailwayController&dss.vector.solutions.geo.generated.TownController&dss.vector.solutions.geo.generated.NonSentinelSite&dss.vector.solutions.geo.GeoEntityView";
    //
    // String[] arr = s.split("&");
    //
    // for(String type : arr)
    // {
    // // System.out.println(type);
    // }
    //
    // Date before = new Date();
    //
    // // String js = JSONController.importTypes(session.getSessionId(), arr,
    // true);
    // String js = JSONController.importTypes(session.getSessionId(), new
    // String[]{GeoEntity.CLASS}, true);
    //
    //
    // //System.out.println(js);
    //
    // Date after = new Date();

    // System.out.println(before);
    // System.out.println(after);

    // createGeoEntities(request.getSessionId());

    // testQueries();

    // queryAggregatedCases();

    // testAllPaths();
  }

  @StartSession
  private static void gogo(String sessionId, ClientSession session)
  {

    MdAttributeDAOIF dao = MdAttributeDAO.getByKey("dss.vector.solutions.ontology.Term.namespace");
    dao.getBusinessDAO().apply();

    // AllPaths.get("2d3373900f3c9635c73f7b50b126220c0ng41xiuq0nzo8wjdn1v9cunh9f1icac").printAttributes();

    // QueryFactory queryFactory = new QueryFactory();
    //
    // MdClassQuery mdClassQuery;
    // mdClassQuery = new MdClassQuery(queryFactory);
    //
    // MdAttributeConcreteQuery mdConcreteQuery = new
    // MdAttributeConcreteQuery(queryFactory);
    //
    // ValueQuery concreteQuery = new ValueQuery(queryFactory);
    // ValueQuery virtualQuery = new ValueQuery(queryFactory);
    //
    // ValueQuery unioned = new ValueQuery(queryFactory);
    //
    // MdAttributeVirtualQuery mdVirtualQuery = new
    // MdAttributeVirtualQuery(queryFactory);
    //
    // // join concrete attribute with display labels
    // concreteQuery.SELECT(mdClassQuery.getId("classId"),
    // mdClassQuery.getDisplayLabel().currentLocale("classLabel"),
    // mdConcreteQuery.getId("attributeId"),
    // mdConcreteQuery.getDisplayLabel().currentLocale("attributeLabel"),
    // mdConcreteQuery.getDefiningMdClass().getId("definingMdClass"));
    // concreteQuery.WHERE(mdConcreteQuery.getDefiningMdClass().EQ(mdClassQuery));
    //
    // virtualQuery.SELECT(mdClassQuery.getId("classId"),
    // mdClassQuery.getDisplayLabel().currentLocale("classLabel"),
    // mdVirtualQuery.getId("attributeId"),
    // mdVirtualQuery.getDisplayLabel().currentLocale("attributeLabel"),
    // mdVirtualQuery.getDefiningMdView().getId("definingMdClass"));
    // virtualQuery.WHERE(mdVirtualQuery.getDefiningMdView().EQ(mdClassQuery));
    //
    // unioned.UNION(concreteQuery, virtualQuery);
    //
    // for(ValueObject valueObject : unioned.getIterator())
    // {
    // valueObject.printAttributes();
    // }

    try
    {
    }
    catch (Throwable t)
    {
      t.printStackTrace();
    }
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
      sb.append(selectableArray[i].getQualifiedName());
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
  public static void testGeoEntityQuery()
  {
    ValueQuery query = GeoEntity.searchByParameters("kalombo ka", new String[] { "true", "false", "false" }, true);

    OIterator<ValueObject> it = query.getIterator();

    while (it.hasNext())
    {
      ValueObject next = it.next();

      System.out.println(next.getValue(GeoEntity.ENTITYNAME) + next.getValue(GeoEntityView.MOSUBTYPE));
    }
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

    System.out.println("\nHeads up: " + mdBusiness.getDisplayLabel() + ": " + mdBusiness.getSuperMdBusiness().getDisplayLabel());

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
