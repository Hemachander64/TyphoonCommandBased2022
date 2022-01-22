/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drivetrain extends SubsystemBase
{
	// Create our motors

	CANSparkMax leftFront = new CANSparkMax(Constants.LF_MOTOR_ID, MotorType.kBrushless);
	CANSparkMax leftBack = new CANSparkMax(Constants.LB_MOTOR_ID, MotorType.kBrushless);
	CANSparkMax rightFront = new CANSparkMax(Constants.RF_MOTOR_ID, MotorType.kBrushless);
	CANSparkMax rightBack = new CANSparkMax(Constants.RB_MOTOR_ID, MotorType.kBrushless);

	RelativeEncoder lfEncoder = leftFront.getEncoder();
	RelativeEncoder lbEncoder = leftBack.getEncoder();
	RelativeEncoder rfEncoder = rightFront.getEncoder();
	RelativeEncoder rbEncoder = rightBack.getEncoder();

	MotorControllerGroup left = new MotorControllerGroup(leftFront, leftBack);
	MotorControllerGroup right = new MotorControllerGroup(rightFront, rightBack);;

	DifferentialDrive dt = new DifferentialDrive(left, right);

	// Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
	AHRS gyro = new AHRS(SPI.Port.kMXP);

	private static final double output = 1.0;

	public static final double kTrackwidthMeters = 0.69;
	public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(
			kTrackwidthMeters);

	private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(
			new Rotation2d(), new Pose2d());

	public Drivetrain()
	{
		lfEncoder.setPositionConversionFactor(0.4788 / 10.71);
		rfEncoder.setPositionConversionFactor(-0.4788 / 10.71);
		lfEncoder.setVelocityConversionFactor(0.4788 / 10.71);
		rfEncoder.setVelocityConversionFactor(-0.4788 / 10.71);

		lfEncoder.setPosition(0);
		lbEncoder.setPosition(0);
		rfEncoder.setPosition(0);
		rbEncoder.setPosition(0);

		leftFront.setIdleMode(IdleMode.kBrake);
		rightFront.setIdleMode(IdleMode.kBrake);
		leftBack.setIdleMode(IdleMode.kBrake);
		rightBack.setIdleMode(IdleMode.kBrake);

		leftFront.setInverted(false);
		leftBack.setInverted(false);
		rightFront.setInverted(true);
		rightBack.setInverted(true);

		leftFront.burnFlash();
		leftBack.burnFlash();
		rightFront.burnFlash();
		rightBack.burnFlash();
	}

	public void driveBoy(double xSpeed, double zRotation)
	{
		dt.arcadeDrive(output * xSpeed, output * zRotation);
	}

	public double getDistance()
	{
		// return 0;
		SmartDashboard.putNumber("position", lfEncoder.getPosition());
		return lfEncoder.getPosition();

	}

	public double getAngle()
	{
		return gyro.getAngle();
	}

	public void resetSensors()
	{
		resetEncoders();
		resetGyro();
	}

	public void setOutput(double x)
	{
		dt.setMaxOutput(x);
	}

	public void resetEncoders()
	{
		lfEncoder.setPosition(0);
		rfEncoder.setPosition(0);
	}

	public void resetGyro()
	{
		gyro.reset();
		gyro.calibrate();
	}

	@Override
	public void periodic()
	{
		// This method will be called once per scheduler run'
		SmartDashboard.putNumber("lTicks", lfEncoder.getPosition());
		SmartDashboard.putNumber("rTicks", rfEncoder.getPosition());

		// Print out the odometry to smartdashboard
		final var odometryPose = odometry.getPoseMeters();
		SmartDashboard.putNumber("odom x", odometryPose.getTranslation().getX());
		SmartDashboard.putNumber("odom y", odometryPose.getTranslation().getY());
		SmartDashboard.putNumber("odom heading", odometryPose.getRotation().getDegrees());
		SmartDashboard.putNumber("gyro raw angle", gyro.getAngle());

		odometry.update(gyro.getRotation2d(), lfEncoder.getPosition(), rfEncoder.getPosition());
	}

	public DifferentialDriveWheelSpeeds getWheelSpeeds()
	{
		return new DifferentialDriveWheelSpeeds(lfEncoder.getVelocity(), rfEncoder.getVelocity());
	}

	public Pose2d getPose()
	{
		return odometry.getPoseMeters();
	}

	/**
	 * Controls the left and right sides of the drive directly with voltages.
	 *
	 * @param leftVolts
	 *            the commanded left output
	 * @param rightVolts
	 *            the commanded right output
	 */
	public void tankDriveVolts(double leftVolts, double rightVolts)
	{
		left.setVoltage(-leftVolts);
		right.setVoltage(-rightVolts);
		dt.feed();
	}

	public void resetOdometry(Pose2d initialPose)
	{
		odometry.resetPosition(initialPose, new Rotation2d(gyro.getAngle()));
	}
}
