/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2472.robot;

import java.util.ArrayList;

import com.kauailabs.nav6.frc.IMUAdvanced;

import autoActions.armIntake;
import autoActions.doNothing;
import autoActions.extendCarriage;
import autoActions.lift;
import autoActions.pathFollower;
import constants.Const;
import constants.ConstPaths;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import objects.Action;
import subsystem.FirstLift;
import subsystem.SecondLift;
import subsystem.arms;
import subsystem.carriage;
import subsystem.climber;
import subsystem.drive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	int armPos=0;
	int liftPos=0;
	int carriagePos=0;
//	UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture();
	boolean scaleClose,switchClose;
	Compressor compress=new Compressor(0);
	//public static AnalogInput distSense = new AnalogInput(Const.dSense);
	//public static NetworkTableEntry entry;
	Joystick xbox =new Joystick(Const.xboxManipulator);
	public static drive d = new drive();
	char fieldSide='q';
	public static arms a = new arms();
	public static carriage c =new carriage();
	public static FirstLift First=new FirstLift();
	public static SecondLift Second=new SecondLift();
	public static climber climb=new climber();
	

	Joystick joyL = new Joystick(Const.jstickL);
	Joystick joyR = new Joystick(Const.jstickR);
	XboxController xboxDrive = new XboxController(Const.xbox);
	Joystick box = new Joystick(Const.box);

	ArrayList<Action> step = new ArrayList<Action>();
	ArrayList<Action> step2 = new ArrayList<Action>();
	int nAction = 0;
	
	//NetworkTableInstance offSeasonNetworkTable;
	String gameDataInit;

	char[] gameData;
	boolean[] automode; // left = false, right = true
	int testmode = 0;
	String[] testStrings;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
