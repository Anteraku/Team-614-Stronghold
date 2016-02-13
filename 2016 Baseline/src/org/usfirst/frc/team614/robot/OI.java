package org.usfirst.frc.team614.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

import org.usfirst.frc.team614.robot.RobotMap;
import org.usfirst.frc.team614.robot.commands.shooter.ServoTestDrive;
import org.usfirst.frc.team614.robot.commands.shooter.Shoot;
import org.usfirst.frc.team614.robot.commands.shooter.ShootSequence;
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
	
	private static final int SERVO_TEST = Gamepad.button_A;
	private static final int SHOOT = Gamepad.button_B;
	
	
	
			
	
	
	// Gamepads
		public final static Gamepad driverGamepad = new Gamepad(RobotMap.driverGamepad);			// Driver gamepad
		public final static Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepad);		// Operator gamepad
		
		
		private static final Button servoTest = new JoystickButton(driverGamepad, SERVO_TEST);
		private static final Button shoot = new JoystickButton(driverGamepad, SHOOT);
		
		public OI(){
			servoTest.whenPressed(new ServoTestDrive(1));
			shoot.whenPressed(new Shoot(1));
		//shootSequence.whenPressed(new ShootSequence());
}

}