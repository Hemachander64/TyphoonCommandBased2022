package frc.robot.commands.drive;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class RamseteTrajCommand extends SequentialCommandGroup
{
	Trajectory traj = new Trajectory();

	public RamseteTrajCommand(Drivetrain dt)
	{       
		var autoVoltageConstraint =
			new DifferentialDriveVoltageConstraint(
				new SimpleMotorFeedforward(Constants.ksVolts, 
											Constants.kvVoltSecondsPerMeter),
				Drivetrain.kDriveKinematics,
				10);

		// Create config for trajectory
		TrajectoryConfig config =
			new TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond,
								Constants.kMaxAccelerationMetersPerSecondSquared)
				// Add kinematics to ensure max speed is actually obeyed
				.setKinematics(Drivetrain.kDriveKinematics)
				.addConstraint(autoVoltageConstraint);
				// .setReversed(true);

		// Trajectory traj = TrajectoryGenerator.generateTrajectory(
		// 	new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
		// 	List.of(
		// 		// new Pose2d(0, 1.2, Rotation2d.fromDegrees(0)),
		// 		// new Pose2d(1.2, 2.4, Rotation2d.fromDegrees(90)),
		// 		// new Pose2d(2.4, 1.2, Rotation2d.fromDegrees(180)),
		// 		// new Pose2d(2.4, 0, Rotation2d.fromDegrees(180)),
		// 		// new Pose2d(2.4, 0, Rotation2d.fromDegrees(0)),
		// 		// new Pose2d(2.4, 1.2, Rotation2d.fromDegrees(0)),
		// 		// new Pose2d(1.2, 2.4, Rotation2d.fromDegrees(-90)),
		// 		// new Pose2d(0, 1.2, Rotation2d.fromDegrees(-180)),
		// 		// new Pose2d(0, 0, Rotation2d.fromDegrees(-180)),
		// 		// new Pose2d(0, 0, Rotation2d.fromDegrees(-90)),
		// 		// new Pose2d(-2.5, 0, Rotation2d.fromDegrees(-90)),
		// 		// new Pose2d(0, 0, Rotation2d.fromDegrees(-90))
		// 		new Translation2d(0, 1.2),
		// 		new Translation2d(1.2, 2.4),
		// 		new Translation2d(2.4, 1.2),
		// 		new Translation2d(2.4, 0),
		// 		new Translation2d(2.4, 0),
		// 		new Translation2d(2.4, 1.2),
		// 		new Translation2d(1.2, 2.4),
		// 		new Translation2d(0, 1.2),
		// 		new Translation2d(0, 0),
		// 		new Translation2d(-2.5, 0),
		// 		new Translation2d(0, 0)
		// 	),
		// 	new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
		// 	config
		// );
		String trajectoryJSON = "output/path1.wpilib.json";

		try {
			Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
			traj = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
		} catch (IOException ex) {
			DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
		}

		RamseteCommand ramseteCommand =
			new RamseteCommand(
				traj,
				dt::getPose,
				new RamseteController(),
				new SimpleMotorFeedforward(
					Constants.ksVolts,
					Constants.kvVoltSecondsPerMeter,
					Constants.kaVoltSecondsSquaredPerMeter),
				Drivetrain.kDriveKinematics,
				dt::getWheelSpeeds,
				new PIDController(Constants.kPDriveVel, 0, 0),
				new PIDController(Constants.kPDriveVel, 0, 0),
				// RamseteCommand passes volts to the callback
				dt::tankDriveVolts,
				dt
		);

		addCommands
		(
			new PrintCommand("Ramsete Trajectory"),
			new InstantCommand(() -> dt.resetOdometry(traj.getInitialPose()), dt),
			new PrintCommand("Odometry reset"),
			new InstantCommand(() -> dt.resetSensors(), dt),
			new PrintCommand("Sensors reset"),
			ramseteCommand,
			new InstantCommand(() -> dt.tankDriveVolts(0, 0), dt),
			new PrintCommand("\n\n\n\n\nTRAJ DONE\n\n\n\n\n")
		);
	}
}