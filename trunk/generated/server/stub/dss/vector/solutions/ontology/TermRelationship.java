package dss.vector.solutions.ontology;

import com.runwaysdk.dataaccess.transaction.Transaction;

public class TermRelationship extends TermRelationshipBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1253040116570L;

  public TermRelationship(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public TermRelationship(dss.vector.solutions.ontology.Term parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }

  @Override
  protected String buildKey()
  {
    return this.getId();
  }

  @Override
  @Transaction
  public void apply()
  {
    this.applyWithoutCreatingAllPaths();

    AllPaths.updateAllPathForTerm(this.getChildId(), this.getParentId(), this.getOntologyRelationship().getId());
  }


  @Transaction
  public void applyWithoutCreatingAllPaths()
  {
    this.checkValidRelationshipType();
    super.apply();
  }

  private void checkValidRelationshipType()
  {
    Ontology parentOntology = this.getParent().getOntology();
    Ontology childOntology = this.getChild().getOntology();
    OntologyRelationship ontologyRelationship = this.getOntologyRelationship();

    // If it is a builtin relationship type, then it is OK, as it applies
    // to all ontologies
    if (ontologyRelationship.getIsBuiltIn())
    {
      return;
    }

    boolean validParent = false;
    boolean validChild = false;
    for (Ontology ontology : ontologyRelationship.getAllOntology())
    {
      if (parentOntology.equals(ontology))
      {
        validParent = true;
      }
      if (childOntology.equals(ontology))
      {
        validChild = true;
      }
    }

    // Throw exception if either the child or the parent term are from an ontology with which
    // the relationship is not allowed to participate with.
    if (!validParent || !validChild)
    {
      InvalidRelationshipForOntologyException e = new InvalidRelationshipForOntologyException();
      e.setRelationship(this.getOntologyRelationship().getName());

      if (!validParent)
      {
        e.setOntology(parentOntology.getTitle());
      }

      if (!validChild)
      {
        e.setOntology(childOntology.getTitle());
      }

      throw e;
    }

  }

}
