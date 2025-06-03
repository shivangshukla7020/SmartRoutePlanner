@echo off
cd /d "%~dp0"

echo Cleaning and building the project...
mvn clean install
if errorlevel 1 (
    echo Build failed. Exiting.
    pause
    exit /b 1
)

echo Running the project...
mvn exec:java -Dexec.mainClass=smartroute.Main
if errorlevel 1 (
    echo Run failed. Exiting.
    pause
    exit /b 1
)

echo Done!
pause
