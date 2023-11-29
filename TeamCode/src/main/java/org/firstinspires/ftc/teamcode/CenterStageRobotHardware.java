package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;
public class CenterStageRobotHardware {

    // DECLARE ALL VARIABLES HERE


    //We create space holders here. So we can declare in the init function
    public DcMotorSimple FrontLeft;
    public DcMotor FrontRight;
    public DcMotor BackLeft;
    public DcMotor BackRight;
    public Servo Claw;
    public BNO055IMU Imu;

    public ElapsedTime MoveTime = new ElapsedTime();

    public double power = 0;

    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera

    /**
     * The variable to store our instance of the AprilTag processor.
     */
    private AprilTagProcessor aprilTag;

    /**
     * The variable to store our instance of the vision portal.
     */
    private VisionPortal visionPortal;


    public void Init(HardwareMap map) {
        //We assign the actual motors to the variables we created above
        FrontLeft = map.get(DcMotorSimple.class, "Fleft");
        FrontRight = map.get(DcMotor.class, "Fright");
        BackRight = map.get(DcMotor.class, "Bright");
        BackLeft = map.get(DcMotor.class, "Bleft");
        Claw = map.get(Servo.class, "Claw");

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
        //FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Sets the Claw to open in Initialization
        Claw.setPosition(0);
    }

    //This function is called to stop all the motors on the robot in autonomous
    public void Stop() {
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
    }

    //This function create a timer and takes inputs from when you call the function to tell the robot how far to move and what direction
    public void Drive(String dir, int blocks) {
        MoveTime.reset();
        //It takes 1450 milliseconds to move one block so we multiply that by the amount of blocks we want to go
        double newTime = blocks * 1450;
        MoveTime.startTime();
        //These check to see what we inputed to tell it what direction we want it to go
        if (dir == "Forward" && MoveTime.milliseconds() <= newTime) {
            FrontLeft.setPower(1);
            FrontRight.setPower(1);
            BackLeft.setPower(1);
            BackRight.setPower(1);
        } else if (dir == "Backwards" && MoveTime.milliseconds() <= newTime) {
            FrontLeft.setPower(-1);
            FrontRight.setPower(-1);
            BackLeft.setPower(-1);
            BackRight.setPower(-1);
        }
        //This calls the function created above to stop all motors when the robot has reached it's destination
        else {
            this.Stop();
        }

    }
}
    /*public void AprilTagScan(HardwareMap map) {

        initAprilTag();


        // Save more CPU resources when camera is no longer needed.
        visionPortal.close();

        // end method runOpMode()

        /
         * Initialize the AprilTag processor.
         *
        private void initAprilTag() {

            // Create the AprilTag processor.
           // aprilTag = new AprilTagProcessor.Builder()

                    // The following default settings are available to un-comment and edit as needed.
                    //.setDrawAxes(false)
                    //.setDrawCubeProjection(false)
                    //.setDrawTagOutline(true)
                    //.setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                    //.setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                    //.setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)

                    // == CAMERA CALIBRATION ==
                    // If you do not manually specify calibration parameters, the SDK will attempt
                    // to load a predefined calibration for your camera.
                    .setLensIntrinsics(578.272, 578.272, 402.145, 221.506)
                    // ... these parameters are fx, fy, cx, cy.

                    .build();

            // Adjust Image Decimation to trade-off detection-range for detection-rate.
            // eg: Some typical detection data using a Logitech C920 WebCam
            // Decimation = 1 ..  Detect 2" Tag from 10 feet away at 10 Frames per second
            // Decimation = 2 ..  Detect 2" Tag from 6  feet away at 22 Frames per second
            // Decimation = 3 ..  Detect 2" Tag from 4  feet away at 30 Frames Per Second (default)
            // Decimation = 3 ..  Detect 5" Tag from 10 feet away at 30 Frames Per Second (default)
            // Note: Decimation can be changed on-the-fly to adapt during a match.
            //aprilTag.setDecimation(3);

            // Create the vision portal by using a builder.
            VisionPortal.Builder builder = new VisionPortal.Builder();

            // Set the camera (webcam vs. built-in RC phone camera).
            if (USE_WEBCAM) {
                builder.setCamera(map.get(WebcamName.class, "Webcam 1"));
            } else {
                builder.setCamera(BuiltinCameraDirection.BACK);
            }

            // Choose a camera resolution. Not all cameras support all resolutions.
            //builder.setCameraResolution(new Size(640, 480));

            // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
            //builder.enableLiveView(true);

            // Set the stream format; MJPEG uses less bandwidth than default YUY2.
            //builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);

            // Choose whether or not LiveView stops if no processors are enabled.
            // If set "true", monitor shows solid orange screen if no processors enabled.
            // If set "false", monitor shows camera view without annotations.
            //builder.setAutoStopLiveView(false);

            // Set and enable the processor.
            builder.addProcessor(aprilTag);

            // Build the Vision Portal, using the above settings.
            visionPortal = builder.build();

            // Disable or re-enable the aprilTag processor at any time.
            //visionPortal.setProcessorEnabled(aprilTag, true);

        }   // end method initAprilTag()



         * Add telemetry about AprilTag detections.

    }
    private void telemetryAprilTag() {

        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        //telemetry.addData("# AprilTags Detected", currentDetections.size());

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                //telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                //telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
               // telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                //telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
            } else {
                //telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                //telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }   // end for() loop

        // Add "key" information to telemetry
        //telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
        //telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
        //telemetry.addLine("RBE = Range, Bearing & Elevation");

    }   // end method telemetryAprilTag()

}   // end class


*/

