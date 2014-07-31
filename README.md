bootstrap
==

Bootstrap is a full stack web application kick starter project it is divided into two modules where the backend is a Java application which exposes a restful interface and serves out json to its clients.

The frontend is a AngularJS powered user interface which consumes the restful services and renders the user interface.

Each of the module has building blocks bundled with it, feel free to use them or tweak them as per your needs.

##You are the Architect!
Use with care, you are still the Architect, download it, understand it deeply and modify to fit your context.	

You should ideally not need to dig into the org.nthdimenzion package, just put your code into a com.xxx package and get going, you have all infrastructure configured for you.

The [WIKI] (https://github.com/sudarshan89/bootstrap/wiki/Deploying-a-bootstrap-application) talks about deploying your bootstrap application using jetty and Apache Webserver, however you can deploy it in any J2EE complaint web/application server.

==

#### /backend

###### Software pre-requisites
JDK 1.8
MySQL 5.1


A kick starter module to create a backend with Axon,Spring and Hibernate.

What you get Out of the Box ?

DDD Building blocks

CQRS building blocks

DB Connectivity,Transaction and Logging Configuration,Unit/Integration Test setup/configuration

Simple functionality like

	Services for user login
	
	Forgot password
	
	Assigning homepages to user roles
	
==
	
#### /frontend

 A kick starter module built with Yeoman. Using angular-generator to create it.

**Install prerequisites**

NodeJS v0.10.x+  [Click here for download link](http://nodejs.org/download/)

NPM(Bundled with Node)

Grunt-cli v0.1.13+

* Use _npm install -g grunt-cli_

Bower v1.3.5+

* Use _npm install -g bower_
 
 Use the below command in console(root directory).To install the dependency
 
						npm install
                        bower install
		
Run the grunt play:dev command to package the front end and deploy it on the server.
		
Simple login functionality has been added. Click on the "Test Drive" button.

A login popup will appear.Enter the below credential.

Username : admin@gmail.com

Password : admin


	

	
