package org.usfirst.frc.team614.robot.subsystems;

import org.team708.robot.util.Math708;
import org.usfirst.frc.team614.robot.Constants;
import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.RobotMap;
import org.usfirst.frc.team614.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team614.robot.RobotDrive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
  
 */
public class Drivetrain extends PIDSubsystem {
    
	private boolean usePID = true;
	
	//Variables specific for drivetrain PID loop
	private double moveSpeed = 0.0;
	private double pidOutput = 0.0;
	
	
	
	private VictorSP leftMotor,rightMotor; //for use when PWMS split into 2
	
	
	private Encoder leftGeartrainEncoder, rightGeartrainEncoder;
	
	public static final int LEncoder = 0;
	public static final int REncoder = 1;
	
	public double distancePerPulse;
	
	public Solenoid Piston;
	
	private AnalogGyro Gyro;
	
	private RobotDrive drivetrain;											// FRC provided drivetrain class
	
	
	public Drivetrain(){
		
		super("Drivetrain", Constants.Kp, Constants.Ki, Constants.Kd);
			
		//Initializes the motors
	
    	 //  FOR USE WHEN PWMS SPLIT INTO 2
 		leftMotor = new VictorSP(RobotMap.leftGeartrainMotor);
 		rightMotor = new VictorSP(RobotMap.rightGeartrainMotor);
 		
 		
    	 //Initializes the solenoid
    	 Piston = new Solenoid(RobotMap.solenoid);
    	// Piston2 = new Solenoid(RobotMap.solenoid2_A, RobotMap.solenoid2_B);
    	
		//Initializes the Encoders
    
    	 
    	 leftGeartrainEncoder = new Encoder(RobotMap.leftGeartrainEncoder_A, RobotMap.leftGeartrainEncoder_B);
    	 rightGeartrainEncoder = new Encoder(RobotMap.rightGeartrainEncoder_A, RobotMap.rightGeartrainEncoder_B);
    	 
    	 resetEncoders();
		
    	 distancePerPulse = (Constants.DRIVETRAIN_WHEEL_DIAMETER * Math.PI) / Constants.DRIVETRAIN_ENCODER_PULSES_PER_REV;
     	 leftGeartrainEncoder.setDistancePerPulse(distancePerPulse);
     	 rightGeartrainEncoder.setDistancePerPulse(distancePerPulse);
    	 
    	 //Initializes gyro
		 Gyro = new AnalogGyro(RobotMap.gyro_ID);
		 Gyro.reset();
	
		
		 //Initializes drivetrain class
		 
		 //drivetrain = new RobotDrive(frontLeftMotor, frontRightMotor, rearLeftMotor, rearRightMotor, midLeftMotor, midRightMotor);
		 drivetrain = new RobotDrive(leftMotor, rightMotor);		// Initializes drivetrain class; for use when PWMS split into 2
		
		 setInputRange(-25.0, 25.0);
		 setAbsoluteTolerance(Constants.pid_tolerance);
		 setSetpoint(0.0);
	
		 disable();
//		 drivetrain.setSafetyEnabled(false);
//		 leftMotor.setSafetyEnabled(false);
//		 rightMotor.setSafetyEnabled(false);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new JoystickDrive());
    
    }
    

    /*
     * Motor Methods
     */
    public void arcadeDriveMode(double move, double rotate, boolean usePID){
  
    
    	move = move * Constants.DRIVE_MOTOR_MAX_SPEED;
    	rotate = rotate * Constants.ROTATE_MOTOR_MAX_SPEED;
    	
    	if(usePID){
    		if(rotate == 0.0 && move != 0.0) {
    			
    			if(!getPIDController().isEnabled()){
    				getPIDController().setPID(Constants.Kp, Constants.Ki, Constants.Kd);
    				getPIDController().reset();
    				Gyro.reset();
    				enable();
    				Gyro.reset();
    			}
    			//Set the forward move speed to the move parameter
    			moveSpeed = move;
    		
    		} else {
    			//Disables the PID controller if it is enabled so the drivetrain can move freely
    			if(getPIDController().isEnabled()) {
    				getPIDController().reset();
    			}
    			drivetrain.arcadeDrive(move, rotate);
    		}
    	} else {
    		//Disables the PID Controller if it is enabled so the drivetrain can move freely
    		if(getPIDController().isEnabled())
    		getPIDController().reset();
    	}
    	
    	/**	
    	// Simplified IF statement. If leftValue is in the deadband range(-JoystickDeadband, JoystickDeadband), it returns 0
    	leftValue = (leftValue < RobotMap.JOYSTICK_DEADBAND && leftValue > -RobotMap.JOYSTICK_DEADBAND ? 0 : leftValue);
    	
    	// Simplified IF statement. If rightValue is in the deadband range(-JoystickDeadband, JoystickDeadband), it returns 0
    	rightValue = (rightValue < RobotMap.JOYSTICK_DEADBAND && rightValue > -RobotMap.JOYSTICK_DEADBAND ? 0 : rightValue);
    	
    	//System.out.println("Tank Drive: " + leftValue + ", " + rightValue);
    */
    	drivetrain.arcadeDrive(move, rotate);
    }
    
