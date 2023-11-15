package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        @Disabled        //We set the type of file this is plus name it
@Autonomous(name = "HardwareTesting")
public class CenterStageRobotHardwareTest extends LinearOpMode {

    //This creates a Instance of CenterStageRobotHardware and stores it to a variable. In this case we save it to "Robot"
    CenterStageRobotHardware Robot = new CenterStageRobotHardware();
    @Override
    public void runOpMode() {
        //Since Robot is the Instance of CenterStageRobotHardware we have to initialize it to initialize all the motors
        Robot.Init(hardwareMap);
        waitForStart();

        //This checks to see if the OpMode is running and if so runs the code inside of it
        if (opModeIsActive()){
            //This will run anything in the curly brackets while the condition is true.
            //In this case we already know that it is true when it enters. And then it remains true until we press stop
            while(opModeIsActive()){
                //This is calling the Drive function in CenterStageRobotHardware. So we give it a direction and the distance to travel
                Robot.Drive("Forward", 2);
            }
        }
    }

}
