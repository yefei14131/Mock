package org.yefei.qa.mock.cache;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 *
 * BaseCache.java Create on 2017年2月21日 下午9:42:55
 *
 * 类功能说明: GUAVA缓存
 *
 * @author: yefei
 * @date: 2018/8/31 01:55
 *
 */
public abstract class BaseGuavaCache<K, V>
{
    protected LoadingCache<K,V> cache;

    private LoadingCache genCache(long duration, TimeUnit timeUnit) {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(duration, timeUnit)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K k)
                    {
                        return loadData(k);
                    }
                });
    }

    public BaseGuavaCache() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(10000)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K k)
                    {
                        return loadData(k);
                    }
                });
    }

    /**
     * 平滑地更新缓存时间。从原cache中复制所有对象到新cache中，可避免瞬间大量请求穿透到db。
     *
     * @param duration
     * @param timeUnit
     */
    public synchronized void updateCacheTime(long duration, TimeUnit timeUnit) {
        LoadingCache<K, V> oldCache = cache;

        LoadingCache<K, V> newache = genCache(duration, timeUnit);
        ConcurrentMap<K, V> kvConcurrentMap = cache.asMap();
        newache.putAll(kvConcurrentMap);
        cache = newache;
        oldCache.invalidateAll();

    }

    /**
     * 超时缓存：数据写入缓存超过一定时间自动刷新
     * @param duration
     * @param timeUnit
     */
    public BaseGuavaCache(long duration, TimeUnit timeUnit) {
        cache = genCache(duration, timeUnit);
    }

    /**
     * 限容缓存：缓存数据个数不能超过maxSize
     * @param maxSize
     */
    public BaseGuavaCache(long maxSize)
    {
        cache = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K k)
                    {
                        return loadData(k);
                    }
                });
    }

    /**
     * 权重缓存：缓存数据权重和不能超过maxWeight
     * @param maxWeight
     * @param weigher：权重函数类，需要实现计算元素权重的函数
     */
    public BaseGuavaCache(long maxWeight, Weigher<K, V> weigher)
    {
        cache = CacheBuilder.newBuilder()
                .maximumWeight(maxWeight)
                .weigher(weigher)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K k) throws Exception
                    {
                        return loadData(k);
                    }
                });
    }


    /**
     *
     * 缓存数据加载方法
     * @author coshaho
     * @param k
     * @return
     */
    protected abstract V loadData(K k);

    /**
     *
     * 从缓存获取数据
     * @author coshaho
     * @param param
     * @return
     */
    public V getCache(K param)
    {
        return cache.getUnchecked(param);
    }

    /**
     *
     * 清除缓存数据，缓存清除后，数据会重新调用load方法获取
     * @author coshaho
     * @param k
     */
    public void refresh(K k)
    {
        cache.refresh(k);
    }

    /**
     *
     * 主动设置缓存数据
     * @author coshaho
     * @param k
     * @param v
     */
    public void put(K k, V v)
    {
        cache.put(k, v);
    }
}
