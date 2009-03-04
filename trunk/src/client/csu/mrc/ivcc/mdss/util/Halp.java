package csu.mrc.ivcc.mdss.util;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

import csu.mrc.ivcc.mdss.mo.AbstractTermDTO;


public class Halp
{
	 public static String join(ArrayList<String> s, String delimiter) {
	      StringBuilder builder = new StringBuilder();
	      Iterator<String> iter = s.iterator();
	      while (iter.hasNext()) {
	         builder.append(iter.next());
	          if (iter.hasNext()) {
	              builder.append(delimiter);
	          }
	      }
	      return builder.toString();
	 }
	 
	public static String getDropDownMap(AbstractTermDTO[] terms) throws JSONException {
		 JSONObject map = new JSONObject();
		 for(AbstractTermDTO term : terms)
		 {
		    map.put(term.getDisplayLabel(),term.getId());
		 } 
		 return map.toString();
	 }
	 
}
