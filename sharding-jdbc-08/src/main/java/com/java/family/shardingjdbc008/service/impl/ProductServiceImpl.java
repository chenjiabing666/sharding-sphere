package com.java.family.shardingjdbc008.service.impl;

import com.java.family.shardingjdbc008.dao.ProductMapper;
import com.java.family.shardingjdbc008.model.Product;
import com.java.family.shardingjdbc008.service.ProductService;
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

    @Transactional
    @ShardingTransactionType(value = TransactionType.BASE)
    @Override
    public int insertProduct() {
        for (int i = 0; i < 10; i++) {
            Product product = Product.builder()
                    .name("Spring Cloud Alibaba实战课程")
                    .price(159L)
                    .originAddress("码猿技术专栏")
                    .shopId((long)(new Random().nextInt(100)+1))
                    .typeId(1L)
//                    .productId((long) ++i)
                    .build();
            productMapper.insertProductBase(product);
        }
        System.out.println(1/0);
        return 0;
    }

    @Transactional
    @ShardingTransactionType(value = TransactionType.BASE)
    @Override
    public int insertProduct2() {
        for (int i = 0; i < 10; i++) {
            Product product = Product.builder()
                    .name("Spring Cloud Alibaba实战课程")
                    .price(159L)
                    .originAddress("码猿技术专栏")
                    .shopId((long)(new Random().nextInt(100)+1))
                    .typeId(1L)
                    .productId((Long) (new SnowflakeShardingKeyGenerator().generateKey()))
                    .build();
            productMapper.insertProductBase(product);
        }
        return 0;
    }
}
