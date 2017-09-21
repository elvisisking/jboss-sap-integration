package com.redhat.iot.trackman.demo.camel;

import java.io.File;

import org.apache.camel.LoggingLevel;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.io.FileUtils;

/**
 * SQLAnywhere demo route.
 */
public class TestRoute extends RouteBuilder {

   @Override
    public void configure() throws Exception {

//      Generate fake data for testing every 5 seconds, send to MQTT topic
//      Comment out for real demo
//      from("timer://heartbeat?fixedRate=true&period=5000")

      /*
      from("file:/Users/ccustine/development/redhat/rh-vertica-demo/com.redhat.iot.vertica.demo.camel/src/test/resources/?fileName=Vertica_Data_PM_short.csv&noop=true")
            .split().tokenize("\\n").streaming()
            .delay(1000)
            .log("Sending ${header.CamelSplitIndex} of ${header.CamelSplitSize}")
            .unmarshal(new CsvDataFormat()
                  .setIgnoreEmptyLines(true)
                  .setUseMaps(true)
                  .setCommentMarker('#')
                  .setHeader(new String[]{"id", "cycle", "settings1", "settings2", "settings3", "s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "s12", "s13", "s14", "s15", "s16", "s17", "s18", "s19", "s20", "s21"}))
            .to("log:Send");
*/

      // Subscribe to topics, unmarshal Kura gzipped/protobuf messages
//	   from("file:/my/test/dir?moveFailed=.error&autoCreate=true&readLockLoggingLevel=WARN&shuffle=true&readLock=idempotent&idempotentRepository=#fileConsumerRepo&readLockRemoveOnCommit=true")
//	    .routeId("ingestionFile")
//	    .convertBodyTo(String.class)
//	    .log(LoggingLevel.INFO, "File received");
	
	   
	  
	  
	   final String sql = FileUtils.readFileToString(new File("./src/main/resources/sqlanywhere/inserts.ddl"));

	   
	   
	      // Subscribe to topics, unmarshal Kura gzipped/protobuf messages
		   from("direct:start")
		    .to("jdbc:sqlanywhere?useHeadersAsParameters=true")
		    .log("result = ${body}")
		    .routeId("ingestionFile")
		    .convertBodyTo(String.class)
		   .to("sql:"+sql);
		   
	   

   }
}