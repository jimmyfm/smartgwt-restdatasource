# SmartGWT Rest Data Source

The idea is to create a back-end for [SmartGWT RestDataSource](http://www.smartclient.com/smartgwt/javadoc/com/smartgwt/client/data/RestDataSource.html).

Would be optimal to have a solution working both with Super Dev Mode and a JavaEE container.

Would be great to be able to use @Path annotation and leverage jackson for objects to json conversion. 

## Notes

Jetty as configured in Super Dev Mode does not support @Path annotation, we will need to make some magic to make something working both ways.

[https://www.eclipse.org/jetty/documentation/current/using-annotations.html](https://www.eclipse.org/jetty/documentation/current/using-annotations.html)