import '../entities/cart_item.dart';
import '../entities/product.dart';

abstract class CartRepository {
  Future<void> load();
  Future<void> save();

  Map<String, CartItem> get itemsById;

  void add(Product product);
  void removeOne(String productId);
  void removeAll(String productId);
  void clear();
  double get totalPrice;
  int get totalItems;
}