package subsystem;

import constants.Const;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class carriage {

	public boolean out;
	public boolean mid;
	public long time=0;
	public boolean in;
	DoubleSolenoid Carriage1 =new DoubleSolenoid(Const.carriage1Out,Const.carriage1In);
	//DoubleSolenoid Carriage2 =new DoubleSolenoid(Const.carriage2Out,Const.carriage2In);
	public void Out() {
		time=System.currentTimeMillis();
		in=false;
		Carriage1.set(DoubleSolenoid.Value.kForward);
	
	//	Carriage2.set(DoubleSolenoid.Value.kForward);
	}
	public void In() {
		time=System.currentTimeMillis();
		in=true;
		Carriage1.set(DoubleSolenoid.Value.kReverse);
	
	//	Carriage2.set(DoubleSolenoid.Value.kReverse);
	}
	public void middle() {
		out=false;
		mid=true;
		in=false;
		Carriage1.set(DoubleSolenoid.Value.kReverse);
	
	}

}
