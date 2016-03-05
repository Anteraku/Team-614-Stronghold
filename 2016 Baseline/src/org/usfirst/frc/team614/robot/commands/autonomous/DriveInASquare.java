package org.usfirst.frc.team614.robot.commands.autonomous;

import org.usfirst.frc.team614.robot.commands.drivetrain.DriveStraightForATime;
import org.usfirst.frc.team614.robot.commands.drivetrain.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */

public class DriveInASquare extends CommandGroup {

	

private static final double driveStraightSpeed = .85;
private static final double driveStraightTime = 2;

private static final double turnSpeed = .8;
private static final double turnDegrees = 90;

    public  DriveInASquare() {
    	
    	
    	addSequential(new DriveStraightForATime(driveStraightSpeed, driveStraightTime, true, true));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new TurnToAngle(turnSpeed, turnDegrees));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new DriveStraightForATime(driveStraightSpeed, driveStraightTime, true, true));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new TurnToAngle(turnSpeed, turnDegrees));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new DriveStraightForATime(driveStraightSpeed, driveStraightTime, true, true));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new TurnToAngle(turnSpeed, turnDegrees));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new DriveStraightForATime(driveStraightSpeed, driveStraightTime, true, true));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new TurnToAngle(turnSpeed, turnDegrees));
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
