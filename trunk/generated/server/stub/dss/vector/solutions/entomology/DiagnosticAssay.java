package dss.vector.solutions.entomology;

import dss.vector.solutions.general.Disease;

public class DiagnosticAssay extends DiagnosticAssayBase implements com.runwaysdk.generation.loader.Reloadable {
	private static final long serialVersionUID = -469781453;

	public DiagnosticAssay() {
		super();
	}

	@Override
	public void apply() {
		if (this.isNew() && this.getDisease() == null) {
			this.setDisease(Disease.getCurrent());
		}
		super.apply();
	}

}
