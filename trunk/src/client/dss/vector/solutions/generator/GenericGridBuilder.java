package dss.vector.solutions.generator;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.runwaysdk.ClientException;
import com.runwaysdk.business.MutableDTO;
import com.runwaysdk.business.RelationshipDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.constants.TypeGeneratorInfo;
import com.runwaysdk.form.web.WebFormObject;
import com.runwaysdk.form.web.field.WebSingleTermGrid;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdRelationshipDTO;
import com.runwaysdk.system.metadata.MdWebPrimitiveDTO;
import com.runwaysdk.system.metadata.MdWebSingleTermGridDTO;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class GenericGridBuilder extends GridBuilder implements Reloadable
{
  private WebSingleTermGrid      grid;

  private ClientRequestIF        request;

  private boolean                readOnly;

  private WebFormObject          formObject;

  private MdWebSingleTermGridDTO mdField;

  private MdRelationshipDTO      mdRel;

  public GenericGridBuilder(WebFormObject formObject, WebSingleTermGrid grid, ClientRequestIF request, boolean readOnly)
  {
    this.formObject = formObject;
    this.grid = grid;
    this.request = request;
    this.readOnly = readOnly;

    this.mdField = MdWebSingleTermGridDTO.get(this.request, grid.getFieldMd().getId());

    this.mdRel = MdFormUtilDTO.getMdRelationship(this.request, mdField);
  }

  @Override
  public DataGrid build()
  {

    // create a new instance of the relationship for the grid metadata (the
    // rows)
    RelationshipDTO relForMetadata = this.newRelationship("", "");

    // create the columns based on the MdFields in the grid composite
    String[] keys = this.getKeys();
    Map<String, ColumnSetup> columns = getColumns(keys, 1, false);

    ColumnSetup parentId = new ColumnSetup("parentId");
    columns.put("parentId", parentId);

    ColumnSetup childId = new ColumnSetup("childId");
    columns.put("childId", childId);

    ColumnSetup relId = new ColumnSetup("relId");
    columns.put("relId", relId);

    ColumnSetup termDisplayLabel = new ColumnSetup("termDisplayLabel");
    termDisplayLabel.setHidden(false);
    termDisplayLabel.setLabel("");
    columns.put("termDisplayLabel", termDisplayLabel);

    if (this.readOnly)
    {
      for (String key : columns.keySet())
      {
        ColumnSetup column = columns.get(key);

        column.setEditable(false);
      }
    }

    // get all rows for the data object
    MutableDTO[] data = this.getData();

    String tableId = "g_" + grid.getFieldMd().getId();
    return new ViewDataGrid(tableId, formObject.isReadable(), relForMetadata, columns, keys, data);

  }

  @SuppressWarnings("unchecked")
  private RelationshipDTO newRelationship(String parentId, String childId)
  {
    try
    {
      String relType = this.mdRel.getPackageName() + "." + this.mdRel.getTypeName() + TypeGeneratorInfo.DTO_SUFFIX;
      Class<? extends RelationshipDTO> clazz = (Class<? extends RelationshipDTO>) LoaderDecorator.load(relType);
      return clazz.getConstructor(ClientRequestIF.class, String.class, String.class).newInstance(this.request, parentId, childId);
    }
    catch (Throwable t)
    {
      throw new ClientException(t);
    }
  }

  @SuppressWarnings("unchecked")
  private MutableDTO[] getData()
  {
    try
    {
      String relGetter = "getAll" + mdRel.getChildMethod() + "Relationships";

      String className = this.grid.getFieldMd().getDefiningClass() + TypeGeneratorInfo.DTO_SUFFIX;
      Class<?> clazz = LoaderDecorator.load(className);

      MutableDTO dto;
      List<? extends RelationshipDTO> rels;
      if (this.formObject.isNewInstance())
      {
        dto = (MutableDTO) clazz.getConstructor(ClientRequestIF.class).newInstance(this.request);
        rels = new LinkedList<RelationshipDTO>();
      }
      else
      {
        dto = (MutableDTO) clazz.getMethod("get", ClientRequestIF.class, String.class).invoke(null, this.request, this.formObject.getDataId());
        rels = (List<? extends RelationshipDTO>) clazz.getMethod(relGetter).invoke(dto);
      }

      // mapping between the term id and relationship object
      Map<String, RelationshipDTO> existing = new HashMap<String, RelationshipDTO>();
      for (RelationshipDTO rel : rels)
      {
        existing.put(rel.getChildId(), rel);
      }

      // If a relationship does not exist for one of the terms then manually
      // create a new relationship dto.
      String busClass = this.grid.getFieldMd().getDefiningClass();
      String attribute = this.grid.getFieldMd().getDefiningAttribute();

      Set<RelationshipDTO> data = new LinkedHashSet<RelationshipDTO>();
      List<TermDTO> terms = Arrays.asList(TermDTO.getAllTermsForField(this.request, busClass, attribute));
      Collections.sort(terms, new TermComparator());

      for (TermDTO t : terms)
      {
        if (existing.containsKey(t.getId()))
        {
          data.add(existing.get(t.getId()));
        }
        else
        {
          // create a filler object
          RelationshipDTO filler = this.newRelationship(this.formObject.getDataId(), t.getId());
          data.add(filler);
        }
      }

      return data.toArray(new MutableDTO[data.size()]);
    }
    catch (Throwable t)
    {
      throw new ClientException(t);
    }
  }

  private String[] getKeys()
  {
    // FIXME add hidden/editable/read
    MdWebPrimitiveDTO[] columnFields = MdFormUtilDTO.getCompositeFields(this.request, this.mdField.getId());
    String[] keys = new String[columnFields.length + 4];

    keys[0] = "parentId"; // Business Id
    keys[1] = "childId"; // Term Id
    keys[2] = "relId"; // Relationship Id
    keys[3] = "termDisplayLabel";

    for (int i = 0; i < columnFields.length; i++)
    {
      keys[i + 4] = columnFields[i].getFieldName();
    }

    return keys;
  }

}
