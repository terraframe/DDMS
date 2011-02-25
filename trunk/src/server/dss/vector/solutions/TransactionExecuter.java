package dss.vector.solutions;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Request;

public abstract class TransactionExecuter
{
  public void execute() throws Exception
  {
    executeRequest();
  }

  @Request
  private void executeRequest() throws Exception
  {
    executeTransaction();
  }

  @Transaction
  private void executeTransaction() throws Exception
  {
    executeMethod();
  }

  protected abstract void executeMethod() throws Exception;
}
