package dss.vector.solutions.geoserver;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.runwaysdk.constants.VaultProperties;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Request;

import dss.vector.solutions.SessionParameterFacade;
import dss.vector.solutions.util.DatabaseUtil;
import dss.vector.solutions.util.Iterables;

public class CleanupFacade implements Reloadable
{
  @Request
  public static void cleanupUnusedLayers()
  {
    cleanupUnusedLayers_Transaction();
  }

  @Transaction
  private static void cleanupUnusedLayers_Transaction()
  {
    List<String> viewNames = DatabaseUtil.getViewsByPrefix(SessionPredicate.PREFIX);

    new Iterables<String>().remove(viewNames, new SessionPredicate());

    for (String viewName : viewNames)
    {
      // First remove the geoserver layer
      GeoserverFacade.removeLayer(viewName);

      // Clear the session map
      String sessionId = SessionPredicate.getSessionId(viewName);
      SessionParameterFacade.clear(sessionId);
    }

    // Second delete the database views
    DatabaseUtil.dropViews(viewNames);
  }

  @Request
  public static void cleanupUnusedFiles()
  {
    cleanupUnusedFiles_Transaction();
  }

  @Transaction
  private static void cleanupUnusedFiles_Transaction()
  {
    File root = new File(new File(VaultProperties.getPath("vault.default")), "files");

    if (!root.exists())
    {
      String[] list = root.list();

      if (list != null && list.length > 0)
      {
        LinkedList<String> names = new LinkedList<String>(Arrays.asList(list));

        new Iterables<String>().remove(names, new SessionPredicate());

        for (String name : names)
        {
          File directory = new File(new File(VaultProperties.getPath("vault.default"), "files"), name);

          try
          {
            FileUtils.deleteDirectory(directory);
          }
          catch (IOException e)
          {
            throw new ProgrammingErrorException(e);
          }
        }
      }
    }
  }
}
