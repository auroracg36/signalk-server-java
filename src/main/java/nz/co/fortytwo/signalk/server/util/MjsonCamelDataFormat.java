
/*
 * Copyright 2012,2013, 2014 Robert Huitema robert@42.co.nz
 * 
 * This file is part of FreeBoard. (http://www.42.co.nz/freeboard)
 * 
 * FreeBoard is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * FreeBoard is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with FreeBoard. If not, see <http://www.gnu.org/licenses/>.
 */
 package nz.co.fortytwo.signalk.server.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.StringCharacterIterator;

import mjson.Json;

import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;
import org.apache.commons.io.IOUtils;


	
	 public class MjsonCamelDataFormat implements DataFormat {


	    public MjsonCamelDataFormat() {
	         
	     }

	     public void marshal(Exchange exchange, Object graph, OutputStream stream) throws Exception {
	         IOUtils.write(((Json)graph).asString(), stream);
	     }

	     public Object unmarshal(Exchange exchange, InputStream stream) throws Exception {
	         return Json.read(new StringCharacterIterator(IOUtils.toString(stream)));
	     }

	  
	 
}