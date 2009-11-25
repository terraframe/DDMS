package dss.vector.solutions.ontology;

import java.security.SecureRandom;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.terraframe.mojo.constants.ComponentInfo;
import com.terraframe.mojo.constants.ServerConstants;
import com.terraframe.mojo.constants.ServerProperties;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.dataaccess.database.DuplicateDataDatabaseException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.session.SessionIF;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.system.metadata.MdBusiness;
import com.terraframe.mojo.util.IdParser;


public class AllPaths extends AllPathsBase implements com.terraframe.mojo.generation.loader.Reloadable
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

//    updateAllPaths();

    updateAllPathsStoredProc();
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

  @Transaction
  public static void updateAllPathsStoredProc()
  {
    Connection conn = Database.getConnection();

    MdBusiness mdBussinessAllPaths = MdBusiness.getMdBusiness(AllPaths.CLASS);

    String allPathsRootTypeId = IdParser.parseRootFromId(mdBussinessAllPaths.getId());
    SecureRandom random = new SecureRandom();
    long randomLong = random.nextLong();
    String domain = ServerProperties.getDomain();

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
    Date transactionDate = new Date();
    QueryFactory qf = new QueryFactory();
    OntologyRelationshipQuery orQ = new OntologyRelationshipQuery(qf);

    for (OntologyRelationship ontologyRelationship : orQ.getIterator())
    {
      String ontologyRelationshipId = ontologyRelationship.getId();
      String procCallString = "{ call dss_ontology_build_allpaths(?, ?, ?, ?, ?, ?)}";

      CallableStatement procCall = null;

      try
      {
        procCall = conn.prepareCall(procCallString);
        procCall.setString(1, allPathsRootTypeId);
        procCall.setLong(2, randomLong);
        procCall.setString(3, domain);
        procCall.setString(4, createdById);
        procCall.setDate(5, new java.sql.Date(transactionDate.getTime()));
        procCall.setString(6, ontologyRelationshipId);
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

  }

  public static void copyTerm(String newParentTermId, String childTerm, String ontologyRelationshipId)
  {
    Connection conn = Database.getConnection();

    MdBusiness mdBussinessAllPaths = MdBusiness.getMdBusiness(AllPaths.CLASS);

    String allPathsRootTypeId = IdParser.parseRootFromId(mdBussinessAllPaths.getId());
    SecureRandom random = new SecureRandom();
    long randomLong = random.nextLong();
    String domain = ServerProperties.getDomain();

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
    Date transactionDate = new Date();

    String procCallString = "{ call dss_ontology_copy_term(?, ?, ?, ?, ?, ?, ?, ?)}";

    CallableStatement procCall = null;

    try
    {
      procCall = conn.prepareCall(procCallString);
      procCall.setString(1, allPathsRootTypeId);
      procCall.setLong(2, randomLong);
      procCall.setString(3, domain);
      procCall.setString(4, createdById);
      procCall.setDate(5, new java.sql.Date(transactionDate.getTime()));
      procCall.setString(6, newParentTermId);
      procCall.setString(7, childTerm);
      procCall.setString(8, ontologyRelationshipId);
      procCall.execute();

      /*
      _allPathsRootTypeId     VARCHAR,
      _random                 BIGINT,
      _sitemaster             allpaths.sitemaster%TYPE,
      _createdById            allpaths.id%TYPE,
      _transactionDate        allpaths.createdate%TYPE,
      _newParentTerm          allpaths.parentterm%TYPE,
      _childTerm              allpaths.childterm%TYPE,
      _ontologyRelationshipId allpaths.ontologyrelationship%TYPE
    */
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
