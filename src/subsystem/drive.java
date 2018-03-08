package subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


import autoActions.Action;
import constants.Const;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;


public class drive extends Action {
	public TalonSRX bL, fL, bR, fR;
	double leftSide,rightSide;
	
	AnalogInput distSensL, distSensR;

	public drive() {
		bL = new TalonSRX(Const.motorBL);
		bR = new TalonSRX(Const.motorBR);
		fL = new TalonSRX(Const.motorFL);
		fR = new TalonSRX(Const.motorFR);
		bR.setInverted(true);
		fR.setInverted(true);
		fR.set(ControlMode.Follower,0);
		bL.set(ControlMode.Follower,0);
		fR.follow(bR);
		bL.follow(fL);
		
		//bR.setInverted(true);
		//fR.setInverted(true);
//		gonnaCrashL = new isGonnaCrashAh(Const.dSensL);
	//	gonnaCrashR = new isGonnaCrashAh(Const.dSensR);
		// setInverted is used to enumerate backward motors
		// usually both motors on one side will be inverted
	}

	

	/** Stops all motors */
	public void stopMotors() {
		//bL.set(ControlMode.PercentOutput, 0.0);
		fL.set(ControlMode.PercentOutput, 0.0);
		//bR.set(ControlMode.PercentOutput, 0.0);
		fR.set(ControlMode.PercentOutput, 0.0);
	}

	/** Moves the robot linearly based on joystick movement */
	public void tankDrive(Joystick ljoy, Joystick rjoy) {
		fR.set(ControlMode.PercentOutput,ljoy.getRawAxis(1));
		fL.set(ControlMode.PercentOutput,rjoy.getRawAxis(1));
	}
	
	/** Moves the robot linearly based on joystick movement */
	/*public void tankDrive(XboxController x) {
		// halves speed if near wall / isGonnaCrash
		// sets speed to the joystick value input times .5 if near wall, else times 1
		if (Math.abs(x.getY(Hand.kLeft)) > Const.ledzone) {
			bL.set(ControlMode.PercentOutput, x.getY(Hand.kLeft) * ((gonnaCrashL.isNearWall()) ? .5 : 1));
			fL.follow(bL);
		}
		if (Math.abs(x.getY(Hand.kRight)) > Const.redzone) {
			bR.set(ControlMode.PercentOutput, x.getY(Hand.kRight) * ((gonnaCrashR.isNearWall()) ? .5 : 1));
			fR.follow(bR);
		}
	}*/
	public void XBoxDrive(XboxController xbox,double baseSpeed){

		/* This is a control scheme inspired by rocket league.

		 * 

		 * Pressing left trigger is reverse and pressing right trigger is forward. 

		 * The left thumb stick controls turning. Pushing the stick left will do will

		 * turn left and pushing the stick right will turn left

		 * 

		 */

	if(xbox.getRawAxis(0)>=0) 
	{
	rightSide=(xbox.getRawAxis(3)-xbox.getRawAxis(2))*(1-xbox.getRawAxis(0));
	leftSide=xbox.getRawAxis(3)-xbox.getRawAxis(2);
	}if(xbox.getRawAxis(0)<0) 
	{
		rightSide=xbox.getRawAxis(3)-xbox.getRawAxis(2);
		leftSide=((xbox.getRawAxis(3)-xbox.getRawAxis(2))*(1+xbox.getRawAxis(0)));
	}if(xbox.getRawAxis(0)>0&&xbox.getRawButton(2)) 
	{
		rightSide=-leftSide;
	}
	if(xbox.getRawAxis(0)<0&&xbox.getRawButton(2)) 
	{
		leftSide=-rightSide;
		
	}
	leftSide=leftSide*baseSpeed;
	rightSide=rightSide*baseSpeed;
		turn(leftSide,rightSide);

		//combines the ax'es of the triggers

		

		

	}

	/**
	 * [WIP] Moves the robot based on a quadratic function of joystick movement
	 */
	
	/** Moves all motors at same power */
	public void setAllMotors(double spped) {

		bL.set(ControlMode.PercentOutput, spped);
		//fL.set(ControlMode.PercentOutput, spped);
		bR.set(ControlMode.PercentOutput, spped);
	//	fR.set(ControlMode.PercentOutput, spped);
	}

	/** Runs both sides of the drivetrain at desired power individually */
	public void turn(double speedL, double speedR) {

		//bL.set(ControlMode.PercentOutput, speedL);
		fL.set(ControlMode.PercentOutput, speedL);
		bR.set(ControlMode.PercentOutput,speedR);
		//fR.set(ControlMode.PercentOutput, speedR);
		}

	/**
	 * Causes the robot to spin by running both sides of the drive train in
	 * opposition
	 */
	/** Runs front right motor at full power */
	public void runFR(double spped) {
		fR.set(ControlMode.PercentOutput, spped);
	}

	/** Runs back right motor at full power */
	public void runBR(double spped) {
		bR.set(ControlMode.PercentOutput, spped);
	}

	/** Runs front left motor at full power */
	public void runFL(double spped) {
		fL.set(ControlMode.PercentOutput, spped);
	}

	/** Runs back left motor at full power */
	public void runBL(double spped) {
		fL.set(ControlMode.PercentOutput, spped);
	}
	public void speed(double left, double right) 
	{
		bR.set(ControlMode.Velocity,left);
		fL.set(ControlMode.Velocity,right);
		
	}
}
