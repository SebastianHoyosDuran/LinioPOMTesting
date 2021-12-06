# RetoSQALinio
Este proyecto es realizado con la intención de poner en práctica los conocimientos adquiridos por en el semillero de automatización de pruebas impartido por la empresa SQA.

## Importante !
Linio es una página muy dinámica, con muchos cambios, lo que quiero decir con este es que al día que se desarrolló esta prueba no había ningún mensaje emergente acerca de una promoción o evento, si al momento de realizar esta prueba hay una ventana emergente, muy seguramente dará fallo. También sus productos tienen demasiado movimiento, puede que algún producto de la lista, deje de haber stock, cambie de nombre, cambie de precio. Cualquier cambio en algún producto puede generar falla en la prueba.

### Herramientas
_Las herramientas empleadas para este proyecto son:_
* Java
* Selenium Web Driver
* POM
* Cucumber
* Gherkin


### Requisitos 🔧

_Para este reto, nuestra automatización debía cumplir con los siguientes requisitos:_

```
Leer un archivo Excel que contendrá como mínimo 5 productos pertenecientes a su página (El archivo Excel lo creara cada uno)
Debe buscar cada producto del archivo Excel en sus respectivas páginas y seleccionarlos (Este proceso es por cada producto)
Debemos validar que el nombre del producto que seleccionemos concuerde con el del archivo Excel.
```

_Pero sin duda, el más importante para mí es:_
```
Dejar que su imaginacion y creatividad fluyan
```

_Por esta razón decidí ir un poco más allá para ponerme un desafío que aunque me hizo sudar en algunos momentos, aprendí bastante, incluso tuve que buscar en foros, Stack Overflow, en blogs e incluso videos de Hindúes para encontrar cierta información clave._

_Entonces el requerimiento funcional extra que añadí es:_
```
Añadir cada item al carrito
Poder manipular la ventana modal que aparece
Abrir el carrito despues de añadir todos los objetos
Validar el precio final obteniendolo desde el xpath y comparandolo con el valor que se le pasa como parametro
```
### Video Explicativo
_Antes de continuar, me gustaría añadir que grabé un video explicando la prueba paso a paso y también ejecutándola en tiempo real_
* [Video](https://youtu.be/XwREPo-XmYg) - Presentación del proyecto


### Acerca de los métodos empleados:
_Para la automatización los métodos principales(Digo principales porque estos a su vez son un conjunto de métodos que se unen para completar 
con la funcionalidad) empleados son los siguientes:_
```
Abrir el navegador de Chrome y abrir la pagina de linio (https://www.linio.com.co/)
Abrir el archivo Excel en donde esta la lista de los nombres de los productos y repetir las siguientes acciones con cada uno de los producto:
    * Colocar el nombre del producto en la barra buscador
    * Daler click en la lupa para que busque el producto
    * En la ventana en donde aparecen todos los productos con el nombre similar , buscar el producto que tenga exactamente el mismo nombre que copiamos en la barra de busqueda , incluso con la misma cantidad de espacios
    * Darle click al producto encontrado
    * En la pagina del producto , obtener su nombre y compararlo con el nombre obtenido del excel
    * Darle click al boton de añadir al carrito
    * Cuando se despliegue la ventana modal confirmando que el producto se añadió al carrito, darle click al boton de seguir comprando para que se repita el proceso
Una vez se haya hecho el proceso anterior con todos los producto, se le da click al carrito
Una vez en la pagina del carrito , buscamos mediante el xpath el valor total de la compra y hacemos una validacion, que este valor tiene que ser exactamente con el valor que se le ingresa como parametro
```

_Si todos los pasos anteriormente mencionados se cumplen sin ningún contratiempo , quiere decir que la prueba se ha realizado con éxito y sin errores._




