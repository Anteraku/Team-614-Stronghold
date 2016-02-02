package org.usfirst.frc.team614.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import org.usfirst.frc.team614.robot.RobotMap;
import java.awt.Robot;



public class BestWinchEU extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Victor WinchVictor;
	private Encoder WinchEncoder;
	
	private double MOTOR_SPEED = .625;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	setDefaultCommand(new AssignWinchSpeed());
    	
    }
    
    public Winch()
    {
    	WinchVictor = new Victor(RobotMap.WINCH_MOTOR);
    	WinchEncoder = new Encoder(RobotMap.WINCH_ENCODER_A, RobotMap.WINCH_ENCODER_B, false, EncodingType.k4X);
    }
    
    public void startMotor()
    {
    	WinchVictor.set(MOTOR_SPEED);
    }
    
    public void reverseMotor()
    {
    	WinchVictor.set(-MOTOR_SPEED);
    }
    
    public void setMotorSpeed(double speed)
    {
    	WinchVictor.set(speed);
    }
    
    public void stopMotor()
    {
    	WinchVictor.set(0.0);
    }
    
    public void resetEncoder()
    {
    	WinchEncoder.reset();
    }
    
    public double getEncoderDistance()
    {
    	return WinchEncoder.getDistance();
    }
    
    public boolean getEncoderDirection()
    {
    	return WinchEncoder.getDirection();
    }
    
    public void logEncoderData()
    {
    	SmartDashboard.putNumber("Witch Encoder Distance:", Robot.Winch.getEncoderDistance());
    	SmartDashboard.putString("Witch Encoder Direction:", (Robot.Winch.getEncoderDirection() ? "Clockwise" : "Counter-Clockwise"));
    }
    
    public void speedUpMotor()
    {
    	WinchVictor.set();
    }
    
}

