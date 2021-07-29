package cn.wjzhang.shopproduct.service.impl;

import cn.wjzhang.shopcommon.entity.Product;
import cn.wjzhang.shopproduct.dao.ProductDao;
import cn.wjzhang.shopproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;


    @Override
    public Product findByPid(Integer pid) {
        return productDao.findById(pid).get();
    }
}
