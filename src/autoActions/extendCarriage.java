package autoActions;

import org.usfirst.frc.team2472.robot.Robot;

import objects.Action;

public class extendCarriage extends Action {
	public extendCarriage(int time) {
		timeout=time;
	}
	public void startAction() {
		Robot.c.Out();
	}
	public void periodic() {
		Robot.c.Out();
	}
	public void endAction() {
		
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
