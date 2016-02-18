package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.Constants;
import org.usfirst.frc.team614.robot.RobotMap;
import org.usfirst.frc.team614.robot.commands.JoystickDrive;
import org.usfirst.frc.team614.robot.commands.shooter.FlywheelDrive;
import org.usfirst.frc.team614.robot.commands.shooter.LiftDrive;
import org.usfirst.frc.team614.robot.commands.shooter.ShootSequence;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends PIDSubsystem {
    
	private VictorSP leftMotor, rightMotor, angleMotor;
	private Servo servo;
	private Encoder leftEncoder, rightEncoder, angleEncoder;
	private double distancePerPulse;
	private RobotDrive flywheelDrive;
	
	private double moveSpeed = 0.0;
	private double pidOutput = 0.0;
	
	private AnalogGyro Gyro;
	
	private boolean usePID = true;
	
	public Shooter() {
		
		super("Shooter", Constants.Kp, Constants.Ki, Constants.Kd);
		//Initializes the motors
		leftMotor = new VictorSP(RobotMap.shooterLeftMotor);
		rightMotor = new VictorSP(RobotMap.shooterRightMotor);
		angleMotor = new VictorSP(RobotMap.shooterAngleMotor);
	
		//Initializes the encoders
		leftEncoder = new Encoder(RobotMap.leftShooterEncoder_A, RobotMap.leftShooterEncoder_B);
		rightEncoder = new Encoder(RobotMap.rightShooterEncoder_A, RobotMap.rightShooterEncoder_B);
		angleEncoder = new Encoder(RobotMap.angleShooterEncoder_A, RobotMap.angleShooterEncoder_B);
		
		resetEncoders();
		
		distancePerPulse = 10; //change to whatever the rate is when the encoder comes in
		leftEncoder.setDistancePerPulse(distancePerPulse);
		rightEncoder.setDistancePerPulse(distancePerPulse);
		angleEncoder.setDistancePerPulse(distancePerPulse);
		
		
		//Initialize the servos
		servo = new Servo(RobotMap.servo_ID);
		
		
		//Initializes drivetrain class for the two flywheels
		flywheelDrive = new RobotDrive(leftMotor, rightMotor);
		
		
	
	}
	
	  public void initDefaultCommand() {
	        // Set the default command for a subsystem here.
	    	setDefaultCommand(new FlywheelDrive());
	    	//setDefaultCommand(new ShootSequence());
	    
	    }
	    
	/*
	 * Motor Methods
	 */
	
	/**
	 * Spins up the shooter flywheel to shoot out
	 */
	  
	  public void shootMode(double value){
		  
		  value = value * Constants.DRIVE_MOTOR_MAX_SPEED;
	    	
	    	if(usePID){
	    		//Disables the PID controller if it is enabled so the drivetrain can move freely
	    		if(getPIDController().isEnable()) {
	    			getPIDController().reset();
	    		}
	    		
	    		flywheelDrive.arcadeDrive(value, value);
	    		
	    		//Disables the PID Controller if it is enables so the drivetrain can move freely
	    		if(getPIDController().isEnable())
	    		getPIDController().reset();
	    	}
	    	
		  flywheelDrive.tankDrive(value, value);
	  }
	  
	  
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
	
	/**
	 * Moves the shooter up to shoot higher
	 */
	
	public void stopFlywheel(){
		leftMotor.set(0);
		rightMotor.set(0);
	}
	
	
	/*
	 * Servo Methods
	 */
	
	/**
	 * Moves the servo to hit the ball into the flywheel
	 */
	public void flickBall(){
		servo.set(Constants.SERVO_ANGLE);
	}
	
	public void resetServo(){
		servo.set(0);
	}
	
	
	/*
	 * AngleMotor Methods
	 */
	 
	 public void setMotorSpeed(double motorSpeed){
		 angleMotor.set(motorSpeed* Constants.ANGLE_REDUCTION_SPEED);
	 }
	 
	 /*
	 * Moves the shooter up to shoot higher
	 */
	public void angleUp(){
		angleMotor.set(Constants.MOTOR_UP);
	}
		
	/*
	 * Moves the shooter down to shoot lower
	 */	
	public void angleDown(){
		angleMotor.set(Constants.MOTOR_DOWN);
	}
	
	public void stopAngle(){
		angleMotor.set(0);
	}
	
	/*
	 * Encoder Methods
	 */
	
	
	
	public double getLeftEncoderRPM(){
		return leftEncoder.getRate();
	}
	
	public double getRightEncoderRPM(){
		return rightEncoder.getRate();
	}
	
	public double getAngleEncoderRPM(){
		return angleEncoder.getRate();
	}
	
	public void resetFlywheelEncoders(){
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public void resetAngleEncoder(){
		angleEncoder.reset();
	}
	
	public void resetEncoders(){
		leftEncoder.reset();
		rightEncoder.reset();
		angleEncoder.reset();
	}
	
	
	 /* PID Methods
     * 
     */
    
    public double returnPIDInput(){
    	return 0;
    }
   
    
    public boolean getUsePID() {
    	return usePID;
    }
    
    public void setUsePID(boolean usePID){
    	this.usePID = usePID;
    }
    
    public void usePIDOutput(double output){
    	pidOutput = output;
    	flywheelDrive.arcadeDrive(moveSpeed, -output);
    }
	
	
	public void sendToDashboard(){
		SmartDashboard.putNumber("Left Flywheel RPM: ", getLeftEncoderRPM());
		SmartDashboard.putNumber("Right Flywheel RPM: ", getRightEncoderRPM());
		SmartDashboard.putNumber("Lift RPM: ", getAngleEncoderRPM());
	}



 
}

