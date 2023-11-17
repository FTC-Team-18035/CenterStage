package org.firstinspires.ftc.teamcode;

import com.qualcomm.ftccommon.configuration.EditLynxModuleActivity;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "ArmSwivelTest")
public class TEST_ArmSwivelTest extends LinearOpMode {
    private double liftPower = 0.7;
    private int LiftTarget = 0;
    ElapsedTime LiftTime = new ElapsedTime();
    ElapsedTime ArmRotationTime = new ElapsedTime();

    static final int MAX_LIFT_HEIGHT = 1100;

    @Override
    public void runOpMode(){
        DcMotor LeftLift = hardwareMap.dcMotor.get("Lift1");
        DcMotor RightLift = hardwareMap.dcMotor.get("Lift2");
        DcMotor ArmRotationMotor = hardwareMap.dcMotor.get("ArmRotationMotor");

        LeftLift.setDirection(DcMotorSimple.Direction.REVERSE);
        RightLift.setDirection(DcMotorSimple.Direction.REVERSE);
        ArmRotationMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        LeftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmRotationMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LeftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ArmRotationMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LeftLift.setTargetPosition(0);
        RightLift.setTargetPosition(0);
        ArmRotationMotor.setTargetPosition(0);

        LeftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ArmRotationMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();
        while(opModeIsActive()){
            if (gamepad2.y && LiftTime.seconds() > 1.0) {
                ArmRotationMotor.setTargetPosition(-100);
                LiftTarget = 1000;
                ArmRotationMotor.setTargetPosition(-100);
                LiftTime.reset();
                ArmRotationTime.reset();
                if(ArmRotationTime.seconds() > 5.0){
                    ArmRotationMotor.setTargetPosition(2000);
                }
            } else if (gamepad2.x && LiftTime.seconds() > 1.0) {
                LiftTarget = 500;
                ArmRotationMotor.setTargetPosition(-100);
                LiftTime.reset();
                ArmRotationTime.reset();
                if(ArmRotationTime.seconds() > 5.0){
                    ArmRotationMotor.setTargetPosition(2000);
                }
            } else if (gamepad2.a && LiftTime.seconds() > 1.0) {
                LiftTarget = 100;
                ArmRotationMotor.setTargetPosition(-100);
                LiftTime.reset();
                ArmRotationTime.reset();
                if(ArmRotationTime.seconds() > 5.0){
                    ArmRotationMotor.setTargetPosition(2000);
                }
            } else if (gamepad2.left_bumper && LiftTime.seconds() > 1.0){
                LiftTarget = 0;
                ArmRotationMotor.setTargetPosition(-100);
                LiftTime.reset();
                ArmRotationTime.reset();
                if(ArmRotationTime.seconds() > 5.0){
                    ArmRotationMotor.setTargetPosition(0);
                }
            }


                /*if((gamepad2.dpad_up) && (LiftTarget + 10) < MAX_LIFT_HEIGHT){
                    LiftTarget = LiftTarget + 1;
                }*/
            if (gamepad2.dpad_up && LeftLift.getCurrentPosition() < MAX_LIFT_HEIGHT - 10) {
                LiftTarget = LiftTarget + 10;
            }
            if (gamepad2.dpad_down && LeftLift.getCurrentPosition() > 10) {
                LiftTarget = LiftTarget - 10;
            }

            if (LiftTarget > LeftLift.getCurrentPosition()) {
                liftPower = 0.7;
            } else {
                liftPower = 0.3;
            }


            // issue lift power for movement
            if (LiftTarget < MAX_LIFT_HEIGHT) {
                RightLift.setTargetPosition(LiftTarget);
                LeftLift.setTargetPosition(LiftTarget);
                RightLift.setPower(liftPower);
                LeftLift.setPower(liftPower);
            }
        }
    }
}
