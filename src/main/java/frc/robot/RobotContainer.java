/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.drive.DriveToDistanceCommand;
import frc.robot.commands.drive.RamseteTrajCommand;
import frc.robot.commands.drive.TTAProfiled;
import frc.robot.commands.drive.TurnToAngleCommand;
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
  private final Drivetrain dt = new Drivetrain();
  // private final ShooterWithSim shooter = new ShooterWithSim();
  // private final Lift lift = new LiftWithSim();

//  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  boolean slowMode = false;
  XboxController driverController = new XboxController(0);

  
 // s
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    //slowMode = richard.getLeftBumper();
    var slowButton = new JoystickButton(driverController, kLeftBumper.value);
    var shootButton = new JoystickButton(driverController, kA.value);
    var liftUpButton = new POVButton(driverController, 0);
    var liftDownButton = new POVButton(driverController, 180); 
    
    slowButton
      .whileHeld(new RunCommand(() -> dt.setOutput(0.2)))
      .whenReleased(new RunCommand(() -> dt.setOutput(1)));
      
    // shootButton
    //   .whileHeld(new RunCommand(() -> shooter.setPower(1)))
    //   .whenReleased(new RunCommand(() -> shooter.stop()));
    dt.setDefaultCommand(new RunCommand(() -> dt.arcadeDrive(-driverController.getLeftY(), driverController.getRightX()), dt));
    
    // lift.setDefaultCommand(new RunCommand(() -> lift.off(), lift));


    // liftUpButton
    //   .whileHeld(new RunCommand(() -> lift.on(1.0)))
    //   .or(liftDownButton.whileHeld(new RunCommand(() -> lift.on(-1.0))));
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
    // return new RamseteTrajCommand(dt);
    // return new DriveToDistanceCommand(1, dt);
    // return new TurnToAngleCommand(90, dt);
    return new TTAProfiled(90, dt);
  }

  public void disabledInit() {
    dt.resetOdometry(new Pose2d());
    dt.resetSensors();
  }
}
