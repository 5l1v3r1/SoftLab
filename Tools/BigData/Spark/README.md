Prerequisites
===============
1. Java-1.7
2. Maven-2/3
3. git
4. Hadoop-2.7.1
5. Spark

How to run
===============
    $ git clone https://github.com/mehikmat/CoderLab.git

    $ cd CoderLab/Tools/Bigdata/Spark/RawDataProcessor
    
    $ mvn clean package

    $ hadoop jar target/BigSchoolCascading-1.0.jar input/input.txt output/output.txt
        OR
    $ sh runner.sh

  In case you get an error of type "Not valid JAR", check the jar path. It might be different from one plateform to another with mvn.


Browse http://localhost:8080 for job status
