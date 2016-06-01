package dss.vector.solutions.ontology;

import java.sql.Savepoint;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.DuplicateGraphPathException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;

import dss.vector.solutions.general.Disease;

public class TermNode implements Comparable<TermNode>, Reloadable
{
  private String id;
  private String name;
  private Integer indent;
  private Term term;
  private Boolean active;
  
  private List<TermNode> parents;
  private List<TermNode> children;
  private static OntologyRelationship ontRel = OntologyRelationship.getByKey(OBO.IS_A);
  
  public TermNode()
  {
    parents = new LinkedList<TermNode>();
    children = new LinkedList<TermNode>();
  }
  
  public TermNode(Term term)
  {
    this();
    this.term = term;
    this.id = term.getTermId();
    this.name = term.getName();
  }
  
  public void apply()
  {
    if (term==null)
    {
      term = new Term();
      term.setTermId(id);
      term.setOntology(OntologyExcelImporter.getOntology());
    }
    else
    {
      term.lock();
    }
    term.setName(name);
    term.apply();
    
    Disease current = Disease.getCurrent();
    
    OIterator<? extends InactiveProperty> props = term.getAllInactiveProperties();
    try
    {
      for (InactiveProperty ip : props)
      {
        ip.appLock();
        Disease disease = ip.getDisease();
        
        if (disease.equals(current))
        {
          ip.setInactive(!active);
        }
        else
        {
          ip.setInactive(true);
        }
        ip.apply();
      }
    }
    finally
    {
      props.close();
    }
  }
  
  public void applyRelationships()
  {
//    Don't pre-check; it's chepaer to just catch the exception
//    Set<Term> existing = new TreeSet<Term>();
//    for (Term t : term.getAllParentTerm())
//    {
//      existing.add(t);
//    }
    
    for (TermNode parentNode : parents)
    {
      Savepoint savepoint = Database.setSavepoint();
      
      Term parentTerm = parentNode.getTerm();
//      if (!existing.contains(parentNode))
//      {
      try
      {
        TermRelationship rel = term.addParentTerm(parentTerm);
        rel.setOntologyRelationship(OntologyExcelImporter.getOntologyRelationship());
        rel.apply();
      }
      catch (DuplicateGraphPathException e2)
      {
        // a relationship between this typedef and the ontology already exists
        Database.rollbackSavepoint(savepoint);
      }
      finally
      {
        Database.releaseSavepoint(savepoint);
      }
    }
  }
  
  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof TermNode)
    {
      TermNode other = (TermNode)obj;
      return this.getId().equals(other.getId());
    }
    return super.equals(obj);
  }
  
  public int compareTo(TermNode o)
  {
    return this.getId().compareTo(o.getId());
  }
  
  public boolean lowerIndentThan(TermNode other)
  {
    if (other==null)
    {
      return false;
    }
    
    return other.getIndent()>=this.getIndent();
  }
  
  @Override
  public String toString()
  {
    String toString = id + ": " + name + " <-- [";
    for (TermNode p : parents)
      toString += p.getId() + ':' + p.getName();
    toString += "]";
    return toString;
  }
  
  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setActive(Boolean active)
  {
    this.active = active;
  }

  public Integer getIndent()
  {
    return indent;
  }

  public void setIndent(Integer indent)
  {
    this.indent = indent;
  }
  
  private Term getTerm()
  {
    return term;
  }
  
  public void addParent(TermNode parent)
  {
    if (parent!=null)
    {
      parents.add(parent);
    }
  }
}
