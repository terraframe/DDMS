package dss.vector.solutions;

import java.util.Arrays;

import com.runwaysdk.business.generation.facade.ControllerStubGenerator;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.constants.MdAttributeReferenceInfo;
import com.runwaysdk.constants.TypeGeneratorInfo;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdControllerDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.MdMethodDAOIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtilityInfo;

public class MDSSControllerStubGenerator extends ControllerStubGenerator implements Reloadable
{

  public MDSSControllerStubGenerator(MdControllerDAOIF mdController)
  {
    super(mdController);
  }
  
  protected void writeFields()
  {
    MdEntityDAOIF mdEntity = this.getMdTypeDAOIF().getMdEntity();

    if (mdEntity != null)
    {
      String jsp = CommonGenerationUtil.replacePackageDotsWithSlashes(mdEntity.definesType());

      getWriter().writeLine("public static final String JSP_DIR = \"WEB-INF/" + jsp + "\";");
      getWriter().writeLine("public static final String LAYOUT = \"/layout.jsp\";");
      getWriter().writeLine("");
    }
  }


  @Override
  protected void writeUpdateAction(String args)
  {
    getWriter().writeLine("try");
    getWriter().openBracket();
    getWriter().writeLine("dto.apply();");
    getWriter().writeLine("this.view(dto.getId());");
    getWriter().closeBracket();
    getWriter().writeLine("catch(" + Throwable.class.getName() + " t)");
    getWriter().openBracket();
    getWriter().writeLine("boolean redirect = " + ErrorUtility.class.getName() + ".prepareThrowable(t, req, resp, this.isAsynchronous());");
    getWriter().writeLine("if (!redirect)");
    getWriter().openBracket();
    getWriter().writeLine("this.failUpdate(" + args + ");");
    getWriter().closeBracket();
    getWriter().closeBracket();
  }

  @Override
  protected void writeNewInstanceAction(MdEntityDAOIF mdEntity)
  {
    getWriter().writeLine("try");
    getWriter().openBracket();

    super.writeNewInstanceAction(mdEntity);

    getWriter().closeBracket();
    getWriter().writeLine("catch(" + Throwable.class.getName() + " t)");
    getWriter().openBracket();
    getWriter().writeLine("boolean redirect = " + ErrorUtility.class.getName() + ".prepareThrowable(t, req, resp, this.isAsynchronous());");
    getWriter().writeLine("if (!redirect)");
    getWriter().openBracket();
    getWriter().writeLine("this.failNewInstance();");
    getWriter().closeBracket();
    getWriter().closeBracket();
  }

  @Override
  protected void writeEditAction(MdEntityDAOIF mdEntity)
  {
    getWriter().writeLine("try");
    getWriter().openBracket();

    super.writeEditAction(mdEntity);

    getWriter().closeBracket();
    getWriter().writeLine("catch(" + Throwable.class.getName() + " t)");
    getWriter().openBracket();
    getWriter().writeLine("boolean redirect = " + ErrorUtility.class.getName() + ".prepareThrowable(t, req, resp, this.isAsynchronous());");
    getWriter().writeLine("if (!redirect)");
    getWriter().openBracket();
    getWriter().writeLine("this.failEdit(id);");
    getWriter().closeBracket();
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
    getWriter().writeLine("catch(" + Throwable.class.getName() + " t)");
    getWriter().openBracket();
    getWriter().writeLine("boolean redirect = " + ErrorUtility.class.getName() + ".prepareThrowable(t, req, resp, this.isAsynchronous());");
    getWriter().writeLine("if (!redirect)");
    getWriter().openBracket();
    getWriter().writeLine("this.failDelete(" + args + ");");
    getWriter().closeBracket();    
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
    getWriter().writeLine("catch(" + Throwable.class.getName() + " t)");
    getWriter().openBracket();
    getWriter().writeLine("boolean redirect = " + ErrorUtility.class.getName() + ".prepareThrowable(t, req, resp, this.isAsynchronous());");
    getWriter().writeLine("if (!redirect)");
    getWriter().openBracket();
    getWriter().writeLine("this.failCreate(" + args + ");");
    getWriter().closeBracket();    
    getWriter().closeBracket();    
  }

