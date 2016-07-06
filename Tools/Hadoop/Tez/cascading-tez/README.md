- clone tez 0.8.3 from github
- cd tez
- mvn clean package -DskipTests=true -Dmaven.javadoc.skip=true
- hadoop fs -mkdir /apps/tez-0.8.3
- hadoop fs -copyFromLocal tez-dist/target/tez-0.8.3.tar.gz /apps/tez-0.8.3/tez-0.8.3.tar.gz
- set tez.lib.uris to "${fs.defaultFS}/apps/tez-0.8.3/tez-0.8.3.tar.gz" in tez-site.xml
- Configure the client node to include the tez-libraries in the hadoop classpath:export HADOOP_CLASSPATH=${TEZ_CONF_DIR}:${TEZ_JARS}/*:${TEZ_JARS}/lib/*

References:
- https://tez.apache.org/install.html
- http://blog.sequenceiq.com/blog/2014/10/20/cascading-on-tez/

For cascading: changes:
```
Properties properties = prepareProperties(this.getClass());
FlowRuntimeProps.flowRuntimeProps()
                .setGatherPartitions(1)
                .buildProperties(properties);
Flow flow = new Hadoop2TezFlowConnector(properties).connect(flowDef);
        ```
