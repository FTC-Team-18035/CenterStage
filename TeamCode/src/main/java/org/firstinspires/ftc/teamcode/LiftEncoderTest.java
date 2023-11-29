package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@Disabled
@TeleOp(name = "Lift  Encoder Test")
public class LiftEncoderTest extends LinearOpMode {

    private double liftPower = 0.7;

    @Override
    public void runOpMode(){
        DcMotor Lift1 = hardwareMap.dcMotor.get("Lift1");
        DcMotor Lift2 = hardwareMap.dcMotor.get("Lift2");

        Lift1.setDirection(DcMotorSimple.Direction.REVERSE);
        Lift2.setDirection(DcMotorSimple.Direction.REVERSE);

        Lift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Lift1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()){
            if(gamepad1.a){
               Lift1.setPower(liftPower);
               Lift2.setPower(liftPower);
            }
            else if(gamepad1.b){
                Lift1.setPower(-liftPower);
                Lift2.setPower(-liftPower);
            }
            telemetry.addData("Left Lift Position", Lift1.getCurrentPosition());
            telemetry.addData("Right Lift Position", Lift2.getCurrentPosition());
            telemetry.update();
        }
    }
}
