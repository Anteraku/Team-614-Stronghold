package org.usfirst.frc.team614.robot.commands.shooter;

import org.usfirst.frc.team614.robot.Constants;
import org.usfirst.frc.team614.robot.OI;
import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PewPewMeasureRetrieve extends Command {

    public PewPewMeasureRetrieve() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.shootMode(-Constants.FLYWHEEL_RETRIEVE_SPEED, true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	 return !(OI.driverGamepad.getButton(OI.PEW_PEW_MEASURE_RETRIEVE));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.stopFlywheel();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
