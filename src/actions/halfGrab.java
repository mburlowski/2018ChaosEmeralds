package actions;

import objects.Action;
import subsystem.arms;

public class halfGrab extends Action {
	arms a;
	boolean dir;

	public halfGrab(boolean side) {
		a = new arms();
		dir = side;
	}

	public void startAction() {
		super.startAction();
	}

	public void periodic() {
		a.halfGrab(dir);
	}

	public void endAction() {

	}
}
