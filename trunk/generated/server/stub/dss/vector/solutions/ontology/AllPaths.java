package dss.vector.solutions.ontology;

import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import com.terraframe.mojo.constants.ComponentInfo;
import com.terraframe.mojo.dataaccess.MdClassDAOIF;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.dataaccess.database.DuplicateDataDatabaseException;
import com.terraframe.mojo.dataaccess.metadata.MdClassDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.system.metadata.MdBusiness;
import com.terraframe.mojo.system.metadata.MdRelationship;
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

  private static void rebuildAllPaths()
  {
    MdBusiness mdBusiness = MdBusiness.getMdBusiness(AllPaths.CLASS);

    mdBusiness.deleteAllTableRecords();

    updateAllPaths();
  }

  public static void updateAllPaths()
  {
    MdRelationship allPathsMdRelationship = (MdRelationship)MdRelationship.getMdElement(IsA.CLASS);

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
            applyCount = updateBatchOfPaths(ids, allPathsMdRelationship.getId(), applyCount);
            ids = new ArrayList<String>(BATCH_SIZE);
        }
      }

      if (ids.size() > 0)
      {
        applyCount = updateBatchOfPaths(ids, allPathsMdRelationship.getId(), applyCount);
      }
    }
    finally
    {
      i.close();
    }
  }

  @Transaction
  public static int updateBatchOfPaths(List<String> ids, String ontologyMdRelationshipId, int applyCount) {
      for (String id: ids)
      {
        applyCount = updateAllPathForTerm(id, ontologyMdRelationshipId, false, true, applyCount);
      }
      return applyCount;
  }

  public static void updateAllPathForTerm(String childId, String parentId, String ontologyMdRelationshipId)
  {
    updateAllPathForTerm(childId, parentId, ontologyMdRelationshipId, true, false, 0);
  }

  public static int updateAllPathForTermWithParent(String childId, String parentId, String ontologyMdRelationshipId, boolean copyChildren, boolean showTicker, int applyCount)
  {
    return updateAllPathForTerm(childId, parentId, ontologyMdRelationshipId, copyChildren, showTicker, applyCount);
  }

  public static int updateAllPathForTerm(String childId, String ontologyMdRelationshipId, boolean copyChildren, boolean showTicker, int applyCount)
  {
    return updateAllPathForTerm(childId, null, ontologyMdRelationshipId, copyChildren, showTicker, applyCount);
  }

  @Transaction
  private static int updateAllPathForTerm(String childId, String parentId, String ontologyMdRelationshipId, boolean copyChildren, boolean showTicker, int applyCount)
  {
    MdClassDAOIF childMdClassIF = MdClassDAO.getMdClassByRootId(IdParser
        .parseMdTypeRootIdFromId(childId));

    createPath(childId, childMdClassIF.getId(), childId, childMdClassIF.getId(), ontologyMdRelationshipId);

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
      parentIdList = Term.getRecursiveParentIds(parentId, ontologyMdRelationshipId);
      parentIdList.add(0, parentId);
    }
    else
    {
      parentIdList = Term.getRecursiveParentIds(childId, ontologyMdRelationshipId);
    }

    for (String someParentId : parentIdList)
    {
      MdClassDAOIF parentMdClassIF = MdClassDAO.getMdClassByRootId(IdParser
          .parseMdTypeRootIdFromId(someParentId));
      createPath(someParentId, parentMdClassIF.getId(), childId, childMdClassIF.getId(), ontologyMdRelationshipId);
      if (showTicker)
      {
        applyCount = updateAllPathsTicker(applyCount);
      }
    }

    if (copyChildren)
    {
      // Update paths of the children.
      List<String> childOfChildIdList = Term.getChildIds(childId, ontologyMdRelationshipId);
      for (String childOfChild : childOfChildIdList)
      {
        applyCount = updateAllPathForTerm(childOfChild, childId, ontologyMdRelationshipId, copyChildren, showTicker, applyCount);
      }
    }

    return applyCount;
  }

  private static void createPath(String parentId, String parentMdBusiness, String childId,
      String childMdBusiness, String ontologyMdRelationship)
  {
    // create save point
    Savepoint savepoint = Database.setSavepoint();

    try
    {

      // Check if the entry already exists. If so, don't create it.
      AllPaths allPaths = new AllPaths();
      allPaths.setValue(AllPaths.PARENTTERM, parentId);
      allPaths.setValue(AllPaths.PARENTONTOLOGYMDBUSINESS, parentMdBusiness);
      allPaths.setValue(AllPaths.CHILDTERM, childId);
      allPaths.setValue(AllPaths.CHILDONTOLOGYMDBUSINESS, childMdBusiness);
      allPaths.setValue(AllPaths.ONTOLOGYRELATIONSHIP, ontologyMdRelationship);
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
