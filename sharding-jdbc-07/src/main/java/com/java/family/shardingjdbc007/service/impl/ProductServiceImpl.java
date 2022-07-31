package com.java.family.shardingjdbc007.service.impl;

import com.java.family.shardingjdbc007.dao.ProductMapper;
import com.java.family.shardingjdbc007.model.Product;
import com.java.family.shardingjdbc007.service.ProductService;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    //开启对XA事务支持
    @ShardingTransactionType(value = TransactionType.XA)
    @Transactional
    @Override
    public int insertProduct() {
        for (int i = 0; i < 10; i++) {
            Product product = Product.builder()
                    .name("Spring Cloud Alibaba实战课程")
                    .price(189L)
                    .originAddress("码猿技术专栏")
                    .shopId((long)(new Random().nextInt(100)+1))
                    .typeId(1L)
                    .build();
            productMapper.insertProductBase(product);
        }
        System.out.println(1/0);
        return 0;
    }
}
