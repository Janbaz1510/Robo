import 'dart:convert';

import 'package:shared_preferences/shared_preferences.dart';

import '../../domain/entities/cart_item.dart';
import '../../domain/entities/product.dart';
import '../../domain/repositories/cart_repository.dart';
import '../../domain/repositories/product_repository.dart';

class CartRepositoryImpl implements CartRepository {
  static const String storageKey = 'shopsy_cart_v1';

  final ProductRepository productRepository;
  final Map<String, CartItem> _itemsById = {};

  CartRepositoryImpl(this.productRepository);

  @override
  Map<String, CartItem> get itemsById => _itemsById;

  @override
  Future<void> load() async {
    final prefs = await SharedPreferences.getInstance();
    final jsonStr = prefs.getString(storageKey);
    if (jsonStr == null) return;
    try {
      final decoded = json.decode(jsonStr) as Map<String, dynamic>;
      final List<Product> products = await productRepository.getProducts();
      final Map<String, Product> idToProduct = {for (final p in products) p.id: p};
      _itemsById.clear();
      decoded.forEach((productId, qtyDynamic) {
        final qty = (qtyDynamic as num).toInt();
        final product = idToProduct[productId];
        if (product != null && qty > 0) {
          _itemsById[productId] = CartItem(product: product, quantity: qty);
        }
      });
    } catch (_) {
      // Ignore corrupt storage
    }
  }

  @override
  Future<void> save() async {
    final prefs = await SharedPreferences.getInstance();
    final Map<String, int> payload = {
      for (final entry in _itemsById.entries) entry.key: entry.value.quantity,
    };
    await prefs.setString(storageKey, json.encode(payload));
  }

  @override
  void add(Product product) {
    final existing = _itemsById[product.id];
    if (existing != null) {
      existing.quantity += 1;
    } else {
      _itemsById[product.id] = CartItem(product: product, quantity: 1);
    }
  }

  @override
  void removeOne(String productId) {
    final existing = _itemsById[productId];
    if (existing == null) return;
    if (existing.quantity > 1) {
      existing.quantity -= 1;
    } else {
      _itemsById.remove(productId);
    }
  }

  @override
  void removeAll(String productId) {
    _itemsById.remove(productId);
  }

  @override
  void clear() {
    _itemsById.clear();
  }

  @override
  double get totalPrice {
    return _itemsById.values.fold(0.0, (sum, item) => sum + item.totalPrice);
  }

  @override
  int get totalItems {
    return _itemsById.values.fold(0, (sum, item) => sum + item.quantity);
  }
}