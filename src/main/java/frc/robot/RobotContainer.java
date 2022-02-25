/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.*;
import frc.robot.commands.drive.*;
import frc.robot.commands.shoot.*;
import frc.robot.subsystems.*;
import static edu.wpi.first.wpilibj.XboxController.Button.*;

import edu.wpi.first.math.geometry.Pose2d;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain dt = new DrivetrainWithSim();
  private final SendableChooser<Command> chooser = new SendableChooser<Command>();
  private final Hood hood = new Hood();
  // private final Shooter shooter = new ShooterWithSim();
  private final Lift lift = new Lift();
  
  private final Limelight ll = new Limelight();
  private final Feeder feeder = new Feeder();

//  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  XboxController driverController = new XboxController(1);
  Saitek flightStick = new Saitek(0);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    
    var slowButton = new JoystickButton(driverController, kLeftBumper.value).or(new JoystickButton(flightStick, Saitek.SButtons.kTrigger.ordinal()));
    var defenseButton = new JoystickButton(driverController, kRightBumper.value).or(new JoystickButton(flightStick, Saitek.SButtons.kM.ordinal()));
    
    var shootButton = new JoystickButton(driverController, kA.value);
    var liftUpButton = new POVButton(driverController, 0);
    var liftDownButton = new POVButton(driverController, 180); 
    
     slowButton
       .whenActive(new InstantCommand(dt::slowMode))
       .whenInactive(new InstantCommand(() -> dt.setOutput(1)));

     defenseButton
       .whenActive(new InstantCommand(dt::evilMode))
       .whenInactive(new InstantCommand(dt::goodMode));
      
    
      
    // shootButton.whenPressed(new ShootCommand(ll, shooter, hood, feeder));//, dt));

      dt.setDefaultCommand(new RunCommand(() -> dt.arcadeDrive(-flightStick.getY()-driverController.getLeftY(), flightStick.getZRotation()+driverController.getRightX()), dt));



      lift.setDefaultCommand(new RunCommand(() -> lift.off(), lift));

      liftUpButton
        .whileHeld(new RunCommand(() -> lift.on(0.5)))
        .or(liftDownButton.whileHeld(new RunCommand(() -> lift.on(-0.5))));
    
     chooser.setDefaultOption("Drive To Distance", new DriveToDistanceCommand(1, dt));
     chooser.addOption("Turn To Angle", new TurnToAngleCommand(90, dt));
     chooser.addOption("TTAProfiled", new TTAProfiled(90, dt));
     chooser.addOption("Ramsete Trajectory Command", new RamseteTrajCommand(dt));
     chooser.addOption("DTDProfiled", new DTDProfiled(1, dt));
     chooser.addOption("SquarePath", new SquareAuto(dt));
     chooser.addOption("TopBallAuto", new TopBallAuto(dt));
     chooser.addOption("MiddleBallAuto", new MiddleBallAuto(dt));
     chooser.addOption("LowBallAuto", new LowBallAuto(dt));
     chooser.addOption("SetWheelSpeeds", new RunCommand(() -> dt.setWheelSpeeds(1,1), dt));

    SmartDashboard.putData(chooser);
  }

  

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }

  public void disabledInit() {
     dt.resetEncoders();
     dt.resetOdometry(new Pose2d());
  }

  
}
