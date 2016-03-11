package org.usfirst.frc.team614.robot;



import org.team708.robot.util.Gamepad;
import org.usfirst.frc.team614.robot.commands.drivetrain.SwitchGears;
import org.usfirst.frc.team614.robot.commands.drivetrain.ToggleDrivetrainPID;
import org.usfirst.frc.team614.robot.commands.shooter.ToggleShooterPID;
import org.usfirst.frc.team614.robot.commands.drivetrain.TurnToAngle;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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
			public static final int TOGGLE_SHOOTER_PID = Gamepad.button_Back;
			public static final int TED_OUT = Gamepad.button_R_Shoulder;
			
		//Driver Gamepad
			public static final int TURN_TO_ANGLE = Gamepad.button_X;
			public static final int SWITCH_GEARS = Gamepad.button_Start;
			public static final int TOGGLE_DRIVETRAIN_PID = Gamepad.button_Back;
	
	
	// Gamepads
			
		public final static Gamepad driverGamepad = new Gamepad(RobotMap.driverGamepad);			// Driver gamepad
		public final static Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepad);		// Operator gamepad
		
		
		//Operator Gamepad
			private static final Button toggleShooterPID = new JoystickButton(operatorGamepad, TOGGLE_SHOOTER_PID);
			
		//Driver Gamepad
			private static final Button turnToAngle = new JoystickButton(driverGamepad, TURN_TO_ANGLE);
			private static final Button switchGears = new JoystickButton(driverGamepad, SWITCH_GEARS);
			private static final Button toggleDrivetrainPID = new JoystickButton(driverGamepad, TOGGLE_DRIVETRAIN_PID);
		
	public OI(){
			
	//Operator Commands
		
		//Control Buttons
		

		//Technical Buttons
			toggleShooterPID.whenPressed(new ToggleShooterPID());
			
			
	//Driver Commands
		
		//Control Buttons
			turnToAngle.whenPressed(new TurnToAngle(90, Constants.MOTOR_TURN_SPEED, 0));
		//Technical Buttons
			switchGears.toggleWhenPressed(new SwitchGears());
			toggleDrivetrainPID.whenPressed(new ToggleDrivetrainPID());
		
		
		
		
		
		
	
}
}

