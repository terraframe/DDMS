/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.permission;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.Roles;
import com.runwaysdk.system.RolesQuery;
import com.runwaysdk.system.metadata.MdType;
import com.runwaysdk.system.metadata.MdTypeQuery;
import com.runwaysdk.util.FileIO;

public class PermissionTable implements Reloadable
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
      writeCellWithTitle(type.getDisplayLabel().getDefaultValue(), type.definesType());
      MdTypeDAO typeDAO = (MdTypeDAO)BusinessFacade.getEntityDAO(type);
      for (Roles role : allRoles)
      {
        RoleDAO roleDAO = (RoleDAO)BusinessFacade.getEntityDAO(role);
        Set<Operation> permissions = roleDAO.getAllPermissions(typeDAO);
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
