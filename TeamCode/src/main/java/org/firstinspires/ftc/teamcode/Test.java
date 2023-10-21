package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Test Motors")//This line declares this file as a TeleOp and sets the names to "Test Motors"
public class Test extends LinearOpMode {

    //Declare motors here
    private double LiftPower = 0.7;//This is to make sure the 2 lift motors will always run at the same speed
    @Override
    public void runOpMode(){
        //We declare motors/servos here. We also insert the names of the motors that are configured in the driver station
        DcMotor lift1 = hardwareMap.dcMotor.get("Lift1");
        DcMotor lift2 = hardwareMap.dcMotor.get("Lift2");
        Servo claw = hardwareMap.servo.get("Claw");
        Servo drone = hardwareMap.servo.get("Drone");

        lift1.setDirection(DcMotorSimple.Direction.REVERSE);
        lift2.setDirection(DcMotorSimple.Direction.REVERSE);


        lift1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        //Everything that is in this while loop runs until you hit stop on the driver station
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
            if(gamepad1.left_bumper){//This checks if the left bumper has been pressed
                claw.setPosition(0);//This is opening the claw
            }
            else if(gamepad1.right_bumper){//This checks if the right bumper has been pressed
                claw.setPosition(1);//This is closing the claw
            }
            if(gamepad1.dpad_left){//This checks if the left dpad button has been pressed
                drone.setPosition(0);//This activates the drone
            }
            else if(gamepad1.dpad_right){//This checks if the right dpad button has been pressed
                drone.setPosition(1);//This resets the drone
            }

            telemetry.addData("Lift1 power", lift1.getPower());
            telemetry.addData("lift2 power", lift2.getPower());
            telemetry.update();
        }
    }
}
