Setup Steps
--------------
- cd PWD
- git clone https://github.com/apache/tez.git
- cd tez
- git checkout tags/release-0.8.3-rc0
- mvn clean package -DskipTests=true -Dmaven.javadoc.skip=true
- hadoop fs -mkdir -p /apps/tez-0.8.3
- hadoop fs -put ./tez-dist/target/tez-0.8.3.tar.gz /apps/tez-0.8.3/tez-0.8.3.tar.gz
- set tez.lib.uris to "${fs.defaultFS}/apps/tez-0.8.3/tez-0.8.3.tar.gz" in tez-site.xml in $HADOOP_CONF_DIR
- Configure the client node to include the tez-libraries in the hadoop classpath
   - extract ./tez-dist/target/tez-0.8.3-minimal.tar.gz in TEZ_JARS dir.
   - export HADOOP_CLASSPATH= $HADOOP_CLASSPATH:${TEZ_JARS}/*:${TEZ_JARS}/lib/* in $HADOOP_CONF_DIR/hadoop_env.sh only in client node.


References
-------------
- https://tez.apache.org/install.html
- http://blog.sequenceiq.com/blog/2014/10/20/cascading-on-tez/
- https://github.com/cascading/cascading/tree/3.0/cascading-hadoop2-tez


Cascading setup
------------------
```
Properties properties = FlowRuntimeProps.flowRuntimeProps()
                .setGatherPartitions(4) // decide value
                .buildProperties();

        AppProps.setApplicationJarClass(properties,Main.class);
        AppProps.setApplicationName(properties,"Tez-Test");

Flow flow = new Hadoop2TezFlowConnector(properties).connect(flowDef);

```
