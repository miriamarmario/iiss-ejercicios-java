# Práctica 3: Inyección de dependencias & Aspectos

## Ejercicios propuestos

### Ejercicio 1

Dado los siguientes fragmentos de código responder a las siguientes preguntas:

#### `DBAccess.java`

```java
public interface DBAccess {
    
    public void initConnection();

}
```

#### `DBAccessA.java`

```java
public class DBAccessA implements DBAccess {
    
    public DBAccessA() {}
    
    public void initConnection() {
        System.out.println("Init A connection with database..");
    }

}
```

#### `DBAccessB.java`

```java
public class DBAccessB implements DBAccess {
    
    public DBAccessB() {}
    
    public void initConnection() {
        System.out.println("Init B connection with database..");
    }

}
```

#### `DBClient.java`

```java
public class DBClient {
    
    private DBAccess dbAccess;
    
    public DBClient(DBAccess dbAccess) {
        this.dbAccess = dbAccess;
    }
    
    public void setDBAccess(DBAccess dbAccess) {
        this.dbAccess = dbAccess;
    }
    
    public void getAllFromDataBase() {
        dbAccess.initConnection();
        System.out.println("Returning all data from database..");
    }
    
    public void getSomeDataFromDataBase() {
        dbAccess.initConnection();
        System.out.println("Returning some data from database..");
    }

}
```

#### `Main.java`

```java
public class Main {
    
    public static void main(String args[]) {
        DBAccess dbAccessB = new DBAccessB();
        DBClient client = new DBClient(dbAccessB);
        System.out.println("Querying all data from database..");
        client.getAllFromDataBase();
        
        DBAccess dbAccessA = new DBAccessA();
        client.setDBAccess(dbAccessA);
        System.out.println("Querying some data from database..");
        client.getSomeDataFromDataBase();
    }

}
```

#### Preguntas propuestas

1. ¿Se realiza inyección de dependencias entre las clases anteriores? Si es así, identifique la clase inyectora, el servicio y el cliente.

Sí, se realiza inyección de dependencias entre las clases. La clase inyectora es Main, el servicio es DBAccess, y el cliente es DBClient.

2. En el caso de que exista inyección de dependencias, además indique:
- El método de inyección que se realiza (constructor, propiedad o método).

En este caso, se realiza inyección de dependencias mediante el constructor. La inyección de dependencias se realiza en las siguientes líneas:

- La/s línea/s donde se realiza la inyección de dependencias.

```java
DBAccess dbAccessB = new DBAccessB();
DBClient client = new DBClient(dbAccessB);
```
```java
DBAccess dbAccessA = new DBAccessA();
client.setDBAccess(dbAccessA);
```

### Ejercicio 2

Dado los siguientes fragmentos de código responder a las siguientes preguntas:

#### `Bank.java`

```java
public class Bank {
    
    public Bank() {}
    
    public void createUser() {
        System.out.println("Creating user..");
    }
    
    public void makeTransaction() {
        System.out.println("Making transaction..");
    }
    
    public void takeMoneyOut() {
        System.out.println("Taking money out..");
    }
    
    public void showUsers() {
        System.out.println("Showing users..");
    }

}
```

#### `Main.java`

```java
public class Main {
    
    private static Scanner input = new Scanner(System.in);

    public static void main(String args[]) {
        
        System.out.println("AspectJ Bank");
        System.out.println("------------");
        System.out.println("1 - Create user");
        System.out.println("2 - Make transaction");
        System.out.println("3 - Take money out");
        System.out.println("4 - Show users");
        System.out.println("5  - Exit");
        
        int option = Integer.valueOf(input.nextLine());
        Bank bank = new Bank();
        
        switch(option) {
        case 1:
            bank.createUser();
            break;
        case 2:
            bank.makeTransaction();
            break;
        case 3:
            bank.takeMoneyOut();
            break;
        case 4:
            bank.showUsers();
            break;
        case 5:
            System.out.println("Exiting..");
            break;
        }
    }
    
}
```

#### `LoginAspect.java`

```java
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoginAspect {
    @Before("..TO-DO..")
    public void before(JoinPoint joinPoint){
        //...TO-DO..
    }
    
    @After("..TO-DO..")
    public void after(JoinPoint joinPoint){
        //...TO-DO..
    }
}
```

#### Preguntas propuestas

Para realizar el ejercicio, se usará __maven__ como herramienta de construcción. Para editar el código fuente puede emplearse el entorno de desarrollo que se prefiera (vscode, etc.)

