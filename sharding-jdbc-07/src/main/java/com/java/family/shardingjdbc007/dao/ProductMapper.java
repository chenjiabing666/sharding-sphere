package com.java.family.shardingjdbc007.dao;

import com.java.family.shardingjdbc007.model.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ProductMapper {
    @Insert(value = "INSERT INTO `product_base`(shop_id,name,price,origin_address,type_id) VALUE(#{shopId}, #{name}, #{price}, #{originAddress},#{typeId});")
    @Options(useGeneratedKeys=true, keyProperty="productId", keyColumn="product_id")
    int insertProductBase(Product product);
}
