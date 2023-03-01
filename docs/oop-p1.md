# Práctica 1: Herencia, composición y polimorfismo

## Ejercicios propuestos

### Ejercicio 1

Dados los siguientes fragmentos de código, responder a las siguientes preguntas:

#### `ElementsSet.java`

```java
public class ElementsSet<E> extends HashSet<E> {
    //Number of attempted elements insertions using the "add" method
    private int numberOfAddedElements = 0;

    public ElementsSet() {}

    @Override
    public boolean add(E element) {
        numberOfAddedElements++; //Counting the element added
        return super.add(element);
    } 

    @Override
    public boolean addAll(Collection<? extends E> elements) {
        numberOfAddedElements += elements.size(); //Counting the elements added
        return super.addAll(elements);
    } 

    public int getNumberOfAddedElements() {
        return numberOfAddedElements;
    }
}
```

#### `Main.java`

```java
    ...
    ElementsSet<String> set = new ElementsSet<String>();
    set.addAll(Arrays.asList("One", "Two", "Three"));
    System.out.println(set.getNumberOfAddedElements());
    ...
```

#### Preguntas propuestas

a) ¿Es el uso de la herencia adecuado para la implementación de la clase `ElementsSet`? ¿Qué salida muestra la función `System.out.println` al invocar el método `getNumberOfAddedElements`, 3 o 6?

No, no es adecuado. Es importante tener en cuenta que el uso directo de las funciones proporcionadas por HashSet de Java a través de la herencia no es recomendable. Esto se debe a que la superclase HashSet ya registra el número de elementos almacenados en el conjunto, lo que significa que no es necesario tener un atributo adicional para guardarlo. 

La respuesta a la segunda pregunta es 6. Debido a la adición duplicada de datos, el método "getNumberOfAddedElements" devolverá este valor.

b) En el caso de que haya algún problema en la implementación anterior, proponga una solución alternativa usando composición/delegación que resuelva el problema.

Usando la composición, el programa quedaría así: 
```java
public class ElementsSet<E>{
    private HashSet<E> set; //Composición.

    public ElementSet(){ set = new HashSet<E>(); }

    public boolean add (E element){ return set.add(element);}

    public boolean addAll(Collection <? extends E> elements) { return set.addAll(elements);}

    public int getNumberOfAddedElements(){ return set.size(); }
}
```

### Ejercicio 2

Dado los siguientes fragmentos de código responder a las siguientes preguntas:

#### `Animal.java`

```java
public abstract class Animal {
    //Number of legs the animal holds
    protected int numberOfLegs = 0;

    public abstract String speak();
    public abstract boolean eat(String typeOfFeed);
    public abstract int getNumberOfLegs();
}
```

#### `Cat.java`

```java
public class Cat extends Animal {
    @Override
    public String speak() {
        return "Meow";
    }

    @Override
    public boolean eat(String typeOfFeed) {
        if(typeOfFeed.equals("fish")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getNumberOfLegs() {
        return super.numberOfLegs;
    }
}
```

#### `Dog.java`

```java
public class Dog extends Animal {
    @Override
    public String speak() {
        return "Woof";
    }

    @Override
    public boolean eat(String typeOfFeed) {
        if(typeOfFeed.equals("meat")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getNumberOfLegs() {
        return super.numberOfLegs;
    }
}
```

#### `Main.java`

```java
    ...
    Animal cat = new Cat();
    Animal dog = new Dog();
    System.out.println(cat.speak());
    System.out.println(dog.speak());
    ...
```

#### Preguntas propuestas

a) ¿Es correcto el uso de herencia en la implementación de las clases `Cat` y `Dog`? ¿Qué beneficios se obtienen?

A las clases Animal, Cat y Dog le falta el constructor, y por tanto no podríamos especificar el número de piernas que tienen. En este cado, tnto los perros como los gatos tendrían 0 patas. Además como los métodos no están implementados, si declaramos un objeto de tipo Animal no podríamos usar ninguno de ellos.

También tanto en Cat como en Dog, se llama al método getNumberOfLegs de la clase Animal, que no está implementado por lo que no daría ningún resultado. Además el método observador que debería devolver el número de piernas no devuelve nada.

Por estos motivos la herencia no está bien usada.

Los beneficios que se obtienen son que se puede crear un objeto tipo Cat y Dog, y además un objeto tipo Animal sin especificar.

b) En el caso de que el uso de la herencia no sea correcto, proponga una solución alternativa. ¿Cuáles son los beneficios de la solución propuesta frente a la original?

#### `Animal.java`

```java
public abstract class Animal {
    //Number of legs the animal holds
    protected int numberOfLegs = 0;
    public Animal(legs) { numberOfLegs = legs; } //Añadimos el constructor de Animal.
    public abstract String speak();
    public abstract boolean eat(String typeOfFeed);
    public          int getNumberOfLegs(){ return numberOfLegs; } //Ponemos bien el método observador.
}
```

#### `Cat.java`

```java
public class Cat extends Animal {
    
    public Cat(){ super(4); } //Añadimos el constructor de Cat, y le pasamos 4 al constructor de Animal, porque todos los tienen 4 patas.

    //Como los métodos no están implementados en la clase base, podemos quitar el @Override
    public String speak() { return "Meow"; }

    public boolean eat(String typeOfFeed) {
        if(typeOfFeed.equals("fish")) {
            return true;
        } else {
            return false;
        }
    }
    public int getNumberOfLegs() { return super.getnumberOfLegs(); } //Ahora que el observador de la clase Animal está implementado, podemos llamarle desde la clase derivada y que nos devuelva el número de piernas.
}
```

#### `Dog.java`

```java
public class Dog extends Animal {
   
   public Dog(){ super(4); } //Añadimos constructor y hacemos lo mismo que en Cat.
   
   //Como los métodos no están implementados en la clase base, podemos quitar el @Override
    public String speak() { return "Woof"; }

    public boolean eat(String typeOfFeed) {
        if(typeOfFeed.equals("meat")) {
            return true;
        } else {
            return false;
        }
    }

    public int getNumberOfLegs() { return super.getnumberOfLegs(); } //Ahora que el observador de la clase Animal está implementado, podemos llamarle desde la clase derivada y que nos devuelva el número de piernas.
  
}
```