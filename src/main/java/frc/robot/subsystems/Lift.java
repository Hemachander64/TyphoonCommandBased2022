package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class Lift extends SubsystemBase
{
    protected CANSparkMax leftLiftMotor = new CANSparkMax(Constants.LEFT_LIFT_MOTOR_ID, MotorType.kBrushless);
    protected CANSparkMax rightLiftMotor = new CANSparkMax(Constants.RIGHT_LIFT_MOTOR_ID, MotorType.kBrushless);

    public Lift()
    {
        leftLiftMotor.restoreFactoryDefaults();
        rightLiftMotor.restoreFactoryDefaults();

        resetEncoders();

        leftLiftMotor.setInverted(false);
        rightLiftMotor.setInverted(true);

        // leftLiftMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
        // leftLiftMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
        // rightLiftMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
        // rightLiftMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

        // leftLiftMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, -1);
        // leftLiftMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, 205);
        // rightLiftMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, -1);
        // rightLiftMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, 205);

        leftLiftMotor.setIdleMode(IdleMode.kBrake);
        rightLiftMotor.setIdleMode(IdleMode.kBrake);

        leftLiftMotor.burnFlash();
        rightLiftMotor.burnFlash();
    }

    public void on(double output)
    {
        leftLiftMotor.set(output);
        rightLiftMotor.set(output);
    }

    public void off()
    {
        leftLiftMotor.set(0);
        rightLiftMotor.set(0);
    }

    @Override
    public void periodic()
    {
        SmartDashboard.putNumber("right liftTicks", rightLiftMotor.getEncoder().getPosition());
        SmartDashboard.putNumber("left liftTicks", leftLiftMotor.getEncoder().getPosition());

    }

    public void resetEncoders()
    {
        rightLiftMotor.getEncoder().setPosition(0);
        leftLiftMotor.getEncoder().setPosition(0);
    }

    public void coastMode()
    {
        leftLiftMotor.setIdleMode(IdleMode.kCoast);
        rightLiftMotor.setIdleMode(IdleMode.kCoast);
    }

    
    public void brakeMode()
    {
        leftLiftMotor.setIdleMode(IdleMode.kBrake);
        rightLiftMotor.setIdleMode(IdleMode.kBrake);
    }
}
