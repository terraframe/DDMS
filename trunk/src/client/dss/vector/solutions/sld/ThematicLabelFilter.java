package dss.vector.solutions.sld;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerDTO;
import dss.vector.solutions.query.QueryConstants;

public class ThematicLabelFilter extends Filter implements Reloadable
{
  protected ThematicLabelFilter(LayerDTO layer)
  {
    super(layer);
  }
  
  @Override
  protected void write(SLDWriter writer)
  {
    writer.writeln("<PropertyIsEqualTo>");
    writer.writeln("<Function name=\"isNull\">");
    writer.writeln("<PropertyName>"+this.layer.getThematicColumnAlias()+"</PropertyName>");
    writer.writeln("</Function>");
    writer.writeln("<Literal>false</Literal>");
    writer.writeln("</PropertyIsEqualTo>");
  }

}
