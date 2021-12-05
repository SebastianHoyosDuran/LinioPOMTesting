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

