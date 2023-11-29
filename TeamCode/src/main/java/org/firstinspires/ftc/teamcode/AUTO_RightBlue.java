package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AUTO_RightBlue extends LinearOpMode {
    @Override
    public void runOpMode(){
        DcMotor Fleft = hardwareMap.dcMotor.get("Fleft");
        DcMotor Fright = hardwareMap.dcMotor.get("Fright");
        DcMotor Bright = hardwareMap.dcMotor.get("Bright");
        DcMotor Bleft = hardwareMap.dcMotor.get("Bleft");

        Servo Claw1 = hardwareMap.servo.get("Claw1");
        Servo Claw2 = hardwareMap.servo.get("Claw2");

        DcMotor LeftLiftMotor = hardwareMap.dcMotor.get("Lift1");
        DcMotor RightLiftMotor = hardwareMap.dcMotor.get("Lift2");
        DcMotor ArmRotationMotor = hardwareMap.dcMotor.get("ArmRotationMotor");

        DcMotor IntakeMotor = hardwareMap.dcMotor.get("IntakeMotor");

        IntakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        RightLiftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        Fright.setDirection(DcMotorSimple.Direction.REVERSE);
        Bleft.setDirection(DcMotorSimple.Direction.REVERSE);


        RightLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmRotationMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Fleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Fright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Bright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Bleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LeftLiftMotor.setTargetPosition(10);
        RightLiftMotor.setTargetPosition(10);
        ArmRotationMotor.setTargetPosition(0);
        Fleft.setTargetPosition(0);
        Fright.setTargetPosition(0);
        Bright.setTargetPosition(0);
        Bleft.setTargetPosition(0);

        LeftLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RightLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ArmRotationMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Fleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Fright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Bright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Bleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set motor zero power behavior

        Fleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Fright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Bleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Bright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LeftLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ArmRotationMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Claw1.setPosition(1);
        Claw2.setPosition(1);
        waitForStart();
        while(opModeIsActive()){
            //Drives forwards from the start to the seconds spike mark
            Fleft.setTargetPosition(500);
            Fright.setTargetPosition(500);
            Bright.setTargetPosition(500);
            Bleft.setTargetPosition(500);
            //Sets the power to the wheels
            Fleft.setPower(0.5);
            Fright.setPower(0.5);
            Bright.setPower(0.5);
            Bleft.setPower(0.5);
            //This waits 1 seconds to start the next step
            sleep(1000);
            //Resets the encoder values
            Fleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Fright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Bright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Bleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //Resets the encoders to 0
            Fleft.setTargetPosition(0);
            Fright.setTargetPosition(0);
            Bright.setTargetPosition(0);
            Bleft.setTargetPosition(0);
            //sets the mode to use encoder values to move the wheels
            Fleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Fright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Bright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Bleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //Sets the power of the intake to reverse to spit the pixel out
            IntakeMotor.setPower(-0.5);
            //Waits 2 seconds till the steps continue
            sleep(2000);
            //Sets the Intake power to 0
            IntakeMotor.setPower(0);
            //Sets the motor positions to turn  to the right
            Fleft.setTargetPosition(-500);
            Fright.setTargetPosition(500);
            Bright.setTargetPosition(500);
            Bleft.setTargetPosition(-500);
            //Waits 2 seconds till the steps continue
            sleep(2000);
            //Resets the encoder values
            Fleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Fright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Bright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Bleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //Resets the encoder values to 0
            Fleft.setTargetPosition(0);
            Fright.setTargetPosition(0);
            Bright.setTargetPosition(0);
            Bleft.setTargetPosition(0);
            //Sets the mode to use encoder values to move the wheels
            Fleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Fright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Bright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Bleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //Moves straight towards the backdrop
            Fleft.setTargetPosition(200);
            Fright.setTargetPosition(200);
            Bright.setTargetPosition(200);
            Bleft.setTargetPosition(200);
            //Moves the lift up to the deliver position
            LeftLiftMotor.setTargetPosition(2000);
            RightLiftMotor.setTargetPosition(2000);
            //Waits 10 milliseconds to continue the steps
            sleep(10);
            //Move the arm of the robot back to clear the robot coming out
            ArmRotationMotor.setTargetPosition(-150);
            //Waits 200 milliseconds to continue the steps
            sleep(200);
            //Moves the arm to the deliver position
            ArmRotationMotor.setTargetPosition(880);
            //Resets the encoder value
            Fleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Fright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Bright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Bleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //Resets the encoder values to 0
            Fleft.setTargetPosition(0);
            Fright.setTargetPosition(0);
            Bright.setTargetPosition(0);
            Bleft.setTargetPosition(0);
            //Sets the mode to use encoder values to move the wheels
            Fleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Fright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Bright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Bleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //Opens the claw
            Claw1.setPosition(1);
            //waits 100 milliseconds
            sleep(100);
            //Closes the claw
            Claw1.setPosition(0);
            //Moves the Arm back so it clears the robot going down
            ArmRotationMotor.setTargetPosition(-150);
            //Waits 500 milliseconds till it continues the steps
            sleep(500);
            //Moves the lift down almost to intake position
            LeftLiftMotor.setTargetPosition(100);
            RightLiftMotor.setTargetPosition(100);
            //waits 4000 millisseconds till it continues the steps
            sleep(4000);
            //Moves the arm down to intake position
            ArmRotationMotor.setTargetPosition(0);
            //wait 500 milliseconds
            sleep(500);
            //Moves the lift down to intake position
            LeftLiftMotor.setTargetPosition(10);
            RightLiftMotor.setTargetPosition(10);
            //Moves the robot to the left beside the backdrop
            Fleft.setTargetPosition(1000);
            Fright.setTargetPosition(-1000);
            Bright.setTargetPosition(1000);
            Bleft.setTargetPosition(-1000);
            //Waits 5 seconds before continuing the steps
            sleep(5000);
            //Resets the encoder values
            Fleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Fright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Bright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Bleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //Resets the encoder values to 0
            Fleft.setTargetPosition(0);
            Fright.setTargetPosition(0);
            Bright.setTargetPosition(0);
            Bleft.setTargetPosition(0);
            //Sets the modes to use encoder values to move the wheels
            Fleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Fright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Bright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Bleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //Moves the robots further into the tape to make it clear
            Fleft.setTargetPosition(100);
            Fright.setTargetPosition(100);
            Bright.setTargetPosition(100);
            Bleft.setTargetPosition(100);

        }
    }
}
