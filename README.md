# **Ejercicio 10**

### Estructuras de datos utilizadas en el algoritmo

Para almacenar el grafo completo he utilizado un **HashMap<K,V>** donde la clave K es el número de vértice
y V son sus conexiones salientes, es decir, donde K es el origen del borde. Esto nos permite lo siguiente: 

-Añadir nuevos caminos a un vértice dado

-Acceder a la información del grafo a través de la key, operación muy utilizada en el código.

-Controlar la inserción para evitar duplicados y claves a null.

-Al no tener una gran volumetría (en número de vértices ni accesos concurrentes, no necesitamos objetos más especializados como ConcurrentHashMap o SynchronizedMap)

Para almacenar los nodos alcanzables desde un vértice para ser procesados, he utilizado **TreeSet<I>** donde I es 
un Integer que contiene el valor de los vertices alcanzables desde el origen. He utilizado esta estructura por lo siguiente: 

-Se mantiene el orden natural dado que es una ordenación de Integer, lo cual nos viene genial para procesar los caminos en orden 
y evitar duplicidades y mantener el orden en la creación de las tripletas.

-Al ser una estructura utilizada en un método recursivo, el tiempo de acceso (inserción) tiene un coste logarítmico con respecto al número de elementos,
por lo que en las primeras fases y/o con un número de elementos reducidos, es una buena elección en términos de rendimiento.

Finalmente, para el almacenamiento de las tripletas, he decidido almacenarlas en un Set<T> donde T es una clase Triplet, para almacenar los 3 vértices 
unidos y que contienen algún camino en rojo en sus pares por el siguiente motivo: 

-Al ser solo de inserción, solo nos interesa la velocidad de acceso para las inserciones, y ésta, es mayor que TreeSet.

-Evitamos duplicados, el orden, nos da igual, solo nos interesa el tamaño de este Set, que es la solución al problema.

-La clase Triplet contiene en su constructor la utilización de un ArrayList para ordenar los elementos y evitar el abuso de creación de elementos
con las permutaciones de los 3 elementos. Al ser solo 3 elementos hemos utilizado directamente ArrayList con el método _**Collections.Sort(L)**_ ya que 
su performance es buena, si tuviésemos un número elevado de elementos, habríamos optado por otra colección, por ejemplo, LinkedHashSet.

-La clave de la complejidad temporal y de procesado en este algoritmo está precisamente en la ordenación y posterior inserción de estas tripletas en
el Set<T> ya que la tripleta {1,2,3} y {1,3,2} se intentarían insertar como {1,2,3} al estar ordenadas y se descartarían por lo tanto 
las permutaciones en tiempo de inserción y no de procesado (El HashSet no permite estos duplicados)

### **Complejidad temporal del algoritmo**

Dado que tenemos N-1 caminos, descartamos f(n) = n! * n, ya que las permutaciones están restringidas a las 
posibilidades de recorrer un camino y no a las conexiones de cada vértice, es decir, sabemos que un vértice
tiene 1 o 0 conexiones (lo de 0 conexiones y tener nodos isolated lo he contemplado yo como posibilidad, lo normal
con este enunciado es que cada node tenga 1 conexión saliente y 1 conexión entrante)

Por lo que, dado que las permutaciones de estos caminos, donde N=3 , las características del enunciado y 
las estructuras utilizadas, veamos unos ejemplos de ejecución

P = numero de caminos

| Número de caminos | Creación de tripletas validas | Comprobación de caminos |
|-------------------|-------------------------------|-------------------------|
| p=3               | 4                             | 8                       |
| p=4               | 10                            | 17                      |
| p=5               | 20                            | 35                      |



Hemos descartado aquí las inserciones de caminos repetidos, para el coste temporal, ya que la inserción de 
un camino repetido (contiene los mismos 3 elementos independientemente del orden) es 0

Vamos a ver el mejor de los casos (todos los caminos son negros) en relación al coste temporal : 

| Número de caminos | Creación de tripletas validas | Comprobación de caminos |
|-------------------|-------------------------------|-------------------------|
| p=3               | 4                             | 4                       |
| p=4               | 10                            | 10                      |
| p=5               | 20                            | 20                      |


Y ahora con el peor de los casos (todos los caminos son rojos) : 

| Número de caminos | Creación de tripletas válidas | Comprobación de caminos |
|-------------------|-------------------------------|-------------------------|
| p=3               | 4                             | 8                       |
| p=4               | 10                            | 20                      |
| p=5               | 20                            | 40                      |


Por lo que podemos estimar que el coste temporal (siempre tomando el escenario más complejo) del algoritmo es :

**_f(n) = (n-1)^2 + n  + 2((n-1)^2 + n)_** --> **_f(n) = ((n-1)^2 + n)  * 3_**

Contando 1 vez la creación de las tripletas y 2 veces la comprobación de estos caminos (como son caminos de 3 vertices
en un árbol conectado con N-1, tiene sentido que sea *2), es decir {a,b,c} se comprueba la conexión entre a y b, y la 
conexión entre b y c; la conexión entre a y c está implícita, ya que el camino es único y lineal.


