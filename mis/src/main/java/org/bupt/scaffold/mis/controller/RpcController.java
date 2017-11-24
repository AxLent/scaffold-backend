package org.bupt.scaffold.mis.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.thrift.protocol.TProtocol;
import org.bupt.common.bean.ResponseResult;
import org.bupt.scaffold.mis.constant.EnvConsts;
import org.bupt.scaffold.rpcapi.dubbo.DubboService;
import org.bupt.scaffold.rpcapi.thrift.ThriftService;
import org.bupt.common.thrift.ThriftConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * UserController
 */
@RestController
@RequestMapping("api/rpc")
public class RpcController {

	private static final Logger logger = LoggerFactory.getLogger(RpcController.class);


	@Autowired
	private EnvConsts envConsts;

	@Autowired
	private ThriftConnectionService thriftConnectionService;

	@Reference
    private static DubboService dubboService;

	/**
	 * Thrift Service Test
	 *
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "thrift", method = RequestMethod.POST)
	public ResponseResult thrift(@RequestBody Map<String, Object> params) {


		TProtocol protocol = thriftConnectionService.getConnection();
		ThriftService.Client thriftSerivce = new ThriftService.Client(protocol);
		String result = null;
		try {
			result = thriftSerivce.word_pos("test");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseResult.error("error");
		} finally {
			thriftConnectionService.returnConnection(protocol);
		}

		logger.info(result);
		return ResponseResult.success();
	}


	/**
	 * Dubbo Service Test
	 *
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "dubbo", method = RequestMethod.POST)
	public ResponseResult dubbo(@RequestBody Map<String, Object> params) {

		logger.info(dubboService.hello("ken"));
		return ResponseResult.success();
	}


}
