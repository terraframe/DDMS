package dss.vector.solutions.ontology;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class MO extends MOBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1253040123897L;
  
  public MO()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    // If this is new, set the Ontology value to the MO ontology.
    // FIXME this will be removed once Term subclasses are refactored
    // out and Term will be come concrete.
    if(this.isNew())
    {
      QueryFactory f = new QueryFactory();
      OntologyDefinitionQuery q = new OntologyDefinitionQuery(f);
      
      q.WHERE(q.getOntologyName().EQ("MO"));
      
      OIterator<? extends OntologyDefinition> iter = q.getIterator();
      
      try
      {
        // There WILL be one record; otherwise, the application was not set up properly
        this.setOntology(iter.next());
      }
      finally
      {
        iter.close();
      }
    }
    
    super.apply();
  }
  
}
