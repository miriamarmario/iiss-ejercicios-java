import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

	Map<Product, Integer> shoppingCart;

	public ShoppingCart() {
		shoppingCart = new HashMap<Product, Integer>();
	}

	public void addProduct(Product product, int number) {

		assert number > 0 : "No se puede anadir un producto con un numero de unidades negativo o nulo";

		if(shoppingCart.keySet().stream().filter(element -> element.getCode() == product.getCode()).count() == 0) {
			shoppingCart.put(product, number);
		}
	}

	public Product removeProduct(Product product) {
		if(shoppingCart.containsKey(product)) {
			shoppingCart.remove(product);
			return product;
		}  else {
			assert false : "No se puede eliminar un producto que no existe en el carrito";
			return null;
		}
	}

	public void printShoppingCartContent() {
		System.out.println("El contenido es: ");

		for(Product product: shoppingCart.keySet()) {
			System.out.println(product.getCode() + " - " + product.getName() + " : " + shoppingCart.get(product));
		}

	}
}