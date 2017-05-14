## Spring Core Annotation
* @Autowired
	* > for bean injection
	* > byType injection

* @Resource
	* > for bean injection
	* > byName injection

* @Qualifier
	* > for bean byType injection
	* > specify bean name to be injected		

* @Configuration	
	* > Same as __beans...\beans__ in spring XML configuration
	
* @Bean
	* > @Bean is used to explicitly declare a single bean, rather than letting Spring do it automatically as above. It decouples the declaration of the bean from the class definition, and lets you create and configure beans exactly how you choose.
	
* @Component
	* > @Component (and @Service and @Repository) are used to auto-detect and auto-configure beans using classpath scanning. There's an implicit one-to-one mapping between the annotated class and the bean (i.e. one bean per class). Control of wiring is quite limited with this approach, since it's purely declarative.
	* > @Service
	* > @Repository
	* > @Controller