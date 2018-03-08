package autoActions;

import org.usfirst.frc.team2472.robot.Robot;

public class UseArmGrip {
	int timeout;
	boolean grip;
	/**
	 * Pass true to grip with the arms
	 * @param time
	 * @param grab
	 */
	public UseArmGrip(int time,boolean grab) {
		timeout=time;
		grip=grab;
	}
	public void startAction() {
		
	}
	public void periodic() {
		if(grip) {
			Robot.a.grab();
		} else Robot.a.release();
	}
	public void endAction() {
		
	}
	public boolean isFinished() {
		if(grip) {
			return !Robot.a.release;
		} else return Robot.a.release;
	}
	


}