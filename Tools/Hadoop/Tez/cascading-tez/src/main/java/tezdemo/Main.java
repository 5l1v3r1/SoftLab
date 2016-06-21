package tezdemo;

import cascading.flow.Flow;
import cascading.flow.tez.Hadoop2TezFlowConnector;
import cascading.pipe.Pipe;
import cascading.property.AppProps;
import cascading.scheme.hadoop.TextDelimited;
import cascading.tap.SinkMode;
import cascading.tap.Tap;
import cascading.tap.hadoop.Hfs;
import cascading.tuple.Fields;

import java.util.Properties;

/**
 * Created by hdhamee on 5/16/16.
 */
public class Main {
    public void es(){
        Tap src1 = new Hfs(new TextDelimited(new Fields("a","b","c","d","e","f"),";"), "input/input.txt", SinkMode.KEEP);
        Tap snk1 = new Hfs(new TextDelimited(new Fields("a"),";"), "output.txt", SinkMode.REPLACE);

        Pipe pipe1 = new Pipe("copy1");

        //set tez libs path
        Properties properties = new Properties();
        properties.setProperty("tez.lib.uris","/apps/tez-0.8.4-SNAPSHOT/tez-0.8.4-SNAPSHOT.tar.gz");
        Flow flow2 = new Hadoop2TezFlowConnector(properties).connect(src1,snk1,pipe1);

        AppProps.setApplicationJarClass(properties,Main.class);

        flow2.complete();
    }

    public static void main(String[] args) {
        new Main().es();
    }
}
