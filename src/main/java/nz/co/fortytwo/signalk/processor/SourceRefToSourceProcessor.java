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

import static nz.co.fortytwo.signalk.util.SignalKConstants.dot;
import static nz.co.fortytwo.signalk.util.SignalKConstants.source;
import static nz.co.fortytwo.signalk.util.SignalKConstants.sourceRef;
import static nz.co.fortytwo.signalk.util.SignalKConstants.sources;

import java.util.Map.Entry;
import java.util.NavigableMap;

import nz.co.fortytwo.signalk.model.SignalKModel;
import nz.co.fortytwo.signalk.util.JsonSerializer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager; import org.apache.logging.log4j.Logger;

/**
 * Replaces $source with the actual source object
 * 
 * @author robert
 *
 */
public class SourceRefToSourceProcessor extends SignalkProcessor implements Processor {

	private static Logger logger = LogManager.getLogger(SourceRefToSourceProcessor.class);
	JsonSerializer ser = new JsonSerializer();
	
	public void process(Exchange exchange) throws Exception {
		
		if (exchange.getIn().getBody()==null)
			return;
		if(logger.isDebugEnabled())logger.debug("Processing:"+exchange.getIn().getBody().getClass());
		
		if (exchange.getIn().getBody() instanceof SignalKModel){
			SignalKModel model = exchange.getIn().getBody(SignalKModel.class);
			if(logger.isDebugEnabled())logger.debug("Processing:"+model);
			for(String key : model.getKeys()){
				if(key.endsWith(dot+sourceRef)){
					String val = (String) model.get(key);
					if(logger.isDebugEnabled())logger.debug("key:"+key+", value:"+val);
					//val is the path
					model.getFullData().remove(key);
					//fix key
					key = key.replace(dot+sourceRef, dot+source);
					NavigableMap<String, Object> node = signalkModel.getSubMap(sources+dot+val);
					int pos = (sources+dot+val).length();
					//node is the source object 
					if(node!=null){
						for(Entry<String, Object> entry:node.entrySet()){
							String nodeKey = entry.getKey();
							model.getFullData().put(key+nodeKey.substring(pos), entry.getValue());
						}
					}
				}
			}
			exchange.getIn().setBody(model);
		}
		if(logger.isDebugEnabled())logger.debug("Outputting:"+exchange.getIn());
	}

}
