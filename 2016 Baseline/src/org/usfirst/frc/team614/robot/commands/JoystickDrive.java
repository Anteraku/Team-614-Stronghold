package org.usfirst.frc.team614.robot.commands;




import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team614.robot.OI;
import org.usfirst.frc.team614.robot.Robot;
import org.team708.robot.util.Gamepad;

/**
 *
 */
public class JoystickDrive extends Command {

    public JoystickDrive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.arcadeDriveMode(OI.driverGamepad.getAxis(Gamepad.leftStick_Y), -OI.driverGamepad.getAxis(Gamepad.rightStick_X));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
