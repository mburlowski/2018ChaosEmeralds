package autoActions;

import org.usfirst.frc.team2472.robot.Robot;

import objects.Action;

public class simpleForward extends Action{
	int timeout;
	double speed;
	/**
	 * drive forward
	 * @param time
	 * @param speed
	 */
	public simpleForward(int time,double Fast) {
		speed=Fast;
		timeout=time;
		
	}
	
	public void periodic() {
		Robot.d.speed(speed, speed);
	}
	
	
	


}