package subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import constants.Const;
public class SecondLift {
	TalonSRX liftMotor=new TalonSRX(Const.lift);

public void lift(double speed) 
{

liftMotor.set(ControlMode.PercentOutput,speed);

}
	}