package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.runwaysdk.business.Transient;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdWebAttribute;

import dss.vector.solutions.kaleidoscope.ontology.NonUniqueCategoryException;
import dss.vector.solutions.ontology.AllPathsQuery;
import dss.vector.solutions.ontology.BrowserFieldQuery;
import dss.vector.solutions.ontology.BrowserRoot;
import dss.vector.solutions.ontology.BrowserRootQuery;
import dss.vector.solutions.ontology.BrowserRootView;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.ontology.TermSynonymQuery;

public class TargetFieldDomain extends TargetFieldBasic implements TargetFieldIF, TargetFieldValidationIF, Reloadable
{
  private MdAttributeReferenceDAOIF mdAttribute;

  @Override
  public FieldValue getValue(MdAttributeConcreteDAOIF mdAttribute, Transient source)
  {
    String value = source.getValue(this.getSourceAttributeName());

    if (value != null && value.length() > 0)
    {
      MdAttributeReferenceDAOIF mdAttributeTerm = (MdAttributeReferenceDAOIF) mdAttribute;

      Term classifier = this.findTerm(mdAttributeTerm, value);

      if (classifier == null)
      {
        throw new ExclusionException("Unable to find matching term with label [" + value + "]");
      }

      return new FieldValue(classifier.getId());
    }

    return new FieldValue();
  }

  @Override
  public void persist(TargetBinding binding)
  {
    MdAttribute sourceAttribute = MdAttribute.getByKey(binding.getSourceView().definesType() + "." + this.getSourceAttributeName());
    MdWebAttribute targetAttribute = MdWebAttribute.getByKey(this.getKey());

    TargetFieldDomainBinding field = new TargetFieldDomainBinding();
    field.setTarget(binding);
    field.setTargetAttribute(targetAttribute);
    field.setSourceAttribute(sourceAttribute);
    field.setColumnLabel(this.getLabel());
    field.apply();
  }

  @SuppressWarnings("unchecked")
  @Override
  public ImportProblemIF validate(Transient source, Map<String, Object> parameters)
  {
    String value = source.getValue(this.getSourceAttributeName());

    if (value != null && value.length() > 0)
    {
      Map<String, Set<String>> exclusions = (Map<String, Set<String>>) parameters.get("categoryExclusions");

      MdAttributeReferenceDAOIF mdAttributeTerm = this.getMdAttribute();

      if (!this.isExcluded(exclusions, mdAttributeTerm, value))
      {
        Term classifier = this.findTerm(mdAttributeTerm, value.trim());

        if (classifier == null)
        {
          List<BrowserRootView> roots = BrowserRoot.getDirectAttributeRoots(mdAttributeTerm.definedByClass().definesType(), mdAttributeTerm.definesAttribute());
          String attributeLabel = mdAttributeTerm.getDisplayLabel(Session.getCurrentLocale());

          return new CategoryProblem(value.trim(), roots.get(0).getTermId(), mdAttributeTerm.getId(), attributeLabel);
        }
      }
    }

    return null;
  }

  private MdAttributeReferenceDAOIF getMdAttribute()
  {
    if (this.mdAttribute == null)
    {
      MdWebAttribute mdField = MdWebAttribute.getByKey(this.getKey());

      this.mdAttribute = (MdAttributeReferenceDAOIF) MdAttributeDAO.get(mdField.getDefiningMdAttributeId());
    }

    return this.mdAttribute;
  }

  private boolean isExcluded(Map<String, Set<String>> exclusions, MdAttributeReferenceDAOIF mdAttributeTerm, String label)
  {
    if (exclusions.containsKey(mdAttributeTerm.getId()))
    {
      Set<String> labels = exclusions.get(mdAttributeTerm.getId());

      return labels.contains(label);
    }

    return false;
  }

  private Term findTerm(MdAttributeReferenceDAOIF mdAttributeTerm, String label)
  {
    QueryFactory factory = new QueryFactory();

    BrowserFieldQuery bfQuery = new BrowserFieldQuery(factory);
    bfQuery.WHERE(bfQuery.getMdAttribute().EQ(mdAttributeTerm.getId()));

    BrowserRootQuery brQuery = new BrowserRootQuery(factory);
    brQuery.WHERE(brQuery.getBrowserField().EQ(bfQuery));

    AllPathsQuery aptQuery = new AllPathsQuery(factory);
    aptQuery.WHERE(aptQuery.getParentTerm().EQ(brQuery.getTerm()));

    TermSynonymQuery synonymQuery = new TermSynonymQuery(factory);
    synonymQuery.WHERE(synonymQuery.getTermName().EQ(label));

    TermQuery query = new TermQuery(factory);
    query.AND(query.getId().EQ(aptQuery.getChildTerm().getId()));
    query.AND(OR.get(query.getTermDisplayLabel().localize().EQ(label), query.synonyms(synonymQuery)));

    OIterator<? extends Term> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        Term entity = iterator.next();

        if (iterator.hasNext())
        {
          NonUniqueCategoryException e = new NonUniqueCategoryException();
          e.setLabel(label);

          throw e;
        }

        return entity;
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

}
