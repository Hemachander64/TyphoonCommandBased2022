// package frc.robot.subsystems;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants;

// public class Traversal extends SubsystemBase
// {
//     protected CANSparkMax leftTraversalMotor = new CANSparkMax(Constants.LEFT_TRAVERSAL_MOTOR_ID, MotorType.kBrushless);
//     protected CANSparkMax rightTraversalMotor = new CANSparkMax(Constants.RIGHT_TRAVERSAL_MOTOR_ID, MotorType.kBrushless);

// 	private double kForwardsSpeed = 0.25;
// 	private double kBackwardsSpeed = -0.25;
// 	public Traversal()
// 	{
//         leftTraversalMotor.restoreFactoryDefaults();
//         rightTraversalMotor.restoreFactoryDefaults();
				
//         leftTraversalMotor.setInverted(false);
//         rightTraversalMotor.setInverted(true);
		
//         leftTraversalMotor.setIdleMode(IdleMode.kBrake);
//         rightTraversalMotor.setIdleMode(IdleMode.kBrake);

//         leftTraversalMotor.burnFlash();
//         rightTraversalMotor.burnFlash();
// 	}

// 	public void forwards()
// 	{
// 		leftTraversalMotor.set(kForwardsSpeed);
// 		rightTraversalMotor.set(kForwardsSpeed);
// 	}

// 	public void backwards()
// 	{
// 		leftTraversalMotor.set(kBackwardsSpeed);
// 		rightTraversalMotor.set(kBackwardsSpeed);
// 	}

// 	public void off()
// 	{
// 		leftTraversalMotor.set(0);
// 		rightTraversalMotor.set(0);
// 	}

// 	public void slowMode()
// 	{
// 		kForwardsSpeed = 0.25;
// 		kBackwardsSpeed = -0.25;
// 	}

// 	public void fastMode()
// 	{
// 		kForwardsSpeed = 1;
// 		kBackwardsSpeed = -1;
// 	}
// }
