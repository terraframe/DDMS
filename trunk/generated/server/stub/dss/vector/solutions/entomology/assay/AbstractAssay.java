package dss.vector.solutions.entomology.assay;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.constants.MdBusinessInfo;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.mo.AbstractTerm;

public abstract class AbstractAssay extends AbstractAssayBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234543768433L;

  public AbstractAssay()
  {
    super();
  }

  @Override
  public void apply()
  {
    validateTestDate();

    super.apply();
  }

  @Override
  public void validateTestDate()
  {
    if (this.getTestDate() != null)
    {
      super.validateTestDate();

      Date current = new Date();

      if (current.before(this.getTestDate()))
      {
        String msg = "It is impossible to have a test date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getTestDate());
        p.setCurrentDate(current);
        p.setNotification(this, TESTDATE);
        p.apply();
        p.throwIt();
      }
    }
  }

  /**
   * Returns a JSON String that represents the Assay hierarchy.
   *
   * @return
   */
  public static String getAssayTree()
  {
    try
    {
      MdBusiness root = MdBusiness.getMdBusiness(AbstractTerm.CLASS);

      JSONArray rootChildren = new JSONArray();
      JSONObject rootNode = treeRecurse(root);
      rootChildren.put(rootNode);

      return rootChildren.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  /**
   * Recurses into the AbstractAssay hierarchy.
   *
   * @param parent
   * @return
   * @throws JSONException
   */
  private static JSONObject treeRecurse(MdBusiness parent) throws JSONException
  {
    OIterator<? extends MdBusiness> iter = parent.getAllSubClass();

    JSONObject parentNode = new JSONObject();
    JSONArray children = new JSONArray();

    String type = parent.getPackageName() + "." + parent.getTypeName();
    parentNode.put(MdBusinessInfo.ID, parent.getId());
    parentNode.put(MdBusinessInfo.CLASS, type);
    parentNode.put(MdBusinessInfo.ABSTRACT, parent.getIsAbstract());
    parentNode.put(MdBusinessInfo.DISPLAY_LABEL, parent.getDisplayLabel());
    parentNode.put("children", children);

    try
    {

      while (iter.hasNext())
      {
        MdBusiness child = iter.next();

        JSONObject childNode = treeRecurse(child);

        children.put(childNode);
      }
    }
    finally
    {
      iter.close();
    }

    return parentNode;
  }



}
