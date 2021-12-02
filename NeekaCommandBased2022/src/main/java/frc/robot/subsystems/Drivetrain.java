import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.SPI;

public class Drivetrain extends SubsystemBase {
    CANSparkMax lf = new CANSparkMax(Constants.LEFT_FRONT_ID, MotorType.kBrushless);
    CANSparkMax lb = new CANSparkMax(Constants.LEFT_BACK_ID, MotorType.kBrushless);
    CANSparkMax rf = new CANSparkMax(Constants.RIGHT_FRONT_ID, MotorType.kBrushless);
    CANSparkMax rb = new CANSparkMax(Constants.RIGHT_BACK_ID, MotorType.kBrushless);

    CANEncoder lfEncoder = lf.getEncoder();
    CANEncoder lbEncoder = lb.getEncoder();
    CANEncoder rfEncoder = rf.getEncoder();
    CANEncoder rbEncoder = rb.getEncoder();

    SpeedControllerGroup Left = new SpeedControllerGroup(lf, lb);
    SpeedControllerGroup Right = new SpeedControllerGroup(rf,rb); 
    DifferentialDrive dt = new DifferentialDrive(Left, Right);

    Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
    
    public Drivetrain()
    {
        rfEncoder.setPositionConversionFactor(0.0454);
        rbEncoder.setPositionConversionFactor(0.0454);
        lfEncoder.setPositionConversionFactor(0.0454);
        lbEncoder.setPositionConversionFactor(0.0454);
        
        lb.setIdleMode(IdleMode.kCoast);
		rf.setIdleMode(IdleMode.kCoast);
		rb.setIdleMode(IdleMode.kCoast);
		lf.setIdleMode(IdleMode.kCoast);

		lf.burnFlash();
		lb.burnFlash();
		rf.burnFlash();
		rb.burnFlash();
    }

    public void driveBro(double xSpeed, double zRotation){
        dt.arcadeDrive(xSpeed, zRotation);
    }
    public double getDistance()
	{
		return (lfEncoder.getPosition() + rfEncoder.getPosition()) / 2;
    }
    
    public double getAngle()
    {
        return gyro.getAngle();
    }

    public void resetEncoders() {
        lfEncoder.setPosition(0);
        rfEncoder.setPosition(0);
    }
    public void resetGyro() {
        gyro.reset();
		gyro.calibrate();
    }
    public void resetSensors() {
        resetEncoders();
        resetGyro();
    }
}

