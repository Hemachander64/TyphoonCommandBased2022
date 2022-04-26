/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.*;
// import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
// import frc.robot.commands.*;
import frc.robot.subsystems.*;
import static edu.wpi.first.wpilibj.XboxController.Button.*;

// import edu.wpi.first.math.geometry.Pose2d;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
  // The robot's subsystems and commands are defined here...
  private final SendableChooser<Command> chooser = new SendableChooser<Command>();
  private final Drivetrain dt = new Drivetrain();

  XboxController driverController = new XboxController(1);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    var slowButton = new JoystickButton(driverController, kLeftBumper.value);
    var defenseButton = new JoystickButton(driverController, kB.value);
    
    slowButton.whenActive(new InstantCommand(dt::slowMode)).whenInactive(new InstantCommand(() -> dt.setOutput(1)));

    defenseButton.whenActive(new InstantCommand(dt::evilMode)).whenInactive(new InstantCommand(dt::goodMode));

    dt.setDefaultCommand(new RunCommand(() -> dt.arcadeDrive(driverController.getLeftY(), driverController.getRightX()), dt));

    // chooser.addOption("SetWheelSpeeds", new RunCommand(() -> dt.setWheelSpeeds(1, 1), dt));

    SmartDashboard.putData(chooser);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    return chooser.getSelected();
  }

  public void disabledInit()
  {
    dt.resetEncoders();
    // dt.resetOdometry(new Pose2d()); // we aren't using odometry at waco
  }

}
