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
public interface IUserService extends IServiceKey , IService {
    /**
     *
     * @param loginName 用户名
     * @param password 密码
     * @param tenantCode 单例模式下为空
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws KeeperException
     */
    @Command(CircuitBreakerForceOpen = false,ShuntStrategy = AddressSelectorMode.RoundRobin, Strategy = StrategyType.Failover, RequestCacheEnabled = false, InjectionNamespaces = {}, BreakerForceClosed = false,  FallBackName = "")
    String login(String loginName,String password,String tenantCode) throws IOException, InterruptedException, KeeperException;
}
