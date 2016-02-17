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
	public static final int driverGamepad   = 1;
	public static final int operatorGamepad = 2;
	
	//JOYSTICK CONSTANTS
		public static final double JOYSTICK_DEADBAND = 0.2;
	
	// Drivetrain VictorSP ports
		public static final int drivetrainFrontLeftMotor  = 0;
		public static final int drivetrainFrontRightMotor   = 1;
		public static final int drivetrainRearLeftMotor = 2;
		public static final int drivetrainRearRightMotor  = 3;
		public static final int drivetrainMidLeftMotor = 4;
		public static final int drivetrainMidRightMotor  = 5;
	
	//Shooter VictorSP ports
		public static final int shooterLeftMotor = 6;
		public static final int shooterRightMotor = 7;
		
	//Solenoid port
		public static final int solenoid_A = 0;
		public static final int solenoid_B = 1;
		
	//vision processing data

		
	
}
