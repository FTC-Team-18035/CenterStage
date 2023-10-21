package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Ehub Test")
public class ExpansionHubTest extends LinearOpMode {
    @Override
    public void runOpMode(){

        Servo Claw = hardwareMap.servo.get("Claw");

        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.a){
                Claw.setPosition(0);
            }
            else if(gamepad1.b){
                Claw.setPosition(1);
            }
        }
    }
}
