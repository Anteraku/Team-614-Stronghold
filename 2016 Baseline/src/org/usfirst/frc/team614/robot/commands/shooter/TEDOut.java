package org.usfirst.frc.team614.robot.commands.shooter;

import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TEDOut extends Command {

	double time, speed;
	
	boolean doICare;
	
    public TEDOut(double time, double speed, boolean doICare) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.shooter);
    	
    	this.time = time;
    	this.speed = speed;
    	this.doICare = doICare;
    }
    
    public TEDOut(double time, double speed) {
    	this(time, speed, false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if (!(doICare) && !(Robot.shooter.tedIn)) {
    		Robot.shooter.TEDOut(speed);
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
//    	Robot.shooter.tedIn = false;
    	Robot.shooter.stopTED();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
