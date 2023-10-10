import java.util.List;
import java.util.stream.Collectors;

public class Map {
    static class Product {
        String name;
        int price;

        public Product(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return this.name;
        }
    }

    public static void main(String[] args) {
        Product product1 = new Product("하나", 1);
        Product product3 = new Product("둘", 2);
        Product product2 = new Product("셋", 3);

        List<Product> productList = List.of(product1, product2, product3);

        List<String> productNameList = productList.stream().map(Product::getName)
                                    .collect(Collectors.toList());

        System.out.println(productNameList.toString());
    }
}
