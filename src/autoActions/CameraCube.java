package autoActions;

import org.usfirst.frc.team2472.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.AnalogInput;

public class CameraCube extends Action {
	double x,right,left;
	public CameraCube(int time) {
		timeout=time;
	}
	public void startAction() {
		
		
	}
	public void periodic() {
		Robot.a.release();
		Robot.a.armSpeed(.25);
		
	//	x=Robot.entry.getDouble(0.0);
		double Const = 100.0;
		double p = 1100.0;
		double speed = 0.4;
		System.out.println(x);
		
		if(1==1) {
		if(x!=0.0&&x>320.0) 
		{
			double xoffset = Math.abs(x-320)/p;
			System.out.print(" xoffset: "+(1-xoffset)+" ");

			Robot.d.setSide(speed, (1-xoffset)*speed);
			
			
		}else if(x<320.0&&x!=0.0)
		{
			double xoffset = Math.abs(x-320)/p;
			System.out.print(" xoffset: "+xoffset+" ");
			
			Robot.d.setSide((1-xoffset)*speed, speed);
			
			
			
		}
		}else if(1==1) {
			Robot.a.grab();
			Robot.d.stopMotors();
			Robot.a.armSpeed(0);
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

	
	


