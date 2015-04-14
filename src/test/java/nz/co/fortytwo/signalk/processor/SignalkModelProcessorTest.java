/*
 *
 * Copyright (C) 2012-2014 R T Huitema. All Rights Reserved.
 * Web: www.42.co.nz
 * Email: robert@42.co.nz
 * Author: R T Huitema
 *
 * This file is provided AS IS with NO WARRANTY OF ANY KIND, INCLUDING THE
 * WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package nz.co.fortytwo.signalk.processor;

import static nz.co.fortytwo.signalk.util.JsonConstants.SELF;
import static nz.co.fortytwo.signalk.util.SignalKConstants.dot;
import static nz.co.fortytwo.signalk.util.SignalKConstants.nav_courseOverGroundTrue;
import static nz.co.fortytwo.signalk.util.SignalKConstants.self;
import static nz.co.fortytwo.signalk.util.SignalKConstants.vessels;
import static nz.co.fortytwo.signalk.util.SignalKConstants.vessels_dot_self_dot;

import java.io.IOException;
import java.util.NavigableMap;

import nz.co.fortytwo.signalk.model.SignalKModel;
import nz.co.fortytwo.signalk.model.impl.SignalKModelFactory;
import nz.co.fortytwo.signalk.server.RouteManagerFactory;
import nz.co.fortytwo.signalk.util.JsonSerializer;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SignalkModelProcessorTest extends CamelTestSupport {

	@Before
	public void setUp() throws Exception {
		RouteManagerFactory.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldMerge() throws IOException {
		SignalKModel signalkModel=SignalKModelFactory.getInstance();
		signalkModel.put(vessels+dot+self, null);
		signalkModel.put(vessels+dot+"366951720", null);
		SignalkModelProcessor p = new SignalkModelProcessor();
		assertEquals(signalkModel.toString(),"{}");
		
		JsonSerializer ser = new JsonSerializer();
		NavigableMap<String, Object> tmp = ser.read("{\"vessels\":{\""+SELF+"\":{\"navigation\":{\"courseOverGroundTrue\":{\"timestamp\":\"2014-08-15T16:00:00.081Z\",\"source\":\"/dev/actisense-N2K-115-128267\",\"value\":172.9},\"speedOverGround\":{\"timestamp\":\"2014-08-15T16:00:00.081Z\",\"source\":\"/dev/actisense-N2K-115-128267\",\"value\":3.85}}}}}");
		
		p.handle(SignalKModelFactory.getWrappedInstance(tmp));
		double cog = (double) signalkModel.getValue(vessels_dot_self_dot+nav_courseOverGroundTrue);
		assertEquals(172.9,cog, 0.001);
	}
	@Test
	public void shouldNotMergeOtherJson() throws IOException {
		SignalKModel signalkModel=SignalKModelFactory.getInstance();
		signalkModel.put(vessels+dot+self, null);
		signalkModel.put(vessels+dot+"366951720", null);
		SignalkModelProcessor p = new SignalkModelProcessor();
		assertEquals(signalkModel.toString(),"{}");
		JsonSerializer ser = new JsonSerializer();
		NavigableMap<String, Object> tmp = ser.read("{\"invalid\":{\""+SELF+"\":{\"navigation\":{\"courseOverGroundTrue\":{\"timestamp\":\"2014-08-15T16:00:00.081Z\",\"source\":\"/dev/actisense-N2K-115-128267\",\"value\":172.9},\"speedOverGround\":{\"timestamp\":\"2014-08-15T16:00:00.081Z\",\"source\":\"/dev/actisense-N2K-115-128267\",\"value\":3.85}}}}}");
		p.handle(SignalKModelFactory.getWrappedInstance(tmp));
		
		Double cog = (Double) signalkModel.getValue(vessels_dot_self_dot+nav_courseOverGroundTrue);
		assertNull(cog);
		assertEquals(signalkModel.toString(),"{}");
		
	}
	@Test
	public void shouldNotMergeNmea() throws Exception {
		SignalKModel signalkModel=SignalKModelFactory.getInstance();
		signalkModel.put(vessels+dot+self, null);
		signalkModel.put(vessels+dot+"366951720", null);
		SignalkModelProcessor p = new SignalkModelProcessor();
		assertEquals(signalkModel.toString(),"{}");
		//make an exchange here
		CamelContext ctx = new DefaultCamelContext(); 
	    Exchange ex = new DefaultExchange(ctx);
	    ex.getIn().setBody("$GPRMC,144629.30,A,5156.91115,N,00434.80383,E,1.689,,011113,,,A*73");
	    p.process(ex);
		assertEquals(signalkModel.toString(),"{}");
		
	}
	@Test
	public void shouldNotMergeAis() throws Exception {
		SignalKModel signalkModel=SignalKModelFactory.getInstance();
		signalkModel.put(vessels+dot+self, null);
		signalkModel.put(vessels+dot+"366951720", null);
		SignalkModelProcessor p = new SignalkModelProcessor();
		assertEquals(signalkModel.toString(),"{}");
		//make an exchange here
		CamelContext ctx = new DefaultCamelContext(); 
	    Exchange ex = new DefaultExchange(ctx);
	    ex.getIn().setBody("!AIVDM,1,1,,B,15MwkRUOidG?GElEa<iQk1JV06Jd,0*6D");
	    p.process(ex);
		assertEquals(signalkModel.toString(),"{}");
		
	}
	@Test
	public void shouldHandleNull() throws Exception{
		SignalKModel signalkModel=SignalKModelFactory.getInstance();
		signalkModel.put(vessels+dot+self, null);
		signalkModel.put(vessels+dot+"366951720", null);
		SignalkModelProcessor p = new SignalkModelProcessor();
		assertEquals(signalkModel.toString(),"{}");
		//make an exchange here
		CamelContext ctx = new DefaultCamelContext(); 
	    Exchange ex = new DefaultExchange(ctx);
	    ex.getIn().setBody(null);
	    p.process(ex);
		assertEquals(signalkModel.toString(),"{}");
	}
	
	
}
