# spider-portadas
Aplicación que se encarga de cargar catálogos de libros (en formato XML) y de buscar portadas de dichos libros en la web y descargarlas en un directorio específico.
Para realizar ambas acciones la aplicación cuenta con un proceso de autenticación de usuarios.

El proyecto se encuentra desacoplado en 6 componentes o Capas:
- com.norbs.spider.common: Donde se manejan procesos genéricos como utilidades empleados por otros componentes que facilitan sus operaciones.
- com.norbs.spider.entity: Aquí se define la capa de persistencia en el que se encuentran las entidades a trabajar basada en anotaciones JPA 2.
- com.norbs.spider.dao: Capa de acceso a datos (Data Access Object) En este nivel se definen todas las clases DAO, las cuales serán el único vínculo del sistema con las entidades en base de datos.
- com.norbs.spider.service: Capa de lógica de negocio que incluye todas las entidades de negocio que implementan las reglas que deben llevar a cabo las operaciones para cargar catálogos de libros y el spider para descargar las portadas.
- com.norbs.spider.web: Capa de presentación es la que da apariencia visual al sistema y está construída con Spring MVC.
- com.norbs.spider.rest - Web service empleado como interfaz para que pueda ser accedido por otras aplicaciones a través de Rest Controllers de Spring.

Tecnologías empleadas:
- Java EE 7
- Spring framework 4.2.6
- Spring security 4.0.1
- Maven 4.0.0
- JPA 2
- Eclipselink 2.5.2
- PostgreSQL 9.5

Directorios:
- Catalogos de libros: /catalogos
- Portadas de libros descargadas: /portadas 

Infraestructura:
- No es necesario ejecutar ningún script en la base de datos para la creación de tablas, solo basta con crear una base de datos y llamar "spider", las tablas se generarán automáticamente cuando se inicie la aplicación.
- La unidad de persistencia se encuentra en com.norbs.spider.dao por si se desea cambiar el driver de base de datos y credenciales.
- Se debe configurar el servidor donde estará alojada la aplicación en el caso de Glassfish puede basta con crear un Pool de conexiones "SpiderPool" y el recurso JDBC está definido como "jdbc/spider".