  @Override
  protected void writeViewAction(MdEntityDAOIF mdEntity)
  {
    getWriter().writeLine("try");
    getWriter().openBracket();
    
    getWriter().writeLine(RedirectUtilityInfo.CLASS + " utility = new " + RedirectUtilityInfo.CLASS + "(req, resp);");
    getWriter().writeLine("utility.put(\"id\", id);");
    getWriter().writeLine("utility.checkURL(this.getClass().getSimpleName(), \"view\");");

    String clientRequest = ClientRequestIF.class.getName();

    getWriter().writeLine(clientRequest + " clientRequest = super.getClientRequest();");

    String typeName = mdEntity.definesType() + "DTO";

    getWriter().writeLine(typeName + " dto = " + typeName + ".get(clientRequest, id);");

    // Load options for Enumeration and Reference attributes
    this.generateRequestsForReferencesAndEnumerations(mdEntity);

    getWriter().writeLine("req.setAttribute(\"item\", dto);");

    writeRender("View " + mdEntity.getTypeName(), "viewComponent.jsp");
    getWriter().closeBracket();
    getWriter().writeLine("catch(" + Throwable.class.getName() + " t)");
    getWriter().openBracket();
    getWriter().writeLine("boolean redirect = " + ErrorUtility.class.getName() + ".prepareThrowable(t, req, resp, this.isAsynchronous());");
    getWriter().writeLine("if (!redirect)");
    getWriter().openBracket();
    getWriter().writeLine("this.failView(id);");
    getWriter().closeBracket();    
    getWriter().closeBracket();    

  }

  @Override
  protected void generateReferenceList(MdAttributeDAOIF mdAttribute)
  {
    MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttribute;
    MdBusinessDAOIF mdBusiness = mdAttributeReference.getReferenceMdBusinessDAO();

    boolean published = mdBusiness.isPublished();
    boolean isGeoEntity = MDSSGenerationUtility.isAGeoEntity(mdBusiness);
    boolean isTerm = MDSSGenerationUtility.isATerm(mdBusiness);

    String definesAttribute = mdAttribute.definesAttribute();

    if (isTerm)
    {
      String accessor = mdAttribute.getValue(MdAttributeReferenceInfo.GENERATE_ACCESSOR);
      String definingType = mdAttribute.definedByClass().definesType() + TypeGeneratorInfo.DTO_SUFFIX;

      if (accessor == null || accessor.equals(""))
      {
        accessor = definesAttribute;
      }

      getWriter().writeLine("req.setAttribute(\"" + definesAttribute + "\", " + AttributeUtil.class.getName() +  ".getValue(" + definingType + "." + definesAttribute.toUpperCase() +", dto));");
    }
    else if (published && !isGeoEntity)
    {
      String request = "super.getClientSession().getRequest()";
      String dtoType = mdBusiness.definesType() + TypeGeneratorInfo.DTO_SUFFIX;
      String methodName = "getAllInstances";
      String command = dtoType + "." + methodName + "(" + request + ", \"keyName\", true, 0, 0).getResultSet()";

      try
      {
        MdMethodDAOIF mdMethod = mdBusiness.getMdMethod("getAllActive");

        command = Arrays.class.getName() + ".asList(" + dtoType + "." + mdMethod.getName() + "(" + request + "))";
      }
      catch (DataNotFoundException e)
      {
        try
        {
          MdMethodDAOIF mdMethod = mdBusiness.getMdMethod("getAll");

          command = Arrays.class.getName() + ".asList(" + dtoType + "." + mdMethod.getName() + "(" + request + "))";
        }
        catch (DataNotFoundException e1)
        {
          // Do nothing
        }
      }
      // If the referenced MdBusinessDAO is not published it is impossible to
      // populate
      getWriter().writeLine("req.setAttribute(\"" + definesAttribute + "\", " + command + ");");
    }
  }
}
