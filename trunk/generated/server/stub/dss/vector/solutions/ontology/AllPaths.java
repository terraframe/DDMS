package dss.vector.solutions.ontology;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.runwaysdk.constants.ComponentInfo;
import com.runwaysdk.constants.RelationshipInfo;
import com.runwaysdk.constants.ServerConstants;
import com.runwaysdk.constants.ServerProperties;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.database.DuplicateDataDatabaseException;
import com.runwaysdk.dataaccess.database.general.PostgreSQL;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionIF;
import com.runwaysdk.session.StartSession;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdRelationship;
import com.runwaysdk.util.IdParser;

public class AllPaths extends AllPathsBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1253040252503L;

  public static int BATCH_SIZE = 1000;

  public AllPaths()
  {
    super();
  }

  /**
   * @param args
   */
  @StartSession
  public static void main(String[] args)
  {
    rebuildAllPaths();
  }

  /**
   * Only one thread can rebuild this table at a time.
   */
  @Transaction
  public synchronized static void rebuildAllPaths()
  {
    MdBusiness mdBusiness = MdBusiness.getMdBusiness(AllPaths.CLASS);

    mdBusiness.deleteAllTableRecords();

    buildAllPathsFast();
  }

  @Transaction
  public static void buildAllPathsFast()
  {
    String termTable = MdBusiness.getMdBusiness(Term.CLASS).getTableName();
    String termRelationshipTable = MdRelationship.getMdElement(TermRelationship.CLASS).getTableName();
    String allPathsTable = MdBusiness.getMdBusiness(AllPaths.CLASS).getTableName();
    String allPathsRootTypeId = IdParser.parseRootFromId(MdBusiness.getMdBusiness(AllPaths.CLASS).getId());
    String sitemaster = ServerProperties.getDomain();
    Date transactionDate = new Date();
    String createdById;
    SessionIF sessionIF = Session.getCurrentSession();
    if (sessionIF != null)
    {
      createdById = sessionIF.getUser().getId();
    }
    else
    {
      createdById = ServerConstants.SYSTEM_USER_ID;
    }

    QueryFactory qf = new QueryFactory();
    OntologyRelationshipQuery orQ = new OntologyRelationshipQuery(qf);

    for (OntologyRelationship ontologyRelationship : orQ.getIterator())
    {
      String sql = "INSERT INTO "+allPathsTable+" (\n" +
      "  "+AllPaths.getIdMd().definesAttribute().toLowerCase()+",\n" +
      "  "+AllPaths.getSiteMasterMd().definesAttribute().toLowerCase()+",\n" +
      "  "+AllPaths.getKeyNameMd().definesAttribute().toLowerCase()+",\n" +
      "  "+AllPaths.getTypeMd().definesAttribute().toLowerCase()+",\n" +
      "  "+AllPaths.getEntityDomainMd().definesAttribute().toLowerCase()+",\n" +
      "  "+AllPaths.getLastUpdateDateMd().definesAttribute().toLowerCase()+",\n" +
      "  "+AllPaths.getSeqMd().definesAttribute().toLowerCase()+",\n" +
      "  "+AllPaths.getCreatedByMd().definesAttribute().toLowerCase()+",\n" +
      "  "+AllPaths.getLockedByMd().definesAttribute().toLowerCase()+",\n" +
      "  "+AllPaths.getCreateDateMd().definesAttribute().toLowerCase()+",\n" +
      "  "+AllPaths.getOwnerMd().definesAttribute().toLowerCase()+",\n" +
      "  "+AllPaths.getLastUpdatedByMd().definesAttribute().toLowerCase()+",\n" +
      "  "+AllPaths.getParentTermMd().definesAttribute().toLowerCase()+",\n" +
      "  "+AllPaths.getChildTermMd().definesAttribute().toLowerCase()+",\n" +
      "  "+AllPaths.getOntologyRelationshipMd().definesAttribute().toLowerCase()+"\n" +
      ") \n" +

      "SELECT  \n" +
      "    md5(term1."+AllPaths.getIdMd().definesAttribute().toLowerCase()+" || term2."+AllPaths.getIdMd().definesAttribute().toLowerCase()+" ) || '"+allPathsRootTypeId+"',\n" +
      "    '"+sitemaster+"'                                       AS "+AllPaths.getSiteMasterMd().definesAttribute().toLowerCase()+",\n" +
      "    md5(term1."+AllPaths.getIdMd().definesAttribute().toLowerCase()+" || term2."+AllPaths.getIdMd().definesAttribute().toLowerCase()+" ) || '"+allPathsRootTypeId+"' AS "+AllPaths.getKeyNameMd().definesAttribute().toLowerCase()+",\n" +
      "    '"+AllPaths.CLASS+"'                                   AS \""+AllPaths.getTypeMd().definesAttribute().toLowerCase()+"\",\n" +
      "    ''                                                     AS "+AllPaths.getEntityDomainMd().definesAttribute().toLowerCase()+",\n" +
      "    ?                                                      AS "+AllPaths.getLastUpdateDateMd().definesAttribute().toLowerCase()+",\n" +
      "    NEXTVAL('"+PostgreSQL.UNIQUE_OBJECT_ID_SEQUENCE+"')    AS "+AllPaths.getSeqMd().definesAttribute().toLowerCase()+",\n" +
      "    '"+createdById+"'                                      AS "+AllPaths.getCreatedByMd().definesAttribute().toLowerCase()+",\n" +
      "    null                                                   AS "+AllPaths.getLockedByMd().definesAttribute().toLowerCase()+",\n" +
      "    ?                                                      AS "+AllPaths.getCreatedByMd().definesAttribute().toLowerCase()+",\n" +
      "    '"+createdById+"'                                      AS \""+AllPaths.getOwnerMd().definesAttribute().toLowerCase()+"\",\n" +
      "    '"+createdById+"'                                      AS "+AllPaths.getLastUpdatedByMd().definesAttribute().toLowerCase()+",\n" +
      "    recurs_rel."+RelationshipInfo.PARENT_ID+"              AS "+AllPaths.getParentTermMd().definesAttribute().toLowerCase()+", \n" +
      "    recurs_rel.root_id                                     AS "+AllPaths.getChildTermMd().definesAttribute().toLowerCase()+", \n" +
      "    '"+ontologyRelationship.getId()+"'                      AS "+AllPaths.getOntologyRelationshipMd().definesAttribute().toLowerCase()+" \n" +

      "FROM "+termTable+" as term1, "+termTable+" as term2,\n" +
      " (SELECT root_id, "+RelationshipInfo.PARENT_ID+", "+RelationshipInfo.CHILD_ID+" \n"+
      " FROM \n"+
      "  ( WITH RECURSIVE quick_paths AS \n"+
      "    ( SELECT "+RelationshipInfo.CHILD_ID+" AS root_id, "+RelationshipInfo.CHILD_ID+", "+RelationshipInfo.PARENT_ID+" \n"+
      "      FROM "+termRelationshipTable+" \n" +
      "      WHERE "+termRelationshipTable+"."+AllPaths.getOntologyRelationshipMd().definesAttribute().toLowerCase()+" = '"+ontologyRelationship.getId()+"' \n"+
      "      UNION \n"+
      "      SELECT a.root_id, b."+RelationshipInfo.CHILD_ID+", b."+RelationshipInfo.PARENT_ID+" \n"+
      "      FROM quick_paths a, "+termRelationshipTable+" b \n"+
      "      WHERE b."+RelationshipInfo.CHILD_ID+" = a."+RelationshipInfo.PARENT_ID+" \n"+
      "      AND b."+AllPaths.getOntologyRelationshipMd().definesAttribute().toLowerCase()+" = '"+ontologyRelationship.getId()+"' \n"+
      "    ) \n"+
      "    SELECT root_id, root_id as "+RelationshipInfo.CHILD_ID+", "+RelationshipInfo.PARENT_ID+" \n"+
      "    FROM quick_paths \n"+
      "    UNION \n"+
      "    SELECT "+AllPaths.getIdMd().definesAttribute().toLowerCase()+", "+AllPaths.getIdMd().definesAttribute().toLowerCase()+", "+AllPaths.getIdMd().definesAttribute().toLowerCase()+" \n"+
      "    FROM "+termTable+" \n"+
      "    WHERE "+AllPaths.getIdMd().definesAttribute().toLowerCase()+" IN \n"+
      "      (SELECT "+RelationshipInfo.PARENT_ID+" \n"+
      "       FROM "+termRelationshipTable+" \n"+
      "       WHERE "+AllPaths.getOntologyRelationshipMd().definesAttribute().toLowerCase()+" = '"+ontologyRelationship.getId()+"' \n"+
      "      ) \n"+
      "     OR "+AllPaths.getIdMd().definesAttribute().toLowerCase()+" IN \n"+
      "      (SELECT "+RelationshipInfo.CHILD_ID+" \n"+
      "       FROM "+termRelationshipTable+" \n"+
      "       WHERE "+AllPaths.getOntologyRelationshipMd().definesAttribute().toLowerCase()+" = '"+ontologyRelationship.getId()+"' \n"+
      "      )\n"+
      "  ) AS inner_recurs_rel) AS recurs_rel \n" +
      "WHERE term1."+AllPaths.getIdMd().definesAttribute().toLowerCase()+" = recurs_rel."+RelationshipInfo.PARENT_ID+" AND term2."+AllPaths.getIdMd().definesAttribute().toLowerCase()+" = recurs_rel.root_id\n";

      Connection conn = Database.getConnection();

      PreparedStatement prepared = null;

      try
      {
        prepared = conn.prepareStatement(sql);
        prepared.setTimestamp(1, new Timestamp(transactionDate.getTime()));
        prepared.setTimestamp(2, new Timestamp(transactionDate.getTime()));
        prepared.executeUpdate();
      }
      catch (SQLException e)
      {
        throw new ProgrammingErrorException(e);
      }
      finally
      {
        if (prepared != null)
        {
          try
          {
            prepared.close();
          }
          catch (SQLException e)
          {
            throw new ProgrammingErrorException(e);
          }
        }
      }

    }
  }


  @Transaction
  public static void copyTermFast(String newParentTermId, String childTermId, String ontologyRelationshipId)
  {
    String allPathsTable = MdBusiness.getMdBusiness(AllPaths.CLASS).getTableName();
    String allPathsRootTypeId = IdParser.parseRootFromId(MdBusiness.getMdBusiness(AllPaths.CLASS).getId());
    String sitemaster = ServerProperties.getDomain();
    Date transactionDate = new Date();
    String createdById;
    SessionIF sessionIF = Session.getCurrentSession();
    if (sessionIF != null)
    {
      createdById = sessionIF.getUser().getId();
    }
    else
    {
      createdById = ServerConstants.SYSTEM_USER_ID;
    }

    String sql = "INSERT INTO "+allPathsTable+" (\n" +
    "  "+AllPaths.getIdMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getSiteMasterMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getKeyNameMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getTypeMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getEntityDomainMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getLastUpdateDateMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getSeqMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getCreatedByMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getLockedByMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getCreateDateMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getOwnerMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getLastUpdatedByMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getParentTermMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getChildTermMd().definesAttribute().toLowerCase()+",\n" +
    "  "+AllPaths.getOntologyRelationshipMd().definesAttribute().toLowerCase()+"\n" +
    ") \n" +
    " SELECT \n"+
    "   MD5(allpaths_parent."+AllPaths.getParentTermMd().definesAttribute().toLowerCase()+" || allpaths_child."+AllPaths.getChildTermMd().definesAttribute().toLowerCase()+" ) || '"+allPathsRootTypeId+"' AS newId,\n" +
    "    '"+sitemaster+"'                                       AS "+AllPaths.getSiteMasterMd().definesAttribute().toLowerCase()+",\n" +
    "   MD5(allpaths_parent."+AllPaths.getParentTermMd().definesAttribute().toLowerCase()+" || allpaths_child."+AllPaths.getChildTermMd().definesAttribute().toLowerCase()+" ) || '"+allPathsRootTypeId+"' AS newKey,\n" +
    "    '"+AllPaths.CLASS+"'                                   AS \""+AllPaths.getTypeMd().definesAttribute().toLowerCase()+"\",\n" +
    "    ''                                                     AS "+AllPaths.getEntityDomainMd().definesAttribute().toLowerCase()+",\n" +
    "    ?                                                      AS "+AllPaths.getLastUpdateDateMd().definesAttribute().toLowerCase()+",\n" +
    "    NEXTVAL('"+PostgreSQL.UNIQUE_OBJECT_ID_SEQUENCE+"')    AS "+AllPaths.getSeqMd().definesAttribute().toLowerCase()+",\n" +
    "    '"+createdById+"'                                      AS "+AllPaths.getCreatedByMd().definesAttribute().toLowerCase()+",\n" +
    "    NULL                                                   AS "+AllPaths.getLockedByMd().definesAttribute().toLowerCase()+",\n" +
    "    ?                                                      AS "+AllPaths.getCreatedByMd().definesAttribute().toLowerCase()+",\n" +
    "    '"+createdById+"'                                      AS \""+AllPaths.getOwnerMd().definesAttribute().toLowerCase()+"\",\n" +
    "    '"+createdById+"'                                      AS "+AllPaths.getLastUpdatedByMd().definesAttribute().toLowerCase()+",\n" +
    "    allpaths_parent."+AllPaths.getParentTermMd().definesAttribute().toLowerCase()+" AS "+AllPaths.getParentTermMd().definesAttribute().toLowerCase()+", \n" +
    "    allpaths_child."+AllPaths.getChildTermMd().definesAttribute().toLowerCase()+"   AS "+AllPaths.getChildTermMd().definesAttribute().toLowerCase()+", \n" +
    "    '"+ontologyRelationshipId+"'                           AS "+AllPaths.getOntologyRelationshipMd().definesAttribute().toLowerCase()+" \n" +
    " FROM \n"+
    // Fech all of the recursive children of the given child term, including the child term itself.
    "  (SELECT "+AllPaths.getChildTermMd().definesAttribute().toLowerCase()+" \n"+
    "    FROM "+allPathsTable+" \n"+
    "     WHERE "+AllPaths.getParentTermMd().definesAttribute().toLowerCase()+" = '"+childTermId+"' \n"+
    "       AND "+AllPaths.getOntologyRelationshipMd().definesAttribute().toLowerCase()+" = '"+ontologyRelationshipId+"') AS allpaths_child, \n"+
    // Fech all of the recursive parents of the given new parent term, including the new parent term itself.
    "  (SELECT "+AllPaths.getParentTermMd().definesAttribute().toLowerCase()+" \n"+
    "     FROM "+allPathsTable+" \n"+
    "    WHERE "+AllPaths.getChildTermMd().definesAttribute().toLowerCase()+" = '"+newParentTermId+"' \n"+
    "      AND "+AllPaths.getOntologyRelationshipMd().definesAttribute().toLowerCase()+" = '"+ontologyRelationshipId+"' \n"+
    "    ) AS allpaths_parent \n"+
    // Since a term can have multiple parents, a path to one of the new parent's parents may already exist
    " WHERE allpaths_parent."+AllPaths.getParentTermMd().definesAttribute().toLowerCase()+" NOT IN \n"+
    "   (SELECT "+AllPaths.getParentTermMd().definesAttribute().toLowerCase()+" \n"+
    "      FROM "+allPathsTable+" \n"+
    "     WHERE "+AllPaths.getParentTermMd().definesAttribute().toLowerCase()+" = allpaths_parent."+AllPaths.getParentTermMd().definesAttribute().toLowerCase()+" \n"+
    "      AND "+AllPaths.getChildTermMd().definesAttribute().toLowerCase()+" = allpaths_child."+AllPaths.getChildTermMd().definesAttribute().toLowerCase()+" \n"+
    "      AND "+AllPaths.getOntologyRelationshipMd().definesAttribute().toLowerCase()+" = '"+ontologyRelationshipId+"') \n";

    Connection conn = Database.getConnection();

    PreparedStatement prepared = null;

    try
    {
      prepared = conn.prepareStatement(sql);
      prepared.setTimestamp(1, new Timestamp(transactionDate.getTime()));
      prepared.setTimestamp(2, new Timestamp(transactionDate.getTime()));
      prepared.executeUpdate();
    }
    catch (SQLException e)
    {
      throw new ProgrammingErrorException(e);
    }
    finally
    {
      if (prepared != null)
      {
        try
        {
          prepared.close();
        }
        catch (SQLException e)
        {
          throw new ProgrammingErrorException(e);
        }
      }
    }
  }

  /**
   * Removes all AllPaths entries where the given term is a parent or child.
   *
   * @param termId
   */
  public static void deleteTermFromAllPaths(String termId)
  {
    MdBusiness mdBusinessAllPaths = MdBusiness.getMdBusiness(AllPaths.CLASS);

    String tableName = mdBusinessAllPaths.getTableName();

    String childTermColumn = AllPaths.getChildTermMd().definesAttribute();
    String parentTermColumn = AllPaths.getParentTermMd().definesAttribute();

    String procCallString = "DELETE FROM "+tableName+" WHERE "+childTermColumn+" = ? "
      + " OR "+parentTermColumn+" = ?";

    Connection conn = Database.getConnection();
    CallableStatement procCall = null;

    try
    {
      procCall = conn.prepareCall(procCallString);
      procCall.setString(1, termId);
      procCall.setString(2, termId);
      procCall.execute();
    }
    catch (SQLException e)
    {
      throw new ProgrammingErrorException(e);
    }
    finally
    {
      if (procCall != null)
      {
        try
        {
          procCall.close();
        }
        catch (SQLException e2)
        {
          throw new ProgrammingErrorException(e2);
        }
      }
    }
  }


  /**
   * Precondition:  Assumes the given term is a leaf node!!!
   */
  public static void deleteLeafFromAllPaths(String leafTermId)
  {
    MdBusiness mdBusinessAllPaths = MdBusiness.getMdBusiness(AllPaths.CLASS);

    String tableName = mdBusinessAllPaths.getTableName();

    String childTermColumn = AllPaths.getChildTermMd().definesAttribute();

    String procCallString = "DELETE FROM "+tableName+" WHERE "+childTermColumn+" = ?";

    Connection conn = Database.getConnection();
    CallableStatement procCall = null;

    try
    {
      procCall = conn.prepareCall(procCallString);
      procCall.setString(1, leafTermId);
      procCall.execute();
    }
    catch (SQLException e)
    {
      throw new ProgrammingErrorException(e);
    }
    finally
    {
      if (procCall != null)
      {
        try
        {
          procCall.close();
        }
        catch (SQLException e2)
        {
          throw new ProgrammingErrorException(e2);
        }
      }
    }
  }

  /**
   * This procedure rebuilds the allpaths table using the object API.  It is much slower than
   * using a database stored procedure or some SQL wizardry.
   */
  public static void updateAllPaths()
  {
    OntologyRelationship ontologyRelationship_IsA = OntologyRelationship.getByKey(OBO.IS_A);

    QueryFactory qf = new QueryFactory();

    TermQuery termQuery = new TermQuery(qf);

    ValueQuery q = new ValueQuery(qf);
    q.SELECT(termQuery.getId(ComponentInfo.ID));

    OIterator<ValueObject> i = q.getIterator();

    try
    {
      int applyCount = 0;
      ArrayList<String> ids = new ArrayList<String>(BATCH_SIZE);

      for (ValueObject valueObject : i)
      {
        String childId = valueObject.getValue(ComponentInfo.ID);
        ids.add(childId);
        if (ids.size() >= BATCH_SIZE) {
            applyCount = updateBatchOfPaths(ids, ontologyRelationship_IsA.getId(), applyCount);
            ids = new ArrayList<String>(BATCH_SIZE);
        }
      }

      if (ids.size() > 0)
      {
        applyCount = updateBatchOfPaths(ids, ontologyRelationship_IsA.getId(), applyCount);
      }
    }
    finally
    {
      i.close();
    }
  }

  @Transaction
  public static int updateBatchOfPaths(List<String> ids, String ontologyRelationshipId, int applyCount)
  {
      for (String id: ids)
      {
        applyCount = updateAllPathForTerm(id, ontologyRelationshipId, false, true, applyCount);
      }
      return applyCount;
  }

  public static void updateAllPathForTerm(String childId, String parentId, String ontologyRelationshipId)
  {
    updateAllPathForTerm(childId, parentId, ontologyRelationshipId, true, false, 0);
  }

  public static int updateAllPathForTermWithParent(String childId, String parentId, String ontologyRelationshipId, boolean copyChildren, boolean showTicker, int applyCount)
  {
    return updateAllPathForTerm(childId, parentId, ontologyRelationshipId, copyChildren, showTicker, applyCount);
  }

  public static int updateAllPathForTerm(String childId, String ontologyRelationshipId, boolean copyChildren, boolean showTicker, int applyCount)
  {
    return updateAllPathForTerm(childId, null, ontologyRelationshipId, copyChildren, showTicker, applyCount);
  }

  @Transaction
  private static int updateAllPathForTerm(String childId, String parentId, String ontologyRelationshipId, boolean copyChildren, boolean showTicker, int applyCount)
  {
    createPath(childId, childId, ontologyRelationshipId);

    if (showTicker)
    {
      applyCount = updateAllPathsTicker(applyCount);
    }

    // If an id of a parent is given, only build paths between this node, the given parent
    // and that parent's parents.  This is ideal for copies, so we don't have to traverse
    // the paths of existing parents.
    List<String> parentIdList;
    if (parentId != null)
    {
      parentIdList = Term.getRecursiveParentIds(parentId, ontologyRelationshipId);
      parentIdList.add(0, parentId);
    }
    else
    {
      parentIdList = Term.getRecursiveParentIds(childId, ontologyRelationshipId);
    }

    for (String someParentId : parentIdList)
    {
      createPath(someParentId, childId, ontologyRelationshipId);
      if (showTicker)
      {
        applyCount = updateAllPathsTicker(applyCount);
      }
    }

    if (copyChildren)
    {
      // Update paths of the children.
      List<String> childOfChildIdList = Term.getChildIds(childId, ontologyRelationshipId);
      for (String childOfChild : childOfChildIdList)
      {
        applyCount = updateAllPathForTerm(childOfChild, childId, ontologyRelationshipId, copyChildren, showTicker, applyCount);
      }
    }

    return applyCount;
  }

  private static void createPath(String parentId, String childId, String ontologyRelationship)
  {
    // create save point
    Savepoint savepoint = Database.setSavepoint();

    try
    {

      // Check if the entry already exists. If so, don't create it.
      AllPaths allPaths = new AllPaths();
      allPaths.setValue(AllPaths.PARENTTERM, parentId);
      allPaths.setValue(AllPaths.CHILDTERM, childId);
      allPaths.setValue(AllPaths.ONTOLOGYRELATIONSHIP, ontologyRelationship);
      allPaths.apply();

    }
    catch (DuplicateDataDatabaseException ex)
    {
      // This might happen. Relationship already exists.
      Database.rollbackSavepoint(savepoint);
    }
    catch (RuntimeException ex)
    {
      Database.rollbackSavepoint(savepoint);
      throw ex;
    }
    finally
    {
      Database.releaseSavepoint(savepoint);
    }
  }

  private static int updateAllPathsTicker(int applyCount)
  {
    System.out.print(".");
    applyCount++;

    if (applyCount % dss.vector.solutions.util.GeoEntityImporter.feedbackMod == 0)
    {
      System.out.println();
    }
    return applyCount;
  }

}
