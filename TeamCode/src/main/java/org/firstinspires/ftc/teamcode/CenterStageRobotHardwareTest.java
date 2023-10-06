package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "HardwareTesting")
public class CenterStageRobotHardwareTest extends LinearOpMode {

    CenterStageRobotHardware Robot = new CenterStageRobotHardware();
    @Override
    public void runOpMode() {
        Robot.Init(hardwareMap);
        waitForStart();

        if (opModeIsActive()){
            while(opModeIsActive()){
                Robot.Drive("Forward", 2);
            }
        }
    }

}
