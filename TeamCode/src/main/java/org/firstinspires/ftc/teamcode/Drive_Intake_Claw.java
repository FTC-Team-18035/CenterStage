    package org.firstinspires.ftc.teamcode;

    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
    import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.DcMotorSimple;
    import com.qualcomm.robotcore.hardware.Servo;
    import com.qualcomm.robotcore.util.ElapsedTime;

    @TeleOp(name = "DRIVE w In/Clw")

    // This opmode has framework for all systems included, but only functions chassis, intake, and claw systems

    public class Drive_Intake_Claw extends LinearOpMode {
        // variables
        static final double     COUNTS_PER_MOTOR_REV    = 288 ;    // eg: TETRIX Motor Encoder
        static final double     DRIVE_GEAR_REDUCTION    = 1 ;     // This is < 1.0 if geared UP
        static final double     WHEEL_DIAMETER_INCHES   = .5 ;     // For figuring circumference
        static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);
        static final int        MAX_LIFT_HEIGHT         = 400;      // YET TO BE DETERMINED
        
        private ElapsedTime     ClawTime = new ElapsedTime();    // sets up timer functions
        private ElapsedTime     LiftTime = new ElapsedTime();
        private ElapsedTime     DroneTime = new ElapsedTime();
        private ElapsedTime     IntakeTime = new ElapsedTime();
        private ElapsedTime     IntakeServoTime = new ElapsedTime();
        private ElapsedTime     ReverseIntakeTime = new ElapsedTime();

        private ElapsedTime     runtime = new ElapsedTime();    // sets up a timer function
        private ElapsedTime     runtime2 = new ElapsedTime();    // sets up a timer function
        private double          frontLeftPower = 0;     // declare motor power variable
        private double          backLeftPower = 0;      // declare motor power variable
        private double          frontRightPower = 0;    // declare motor power variable
        private double          backRightPower = 0;     // declare motor power variable
        private double          denominator = 1;        // declare motor power calculation variable
        private int             precision = 2;          // chassis motor power reduction factor 1=full 2=1/2 power 4=1/4 power
        private double          liftPower = 0.7;        // declare lift motor power variable *******
        private boolean         isClosed1 = false;      // Claw state variable
        private boolean         isClosed2 = false;      // Claw state variable
        private boolean         E_DoubleClose = false;  // Claw state variable
        private int             ClawInput = 0;          // Claw button case variable
        private int             LiftTarget = 0;         // Lift target position variable
        private boolean         BeganPressed = false;
        private boolean         IntakeRunning = false;

        @Override
        public void runOpMode() throws InterruptedException {
            // Declare our motors
            // Make sure your ID's match your configuration

            DcMotor Fleft = hardwareMap.dcMotor.get("Fleft");
            DcMotor Bleft = hardwareMap.dcMotor.get("Bleft");
            DcMotor Fright = hardwareMap.dcMotor.get("Fright");
            DcMotor Bright = hardwareMap.dcMotor.get("Bright");
            Servo Claw1 = hardwareMap.servo.get("Claw1");
            Servo Claw2 = hardwareMap.servo.get("Claw2");
/*
            Servo Drone = hardwareMap.servo.get("Drone");
            DcMotor LeftLiftMotor = hardwareMap.dcMotor.get("LeftLiftMotor");    // Lift Motors Name ???
            DcMotor RightLiftMotor = hardwareMap.dcMotor.get("RightLiftMotor");    // Lift Motors Name  ???
            DcMotor ArmRotationMotor = hardwareMap.dcMotor.get("ArmRotationMotor");
*/
            DcMotor IntakeMotor = hardwareMap.dcMotor.get("IntakeMotor");
   
            // Reverse the right side motors

            Fright.setDirection(DcMotorSimple.Direction.REVERSE);
            Bright.setDirection(DcMotorSimple.Direction.REVERSE);
/*
            LeftLiftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            RightLiftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

            RightLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LeftLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
*/
            // Set motor zero power behavior
            
            Fleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Fright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Bleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Bright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
/*            
            LeftLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            RightLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
*/
            Claw1.setPosition(0);
            Claw2.setPosition(0);

            waitForStart();

            while (opModeIsActive()) {

                // check for driving input

                double y = gamepad1.left_stick_y; // Remember, this is reversed!
                double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
                double rx = -gamepad1.right_stick_x;

                telemetry.update();

                // calculate motor movement math and adjust according to lift height or manual precision mode selection

                denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

                // check for Turbo or Precision Mode

                if(gamepad1.left_bumper){
                    precision = 1;              // set speed to full power - TRUBO MODE
                }
                else if(gamepad1.right_bumper){
                    precision = 4;              // set speed to 1/4 power - PRECISION MODE
                }
                else {
                    precision = 2;              // reset default speed to half power
                }

                // calculate motor power

                denominator = denominator * precision;
                frontLeftPower = (y + x + rx) / denominator;
                backLeftPower = (y - x + rx) / denominator;
                frontRightPower = (y - x - rx) / denominator;
                backRightPower = (y + x - rx) / denominator;

                // check for Claw input

                switch(ClawInput){
                        //If the answer is 1 it runs the code here
                    case 0:
                        if(gamepad2.b && ClawTime.milliseconds() > 300 && isClosed1){//This checks to see if it has been more than 300 milliseconds since the "a" button has been pressed
                            ClawTime.reset();//This resets the timer to 0
                            Claw1.setPosition(0);//this opens claw 1
                            isClosed1 = false;
                            ClawInput++;//This adds one to the variable to keep track of times the "a" button has been pressed
                        }
                        //If the answer is 3 it runs the code here
                    case 1:
                        if(gamepad2.b && ClawTime.milliseconds() > 300 && isClosed2){//This checks to see if it has been more than 300 milliseconds since the "a" button has been pressed
                            ClawTime.reset();//This resets the timer to 0
                            Claw2.setPosition(0);//this opens claw 2
                            isClosed2 = false;
                            ClawInput++;//This adds one to the variable to keep track of times the "a" button has been pressed
                        }
                        //If the answer is 4 it runs the code here
                    case 2:
                        if(gamepad2.b && ClawTime.milliseconds() > 300){//This checks to see if the "a" button has been pressed on gamepad1
                            ClawTime.reset();//This starts the timer since the "a" button was just pressed
                            Claw1.setPosition(1);//this closes claw 1
                            Claw2.setPosition(1);//this closes claw 2
                            isClosed1 = true;
                            isClosed2 = true;
                            E_DoubleClose = true;
                            ClawInput = 0;//This adds one to the variable to keep track of times the "a" button has been pressed
                        }
                }
                if(gamepad2.right_bumper && !E_DoubleClose && ClawTime.milliseconds() > 300){
                    Claw1.setPosition(1);
                    Claw2.setPosition(1);
                    E_DoubleClose = true;
                    ClawTime.reset();
                }
                else if(gamepad2.right_bumper && E_DoubleClose && ClawTime.milliseconds() > 300){
                    Claw1.setPosition(0);
                    Claw2.setPosition(0);
                    E_DoubleClose = false;
                    ClawTime.reset();
                }

                // issue motor power

                Fleft.setPower(frontLeftPower);
                Bleft.setPower(backLeftPower);
                Fright.setPower(frontRightPower);
                Bright.setPower(backRightPower);

            }

        }
    }

