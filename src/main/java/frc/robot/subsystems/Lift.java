package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.RelativeEncoder;
import frc.robot.Constants;

public class Lift extends SubsystemBase 
{
    protected CANSparkMax leftLiftMotor = new CANSparkMax(Constants.LEFT_LIFT_MOTOR_ID, MotorType.kBrushed);
    protected CANSparkMax rightLiftMotor = new CANSparkMax(Constants.RIGHT_LIFT_MOTOR_ID, MotorType.kBrushed);
    
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
}

