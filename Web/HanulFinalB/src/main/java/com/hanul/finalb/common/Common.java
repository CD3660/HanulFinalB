package com.hanul.finalb.common;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:db/conninfo.properties")
public class Common {
	
}
