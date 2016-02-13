package org.usfirst.frc.team614.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *Used to move the robot into place, align with the goal, and then shoot with the servo and motors. 
 */
public class ShootSequence extends CommandGroup {
    
	private static final double timeToStart = 2;
	
    public  ShootSequence() {
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
