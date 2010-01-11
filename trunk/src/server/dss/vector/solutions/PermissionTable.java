package dss.vector.solutions;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.terraframe.mojo.business.BusinessFacade;
import com.terraframe.mojo.business.rbac.Operation;
import com.terraframe.mojo.business.rbac.RoleDAO;
import com.terraframe.mojo.dataaccess.metadata.MdTypeDAO;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.Roles;
import com.terraframe.mojo.system.RolesQuery;
import com.terraframe.mojo.system.metadata.MdType;
import com.terraframe.mojo.system.metadata.MdTypeQuery;
import com.terraframe.mojo.util.FileIO;

public class PermissionTable
{
  private String html;
  private List<Operation> allOperations;
  
  public static void main(String[] args) throws Exception
  {
    FileIO.write("permissions.html", new PermissionTable().getHTML());
  }
  
  public PermissionTable()
  {
    html = new String();
    allOperations = new LinkedList<Operation>();
    allOperations.add(Operation.READ);
    allOperations.add(Operation.WRITE);
    allOperations.add(Operation.CREATE);
    allOperations.add(Operation.DELETE);
    allOperations.add(Operation.READ_PARENT);
    allOperations.add(Operation.READ_CHILD);
    allOperations.add(Operation.WRITE_PARENT);
    allOperations.add(Operation.WRITE_CHILD);
    allOperations.add(Operation.ADD_PARENT);
    allOperations.add(Operation.ADD_CHILD);
    allOperations.add(Operation.DELETE_PARENT);
    allOperations.add(Operation.DELETE_CHILD);
  }
  
  public String getHTML()
  {
    RolesQuery rolesQuery = new RolesQuery(new QueryFactory());
    rolesQuery.WHERE(rolesQuery.getRoleName().LIKEi("mdss.*"));
    // Remove the MDSS role, since it can always do everything
    rolesQuery.WHERE(rolesQuery.getRoleName().NE("mdss.MDSS"));
    List<? extends Roles> allRoles = rolesQuery.getIterator().getAll();
    
    MdTypeQuery typeQuery = new MdTypeQuery(new QueryFactory());
    typeQuery.WHERE(typeQuery.getPackageName().LIKEi("dss.vector.solutions*"));
    typeQuery.ORDER_BY_ASC(typeQuery.getPackageName());
    typeQuery.ORDER_BY_ASC(typeQuery.getTypeName());
    List<? extends MdType> allTypes = typeQuery.getIterator().getAll();
    
    openRow();
    writeHeader("");
    for (Roles role : allRoles)
    {
      String roleName = role.getRoleName();
      writeHeaderWithSpan(roleName.substring(roleName.indexOf(".")+1));
    }
    closeRow();
    
    openRow();
    writeHeader("");
    for (Roles role : allRoles)
    {
      writeHeader("Read");
      writeHeader("Write");
      writeHeader("Create");
      writeHeader("Delete");
      writeHeader("Read Parent");
      writeHeader("Read Child");
      writeHeader("Write Parent");
      writeHeader("Write Child");
      writeHeader("Add Parent");
      writeHeader("Add Child");
      writeHeader("Delete Parent");
      writeHeader("Delete Child");
    }
    closeRow();
    
    for (MdType type : allTypes)
    {
      openRow();
      writeCellWithTitle(type.getDisplayLabel().getDefaultLocale(), type.definesType());
      MdTypeDAO typeDAO = (MdTypeDAO)BusinessFacade.getEntityDAO(type);
      for (Roles role : allRoles)
      {
        RoleDAO roleDAO = (RoleDAO)BusinessFacade.getEntityDAO(role);
        Set<Operation> permissions = roleDAO.getPermissions(typeDAO);
        for (Operation op : allOperations)
        {
          if (permissions.contains(op))
          {
            writeCell("X");
          }
          else
          {
            writeCell("");
          }
        }
      }
      closeRow();
    }
    
    return "<table border=1>" + html + "</table>";
  }
  
  private void openRow()
  {
    html += "<tr>";
  }
  
  private void closeRow()
  {
    html += "</tr>";
  }
  
  private void writeCellWithTitle(String data, String title)
  {
    html += "<td title=\"" + title + "\">" + data + "</td>";
  }
  
  private void writeCell(String data)
  {
    html += "<td>" + data + "</td>";
  }
  
  private void writeHeaderWithSpan(String data)
  {
    html += "<th colspan=12>" + data + "</th>";
  }
  
  private void writeHeader(String data)
  {
    html += "<th>" + data + "</th>";
  }
  
  private String getCode(Operation op)
  {
    if (op==Operation.READ) return "R";
    if (op==Operation.WRITE) return "W";
    if (op==Operation.PROMOTE) return "P";
    if (op==Operation.CREATE) return "C";
    if (op==Operation.DELETE) return "D";
    if (op==Operation.ADD_PARENT) return "Ap";
    if (op==Operation.ADD_CHILD) return "Ac";
    if (op==Operation.DELETE_PARENT) return "Dp";
    if (op==Operation.DELETE_CHILD) return "Dc";
    if (op==Operation.WRITE_PARENT) return "Wp";
    if (op==Operation.WRITE_CHILD) return "Wc";
    if (op==Operation.READ_PARENT) return "Rp";
    if (op==Operation.READ_CHILD) return "Rc";
    if (op==Operation.GRANT) return "G";
    if (op==Operation.REVOKE) return "V";
    if (op==Operation.EXECUTE) return "E";
    return "";
  }
}
