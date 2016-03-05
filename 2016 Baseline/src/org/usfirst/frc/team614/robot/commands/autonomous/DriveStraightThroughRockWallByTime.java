package org.usfirst.frc.team614.robot.commands.autonomous;

import org.usfirst.frc.team614.robot.commands.drivetrain.DriveStraightForATime;
import org.usfirst.frc.team614.robot.commands.shooter.LowerLift;
import org.usfirst.frc.team614.robot.commands.shooter.RaiseLift;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DriveStraightThroughRockWallByTime extends CommandGroup {
    
	private static final double driveStraightSpeed = 1;
	private static final double driveStraightTime = 5;
	
	private static final double turnSpeed = .5;
	private static final double shootAngle = 30;
	
    public  DriveStraightThroughRockWallByTime() {
    	
    	addSequential(new RaiseLift());
    	
    	
    addParallel(new DriveStraightForATime(5, driveStraightSpeed, true, true));
    addSequential(new WaitCommand(3));
    addSequential(new LowerLift());
    
    
    
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
