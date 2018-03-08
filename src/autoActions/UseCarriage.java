package autoActions;

import org.usfirst.frc.team2472.robot.Robot;

import objects.Action;

public class UseCarriage extends Action {
	boolean in;
	/**
	 * Pass true to retract carriage
	 * @param time
	 * @param retract
	 */
	public UseCarriage(int time,boolean retract) {
		timeout=time;
		in=retract;
	}
	public void startAction() {
		
	}
	public void periodic() {
		if(in) {
			Robot.c.In();
		} else Robot.c.Out();
	}
	public void endAction() {
		
	}
	public boolean isFinished() {
		if(in) {
			return Robot.c.in;
		} else return Robot.c.out;
	}
	
}