package org.usfirst.frc.team614.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	
	// Gamepad USB ports
	public static final int driverGamepad   = 0;
	public static final int operatorGamepad = 1;;
	
	//JOYSTICK CONSTANTS
		public static final double JOYSTICK_DEADBAND = 0.2;
	
	// Drivetrain VictorSP ports (PWM)
		public static final int drivetrainFrontLeftMotor  = 0;
		public static final int drivetrainFrontRightMotor = 1;
		public static final int drivetrainRearLeftMotor   = 2;
		public static final int drivetrainRearRightMotor  = 3;
		public static final int drivetrainMidLeftMotor    = 4;
		public static final int drivetrainMidRightMotor   = 5;
		
	
	
	//Shooter VictorSP ports (PWM)
		public static final int shooterLeftMotor  = 6;
		public static final int shooterRightMotor = 7;
		public static final int shooterAngleMotor = 9;
		
	//Servo port (PWM)
		public static final int servo_ID = 8;
	
	//Solenoid port (PCM, Pneumatics Control Module)
		public static final int solenoid_A = 0;
		public static final int solenoid_B = 1;
	
	//Analog Sensor IDs
		public static final int gyro_ID = 0;
		
	//Encoder ports (DIO)
		
		//Shooter Encoders
		public static final int leftShooterEncoder_A  = 0;
		public static final int leftShooterEncoder_B  = 1; 
		public static final int rightShooterEncoder_A = 2;
		public static final int rightShooterEncoder_B = 3;
		public static final int angleShooterEncoder_A = 4;
		public static final int angleShooterEncoder_B = 5;
		
		//Drivetrain Encoders
		
		public static final int leftGeartrainEncoder_A = 6;
		public static final int leftGeartrainEncoder_B = 7;
		public static final int rightGeartrainEncoder_A = 8;
		public static final int rightGeartrainEncoder_B = 9;
}
