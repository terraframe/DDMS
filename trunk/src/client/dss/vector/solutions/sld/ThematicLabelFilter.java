package dss.vector.solutions.sld;

import java.util.HashMap;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerDTO;

public class ThematicLabelFilter extends Filter implements Reloadable
{
  protected ThematicLabelFilter(LayerDTO layer)
  {
    super(layer);
  }
  
  @Override
  protected void write(SLDWriter writer)
  {    
    writer.openTag("ogc:PropertyIsEqualTo");

    HashMap<String,String> map = new HashMap<String, String>();
    map.put("name", "isNull");
    
    writer.openTag("ogc:Function", map);
    writer.writeEmptyTagWithValue("ogc:PropertyName", this.layer.getThematicColumnAlias());
    writer.closeTag();

    writer.writeEmptyTagWithValue("ogc:Literal", "false");
    
    writer.closeTag();
  }

}
