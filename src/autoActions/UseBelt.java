package autoActions;

import org.usfirst.frc.team2472.robot.Robot;

import objects.Action;
import subsystem.BeltLift;

public class UseBelt extends Action {
	boolean lift;
	int timeout;
	double beltSpeed;
	/**
	 * Give speed for belt
	 * @param time
	 * @param speed
	 */
	public UseBelt(int time,double speed) {
		timeout=time;
		beltSpeed=speed;
	}
	public void startAction() {
		
	}
	public void periodic() {
		Robot.Belt.lift(beltSpeed);
	}
	public void endAction() {
		Robot.Belt.lift(0);
		
	}
	}

