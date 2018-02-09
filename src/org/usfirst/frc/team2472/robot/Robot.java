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
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import objects.Action;
import subsystem.arms;
import subsystem.drive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	AnalogInput distSensL = new AnalogInput(Const.dSensL);
	AnalogInput distSensR = new AnalogInput(Const.dSensR);

	drive d = new drive();
	arms a = new arms();

	Joystick joyL = new Joystick(Const.jstickL);
	Joystick joyR = new Joystick(Const.jstickR);
	XboxController xbox = new XboxController(Const.xbox);
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
			step.add(new doNothing());
			step2.add(new doNothing());
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
	public void testPeriodic() {
		// DPad left and right switch test modes
		SmartDashboard.putNumber("BL Motor", d.bL.getMotorOutputPercent());
		SmartDashboard.putNumber("BR Motor", d.bR.getMotorOutputPercent());
		SmartDashboard.putNumber("FL Motor", d.fL.getMotorOutputPercent());
		SmartDashboard.putNumber("FR Motor", d.fR.getMotorOutputPercent());
		SmartDashboard.putNumber("IL Motor", a.intakeL.getMotorOutputPercent());
		SmartDashboard.putNumber("IR Motor", a.intakeR.getMotorOutputPercent());
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
			if (xbox.getAButtonPressed()) {
				if (xbox.getTriggerAxis(Hand.kRight) >= 60 && xbox.getTriggerAxis(Hand.kLeft) < 60)
					d.runBL(xbox.getTriggerAxis(Hand.kLeft));
				if (xbox.getTriggerAxis(Hand.kLeft) >= 60 && xbox.getTriggerAxis(Hand.kRight) < 60)
					d.runBL(-xbox.getTriggerAxis(Hand.kLeft));
			} else if (xbox.getAButtonReleased()) {
				d.runBL(0);
			}
			if (xbox.getBButtonPressed()) {
				if (xbox.getTriggerAxis(Hand.kRight) >= 60 && xbox.getTriggerAxis(Hand.kLeft) < 60)
					d.runBR(xbox.getTriggerAxis(Hand.kLeft));
				if (xbox.getTriggerAxis(Hand.kLeft) >= 60 && xbox.getTriggerAxis(Hand.kRight) < 60)
					d.runBR(-xbox.getTriggerAxis(Hand.kLeft));
			} else if (xbox.getBButtonReleased()) {
				d.runBR(0);
			}
			if (xbox.getXButtonPressed()) {
				if (xbox.getTriggerAxis(Hand.kRight) >= 60 && xbox.getTriggerAxis(Hand.kLeft) < 60)
					d.runFL(xbox.getTriggerAxis(Hand.kLeft));
				if (xbox.getTriggerAxis(Hand.kLeft) >= 60 && xbox.getTriggerAxis(Hand.kRight) < 60)
					d.runBL(-xbox.getTriggerAxis(Hand.kLeft));
			} else if (xbox.getXButtonReleased()) {
				d.runFL(0);
			}
			if (xbox.getYButtonPressed()) {
				if (xbox.getTriggerAxis(Hand.kRight) >= 60 && xbox.getTriggerAxis(Hand.kLeft) < 60)
					d.runFR(xbox.getTriggerAxis(Hand.kLeft));
				if (xbox.getTriggerAxis(Hand.kLeft) >= 60 && xbox.getTriggerAxis(Hand.kRight) < 60)
					d.runFR(-xbox.getTriggerAxis(Hand.kLeft));
			} else if (xbox.getYButtonReleased()) {
				d.runFR(0);
			}
			if (xbox.getBumperPressed(Hand.kLeft)) {
				if (xbox.getTriggerAxis(Hand.kRight) >= 60 && xbox.getTriggerAxis(Hand.kLeft) < 60)
					a.runL(xbox.getTriggerAxis(Hand.kLeft));
				if (xbox.getTriggerAxis(Hand.kLeft) >= 60 && xbox.getTriggerAxis(Hand.kRight) < 60)
					a.runL(-xbox.getTriggerAxis(Hand.kLeft));
			} else if (xbox.getBumperReleased(Hand.kLeft)) {
				a.runL(0);
			}
			if (xbox.getBumperPressed(Hand.kRight)) {
				if (xbox.getTriggerAxis(Hand.kRight) >= 60 && xbox.getTriggerAxis(Hand.kLeft) < 60)
					a.runR(xbox.getTriggerAxis(Hand.kLeft));
				if (xbox.getTriggerAxis(Hand.kLeft) >= 60 && xbox.getTriggerAxis(Hand.kRight) < 60)
					a.runR(-xbox.getTriggerAxis(Hand.kLeft));
			} else if (xbox.getBumperReleased(Hand.kRight)) {
				a.runR(0);
			}
			break;
		case 1:
			// Hold LB or RB to choose which piston
			// press Y to set piston "forward"
			// press B to set piston "reverse"
			// press X to set piston "off"
			if (xbox.getYButtonPressed()) {
				if (xbox.getBumperPressed(Hand.kLeft))
					a.runPiston(false, 2);
				else if (xbox.getBumper(Hand.kRight))
					a.runPiston(true, 2);
			}
			if (xbox.getBButtonPressed()) {
				if (xbox.getBumperPressed(Hand.kLeft))
					a.runPiston(false, 1);
				else if (xbox.getBumper(Hand.kRight))
					a.runPiston(true, 1);
			}
			if (xbox.getAButtonPressed()) {
				if (xbox.getBumperPressed(Hand.kLeft))
					a.runPiston(false, 0);
				else if (xbox.getBumper(Hand.kRight))
					a.runPiston(true, 0);
			}
			break;
		case 2:
			// Hold LB or RB to choose which piston
			// press Y to set piston "on"
			// press X to set piston "off"
			if (xbox.getYButtonPressed()) {
				if (xbox.getBumperPressed(Hand.kLeft))
					a.runPiston(false, true);
				else if (xbox.getBumperPressed(Hand.kRight))
					a.runPiston(true, true);
			}
			if (xbox.getAButtonPressed()) {
				if (xbox.getBumperPressed(Hand.kLeft))
					a.runPiston(false, false);
				else if (xbox.getBumperPressed(Hand.kRight))
					a.runPiston(true, false);
			}

			break;
		}
		if (xbox.getPOV() == Const.povRight && testmode < 2) {
			testmode++;
			testInit();
		}
		if (xbox.getPOV() == Const.povLeft && testmode > 0) {
			testmode--;
			testInit();
		}

	}
}
