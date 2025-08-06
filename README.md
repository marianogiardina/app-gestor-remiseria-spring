# Sistema Gestor de Remisería

Sistema desarrollado en Java Spring Boot para gestionar una remisería, permitiendo administrar choferes, autos y viajes.

## Descripción

Esta aplicación permite gestionar:
- Choferes (con o sin auto propio)
- Autos de la remisería
- Viajes realizados
<hr/>

## Base de datos

- El script de base de datos se encuentra en db/app_gestor_remiseria_avanzada_spring.sql
<hr/>

## Tecnologías utilizadas

- MySQL
- Java 17
- Spring Boot
<hr/>  

## Aspectos tecnicos

- Login del sistema
  - usuario: invitado
  - contraseña: 123


- Configuracion de base de datos
  - Se encuentra en el archivo src/DAO/DbConn 

````properties
#src/main/resources/application.properties

#Base de datos

spring.datasource.url=jdbc:mysql://localhost:3306/app_gestor_remiseria_avanzada_spring
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

#Hibernate

spring.jpa.hibernate.ddl-auto=update
````
<hr/>

## Flujo de programa

- Crear autos (es obligatorio para testear los choferes que no tienen auto propio)


- Crear choferes (con auto propio y sin), tener en cuenta que para crear un chofer sin auto (o sea, que debe alquilar), es obligatorio seleccionar un auto
  - Al asignar un auto al chofer sin auto propio, se actualizara el estado del auto a no disponible.
  - En caso de editar el chofer (ahora tiene auto propio) o eliminar el chofer, el estado del auto pasara a ser disponible


- Crear viajes
    - Si el estado del viaje se encuentra 'en curso' se actualizara el estado del chofer a no disponible y no contara para consultar los balances
    - Una vez el estado pase a 'finalizado', el estado del chofer volvera a ser disponible
    - Al finalizar el viaje (estado 'finalizado') los kilometros se sumaran al auto del chofer (en caso de que sea auto de la remiseria)


- Consultar sueldos semanales
  - Entrar en la seccion 'Liquidar semana a choferes', esto mostrara la informarcion de cada chofer que realizo viajes(y no se encuentre eliminado) dentro de los ultimos 7 dias al momento de entrar.
  - En la columna 'Sueldo Semanal' es lo que deberia cobrar el chofer.


- Consultar balance mensual
  - Muestra actualizado el balance mensual en el momento de consultar.
