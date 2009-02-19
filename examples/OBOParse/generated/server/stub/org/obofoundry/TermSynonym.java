package org.obofoundry;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class TermSynonym extends TermSynonymBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public TermSynonym()
  {
    super();
  }
  
  public String toString()
  {
    return this.getSynonymName().replace("\"", " ");
  }
 
  public static TermSynonym getSynonymByName(String synonymName)
  {
    TermSynonymQuery q = new TermSynonymQuery(new QueryFactory());
    
    q.WHERE(q.getSynonymName().EQ(synonymName));
    
    OIterator<? extends TermSynonym> iterator = q.getIterator();
    
    if (iterator.hasNext())
    {
      return iterator.next();
    }
    else
    {
      return null;
    }
  }
}