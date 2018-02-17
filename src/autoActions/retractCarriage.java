package autoActions;

import org.usfirst.frc.team2472.robot.Robot;

import objects.Action;

public class retractCarriage extends Action {
	public retractCarriage(int time) {
		timeout=time;
	}
	public void startAction() {
		
	}
	public void periodic() {
		Robot.c.In();
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