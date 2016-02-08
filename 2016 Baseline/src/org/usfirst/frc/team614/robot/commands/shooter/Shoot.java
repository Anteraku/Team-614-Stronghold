package org.usfirst.frc.team614.robot.commands.shooter;

import org.usfirst.frc.team614.robot.Constants;
import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {

    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.revUpForward();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.shooter.getLeftEncoderRPM() > Constants.TARGET_RPM && Robot.shooter.getRightEncoderRPM() > Constants.TARGET_RPM);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.flickBall();
    	//if flick doesn't not execute in time for the motors to slow down, add a delay
    	Robot.shooter.stop();
    	Robot.shooter.resetServo();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
