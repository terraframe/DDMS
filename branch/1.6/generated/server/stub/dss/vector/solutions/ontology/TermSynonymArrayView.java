package dss.vector.solutions.ontology;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;


public class TermSynonymArrayView extends TermSynonymArrayViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1040211187;
  
  public TermSynonymArrayView()
  {
    super();
  }
  
  /**
   * MdMethod
   * 
   * @return
   */
  public static TermSynonymArrayView getTermSynonym(String termId)
  {
    TermSynonymArrayView searchView = new TermSynonymArrayView();
    searchView.setTerm(Term.get(termId));
    
    TermSynonymArrayViewQuery query = TermSynonymArrayViewQuery.search(searchView);
    
    OIterator<? extends TermSynonymArrayView> it = query.getIterator();
    try
    {
      if (it.hasNext())
      {
        TermSynonymArrayView view = it.next();
        view.applyToCR();
        return view;
      }
      else
      {
        return null;
      }
    }
    finally
    {
      it.close();
    }
  }
  
  /**
   * Applies the view to the client request, not the concretes.
   */
  public void applyToCR()
  {
    super.apply();
  }
  
  /**
   * MdMethod
   * 
   * @return
   */
  public static dss.vector.solutions.ontology.TermSynonymArrayViewQuery getMostRecent()
  {
    return TermSynonymArrayViewQuery.search(null);
  }
  
  /**
   * MdMethod
   * 
   * @return
   */
  public static dss.vector.solutions.ontology.TermSynonymArrayViewQuery searchByView(TermSynonymArrayView view, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    TermSynonymArrayViewQuery query = TermSynonymArrayViewQuery.search(view);

    if (sortAttribute != null)
    {
      SelectablePrimitive selecPrim = null;
      Selectable selec = null;

      if (sortAttribute.equalsIgnoreCase(TermSynonymArrayView.TERM))
      {
        selecPrim = (SelectablePrimitive) query.getSelectableRef(TermSynonymArrayView.TERM);
      }
      else if (sortAttribute.equalsIgnoreCase(TermSynonymArrayView.TERMNAME))
      {
        selecPrim = (SelectablePrimitive) query.getSelectableRef(TermSynonymArrayView.TERMNAME);
      }
      else if (sortAttribute.equalsIgnoreCase(TermSynonymArrayView.SYNONYMNAMES))
      {
        selecPrim = (SelectablePrimitive) query.getSelectableRef(TermSynonymArrayView.SYNONYMNAMES);
      }
      else
      {
        selec = query.getSelectableRef(TermSynonymArrayView.TERMNAME);
      }

      if (isAscending)
      {
        if (selecPrim != null)
        {
          query.ORDER_BY_ASC(selecPrim, sortAttribute);
        }
        else if (selec != null)
        {
          query.ORDER_BY_ASC(selec, sortAttribute);
        }
      }
      else
      {
        if (selecPrim != null)
        {
          query.ORDER_BY_DESC(selecPrim, sortAttribute);
        }
        else if (selec != null)
        {
          query.ORDER_BY_DESC(selec, sortAttribute);
        }
      }
    }

    query.restrictRows(pageSize, pageNumber);

    return query;
  }
  
  public void applyWithSynonyms(TermSynonymView[] synonymViews)
  {
    // Convert geoId to a geoentity Id
    // Why: The client only knows how to specify geoentities by their geoId, so we did a hack and stuffed the geoId into geoEntityName.
    String termId = this.getValue(TermSynonymArrayView.TERM);
    if (termId == null || termId.equals(""))
    {
      String name = this.getTermName();
      if (name.contains("TERMID="))
      {
        this.setTerm(Term.get(name.replace("TERMID=", "")));
      }
      else
      {
        return;
      }
    }
    Term term = this.getTerm();
    
    // Delete all existing synonyms for this GeoEntity
    OIterator<? extends HasSynonym> termRelsIt = term.getAllSynonymsRel();
    while (termRelsIt.hasNext())
    {
      HasSynonym rel = termRelsIt.next();
      TermSynonym syn = rel.getChild();
      rel.delete();
      syn.delete();
    }
    
    // Apply all the synonyms they sent us.
    ArrayList<String> ids = new ArrayList<String>();
    for (TermSynonymView view : synonymViews)
    {
      String synId = view.getTermSynonymId();
      
      TermSynonym synonym;
      if (synId == null || synId.equals(""))
      {
        synonym = new TermSynonym();
      }
      else
      {
        try
        {
        synonym = TermSynonym.get(view.getTermSynonymId());
        }
        catch (DataNotFoundException e)
        {
          // This is fine. We may have deleted the synonym earlier when we deleted all synonyms linked to our GeoEntity
          synonym = new TermSynonym();
        }
      }
      synonym.setTermName(view.getSynonymName());
      synonym.apply();
      
      ids.add(synonym.getId());
      
      // Update the relationships between the synonym and the geoEntity
      OIterator<? extends HasSynonym> it = synonym.getAllTermRel();
      while(it.hasNext())
      {
        it.next().delete();
      }
      
      synonym.addTerm(term).apply();
    }
    this.setSynonymIds(StringUtils.join(ids, ","));
  }
  
  public void lock()
  {
    String synonymIds = this.getSynonymIds();
    
    if (synonymIds.length() > 0)
    {
      for (String synonymId : synonymIds.split(","))
      {
        TermSynonym gs = TermSynonym.get(synonymId);
        gs.lock();
      }
    }
  }
  
  public void unlock()
  {
    String synonymIds = this.getSynonymIds();
    
    if (synonymIds.length() > 0)
    {
      for (String synonymId : synonymIds.split(","))
      {
        TermSynonym gs = TermSynonym.get(synonymId);
        gs.unlock();
      }
    }
  }
  
}
