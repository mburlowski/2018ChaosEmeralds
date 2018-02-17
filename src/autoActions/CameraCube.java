package autoActions;

import org.usfirst.frc.team2472.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.AnalogInput;
import objects.Action;

public class CameraCube extends Action {
	double x,right,left;
	public CameraCube(int time) {
		timeout=time;
	}
	public void startAction() {
		
		
	}
	public void periodic() {
		Robot.a.release();
		Robot.a.takeIn(.25);
		
		x=Robot.entry.getDouble(0.0);
		double Const = 100.0;
		double p = 1100.0;
		double speed = 0.4;
		System.out.println(x);
		
		if(Robot.distSense.getValue()<1600) {
		if(x!=0.0&&x>320.0) 
		{
			double xoffset = Math.abs(x-320)/p;
			System.out.print(" xoffset: "+(1-xoffset)+" ");

			Robot.d.turn(speed, (1-xoffset)*speed);
			
			
		}else if(x<320.0&&x!=0.0)
		{
			double xoffset = Math.abs(x-320)/p;
			System.out.print(" xoffset: "+xoffset+" ");
			
			Robot.d.turn((1-xoffset)*speed, speed);
			
			
			
		}
		}else if(Robot.distSense.getValue()>1600) {
			Robot.a.grab();
			Robot.d.stopMotors();
			Robot.a.takeIn(0);
	}
		
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

	
	


