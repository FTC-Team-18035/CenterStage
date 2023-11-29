package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@Disabled
@TeleOp(name = "Claw Test")
public class ClawTest extends LinearOpMode {

    private ElapsedTime ClawTime = new ElapsedTime();
    private int             ClawInput = 0;
    private boolean         isClosed1 = false;
    private boolean         isClosed2 = false;
    private boolean         E_DoubleClose = false;

    @Override
    public void runOpMode(){

        Servo Claw1 = hardwareMap.servo.get("Claw1");
        Servo Claw2 = hardwareMap.servo.get("Claw2");

        Claw1.setPosition(1);
        Claw2.setPosition(1);

        waitForStart();

        while(opModeIsActive()){
            switch(ClawInput){
                //If the answer is 1 it runs the code here
                case 0:
                    if(gamepad2.b && ClawTime.milliseconds() > 300 && isClosed1){//This checks to see if it has been more than 300 milliseconds since the "a" button has been pressed
                        ClawTime.reset();//This resets the timer to 0
                        Claw1.setPosition(1);//this opens claw 1
                        isClosed1 = false;
                        E_DoubleClose = false;
                        ClawInput++;//This adds one to the variable to keep track of times the "a" button has been pressed
                    }
                    //If the answer is 3 it runs the code here
                case 1:
                    if(gamepad2.b && ClawTime.milliseconds() > 300 && isClosed2){//This checks to see if it has been more than 300 milliseconds since the "a" button has been pressed
                        ClawTime.reset();//This resets the timer to 0
                        Claw2.setPosition(1);//this opens claw 2
                        isClosed2 = false;
                        E_DoubleClose = false;
                        ClawInput++;//This adds one to the variable to keep track of times the "a" button has been pressed
                    }
                    //If the answer is 4 it runs the code here
                case 2:
                    if(gamepad2.b && ClawTime.milliseconds() > 300){//This checks to see if the "a" button has been pressed on gamepad1
                        ClawTime.reset();//This starts the timer since the "a" button was just pressed
                        Claw1.setPosition(0);//this closes claw 1
                        Claw2.setPosition(0);//this closes claw 2
                        isClosed1 = true;
                        isClosed2 = true;
                        E_DoubleClose = true;
                        ClawInput = 0;//This adds one to the variable to keep track of times the "a" button has been pressed
                    }
            }
            if(gamepad2.right_bumper && !E_DoubleClose && ClawTime.milliseconds() > 300){
                Claw1.setPosition(0);
                Claw2.setPosition(0);
                isClosed1 = true;
                isClosed2 = true;
                E_DoubleClose = true;
                ClawInput = 0;
                ClawTime.reset();
            }
            else if(gamepad2.right_bumper && E_DoubleClose && ClawTime.milliseconds() > 300){
                Claw1.setPosition(1);
                Claw2.setPosition(1);
                isClosed1 = false;
                isClosed2 = false;
                E_DoubleClose = false;
                ClawTime.reset();
            }
        }
    }
}
