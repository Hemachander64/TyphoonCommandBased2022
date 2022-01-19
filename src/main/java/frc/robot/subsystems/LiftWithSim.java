package frc.robot.subsystems;
import com.revrobotics.REVPhysicsSim;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;

public class LiftWithSim extends Lift {

    private ElevatorSim liftSim = new ElevatorSim(DCMotor.getNEO(1), 1, 0.1, 1, 0, 10);

    public LiftWithSim()
    {
        REVPhysicsSim.getInstance().addSparkMax(liftMotor, DCMotor.getNEO(1));
    }

    @Override
	public void simulationPeriodic()
	{
    // In this method, we update our simulation of what our elevator is doing
    // First, we set our "inputs" (voltages)
    liftSim.setInput(liftMotor.get() * RobotController.getBatteryVoltage());

    // Next, we update it. The standard loop time is 20ms.
    liftSim.update(0.020);

    // Finally, we set our simulated encoder's readings and simulated battery voltage
    liftEncoder.setPosition(liftSim.getPositionMeters());
    // SimBattery estimates loaded battery voltages
    RoboRioSim.setVInVoltage(
        BatterySim.calculateDefaultBatteryLoadedVoltage(liftSim.getCurrentDrawAmps()));
        
        
        // liftSim.update(0.02);


        // liftSim.setInputVoltage(liftMotor.getvo );
        // liftMotor.set()


        /*
		shootSim.setInputVoltage(rightShooter.getMotorOutputVoltage());
		
        
		RoboRioSim.setVInVoltage(
			BatterySim.calculateDefaultBatteryLoadedVoltage(shootSim.getCurrentDrawAmps()));
        */
	}
    
}
