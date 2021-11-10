# Ensah-Recognition
A simple desktop application that detect and recognize faces
<p align="center">
<img  width="300" height="300"  src="https://github.com/Khalid-Na/Ensah-Recognition/blob/1506182303076030cb0cab927129c9d48142440b/Results/recognition.png">
</p>

## Features

  + Real Time Face Detection (Red shape)
  + Real Time Face Recognition
  + Face Trainer
  + Persistent Storage for Trained Faces Image Using Database
  
 ## Technologies
 
  + Core Java
  + JavaCV (wrapper of Opencv )
  + JavaFX
  + MySQL
  
 ## User Interface

<p align="center">
<img  width="400"   alt="Face recognition interface" src="https://github.com/Khalid-Na/Ensah-Recognition/blob/1506182303076030cb0cab927129c9d48142440b/Results/Screenshot1.png">
<img  width="400"  alt="Face recognition interface"  src="https://github.com/Khalid-Na/Ensah-Recognition/blob/1506182303076030cb0cab927129c9d48142440b/Results/Screenshot%202.png">
</p>

## Requirements

+ JavaFx :
  - https://gluonhq.com/products/javafx/
+ OpenCV :
  - https://opencv.org/opencv-4-5-0/
+ JavaCv :
  - https://github.com/bytedeco/javacv/releases
+ JavaCpp :
  - https://github.com/bytedeco/javacpp/releases
+ JavaCppPresets :
  - https://github.com/bytedeco/javacpp-presets/releases
+ MysqlConnector :
  - https://mvnrepository.com/artifact/mysql/mysql-connector-java
+ JavaFX Scene Builder: (Follow The Installation Instruction)
  - JavaFX Scene Builder https://docs.oracle.com/javafx/scenebuilder/1/installation_1-0/jsbpub-installation_1-0.html
      
 ## Database Settings
 + Open MySQL on your SQL database engine then Create a New Database & name it **Recognition**
 + Now import attached face.sql to the Recognition Database
    - Or Create a New Table
```
DROP TABLE IF EXISTS `face`;
CREATE TABLE IF NOT EXISTS `face` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` int(10) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `age` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
```
