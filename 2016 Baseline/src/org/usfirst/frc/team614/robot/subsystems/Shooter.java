package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.Constants;
import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private VictorSP leftMotor, rightMotor;
	private Servo servo;
	private Encoder leftEncoder, rightEncoder;
	private double distancePerPulse;
	
	public Shooter() {
		//Initializes the motors
		leftMotor = new VictorSP(RobotMap.shooterLeftMotor);
		rightMotor = new VictorSP(RobotMap.shooterRightMotor);
	
		//Initializes the encoders
		leftEncoder = new Encoder(RobotMap.leftShooterEncoder_A, RobotMap.leftShooterEncoder_B);
		rightEncoder = new Encoder(RobotMap.rightShooterEncoder_A, RobotMap.rightShooterEncoder_B);
		distancePerPulse = 10; //change to whatever the rate is when the encoder comes in
		leftEncoder.setDistancePerPulse(distancePerPulse);
		rightEncoder.setDistancePerPulse(distancePerPulse);
		leftEncoder.reset();
		rightEncoder.reset();
		
		
		//Initialize the servos
		servo = new Servo(RobotMap.servo_ID);
		
	}
	
	/**
	 * Spins up the shooter flywheel to shoot out
	 */
	public void revUpForward(){
		leftMotor.set(Constants.MOTOR_FORWARD);
		rightMotor.set(-Constants.MOTOR_FORWARD);
	}
	/**
	 * Spins up the shooter flywheel to bring in the ball
	 */
	public void revUpReverse(){
		leftMotor.set(Constants.MOTOR_REVERSE);
		rightMotor.set(-Constants.MOTOR_REVERSE);
	
	}
	public void stop(){
		leftMotor.set(0);
		rightMotor.set(0);
	}
	
	/**
	 * Moves the servo to hit the ball into the flywheel
	 */
	public void flickBall(){
		servo.set(Constants.SERVO_ANGLE);
	}
	
	public void resetServo(){
		servo.set(0);
	}
	
	public double getLeftEncoderRPM(){
		return leftEncoder.getRate();
	}
	
	public double getRightEncoderRPM(){
		return rightEncoder.getRate();
	}
	
	public void resetEncoder(){
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public void returnServo(){
		servo.set(0);
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

