#!/bin/bash

echo "Cleaning and building the project..."
mvn clean install
if [ $? -ne 0 ]; then
  echo "Build failed. Exiting."
  exit 1
fi

echo "Running the project..."
mvn exec:java -Dexec.mainClass=smartroute.Main
