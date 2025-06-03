@echo off
echo Cleaning and building the project...
mvn clean install
if errorlevel 1 (
    echo Build failed. Exiting.
    exit /b 1
)
echo Running the project...
mvn exec:java -Dexec.mainClass=com.example.smartroute.Main
pause
