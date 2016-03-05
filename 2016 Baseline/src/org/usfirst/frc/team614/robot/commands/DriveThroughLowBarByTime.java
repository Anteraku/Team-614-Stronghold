package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.commands.drivetrain.DriveStraightForADistance;
import org.usfirst.frc.team614.robot.commands.drivetrain.DriveStraightForATime;
import org.usfirst.frc.team614.robot.commands.drivetrain.TurnToAngle;
import org.usfirst.frc.team614.robot.commands.shooter.LowerLift;
import org.usfirst.frc.team614.robot.commands.shooter.PewPewMeasureOut;
import org.usfirst.frc.team614.robot.commands.shooter.PewPewShoot;
import org.usfirst.frc.team614.robot.commands.shooter.RaiseLift;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DriveThroughLowBarByTime extends CommandGroup {
	
	private static final double driveStraightSpeed = 1;
	private static final double driveStraightTime = 5;
	
	private static final double turnSpeed = .5;
	private static final double shootAngle = 30;
    
    public  DriveThroughLowBarByTime() {
    	
    	addSequential(new LowerLift());
    	
    	//Turn left to become aligned with the low bar
    	addSequential(new TurnToAngle(-90, turnSpeed));
    	addSequential(new WaitCommand(1));
    	
    	//Drive towards the low bar to become aligned with it
    	addSequential(new DriveStraightForATime(3, driveStraightSpeed, true, true));
    	addSequential(new WaitCommand(1));
    	
    	addSequential(new DriveStraightForATime(1, .5, true, false));
    	addSequential(new WaitCommand(1));
    	
    	//Turn right to face the low bar
    	addSequential(new TurnToAngle(90, turnSpeed));
    	addSequential(new WaitCommand(1));
    	
    	//Drive through the low bar
    	addSequential(new DriveStraightForATime(driveStraightTime, driveStraightSpeed, true, true));
    	addSequential(new WaitCommand(1));
    	
    	
    	addSequential(new RaiseLift());
    	
    	//Face the goal
    	addSequential(new TurnToAngle(180+shootAngle, turnSpeed));
    	
    	//Rev up and shoot at goal
    	addParallel(new PewPewMeasureOut());
    	addSequential(new PewPewShoot(3));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
