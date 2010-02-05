package dss.vector.solutions.surveillance;

import dss.vector.solutions.InvalidPositiveTestProblem;
import dss.vector.solutions.ontology.Term;

public class CaseDiagnostic extends CaseDiagnosticBase implements ChildOption, com.terraframe.mojo.generation.loader.Reloadable {
	private static final long serialVersionUID = 1238693166129L;

	public CaseDiagnostic(String parentId, String childId) {
		super(parentId, childId);
	}

	public CaseDiagnostic(dss.vector.solutions.surveillance.AggregatedCase parent, Term child) {
		this(parent.getId(), child.getId());
	}

	@Override
	public void validateAmountPositive() {
		if (this.getAmountPositive() != null) {
			super.validateAmountPositive();

			if (this.getAmount() == null || this.getAmountPositive() > this.getAmount()) {
				String msg = "It is impossible to have more positive tests than tests";

				InvalidPositiveTestProblem p = new InvalidPositiveTestProblem(msg);
				p.setAmount(this.getAmount());
				p.setAmountPositive(this.getAmountPositive());
				p.setNotification(this, AMOUNTPOSITIVE);
				p.apply();
				p.throwIt();
			}
		}
	}

	@Override
	public void apply() {
		this.validateAmountPositive();

		super.apply();
	}

	public CaseDiagnostic clone(AggregatedCase parent) {
		CaseDiagnostic clone = new CaseDiagnostic(parent, this.getChild());
		clone.setAmount(this.getAmount());
		clone.setAmountPositive(this.getAmountPositive());

		return clone;
	}

	@Override
	protected String buildKey() {
		return this.getParent().getKey() + "." + this.getChild().getKey();
	}
}
