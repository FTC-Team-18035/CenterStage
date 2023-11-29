package org.firstinspires.ftc.teamcode;

import com.qualcomm.ftccommon.configuration.EditLynxModuleActivity;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
@Disabled
@TeleOp(name = "ArmSwivelTest")
public class TEST_ArmSwivelTest extends LinearOpMode {
    private double liftPower = 0.7;
    private int LiftTarget = 0;
    ElapsedTime LiftTime = new ElapsedTime();
    ElapsedTime ArmRotationTime = new ElapsedTime();

    static final int MAX_LIFT_HEIGHT = 4285;

    @Override
    public void runOpMode(){
        DcMotor LeftLiftMotor = hardwareMap.dcMotor.get("Lift1");
        DcMotor RightLiftMotor = hardwareMap.dcMotor.get("Lift2");
        DcMotor ArmRotationMotor = hardwareMap.dcMotor.get("ArmRotationMotor");

        RightLiftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        LeftLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmRotationMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LeftLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ArmRotationMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LeftLiftMotor.setTargetPosition(0);
        RightLiftMotor.setTargetPosition(0);
        ArmRotationMotor.setTargetPosition(0);

        LeftLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RightLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ArmRotationMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();
        while(opModeIsActive()){
            if (gamepad2.y && LiftTime.seconds() > 1.0) {
                LiftTarget = 4280;
                ArmRotationMotor.setTargetPosition(-138);
                LiftTime.reset();
                ArmRotationTime.reset();
                if(ArmRotationTime.seconds() > 0.5){
                    ArmRotationMotor.setTargetPosition(2600);
                }
            } else if (gamepad2.x && LiftTime.seconds() > 1.0) {
                LiftTarget = 3000;
                ArmRotationMotor.setTargetPosition(-138);
                LiftTime.reset();
                ArmRotationTime.reset();
                if(ArmRotationTime.seconds() > 2.0){
                    ArmRotationMotor.setTargetPosition(2600);
                }
            } else if (gamepad2.a && LiftTime.seconds() > 1.0) {
                LeftLiftMotor.setTargetPosition(2000);
                RightLiftMotor.setTargetPosition(2000);
                LeftLiftMotor.setPower(0.7);
                RightLiftMotor.setPower(0.7);
                sleep(30);
                //LiftTarget = 2000;
                ArmRotationMotor.setTargetPosition(-138);
                //LiftTime.reset();
                //ArmRotationTime.reset();
                sleep(2000);
                ArmRotationMotor.setTargetPosition(880);

            } else if (gamepad2.left_bumper && LiftTime.seconds() > 1.0){
                LiftTarget = 0;
                ArmRotationMotor.setTargetPosition(-138);
                LiftTime.reset();
                ArmRotationTime.reset();
                if(ArmRotationTime.seconds() > 2.0){
                    ArmRotationMotor.setTargetPosition(0);
                }
            }


                /*if((gamepad2.dpad_up) && (LiftTarget + 10) < MAX_LIFT_HEIGHT){
                    LiftTarget = LiftTarget + 1;
                }*/
            if (gamepad2.dpad_up && LeftLiftMotor.getCurrentPosition() < MAX_LIFT_HEIGHT - 10) {
                LiftTarget = LiftTarget + 10;
            }
            if (gamepad2.dpad_down && LeftLiftMotor.getCurrentPosition() > 10) {
                LiftTarget = LiftTarget - 10;
            }

            if (LiftTarget > LeftLiftMotor.getCurrentPosition()) {
                liftPower = 0.7;
            } else {
                liftPower = 0.3;
            }


            // issue lift power for movement
        /*    if (LiftTarget < MAX_LIFT_HEIGHT) {
                RightLiftMotor.setTargetPosition(LiftTarget);
                LeftLiftMotor.setTargetPosition(LiftTarget);
                RightLiftMotor.setPower(liftPower);
                LeftLiftMotor.setPower(liftPower);
         }*/
            ArmRotationMotor.setPower(0.5);
            telemetry.addData("Rotation Time", ArmRotationTime);
            telemetry.update();
        }
    }
}
