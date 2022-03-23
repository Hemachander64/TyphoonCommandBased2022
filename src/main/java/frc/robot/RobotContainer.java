/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.*;
import frc.robot.commands.drive.*;
import frc.robot.commands.shoot.*;
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
  private final Hood hood = new Hood();
  private final Shooter shooter = new Shooter();
  private final Lift lift = new Lift();
  private final Drivetrain dt = new Drivetrain();
  private final Feeder feeder = new Feeder();
  private final Intake intake = new Intake();
  private final Traversal traversal = new Traversal();
 // private final Limelight ll = new Limelight();

  //Saitek flightStick = new Saitek(0);
  XboxController driverController = new XboxController(1);
  XboxController operatorController = new XboxController(2);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    // TODO: write terminal autos
    var rumbleOnCommand = new RunCommand(() -> operatorController.setRumble(RumbleType.kLeftRumble, 1.0)) // TODO: use this
        .alongWith(new RunCommand(() -> operatorController.setRumble(RumbleType.kRightRumble, 1.0)));

    var rumbleOffCommand = new RunCommand(() -> operatorController.setRumble(RumbleType.kLeftRumble, 0.0))
        .alongWith(new RunCommand(() -> operatorController.setRumble(RumbleType.kRightRumble, 0.0)));

    var slowButton = new JoystickButton(driverController, kLeftBumper.value)
      .or(new JoystickButton(driverController, kRightBumper.value));
    
    final double defenseTriggerSensitivity = 0.2;
    var defenseButton = new Trigger(()-> driverController.getLeftTriggerAxis() > defenseTriggerSensitivity 
      || driverController.getRightTriggerAxis() > defenseTriggerSensitivity);

    var shootButton = new JoystickButton(operatorController, kA.value);
    // var lowShotButton = new JoystickButton(operatorController, kB.value);
    var reverseShootButton = new JoystickButton(operatorController, kY.value);
    var liftUpButton = new POVButton(driverController, 0);
    var liftDownButton = new POVButton(driverController, 180);
    var traversalForwardsButton = new POVButton(driverController, 270);
    var traversalBackwardsButton = new POVButton(driverController, 90);
    var intakeButton = new JoystickButton(operatorController, kRightBumper.value);
    var outtakeButton = new JoystickButton(operatorController, kLeftBumper.value);
    var intakeTogglePneumaticsButton = new JoystickButton(operatorController, kX.value);
    var feedUpButton = new POVButton(operatorController, 0);
    var feedDownButton = new POVButton(operatorController, 180);
    // var resetLiftEncodersButton = new JoystickButton(driverController, kStart.value);
    // var switchLiftIdleModeButton = new JoystickButton(driverController, kBack.value);

    // var aimButton = new JoystickButton(driverController, kRightBumper.value);
    
    slowButton.whenActive(new InstantCommand(dt::slowMode).alongWith(new InstantCommand(traversal::slowMode)))
      .whenInactive(new InstantCommand(() -> dt.setOutput(1)).alongWith(new InstantCommand(traversal::fastMode)));

    defenseButton.whenActive(new InstantCommand(dt::evilMode)).whenInactive(new InstantCommand(dt::goodMode));

    shootButton.whileHeld(new ShootUpperHub(shooter, hood, feeder).andThen(rumbleOnCommand))
        .whenReleased(new InstantCommand(shooter::stop, shooter).alongWith(rumbleOffCommand));    
    // lowShotButton.whileHeld(new ShootLowHub(shooter, hood, feeder))
    //     .whenReleased(new InstantCommand(shooter::stop, shooter));
    reverseShootButton.whileHeld(new RunCommand(() -> shooter.setMotorVelocity(-1000), shooter))
        .whenReleased(new InstantCommand(shooter::stop, shooter));

    dt.setDefaultCommand(new RunCommand(() -> dt.arcadeDrive(-driverController.getLeftY(),
        driverController.getRightX()), dt));

    lift.setDefaultCommand(new RunCommand(() -> lift.off(), lift));

    liftUpButton.whileHeld(new RunCommand(() -> lift.on(0.5), lift))
        .or(liftDownButton.whileHeld(new RunCommand(() -> lift.on(-0.5), lift)))
        .whenInactive(new RunCommand(lift::off, lift));
    
    traversalForwardsButton.whileHeld(new RunCommand(traversal::forwards, traversal))
      .or(traversalBackwardsButton.whileHeld(new RunCommand(traversal::backwards, traversal)))
      .whenInactive(new RunCommand(traversal::off, traversal));

    intakeButton.whileHeld(new RunCommand(intake::on, intake))
        .or(outtakeButton.whileHeld(new RunCommand(intake::reverse, intake)))
        .whenInactive(new RunCommand(intake::off, intake));

    intakeTogglePneumaticsButton.whenPressed(new InstantCommand(intake::togglePneumatics));

    feeder.setDefaultCommand(new RunCommand(() -> feeder.off(), feeder));

    feedUpButton.whileHeld(new RunCommand(() -> feeder.on()))
        .or(feedDownButton.whileHeld(new RunCommand(() -> feeder.reverse())))
        .whenInactive(new RunCommand(feeder::off, feeder));

  //  aimButton.whileHeld(new AimCommand(dt, ll::getTx));
    // resetLiftEncodersButton.whenPressed(new InstantCommand(lift::resetEncoders, lift));
    // switchLiftIdleModeButton.whenHeld(new RunCommand(lift::coastMode, lift))
        // .whenReleased(new InstantCommand(lift::brakeMode, lift));

    chooser.setDefaultOption("TopBallAuto", new TopBallAuto(shooter, hood, feeder, intake, dt));
    chooser.addOption("MiddleBallAuto", new MiddleBallAuto(shooter, hood, feeder, intake, dt));
    chooser.addOption("LowBallAuto", new LowBallAuto(shooter, hood, feeder, intake, dt));
    chooser.addOption("No auto", new ShootLowHub(shooter, hood, feeder).andThen(new RunCommand(feeder::on, feeder).withTimeout(2)));
    chooser.addOption("No-ish auto", new ShootLowHub(shooter, hood, feeder).andThen(new RunCommand(feeder::on, feeder).withTimeout(2)).andThen(new DTDProfiled(1, dt)));
    chooser.addOption("Drive To Distance", new DriveToDistanceCommand(1, dt));
    chooser.addOption("Turn To Angle", new TurnToAngleCommand(90, dt));
    chooser.addOption("DTDProfiled", new DTDProfiled(1, dt));
    chooser.addOption("TTAProfiled", new TTAProfiled(90, dt));
    chooser.addOption("SquarePath", new SquareAuto(dt));
    chooser.addOption("Ramsete Trajectory Command", new RamseteTrajCommand(dt));
    chooser.addOption("SetWheelSpeeds", new RunCommand(() -> dt.setWheelSpeeds(1, 1), dt));

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
    shooter.stop();
    dt.resetEncoders();
    // dt.resetOdometry(new Pose2d()); // we aren't using odometry at waco
  }

}
