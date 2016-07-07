package tezdemo;

import cascading.flow.Flow;
import cascading.flow.FlowRuntimeProps;
import cascading.flow.tez.Hadoop2TezFlowConnector;
import cascading.operation.Identity;
import cascading.pipe.Each;
import cascading.pipe.GroupBy;
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
        pipe1 = new Each(pipe1,new Identity());
        pipe1 = new GroupBy(pipe1,new Fields("a"));

        Properties properties = FlowRuntimeProps.flowRuntimeProps()
                .setGatherPartitions(4)
                .buildProperties();

        AppProps.setApplicationJarClass(properties,Main.class);
        AppProps.setApplicationName(properties,"Tez-Test");

        Flow flow2 = new Hadoop2TezFlowConnector(properties).connect(src1,snk1,pipe1);

        flow2.complete();
    }

    public static void main(String[] args) {
        new Main().es();
    }
}
