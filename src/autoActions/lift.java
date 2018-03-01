package autoActions;

import org.usfirst.frc.team2472.robot.Robot;

import objects.Action;
import subsystem.BeltLift;

public class lift extends Action {
	boolean lift;
	int timeout;
	public lift(int time) {
		timeout=time;
	}
	public void startAction() {
		
	}
	public void periodic() {
		if(!Robot.Piston.lifted) {
			Robot.Piston.liftUp();
		}
		else {
			Robot.Belt.lift(0.5);
		}
		
	}
	public void endAction() {
		Robot.Belt.lift(0);
		
	}
	public boolean isFinished() {
		if(isTimedOut()){
			
			endAction();
			
			return true;
			
		}
		
		else{
		
			return false;
			
		}
		
	}
	}

