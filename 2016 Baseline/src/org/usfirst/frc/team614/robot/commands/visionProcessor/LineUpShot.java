package org.usfirst.frc.team614.robot.commands.visionProcessor;

import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LineUpShot extends Command {

    public LineUpShot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.visionProcessor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.visionProcessor.processData();
    	Robot.drivetrain.arcadeDriveMode(0, Robot.visionProcessor.getRotate(), true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.visionProcessor.getRotate() == 0;
    }

    // Called once after isFinished returns true
    protected void end(){
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.stop();
    }
}
