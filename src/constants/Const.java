package constants;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class Const {
	//distance sensor values
	public static int dSensL = 0;
	public static int dSensR = 0;
	//motors
	public static int motorBL = 0;
	public static int motorBR = 1;
	public static int motorFL = 2;
	public static int motorFR = 3;
	//arms
	public static int arm1 = 4;
	public static int arm2 = 5;
	//lift
	public static int lift = 6;
	//joysticcs
	public static int jstickL = 0;
	public static int jstickR = 0;
	public static int gpad = 0;
	public static int box = 0;
	//joysticc functions
	public static double ledzone = .1;//deadzone of left thing (decimal from 0 to 1)
	public static double redzone = .1;//deadone of right thing
	public static double edge = 1;//outer ring
	//IMU serial port
	public static SerialPort imuPort = new SerialPort(57600,Port.kUSB2);
}
