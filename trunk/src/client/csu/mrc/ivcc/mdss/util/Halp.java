package csu.mrc.ivcc.mdss.util;

import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import csu.mrc.ivcc.mdss.mo.AbstractTermDTO;
import csu.mrc.ivcc.mdss.mo.SpecieDTO;

public class Halp implements com.terraframe.mojo.generation.loader.Reloadable {

	public final static String CLASS = "mdss.ivcc.mrc.csu.util.Halp";

	public static String join(List<String> s, String delimiter) {
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

	public static String getDropDownMap(AbstractTermDTO[] terms)
			throws JSONException {
		JSONObject map = new JSONObject();
		for (AbstractTermDTO term : terms) {
			map.put(term.getDisplayLabel(), term.getId());
		}
		return map.toString();
	}

	public static String getDropDownMap2(List<SpecieDTO> terms)
			throws JSONException {
		JSONObject map = new JSONObject();
		for (SpecieDTO term : terms) {
			map.put(term.getDisplayLabel(), term.getId());
		}
		return map.toString();
	}

}