Puede emplear los siguientes comandos para compilar, limpiar y/o generar un fichero _jar_ con todas las dependencias necesarias, preparado para ejecutar:

- `mvn compile ` — compilar todo el código del proyecto

- `mvn clean`  — limpiar el proyecto

- `mvn assembly:single` — generar un jar con las dependencias

- `mvn compile assembly:single` — compilar y generar el jar


Para ejecutar, por ejemplo, una clase principal `Main` dentro del jar generado para el proyecto `ejercicio-aspectj` (según la configuración del `pom.xml` incluida en el proyecto), se puede usar el siguiente comando:

```shel
java -cp target/ejercicio-aspectj-0.0.1-SNAPSHOT-jar-with-dependencies.jar Main
```

El ejercicio está preparado para Java 1.8. Para versiones posteriores de Java 9, puede no funcionar. Para facilitar disponer de varias versiones de Java en la línea de comandos, se recomienda instalar [jEnv](https://www.jenv.be/) y seleccionar una versión del JDK compatible con Java 1.8

Los pasos a completar son:

1. Descargar la __plantilla del proyecto__ disponible en el Campus Virtual.

2. Completar en la clase `LoginAspect.java` las secciones `TO-DO` de forma que se cumplan las siguientes condiciones:

a) Mostrar el mensaje "The login is required" antes de la ejecución de las operaciones `makeTransaction` y `takeMoneyOut`.

Para mostrar el mensaje antes de la ejecución de las operaciones makeTransaction y takeMoneyOut, se debe completar la anotación @Before de la siguiente manera:

```java
@Before("execution(* Bank.makeTransaction()) || execution(* Bank.takeMoneyOut())")
public void before(JoinPoint joinPoint){
    System.out.println("The login is required");
}
```
La expresión "execution(* Bank.makeTransaction()) || execution(* Bank.takeMoneyOut())" indica que se interceptarán las llamadas a los métodos makeTransaction() y takeMoneyOut() de la clase Bank.


b) Mostrar el mensaje "The database is empty" después de la ejecución de la operación `showUsers`.

Para mostrar el mensaje después de la ejecución de la operación showUsers, se debe completar la anotación @After de la siguiente manera:

```java
@After("execution(* Bank.showUsers())")
public void after(JoinPoint joinPoint){
    System.out.println("The database is empty");
}
```
La expresión "execution(* Bank.showUsers())" indica que se interceptará la llamada al método showUsers() de la clase Bank.

3. Finalmente, sustituir el fichero `LoginAspect.java` por el fichero `LoginAspect.aj` incluyendo la misma funcionalidad pero utilizando la sintaxis de AspectJ.

Creamos el fichero LoginAspect.aj con el siguiente contenido:
```java
@Aspect
public class LoginAspect {
    @Before("execution(* Bank.makeTransaction()) || execution(* Bank.takeMoneyOut())")
    public void before(JoinPoint joinPoint){
        System.out.println("The login is required");
    }
    
    @After("execution(* Bank.showUsers())")
    public void after(JoinPoint joinPoint){
        System.out.println("The database is empty");
    }
}
```


## Referencias

### Herramientas de construcción

- [Configuración de las versiones de Java con jEnv](https://www.jenv.be/)
- [Maven](https://maven.apache.org/)

### Inyección de dependencias

- [Dependency Injection Tutorial](https://www.tutorialsteacher.com/ioc/dependency-injection)
- [Spring Framework](https://www.vogella.com/tutorials/SpringDependencyInjection/article.html)
- [Google Guice Framework](https://github.com/google/guice/wiki/GettingStarted)

### Programación orientad a aspectos y AspectJ

- [AspectJ Hello World](https://www.baeldung.com/aspectj)
- [Cheat sheet para la definición de etiquetas en AspectJ](https://blog.espenberntsen.net/2010/03/20/aspectj-cheat-sheet/)
- [Ejemplo de configuración de la etiqueta Before en AspectJ](https://howtodoinjava.com/spring-aop/aspectj-before-annotation-example/)
- [Ejemplo de configuración de la etiqueta After en AspectJ](https://howtodoinjava.com/spring-aop/aspectj-after-annotation-example/)
- [Implementar una anotación a medida de Spring AOP](http://www.baeldung.com/spring-aop-annotation)
- [AOP Blog](https://www.webopedia.com/TERM/A/aspect_oriented_programming.html)
- [AspectJ Documentación oficial](https://www.eclipse.org/aspectj/docs.php)
- [AspectJ en blog Java and Spring Development](https://blog.espenberntsen.net/2010/03/20/aspectj-cheat-sheet/)