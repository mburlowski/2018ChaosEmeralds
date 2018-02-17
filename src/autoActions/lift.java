package autoActions;

import org.usfirst.frc.team2472.robot.Robot;

import objects.Action;
import subsystem.SecondLift;

public class lift extends Action {
	boolean lift;
	int timeout;
	public lift(int time) {
		timeout=time;
	}
	public void startAction() {
		
	}
	public void periodic() {
		if(!Robot.First.lifted) {
			Robot.First.liftUp();
		}
		else {
			Robot.Second.lift(0.5);
		}
		
	}
	public void endAction() {
		Robot.Second.lift(0);
		
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

