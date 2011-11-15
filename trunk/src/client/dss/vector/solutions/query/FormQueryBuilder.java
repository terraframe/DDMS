package dss.vector.solutions.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcreteDTO;
import com.runwaysdk.system.metadata.MdAttributeDTO;
import com.runwaysdk.system.metadata.MdClassDTO;
import com.runwaysdk.system.metadata.MdRelationshipDTO;
import com.runwaysdk.system.metadata.MdWebAttributeDTO;
import com.runwaysdk.system.metadata.MdWebDateDTO;
import com.runwaysdk.system.metadata.MdWebFieldDTO;
import com.runwaysdk.system.metadata.MdWebFormDTO;
import com.runwaysdk.system.metadata.MdWebGeoDTO;
import com.runwaysdk.system.metadata.MdWebMultipleTermDTO;
import com.runwaysdk.system.metadata.MdWebPrimitiveDTO;
import com.runwaysdk.system.metadata.MdWebSingleTermGridDTO;
import com.runwaysdk.system.metadata.MdWebTextDTO;

import dss.vector.solutions.general.EpiDateDTO;
import dss.vector.solutions.generator.MdFormUtilDTO;
import dss.vector.solutions.geo.GeoFieldDTO;
import dss.vector.solutions.ontology.NestedTermsWarningDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.util.Halp;
import dss.vector.solutions.util.QueryUtil;

public class FormQueryBuilder implements Reloadable
{
  private ClientRequestIF       request;

  private List<SelectableGroup> groups;

  private SerializableGroup     dateGroup;

  private SerializableGroup     geoGroup;

  private CompositeGeoField     geoField;

  private List<String>          typesToLoad;

  private String                mainType;

  private JSONObject            queryList;

  private String                queryClass;

  public FormQueryBuilder(ClientRequestIF request, String queryClass)
  {
    this.request = request;
    this.queryClass = queryClass;
    this.groups = new LinkedList<SelectableGroup>();
    this.dateGroup = new SerializableGroup();
    this.geoGroup = new SerializableGroup();
    this.geoField = new CompositeGeoField();

    this.typesToLoad = new LinkedList<String>();
    this.typesToLoad.addAll(Arrays.asList(new String[] { NestedTermsWarningDTO.CLASS, EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS }));
  }

  public void setQuerySpecifics(String type, QueryConstants.QueryType queryType) throws JSONException
  {
    this.loadQuerySpecifics(QueryConstants.namespaceQuery(type, queryType));
  }

  public void setQuerySpecifics(String type, String typeName) throws JSONException
  {
    this.loadQuerySpecifics(QueryConstants.namespaceQuery(type, typeName));
  }

  private void loadQuerySpecifics(String namespacedType) throws JSONException
  {
    SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(request, namespacedType);
    JSONArray queries = new JSONArray();
    for (SavedSearchViewDTO view : query.getResultSet())
    {
      JSONObject idAndName = new JSONObject();
      idAndName.put("id", view.getSavedQueryId());
      idAndName.put("name", view.getQueryName());

      queries.put(idAndName);
    }

    JSONObject queryList = new JSONObject();
    queryList.put("queries", queries);
    queryList.put("namespacedType", namespacedType);

    this.queryList = queryList;
  }

