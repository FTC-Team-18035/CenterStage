package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@TeleOp(name = "Ehub Test")
public class ExpansionHubTest extends LinearOpMode {
    private double LiftPower = 0.7;
    @Override
    public void runOpMode(){

        Servo Claw = hardwareMap.servo.get("Claw");
        DcMotor lift1 = hardwareMap.dcMotor.get("Lift1");
        DcMotor lift2 = hardwareMap.dcMotor.get("Lift2");

        lift1.setDirection(DcMotorSimple.Direction.REVERSE);
        lift2.setDirection(DcMotorSimple.Direction.REVERSE);

        lift1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.a){//This is checking if the "a" button has been pressed
                lift1.setPower(LiftPower);//This is setting lift1 power to LiftPower or 0.5
                lift2.setPower(LiftPower);//This is setting lift2 power to LiftPower or 0.5
            }
            else if(gamepad1.b){//This is checking if the "b" button has been pressed
                lift1.setPower(-LiftPower);//This is setting lift1 power to - LiftPower or -0.5
                lift2.setPower(-LiftPower);//This is setting lift2 power to - LiftPower or -0.5
            }
            else{//If neither the "a" button or "b" button have been pressed this runs
                lift1.setPower(0);//This sets lift1 power to 0 and stopping it
                lift2.setPower(0);//This sets lift2 power to 0 and stopping it
            }
            if(gamepad1.right_bumper){
                Claw.setPosition(0);
            }
            else if(gamepad1.left_bumper){
                Claw.setPosition(1);
            }
        }
    }
}

