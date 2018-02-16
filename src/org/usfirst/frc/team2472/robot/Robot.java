/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2472.robot;

import java.util.ArrayList;

import com.kauailabs.nav6.frc.IMUAdvanced;

import autoActions.doNothing;
import constants.Const;
import edu.wpi.first.wpilibj.AnalogInput;
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
	Compressor compress=new Compressor(0);
	AnalogInput distSensL = new AnalogInput(Const.dSensL);
	AnalogInput distSensR = new AnalogInput(Const.dSensR);
	Joystick xbox =new Joystick(Const.xboxManipulator);
	public static drive d = new drive();
	public static arms a = new arms();
	public static carriage c =new carriage();
	public static FirstLift First=new FirstLift();
	public static SecondLift Second=new SecondLift();
	public static climber climb=new climber();
	

	Joystick joyL = new Joystick(Const.jstickL);
	Joystick joyR = new Joystick(Const.jstickR);
	XboxController xboxDrive = new XboxController(Const.xbox);
	Joystick box = new Joystick(Const.box);

	IMUAdvanced imu = new IMUAdvanced(Const.imuPort);

	ArrayList<Action> step = new ArrayList<Action>();
	ArrayList<Action> step2 = new ArrayList<Action>();
	int nAction = 0;

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
		compress.setClosedLoopControl(true);
		imu.zeroYaw();
		// smartdashboard shtuff
		SmartDashboard.putNumber("A. Number", .1);
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
		gameData = DriverStation.getInstance().getGameSpecificMessage().toCharArray();
		for (int i = 0; i < gameData.length; i++)
			automode[i] = (gameData[i] == 'R');
		if (box.getRawButton(1)) {
			step.add(new doNothing(15));
			step2.add(new doNothing(15));
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


		d.tankDrive(xbox);
	}

	@Override
	public void testInit() {
		testmode = 0;
		testStrings = new String[] { "Talon test mode", "DoubleSolenoid test mode", "Solenoid test mode" };
		System.out.println(testStrings[testmode]);
	}

	@Override
	/*
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
			testInit();
		}

	}
	*/
}
