package org.usfirst.frc.team614.robot.subsystems;

import org.team708.robot.util.EncoderRotationSensor;
import org.team708.robot.util.Math708;
import org.usfirst.frc.team614.robot.Constants;
import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.RobotMap;
import org.usfirst.frc.team614.robot.commands.drivetrain.JoystickDrive;



import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
  
 */
public class Drivetrain extends PIDSubsystem {
    	
	private RobotDrive drivetrain;
	
	private VictorSP leftMotor,rightMotor; 
	
	private Encoder leftGeartrainEncoder, rightGeartrainEncoder;
	public static final int LEncoder = 0;
	public static final int REncoder = 1;
	private double distancePerPulse;
	
	//Variables specific for drivetrain PID loop
	private boolean usePID = true;
	private double moveSpeed = 0.0;
	private double pidOutput = 0.0;
	
	private Solenoid piston;
	
	private AnalogGyro gyro;
	private EncoderRotationSensor rotationSensor;
	private BuiltInAccelerometer accelerometer;	
	
	public Drivetrain(){
		
		super("Drivetrain", Constants.Kp, Constants.Ki, Constants.Kd);
			
		
		 
		//Initializes the motors
 		leftMotor = new VictorSP(RobotMap.leftGeartrainMotor);
 		rightMotor = new VictorSP(RobotMap.rightGeartrainMotor);
 		
 		//Initializes drivetrain class
		drivetrain = new RobotDrive(rightMotor, leftMotor);
		 
 		//Initializes the Encoders
 		leftGeartrainEncoder = new Encoder(RobotMap.leftGeartrainEncoder_A, RobotMap.leftGeartrainEncoder_B);
 		rightGeartrainEncoder = new Encoder(RobotMap.rightGeartrainEncoder_A, RobotMap.rightGeartrainEncoder_B);

 		distancePerPulse = (Constants.DRIVETRAIN_WHEEL_DIAMETER * Math.PI) / Constants.DRIVETRAIN_ENCODER_PULSES_PER_REV;
 		leftGeartrainEncoder.setDistancePerPulse(distancePerPulse);
 		rightGeartrainEncoder.setDistancePerPulse(distancePerPulse);
 		rightGeartrainEncoder.setReverseDirection(true);
 		resetEncoders();

    	//Initializes the solenoid
    	piston = new Solenoid(RobotMap.solenoid);
    
    	//Initializes gyro
		gyro = new AnalogGyro(RobotMap.gyro_ID);
		gyro.reset();
		
		//Initializes the encoder rotation sensor
		rotationSensor = new EncoderRotationSensor(leftGeartrainEncoder, rightGeartrainEncoder, Constants.ROBOT_DIAMETER_IN);
		
		accelerometer = new BuiltInAccelerometer();
		
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
    	rotate = -rotate * Constants.ROTATE_MOTOR_MAX_SPEED;
    	
    	if(usePID){
    		if(rotate == 0.0 && move != 0.0) {
    			
    			if(!getPIDController().isEnabled()){
//    				getPIDController().reset();
    				getPIDController().setPID(Constants.Kp, Constants.Ki, Constants.Kd);
//    				resetAngle();
    				enable();
//    				resetAngle();
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
    	
    
    
    	drivetrain.arcadeDrive(move, rotate);
    }
    
    public void stop(){
    	drivetrain.arcadeDrive(0,0);
    }
    
    
    /*
     * Solenoid Methods
     */
    public void extend(){
    	piston.set(true);	
    	//Piston2.set(true);
    }
    
    public void retract(){
    	piston.set(false);	
    	//Piston2.set(false);
    }
    
    public void togglePiston(){
    	piston.set(!piston.get());	
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
    		return rightGeartrainEncoder.getDistance() * Constants.DRIVETRAIN_ENCODER_PERCENT_ERROR;
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
    
    public double getEncoderDifference(){
    	return getEncoderDistance(LEncoder) - getEncoderDistance(REncoder);
    }
    
    public void resetEncoders(){
    	leftGeartrainEncoder.reset();
    	rightGeartrainEncoder.reset();
    }

    
/*
 	* Rotation Sensor Methods
 */
    public double getAngle(){
	  return rotationSensor.getAngle() *Constants.ENCODER_TO_DEGREES;
  }
  
    public void resetAngle(){
	  rotationSensor.reset();
	  //resetEncoders();
	  
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
    	return getAngle();
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
    	if(Constants.DEBUG){
    		
    	//Encoder Info
    	SmartDashboard.putNumber("Left Geartrain Encoder Distance: ", Robot.drivetrain.getEncoderDistance(LEncoder));
//    	SmartDashboard.putString("Left Geartrain Encoder Direction: ", (Robot.drivetrain.getEncoderDirection(LEncoder) ? "Clockwise" : "Counter-Clockwise"));
//    	SmartDashboard.putNumber("Left Geartrain Encoder RPM: ", Robot.drivetrain.getEncoderRPM(LEncoder));
    	
    	SmartDashboard.putNumber("Right Geartrain Encoder Distance: ", Robot.drivetrain.getEncoderDistance(REncoder));
//    	SmartDashboard.putString("Right Geartrain Encoder Direction: ", (Robot.drivetrain.getEncoderDirection(REncoder) ? "Clockwise" : "Counter-Clockwise"));
//    	SmartDashboard.putNumber("Right Geartrain Encoder RPM: ", Robot.drivetrain.getEncoderRPM(REncoder));
    	
//    	SmartDashboard.putNumber("Left Geartrain Encoder RAW: ",  Robot.drivetrain.leftGeartrainEncoder.get());
//    	SmartDashboard.putNumber("Right Geartrain Encoder RAW: ", Robot.drivetrain.rightGeartrainEncoder.get());
//    	SmartDashboard.putNumber("Left and Right Encoder Difference", Math.abs(getEncoderDifference()));
    	
    	// Accelerometer Info
//    	SmartDashboard.putNumber("Accelerometer X", accelerometer.getX());
//    	SmartDashboard.putNumber("Accelerometer Y", accelerometer.getY());
//    	SmartDashboard.putNumber("Accelerometer Z", accelerometer.getZ());
    	

    	
    	}
    	
    	SmartDashboard.putNumber("Drivetrain Angle: ", Robot.drivetrain.getAngle());
    	
    	SmartDashboard.putBoolean("Drivetrain PID", getUsePID());
    	
    	
   
    }
	
		
}
  
    
    
    
    
   


