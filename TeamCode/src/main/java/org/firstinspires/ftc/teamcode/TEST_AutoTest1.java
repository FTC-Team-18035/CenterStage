package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "AutonomousTest")
public class TEST_AutoTest1 extends LinearOpMode {
    private double power = 0.5;

    @Override
    public void runOpMode(){
        DcMotor Fleft = hardwareMap.dcMotor.get("Fleft");
        DcMotor Fright = hardwareMap.dcMotor.get("Fright");
        DcMotor Bleft = hardwareMap.dcMotor.get("Bleft");
        DcMotor Bright = hardwareMap.dcMotor.get("Bright");
        DcMotor IntakeMotor = hardwareMap.dcMotor.get("IntakeMotor");

        Fright.setDirection(DcMotorSimple.Direction.REVERSE);
        Bright.setDirection(DcMotorSimple.Direction.REVERSE);
        IntakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        Fleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Fright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Bleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Bright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Fleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Fright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Bleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Bright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Fleft.setTargetPosition(0);
        Fright.setTargetPosition(0);
        Bleft.setTargetPosition(0);
        Bright.setTargetPosition(0);

        Fleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Fright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Bleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Bright.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        if(opModeIsActive()){
            while(opModeIsActive()){
                Fleft.setTargetPosition(600);
                Fright.setTargetPosition(600);
                Bleft.setTargetPosition(600);
                Bright.setTargetPosition(600);

                Fleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                Fright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                Bleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                Bright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                break;
            }
        }
    }
}
