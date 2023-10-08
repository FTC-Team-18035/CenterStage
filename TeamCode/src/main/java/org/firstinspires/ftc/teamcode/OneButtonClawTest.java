package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "1 Button Test")
public class OneButtonClawTest extends LinearOpMode {
    int TimesPressed = 0;
    ElapsedTime ClawTime = new ElapsedTime();

    @Override
    public void runOpMode(){
        Servo claw1 = hardwareMap.servo.get("Claw1");
        Servo claw2 = hardwareMap.servo.get("Claw2");

        claw1.setPosition(0);
        claw2.setPosition(0);
        waitForStart();

        while(opModeIsActive()){
            switch(TimesPressed){
                case 0:
                    if(gamepad1.a){
                        ClawTime.startTime();
                        claw1.setPosition(1);
                        TimesPressed++;
                    }
                case 1:
                    if(gamepad1.a && ClawTime.milliseconds() >= 300){
                        ClawTime.reset();
                        claw1.setPosition(0);
                        TimesPressed++;
                    }
                case 2:
                    if(gamepad1.a){
                        ClawTime.startTime();
                        claw2.setPosition(1);
                        TimesPressed++;
                    }
                case 3:
                    if(gamepad1.a && ClawTime.milliseconds() >= 300){
                        ClawTime.reset();
                        claw2.setPosition(0);
                        TimesPressed++;
                    }
                case 4:
                    if(gamepad1.a){
                        ClawTime.startTime();
                        claw1.setPosition(1);
                        claw2.setPosition(1);
                        TimesPressed++;
                    }
                case 5:
                    if(gamepad1.a && ClawTime.milliseconds() >= 300){
                        ClawTime.reset();
                        claw1.setPosition(0);
                        claw2.setPosition(0);
                        TimesPressed = 0;
                    }
                }

            telemetry.addData("Claw Coodown", ClawTime.milliseconds());
            telemetry.addData("Times Pressed", TimesPressed);
            telemetry.update();

            }
        }
    }
