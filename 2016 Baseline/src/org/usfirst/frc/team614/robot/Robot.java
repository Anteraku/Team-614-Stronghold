
package org.usfirst.frc.team614.robot;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.team708.robot.util.Gamepad;
import org.usfirst.frc.team614.robot.subsystems.Drivetrain;
import org.usfirst.frc.team614.robot.subsystems.Shooter;
import org.usfirst.frc.team614.robot.subsystems.VisionProcessor;
import org.usfirst.frc.team614.robot.util.CameraFeeds;
import org.usfirst.frc.team614.robot.commands.DoNothing;
import org.usfirst.frc.team614.robot.commands.autonomous.DropItLikeItsHot;
import org.usfirst.frc.team614.robot.commands.autonomous.Shoot;
import org.usfirst.frc.team614.robot.commands.drivetrain.DriveStraightForADistance;
import org.usfirst.frc.team614.robot.commands.drivetrain.DriveStraightForATime;
import org.usfirst.frc.team614.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team614.robot.commands.drivetrain.ResetDrivetrainEncoders;
import org.usfirst.frc.team614.robot.commands.drivetrain.TurnToAngle;
import org.usfirst.frc.team614.robot.commands.shooter.LowerLift;
import org.usfirst.frc.team614.robot.commands.shooter.PewPewRevOut;
import org.usfirst.frc.team614.robot.commands.shooter.RaiseLift;
import org.usfirst.frc.team614.robot.commands.shooter.ShootSequence; 
import org.usfirst.frc.team614.robot.commands.shooter.TEDIn;
import org.usfirst.frc.team614.robot.commands.shooter.TEDOut;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	 public static Drivetrain drivetrain;
	 public static Shooter shooter;
	 public static VisionProcessor visionProcessor;
	 //public static CameraServer shootCamera = CameraServer.getInstance();
	 //public static CameraServer topCamera = CameraServer.getInstance();
	 

	 //	public static USBCamera shootCam;
	 //public static USBCamera topCam;

    Command autonomousCommand;
    SendableChooser autonomousMode;

    Timer statsTimer;
   
    //CameraFeeds cameraFeeds = new CameraFeeds(OI.driverGamepad);
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     * 
     */
    public void robotInit() {
    	statsTimer = new Timer();
    	statsTimer.start();
		
		drivetrain = new Drivetrain();
		shooter = new Shooter();
		visionProcessor = new VisionProcessor();
		
		
		oi = new OI();
		
		
		CameraServer.getInstance().startAutomaticCapture("cam0");
		
		//frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		
		//the camera name (ex "cam0") can be found through the roborio web interface
		//session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		//NIVision.IMAQdxConfigureGrab(session);
		
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(shooter);
		SmartDashboard.putData(visionProcessor);
		SmartDashboard.putData(new PewPewRevOut());
		SmartDashboard.putData(new TEDIn(.5));
		SmartDashboard.putData(new TEDOut(.5));
		SmartDashboard.putData(new ResetDrivetrainEncoders());
		SmartDashboard.putData(new TurnToAngle(90, 1, 0));
        autonomousMode = new SendableChooser();
        addAutonomousModes();
      
  
      
        
               
//     
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	//cameraFeeds.end();
    	//cameraFeeds.init();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
//		cameraFeeds.run();
		sendStatistics();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) autonomousMode.getSelected();
     
    	
    
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
       
        sendStatistics();
        
        
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	
        if (autonomousCommand != null) autonomousCommand.cancel();
//        cameraFeeds.end();
//        cameraFeeds.init();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
       
//		cameraFeeds.run();
        sendStatistics();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    private void sendStatistics() {
    	if(statsTimer.get() >= Constants.SEND_STATS_INTERVAL){
    		statsTimer.reset();
    		
    		drivetrain.sendToDashboard();
    		shooter.sendToDashboard();
    		visionProcessor.sendToDashboard();
    	}
    }
   
    
  private void addAutonomousModes() {
	autonomousMode.addDefault("1) Do Nothing", new DoNothing());
	
	autonomousMode.addDefault("1) Shoot From Spy Box", new Shoot());
	autonomousMode.addObject("2) How Low Can You Go", new DropItLikeItsHot());

	autonomousMode.addObject("3) Drive For Time: 3, .8, F", new DriveStraightForATime(3, .8, true, true));
	autonomousMode.addObject("4) Drive For Time Backwards: 3, .8, B", new DriveStraightForATime(3, .8, true, false));
	autonomousMode.addObject("5) Drive For a Distance: 24, .8, F", new DriveStraightForADistance(24, .8, true, false));
	autonomousMode.addObject("6) Drive For a Distance Backwards: 24, .8, B", new DriveStraightForADistance(24, .8, true, true));
	
	//autonomousMode.addObject("3) Drive Through Rock Wall in Front (Distance)", )
	SmartDashboard.putData("Autonomous Selection", autonomousMode);
	
}
}
