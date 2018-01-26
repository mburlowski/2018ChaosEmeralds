package subsystem;

import constants.Const;
import edu.wpi.first.wpilibj.AnalogInput;

public class isGonnaCrashAh {
	AnalogInput distSens;
	boolean isNearWall;
	public isGonnaCrashAh(int dSens) {
		distSens = new AnalogInput(dSens);
	}
	/**Checks if the chosen distance sensor is too close to wall*/
	public boolean tailsWatchOut() {
		return distSens.getValue()<10;
	}
}