This is a simple demo using corda rpc client to connect corda node with ktor.

Now we have 5 properties in the config.properties:

1. port: the port ktor server listen on

2. host: corda node host

3. username: username which needed to call node rpc server / in fact flow

4. password: password which needed to call node rpc server / in fact flow

5. rpcPort: node server's rpc port

you can use command 'gradle shadowJar' to generate your fat jar, you should put you conf.properties under the same directory as the fat jar, you can also use replace properties with environment variables, the key names of the environment variables are the same as properties,for example:

port=8099 java -jar app-1.0-SNAPSHOT-shadow.jar 

then the configuration item named 'port' in config.properties will be be ignored



I also gave a restful-style interface to get the node party name :)