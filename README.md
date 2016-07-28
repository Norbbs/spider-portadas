# spider-portadas
Spider encargado de buscar portadas de libros en la web usando para ello un repositorio de urls.

El proyecto se encuentra desacoplado en 5 componentes:
1. com.norbs.spider.common - Maneja datos generales y comunes a los demás componentes.
2. com.norbs.spider.entity - Capa de persistencia en la que se encuentran las entidades a trabajar basada en anotaciones JPA 2.
3. com.norbs.spider.dao - Capa de acceso a datos (Data Access Object).
4. com.norbs.spider.service - Capa de lógica de negocio en la que están los procesos para cargar catálogos de libros y el spider para descargar las portadas de libros.
5. com.norbs.spider.web - Aplicación cliente que consume los servicios proporcionados por la capa de negocio.