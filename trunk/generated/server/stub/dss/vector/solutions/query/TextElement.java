/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.query;

import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;

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
