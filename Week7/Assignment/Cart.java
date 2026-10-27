/** Shopping cart exposing only a computed total and item count. */
public class Cart {

    private final String cartId;
    private final int[] prices;
    private int itemCount;

    public Cart(String cartId, int maxItems) {
        if (cartId == null || cartId.trim().isEmpty() || maxItems <= 0) {
            throw new IllegalArgumentException("Cart needs an ID and a positive size.");
        }
        this.cartId = cartId;
        this.prices = new int[maxItems];
    }

    public String getCartId() {
        return cartId;
    }

    public void addItem(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        if (itemCount == prices.length) {
            System.out.println("Cart full: cannot add item priced " + price);
            return;
        }
        prices[itemCount++] = price;
    }

    public int getTotal() {
        int total = 0;
        for (int i = 0; i < itemCount; i++) {
            total += prices[i];
        }
        return total;
    }

    public int getItemCount() {
        return itemCount;
    }

    public static void main(String[] args) {
        Cart cart = new Cart("CART-5", 20);
        cart.addItem(250);
        cart.addItem(99);
        cart.addItem(151);
        System.out.println("getTotal() -> " + cart.getTotal());
        System.out.println("getItemCount() -> " + cart.getItemCount());
    }
}
