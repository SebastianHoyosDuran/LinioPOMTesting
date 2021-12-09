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
* [![Alt text](https://img.youtube.com/vi/XwREPo-XmYg/0.jpg)](https://www.youtube.com/watch?v=XwREPo-XmYg)


### Acerca de los pasos que realiza la automatizacion:
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


## Paquetes, Clases y Metodos:
_A continuación mostraremos los códigos y de las clases a que paquete pertenecen y cuál es su función_

### Paquete Driver
#### Clase GoogleChromeDriver
##### Metodo ChromeDrive
_Este método tiene el parámetro String de Url o también conocido como link, la intención de este método es abrir el navegador web con unas configuraciones definidas e iniciar el navegador en el link que le pasamos como parámetro_
```
public static void ChromeDriver(String url){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-infobars");
        driver = new ChromeDriver(options);
        driver.get(url);

    }
```

### Paquete files
#### Clase Lectura Excel
##### Metodo LecturaExcel
_Este método tiene como fin leer un archivo Excel, como parámetros acepta dos: String rutaDeExcel y String hojaDeExcel, el parámetro rutaDeExcel, se debe indicar que lugar se encuentra el archivo Excel que contiene la información. Con el parámetro hojaDeExcel se da el nombre de la hoja de Excel en donde se encuentra la información a leer_

_Este método retorna un la información en un Arraylist la cual se deberá usar más adelante_

```

public class LecturaExcel {
    public static ArrayList<Map<String, String>> leerDatosDeHojaDeExcel(String rutaDeExcel, String hojaDeExcel) throws IOException {
        ArrayList<Map<String, String>> arrayListDatoPlanTrabajo = new ArrayList<Map<String, String>>();
        Map<String, String> informacionProyecto = new HashMap<String, String>();
        File file = new File(rutaDeExcel);
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet newSheet = newWorkbook.getSheet(hojaDeExcel);
        Iterator<Row> rowIterator = newSheet.iterator();
        Row titulos = rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                cell.getColumnIndex();
                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        informacionProyecto.put(titulos.getCell(cell.getColumnIndex()).toString(), cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        informacionProyecto.put(titulos.getCell(cell.getColumnIndex()).toString(), String.valueOf((long) cell.getNumericCellValue()));
                        break;
                    case BLANK:
                        informacionProyecto.put(titulos.getCell(cell.getColumnIndex()).toString(), "");
                        break;
                    default:
                }
            }
            arrayListDatoPlanTrabajo.add(informacionProyecto);
            informacionProyecto = new HashMap<String, String>();
        }
        return arrayListDatoPlanTrabajo;
    }
```

### Paquete pages
_En estas clases que pertenecen al paquete pages , es en donde guardamos los xpath de los elementos que necesitamos como botones, texto y barras de búsqueda_
#### Clase PgInicio
_En la clase PgInicio tenemos los elementos de La Barra de Búsqueda denotado como txtBuscador, el botón de la barra de búsqueda denotado como btnBuscador y algunos botones para el manejo de ventanas emergentes_

_También contamos con los métodos get de estos elementos para hacer llamados a estos elementos cuando sean necesarios_


```
 By txtBuscador = By.xpath("//div[@class = 'input-group']//input[@type='search' and @placeholder='Busca productos']");
    By btnBuscador = By.xpath("//div[@class='input-group-btn']//button[@class='btn btn-primary btn-search']//span[@class='icon icon-inverse']");
    
    By btnPopUpCerrarNoTeLoPierdas = By.xpath("//div[@class='dy-lb-close']");
    
    By btnPorAhoraNo = By.xpath("//button[@id='onesignal-slidedown-cancel-button']");

    public By getBtnPorAhoraNo() {
        return btnPorAhoraNo;
    }

    public By getTxtBuscador() {
        return txtBuscador;
    }

    public By getBtnBuscador() {
        return btnBuscador;
    }

    public By getBtnPopUpCerrarNoTeLoPierdas() {
        return btnPopUpCerrarNoTeLoPierdas;
    }
```

#### Clase PgCarrito

_Contamos con los atributos By del botón para abrir el carrito llamado btnAbrirCarrito y con el elemento del texto en donde obtenemos el valor total de los productos y con sus costos adicionales como los son el impuesto y costos de envío. También contamos con sus respectivos métodos get para poder hacer llamado a los atributos cuando sea necesario_

```
    By btnAbrirCarrito = By.xpath("//span[@class='quantity-icon quantity-icon-notification' and @id='cart-counter']");
    By txtTotal = By.xpath("//li[@class='summary-total']//h3[@class='d-flex']//span[@class='price-main-md float-right ml-auto' and @ngbind='cart.data.grandTotal|formatMoney']");

    public By getBtnAbrirCarrito() {
        return btnAbrirCarrito;
    }

    public By getTxtTotal() {
        return txtTotal;
    }

```

#### Clase PgBusquedaItem

_Esta es la clase que le pertenece a la página después de darle clic al botón de lupa, es decir es la página en donde nos aparecen todos los productos con el nombre ingresado_
_En esta clase contamos con el xpath del elemento buscado, es decir es que se va elemento a elemento buscando el ítem que tiene exactamente el mismo nombre._
_En esta clase se maneje el método get del atributo, pero también el método set, dado que se va a necesitar porque se maneja la búsqueda de diferentes elementos, el método set recibe como parámetro el String producto_

```
  
    By txtElementoBuscado = By.xpath("//div[@class='image-container']//figure//picture[@class='image-wrapper']//source[@type='image/webp' and @srcset='//i.linio.com/p/83c2973b80211132e46892e5ed1b87ec-catalog.webp']");

    public By getTxtElementoBuscado() {
        return txtElementoBuscado;
    }

    public void setTxtElementoBuscado(String producto) {
        this.txtElementoBuscado = By.xpath("//div[@class='detail-container']//*[text()[contains(.,'"+producto+"')]]");
    }
```

#### Clase PgVisualizarItem

_Esta es la clase que le pertenece a la pagina en donde podemos visualizar el ítem y todos sus detalles, en esta clase manejamos los xpath de txtItemSeleccionado el cual obtiene el nombre del ítem, el btnAnadirAlCarrito el cual es el botón para poder añadir el producto al carrito de compras y por último , el botón Seguir comprando, porque una vez que le damos clic a añadir al carrito, se despliega una página modal, al cual debemos indicar si queremos ir al carrito para terminar nuestra compra o si quieres seguir buscando Ítems para añadirlo al carrito_

_En esta clase también estamos manejando un método set para el txtItemSeleccioando dado que debemos automatizar la búsqueda del nombre del producto, este método set recibe como parámetro un String el cual debe contener el nombre del producto buscado_

```
  
    By btnAnadirAlCarrito = By.xpath("//button[@id='buy-now']");

    By btnSeguirComprando = By.xpath("//div[@class='modal-dialog modal-dialog-fluid success']//div[@class='alert alert-body']//a[@class='header-icon-close-container btn-link']//span[@class='icon header-icon-close']");"
    

    By txtItemSeleccionado = By.xpath("//h1[@class='col-12 product-title-box']//span[contains(text(),'producto')]");



    public By getTxtItemSeleccionado() {
        return txtItemSeleccionado;
    }

    public void setTxtItemSeleccionado(String nombreProducto) {
        this.txtItemSeleccionado = By.xpath("//h1[@class='col-12 product-title-box']//span[contains(text(),'"+nombreProducto+"')]");

    }

    public By getBtnAnadirAlCarrito() {
        return btnAnadirAlCarrito;
    }

    public By getBtnSeguirComprando() {
        return btnSeguirComprando;
    }
    
```

### Paquete Steps
_El paquete steps en donde se crean todos los métodos , como estamos usando el modelo POM , que ve a las páginas como objetos , entonces cada página va a tener sus métodos_
#### Clase InicioSteps

##### Metodo abrirPagina
_Este método abre el navegador en la Página de https://www.linio.com.co/_

```
    public void  abrirPagina(){
        GoogleChromeDriver.ChromeDriver("https://www.linio.com.co/");
    }
```


##### Metodo cerrarPagina
_Metodo para cerrar el navegador usando el metodo quit()_
```
 public void cerrarPagina(){
        GoogleChromeDriver.driver.quit();
    }
```

##### Metodo buscarItem
_Metodo el cual recibe un String como parámetro, este método coloca nuestro parámetro en la barra de búsqueda de producto de Linio y le da Clic, una vez cargue la página con todos los productos con un nombre similar, le va a dar clic al producto el cual tenga exactamente el mismo nombre que le pasamos como parámetro_

```
public void buscarItem(String item) {
        GoogleChromeDriver.driver.findElement(pgInicio.getTxtBuscador()).sendKeys(item);
        GoogleChromeDriver.driver.findElement(pgInicio.getBtnBuscador()).click();
        busquedaItemSteps.encontrarItem(item);
    }
```

##### Metodo buscarAnadirItem
_Este método hace llamado a los métodos buscarItem y AnadirAlCarrito con el fin de introducirlos en un ciclo for, el cual va a leer el archivo Excel y repetirá la acción de buscar el ítem, verificar que el nombre del ítem sea el mismo del archivo Excel y por último añadirlo al carrito, así con cada ítem que se encuentre en el archivo Excel_
```
public void buscarAnadirItem() throws IOException, InterruptedException {
        for (int i = 0 ; i < lecturaExcel.leerDatosDeHojaDeExcel("Productos.xlsx", "Hoja1").size() ; i++ ){
            buscarItem(lecturaExcel.leerDatosDeHojaDeExcel("Productos.xlsx", "Hoja1").get(i).get("Lista de productos"));
            visualizarItemSteps.validarNombre(lecturaExcel.leerDatosDeHojaDeExcel("Productos.xlsx", "Hoja1").get(i).get("Lista de productos"));
            visualizarItemSteps.anadirAlCarrito();
        }
```


#### Clase BusquedaItemSteps

##### Metodo encontrarItem

_Este método lo que hace es que busca el ítem que le pasemos como parámetro, el parámetro es un String ítem, y una vez encuentre dentro de la lista de productos de la página , el producto el cual tenga exactamente el mismo nombre le dará clic_

```
  public void encontrarItem(String item){
        pgBusquedaItem.setTxtElementoBuscado(item);
        GoogleChromeDriver.driver.findElement(pgBusquedaItem.getTxtElementoBuscado()).click();
    }
```

#### Clase VisualizarItemSteps

##### Metodo validarNombre
_La intención de este método es validar , si el nombre del producto encontrado, coincide con el nombre que le pasemos como parámetro, el parámetro es un String con el nombre nombreEsperado y se hace una validación con un assertEquals , en donde el parámetro nombreEsperado es el valor esperado , y el valor actual lo obtenemos directamente del xpath._
```
  public void validarNombre(String nombreEsperado){
        pgVisualizarItem.setTxtItemSeleccionado(nombreEsperado);
        Assert.assertEquals("El nombre no es el esperado",nombreEsperado, GoogleChromeDriver.driver.findElement(pgVisualizarItem.getTxtItemSeleccionado()).getText());

    }

```

##### Metodo anadirAlCarrito
_Este método lo que hace es darle clic al botón añadir al carrito y hacer una espera de 0.5 segundos para darle tiempo a la ventana modal que cargue y darle clic al botón seguir comprando_

```
 public void anadirAlCarrito() throws InterruptedException {
        Thread.sleep(500);
        GoogleChromeDriver.driver.findElement(pgVisualizarItem.getBtnAnadirAlCarrito()).click();
        Thread.sleep(500);
        GoogleChromeDriver.driver.findElement(pgVisualizarItem.getBtnSeguirComprando()).click();
    }

```

#### Clase CarritoSteps

##### Metodo abrirCarrito
_Este método da clic al botón del carrito para que cargue esta página_

```
public void abrirCarrito() {
        GoogleChromeDriver.driver.findElement(pgCarrito.getBtnAbrirCarrito()).click();
    }
```

##### Metodo validarValorTotal
_Este método , con un assertEquals , comparamos el valor total que nos da la página, con el valor total esperado, el valor total esperado se obtienen mediante el parámetro String valor , el cual es el único parámetro que recibe este método_


```
public void   validarValorTotal(String valor) {
        Assert.assertEquals("El valor no hace coincidencia",valor,GoogleChromeDriver.driver.findElement(pgCarrito.getTxtTotal()).getText());
    }
```

### Paquete feature
####  LinioComprador.feature
_Este paquete incluye el feature Linio comprador en donde trae el Scenario , el Given , When y Then_


```
Feature: HU-01 Busqueda de un grupo de productos en Linio para añadirlos al carrito
  Yo como usuario de Linio
  Quiero buscar un grupo de productos en la platadomra
  Para ver el nombre del producto en pantalla
  Y podre agregarlos al carrito
  Y conocer el valor total de los productos

  Scenario: Buscar un producto
    Given que busco un producto en linio
    When podre anadir el producto al carrito
    Then Podre ver la pagina del carrito en pantalla
```

### Paquete stepsDefinitons
_Con el archivo feauture obtenemos los nombres del @Given, @When y @Then_


#### Clase BuscadorStepsDefinitons

##### @Given("^que busco un producto en linio$")
_El método que se usa en el given es el queBuscoUnProductoEnLinio() el cual llama al método abrirPagina de una instancia de la clase inicioSteps_
```
 @Given("^que busco un producto en linio$")
    public void queBuscoUnProductoEnLinio() {
        inicioSteps.abrirPagina();
    }

```


##### @When("^podre anadir el producto al carrito$")
_El método que encontramos es el podreAnadirElProductoAlCarrito() el cual llama a buscarAnadirItem de una instancia de la clase inicioSteps_

```
@When("^podre anadir el producto al carrito$")
    public void podreAnadirElProductoAlCarrito() throws InterruptedException, IOException {
        inicioSteps.buscarAnadirItem();
    }

```
##### @Then("^Podre ver la pagina del carrito en pantalla$")
_Por parte del Then , tenemos el método podreVerLaPaginaDelCarritoEnPantalla()_
_En cuanto al método podreVerLaPaginaDelCarritoEnPantalla() , este hace llamado a otros 3 métodos en su cuerpo, primero tenemos el método abrirCarrito y validarTotal, ambos de la clase carritoSteps, y por último el método cerrarPagina de la clase inicioSteps_

```
 @Then("^Podre ver la pagina del carrito en pantalla$")
    public void podreVerLaPaginaDelCarritoEnPantalla() {
        carritoSteps.abrirCarrito();
        carritoSteps.validarValorTotal("$11.118.456");
        inicioSteps.cerrarPagina();
    }
```

### Paquete runner
_El paquete runner , como su nombre lo indica es que el correrá la prueba_ , en este paquete encontraremos la clase BuscadorRunner.

#### Clase buscador runner
_Esta clase puede parecer algo dificil pero como tal solo nos interesan el features y el glue, en donde el feautures solo vamos a poner la ruta de nuestro archivo y el apartado de glue , el donde vamos a poner nuestra clases de stepsDefinitions, es nuestra clase ejecutadora, es en donde almacenamos todas las intrucciones para que nuestra automatizacion las ejecute._

```
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src\\test\\resources\\features\\LinioComprador.feature",
        glue = "stepsDefinitions",
        snippets = SnippetType.CAMELCASE
)


public class BuscadorRunner {
}


```

# Esto sería todo por parte del proyecto de automatización a la página Linio, gracias por la atención prestada! 




