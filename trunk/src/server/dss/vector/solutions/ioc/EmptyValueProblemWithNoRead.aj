package dss.vector.solutions.ioc;

import java.util.List;

import com.runwaysdk.ProblemException;
import com.runwaysdk.ProblemIF;
import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.business.rbac.UserDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.attributes.EmptyValueProblem;
import com.runwaysdk.dataaccess.transaction.TransactionManagement;
import com.runwaysdk.session.AttributeReadPermissionException;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionIF;
import com.runwaysdk.session.SessionFacade;
import com.runwaysdk.session.RequestManagement;

privileged public aspect EmptyValueProblemWithNoRead
{
  declare                                       precedence : EmptyValueProblemWithNoRead, TransactionManagement;

  public pointcut emptyValueProblem() : TransactionManagement.topLevelTransactions();

  Object around() : emptyValueProblem()
  {

    Object object = null;

    try
    {
//      System.out.println("around "+thisJoinPointStaticPart.toLongString());

      object = proceed();

      List<ProblemIF> problemList = RequestManagement.aspectOf().getProblemList();
//      System.out.println("Problem size: "+problemList.size());
      this.processProblemList(problemList);

    }
    catch(ProblemException problemException)
    {
      List<ProblemIF> problemList = problemException.getProblems();

      this.processProblemList(problemList);

      throw problemException;
    }

    return object;
  }


  private void processProblemList(List<ProblemIF> problemList)
  {
	// If we are not in a logged-in session, then no need to perform check
	SessionIF sessionIF = Session.getCurrentSession();
	if (sessionIF == null)
	{
	  return;
	}

    for (ProblemIF problemIF : problemList)
    {
      if (problemIF instanceof EmptyValueProblem)
      {
        EmptyValueProblem emptyValueProblem = (EmptyValueProblem)problemIF;

        MdAttributeDAOIF mdAttributeDAOIF = emptyValueProblem.getMdAttributeIF();

        boolean hasReadAccess = SessionFacade.checkAttributeAccess(sessionIF.getId(), Operation.READ, mdAttributeDAOIF);

        if (!hasReadAccess)
        {
          UserDAOIF userDAOIF = sessionIF.getUser();
          String devMsg = "MDSS Specific Exception: Unable to write to a manditory field because the user does not have read permissions.";
          throw new AttributeReadPermissionException(devMsg, mdAttributeDAOIF, userDAOIF);
        }
      }
    }
  }

}
