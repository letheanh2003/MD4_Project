package ra.model.entity;

public class Product {
    private int id;
    private String name;
    private String title;
    private int quantity;
    private float price;
    private String thumbnailUrl;
    private Catalog catalogId;

    public Product() {
    }

    public Product(String name, String title, int quantity, float price, String thumbnailUrl, Catalog catalogId) {
        this.name = name;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.thumbnailUrl = thumbnailUrl;
        this.catalogId = catalogId;
    }

    public Product(int id, String name, String title, int quantity, float price, String thumbnailUrl, Catalog catalogId) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.thumbnailUrl = thumbnailUrl;
        this.catalogId = catalogId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Catalog getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Catalog catalogId) {
        this.catalogId = catalogId;
    }
}