  public void addForm(MdWebFormDTO form, String groupName)
  {
    MdClassDTO formMdClass = form.getFormMdClass();
    String classType = formMdClass.getPackageName() + "." + formMdClass.getTypeName();

    if (this.mainType == null)
    {
      this.mainType = classType;
    }

    this.typesToLoad.add(classType);

    MdWebFieldDTO[] fields = MdFormUtilDTO.getFields(request, form);

    Set<String> readableAttributeNames = Halp.getReadableAttributeNames(classType, request);
    Map<MdWebFieldDTO, MdAttributeConcreteDTO> readableFieldMap = this.getReadableFieldMap(fields, readableAttributeNames);
    List<MdWebFieldDTO> readableFields = new LinkedList<MdWebFieldDTO>(readableFieldMap.keySet());

    Collections.sort(readableFields, new FieldComparator());

    SelectableGroup group = new SelectableGroup();
    group.setClassType(classType);
    group.setLabel(form.getDisplayLabel().getValue());
    group.setGroup(groupName);

    this.groups.add(group);

    for (MdWebFieldDTO field : readableFields)
    {
      String fieldName = field.getFieldName();
      String fieldLabel = field.getDisplayLabel().getValue();

      MdAttributeConcreteDTO mdAttribute = readableFieldMap.get(field);
      String attributeName = mdAttribute.getAttributeName();

      if (field instanceof MdWebMultipleTermDTO)
      {
        TermDTO[] terms = TermDTO.getAllTermsForField(request, classType, attributeName);
        MdRelationshipDTO mdRelationship = MdFormUtilDTO.getMdRelationship(request, field);

        String relType = mdRelationship.getPackageName() + "." + mdRelationship.getTypeName();
        this.typesToLoad.add(relType);

        TermOptionFactory factory = new TermOptionFactory(QueryUtil.DUMMY_RELATIONSHIP_VALUE_ONE, relType);
        factory.setAttributeNamePrepend(attributeName);
        factory.setLabel(fieldLabel + " - ");

        SelectableGroup selectableGroup = new SelectableGroup();
        selectableGroup.setLabel(fieldLabel);
        selectableGroup.setClassType(relType);

        for (TermDTO term : terms)
        {
          selectableGroup.addOption(factory.createOption(term));
        }

        this.groups.add(selectableGroup);
      }
      else if (field instanceof MdWebSingleTermGridDTO)
      {
        TermDTO[] terms = TermDTO.getAllTermsForField(request, classType, attributeName);

        MdRelationshipDTO mdRelationship = MdFormUtilDTO.getMdRelationship(request, field);

        String relType = mdRelationship.getPackageName() + "." + mdRelationship.getTypeName();
        this.typesToLoad.add(relType);

        MdWebPrimitiveDTO[] compositeFields = MdFormUtilDTO.getCompositeFields(request, field.getId());

        for (MdWebPrimitiveDTO compositeField : compositeFields)
        {
          String compositeAttributeName = compositeField.getFieldName();
          String attributeLabel = compositeField.getDisplayLabel().getValue();

          TermOptionFactory factory = new TermOptionFactory(compositeField, compositeAttributeName, relType);
          factory.setAttributeNamePrepend(fieldName + "_" + compositeAttributeName);
          factory.setLabel(attributeLabel + " - ");

          SelectableGroup selectableGroup = new SelectableGroup();
          selectableGroup.setLabel(fieldLabel + " - " + attributeLabel);
          selectableGroup.setClassType(relType);

          for (TermDTO term : terms)
          {
            selectableGroup.addOption(factory.createOption(term));
          }

          this.groups.add(selectableGroup);
        }
      }
      else if (! ( field instanceof MdWebTextDTO ))
      {
        if (field instanceof MdWebGeoDTO)
        {
          this.geoField.addField(GeoFieldDTO.getGeoField(request, classType, attributeName));

          this.geoGroup.addOption(new GeoOption(classType, attributeName, fieldLabel));
        }
        else if (field instanceof MdWebDateDTO)
        {
          this.dateGroup.addOption(new DateOption(classType, attributeName));
        }

        new SelectableOptionFactory(group, form.getFormName()).create(mdAttribute);
      }
    }
  }

  private Map<MdWebFieldDTO, MdAttributeConcreteDTO> getReadableFieldMap(MdWebFieldDTO[] fields, Set<String> readableAttributeNames)
  {
    Map<MdWebFieldDTO, MdAttributeConcreteDTO> map = new HashMap<MdWebFieldDTO, MdAttributeConcreteDTO>();

    for (MdWebFieldDTO field : fields)
    {
      if (field instanceof MdWebAttributeDTO)
      {
        MdAttributeDTO mdAttribute = ( (MdWebAttributeDTO) field ).getDefiningMdAttribute();

        if (mdAttribute instanceof MdAttributeConcreteDTO)
        {
          MdAttributeConcreteDTO mdAttributeConcrete = (MdAttributeConcreteDTO) mdAttribute;

          if (readableAttributeNames.contains(mdAttributeConcrete.getAttributeName()))
          {
            map.put(field, mdAttributeConcrete);
          }
        }
      }
    }

    return map;
  }

  public void populateRequest(HttpServletRequest req)
  {
    req.setAttribute("typesToLoad", typesToLoad);
    req.setAttribute("dateAttributes", this.dateGroup.serialize());
    req.setAttribute("geoAttributes", this.geoGroup.serialize());
    req.setAttribute("geoField", geoField);
    req.setAttribute("groups", groups);
    req.setAttribute("type", mainType);
    req.setAttribute("queryList", queryList.toString());
    req.setAttribute("queryClass", queryClass);
  }
}
