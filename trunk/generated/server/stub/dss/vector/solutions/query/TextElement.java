package dss.vector.solutions.query;

import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;

public class TextElement extends TextElementBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1168235133;
  
  public TextElement()
  {
    super();
  }
  
  @Override
  @Transaction
  public String removeTextElement(String textElementId, String mapId)
  {
    TextElement text = TextElement.get(textElementId);
    String textValue = text.getTextValue();
    String textCustomId = text.getCustomTextElementId();
    
    // delete the instance of TextElement from the default map so it isn't copied back to the saved map
    // this works because the default map should only have one instance of a text element for a given saved map
    DefaultSavedMap defaultMap = SavedMap.getSessionDefaultMap();
    List<? extends TextElement> defaultMapChildTextElements = defaultMap.getAllHasTextElement().getAll();
    for( TextElement defaultMapChildText : defaultMapChildTextElements){
      if(defaultMapChildText.getCustomTextElementId().equals(textCustomId)){
        defaultMapChildText.delete();
      }
    }
    
    text.delete();
    
    return textElementId;
  }
  
}
