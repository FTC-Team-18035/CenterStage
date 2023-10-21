package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Lift Test")
public class LiftEncoderTest extends LinearOpMode {

    static final double COUNTS_PER_REV = 223;
    static final double DRIVE_GEAR_REDUCTION = 1;
    static final double WHEEL_DIAMETER_INCHES = .34;
    static final double COUNTS_PER_INCH = (COUNTS_PER_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    @Override
    public void runOpMode(){
        DcMotor Lift1 = hardwareMap.dcMotor.get("Lift1");
        DcMotor Lift2 = hardwareMap.dcMotor.get("Lift2");

        Lift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()){
            if(gamepad1.a){
                Lift1.setTargetPosition(Lift1.getCurrentPosition() + 223);
                Lift2.setTargetPosition(Lift2.getCurrentPosition() + 223);
            }
            else if(gamepad1.b){
                Lift1.setTargetPosition(Lift1.getCurrentPosition() - 223);
                Lift2.setTargetPosition(Lift2.getCurrentPosition() - 223);
            }
            Lift1.setPower(0.3);
            Lift2.setPower(0.3);
            telemetry.addData("Left Lift Position", Lift1.getCurrentPosition());
            telemetry.addData("Right Lift Position", Lift2.getCurrentPosition());
            telemetry.update();
        }
    }
}
