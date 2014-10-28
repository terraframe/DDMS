package dss.vector.solutions;

import java.io.InputStream;

import com.runwaysdk.business.BusinessDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.RequestFacadeIF;

public class ClientRequestFacade implements RequestFacadeIF, Reloadable
{
  private ClientRequestIF request;

  public ClientRequestFacade(ClientRequestIF request)
  {
    this.request = request;
  }

  @Override
  public String newFile(String path, String filename, String extension, InputStream stream)
  {
    BusinessDTO file = this.request.newFile(path, filename, extension, stream);

    return file.getId();
  }

  @Override
  public void delete(String id)
  {
    this.request.delete(id);
  }

}
