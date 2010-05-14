package dss.vector.solutions.general;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.session.Session;

import dss.vector.solutions.MDSSUser;

public class Disease extends DiseaseBase implements com.runwaysdk.generation.loader.Reloadable {
	private static final long serialVersionUID = -1180941084;

	public static final String MALARIA = "MALARIA";
	public static final String DENGUE = "DENGUE";

	public static Disease getMalaria() {
		return Disease.getByKey(MALARIA);
	}

	public static Disease getDengue() {
		return Disease.getByKey(DENGUE);
	}

	public static Disease getCurrent() {
		String id = Session.getCurrentSession().getUser().getId();
		MDSSUser user = MDSSUser.get(id);

		String name = user.getDiseaseName();
		return Disease.getByKey(name);
	}
	
	public static Disease[] getAllDiseases() {
		DiseaseQuery query = Disease.getAllInstances(null, true, 0, 0);
		OIterator<? extends Disease> it = query.getIterator();
		return it.getAll().toArray(new Disease[(int) query.getCount()]);
	}

	public Disease() {
		super();
	}

}
