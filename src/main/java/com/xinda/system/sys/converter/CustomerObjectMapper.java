package com.xinda.system.sys.converter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinda.system.sys.contant.BaseConstants;

/**
 * 解决Date类型返回json格式为自定义格式
 * 
 * @author coudy
 * 
 *
 *         2017年2月21日
 */
public class CustomerObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	public CustomerObjectMapper() {
		super();
		this.setTimeZone(TimeZone.getDefault());
		CustomSerializerFactory factory = new CustomSerializerFactory();
		factory.addGenericMapping(Date.class, new JsonSerializer<Date>() {
			@Override
			public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider provider)
					throws IOException {
				SimpleDateFormat sdf = new SimpleDateFormat(BaseConstants.DATE_TIME_FORMAT);
				jsonGenerator.writeString(sdf.format(value));
			}
		});
	}

}
