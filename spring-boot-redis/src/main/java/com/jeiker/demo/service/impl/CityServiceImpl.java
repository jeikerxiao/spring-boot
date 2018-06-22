package com.jeiker.demo.service.impl;

import com.jeiker.demo.core.AbstractService;
import com.jeiker.demo.dao.CityMapper;
import com.jeiker.demo.model.City;
import com.jeiker.demo.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Author : xiao
 * @Date : 2017/07/31
 */
@Service
@CacheConfig(cacheNames="cityCache") // 本类内方法指定使用缓存时，默认的名称就是 cityCache
@Transactional
public class CityServiceImpl extends AbstractService<City> implements CityService {

    private Logger log = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityMapper cityMapper;

    // 因为必须要有返回值，才能保存到数据库中，如果保存的对象的某些字段是需要数据库生成的，
    //那保存对象进数据库的时候，就没必要放到缓存了
    //必须要有返回值，否则没数据放到缓存中
    @CachePut(key="#p0.id")  //#p0表示第一个参数
    public City insert(City city){
        cityMapper.insert(city);
        //u对象中可能只有只几个有效字段，其他字段值靠数据库生成，比如id
        return cityMapper.selectByPrimaryKey(city.getId());
    }

    @CachePut(key="#p0.id")
    public City updateCity(City city){
        cityMapper.updateByPrimaryKeySelective(city);
        //可能只是更新某几个字段而已，所以查次数据库把数据全部拿出来全部
        return cityMapper.selectByPrimaryKey(city.getId());
    }

    // @Cacheable 会先查询缓存，如果缓存中存在，则不执行方法
    @Cacheable(key="#p0")
    public City find(Integer id){
        log.info("根据id=" + id +"获取用户对象，从数据库中获取");
        return cityMapper.selectByPrimaryKey(id);
    }

    public List<City> findAll() {
        return cityMapper.selectAll();
    }

    //删除缓存名称为 userCache ,key等于指定的id对应的缓存
    @CacheEvict(key="#p0")
    public void delete(Integer id){
        cityMapper.deleteByPrimaryKey(id);
    }

    //清空缓存名称为userCache（看类名上的注解)下的所有缓存
    //如果数据失败了，缓存时不会清除的
    @CacheEvict(allEntries = true)
    public void deleteAll(){
//        cityMapper.deleteByPrimaryKey();
    }

}
