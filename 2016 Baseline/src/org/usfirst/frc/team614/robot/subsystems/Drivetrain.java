package org.usfirst.frc.team614.robot.subsystems;

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
	
	
	//private VictorSP frontLeftMotor, frontRightMotor, rearLeftMotor, rearRightMotor, midLeftMotor, midRightMotor; 
	private VictorSP leftMotor, rightMotor; //for use when PWMS split into 2
	
	//private Encoder frontLeftEncoder, frontRightEncoder, rearLeftEncoder, rearRightEncoder, midLeftEncoder, midRightEncoder;
	private Encoder leftGeartrainEncoder, rightGeartrainEncoder;
	//private static final int FLEncoder = 0, FREncoder = 1, RLEncoder = 2, RREncoder = 3, MLEncoder = 4, MREncoder = 5;
	private static final int LEncoder = 0, REncoder = 1;
	
	private Solenoid Piston;
	
	private AnalogGyro Gyro;
	
	private RobotDrive drivetrain;											// FRC provided drivetrain class
	
	private double previousRPM = 0.0;
	public Drivetrain(){
		
		super("Drivetrain", Constants.Kp, Constants.Ki, Constants.Kd);
	
		//Initializes the motors
		/**
		 frontLeftMotor = new VictorSP(RobotMap.drivetrainFrontLeftMotor);
    	 rearLeftMotor = new VictorSP(RobotMap.drivetrainRearLeftMotor);
    	 frontRightMotor = new VictorSP(RobotMap.drivetrainFrontRightMotor);
    	 rearRightMotor = new VictorSP(RobotMap.drivetrainRearRightMotor);
    	 midLeftMotor = new VictorSP(RobotMap.drivetrainMidLeftMotor);
    	 midRightMotor = new VictorSP(RobotMap.drivetrainMidRightMotor);
    	 */
    	 //  FOR USE WHEN PWMS SPLIT INTO 2
 		leftMotor = new VictorSP(RobotMap.leftGeartrainMotor);
 		rightMotor = new VictorSP(RobotMap.rightGeartrainMotor);
 		
    	 //Initializes the solenoid
    	 Piston = new Solenoid(RobotMap.solenoid_A, RobotMap.solenoid_B);
    	 
		//Initializes the Encoders
    	 /**
    	 frontLeftEncoder = new Encoder(RobotMap.frontLeftEncoder_A, RobotMap.frontLeftEncoder_B);
    	 frontRightEncoder = new Encoder(RobotMap.frontRightEncoder_A, RobotMap.frontRightEncoder_B);
    	 rearLeftEncoder = new Encoder(RobotMap.rearLeftEncoder_A, RobotMap.rearLeftEncoder_B);
    	 rearRightEncoder = new Encoder(RobotMap.rearRightEncoder_A, RobotMap.rearRightEncoder_B);
    	 midLeftEncoder = new Encoder(RobotMap.midLeftEncoder_A, RobotMap.midLeftEncoder_B);
		 midRightEncoder = new Encoder(RobotMap.midRightEncoder_A, RobotMap.midRightEncoder_B);	
	*/
    	 
    	 leftGeartrainEncoder = new Encoder(RobotMap.leftGeartrainEncoder_A, RobotMap.leftGeartrainEncoder_B);
    	 rightGeartrainEncoder = new Encoder(RobotMap.rightGeartrainEncoder_A, RobotMap.rightGeartrainEncoder_B);
    	 
    	 resetEncoders();
		//Initializes gyro
		 Gyro = new AnalogGyro(RobotMap.gyro_ID);
		 Gyro.reset();
		
		 //Initializes drivetrain class
		 //drivetrain = new RobotDrive(frontLeftMotor, frontRightMotor, rearLeftMotor, rearRightMotor, midLeftMotor, midRightMotor);
		 drivetrain = new RobotDrive(leftMotor, rightMotor);		// Initializes drivetrain class; for use when PWMS split into 2
		
	
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
    }
    
    public void retract(){
    	Piston.set(false);	
    }
    
    
    /*
     * Encoder Methods
     */
    
    /**
    public double getFrontLeftEncoderRPM(){
    	return frontLeftEncoder.getRate();
    }
    
    public double getFrontRightEncoderRPM(){
    	return frontRightEncoder.getRate();
    }
    
    public double getRearLeftEncoderRPM(){
    	return rearLeftEncoder.getRate();
    }
    
    public double getRearRightEncoderRPM(){
    	return rearRightEncoder.getRate();
    }
    
    public double getMidLeftEncoderRPM(){
    	return midLeftEncoder.getRate();
    }
    
    public double getMidRightEncoderRPM(){
    	return midRightEncoder.getRate();
    }
    
    public double getFrontLeftEncoderDistance(){
    	return frontLeftEncoder.getDistance();
    }
    
    public double getFrontRightEncoderDistance(){
    	return frontRightEncoder.getDistance();
    }
    
    public double getRearLeftEncoderDistance(){
    	return rearLeftEncoder.getDistance();
    }
    
    public double getRearRightEncoderDistance(){
    	return rearRightEncoder.getDistance();
    }
    
    public double getMidLeftEncoderDistance(){
    	return midLeftEncoder.getDistance();
    }
    
    public double getMidRightEncoderDistance(){
    	return midRightEncoder.getDistance();
    }
   
    public double getEncoderDistance(int encoderNum){ 
    	switch(encoderNum){
    		case FLEncoder: //if = 0
    			return frontLeftEncoder.getDistance();
    		case FREncoder: //if = 1
    			return frontRightEncoder.getDistance();
    		case RLEncoder: //if = 2
    			return rearLeftEncoder.getDistance();
    		case RREncoder: //if = 3
    			return rearRightEncoder.getDistance();
    		case MLEncoder: //if = 4
    			return midLeftEncoder.getDistance();
    		case MREncoder: //if = 5
    			return midRightEncoder.getDistance();
    		default:
    			return 0.0;
    	}
    }
    
   
    public double getEncoderRPM(int encoderNum){ 
    	switch(encoderNum){
    		case FLEncoder: //if = 0
    			return frontLeftEncoder.getRate();
    		case FREncoder: //if = 1
    			return frontRightEncoder.getRate();
    		case RLEncoder: //if = 2
    			return rearLeftEncoder.getRate();
    		case RREncoder: //if = 3
    			return rearRightEncoder.getRate();
    		case MLEncoder: //if = 4
    			return midLeftEncoder.getRate();
    		case MREncoder: //if = 5
    			return midRightEncoder.getRate();
    		default:
    			return 0.0;
    	}
    }
   
    public boolean getEncoderDirection(int encoderNum){
    	switch(encoderNum){
    	case FLEncoder: //if = 0
    		return frontLeftEncoder.getDirection();
    	case FREncoder: //if = 1
    		return frontRightEncoder.getDirection();
    	case RLEncoder: //if = 2
    		return rearLeftEncoder.getDirection();
    	case RREncoder: //if = 3
    		return rearRightEncoder.getDirection();
    	case MLEncoder: //if = 4
    		return midLeftEncoder.getDirection();
    	case MREncoder: //if = 5
    		return midRightEncoder.getDirection();
    	default:
    	return false;
    	}
    }
    */
    public double getEncoderRPM(int encoderNum){
    	switch(encoderNum){
    		case LEncoder: //if = 0
    			previousRPM = leftGeartrainEncoder.getRate();
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
    		return rightGeartrainEncoder.getDistance();
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
    	/**
    	frontLeftEncoder.reset();
    	frontRightEncoder.reset();
    	rearLeftEncoder.reset();
    	rearRightEncoder.reset();
    	midLeftEncoder.reset();
    	midRightEncoder.reset();
    */
    	
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
    

    
    
    
    
    
    /* For Logging the Encoder and Gyro Values to the SmartDashboard */
    public void sendToDashboard(){
    	/**
    	SmartDashboard.putNumber("Front Left Encoder Distance: ", Robot.drivetrain.getEncoderDistance(FLEncoder));
    	SmartDashboard.putString("Front Left Encoder Direction: ", (Robot.drivetrain.getEncoderDirection(FLEncoder) ? "Clockwise" : "Counter-Clockwise"));
    	SmartDashboard.putNumber("Front Left Encoder RPM: ",  Robot.drivetrain.getEncoderRPM(FLEncoder));
    	
    	SmartDashboard.putNumber("Front Right Encoder Distance: ", Robot.drivetrain.getEncoderDistance(FREncoder));
    	SmartDashboard.putString("Front Right Encoder Direction: ", (Robot.drivetrain.getEncoderDirection(FREncoder) ? "Clockwise" : "Counter-Clockwise"));
    	SmartDashboard.putNumber("Front Right Encoder RPM: ",  Robot.drivetrain.getEncoderRPM(FREncoder));
    	
    	SmartDashboard.putNumber("Rear Left Encoder Distance: ", Robot.drivetrain.getEncoderDistance(RLEncoder));
    	SmartDashboard.putString("Rear Left Encoder Direction: ", (Robot.drivetrain.getEncoderDirection(RLEncoder) ? "Clockwise" : "Counter-Clockwise"));
    	SmartDashboard.putNumber("Rear Left Encoder RPM: ",  Robot.drivetrain.getEncoderRPM(RLEncoder));
    	
    	SmartDashboard.putNumber("Rear Right Encoder Distance: ", Robot.drivetrain.getEncoderDistance(RREncoder));
    	SmartDashboard.putString("Rear Right Encoder Direction: ", (Robot.drivetrain.getEncoderDirection(RREncoder) ? "Clockwise" : "Counter-Clockwise"));
    	SmartDashboard.putNumber("Rear Right Encoder RPM: ",  Robot.drivetrain.getEncoderRPM(RREncoder));
    	
    	SmartDashboard.putNumber("Mid Left Encoder Distance: ", Robot.drivetrain.getEncoderDistance(MLEncoder));
    	SmartDashboard.putString("Mid Left Encoder Direction: ", (Robot.drivetrain.getEncoderDirection(MLEncoder) ? "Clockwise" : "Counter-Clockwise"));
    	SmartDashboard.putNumber("Mid Left Encoder RPM: ",  Robot.drivetrain.getEncoderRPM(MLEncoder));
    	
      	SmartDashboard.putNumber("Mid Right Encoder Distance: ", Robot.drivetrain.getEncoderDistance(MREncoder));
    	SmartDashboard.putString("Mid Right Encoder Direction: ", (Robot.drivetrain.getEncoderDirection(MREncoder) ? "Clockwise" : "Counter-Clockwise"));
    	SmartDashboard.putNumber("Mid Right Encoder RPM: ",  Robot.drivetrain.getEncoderRPM(MREncoder));
    	*/
    	
    	SmartDashboard.putNumber("Left Geartrain Encoder Distance: ", Robot.drivetrain.getEncoderDistance(LEncoder));
    	SmartDashboard.putString("Left Geartrain Encoder Direction: ", (Robot.drivetrain.getEncoderDirection(LEncoder) ? "Clockwise" : "Counter-Clockwise"));
    	SmartDashboard.putNumber("Left Geartrain Encoder RPM: ", Robot.drivetrain.getEncoderRPM(LEncoder));
    	
    	SmartDashboard.putNumber("Right Geartrain Encoder Distance: ", Robot.drivetrain.getEncoderDistance(REncoder));
    	SmartDashboard.putString("Right Geartrain Encoder Direction: ", (Robot.drivetrain.getEncoderDirection(REncoder) ? "Clockwise" : "Counter-Clockwise"));
    	SmartDashboard.putNumber("Right Geartrain Encoder RPM: ", Robot.drivetrain.getEncoderRPM(REncoder));
    	
    	SmartDashboard.putNumber("Left Geartrain Encoder RAW: ",  Robot.drivetrain.leftGeartrainEncoder.get());
    	SmartDashboard.putNumber("Left and Right Encoder Difference", Math.abs(Robot.drivetrain.getEncoderRPM(LEncoder) - Robot.drivetrain.getEncoderRPM(REncoder)));
    	SmartDashboard.putNumber("Gyro Angle: ", Robot.drivetrain.getAngle());
    	
    	
    
    }
	
		
	}
  
    
    
    
    
   


