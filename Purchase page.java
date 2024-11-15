package ir.isc.training;


	import java.io.*;  
	import java.util.ArrayList;  
	import java.util.List;  
	import java.util.logging.level; 

public	class Product {  
	    private String name;  
	    private String category;  
	    private boolean isOnSale;  

	    public Product(String name, String category, boolean isOnSale) {  
	        this.name = name;  
	        this.category = category;  
	        this.isOnSale = isOnSale;  
	    }  

	    // Getters  
	    public String getName() { return name; }  
	    public String getCategory() { return category; }  
	    public boolean isOnSale() { return isOnSale; }  

	    @Override  
	    public String toString() {  
	        return "Product{" +  
	                "name='" + name + '\'' +  
	                ", category='" + category + '\'' +  
	                ", isOnSale=" + isOnSale +  
	                '}';  
	    }  
	}  

	class ProductCatalog {  
	    private List<Product> products;  

	    public ProductCatalog() {  
	        products = new ArrayList<>();  
	        // Adding some sample products  
	        products.add(new Product("Laptop", "Digital", true));  
	        products.add(new Product("Shirt", "Clothing", false));  
	        products.add(new Product("Vacuum Cleaner", "Home Appliance", true));  
	        products.add(new Product("Jeans", "Clothing", false));  
	        products.add(new Product("Smartphone", "Digital", true));  
	    }  

	    public List<Product> searchProducts(String name, String category) throws Exception {  
	        List<Product> result = new ArrayList<>();  

	        if (name == null && category == null) {  
	            throw new Exception("Both name and category cannot be null.");  
	        }  

	        for (Product product : products) {  
	            boolean matches = true;  

	            if (name != null && !name.isEmpty() && !product.getName().toLowerCase().contains(name.toLowerCase())) {  
	                matches = false;  
	            }  

	            if (category != null && !category.isEmpty() && !product.getCategory().equalsIgnoreCase(category)) {  
	                matches = false;  
	            }  

	            if (matches) {  
	                result.add(product);  
	            }  
	        }  

	        return result;  
	    }  
	}  

	public class Main {  
	    private static final Logger logger = Logger.getLogger(Main.class.getName());  

	    static {  
	        try {  
	            FileHandler fileHandler = new FileHandler("error.log", true);  
	            logger.addHandler(fileHandler);  
	            logger.setLevel(Level.SEVERE);  
	            SimpleFormatter formatter = new SimpleFormatter();  
	            fileHandler.setFormatter(formatter);  
	        } catch (IOException e) {  
	            System.err.println("Failed to initialize log file handler.");  
	        }  
	    }  

	    public static void main(String[] args) {  
	        ProductCatalog catalog = new ProductCatalog();  

	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {  
	            System.out.println("Enter product name to search (or leave empty): ");  
	            String name = reader.readLine().trim();  

	            System.out.println("Enter product category to search (Digital/Home Appliance/Clothing, or leave empty): ");  
	            String category = reader.readLine().trim();  

	            List<Product> results = catalog.searchProducts(name.isEmpty() ? null : name, category.isEmpty() ? null : category);  

	            if (results.isEmpty()) {  
	                System.out.println("No products found.");  
	            } else {  
	                System.out.println("Found products:");  
	                for (Product product : results) {  
	                    System.out.println(product);  
	                }  
	            }  

	        } catch (Exception e) {  
	            logger.severe("An error occurred: " + e.getMessage());  
	            System.out.println("An error occurred: " + e.getMessage());  
	        }  
	    }  
	}

}
