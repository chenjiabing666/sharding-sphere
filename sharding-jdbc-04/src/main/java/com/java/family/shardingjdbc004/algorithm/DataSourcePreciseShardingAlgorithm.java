package com.java.family.shardingjdbc004.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.text.MessageFormat;
import java.util.Collection;

/**
 * @author 公众号：码猿技术专栏
 * @url: www.java-family.cn
 * @description 精确范围分片算法，=/in
 */
public class DataSourcePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        //店铺Id的值
        Long value = shardingValue.getValue();
        //逻辑表名称,product_base
        String logicTableName = shardingValue.getLogicTableName();
        //数据源节点的个数  =2
        int size = availableTargetNames.size();
        //availableTargetNames:ds1/ds2
        return "ds"+(value% size +1);
    }
}
