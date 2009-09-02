package dss.vector.solutions;

import java.util.Arrays;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.generation.facade.ControllerStubGenerator;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.TypeGeneratorInfo;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeReferenceDAOIF;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.MdControllerDAOIF;
import com.terraframe.mojo.dataaccess.MdEntityDAOIF;
import com.terraframe.mojo.dataaccess.MdMethodDAOIF;
import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class MDSSControllerStubGenerator extends ControllerStubGenerator implements Reloadable
{

  public MDSSControllerStubGenerator(MdControllerDAOIF mdController)
  {
    super(mdController);
  }

  @Override
  protected void writeUpdateAction(String args)
  {
    getWriter().writeLine("try");
    getWriter().openBracket();
    getWriter().writeLine("dto.apply();");
    getWriter().writeLine("this.view(dto.getId());");
    getWriter().closeBracket();
    getWriter().writeLine("catch(" + ProblemExceptionDTO.class.getName() + " e)");
    getWriter().openBracket();
    getWriter().writeLine(ErrorUtility.class.getName() + ".prepareProblems(e, req);");
    getWriter().writeLine("this.failUpdate(" + args + ");");
    getWriter().closeBracket();
    getWriter().writeLine("catch(" + Throwable.class.getName() + " t)");
    getWriter().openBracket();
    getWriter().writeLine(ErrorUtility.class.getName() + ".prepareThrowable(t, req);");
    getWriter().writeLine("this.failUpdate(" + args + ");");
    getWriter().closeBracket();
  }

  @Override
  protected void writeDeleteAction(String args)
  {
    getWriter().writeLine("try");
    getWriter().openBracket();
    getWriter().writeLine("dto.delete();");
    getWriter().writeLine("this.viewAll();");
    getWriter().closeBracket();
    getWriter().writeLine("catch(" + ProblemExceptionDTO.class.getName() + " e)");
    getWriter().openBracket();
    getWriter().writeLine(ErrorUtility.class.getName() + ".prepareProblems(e, req);");
    getWriter().writeLine("this.failDelete(" + args + ");");
    getWriter().closeBracket();
    getWriter().writeLine("catch(" + Throwable.class.getName() + " t)");
    getWriter().openBracket();
    getWriter().writeLine(ErrorUtility.class.getName() + ".prepareThrowable(t, req);");
    getWriter().writeLine("this.failDelete(" + args + ");");
    getWriter().closeBracket();
  }

  @Override
  protected void writeCreateAction(String args)
  {
    getWriter().writeLine("try");
    getWriter().openBracket();
    getWriter().writeLine("dto.apply();");
    getWriter().writeLine("this.view(dto.getId());");
    getWriter().closeBracket();
    getWriter().writeLine("catch(" + ProblemExceptionDTO.class.getName() + " e)");
    getWriter().openBracket();
    getWriter().writeLine(ErrorUtility.class.getName() + ".prepareProblems(e, req);");
    getWriter().writeLine("this.failCreate(" + args + ");");
    getWriter().closeBracket();
    getWriter().writeLine("catch(" + Throwable.class.getName() + " t)");
    getWriter().openBracket();
    getWriter().writeLine(ErrorUtility.class.getName() + ".prepareThrowable(t, req);");
    getWriter().writeLine("this.failCreate(" + args + ");");
    getWriter().closeBracket();
  }

  @Override
  protected void writeViewAction(MdEntityDAOIF mdEntity)
  {
    getWriter().writeLine(RedirectUtility.class.getName() + " utility = new " + RedirectUtility.class.getName() + "(req, resp);");
    getWriter().writeLine("utility.put(\"id\", id);");
    getWriter().writeLine("utility.checkURL(this.getClass().getSimpleName(), \"view\");");

    String clientRequest = ClientRequestIF.class.getName();

    getWriter().writeLine(clientRequest + " clientRequest = super.getClientRequest();");

    // Load options for Enumeration and Reference attributes
    this.generateRequestsForReferencesAndEnumerations(mdEntity);

    getWriter().writeLine("req.setAttribute(\"item\", " + mdEntity.definesType() + "DTO.get(clientRequest, id));");

    writeRender("View " + mdEntity.getTypeName(), "viewComponent.jsp");
  }

  @Override
  protected void generateReferenceList(MdAttributeDAOIF mdAttribute)
  {
    MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttribute;
    MdBusinessDAOIF mdBusiness = mdAttributeReference.getReferenceMdBusinessDAO();

    if (mdBusiness.isPublished() && !MDSSGenerationUtility.isAGeoEntity(mdBusiness))
    {
      String request = "super.getClientSession().getRequest()";
      String dtoType = mdBusiness.definesType() + TypeGeneratorInfo.DTO_SUFFIX;
      String methodName = "getAllInstances";
      String command = dtoType + "." + methodName + "(" + request + ", \"keyName\", true, 0, 0).getResultSet()";

      try
      {
        MdMethodDAOIF mdMethod = mdBusiness.definesMdMethod("getAllActive");

        command = Arrays.class.getName() + ".asList(" + dtoType + "." + mdMethod.getName() + "(" + request + "))";
      }
      catch (DataNotFoundException e)
      {
        try
        {
          MdMethodDAOIF mdMethod = mdBusiness.definesMdMethod("getAll");

          command = Arrays.class.getName() + ".asList(" + dtoType + "." + mdMethod.getName() + "(" + request + "))";
        }
        catch (DataNotFoundException e1)
        {
          // Do nothing
        }
      }
      // If the referenced MdBusinessDAO is not published it is impossible to
      // populate
      getWriter().writeLine("req.setAttribute(\"" + mdAttribute.definesAttribute() + "\", " + command + ");");
    }
  }
}
