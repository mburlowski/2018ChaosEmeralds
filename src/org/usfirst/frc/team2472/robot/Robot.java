/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2472.robot;

import java.util.ArrayList;
import autoActions.UseIntake;
import autoActions.Action;
import autoActions.UseBelt;
import autoActions.UseCarriage;
import autoActions.pathFollower;
import constants.Const;
import constants.ConstPaths;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import subsystem.PistonLift;
import subsystem.BeltLift;
import subsystem.arms;
import subsystem.carriage;
import subsystem.drive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	long time;
	int mode;
	long cTime, aTime, pTime;
	int armPos = 0;
	int currentAction;
	int liftPos = 0;
	int carriagePos = 0;

	boolean switchClose;
	Compressor compress = new Compressor(0);
	int AutoSpeedL=200;
	int AutoSpeedR=200;
	Joystick L = new Joystick(4);
	boolean lastChange = false;
	Joystick R = new Joystick(5);
	Joystick xbox = new Joystick(Const.xboxManipulator);
	public static drive d = new drive();
	char fieldSide = 'q';
	public static arms a = new arms();
	public static carriage c = new carriage();
	public static PistonLift Piston = new PistonLift();
	public static BeltLift Belt = new BeltLift();
	XboxController xboxDrive = new XboxController(Const.xbox);
	Joystick box = new Joystick(Const.box);

	// IMUAdvanced imu = new IMUAdvanced(Const.imuPort);

	ArrayList<Action> step = new ArrayList<Action>();
	ArrayList<Action> step2 = new ArrayList<Action>();

	// NetworkTableInstance offSeasonNetworkTable;
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
		UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture();
		cam0.setFPS(60);
		cam0.setResolution(160, 120);
		cam0.setWhiteBalanceAuto();
		cam0.setExposureAuto();
		compress.setClosedLoopControl(true);
	}

	
	public void autonomousInit() {
		gameData = DriverStation.getInstance().getGameSpecificMessage().toCharArray();
		time = System.currentTimeMillis();
		if (box.getRawButton(5))
			fieldSide = 'L';
		if (box.getRawButton(6))
			fieldSide = 'M';
		if (box.getRawButton(7))
			fieldSide = 'R';
		switchClose = (fieldSide == gameData[0]);
		int mode = 0;

		
	
		if (fieldSide == 'M') {
			// TODO write code for middle to switcho
			midSwitch();
		} else {
			if (switchClose) {
				System.out.println("Close switch left");
				hitSwitch();
			} else if (fieldSide != 'M' && !switchClose) {
				System.out.println("Close switch left");
				hitSwitchLong();
			}

		}

		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		for(int i=0;i<step.size();i++) 
		{
			step.get(i).compareTime(time);
			step2.get(i).compareTime(time);
			if(step.get(i).ActionEnded)step.remove(i);
			if(step2.get(i).ActionEnded)step2.remove(i);
		}
		
		
		 // AUTONYMOUS THAT IS CONFIRMED TO WORK 
		

		 if(time+3570>System.currentTimeMillis())
		  {d.speed(AutoSpeedL, AutoSpeedR); }else {d.speed(0, 0);}
		  
		  if(time+3570<System.currentTimeMillis()&&time+3570+3000>System.
		  currentTimeMillis()&&switchClose) { Belt.lift(-1.0);
		  }if(time+3570+3000<System.currentTimeMillis()&&switchClose) { Belt.lift(0.0);
		  a.armSpeed(-.5); }
		 
	}

	public void teleopInit() {
		aTime = System.currentTimeMillis();
		// cTime = System.currentTimeMillis();
		pTime = System.currentTimeMillis();

	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		if (xbox.getRawButton(5)) {
			c.Out();
			cTime = System.currentTimeMillis();
		System.out.print(carriagePos);
		}
		if (xbox.getRawButton(6)) {
			c.In();
			cTime = System.currentTimeMillis();
			System.out.print(carriagePos);
		}
		/*
		 * Button mapings
		 * 
		 * left stick is belt lift
		 * 
		 * Y is toggle to send out carrage B is lower piston lift A is raise piston lift
		 * X is toggle for arm to close/open
		 * 
		 * Arm intake is left trigger - right trigger
		 * 
		 * 
		 */
		// d.tankDrive(L,R);
		d.XBoxDrive(xboxDrive, .7);

		// Arm intake
		a.armSpeed(xbox.getRawAxis(1));

		// Piston Toggle on A
		// if(Piston.lifted&&xbox.getRawButton(Const.buttonA))liftPos=1;
		// if(!Piston.lifted&&xbox.getRawButton(Const.buttonA))liftPos=2;
		// if(liftPos==1)Piston.liftDown();

		// if(liftPos==2)Piston.liftUp();

		if (xbox.getRawButton(Const.buttonA))
			Piston.liftUp();
		if (xbox.getRawButton(Const.buttonB))
			Piston.liftDown();

		// &&cTime+20<System.currentTimeMillis()
		// Carriaige toggle on Y
		if (carriagePos == 2 && xbox.getRawButton(Const.buttonY)) {
			carriagePos = 1;
		}
		if (carriagePos == 1 && xbox.getRawButton(Const.buttonY)) {
			carriagePos = 2;
		}

		// Arm in unless it is held

		if (xbox.getRawButton(Const.buttonX)) {

			armPos = 1;
			System.out.println("arms");
			a.armSpeed(.4);

		}

		else {
			armPos = 2;
		}

		if (armPos == 1) {
			a.release();
			if (!lastChange)
				aTime = System.currentTimeMillis();
			else {
				lastChange = true;
			}
		}
		if (armPos == 2) {
			a.grab();
			aTime = System.currentTimeMillis();
		}

		// Belt lift toggle on left stick
		Belt.lift(xbox.getRawAxis(3) - xbox.getRawAxis(2));
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
		/*
		 * if(xbox.getRawButton(Const.buttonA))d.runBL(.25); else d.runBL(0);
		 * if(xbox.getRawButton(Const.buttonB))d.runFL(.25); else d.runFL(0);
		 * if(xbox.getRawButton(Const.buttonX))d.runBR(.25); else d.runBR(0);
		 * if(xbox.getRawButton(Const.buttonY))d.runFR(.25); else d.runFR(0);
		 */

		/*
		 * SmartDashboard.putNumber("BL Motor", d.bL.getMotorOutputPercent());
		 * SmartDashboard.putNumber("BR Motor", d.bR.getMotorOutputPercent());
		 * SmartDashboard.putNumber("FL Motor", d.fL.getMotorOutputPercent());
		 * SmartDashboard.putNumber("FR Motor", d.fR.getMotorOutputPercent());
		 * SmartDashboard.putNumber("IL Motor",
		 * a.intakeLeftArm.getMotorOutputPercent());
		 * SmartDashboard.putNumber("IR Motor",
		 * a.intakeRightArm.getMotorOutputPercent()); switch (testmode) {
		 * 
		 * case 0: // talon speeds are set by the analog triggers (LT/RT) // RT should
		 * move forward, LT should move backward. // A button should run BackLeft motor
		 * // B button should run BackRight motor // X button should run FrontLeft motor
		 * // Y button should run FrontRight motor // Left Bumper should run Left arm
		 * intake // Right Bumper should run Right arm intake if
		 * (xboxDrive.getAButtonPressed()) { if (xboxDrive.getTriggerAxis(Hand.kRight)
		 * >= 60 && xboxDrive.getTriggerAxis(Hand.kLeft) < 60)
		 * d.runBL(xboxDrive.getTriggerAxis(Hand.kLeft)); if
		 * (xboxDrive.getTriggerAxis(Hand.kLeft) >= 60 &&
		 * xboxDrive.getTriggerAxis(Hand.kRight) < 60)
		 * d.runBL(-xboxDrive.getTriggerAxis(Hand.kLeft)); } else if
		 * (xboxDrive.getAButtonReleased()) { d.runBL(0); } if
		 * (xboxDrive.getBButtonPressed()) { if (xboxDrive.getTriggerAxis(Hand.kRight)
		 * >= 60 && xboxDrive.getTriggerAxis(Hand.kLeft) < 60)
		 * d.runBR(xboxDrive.getTriggerAxis(Hand.kLeft)); if
		 * (xboxDrive.getTriggerAxis(Hand.kLeft) >= 60 &&
		 * xboxDrive.getTriggerAxis(Hand.kRight) < 60)
		 * d.runBR(-xboxDrive.getTriggerAxis(Hand.kLeft)); } else if
		 * (xboxDrive.getBButtonReleased()) { d.runBR(0); } if
		 * (xboxDrive.getXButtonPressed()) { if (xboxDrive.getTriggerAxis(Hand.kRight)
		 * >= 60 && xboxDrive.getTriggerAxis(Hand.kLeft) < 60)
		 * d.runFL(xboxDrive.getTriggerAxis(Hand.kLeft)); if
		 * (xboxDrive.getTriggerAxis(Hand.kLeft) >= 60 &&
		 * xboxDrive.getTriggerAxis(Hand.kRight) < 60)
		 * d.runBL(-xboxDrive.getTriggerAxis(Hand.kLeft)); } else if
		 * (xboxDrive.getXButtonReleased()) { d.runFL(0); } if
		 * (xboxDrive.getYButtonPressed()) { if (xboxDrive.getTriggerAxis(Hand.kRight)
		 * >= 60 && xboxDrive.getTriggerAxis(Hand.kLeft) < 60)
		 * d.runFR(xboxDrive.getTriggerAxis(Hand.kLeft)); if
		 * (xboxDrive.getTriggerAxis(Hand.kLeft) >= 60 &&
		 * xboxDrive.getTriggerAxis(Hand.kRight) < 60)
		 * d.runFR(-xboxDrive.getTriggerAxis(Hand.kLeft)); } else if
		 * (xboxDrive.getYButtonReleased()) { d.runFR(0); } if
		 * (xboxDrive.getBumperPressed(Hand.kLeft)) { if
		 * (xboxDrive.getTriggerAxis(Hand.kRight) >= 60 &&
		 * xboxDrive.getTriggerAxis(Hand.kLeft) < 60)
		 * a.runL(xboxDrive.getTriggerAxis(Hand.kLeft)); if
		 * (xboxDrive.getTriggerAxis(Hand.kLeft) >= 60 &&
		 * xboxDrive.getTriggerAxis(Hand.kRight) < 60)
		 * a.runL(-xboxDrive.getTriggerAxis(Hand.kLeft)); } else if
		 * (xboxDrive.getBumperReleased(Hand.kLeft)) { a.runL(0); } if
		 * (xboxDrive.getBumperPressed(Hand.kRight)) { if
		 * (xboxDrive.getTriggerAxis(Hand.kRight) >= 60 &&
		 * xboxDrive.getTriggerAxis(Hand.kLeft) < 60)
		 * a.runR(xboxDrive.getTriggerAxis(Hand.kLeft)); if
		 * (xboxDrive.getTriggerAxis(Hand.kLeft) >= 60 &&
		 * xboxDrive.getTriggerAxis(Hand.kRight) < 60)
		 * a.runR(-xboxDrive.getTriggerAxis(Hand.kLeft)); } else if
		 * (xboxDrive.getBumperReleased(Hand.kRight)) { a.runR(0); } break; case 1:
		 */
		// Hold LB, RB, LT, or RT to choose piston (bumpers forward, triggers up)
		// press Y to set piston "forward"
		// press B to set piston "reverse"
		// press X to set piston "off"
		/*
		 * if (xboxDrive.getYButtonPressed()) { if
		 * (xboxDrive.getBumperPressed(Hand.kLeft)) a.runPiston(0, 2); else if
		 * (xboxDrive.getBumper(Hand.kRight)) a.runPiston(1, 2); else if
		 * (xboxDrive.getTriggerAxis(Hand.kLeft)>.5) a.runPiston(2, 2); else if
		 * (xboxDrive.getTriggerAxis(Hand.kRight)>.5) a.runPiston(3, 2); } if
		 * (xboxDrive.getBButtonPressed()) { if (xboxDrive.getBumperPressed(Hand.kLeft))
		 * a.runPiston(0, 1); else if (xboxDrive.getBumper(Hand.kRight)) a.runPiston(1,
		 * 1); else if (xboxDrive.getTriggerAxis(Hand.kLeft)>.5) a.runPiston(2, 1); else
		 * if (xboxDrive.getTriggerAxis(Hand.kRight)>.5) a.runPiston(3, 1); } if
		 * (xboxDrive.getAButtonPressed()) { if (xboxDrive.getBumperPressed(Hand.kLeft))
		 * a.runPiston(0, 0); else if (xboxDrive.getBumper(Hand.kRight)) a.runPiston(1,
		 * 0); else if (xboxDrive.getTriggerAxis(Hand.kLeft)>.5) a.runPiston(2, 0); else
		 * if (xboxDrive.getTriggerAxis(Hand.kRight)>.5) a.runPiston(3, 0); } break;
		 * case 2: // Hold LB, RB, LT, or RT to choose piston (bumpers forward, triggers
		 * up) // press Y to set piston "on" // press X to set piston "off" if
		 * (xboxDrive.getYButtonPressed()) { if (xboxDrive.getBumperPressed(Hand.kLeft))
		 * a.runPiston(0, true); else if (xboxDrive.getBumperPressed(Hand.kRight))
		 * a.runPiston(1, true); else if (xboxDrive.getTriggerAxis(Hand.kLeft)>.5)
		 * a.runPiston(2, true); else if (xboxDrive.getTriggerAxis(Hand.kRight)>.5)
		 * a.runPiston(3, true); } if (xboxDrive.getAButtonPressed()) { if
		 * (xboxDrive.getBumperPressed(Hand.kLeft)) a.runPiston(0, false); else if
		 * (xboxDrive.getBumperPressed(Hand.kRight)) a.runPiston(1, false); else if
		 * (xboxDrive.getTriggerAxis(Hand.kLeft)>.5) a.runPiston(2, false); else if
		 * (xboxDrive.getTriggerAxis(Hand.kRight)>.5) a.runPiston(3, false); }
		 * 
		 * break; } if (xboxDrive.getPOV() == Const.povRight && testmode < 2) {
		 * testmode++; testInit(); } if (xboxDrive.getPOV() == Const.povLeft && testmode
		 * > 0) { testmode--; testInit();
		 */

	}

	public void hitScale() {
		System.out.println("scale");
		if (gameData[1] == 'L') {
			step.add(new pathFollower(ConstPaths.longScale, ConstPaths.shortScale,0));
			step2.add(new UseBelt(3, 1.0));
			step.add(new UseCarriage(3, false));
			step2.add(new UseIntake(.5, 3));
			step.add(null);
			step2.add(null);
		} else {
			step.add(new pathFollower(ConstPaths.shortScale, ConstPaths.longScale,0));
			step2.add(new UseBelt(3, 1.0));
			step.add(new UseCarriage(3, false));
			step2.add(new UseIntake(.5, 3));
			step.add(null);
			step2.add(null);
		}
	}

	public void hitSwitch() {
		System.out.println("switch");
		if (gameData[1] == 'L') {
			step.add(new pathFollower(ConstPaths.Outside_Short_Switch, ConstPaths.Inside_Short_Switch,0));
			step2.add(new UseBelt(3, 1.0));
			step.add(new UseCarriage(1, false));
			step2.add(new UseIntake(.5, 3));
			step.add(null);
			step2.add(null);

		} else if (gameData[0] == 'R') {
			step.add(new pathFollower(ConstPaths.Inside_Short_Switch, ConstPaths.Outside_Short_Switch,0));
			step2.add(new UseBelt(3, 1.0));
			step.add(new UseCarriage(1, false));
			step2.add(new UseIntake(.5, 3));
			step.add(null);
			step2.add(null);

		}
	}

	public void hitSwitchLong() {
		System.out.println("switchLong");
		if (gameData[1] == 'L') {
			step.add(new pathFollower(ConstPaths.Outside_long_Switch, ConstPaths.Inside_Long_Switch,0));
			step2.add(new UseBelt(3, 1.0));
			step.add(new UseCarriage(1, false));
			step2.add(new UseIntake(.5, 3));
			step.add(null);
			step2.add(null);

		} else if (gameData[0] == 'R') {

			step.add(new pathFollower(ConstPaths.Inside_Long_Switch, ConstPaths.Outside_long_Switch,0));
			step2.add(new UseBelt(3, 1.0));
			step.add(new UseCarriage(1, false));
			step2.add(new UseIntake(.5, 3));
			step.add(null);
			step2.add(null);

		}

	}

	public void midSwitch() {
		System.out.println("midSwitch");
		if (gameData[0] == 'L') {
			step.add(new pathFollower(ConstPaths.leftSide_leftPathMid, ConstPaths.leftSide_rightPathMid,0));
			step2.add(new UseBelt(3, 1.0));
			step.add(new UseCarriage(1, false));
			step2.add(new UseIntake(.5, 3));
			step.add(null);
			step2.add(null);
		} else if (gameData[0] == 'R') {
			step.add(new pathFollower(ConstPaths.rightSide_leftPathMid, ConstPaths.rightSide_rightPathMid,0));
			step2.add(new UseBelt(3, 1.0));
			step.add(new UseCarriage(1, false));
			step2.add(new UseIntake(.5, 3));
			step.add(null);
			step2.add(null);
		}
	}

}
