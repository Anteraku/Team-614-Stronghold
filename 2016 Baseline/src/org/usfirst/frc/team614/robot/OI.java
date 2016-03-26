package org.usfirst.frc.team614.robot;



import org.team708.robot.util.Gamepad;
import org.usfirst.frc.team614.robot.commands.autonomous.LineUpThenShoot;
import org.usfirst.frc.team614.robot.commands.autonomous.Shoot;
import org.usfirst.frc.team614.robot.commands.drivetrain.DriveTestSpeed;
import org.usfirst.frc.team614.robot.commands.drivetrain.SwitchGears;
import org.usfirst.frc.team614.robot.commands.drivetrain.ToggleDrivetrainPID;
import org.usfirst.frc.team614.robot.commands.shooter.PewPewRevIn;
import org.usfirst.frc.team614.robot.commands.shooter.PewPewRevOut;
import org.usfirst.frc.team614.robot.commands.shooter.PewPewShoot;
import org.usfirst.frc.team614.robot.commands.shooter.PrepTEDandRevIn;
import org.usfirst.frc.team614.robot.commands.shooter.TEDIn;
import org.usfirst.frc.team614.robot.commands.shooter.TEDOut;
import org.usfirst.frc.team614.robot.commands.shooter.ToggleShooterPID;
import org.usfirst.frc.team614.robot.commands.shooter.TuckTEDAndLowerLift;
import org.usfirst.frc.team614.robot.commands.visionProcessor.LineUpShot;
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
			public static final int TOGGLE_SHOOTER_PID = Gamepad.button_Back;
			
			public static final int PEW_PEW_REV_IN = Gamepad.button_L_Shoulder;
			public static final int PEW_PEW_REV_OUT = Gamepad.button_R_Shoulder;

			public static final int TED_OUT = Gamepad.button_X;
			public static final int TED_IN = Gamepad.button_B;
			//public static final int SHOOT_SEQUENCE_2 = Gamepad.button_Y;
			public static final int SHOOT_SEQUENCE = Gamepad.button_A;
			public static final int TUCK_TED_AND_LOWER_LIFT = Gamepad.button_Y;
//			public static final int POP_TED_AND_REV_IN = Gamepad.button_L_Shoulder;
		//Driver Gamepad
			public static final int TURN_TO_ANGLE = Gamepad.button_X;
			public static final int SWITCH_GEARS = Gamepad.button_Start;
			public static final int TOGGLE_DRIVETRAIN_PID = Gamepad.button_Back;
			
			public static final int TURN_AROUND_FAST = Gamepad.button_A;
			public static final int LINE_UP_SHOT = Gamepad.button_Y;
			public static final int LINE_UP_THEN_SHOOT = Gamepad.button_B;
			
			public static final int DRIVE_TEST_SPEED = Gamepad.button_R_Shoulder;
	
	// Gamepads
			
		public final static Gamepad driverGamepad = new Gamepad(RobotMap.driverGamepad);			// Driver gamepad
		public final static Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepad);		// Operator gamepad
		
		
		//Operator Gamepad
			private static final Button toggleShooterPID = new JoystickButton(operatorGamepad, TOGGLE_SHOOTER_PID);
			private static final Button revIn = new JoystickButton(operatorGamepad, PEW_PEW_REV_IN);
			private static final Button revOut = new JoystickButton(operatorGamepad, PEW_PEW_REV_OUT);
			
			private static final Button tedIn = new JoystickButton(operatorGamepad, TED_IN);
			private static final Button tedOut = new JoystickButton(operatorGamepad, TED_OUT);
//			private static final Button shootSequence2 = new JoystickButton(operatorGamepad, SHOOT_SEQUENCE_2);
			private static final Button shootSequence = new JoystickButton(operatorGamepad, SHOOT_SEQUENCE);
			private static final Button tuckTEDLowerLift = new JoystickButton(operatorGamepad, TUCK_TED_AND_LOWER_LIFT);
//			private static final Button popTEDAndRevIn = new JoystickButton(operatorGamepad, POP_TED_AND_REV_IN);
		//Driver Gamepad
			private static final Button turnToAngle = new JoystickButton(driverGamepad, TURN_TO_ANGLE);
			private static final Button switchGears = new JoystickButton(driverGamepad, SWITCH_GEARS);
			private static final Button toggleDrivetrainPID = new JoystickButton(driverGamepad, TOGGLE_DRIVETRAIN_PID);
			private static final Button turnAroundFast = new JoystickButton(driverGamepad, TURN_AROUND_FAST);
			private static final Button lineUpShot = new JoystickButton(driverGamepad, LINE_UP_SHOT);
			private static final Button lineUpThenShoot = new JoystickButton(driverGamepad, LINE_UP_THEN_SHOOT);
			private static final Button driveTestSpeed = new JoystickButton(driverGamepad, DRIVE_TEST_SPEED);
	public OI(){
			
	//Operator Commands
		
		//Control Buttons
			revIn.whileHeld(new PewPewRevIn(true));
			revOut.whileHeld(new PewPewRevOut(true));
			tedIn.whenPressed(new TEDIn(1, 1));
			tedOut.whenPressed(new TEDOut(1, 1));
			shootSequence.whenPressed(new Shoot());
//			shootSequence2.whenPressed(new PewPewShoot(3));
//			popTEDAndRevIn.whenPressed(new PrepTEDandRevIn());
			tuckTEDLowerLift.whenPressed(new TuckTEDAndLowerLift());
		//Technical Buttons
			toggleShooterPID.whenPressed(new ToggleShooterPID());
			
			
	//Driver Commands
		
		//Control Buttons
			turnToAngle.whenPressed(new TurnToAngle(90, Constants.MOTOR_TURN_SPEED, 0));
			turnAroundFast.whenPressed(new TurnToAngle(180, 1, 0));
			lineUpShot.whileHeld(new LineUpShot());
			lineUpThenShoot.whenPressed(new LineUpThenShoot());
			
			driveTestSpeed.whileHeld(new DriveTestSpeed(.7, true));
		//Technical Buttons
			switchGears.toggleWhenPressed(new SwitchGears());
			toggleDrivetrainPID.whenPressed(new ToggleDrivetrainPID());
			
		
		
		
		
		
		
	
}
}

