package org.bupt.scaffold.rpcserver.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.bupt.scaffold.rpcapi.dubbo.DubboService;


@Service
public class DubboServiceImpl implements DubboService {

	@Override
	public String hello(String text) {
		return "hello:" + text;
	}
}
