package dss.vector.solutions.odk;

import java.util.Set;
import java.util.TreeSet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.ontology.AllPathsQuery;
import dss.vector.solutions.ontology.BrowserFieldQuery;
import dss.vector.solutions.ontology.BrowserRootQuery;
import dss.vector.solutions.ontology.TermQuery;

public class ODKTermAttribute extends ODKAttribute
{
  public static final int  LIMIT = 100;

  private MdAttributeDAOIF mdAttribute;

  private Set<String>      exported;

  public ODKTermAttribute(MdAttributeDAOIF mdAttribute, Set<String> exported)
  {
    this(mdAttribute, exported, 0);
  }

  public ODKTermAttribute(MdAttributeDAOIF mdAttribute, Set<String> exported, int index)
  {
    super(mdAttribute.definesAttribute(), mdAttribute.getDisplayLabel(Session.getCurrentLocale()), mdAttribute.getDescription(Session.getCurrentLocale()), index, mdAttribute.isRequired());

    this.mdAttribute = mdAttribute;
    this.exported = exported;
  }

  @Override
  public void writeTranslation(Element parent, Document document, String title, int maxDepth)
  {
    super.writeTranslation(parent, document, title, maxDepth);

    ValueQuery query = termQuery(this.mdAttribute);

    long count = query.getCount();

    if (count == 0)
    {
      TermExportProblem problem = new TermExportProblem();
      problem.setAttributeLabel(this.mdAttribute.getDisplayLabel(Session.getCurrentLocale()));
      problem.apply();

      problem.throwIt();
    }
    else if (count > LIMIT)
    {
      TermExportLimitProblem problem = new TermExportLimitProblem();
      problem.setAttributeLabel(this.mdAttribute.getDisplayLabel(Session.getCurrentLocale()));
      problem.setLimit(LIMIT);
      problem.apply();

      problem.throwIt();
    }
    else
    {
      OIterator<ValueObject> it = query.getIterator();

      try
      {
        while (it.hasNext())
        {
          ValueObject vObject = it.next();
          String termId = vObject.getValue("termId");
          String label = vObject.getValue("displayLabel");
          String selectable = vObject.getValue("selectable");
          String rootId = vObject.getValue("rootId");
          String id = vObject.getValue("id");

          if (!this.exported.contains(id) && ( !rootId.equals(id) || new Boolean(selectable) ))
          {
            Element text = document.createElement("text");

            text.setAttribute("id", "'" + termId + "'");

            Element value = document.createElement("value");
            value.setTextContent(label);
            text.appendChild(value);

            parent.appendChild(text);

            this.exported.add(id);
          }
        }

      }
      finally
      {
        it.close();
      }

    }
  }

  @Override
  public void writeBody(Element parent, Document document, String title, int maxDepth)
  {
    Set<String> items = new TreeSet<String>();

    ValueQuery query = termQuery(this.mdAttribute);
    long count = query.getCount();

    Element select1 = document.createElement("select1");
    parent.appendChild(select1);
    select1.setAttribute("appearance", "search");
    select1.setAttribute("ref", "/" + title + "/" + attributeName);

    Element label = document.createElement("label");
    select1.appendChild(label);
    label.setAttribute("ref", "jr:itext('/" + title + "/" + attributeName + ":label')");

    if (count < LIMIT)
    {
      OIterator<ValueObject> it = query.getIterator();

      try
      {
        while (it.hasNext())
        {
          ValueObject vObject = it.next();
          String termId = vObject.getValue("termId");
          String selectable = vObject.getValue("selectable");
          String rootId = vObject.getValue("rootId");
          String id = vObject.getValue("id");

          if (!items.contains(id) && ( !rootId.equals(id) || new Boolean(selectable) ))
          {
            Element item = document.createElement("item");
            select1.appendChild(item);

            Element itemLabel = document.createElement("label");
            itemLabel.setAttribute("ref", "jr:itext(''" + termId + "'')");
            item.appendChild(itemLabel);

            Element itemValue = document.createElement("value");
            itemValue.appendChild(document.createTextNode(termId));
            item.appendChild(itemValue);

            items.add(id);
          }
        }

      }
      finally
      {
        it.close();
      }
    }
  }

  @Override
  public String getODKType()
  {
    return "select1";
  }

  private static ValueQuery termQuery(MdAttributeDAOIF mdAttribute)
  {
    QueryFactory factory = new QueryFactory();

    ValueQuery vQuery = new ValueQuery(factory);

    BrowserRootQuery rootQuery = new BrowserRootQuery(vQuery);
    BrowserFieldQuery fieldQuery = new BrowserFieldQuery(vQuery);
    AllPathsQuery aptQuery = new AllPathsQuery(vQuery);
    TermQuery tQuery = new TermQuery(vQuery);

    vQuery.SELECT(tQuery.getId("id"), rootQuery.getTerm("rootId"), rootQuery.getSelectable(), tQuery.getTermId("termId"), tQuery.getTermDisplayLabel().localize("displayLabel"));
    vQuery.WHERE(fieldQuery.getMdAttribute().getId().EQ(mdAttribute.getId()));
    vQuery.AND(Disease.getInactiveCriteria(vQuery, rootQuery.getTerm(), false));
    vQuery.AND(rootQuery.getDisease().EQ(Disease.getCurrent()));
    vQuery.AND(rootQuery.field(fieldQuery));
    vQuery.AND(aptQuery.getParentTerm().EQ(rootQuery.getTerm()));
    vQuery.AND(tQuery.EQ(aptQuery.getChildTerm()));
    vQuery.ORDER_BY_ASC(tQuery.getTermId());

    return vQuery;
  }
}
