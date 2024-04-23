# wsdl2apigee
wsdl2apigee is an open source project that extends Apigee Edge functionality to provide the ability to:

* Expose a SOAP Service as a REST API (over JSON or XML) or
* Expose a SOAP Service in a pass through mode for deployment on Apigee Edge

## Support
This is an open-source project of the Apigee Corporation. It is not covered by Apigee support contracts. However, we will support you as best we can. For help, please open an issue in this GitHub project. You are also always welcome to submit a pull request.

## Install
```
mvn install
```

## Usage
```
java -jar wsdl2apigee-1.0.0-jar-with-dependencies.jar -wsdl=<wsdl URL or Path>
```

### Other Options
```
java -jar wsdl2apigee-1.0.0-jar-with-dependencies.jar -wsdl=<wsdl URL or Path> -passthru=<true|false> -desc=<description> -allpost=<true|false> -opsmap=<opsmapping.xml> -service=<service name> -port=<port name> -debug=<true|false> -oauth=<true|false> -vhosts=comma separated values for virtuals hosts> -build=<build folder name> -cors=<true|false> -apikey=<true|false> -quota=<true|false> -basepath=<specify base path>

Defaults:
passthru=false            Do not convert to API/JSON. Treat as SOAP
allpost=false             Treat all wsdls operations as HTTP POST
service=If the wsdl contains > 1 service, specify the name. Otherwise, the first service is selected
port=If the service contains > 1 port, specify the port name. Otherwise, the first port is selected
debug=false               Set to true to enable trace.
vhosts=default
oauth=false               Don't add the oauth policy
basic=false               Don't add the basic auth policy (works if oauth disabled)
acl=false                 Don't add the acl call policy (works if oauth disabled)
apikey=false              Don't add apikey verification
quota=false               Works only if apikey or oauth is set
templates=<Path>          Path to custom templates which can be used for request transformation and for policies
header=false              Don't add SOAP header processing
basepath=Uses the basepath from the wsdl
cors=false                Don't enable CORS; Works only for SOAP to REST
caseInsensitive=false     Generate case sensitive conditions
build=specify build folder   default is temp folder; ensure user has access to read/write to temp folder
opsmap=specify operations map
backendurl = Specify the target backend url
backendurlvalidation=false    Specify to disable the backendurl validation
bodyLimit=<4096>              Defines trigger for simplified generation.
defaultOps=<GET|POST|NULL>    Defines default HTTP method. GET is default. Works with opsMap.
useJson2Xml=false             If true, GET is generated using JSON 2 XML template

```
## Output

You should see an API Proxy bundle generated in the same folder you ran the command. The bundle follows the convention of
{soap-servicename}.zip. You can import the bundle via the Edge Admin UI or tools like [apigeetool](https://www.npmjs.com/package/apigeetool).

## Open API Specification
When a proxy bundle for SOAP->REST is generated, an attempt is made to generate an Open API specification.
This document is hosted at http://{proxy-url}/{basePath}/openapi.json

NOTE: The Open API specification is not expected to be accurate for all WSDLs. For ex: OAS does not support xsd:choice.

## WSDL
When a proxy bundle for SOAP PassThru is generated, the WSDL is hosted at http://{proxy-url}/{basePath}?wsdl

## Appendix
OpsMap:
A file that maps WSDL operations to HTTP Verbs. A Sample Ops Mapping file looks like:
```
<proxywriter>
	<get>
		<operation>
			<pattern>get</pattern>
			<location>beginsWith</location>
		</operation>
		<operation>
			<pattern>inq</pattern>
			<location>beginsWith</location>
		</operation>
		<operation>
			<pattern>search</pattern>
			<location>beginsWith</location>
		</operation>
		<operation>
			<pattern>list</pattern>
			<location>beginsWith</location>
		</operation>
		<operation>
			<pattern>retrieve</pattern>
			<location>beginsWith</location>
		</operation>
		<operation>
			<pattern>fetch</pattern>
			<location>beginsWith</location>
		</operation>
	</get>
	<post>
		<operation>
			<pattern>create</pattern>
			<location>contains</location>
		</operation>
		<operation>
			<pattern>add</pattern>
			<location>beginsWith</location>
		</operation>
		<operation>
			<pattern>process</pattern>
			<location>beginsWith</location>
		</operation>
		<operation>
			<pattern>submit</pattern>
			<location>beginsWith</location>
		</operation>
	</post>
	<put>
		<operation>
			<pattern>update</pattern>
			<location>beginsWith</location>
		</operation>
		<operation>
			<pattern>change</pattern>
			<location>beginsWith</location>
		</operation>
		<operation>
			<pattern>modify</pattern>
			<location>beginsWith</location>
		</operation>
		<operation>
			<pattern>set</pattern>
			<location>beginsWith</location>
		</operation>
	</put>
	<delete>
		<operation>
			<pattern>delete</pattern>
			<location>beginsWith</location>
		</operation>
		<operation>
			<pattern>remove</pattern>
			<location>beginsWith</location>
		</operation>
		<operation>
			<pattern>del</pattern>
			<location>beginsWith</location>
		</operation>
		<operation>
			<pattern>cancel</pattern>
			<location>beginsWith</location>
		</operation>
	</delete>
</proxywriter>
```

## Custom templates 

In case of custom template folder is configured, wsdl2apigee will use it as a primary source
for templates (aka xml, xsl or js files). Default java resources will be used as a 
secondary template sources.

_Template folder structure should duplicate structure of the resource folder._
