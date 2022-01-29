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

public class JoshTraj extends SequentialCommandGroup
{
	Trajectory traj = new Trajectory();

	public JoshTraj(Drivetrain dt)
	{       
		var autoVoltageConstraint =
			new DifferentialDriveVoltageConstraint(
				new SimpleMotorFeedforward(Constants.ksVolts, 
											Constants.kvVoltSecondsPerMeter),
				Drivetrain.kDriveKinematics,
				10);

		TrajectoryConfig config =
			new TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond,
								Constants.kMaxAccelerationMetersPerSecondSquared)
				.setKinematics(Drivetrain.kDriveKinematics)
				.addConstraint(autoVoltageConstraint);

		Trajectory traj = TrajectoryGenerator.generateTrajectory(
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			List.of(
				new Translation2d(0, 1.2)
			),
			new Pose2d(0, 2, Rotation2d.fromDegrees(0)),
			config
		);

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