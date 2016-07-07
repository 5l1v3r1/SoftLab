Setup Steps
--------------
- cd PWD
- git clone https://github.com/apache/tez.git
- cd tez
- git checkout tags/release-0.8.3-rc0
- Update root pom.xml to change hadoop version property:  <hadoop.version>2.7.1</hadoop.version>
- mvn clean package -DskipTests=true -Dmaven.javadoc.skip=true
- hadoop fs -mkdir -p /apps/tez-0.8.3
- hadoop fs -put ./tez-dist/target/tez-0.8.3-minimal.tar.gz /apps/tez-0.8.3/tez-0.8.3-minimal.tar.gz
- set `tez.lib.uris to "${fs.defaultFS}/apps/tez-0.8.3/tez-0.8.3-minimal.tar.gz"` and `tez.use.cluster.hadoop-libs to true` in tez-site.xml in $HADOOP_CONF_DIR
- set "mapreduce.framework.name"="yarn-tez" in mapred-site.xml in $HADOOP_CONF_DIR
- Configure the client node to include the tez-libraries in the hadoop classpath
   - extract ./tez-dist/target/tez-0.8.3-minimal.tar.gz in TEZ_JARS dir.
   - export HADOOP_CLASSPATH= $HADOOP_CLASSPATH:${TEZ_JARS}/*:${TEZ_JARS}/lib/* in $HADOOP_CONF_DIR/hadoop_env.sh only in client node.


References
-------------
- https://tez.apache.org/install.html
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

Optimization: Tez configs
-------------------------
```
  <property>
      <name>tez.runtime.compress</name>
      <value>true</value>
      <description>Whether intermediate data should be compressed or not</description>
  </property>
  <property>
      <name>tez.runtime.compress.codec</name>
      <value>org.apache.hadoop.io.compress.SnappyCodec</value>
      <description>The coded to be used if compressing intermediate data. Only
        applicable if tez.runtime.compress is enabled
      </description>
  </property>

  <property>
    <name>tez.am.resource.memory.mb</name>
    <value>4096</value> <!-- Example: "1536" -->
    <description>The amount of memory to be used by the AppMaster.
      Used only if the value is not specified explicitly by the DAG definition.
    </description>
  </property>
  <property>
    <name>tez.task.resource.memory.mb</name>
    <value>4096</value>
    <description>The amount of memory to be used by launched tasks.
    Used only if the value is not specified explicitly by the DAG definition.
    </description>
  </property>

  <property>
    <name>tez.runtime.shuffle.memory-to-memory.enable</name>
    <value>false</value>
  </property>
  <property>
    <name>tez.runtime.optimize.local.fetch</name>
    <value>true</value>
  </property>

  <property>
     <name>mapred.output.committer.class</name>
     <value>org.apache.hadoop.mapred.FileOutputCommitter</value>
  </property>

```
