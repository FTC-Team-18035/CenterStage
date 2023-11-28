package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
@TeleOp(name = "DroneTest")
public class TEST_Drone extends LinearOpMode {

    private ElapsedTime DroneTime = new ElapsedTime();
    private boolean BeganPressed = false;
    @Override
    public void runOpMode() throws InterruptedException {
        Servo Drone = hardwareMap.servo.get("Drone");
        waitForStart();
        while (opModeIsActive()){
            if(gamepad1.right_trigger == 1 && gamepad2.right_trigger == 1){
                Drone.setPosition(0);
            }
                /*DroneTime.reset();
                BeganPressed = true;
            }
            if(!gamepad1.b && DroneTime.seconds() > 3.0 && BeganPressed == true){

                telemetry.addData("Hi", Drone.getPosition());
            }
            else{BeganPressed = false;}
            telemetry.addData("Drone Time", DroneTime);
            telemetry.addData("Started Pressing", BeganPressed);
            telemetry.update();*/
        }
    }
}
