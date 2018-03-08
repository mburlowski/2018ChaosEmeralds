package autoActions;

import org.usfirst.frc.team2472.robot.Robot;

import objects.Action;

public class UseIntake extends Action {
	double armSpeed;
	int timeout;
	/**
	 * Give Speed and time for autonomous
	 * @param speed
	 * @param time
	 */
	public UseIntake(double speed,int time) {
		armSpeed=speed;
		timeout=time;
	}
	public void startAction() {
		super.startAction();
	}
	public void periodic() {
		Robot.a.takeIn(armSpeed);
	}
	public void endAction() {
		
	}
public boolean isFinished(){
		
		if(isTimedOut()){
			
			endAction();
			
			return true;
			
		}
		
		else{
		
			return false;
			
		}
		
	}
}
