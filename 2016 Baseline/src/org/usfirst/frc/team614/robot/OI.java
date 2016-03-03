package org.usfirst.frc.team614.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.Joystick;

import org.usfirst.frc.team614.robot.RobotMap;
import org.usfirst.frc.team614.robot.commands.drivetrain.DriveStraightForADistance;
import org.usfirst.frc.team614.robot.commands.drivetrain.DriveStraightForATime;
import org.usfirst.frc.team614.robot.commands.drivetrain.SwitchGears;
import org.usfirst.frc.team614.robot.commands.drivetrain.ToggleDrivetrainPID;
//import org.usfirst.frc.team614.robot.commands.drivetrain.ToggleDrivetrainPID;
import org.usfirst.frc.team614.robot.commands.drivetrain.TurnToAngle;
import org.usfirst.frc.team614.robot.commands.shooter.PewPewMeasureOut;
import org.usfirst.frc.team614.robot.commands.shooter.PewPewShoot;
import org.usfirst.frc.team614.robot.commands.shooter.PewPewMeasureRetrieve;
import org.usfirst.frc.team614.robot.commands.shooter.Shoot;
import org.usfirst.frc.team614.robot.commands.shooter.ShootSequence;
import org.usfirst.frc.team614.robot.commands.shooter.TEDIn;
import org.usfirst.frc.team614.robot.commands.shooter.TEDOut;
import org.usfirst.frc.team614.robot.commands.shooter.ToggleShooterPID;
//import org.usfirst.frc.team614.robot.commands.shooter.ShootSequence;
//import org.usfirst.frc.team614.robot.commands.shooter.ToggleShooterPID;
import org.team708.robot.util.Gamepad;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	

	//Gamepads
	
		//Operator Gamepad
			public static final int PEW_PEW_MEASURE_OUT = Gamepad.button_A;
			public static final int PEW_PEW_MEASURE_RETRIEVE = Gamepad.button_B;
			public static final int PEW_PEW_SHOOT = Gamepad.button_R_Shoulder;
			public static final int SHOOT_SEQUENCE = Gamepad.button_R_Shoulder;
			public static final int TOGGLE_SHOOTER_PID = Gamepad.button_Back;
			public static final int TED_OUT = Gamepad.button_R_Shoulder;
			
		//Driver Gamepad
			public static final int TURN_TO_ANGLE = Gamepad.button_L_Shoulder;
			
			public static final int SWITCH_GEARS = Gamepad.button_Start;
			public static final int DRIVE_STRAIGHT_DISTANCE = Gamepad.button_Y;
			public static final int DRIVE_STRAIGHT_TIME = Gamepad.button_X;
			public static final int TOGGLE_DRIVETRAIN_PID = Gamepad.button_Back;
	
	
	// Gamepads
			
		public final static Gamepad driverGamepad = new Gamepad(RobotMap.driverGamepad);			// Driver gamepad
		public final static Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepad);		// Operator gamepad
		
		
		//Operator Gamepad
			private static final Button pewPewOut = new JoystickButton(operatorGamepad, PEW_PEW_MEASURE_OUT);
			private static final Button pewPewIn = new JoystickButton(operatorGamepad, PEW_PEW_MEASURE_RETRIEVE);
			private static final Button pewPewShoot = new JoystickButton(operatorGamepad, TED_OUT);
			private static final Button shootSequence = new JoystickButton(operatorGamepad, SHOOT_SEQUENCE);
			private static final Button toggleShooterPID = new JoystickButton(operatorGamepad, TOGGLE_SHOOTER_PID);
			//private static final Button TEDOut = new JoystickButton(operatorGamepad, TED_OUT);
		
		//Driver Gamepad
			private static final Button turnToAngle = new JoystickButton(driverGamepad, TURN_TO_ANGLE);
			private static final Button switchGears = new JoystickButton(driverGamepad, SWITCH_GEARS);
			private static final Button driveDistance = new JoystickButton(driverGamepad, DRIVE_STRAIGHT_DISTANCE);
			private static final Button driveTime = new JoystickButton(driverGamepad, DRIVE_STRAIGHT_TIME);
			private static final Button toggleDrivetrainPID = new JoystickButton(driverGamepad, TOGGLE_DRIVETRAIN_PID);
		
	public OI(){
			
	//Operator Commands
		
		//Control Buttons
			pewPewOut.whileActive(new PewPewMeasureOut());
			pewPewIn.whileActive(new PewPewMeasureRetrieve());
			//shootSequence.whenPressed(new ShootSequence());
			//pewPewShoot.toggleWhenPressed(new PewPewShoot(1)); //replaces by shootSequence since the servo was removed
			pewPewShoot.whenPressed(new PewPewShoot(0));

		//Technical Buttons
			toggleShooterPID.whenPressed(new ToggleShooterPID());
			
			
	//Driver Commands
		
		//Control Buttons
			turnToAngle.whenPressed(new TurnToAngle(90, Constants.MOTOR_TURN_SPEED));
			driveDistance.whenPressed(new DriveStraightForADistance(.7, 12, Robot.drivetrain.getUsePID()));
			driveTime.whenPressed(new DriveStraightForATime(.7, 5, Robot.drivetrain.getUsePID()));
		//Technical Buttons
			switchGears.toggleWhenPressed(new SwitchGears());
			toggleDrivetrainPID.whenPressed(new ToggleDrivetrainPID());
		
		
		
		
		
		
	
}
}

