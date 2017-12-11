# AppDynamics AppChecker

This little tool checks the Application configured within an AppDynamics Controller and watched for changes. Supported changes will be reported as a Custom Event to the Controller. Supported types of changes are:
  * Change of the Application Name
  * Deleting an Application
  * Adding an Application

## Get the Software

Either [Download the AppChecker from the Github Releases](https://github.com/michaelenglert/appchecker/releases/tag/v1.0) or Build from Source.

## Configure

The configuration is done via a configuration file like this:
```
#systemTenant: "system"

systemTenantUser: ""

systemTenantPassword: ""

#applicationId: 4

#tenant: "customer1"

tenantUser: ""

tenantPassword: ""

severity: "INFO"

customeventType: "NewApp"

eventType: "CUSTOM"

appFile: "./apps.json"

controller: "http(s)://<CONTROLLER_HOST>:<CONTROLLER_PORT>"
```

In the default configuration the Custom Events for added/deleted/changes Applications will be reported within the system Tenant Application "AppDynamics Controller". If you want to change this behavior please change the `system...` variables and the `applicationId` within the `config.yml`.

**Note** The User used for reporting the Custom Events has to have an Admin Role within AppDynamics. 

## Run

`java -jar appcheck-<VERSION>.jar ./config.yml`

## Build from Source

1. Clone this repository
2. `./gradlew fatJar`