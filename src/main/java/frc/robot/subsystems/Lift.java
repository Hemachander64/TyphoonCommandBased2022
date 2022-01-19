package frc.robot.subsystems;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.RelativeEncoder;
import frc.robot.Constants;

public class Lift extends SubsystemBase 
{
    protected CANSparkMax liftMotor = new CANSparkMax(Constants.LIFT_MOTOR_ID, MotorType.kBrushless);
    protected RelativeEncoder liftEncoder = liftMotor.getEncoder();
    
    public Lift()
    {
        liftEncoder.setPositionConversionFactor(1); //change if needed
        liftEncoder.setPosition(0); 
        liftMotor.burnFlash();
    }

    public void on(double output)
    {
        liftMotor.set(output);
    }

    public void off()
    {
        liftMotor.set(0);
    }

    @Override
    public void periodic() 
    {
        SmartDashboard.putNumber("Lift Position: ", liftEncoder.getPosition());
    }
}

