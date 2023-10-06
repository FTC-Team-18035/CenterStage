package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class CenterStageRobotHardware {

    // DECLARE ALL VARIABLES HERE


    //We create space holders here. So we can declare in the init function
    public DcMotor FrontLeft;
    public DcMotor FrontRight;
    public DcMotor BackLeft;
    public DcMotor BackRight;
    public Servo Claw;
    public BNO055IMU Imu;

    public ElapsedTime MoveTime = new ElapsedTime();

    public double power = 0;



    public void Init(HardwareMap map){
        //We assign the actual motors to the variables we created above
        FrontLeft = map.get(DcMotor.class, "Fleft");
        FrontRight = map.get(DcMotor.class, "Fright");
        BackRight = map.get(DcMotor.class, "Bright");
        BackLeft = map.get(DcMotor.class, "Bleft");
        Claw = map.get(Servo.class, "Claw");
        Imu = map.get(BNO055IMU.class, "imu");

        //Here we set the Orientation of the imu which is built into the control hub otherwise know as the "Chub"
       /* BNO055IMU.Parameters newImuParameters;

        newImuParameters = new BNO055IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP
                )
        );

        Imu.initialize(newImuParameters);*/

        //Reverse any motors that need it
        BackLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //Set all the motors to ZeroPowerBehavior so they all stop at the same time
        BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Sets the Claw to open in Initialization
        Claw.setPosition(0);
    }

    //This function is called to stop all the motors on the robot in autonomous
    public void Stop(){
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
    }

    //This function create a timer and takes inputs from when you call the function to tell the robot how far to move and what direction
    public void Drive(String dir, int blocks){
        MoveTime.reset();
        //It takes 1450 milliseconds to move one block so we multiply that by the amount of blocks we want to go
        double newTime = blocks * 1450;
        MoveTime.startTime();
        //These check to see what we inputed to tell it what direction we want it to go
        if(dir == "Forward" && MoveTime.milliseconds() <= newTime){
            FrontLeft.setPower(1);
            FrontRight.setPower(1);
            BackLeft.setPower(1);
            BackRight.setPower(1);
        }
        else if(dir == "Backwards" && MoveTime.milliseconds() <= newTime){
            FrontLeft.setPower(-1);
            FrontRight.setPower(-1);
            BackLeft.setPower(-1);
            BackRight.setPower(-1);
        }
        //This calls the function created above to stop all motors when the robot has reached it's destination
        else{this.Stop();}

    }

}
