# TechnicalAssignment_MAS
This application is to query the data provided by the Monetary Authority of Singapore (MAS) which provides monthly interest rates of banks and financial companies for the business requirements.
# Assumptions
The business requirement achieved based on a few assumptions that had made:
* User is only allowed to input the date (**from** and **to**) with the format (**MMM-yyyy e.g. Jan-2017**) to get the data for that period.
* User is able to compare the financial companies rates against bank rates by months, that generated on the table, and graph format (*bar graph*).
* User is able to acknowledge which months financial companies have a higher rate based on the generated table result, and graph (*line graph*). The comparison result is made with the assumption of comparing every month of Financial Companies Savings Deposits against Bank Savings Deposits.
* User is able to know the interest rates slope are on an upward or downward or flat trend during the period based on the comparison of the data from the date (**from** and **to**) entered. The flat trend is the interest rates trend of either inconsistent or straight line trend. The graph (*line graph*) is able to show the interest rates slope trend as well.
* User is able to compare the overall average of Financial Companies rates against Bank rates. This overall average result is made from the assumption of sum up all the months of Financial Companies Fixed Deposit 3 Months divided by the total of months. Thus, there will be 4 set of each result for the overall average interest rates for Financial Companies and Bank.

# Design Patterns
The design patterns that applied in this application is a common class abstract the data model object class ***InterestRates.class*** with the standard Java setters and getters method. There is a common class ***config.properties*** the file is created in this application to ease of easy code maintain in future. If any information is changed from the properties file, the user doesn't need to recompile the java class. In other words, the advantage of using properties file is the user can configure things which are prone to change over a period of time without the need of changing anything in the code.


# Procedure To Build The Code & Run The Application
```
Using Windows Command Line
```
Pre-requisite
1. Go to this [link](http://maven.apache.org/download.cgi) to download the maven in the local machine
2. This [link][http://maven.apache.org/install.html] is the installation steps for maven in the local machine

1. Download the application from here [link](https://github.com/ufinity-veisheng/TechnicalAssignment_MAS.git) as a zip file
2. Extract the .zip file, and access to the (**\TechnicalAssignment_MAS-master\TechnicalAssignment_MAS-master\technicalAssignment\TechnicalAssignment**) using command line
3. Run the command ***mvn clean install*** for the first time
4. If already perfomed before ***mvn clean install*** command, you may directly run the command ***mvn package***
5. After the build process is completed. You may run the command ***mvn exec:java***, and the application will be executed
6. Begin to enter the (**from** and **to**) date with the format (**MMM-yyyy e.g. Jan-2017**) and the result will be shown
```
Using Eclipse IDE
```
1. Clone the project using GitClone and select the directory to clone
2. Open it with Eclipse IDE
3. Press ***ctrl + b*** to build all the project 
4. Access to **src/com.ufinity.main/MainFunction.java** directory, right-click **Run as > Java Application**
5. In the console, enter (**from** and **to**) date with the format (**MMM-yyyy e.g. Jan-2017**) and the result will be shown
6. For test case, access to **test/com.ufinity.test/BusinessReqTest.java** directory, right-click **Run as > JUnit Test**
