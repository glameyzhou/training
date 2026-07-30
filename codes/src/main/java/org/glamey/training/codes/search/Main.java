package org.glamey.training.codes.search;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.Uninterruptibles;
import org.glamey.training.mybatis.domain.UserEntity;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Main main = new Main(jedis);
        Map<String, UserEntity> map = buildUserEntityMap();

        //无返回值的操作
        main.pipeline(pipeline -> map.entrySet().forEach(entry -> pipeline.setex(entry.getKey(), TimeUnit.HOURS.toSeconds(1), JSON.toJSONString(entry.getValue()))));

        //有返回值的操作
        List<String> keys = Lists.newArrayList(map.keySet());
        keys.add("zhouyang");
        Map<String, UserEntity> cacheMap = main.pipeline(keys, (pipeline, key) -> pipeline.get(key), json -> JSON.parseObject(json, UserEntity.class));
        System.out.println(cacheMap);
    }
    private Jedis jedis;


    public Main(Jedis jedis) {
        this.jedis = jedis;
    }

    public void pipeline(Consumer<Pipeline> function) {
        Pipeline pipelined = jedis.pipelined();
        try {
            function.accept(pipelined);
            pipelined.sync();
        } catch (Exception e) {
            throw new RuntimeException("jedis pipeline error", e);
        } finally {
            pipelined.close();
        }
    }

    public <K, R, V> Map<K, V> pipeline(List<K> keys, BiFunction<Pipeline, K, Response<R>> pipelineFunction, Function<R, V> decoderFunction) {
        if (keys == null || keys.isEmpty() || pipelineFunction == null) {
            return Collections.emptyMap();
        }
        Map<K, V> retMap = Maps.newHashMapWithExpectedSize(keys.size());
        Pipeline pipelined = jedis.pipelined();
        try {
            List<Response<R>> responseList = keys.stream().map(k -> pipelineFunction.apply(pipelined, k)).collect(Collectors.toList());
            pipelined.sync();

            for (int i = 0; i < keys.size(); i++) {
                K k = keys.get(i);
                R r = Optional.ofNullable(responseList.get(i)).map(Response::get).orElse(null);
                if (r == null) {
                    continue;
                }
                if (decoderFunction == null) {
                    retMap.put(k, (V) r);
                } else {
                    V decode = decoderFunction.apply(r);
                    retMap.put(k, decode);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("jedis pipeline error, ", e);
        } finally {
            pipelined.close();
        }
        return retMap;
    }


    public static Map<String, List<StoreDTO>> buildMap() {
        List<StoreDTO> list = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            list.add(new StoreDTO("id_" + (i % 10), "name_" + i));
        }
        return list.stream().collect(Collectors.groupingBy(StoreDTO::getStoreId));
    }


    public static void demo(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Map<String, List<StoreDTO>> storeIdListMap = buildMap();
        for (Map.Entry<String, List<StoreDTO>> entry : storeIdListMap.entrySet()) {
            System.out.println(entry.getKey() + "-->" + entry.getValue());
        }

        List<Callable<String>> taskList = Lists.partition(Lists.newArrayList(storeIdListMap.keySet()), 4)
                                               .stream()
                                               .map(storeIdList -> {
                                                   Map<String, List<StoreDTO>> map = storeIdList
                                                           .stream()
                                                           .collect(Collectors.toMap(Function.identity(), x -> storeIdListMap.get(x)));
                                                   return (Callable<String>) () -> fillStoreDTO(map);
                                               }).collect(Collectors.toList());


        List<Map<String, List<StoreDTO>>> collect = Lists.partition(Lists.newArrayList(storeIdListMap.entrySet()), 4)
                                                         .stream()
                                                         .map(x -> x.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                                                         .collect(Collectors.toList());


        executorService.invokeAll(taskList);
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        executorService.shutdown();
    }

    private static String fillStoreDTO(Map<String, List<StoreDTO>> subMap) {
        String threadId = Thread.currentThread().getId() + "-" + Thread.currentThread().getName();
        for (Map.Entry<String, List<StoreDTO>> entry : subMap.entrySet()) {
            entry.getValue().forEach(storeDTO -> {
                storeDTO.setStoreId(storeDTO.getStoreId() + "->" + threadId);
                storeDTO.setStoreName(storeDTO.getStoreName() + "->" + threadId);
            });
            System.out.println(threadId + "->" + entry.getValue());
        }
        return "<>";
    }

    private static Map<String, UserEntity> buildUserEntityMap() {
        Map<String, UserEntity> map = Maps.newHashMap();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setUsername("zhangsan");
        userEntity.setEmail("zhangsan@jd.com");
        userEntity.setAddress("京东总部1号楼");
        userEntity.setCreateTime(new Timestamp(new java.util.Date().getTime()));
        map.put("zhangsan", userEntity);

        userEntity = new UserEntity();
        userEntity.setId(2);
        userEntity.setUsername("lisi");
        userEntity.setEmail("lisi@jd.com");
        userEntity.setAddress("京东总部2号楼");
        userEntity.setCreateTime(new Timestamp(new java.util.Date().getTime()));
        map.put("lisi", userEntity);

        return map;
    }
}
