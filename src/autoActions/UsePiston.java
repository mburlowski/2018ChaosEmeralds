package autoActions;

import org.usfirst.frc.team2472.robot.Robot;

public class UsePiston extends Action{
	boolean down;
	/**
	 * Will lower the piston if you pass true.
	 * @param time
	 * @param lower
	 */
	  UsePiston(int time,boolean lower) {
		timeout=time;
		down=lower;
	}
	public void startAction() {
		
	}
	public void periodic() {
		if(down) {
			Robot.Piston.liftDown();
		} else Robot.Piston.liftUp();
	}
	public void endAction() {
		
	}
	public boolean isFinished() {
		if(down) {
			return !Robot.Piston.lifted;
		} else return Robot.c.out;
	}
	


}
