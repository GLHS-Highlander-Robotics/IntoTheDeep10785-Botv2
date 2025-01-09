# Introduction
*This repository is in a very early stage of development.* 
*This `README.md` may not be appropriately updated to account for recent changes. Please update appropriately if you notice an issue*
- This repository contains the code for version 2 of team 10785's IntoTheDeep robot.
## Contents
- Currently, the only functioning code present in this repository enables robot-centric and field-centric driving
- Code enabling linear slide operation is currently in very early development

## Future Additions
- Converting currently used LinearOpMode to OpMode
- PID drive
  - Determining position
  - Using position to make autonomous drivetrain path

# Strafing Code
## Introduction
- Initial code used is from: https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html
## Initialization
**NOTE: ALL MOTORS ARE KNOWN AS "GoBILDA 5202/3/4 series"**

### Robot Centric
| **Port** | **Device Type**   | **Name in Config** |
|----------|-------------------|--------------------|
| 0        | Front Left Motor  | `frontLeft`        |
| 1        | Front Right Motor | `frontRight`       |
| 2        | Back Left Motor   | `backLeft`         |
| 3        | Back Right Motor  | `backRight`        |

### Field Centric
| **Port**  | **Device Type**   | **Name in Config** |
|-----------|-------------------|--------------------|
| 0         | Front Left Motor  | `frontLeft`        |
| 1         | Front Right Motor | `frontRight`       |
| 2         | Back Left Motor   | `backLeft`         |
| 3         | Back Right Motor  | `backRight`        |
| N/A       | Rev Internal IMU  | `imu`              |

*IMU is in `I2C Bus 0`*

## Controls
### Robot Centric
| Gamepad input   | Output             |
|-----------------|--------------------|
| `left_stick_y`  | Forward-Backward   |
| `left_stick_x`  | Left-Right         |
| `right_stick_y` | *Unbound*          |
| `right_stick_x` | Rotation           |
| `dpad_up`       | Forward 30% power  |
| `dpad_down`     | Backward 30% power |
| `dpad_left`     | Left 30% power     |
| `dpad_right`    | Right 30% power    |
| `left_trigger`  | *Unbound*          |
| `right_trigger` | *Unbound*          |
| `left_bumper`   | *Unbound*          |
| `right_bumper`  | *Unbound*          |
| `x`             | *Unbound*          |
| `y`             | *Unbound*          |
| `b`             | *Unbound*          |
| `a`             | *Unbound*          |
| `back`          | *Unbound*          |
| `start`         | *Unbound*          |

### Field Centric
| Gamepad input   | Output             |
|-----------------|--------------------| 
| `left_stick_y`  | Forward-Backward   |
| `left_stick_x`  | Left-Right         |
| `right_stick_y` | *Unbound*          |
| `right_stick_x` | Rotation           |
| `dpad_up`       | Forward 30% power  |
| `dpad_down`     | Backward 30% power |
| `dpad_left`     | Left 30% power     |
| `dpad_right`    | Right 30% power    |
| `left_trigger`  | *Unbound*          |
| `right_trigger` | *Unbound*          |
| `left_bumper`   | *Unbound*          |
| `right_bumper`  | *Unbound*          |
| `x`             | Reset Orientation  |
| `y`             | *Unbound*          |
| `b`             | *Unbound*          |
| `a`             | *Unbound*          |
| `back`          | *Unbound*          |
| `start`         | *Unbound*          |

## Telemetry
- Definition: Collection/measurement, transmission and display of data.
- Telemetry collects data from code or sensors and displays on FTC driver hub.
- Telemetry displays newest entries first.
- When initialized, telemetry set to have max amount of lines displayed.
- The number of lines each part of telemetry uses is recorded below.
  **Ensure that number of lines generated does not exceed maximum set**
### Robot Centric Implementation
- Data from joysticks x2
- Data from dpad x1
- Current motor power x4
### Field Centric Implementation
- Data from joysticks x2
- Data from dpad x1
- Current bot heading in IMU x1
- Current motor power x4
### Features to be implemented
- Voltage sensor
- Runtime data
- Current draw

## Strafing Info
### Robot Centric
- Definition: In this system, the robot interprets all movement commands relative to its own orientation (local frame).
- The robot's "forward" direction is always aligned with its front.
- Driving in this mode is akin to driving a car.

### Field Centric
- Definition: In this system, movement commands are interpreted relative to the field (global frame) rather than the robot’s orientation.
- Directions like "forward" are always relative to a fixed point on the field (e.g., North).
- Field is set upon initialization of `Field Centric`.
- Field can be reset using `X` on the gamepad.

### Comparison
| Aspect                | Robot-Centric                        | Field-Centric                         |
|-----------------------|--------------------------------------|---------------------------------------|
| **Reference Frame**   | Robot’s local orientation            | Global (field) orientation            |
| **"Forward" Motion**  | Based on where the robot is facing   | Fixed direction on the field          |
| **Ease of Control**   | Harder for drivers during rotations  | Easier and more intuitive for drivers |
| **Implementation**    | Simple; no external sensors required | Requires gyros/IMUs for orientation   |
