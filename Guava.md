## Guava

https://www.cnblogs.com/vikde/p/8045226.html

//设置并发级别为8，并发级别是指可以同时写缓存的线程数
concurrencyLevel(8)

//设置缓存容器的初始容量为10
initialCapacity(10)

//设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
maximumSize(100)

//是否需要统计缓存情况,该操作消耗一定的性能,生产环境应该去除
recordStats()

//设置写缓存后n秒钟过期
expireAfterWrite(17, TimeUnit.SECONDS)

//设置读写缓存后n秒钟过期,实际很少用到,类似于expireAfterWrite
expireAfterAccess(17, TimeUnit.SECONDS)

//只阻塞当前数据加载线程，其他线程返回旧值
refreshAfterWrite(13, TimeUnit.SECONDS)

设置缓存的移除通知
removalListener