//		cam0.setFPS(20);
//		cam0.setResolution(640, 480);
		compress.setClosedLoopControl(true);
		
		// smartdashboard shtuff
	//	SmartDashboard.putNumber("A. Number", .1);
		
		//offSeasonNetworkTable = NetworkTableInstance.create();
		//offSeasonNetworkTable.startClient("10.0.100.5");
	//	gameDataInit = offSeasonNetworkTable.getTable("OffseasonFMSInfo").getEntry("GameData").getString("defaultValue");
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString line to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the SendableChooser
	 * make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		if(box.getRawButton(4))fieldSide='L';
		if(box.getRawButton(5))fieldSide='M';
		if(box.getRawButton(6))fieldSide='R';
		//gameData = DriverStation.getInstance().getGameSpecificMessage().toCharArray();
		//For off season
		//gameData = gameDataInit.toCharArray();

		//2 chances to get the data in AUTOinit
		/*switch(fieldSide) {
			case('L'):
				if(gameData[0]=='L') 
				{	
					// TODO left side to left Switch
				}
				else 
				{
					// TODO leftside to right Switch
				}
					
					
				break;
			case('M'):
				if(gameData[0])
				break;
			case('R'):
				break;
			
		}*/
			if(gameData.length==0) {
				
			}else 
			{
			//gameData = DriverStation.getInstance().getGameSpecificMessage().toCharArray();
				
			}
			switchClose=(gameData[0]==fieldSide);
			scaleClose=(gameData[1]==fieldSide);
			if(scaleClose&&switchClose) 
			{
				//if both are close
				if(box.getRawButton(7)) 
				{
				//scale	
					hitScale();
					
				}
				if(box.getRawButton(8)) 
				{
					hitSwitchClose();
				//switch
				}
				if(box.getRawButton(9)) 
				{
				//both switch and scale
				}
			}else if(scaleClose&&!switchClose) 
			{
				if(box.getRawButton(7)) 
				{
				//scale	
					hitScale();
				}
				if(box.getRawButton(8)) 
				{
				//switch
					hitSwitchFar();
					
				}
				if(box.getRawButton(9)) 
				{
				//both switch and scale(DO NOT USE NOT ENOUGH TIME DEFERS to just switch)
				}
			}else if(!scaleClose&&switchClose) 
			{
				if(box.getRawButton(7)) 
				{
				//scale	
					hitFarScale();
					
				}
				if(box.getRawButton(8)) 
				{
					hitSwitchClose();
				//switch
				}
				if(box.getRawButton(9)) 
				{
					//both switch and scale(DO NOT USE NOT ENOUGH TIME DEFERS to just switch)
				}
			}else if(scaleClose&&!switchClose) 
			{
				if(box.getRawButton(7)) 
				{
				//scale	
					hitScale();
				}
				if(box.getRawButton(8)) 
				{
				//switch
				}
				if(box.getRawButton(9)) 
				{
					//both switch and scale(DO NOT USE NOT ENOUGH TIME DEFERS to just switch)
				}
			}else if(!scaleClose&&!switchClose) 
			{
				if(box.getRawButton(7)) 
				{
				//scale	
					hitFarScale();
				}
				if(box.getRawButton(8)) 
				{
				//switch
					hitSwitchFar();
				}
				if(box.getRawButton(9)) 
				{
				//both switch and scale(TEST, there may be enough time)
				}
			
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		//d.XBoxDrive(xboxDrive, 1.0);
		a.takeIn(xbox.getRawAxis(2)-xbox.getRawAxis(3));
		
		if(First.lifted&&xbox.getRawButton(Const.buttonA))liftPos=1;
		if(!First.lifted&&xbox.getRawButton(Const.buttonA))liftPos=2;
		if(liftPos==1)First.liftDown();
		if(liftPos==2)First.liftUp();
		if(c.in&&xbox.getRawButton(Const.buttonB))carriagePos=1;
		if(!c.in&&xbox.getRawButton(Const.buttonB))carriagePos=2;
		if(carriagePos==1)c.Out();
		if(carriagePos==2)c.In();
		if(a.grab&&xbox.getRawButton(Const.buttonX))armPos=1;
		if(!a.grab&&xbox.getRawButton(Const.buttonX))armPos=2;
		if(armPos==1)a.release();
		if(armPos==2)a.grab();
		Second.lift(xbox.getRawAxis(1));
	}

	@Override
	public void testInit() {
		testmode = 0;
		testStrings = new String[] { "Talon test mode", "DoubleSolenoid test mode", "Solenoid test mode" };
		System.out.println(testStrings[testmode]);
	}

	@Override
	
	public void testPeriodic() {
		// DPad left and right switch test modes
		SmartDashboard.putNumber("BL Motor", d.bL.getMotorOutputPercent());
		SmartDashboard.putNumber("BR Motor", d.bR.getMotorOutputPercent());
		SmartDashboard.putNumber("FL Motor", d.fL.getMotorOutputPercent());
		SmartDashboard.putNumber("FR Motor", d.fR.getMotorOutputPercent());
		SmartDashboard.putNumber("IL Motor", a.intakeLeftArm.getMotorOutputPercent());
		SmartDashboard.putNumber("IR Motor", a.intakeRightArm.getMotorOutputPercent());
		switch (testmode) {

		case 0:
			// talon speeds are set by the analog triggers (LT/RT)
			// RT should move forward, LT should move backward.
			// A button should run BackLeft motor
			// B button should run BackRight motor
			// X button should run FrontLeft motor
			// Y button should run FrontRight motor
			// Left Bumper should run Left arm intake
			// Right Bumper should run Right arm intake
			if (xboxDrive.getAButtonPressed()) {
				if (xboxDrive.getTriggerAxis(Hand.kRight) >= 60 && xboxDrive.getTriggerAxis(Hand.kLeft) < 60)
					d.runBL(xboxDrive.getTriggerAxis(Hand.kLeft));
				if (xboxDrive.getTriggerAxis(Hand.kLeft) >= 60 && xboxDrive.getTriggerAxis(Hand.kRight) < 60)
					d.runBL(-xboxDrive.getTriggerAxis(Hand.kLeft));
			} else if (xboxDrive.getAButtonReleased()) {
				d.runBL(0);
			}
			if (xboxDrive.getBButtonPressed()) {
				if (xboxDrive.getTriggerAxis(Hand.kRight) >= 60 && xboxDrive.getTriggerAxis(Hand.kLeft) < 60)
					d.runBR(xboxDrive.getTriggerAxis(Hand.kLeft));
				if (xboxDrive.getTriggerAxis(Hand.kLeft) >= 60 && xboxDrive.getTriggerAxis(Hand.kRight) < 60)
					d.runBR(-xboxDrive.getTriggerAxis(Hand.kLeft));
			} else if (xboxDrive.getBButtonReleased()) {
				d.runBR(0);
			}
			if (xboxDrive.getXButtonPressed()) {
				if (xboxDrive.getTriggerAxis(Hand.kRight) >= 60 && xboxDrive.getTriggerAxis(Hand.kLeft) < 60)
					d.runFL(xboxDrive.getTriggerAxis(Hand.kLeft));
				if (xboxDrive.getTriggerAxis(Hand.kLeft) >= 60 && xboxDrive.getTriggerAxis(Hand.kRight) < 60)
					d.runBL(-xboxDrive.getTriggerAxis(Hand.kLeft));
			} else if (xboxDrive.getXButtonReleased()) {
				d.runFL(0);
			}
			if (xboxDrive.getYButtonPressed()) {
				if (xboxDrive.getTriggerAxis(Hand.kRight) >= 60 && xboxDrive.getTriggerAxis(Hand.kLeft) < 60)
					d.runFR(xboxDrive.getTriggerAxis(Hand.kLeft));
				if (xboxDrive.getTriggerAxis(Hand.kLeft) >= 60 && xboxDrive.getTriggerAxis(Hand.kRight) < 60)
					d.runFR(-xboxDrive.getTriggerAxis(Hand.kLeft));
			} else if (xboxDrive.getYButtonReleased()) {
				d.runFR(0);
			}
			if (xboxDrive.getBumperPressed(Hand.kLeft)) {
				if (xboxDrive.getTriggerAxis(Hand.kRight) >= 60 && xboxDrive.getTriggerAxis(Hand.kLeft) < 60)
					a.runL(xboxDrive.getTriggerAxis(Hand.kLeft));
				if (xboxDrive.getTriggerAxis(Hand.kLeft) >= 60 && xboxDrive.getTriggerAxis(Hand.kRight) < 60)
					a.runL(-xboxDrive.getTriggerAxis(Hand.kLeft));
			} else if (xboxDrive.getBumperReleased(Hand.kLeft)) {
				a.runL(0);
			}
			if (xboxDrive.getBumperPressed(Hand.kRight)) {
				if (xboxDrive.getTriggerAxis(Hand.kRight) >= 60 && xboxDrive.getTriggerAxis(Hand.kLeft) < 60)
					a.runR(xboxDrive.getTriggerAxis(Hand.kLeft));
				if (xboxDrive.getTriggerAxis(Hand.kLeft) >= 60 && xboxDrive.getTriggerAxis(Hand.kRight) < 60)
					a.runR(-xboxDrive.getTriggerAxis(Hand.kLeft));
			} else if (xboxDrive.getBumperReleased(Hand.kRight)) {
				a.runR(0);
			}
			break;
		case 1:
			// Hold LB, RB, LT, or RT to choose piston (bumpers forward, triggers up)
			// press Y to set piston "forward"
			// press B to set piston "reverse"
			// press X to set piston "off"
			/*
			if (xboxDrive.getYButtonPressed()) {
				if (xboxDrive.getBumperPressed(Hand.kLeft))
					a.runPiston(0, 2);
				else if (xboxDrive.getBumper(Hand.kRight))
					a.runPiston(1, 2);
				else if (xboxDrive.getTriggerAxis(Hand.kLeft)>.5)
					a.runPiston(2, 2);
				else if (xboxDrive.getTriggerAxis(Hand.kRight)>.5)
					a.runPiston(3, 2);
			}
			if (xboxDrive.getBButtonPressed()) {
				if (xboxDrive.getBumperPressed(Hand.kLeft))
					a.runPiston(0, 1);
				else if (xboxDrive.getBumper(Hand.kRight))
					a.runPiston(1, 1);
				else if (xboxDrive.getTriggerAxis(Hand.kLeft)>.5)
					a.runPiston(2, 1);
				else if (xboxDrive.getTriggerAxis(Hand.kRight)>.5)
					a.runPiston(3, 1);
			}
			if (xboxDrive.getAButtonPressed()) {
				if (xboxDrive.getBumperPressed(Hand.kLeft))
					a.runPiston(0, 0);
				else if (xboxDrive.getBumper(Hand.kRight))
					a.runPiston(1, 0);
				else if (xboxDrive.getTriggerAxis(Hand.kLeft)>.5)
					a.runPiston(2, 0);
				else if (xboxDrive.getTriggerAxis(Hand.kRight)>.5)
					a.runPiston(3, 0);
			}
			break;
		case 2:
			// Hold LB, RB, LT, or RT to choose piston (bumpers forward, triggers up)
			// press Y to set piston "on"
			// press X to set piston "off"
			if (xboxDrive.getYButtonPressed()) {
				if (xboxDrive.getBumperPressed(Hand.kLeft))
					a.runPiston(0, true);
				else if (xboxDrive.getBumperPressed(Hand.kRight))
					a.runPiston(1, true);
				else if (xboxDrive.getTriggerAxis(Hand.kLeft)>.5) 
					a.runPiston(2, true);
				else if (xboxDrive.getTriggerAxis(Hand.kRight)>.5) 
					a.runPiston(3, true);
			}
			if (xboxDrive.getAButtonPressed()) {
				if (xboxDrive.getBumperPressed(Hand.kLeft))
					a.runPiston(0, false);
				else if (xboxDrive.getBumperPressed(Hand.kRight))
					a.runPiston(1, false);
				else if (xboxDrive.getTriggerAxis(Hand.kLeft)>.5) 
					a.runPiston(2, false);
				else if (xboxDrive.getTriggerAxis(Hand.kRight)>.5) 
					a.runPiston(3, false);
			}

			break;
		}
		if (xboxDrive.getPOV() == Const.povRight && testmode < 2) {
			testmode++;
			testInit();
		}
		if (xboxDrive.getPOV() == Const.povLeft && testmode > 0) {
			testmode--;
			testInit();*/
		}

	}
	
	public void hitScale() {
		if(gameData[1]=='L') {
			step.add(new pathFollower(ConstPaths.longScale, ConstPaths.shortScale, ConstPaths.longScale.length-1));
			step2.add(new lift(3));
			step.add(new extendCarriage(1));
			step2.add(new armIntake(.5,3));
			step.add(null);
			step2.add(null);
		}else {
			step.add(new pathFollower(ConstPaths.shortScale, ConstPaths.longScale, ConstPaths.longScale.length-1));
			step2.add(new lift(3));
			step.add(new extendCarriage(1));
			step2.add(new armIntake(.5,3));
			step.add(null);
			step2.add(null);
		}
	}
	public void hitFarScale() 
	{
		if(gameData[1]=='L') {
			step.add(new pathFollower(ConstPaths.longLongScale, ConstPaths.shortLongScale, ConstPaths.longLongScale.length-1));
			step2.add(new lift(3));
			step.add(new extendCarriage(1));
			step2.add(new armIntake(.5,3));
			step.add(null);
			step2.add(null);
		}else {
			step.add(new pathFollower(ConstPaths.shortLongScale, ConstPaths.longLongScale, ConstPaths.longLongScale.length-1));
			step2.add(new lift(3));
			step.add(new extendCarriage(1));
			step2.add(new armIntake(.5,3));
			step.add(null);
			step2.add(null);
		}
		
	}
	public void hitSwitchClose() {
		
		if(gameData[0]=='L') {
			step.add(new pathFollower(ConstPaths.longSwitch, ConstPaths.shortSwitch, ConstPaths.shortSwitch.length-1));
			step2.add(new lift(3));
			step.add(new extendCarriage(1));
			step2.add(new armIntake(.5,3));
			step.add(null);
			step2.add(null);
		}else if(fieldSide=='M'){
			if(gameData[0]=='L') 
			{
				step.add(new pathFollower(ConstPaths.longMidswitch, ConstPaths.shortMidswitch, ConstPaths.shortMidswitch.length-1));
				step2.add(new lift(3));
				step.add(new extendCarriage(1));
				step2.add(new armIntake(.5,3));
				step.add(null);
				step2.add(null);
			}
			if(gameData[0]=='R') 
			{
				step.add(new pathFollower(ConstPaths.shortMidswitch, ConstPaths.longMidswitch, ConstPaths.longMidswitch.length-1));
				step2.add(new lift(3));
				step.add(new extendCarriage(1));
				step2.add(new armIntake(.5,3));
				step.add(null);
				step2.add(null);
			}
		}else {
			step.add(new pathFollower(ConstPaths.shortSwitch, ConstPaths.longSwitch, ConstPaths.shortSwitch.length-1));
			step2.add(new lift(3));
			step.add(new extendCarriage(1));
			step2.add(new armIntake(.5,3));
			step.add(null);
			step2.add(null);
		}
		}
	public void hitSwitchFar() {
		if(gameData[0]=='L') {
			step.add(new pathFollower(ConstPaths.LongSwitchLeft, ConstPaths.LongSwitchRight, ConstPaths.LongSwitchRight.length-1));
			step2.add(new lift(3));
			step.add(new extendCarriage(1));
			step2.add(new armIntake(.5,3));
			step.add(null);
			step2.add(null);
			
		}else if(gameData[0]=='R') {
			step.add(new pathFollower(ConstPaths.LongSwitchRight, ConstPaths.LongSwitchLeft, ConstPaths.LongSwitchRight.length-1));
			step2.add(new lift(3));
			step.add(new extendCarriage(1));
			step2.add(new armIntake(.5,3));
			step.add(null);
			step2.add(null);
			
			
		}
		
		
		
	}
		
	}
	

