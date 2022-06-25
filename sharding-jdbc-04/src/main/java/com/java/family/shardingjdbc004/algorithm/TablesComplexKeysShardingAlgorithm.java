package com.java.family.shardingjdbc004.algorithm;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 公众号：码猿技术专栏
 * @url: www.java-family.cn
 * @description 复合分片算法，支持多个分片键
 */
public class TablesComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {

    public final static String LOGIC_ORDER_ID="order_id";
    public final static String LOGIC_USER_ID="user_id";

    /**
     * @param availableTargetNames 可用的数据源或者表
     * @param shardingValue 分片键的值，分为两类
                            1. columnNameAndRangeValuesMap(范围查询的值)
                            2. columnNameAndShardingValuesMap（精确查询的值）
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {
        //范围查询的分片键值集合:userId/orderId
        Map<String, Range<Long>> shardingRangeMaps = shardingValue.getColumnNameAndRangeValuesMap();

        //精确查询的分片键值集合:userId/orderId
        Map<String, Collection<Long>> shardingMaps = shardingValue.getColumnNameAndShardingValuesMap();

        //TODO 范围查询暂时不支持，后续可以自己业务需求自己实现
        if (!shardingRangeMaps.isEmpty()){
            throw new UnsupportedOperationException("只支持精确查询，不支持范围查询");
        }

        //逻辑表的名称，t_order
        String logicTableName = shardingValue.getLogicTableName();
        //orderIds：订单编号分片键值的集合
        Collection<Long> orderIds = shardingMaps.getOrDefault(LOGIC_ORDER_ID,new ArrayList<>());
        //userIds：用户id分片键值的集合
        Collection<Long> userIds = shardingMaps.getOrDefault(LOGIC_USER_ID,new ArrayList<>());
        List<Long> ids=new ArrayList<>();
        ids.addAll(orderIds);
        ids.addAll(userIds);

        //id%2+1->t_order_1/t_order_2
        return ids.stream().map(id-> MessageFormat.format(logicTableName+"_{0}",id%availableTargetNames.size()+1)).collect(Collectors.toSet());
    }
}
