package org.usfirst.frc.team614.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.Joystick;

import org.usfirst.frc.team614.robot.RobotMap;
import org.usfirst.frc.team614.robot.commands.shooter.PewPewMeasureOut;
import org.usfirst.frc.team614.robot.commands.shooter.PewPewShoot;
import org.usfirst.frc.team614.robot.commands.shooter.PewPewMeasureRetrieve;
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
	
	public static final int PEW_PEW_SHOOT = Gamepad.button_R_Shoulder;
	//public static final int PEW_PEW_SHOOT = Gamepad.shoulderAxisRight;

	public static final int PEW_PEW_MEASURE_OUT = Gamepad.button_A;
	public static final int PEW_PEW_MEASURE_RETRIEVE = Gamepad.button_B;
	
	
	
			
	
	
	// Gamepads
		public final static Gamepad driverGamepad = new Gamepad(RobotMap.driverGamepad);			// Driver gamepad
		public final static Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepad);		// Operator gamepad
		
		
		private static final Button pewPewShoot = new JoystickButton(driverGamepad, PEW_PEW_SHOOT);
		//private static final Trigger pewPewShoot2 = new JoystickButton(driverGamepad, PEW_PEW_SHOOT);
		private static final Button pewPewOut = new JoystickButton(driverGamepad, PEW_PEW_MEASURE_OUT);
		private static final Button pewPewIn = new JoystickButton(driverGamepad, PEW_PEW_MEASURE_RETRIEVE);
		
		
		public OI(){
	
		pewPewShoot.toggleWhenPressed(new PewPewShoot(1));
		//pewPewShoot2.toggleWhenActive(new PewPewShoot(1));	
		pewPewOut.toggleWhenActive(new PewPewMeasureOut());
		pewPewIn.toggleWhenActive(new PewPewMeasureRetrieve());
		
	
		//shootSequence.whenPressed(new ShootSequence());
}
}