    public void stop(){
    	drivetrain.arcadeDrive(0,0);
    }
    
    
    /*
     * Solenoid Methods
     */
    public void extend(){
    	Piston.set(true);	
    	//Piston2.set(true);
    }
    
    public void retract(){
    	Piston.set(false);	
    	//Piston2.set(false);
    }
    
    public void togglePiston(){
    	Piston.set(!Piston.get());	
    }
    
  
    
    /*
     * Encoder Methods
     */
    
   
    public double getEncoderRPM(int encoderNum){
    	switch(encoderNum){
    		case LEncoder: //if = 0
    			return leftGeartrainEncoder.getRate();
    		case REncoder: //if = 1
    			return rightGeartrainEncoder.getRate();
    		default:
    		return 0.0;
    	}
    }
    
    public double getEncoderDistance(int encoderNum){
    	switch(encoderNum){
    	case LEncoder: //if = 0
    		return leftGeartrainEncoder.getDistance();
    	case REncoder: //if = 1
    		return -rightGeartrainEncoder.getDistance();
    	default:
    		return 0.0;
    	}
    }
    

    
    public boolean getEncoderDirection(int encoderNum){
    	switch(encoderNum){
    	case LEncoder: //if = 0
    		return leftGeartrainEncoder.getDirection();
    	case REncoder: //if = 1
    		return rightGeartrainEncoder.getDirection();
    	default:
    		return false;
    	}
    }
    public void resetEncoders(){
    	leftGeartrainEncoder.reset();
    	rightGeartrainEncoder.reset();
    }
    
    
   
    
    /*
     * Gyro Methods
     */
    public double getAngle(){
    	return Gyro.getAngle();
    }
    
    public void resetGyro(){
    	Gyro.reset();
    }
    
  public double rotateByGyro(double targetAngle, double tolerance) {
	double difference = getAngle() - targetAngle;
	
//	if (Math708.isWithinThreshold(getIRDistance(), targetAngle, tolerance)) {
//		difference = 0.0;
//	}
	
	return difference / targetAngle;
}
    
    
    /* PID Methods
     * 
     */
    
    public double returnPIDInput(){
    	return Gyro.getAngle();
    }
    
    public boolean getUsePID() {
    	return usePID;
    }
    
    public void setUsePID(boolean usePID){
    	this.usePID = usePID;
    }
    
    public void usePIDOutput(double output){
    	pidOutput = output;
    	drivetrain.arcadeDrive(moveSpeed, -output);
    }
    
    public void togglePID(){
    	usePID = !usePID;
    }
    
    

    
    
    
    
    
    /* For Logging the Encoder and Gyro Values to the SmartDashboard */
    public void sendToDashboard(){
    	
    	SmartDashboard.putNumber("Left Geartrain Encoder Distance: ", Robot.drivetrain.getEncoderDistance(LEncoder));
    	SmartDashboard.putString("Left Geartrain Encoder Direction: ", (Robot.drivetrain.getEncoderDirection(LEncoder) ? "Clockwise" : "Counter-Clockwise"));
    	SmartDashboard.putNumber("Left Geartrain Encoder RPM: ", Robot.drivetrain.getEncoderRPM(LEncoder));
    	
    	SmartDashboard.putNumber("Right Geartrain Encoder Distance: ", Robot.drivetrain.getEncoderDistance(REncoder));
    	SmartDashboard.putString("Right Geartrain Encoder Direction: ", (Robot.drivetrain.getEncoderDirection(REncoder) ? "Clockwise" : "Counter-Clockwise"));
    	SmartDashboard.putNumber("Right Geartrain Encoder RPM: ", Robot.drivetrain.getEncoderRPM(REncoder));
    	
    	SmartDashboard.putNumber("Left Geartrain Encoder RAW: ",  Robot.drivetrain.leftGeartrainEncoder.get());
    	SmartDashboard.putNumber("Left and Right Encoder Difference", Math.abs(Robot.drivetrain.getEncoderRPM(LEncoder) - Robot.drivetrain.getEncoderRPM(REncoder)));
    	SmartDashboard.putNumber("Gyro Angle: ", Robot.drivetrain.getAngle());
    	
    	
    	
    	SmartDashboard.putBoolean("Drivetrain PID", getUsePID());
    	
    	SmartDashboard.putString("TEST", "SKDJFBSJDKFB");
   
    }
	
		
}
  
    
    
    
    
   


