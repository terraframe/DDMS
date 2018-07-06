/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.ontology;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.constants.ComponentInfo;
import com.runwaysdk.constants.RelationshipInfo;
import com.runwaysdk.constants.ServerConstants;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.database.DuplicateDataDatabaseException;
import com.runwaysdk.dataaccess.database.general.PostgreSQL;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Request;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionIF;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdRelationship;
import com.runwaysdk.util.IdParser;

import dss.vector.solutions.util.QueryUtil;

public class AllPaths extends AllPathsBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final Logger logger = LoggerFactory.getLogger(AllPaths.class);
  
  private static final long serialVersionUID = 1253040252503L;

  public static int         BATCH_SIZE       = 1000;

  public AllPaths()
  {
    super();
  }

  /**
   * @param args
   */
  @Request
  public static void main(String[] args)
  {
    logger.info("Starting allpaths table rebuild.");
    
    long start = System.currentTimeMillis();
    try
    {
      rebuildAllPaths();
    }
    finally
    {
      CacheShutdown.shutdown();
    }
    long finish = System.currentTimeMillis();
    
    logger.info("Rebuilding the allpaths table took [" + (finish - start) + "] ms.");
  }

  /**
   * Only one thread can rebuild this table at a time.
   */
  @Transaction
  public synchronized static void rebuildAllPaths()
  {
    rebuildAllPathsInner();
  }

  public static void rebuildAllPathsInner()
  {
    deleteAllTableRecords();

    buildAllPathsFast();
  }

  public static void deleteAllTableRecords()
  {
    MdBusiness mdBusiness = MdBusiness.getMdBusiness(AllPaths.CLASS);

    mdBusiness.deleteAllTableRecords();
  }

  public static void buildAllPathsFast()
  {
    String termTable = MdBusiness.getMdBusiness(Term.CLASS).getTableName();
    String termRelationshipTable = MdRelationship.getMdElement(TermRelationship.CLASS).getTableName();
    String allPathsTable = MdBusiness.getMdBusiness(AllPaths.CLASS).getTableName();
    String allPathsRootTypeId = IdParser.parseRootFromId(MdBusiness.getMdBusiness(AllPaths.CLASS).getId());
    String sitemaster = CommonProperties.getDomain();
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
      String sql = "INSERT INTO " + allPathsTable + " (\n" + "  " + AllPaths.getIdMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getSiteMasterMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getKeyNameMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getTypeMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getEntityDomainMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  "
          + AllPaths.getLastUpdateDateMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getSeqMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getCreatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getLockedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getCreateDateMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getOwnerMd().getMdAttributeConcrete().getColumnName()
          + ",\n" + "  " + AllPaths.getLastUpdatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getParentTermMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getChildTermMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getOntologyRelationshipMd().getMdAttributeConcrete().getColumnName() + "\n" + ") \n" +

          "SELECT  \n" + "    md5(term1." + AllPaths.getIdMd().getMdAttributeConcrete().getColumnName() + " || term2." + AllPaths.getIdMd().getMdAttributeConcrete().getColumnName() + " ) || '" + allPathsRootTypeId + "',\n" + "    '" + sitemaster + "'                                       AS " + AllPaths.getSiteMasterMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    md5(term1." + AllPaths.getIdMd().getMdAttributeConcrete().getColumnName() + " || term2."
          + AllPaths.getIdMd().getMdAttributeConcrete().getColumnName() + " ) || '" + allPathsRootTypeId + "' AS " + AllPaths.getKeyNameMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    '" + AllPaths.CLASS + "'                                   AS \"" + AllPaths.getTypeMd().getMdAttributeConcrete().getColumnName() + "\",\n" + "    ''                                                     AS " + AllPaths.getEntityDomainMd().getMdAttributeConcrete().getColumnName() + ",\n"
          + "    ?                                                      AS " + AllPaths.getLastUpdateDateMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    NEXTVAL('" + PostgreSQL.OBJECT_UPDATE_SEQUENCE + "')    AS " + AllPaths.getSeqMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    '" + createdById + "'                                      AS " + AllPaths.getCreatedByMd().getMdAttributeConcrete().getColumnName() + ",\n"
          + "    null                                                   AS " + AllPaths.getLockedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    ?                                                      AS " + AllPaths.getCreatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    '" + createdById + "'                                      AS \"" + AllPaths.getOwnerMd().getMdAttributeConcrete().getColumnName() + "\",\n" + "    '" + createdById
          + "'                                      AS " + AllPaths.getLastUpdatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    recurs_rel." + RelationshipInfo.PARENT_ID + "              AS " + AllPaths.getParentTermMd().getMdAttributeConcrete().getColumnName() + ", \n" + "    recurs_rel.root_id                                     AS " + AllPaths.getChildTermMd().getMdAttributeConcrete().getColumnName() + ", \n" + "    '" + ontologyRelationship.getId()
          + "'                      AS " + AllPaths.getOntologyRelationshipMd().getMdAttributeConcrete().getColumnName() + " \n" +

          "FROM " + termTable + " as term1, " + termTable + " as term2,\n" + " (SELECT root_id, " + RelationshipInfo.PARENT_ID + ", " + RelationshipInfo.CHILD_ID + " \n" + " FROM \n" + "  ( WITH RECURSIVE quick_paths AS \n" + "    ( SELECT " + RelationshipInfo.CHILD_ID + " AS root_id, " + RelationshipInfo.CHILD_ID + ", " + RelationshipInfo.PARENT_ID + " \n" + "      FROM " + termRelationshipTable + " \n" + "      WHERE " + termRelationshipTable + "."
          + AllPaths.getOntologyRelationshipMd().getMdAttributeConcrete().getColumnName() + " = '" + ontologyRelationship.getId() + "' \n" + "      UNION \n" + "      SELECT a.root_id, b." + RelationshipInfo.CHILD_ID + ", b." + RelationshipInfo.PARENT_ID + " \n" + "      FROM quick_paths a, " + termRelationshipTable + " b \n" + "      WHERE b." + RelationshipInfo.CHILD_ID + " = a." + RelationshipInfo.PARENT_ID + " \n" + "      AND b."
          + AllPaths.getOntologyRelationshipMd().getMdAttributeConcrete().getColumnName() + " = '" + ontologyRelationship.getId() + "' \n" + "    ) \n" + "    SELECT root_id, root_id as " + RelationshipInfo.CHILD_ID + ", " + RelationshipInfo.PARENT_ID + " \n" + "    FROM quick_paths \n" + "    UNION \n" + "    SELECT " + AllPaths.getIdMd().getMdAttributeConcrete().getColumnName() + ", " + AllPaths.getIdMd().getMdAttributeConcrete().getColumnName() + ", "
          + AllPaths.getIdMd().getMdAttributeConcrete().getColumnName() + " \n" + "    FROM " + termTable + " \n" + "    WHERE " + AllPaths.getIdMd().getMdAttributeConcrete().getColumnName() + " IN \n" + "      (SELECT " + RelationshipInfo.PARENT_ID + " \n" + "       FROM " + termRelationshipTable + " \n" + "       WHERE " + AllPaths.getOntologyRelationshipMd().getMdAttributeConcrete().getColumnName() + " = '" + ontologyRelationship.getId() + "' \n" + "      ) \n" + "     OR "
          + AllPaths.getIdMd().getMdAttributeConcrete().getColumnName() + " IN \n" + "      (SELECT " + RelationshipInfo.CHILD_ID + " \n" + "       FROM " + termRelationshipTable + " \n" + "       WHERE " + AllPaths.getOntologyRelationshipMd().getMdAttributeConcrete().getColumnName() + " = '" + ontologyRelationship.getId() + "' \n" + "      )\n" + "  ) AS inner_recurs_rel) AS recurs_rel \n" + "WHERE term1." + AllPaths.getIdMd().getMdAttributeConcrete().getColumnName() + " = recurs_rel."
          + RelationshipInfo.PARENT_ID + " AND term2." + AllPaths.getIdMd().getMdAttributeConcrete().getColumnName() + " = recurs_rel.root_id\n";

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
    String sitemaster = CommonProperties.getDomain();
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

    String sql = "INSERT INTO " + allPathsTable + " (\n" + "  " + AllPaths.getIdMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getSiteMasterMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getKeyNameMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getTypeMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getEntityDomainMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  "
        + AllPaths.getLastUpdateDateMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getSeqMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getCreatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getLockedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getCreateDateMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getOwnerMd().getMdAttributeConcrete().getColumnName() + ",\n"
        + "  " + AllPaths.getLastUpdatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getParentTermMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getChildTermMd().getMdAttributeConcrete().getColumnName() + ",\n" + "  " + AllPaths.getOntologyRelationshipMd().getMdAttributeConcrete().getColumnName() + "\n" + ") \n" + " SELECT \n" + "   MD5(allpaths_parent." + AllPaths.getParentTermMd().getMdAttributeConcrete().getColumnName()
        + " || allpaths_child." + AllPaths.getChildTermMd().getMdAttributeConcrete().getColumnName() + " ) || '" + allPathsRootTypeId + "' AS newId,\n" + "    '" + sitemaster + "'                                       AS " + AllPaths.getSiteMasterMd().getMdAttributeConcrete().getColumnName() + ",\n" + "   MD5(allpaths_parent." + AllPaths.getParentTermMd().getMdAttributeConcrete().getColumnName() + " || allpaths_child." + AllPaths.getChildTermMd().getMdAttributeConcrete().getColumnName()
        + " ) || '" + allPathsRootTypeId + "' AS newKey,\n" + "    '" + AllPaths.CLASS + "'                                   AS \"" + AllPaths.getTypeMd().getMdAttributeConcrete().getColumnName() + "\",\n" + "    ''                                                     AS " + AllPaths.getEntityDomainMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    ?                                                      AS " + AllPaths.getLastUpdateDateMd().getMdAttributeConcrete().getColumnName()
        + ",\n" + "    NEXTVAL('" + PostgreSQL.OBJECT_UPDATE_SEQUENCE + "')    AS " + AllPaths.getSeqMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    '" + createdById + "'                                      AS " + AllPaths.getCreatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    NULL                                                   AS " + AllPaths.getLockedByMd().getMdAttributeConcrete().getColumnName() + ",\n"
        + "    ?                                                      AS " + AllPaths.getCreatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    '" + createdById + "'                                      AS \"" + AllPaths.getOwnerMd().getMdAttributeConcrete().getColumnName() + "\",\n" + "    '" + createdById + "'                                      AS " + AllPaths.getLastUpdatedByMd().getMdAttributeConcrete().getColumnName() + ",\n" + "    allpaths_parent."
        + AllPaths.getParentTermMd().getMdAttributeConcrete().getColumnName() + " AS " + AllPaths.getParentTermMd().getMdAttributeConcrete().getColumnName() + ", \n" + "    allpaths_child." + AllPaths.getChildTermMd().getMdAttributeConcrete().getColumnName() + "   AS " + AllPaths.getChildTermMd().getMdAttributeConcrete().getColumnName() + ", \n" + "    '" + ontologyRelationshipId + "'                           AS " + AllPaths.getOntologyRelationshipMd().getMdAttributeConcrete().getColumnName()
        + " \n"
        + " FROM \n"
        +
        // Fech all of the recursive children of the given child term, including
        // the child term itself.
        "  (SELECT " + AllPaths.getChildTermMd().getMdAttributeConcrete().getColumnName() + " \n" + "    FROM " + allPathsTable + " \n" + "     WHERE " + AllPaths.getParentTermMd().getMdAttributeConcrete().getColumnName() + " = '" + childTermId + "' \n" + "       AND " + AllPaths.getOntologyRelationshipMd().getMdAttributeConcrete().getColumnName() + " = '" + ontologyRelationshipId
        + "') AS allpaths_child, \n"
        +
        // Fech all of the recursive parents of the given new parent term,
        // including the new parent term itself.
        "  (SELECT " + AllPaths.getParentTermMd().getMdAttributeConcrete().getColumnName() + " \n" + "     FROM " + allPathsTable + " \n" + "    WHERE " + AllPaths.getChildTermMd().getMdAttributeConcrete().getColumnName() + " = '" + newParentTermId + "' \n" + "      AND " + AllPaths.getOntologyRelationshipMd().getMdAttributeConcrete().getColumnName() + " = '" + ontologyRelationshipId + "' \n"
        + "    ) AS allpaths_parent \n"
        +
        // Since a term can have multiple parents, a path to one of the new
        // parent's parents may already exist
        " WHERE allpaths_parent." + AllPaths.getParentTermMd().getMdAttributeConcrete().getColumnName() + " NOT IN \n" + "   (SELECT " + AllPaths.getParentTermMd().getMdAttributeConcrete().getColumnName() + " \n" + "      FROM " + allPathsTable + " \n" + "     WHERE " + AllPaths.getParentTermMd().getMdAttributeConcrete().getColumnName() + " = allpaths_parent." + AllPaths.getParentTermMd().getMdAttributeConcrete().getColumnName() + " \n" + "      AND "
        + AllPaths.getChildTermMd().getMdAttributeConcrete().getColumnName() + " = allpaths_child." + AllPaths.getChildTermMd().getMdAttributeConcrete().getColumnName() + " \n" + "      AND " + AllPaths.getOntologyRelationshipMd().getMdAttributeConcrete().getColumnName() + " = '" + ontologyRelationshipId + "') \n";

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
   * Removes the term and all its children from the allpaths table.
   * 
   * @param termId
   */
  public static void deleteTermAndChildrenFromAllPaths(String rootId)
  {
    // Queue results in a breadth first traverse
    Queue<String> qNext = new ArrayDeque<String>();
    qNext.offer(rootId);
    
    while (qNext.size() > 0)
    {
      String sCurrent = qNext.poll();
      Term tCurrent = Term.get(sCurrent);
      
      deleteTermFromAllPaths(sCurrent);
      
      List<String> children = Term.getChildIds(tCurrent.getId(), null);
      for (String child: children)
      {
        qNext.offer(child);
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

    String childTermColumn = QueryUtil.getColumnName(AllPaths.getChildTermMd());
    String parentTermColumn = QueryUtil.getColumnName(AllPaths.getParentTermMd());

    String procCallString = "DELETE FROM " + tableName + " WHERE " + childTermColumn + " = ? " + " OR " + parentTermColumn + " = ?";

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
   * Precondition: Assumes the given term is a leaf node!!!
   */
  public static void deleteLeafFromAllPaths(String leafTermId)
  {
    MdBusiness mdBusinessAllPaths = MdBusiness.getMdBusiness(AllPaths.CLASS);

    String tableName = mdBusinessAllPaths.getTableName();

    String childTermColumn = QueryUtil.getColumnName(AllPaths.getChildTermMd());

    String procCallString = "DELETE FROM " + tableName + " WHERE " + childTermColumn + " = ?";

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
   * This procedure rebuilds the allpaths table using the object API. It is much
   * slower than using a database stored procedure or some SQL wizardry.
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
        if (ids.size() >= BATCH_SIZE)
        {
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
    for (String id : ids)
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

    // If an id of a parent is given, only build paths between this node, the
    // given parent
    // and that parent's parents. This is ideal for copies, so we don't have to
    // traverse
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
        if (parentId != null)
        {
          applyCount = updateAllPathForTerm(childOfChild, childId, ontologyRelationshipId, copyChildren, showTicker, applyCount);
        }
        else
        {
          applyCount = updateAllPathForTerm(childOfChild, null, ontologyRelationshipId, copyChildren, showTicker, applyCount);
        }
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
      AllPaths allPaths = new AllPaths();
      allPaths.setValue(AllPaths.PARENTTERM, parentId);
      allPaths.setValue(AllPaths.CHILDTERM, childId);
      allPaths.setValue(AllPaths.ONTOLOGYRELATIONSHIP, ontologyRelationship);
      allPaths.apply();
      
      // Performing this via insert statement and bypassing the validation in the object API is 15% faster
//      MdBusinessDAO mdBiz = MdBusinessDAO.getMdBusinessDAO(AllPaths.CLASS).getBusinessDAO();
//      String allPathsTbl = mdBiz.getTableName();
//      List<String> tableAttrs = Arrays.asList( MdAttributeCharacterInfo.CLASS, MdAttributeCharacterInfo.CLASS, MdAttributeCharacterInfo.CLASS );
//      List<String> columns = Arrays.asList(mdBiz.definesAttribute(AllPaths.PARENTTERM).getColumnName(), mdBiz.definesAttribute(AllPaths.CHILDTERM).getColumnName(), mdBiz.definesAttribute(AllPaths.ONTOLOGYRELATIONSHIP).getColumnName());
//      
//      List<PreparedStatement> statements = new ArrayList<PreparedStatement>();
//      
//      List<String> bindVals = Arrays.asList("?","?","?");
//      List<Object> vals = Arrays.asList(parentId, childId, ontologyRelationship);
//      
//      PreparedStatement preparedStmt = Database.buildPreparedSQLInsertStatement(allPathsTbl, columns, bindVals, vals, tableAttrs);
//      statements.add(preparedStmt);
//      
//      Database.executeStatementBatch(statements);
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

  @Request
  public static boolean containsValues()
  {
    QueryFactory f2 = new QueryFactory();
    ValueQuery termVQ = new ValueQuery(f2);
    AllPathsQuery termAP = new AllPathsQuery(termVQ);
    termVQ.SELECT(termAP.getId());

    return ( termVQ.getCount() > 0 );
  }

}
