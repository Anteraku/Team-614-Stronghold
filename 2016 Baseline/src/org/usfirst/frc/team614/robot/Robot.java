
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
import org.usfirst.frc.team614.robot.commands.drivetrain.DriveStraightForATime;
import org.usfirst.frc.team614.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team614.robot.commands.drivetrain.TurnToAngle;
import org.usfirst.frc.team614.robot.commands.shooter.ShootSequence; 
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
   
    CameraFeeds cameraFeeds = new CameraFeeds(OI.driverGamepad);
    
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
		
		
		oi = new OI();
		
		//frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		
		//the camera name (ex "cam0") can be found through the roborio web interface
		//session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		//NIVision.IMAQdxConfigureGrab(session);
		
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(shooter);
		
        autonomousMode = new SendableChooser();
        addAutonomousModes();
        
      CameraServer.getInstance().startAutomaticCapture();
  
      
        
               
//     
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
//    	cameraFeeds.end();
//    	cameraFeeds.init();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
//	cameraFeeds.run();
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
        
//		 String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
//		switch(autoSelected) {
//		case "My Auto":
//			autonomousCommand = new TurnToAngle(90,.5);
//			break;
//		case "Default Auto":
//		default:
//			autonomousCommand = new DoNothing();
//			break;
//		} 
    	
    	// schedule the autonomous command (example)
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
    	}
    }
    /*
    public void cameraStuff() {
        NIVision.IMAQdxStartAcquisition(session);

     
      //    grab an image, draw the circle, and provide it for the camera server
        //  which will in turn send it to the dashboard.
         
        NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);

        while (isOperatorControl() && isEnabled()) {

            NIVision.IMAQdxGrab(session, frame, 1);
            NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                    DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
            
            CameraServer.getInstance().setImage(frame);

            
            Timer.delay(0.005);		// wait for a motor update time
        }
        NIVision.IMAQdxStopAcquisition(session);
    }*/
    
  private void addAutonomousModes() {
	autonomousMode.addObject("1) Do Nothing", new DoNothing());
	autonomousMode.addDefault("2) Drive For Time", new DriveStraightForATime(1.0, 1.0, true));
	autonomousMode.addObject("3) Drive For Time 2", new DriveStraightForATime(1.0, 1.0, true));
	autonomousMode.addObject("4) TEDOut", new TEDOut(7.));
	SmartDashboard.putData("Autonomous Selection", autonomousMode);
}
}
