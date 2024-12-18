# Strafing Code
## Introduction
- All code used is from: https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html
- There are **TWO** different types of drive programmed in `strafe-drive`. See the table in #strafing-info for more details.
## Initialization 
**NOTE: ALL MOTORS ARE KNOWN AS "GENERIC MOTOR"**
### Robot Centric
| Port    | Device Type | Name in Config | 
| -------- | ------- | -------- 
| 0 |  Front Left Motor | `frontLeft` |
| 1 |  Front Right Motor | `frontRight` |
| 2 |  Back Left Motor | `backLeft` |
| 3 |  Back Right Motor | `backRight` |

### Field Centric
| Port    | Device Type | Name in Config | 
| -------- | ------- | -------- |
| 0 |  Front Left Motor | `frontLeft` |
| 1 |  Front Right Motor | `frontRight` |
| 2 |  Back Left Motor | `backLeft` |
| 3 |  Back Right Motor | `backRight` |
| N/A |  Rev Internal IMU | `imu` |

*IMU is in `I2C Bus 0`*

## Strafing Info
### Robot Centric
- Definition: In this system, the robot interprets all movement commands relative to its own orientation (local frame).
- The robot's "forward" direction is always aligned with its front.

### Field Centric
- Definition: In this system, movement commands are interpreted relative to the field (global frame) rather than the robot’s orientation.
- Directions like "forward" are always relative to a fixed point on the field (e.g., North).

### Comparison
| **Aspect**         | **Robot-Centric**                       | **Field-Centric**                      |
|--------------------|----------------------------------------|---------------------------------------|
| **Reference Frame** | Robot’s local orientation              | Global (field) orientation            |
| **"Forward" Motion** | Based on where the robot is facing     | Fixed direction on the field          |
| **Ease of Control** | Harder for drivers during rotations    | Easier and more intuitive for drivers |
| **Implementation**  | Simple; no external sensors required   | Requires gyros/IMUs for orientation   |
