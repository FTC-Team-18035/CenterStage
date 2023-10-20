package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


// Please add REM statements to the code blocks - it will give clarity to me and YOU as to what you are doing or attempting to do with each statement and method call
// Sorry to keep beating the same drum, but I think it will be clear to you what is NOT happening when you state what is intended to happen :)
// It's standard coding procedure for newbies and veterans for a good reason....


@TeleOp(name = "3 Button Claw")
public class ThreeButtonClawTest extends LinearOpMode {

    boolean isClosed1 = false;
    boolean isClosed2 = false;
    ElapsedTime close1 = new ElapsedTime();
    ElapsedTime close2 = new ElapsedTime();
    ElapsedTime DoubleClose = new ElapsedTime();

    @Override
    public void runOpMode(){

        Servo claw1 = hardwareMap.servo.get("Claw1");
        Servo claw2 = hardwareMap.servo.get("Claw2");


        claw1.setPosition(0);
        claw2.setPosition(0);
        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.a && !isClosed1){
                claw1.setPosition(1);
                isClosed1 = true;
                close1.startTime();
            }
            else if(gamepad1.a && isClosed1 && close1.milliseconds() >= 300){
                claw1.setPosition(0);
                isClosed1 = false;
                close1.reset();
            }
            if(gamepad1.b && !isClosed2){
                claw2.setPosition(1);
                isClosed2 = true;
                close2.startTime();
            }
            else if(gamepad1.b && isClosed2 && close2.milliseconds() >= 300){
                claw2.setPosition(0);
                isClosed2 = false;
                close2.reset();
            }
            if(gamepad1.x && !isClosed1 || !isClosed2){
                claw1.setPosition(1);
                claw2.setPosition(1);
                isClosed1 = true;
                isClosed2 = true;
                DoubleClose.startTime();
            }
            else if(gamepad1.x && isClosed1 || isClosed2 && DoubleClose.milliseconds() >= 300){
                claw1.setPosition(0);
                claw2.setPosition(0);
                isClosed1 = false;
                isClosed2 = false;
                DoubleClose.reset();
            }
            telemetry.addData("Close time 1", close1.milliseconds());
            telemetry.addData("Close time 2", close2.milliseconds());
            telemetry.addData("Double Close Time", DoubleClose.milliseconds());
            telemetry.update();
        }
    }
}
