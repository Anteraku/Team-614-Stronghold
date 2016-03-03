package org.usfirst.frc.team614.robot.commands.shooter;

import org.team708.robot.util.Gamepad;
import org.usfirst.frc.team614.robot.OI;
import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class PewPewShoot extends Command {

	private double timeout;
	
    public PewPewShoot(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    	timeout = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	 setTimeout(timeout);
    	 Robot.shooter.TEDOut();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !(OI.operatorGamepad.getButton(OI.operatorGamepad.button_R_Shoulder));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.TEDIn();
    	setTimeout(.5);
    	while(!isTimedOut()){
    		Robot.shooter.stopTED();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
