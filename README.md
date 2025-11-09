# Azure Box Creations
A feature-dense and expandable tool that helps generate precise, laser-cut-ready box SVGs with ease. Its modular design makes it simple to use for quick prototypes while allowing advanced customization for complex, parameter-driven designs.

## About
This repository is the Java-based codebase for Azure, a project being developed by Austin, Devin and Grant. The current focus includes:

* Adding more box types

* Expanding features and refining the system

* Adding a lightweight and easy-to-use GUI

## Getting Started

**Prerequisites**

* Java JDK (version 8 or newer)

* Maven build tool

* An IDE of your choice (IntelliJ, Eclipse, VS Code)

* (Optional) Git knowledge for managing branches and commits

**Building the Project**

Clone the repo

```
git clone https://github.com/AustinDMeredith/Azure.git
cd Azure
```
Build the package 

```
mvn clean package -DskipTests
```

Run the package

```
java -cp target/azure-test-1.0-SNAPSHOT.jar Main
```
