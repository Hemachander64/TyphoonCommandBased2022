package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hood;

public class SetHoodAngleCommand extends CommandBase
{
    private Hood hood;
    private double angle;

    public SetHoodAngleCommand (double angle, Hood hood)
    {
        this.hood = hood;
		this.angle = angle;
    }


	@Override
	public void execute() {
		hood.setHoodAngle(angle);
	}


	@Override
	public boolean isFinished() {
		return hood.atSetpoint();
	}
}