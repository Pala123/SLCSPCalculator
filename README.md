# SLCSPCalculator

Follow the below steps to compile and execute the Java program from a command line

- Firstly, Clone this repository onto your local using git command

- Open your terminal and clone the project from git and Navigate to the project (if git is already setup and available)

- Go to directory where you want to clone the project. Right click here and select 'git bash here', it will open a git console. Type the following command in git console to clone the project
````
git clone https://github.com/Pala123/SLCSPCalculator.git
````
- Open a command prompt window or git bash terminal.

- Navigate to the directory where the repository or the source code has been saved/cloned and go to source folder to run the java commands i.e ` ../../../SLCSPCalculator/src `.

- Type ` 'javac SLCSPCalculator.java' `
   
  It would look something as ` ../../../SLCSPCalculator/src>javac SLCSPCalculator.java`

- Now, type ` 'java SLSCPCalculator' ` to run the program.

It would look something as ` ../../../SLCSPCalculator/src>java SLCSPCalculator`

- In case, if you encounter any errors that means java classpath has not been setup properly on the machine you might see an error. In that case, try the below command to set the class path and let the JRE know where to find the class:

` ../../../SLCSPCalculator/src>java -classpath . SLCSPCalculator `


- Results will be printed on the window. An output file for this program has been included in the repo as "Output.txt" file.

Note: If the program has to be run directly using an IDE like Eclipse or IntelliJ. The path for the FileReader in SLSCPCalculator.java has to be changed from FileReader("zips.csv") to FileReader("src/zips.csv") so that the compiler can be able to read the csv files.

` The file for dog license assessment has been included in a separate branch named "Dog-license"  `
