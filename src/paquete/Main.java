package paquete;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


class Factura{
    String descripcion;
    int precio;
    String codigoProducto;
    int stock;
    LocalDate fechaFactura = LocalDate.of( 2020, 5,13) ;


    Factura(String descripcion,int precio, String codigoProducto, int stock){
        this.descripcion = descripcion;
        this.precio = precio;
        this.codigoProducto = codigoProducto;
        this.stock = stock;
        this.fechaFactura = fechaFactura;
    }

    String getDescripcion(){
        return this.descripcion;
    }

    String getCodigoProducto(){
        return this.codigoProducto;
    }

    int getPrecio(){
        return this.precio;
    }

    int getStock(){
        return this.stock;
    }

    LocalDate getFecha(){
        return this.fechaFactura;
    }


    public boolean obtenerFecha(LocalDate cantidad) {
        return this.fechaFactura.isBefore(cantidad) ;
    }

    public boolean obtenerStock(int cantidad) {
        return this.stock > cantidad;
    }

    public boolean obtenerPrecioMayor(int costo){
        return this.precio > costo;
    }

    public boolean obtenerPrecioMenor(int costo){
        return this.precio < costo;
    }
}


public class Main {


    public static void main(String[] args) {
        Factura f=new Factura("ordenador",1000, "KAZ", 1 );
        Factura f2=new Factura("movil",300, "JKL", 3);
        Factura f3=new Factura("impresora",200, "MLN", 5);
        Factura f4=new Factura("imac",1500, "OPA", 7);

        List<Factura> lista = new ArrayList<Factura>();

        lista.add(f);
        lista.add(f2);
        lista.add(f3);
        lista.add(f4);


        List<Factura> obtenerPrecioMayor = lista.stream().filter(c -> c.obtenerPrecioMayor(500)).collect(Collectors.toList());
        obtenerPrecioMayor.forEach( producto -> System.out.println("El producto  " + producto.getDescripcion() + " Es de mayor precio y vale " + producto.getPrecio()));

        List<Factura> obtenerPrecioMenor = lista.stream().filter(c -> c.obtenerPrecioMenor(500)).collect(Collectors.toList());
        obtenerPrecioMenor.forEach( producto -> System.out.println("El producto " + producto.getDescripcion()+ " Es de menor precio y vale " + producto.getPrecio()));

        List<Factura> obtenerStock = lista.stream().filter(c -> c.obtenerStock(0)).collect(Collectors.toList());
        obtenerStock.forEach( producto -> System.out.println("El producto " + producto.getDescripcion() + " Tiene " + producto.getStock() + " en stock") );

        List<Factura> obtenerFechaMenor = lista.stream().filter(c -> c.obtenerFecha(LocalDate.now())).collect(Collectors.toList());
        obtenerFechaMenor.forEach( producto -> System.out.println("El producto " + producto.getDescripcion() + " Fue vendido en la fecha " + producto.getFecha()) );

        Factura obtenerPorCodigo = lista.stream().filter( producto -> producto.getCodigoProducto() == "KAZ" ).findFirst().get();
        System.out.println("El producto " + obtenerPorCodigo.getDescripcion() + " coincide con el codigo");

    }

}