package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@Disabled
@TeleOp(name = "SwivelMotorTest")
public class TEST_Swivel extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor ArmRotationMotor = hardwareMap.dcMotor.get("ArmRotationMotor");

        ArmRotationMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        ArmRotationMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ArmRotationMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmRotationMotor.setTargetPosition(0);
        ArmRotationMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.a){
                ArmRotationMotor.setTargetPosition(5281);
                ArmRotationMotor.setPower(1);
            }
            if(gamepad1.b){
                ArmRotationMotor.setTargetPosition(0);
                ArmRotationMotor.setPower(1);
            }
            telemetry.addData("ArmRotationEncoder", ArmRotationMotor.getCurrentPosition());
            telemetry.update();

        }

    }
}
