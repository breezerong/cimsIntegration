package com.plm.cimsi.ibpm;

import com.ums.core.cplatform.enums.AddressSelectorMode;
import com.ums.core.cplatform.ioc.IServiceKey;
import com.ums.core.cplatform.runtime.server.implementation.serviceDiscovery.annotations.ServiceBundle;
import com.ums.core.cplatform.support.StrategyType;
import com.ums.core.cplatform.support.annotations.Command;
import com.ums.service.ioc.IService;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;

@ServiceBundle(RouteTemplate = "api/{Service}")
public interface IFlowService extends IServiceKey , IService {
    /**
     * 流程可启动列表
     * @param accessToken 调用接口凭证，唯一用户标识
     * @param page 页码
     * @param limit 每页条数
     * @param sort 非必须，字段前如果加上减号。如sort=- created则为按照created倒序排列
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws KeeperException
     */
    @Command(CircuitBreakerForceOpen = false,ShuntStrategy = AddressSelectorMode.RoundRobin, Strategy = StrategyType.Failover, RequestCacheEnabled = false, InjectionNamespaces = {}, BreakerForceClosed = false,  FallBackName = "")
    String pages(String accessToken, int page,int limit,String sort) throws IOException, InterruptedException, KeeperException;
}
