import java.util.Optional;

public class Product {

	private int code;
	private String name;
	private String category;
	private double weight;
	private double height;

	public Product(int code, String name, String category, double weight, double height) {

		this.code = code;

		Optional<String> optionalName = Optional.ofNullable(name);
		this.name = optionalName.orElse("");

		Optional<String> optionalCategory = Optional.ofNullable(category);
		this.category = optionalCategory.orElse("");

		if (this.code < 0) {
			throw new IllegalArgumentException("El valor del atributo `code` no puede ser un numero negativo");
		}
		if (this.weight < 0) {
			throw new IllegalArgumentException("El valor del atributo `weight` no puede ser un numero negativo");
		}
		if (this.height < 0) {
			throw new IllegalArgumentException("El valor del atributo `height` no puede ser un numero negativo");
		}

		this.weight = weight;
		this.height = height;
	}

	public int getCode() {
		return code;
	}

	public void setName(String name) {
		Optional<String> optionalName = Optional.ofNullable(name);
		this.name = optionalName.orElse("");
	}

	public String getName() {
		return this.name;
	}

	public void setCategory(String category) {
		Optional<String> optionalCategory = Optional.ofNullable(category);
		this.category = optionalCategory.orElse("");
	}

	public String getCategory() {
		return this.category;
	}

	public void setWeight(double weight) {
		if (weight < 0) {
			throw new IllegalArgumentException("El valor del atributo `weight` no puede ser un numero negativo");
		}
		this.weight = weight;
	}

	public double getWeight() {
		return this.weight;
	}

	public void setHeight(double height) {
		if (height < 0) {
			throw new IllegalArgumentException("El valor del atributo `height` no puede ser un numero negativo");
		}
		this.height = height;
	}

	public double getHeight() {
		return this.height;
	}
}