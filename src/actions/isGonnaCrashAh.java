package actions;

import constants.Const;
import edu.wpi.first.wpilibj.AnalogInput;
import objects.Action;

public class isGonnaCrashAh extends Action {
	AnalogInput distSens;
	boolean isNearWall;
	/**requires distance sensor port*/
	public isGonnaCrashAh(int dSens) {
		distSens = new AnalogInput(dSens);
	}
	/**Checks if the chosen distance sensor is too close to wall*/
	public boolean tailsWatchOut() {
		return distSens.getValue()<10;
	}
}