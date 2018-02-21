package constants;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class Const {
	public static double DeadZone=.0003;
	//distance sensors -- finalize these
	public static int dSense = 0;
	public static int dSensR = 0;
	//Talons
	public static int motorBL = 0;
	public static int motorBR = 1;
	public static int motorFL = 2;
	public static int motorFR = 3;
	public static int armLIntake = 4;
	public static int armRIntake = 5;
	public static int lift = 6;
	//buttons
	public static final int buttonA = 1;
	public static final int buttonB = 2;
	public static final int buttonX = 3;
	public static final int buttonY = 4;
	public static final int buttonL = 5;
	public static final int buttonR = 6;
	public static final int buttonBack = 7;
	public static final int buttonStart = 8;
	//pneumatic pistons -- finalize these
	public static int armLFwd = 2;
	public static int armLRev = 3;
	public static int carriage1Out =4;
	public static int carriage1In =5;
	public static int firstLiftUP = 1;
	public static int firstLiftdown = 0;

	
	
	public static int armLUpFwd = 0;
	public static int armLUpRev = 0;
	public static int armRUpFwd = 0;
	public static int armRUpRev = 0;
	public static int armL = 0;
	public static int armR = 0;
	//joysticcs -- finalize these
	public static int jstickL = 0;//left flight stick
	public static int jstickR = 0;//right flight stick
	public static int xbox = 0;//xbox controller
	public static int xboxManipulator=1;
	public static int box = 2;//autonomous control box
	//joysticc POVs
	
	//joysticc limits
	public static double ledzone = .1;//deadzone of left thing (decimal from 0 to 1)
	public static double redzone = .1;//deadone of right thing
	public static double edge = 1;//outer ring
	//IMU serial port
//	public static SerialPort imuPort = new SerialPort(57600,Port.kUSB2);
}
