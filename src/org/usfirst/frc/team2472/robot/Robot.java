/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2472.robot;

import java.util.ArrayList;

import com.kauailabs.nav6.frc.IMUAdvanced;

import constants.Const;
import edu.wpi.first.wpilibj.AnalogInput;
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
	XboxController xbox = new XboxController(Const.xbox);// did you know this existed? who knew? nice to know!!!!!
	Joystick box = new Joystick(Const.box);

	IMUAdvanced imu = new IMUAdvanced(Const.imuPort);

	ArrayList<Action> step = new ArrayList<Action>();
	ArrayList<Action> step2 = new ArrayList<Action>();
	int nAction = 0;

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
		d.tankDrive(joyL, joyR);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {

		// talon speeds are set by the analog triggers (LT/RT)
		// RT should move forward, LT should move backward
		if (xbox.getAButtonPressed()) {// A button should run BackLeft motor
			if (xbox.getTriggerAxis(Hand.kRight) >= 60 && xbox.getTriggerAxis(Hand.kLeft) < 60)
				d.runBL(xbox.getTriggerAxis(Hand.kLeft));
			if (xbox.getTriggerAxis(Hand.kLeft) >= 60 && xbox.getTriggerAxis(Hand.kRight) < 60)
				d.runBL(-xbox.getTriggerAxis(Hand.kLeft));
		} else if(xbox.getAButtonReleased()) {
			d.runBL(0);
		}
		if (xbox.getBButtonPressed()) {// B button should run BackRight motor
			if (xbox.getTriggerAxis(Hand.kRight) >= 60 && xbox.getTriggerAxis(Hand.kLeft) < 60)
				d.runBR(xbox.getTriggerAxis(Hand.kLeft));
			if (xbox.getTriggerAxis(Hand.kLeft) >= 60 && xbox.getTriggerAxis(Hand.kRight) < 60)
				d.runBR(-xbox.getTriggerAxis(Hand.kLeft));
		} else if(xbox.getBButtonReleased()) {
			d.runBR(0);
		}
		if (xbox.getXButtonPressed()) {// X button should run FrontLeft motor
			if (xbox.getTriggerAxis(Hand.kRight) >= 60 && xbox.getTriggerAxis(Hand.kLeft) < 60)
				d.runFL(xbox.getTriggerAxis(Hand.kLeft));
			if (xbox.getTriggerAxis(Hand.kLeft) >= 60 && xbox.getTriggerAxis(Hand.kRight) < 60)
				d.runBL(-xbox.getTriggerAxis(Hand.kLeft));
		} else if(xbox.getXButtonReleased()) {
			d.runFL(0);
		}
		if (xbox.getYButtonPressed()) {// Y button should run FrontRight motor
			if (xbox.getTriggerAxis(Hand.kRight) >= 60 && xbox.getTriggerAxis(Hand.kLeft) < 60)
				d.runFR(xbox.getTriggerAxis(Hand.kLeft));
			if (xbox.getTriggerAxis(Hand.kLeft) >= 60 && xbox.getTriggerAxis(Hand.kRight) < 60)
				d.runFR(-xbox.getTriggerAxis(Hand.kLeft));
		} else if(xbox.getYButtonReleased()) {
			d.runFR(0);
		}
		if (xbox.getBumperPressed(Hand.kLeft)) {// Left Bumper should run Left arm intake
			if (xbox.getTriggerAxis(Hand.kRight) >= 60 && xbox.getTriggerAxis(Hand.kLeft) < 60)
				a.runL(xbox.getTriggerAxis(Hand.kLeft));
			if (xbox.getTriggerAxis(Hand.kLeft) >= 60 && xbox.getTriggerAxis(Hand.kRight) < 60)
				a.runL(-xbox.getTriggerAxis(Hand.kLeft));
		} else if(xbox.getBumperReleased(Hand.kLeft)) {
			a.runL(0);
		}
		if (xbox.getBumperPressed(Hand.kRight)) {// Right Bumper should run Right arm intake
			if (xbox.getTriggerAxis(Hand.kRight) >= 60 && xbox.getTriggerAxis(Hand.kLeft) < 60)
				a.runR(xbox.getTriggerAxis(Hand.kLeft));
			if (xbox.getTriggerAxis(Hand.kLeft) >= 60 && xbox.getTriggerAxis(Hand.kRight) < 60)
				a.runR(-xbox.getTriggerAxis(Hand.kLeft));
		} else if(xbox.getBumperReleased(Hand.kRight)) {
			a.runR(0);
		}
		if(xbox.getPOV()==Const.povUp) {
			a.grab();
		}

	}
}
